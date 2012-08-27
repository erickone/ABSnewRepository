/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AdministrativeClassifierController
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.common.dto.TreeNodeDto;
import com.abs.siif.common.support.component.TreeNodeComponent;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.planning.management.RegClassifManagement;
import com.abs.siif.planning.management.RegionalClassifierLevelManagement;
import com.abs.siif.ppp.planning.api.controller.RegionalCatalogControllerApi;
import com.abs.siif.ppp.planning.uihelpers.RegionalCatalogUIHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Yako
 */
@Scope("session")
@Controller("regionalCatalogController")
public class RegionalCatalogController extends SIIFControllerBase
        implements Serializable, RegionalCatalogControllerApi
{

  private static final String REGIONAL_CLASSIFIER_KEY = "regionalClassifierKey";
  private static final String REGIONAL_CLASSIFIER_DESCRIPTION = "regionalClassifierDescription";
  private static final String ROOT_NODE_TEXT = "Regiones";
  private static final String REGIONAL_CLASSIFIER_ID = "regionalClassifierId";
  private static final String PROPERTY_LEVEL_IN_MAIN_CLASS = "regionalLevelClassifier";
  private static final String PROPERTY_LEVEL = "regionalLevelClassifierLevel";
  private static final String PROPETY_IDENTITY_LEVEL = "regionalLevelClassifierId";
  @Resource(name = "RegClassifManagement")
  private transient RegClassifManagement itsRegionalClassifier;
  @Resource(name = "treeNodeComponent")
  private TreeNodeComponent itsTreeNodeComponent;
  @Resource(name = "regionalClassifierLevelManagement")
  private RegionalClassifierLevelManagement itsRegionalLevelClassifierManagement;
  private Collection<String> itsCompositeName;
  private String theirKey;
  private String theirDescription;
  private String theirZipCode;
  private boolean isFrontier;
  private String theirFronterizo;
  private String theirInegiKey;
  private int theirNumberOfMen;
  private int theirNumberOfWomen;
  private int theirInegiYear;
  private String theirSelectedRegionalLevel;
  private String theirSelectedRegion;
  private RegionalClassifierEntity theirRegion;
  private TreeNode theirTreeNode;
  private TreeNode theirSelectedNode;
  private boolean isLevelGenreDisabled;
  private boolean isLevelZipCodeDisabled;
  private boolean isLevelFrontierDisbled;
  private String theirComposedHeaderName;
  private SaveType theirSaveType;
  private RegionalClassifierEntity theirRegionToSave;
  private RegionalLevelClassifierEntity theirLevelToSave;
  private boolean isSaveButtonDisabled;
  private RegionalCatalogUIHelper itsRegionalCatalogHelper;

  /**
   * Código de la sección del treeview
   */
  @Override
  public void prepareToEdit()
  {
    if (getTheirSelectedNode() != null)
    {
      TreeNodeDto mySelectedNode =
              (TreeNodeDto) theirSelectedNode.getData();
      RegionalClassifierEntity myRegion = new RegionalClassifierEntity();
      myRegion.setRegionalClassifierId(mySelectedNode.getItsIdentity());

      myRegion = this.itsRegionalClassifier.getRegionalClassifierById(myRegion);

      RegionalLevelClassifierEntity myRegionalLevel = myRegion.getRegionalLevelClassifier();
      myRegion.setRegionalLevelClassifier(myRegionalLevel);
      this.theirComposedHeaderName = "Nivel: " + myRegionalLevel.getRegionalLevelClassifierKey()
              + " " + myRegionalLevel.getRegionalLevelClassifierDescription();
      if (myRegion != null)
      {
        this.setIsLevelGenreDisabled(!myRegionalLevel.isRegionalLevelClassifierIsGender());
        this.setIsLevelZipCodeDisabled(!myRegionalLevel.isRegionalLevelClassifierIsPostalCode());
        this.setIsLevelFrontierDisbled(!myRegion.getRegionalClassifierIsBorderLine());

        this.setTheirRegion(this.itsRegionalClassifier.getRegionalClassifierById(myRegion));
        this.setTheirKey(myRegion.getRegionalClassifierKey());
        this.setTheirDescription(myRegion.getRegionalClassifierDescription());
        this.setTheirZipCode(myRegion.getRegionalClassifierPostalCode().toString());
        this.setIsFrontier(myRegion.getRegionalClassifierIsBorderLine());
        this.setTheirInegiKey(myRegion.getRegionalClassifierINEGIKey());
        this.setTheirNumberOfMen(myRegion.getRegionalClassifierMenNumber());
        this.setTheirNumberOfWomen(myRegion.getRegionalClassifierWomenNumber());
        this.setTheirInegiYear(myRegion.getRegionalClassifierINEGIYear());
        this.setTheirSaveType(SaveType.UPDATE);
      }
    }
  }

  @Override
  public void initRegionalCatalog() throws NoSuchFieldException, IllegalAccessException
  {
    this.setTheirTreeNode(null);
    Collection<RegionalClassifierEntity> myRegions;
    myRegions = this.itsRegionalClassifier.getRegionalClassifierWithoutFather();
    itsCompositeName = new ArrayList<String>();
    itsCompositeName.add(REGIONAL_CLASSIFIER_KEY);
    itsCompositeName.add(REGIONAL_CLASSIFIER_DESCRIPTION);
        itsTreeNodeComponent.setItsTreeNode(null);
    itsTreeNodeComponent.initTreeNode(myRegions, ROOT_NODE_TEXT, REGIONAL_CLASSIFIER_ID,
            itsCompositeName, RegionalClassifierEntity.class,
            PROPERTY_LEVEL_IN_MAIN_CLASS, PROPERTY_LEVEL, PROPETY_IDENTITY_LEVEL);
    this.setTheirTreeNode(this.itsTreeNodeComponent.getItsTreeNode());
  }

  @Override
  public void onNodeExpand(NodeExpandEvent aNodeExpandEvent)
  {
    TreeNode myExpandedNode = aNodeExpandEvent.getTreeNode();
    TreeNodeDto mySelectedNode = (TreeNodeDto) myExpandedNode.getData();
    RegionalClassifierEntity myRegion = new RegionalClassifierEntity();
    myRegion.setRegionalClassifierId(mySelectedNode.getItsIdentity());

    List<RegionalClassifierEntity> myRegions = this.itsRegionalClassifier.getRegionsByFatherId(myRegion);
    try
    {
      if (!myRegions.isEmpty())
      {
        itsTreeNodeComponent.loadChilds(myRegions, myExpandedNode,
                REGIONAL_CLASSIFIER_ID, itsCompositeName, RegionalClassifierEntity.class,
                PROPERTY_LEVEL_IN_MAIN_CLASS, PROPERTY_LEVEL, PROPETY_IDENTITY_LEVEL);
      }

    }
    catch (NoSuchFieldException ex)
    {
      Logger.getLogger(AdministrativeClassifierController.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
      Logger.getLogger(AdministrativeClassifierController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void saveOrUpdateRegionalCatalog()
  {
    try
    {
      this.setTheirRegionToSave(new RegionalClassifierEntity());
      TreeNodeDto mySelectedNode =
              (TreeNodeDto) theirSelectedNode.getData();

      //Obtener padre
      RegionalClassifierEntity myRegionFather = new RegionalClassifierEntity();
      myRegionFather.setRegionalClassifierId(mySelectedNode.getItsIdentity());
      myRegionFather = this.itsRegionalClassifier.getRegionalClassifierById(myRegionFather).getRegionalclasiffather();

      this.theirRegionToSave.setRegionalClassifierKey(this.getTheirKey());
      this.theirRegionToSave.setRegionalClassifierDescription(this.getTheirDescription());
      this.theirRegionToSave.setRegionalClassifierPostalCode(Integer.parseInt(this.getTheirZipCode()));
      this.theirRegionToSave.setRegionalClassifierIsBorderLine(this.isFrontier());
      this.theirRegionToSave.setRegionalClassifierINEGIKey(this.getTheirInegiKey());
      this.theirRegionToSave.setRegionalClassifierMenNumber(this.getTheirNumberOfMen());
      this.theirRegionToSave.setRegionalClassifierWomenNumber(this.getTheirNumberOfWomen());
      this.theirRegionToSave.setRegionalClassifierINEGIYear(this.getTheirInegiYear());
      this.theirRegionToSave.setRegionalclasiffather(myRegionFather);

      switch (this.theirSaveType)
      {
        case UPDATE:
        {
          //Obtener el Id del Clasif Regional
          RegionalLevelClassifierEntity myRegionLevel = new RegionalLevelClassifierEntity();
          myRegionLevel.setRegionalLevelClassifierId(mySelectedNode.getItsLevelId());
          myRegionLevel = this.itsRegionalLevelClassifierManagement.getRegionalLevelClassifierById(myRegionLevel);

          this.theirRegionToSave.setRegionalclasiffather(myRegionFather);
          this.theirRegionToSave.setRegionalLevelClassifier(myRegionLevel);
          this.theirRegionToSave.setRegionalClassifierId(mySelectedNode.getItsIdentity());

          break;
        }
        case SAVE:
        {
          this.theirRegionToSave.setRegionalclasiffather(myRegionFather);
          this.theirRegionToSave.setRegionalLevelClassifier(this.getTheirLevelToSave());
          this.theirRegionToSave.setRegionalClassifierId(null);
        }
      }
      this.itsRegionalCatalogHelper = new RegionalCatalogUIHelper();
      if (!this.itsRegionalCatalogHelper.validateRequiredFields(theirRegionToSave))
      {
      }
      else
      {

        long myRegionId = itsRegionalClassifier.saveOrUpdateRegion(getTheirRegionToSave());
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, "Guardado Correctamente", getMapKeyExcpetion("Guardado corectamente"));
        //Cargar Region que se guardó
        RegionalClassifierEntity myRegion = new RegionalClassifierEntity();
        myRegion.setRegionalClassifierId(myRegionId);
        myRegion = this.itsRegionalClassifier.getRegionalClassifierById(myRegion);

        String myNodeText = myRegion.getRegionalClassifierKey() + ' ' + myRegion.getRegionalClassifierDescription();

        //Actualizar arbol
        if (this.getTheirSaveType() == SaveType.UPDATE)
        {
          ((TreeNodeDto) this.theirSelectedNode.getData()).setItsNodeText(myNodeText);
        }
        else
        {
          TreeNodeDto myNewDto = new TreeNodeDto(mySelectedNode.getItsIdentity(),
                  myRegion.getRegionalClassifierId(),
                  myNodeText,
                  false,
                  myRegion.getRegionalLevelClassifier().getRegionalLevelClassifierLevel(),
                  myRegion.getRegionalLevelClassifier().getRegionalLevelClassifierId());
          TreeNode myChild = new DefaultTreeNode(myNewDto, this.theirSelectedNode);
        }
      }
    }
    catch (Exception ex)
    {
      ex.toString();
    }
  }

  private void restartNodeTreeview()
  {
    theirTreeNode = null;
  }

  @Override
  public void prepareNewItem()
  {
    this.setTheirDescription("");
    this.setTheirKey("");
    this.setTheirZipCode("0");
    this.setTheirInegiKey("");
    this.setTheirNumberOfMen(0);
    this.setTheirNumberOfWomen(0);
    this.setTheirInegiKey("");
    this.setTheirSaveType(SaveType.SAVE);
    this.setIsSaveButtonDisabled(Boolean.TRUE);

    //Esta variable sirve para determinar los campos que serán o no desactivos
    RegionalLevelClassifierEntity myLevel = new RegionalLevelClassifierEntity();
    TreeNodeDto myDto = (TreeNodeDto) theirSelectedNode.getData();

    Long myRegionId = myDto.getItsIdentity();
 

    myLevel.setRegionalLevelClassifierId(myDto.getItsLevelId());



    myLevel = itsRegionalLevelClassifierManagement.getRegionalLevelClassifierById(myLevel);



    if (myLevel != null)
    {
      /*this.theirComposedHeaderName = "Nivel: " + myLevel.getRegionalLevelClassifierKey()
              + " " + myLevel.getRegionalLevelClassifierDescription();*/
      this.setIsLevelGenreDisabled(!myLevel.isRegionalLevelClassifierIsGender());
      this.setIsLevelZipCodeDisabled(!myLevel.isRegionalLevelClassifierIsPostalCode());
      this.setIsLevelFrontierDisbled(!myLevel.isRegionalLevelClassifierIsMunicipality());

    }

    //se encarga de setear el nivel
    try
    {
      if (myDto.getItsLevel() > 0)
      {
        myRegionId = myDto.getItsIdentity();
        int myNextLevel = myDto.getItsLevel() + 1;
        this.setTheirLevelToSave(getRegionalLevel(myNextLevel));
      }
      else
      {
        int myInitialLevel = 1;
        this.setTheirLevelToSave(this.itsRegionalLevelClassifierManagement.getRegionalClassifierLevelByLevel(myInitialLevel));
      }
      this.theirComposedHeaderName = "Nivel: " + this.getTheirLevelToSave().getRegionalLevelClassifierKey()
              + " " + this.getTheirLevelToSave().getRegionalLevelClassifierDescription();
      this.setIsSaveButtonDisabled(Boolean.FALSE);
    }
    catch (Exception ex)
    {
      this.setIsSaveButtonDisabled(Boolean.TRUE);
      addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, getMapKeyExcpetion(ex.getMessage()),
              getMapKeyExcpetion(ex.getMessage()));
    }
  }

  private RegionalLevelClassifierEntity getRegionalLevel(int aLevel) throws Exception
  {
    RegionalLevelClassifierEntity myRegionalLevel = this.itsRegionalLevelClassifierManagement.getRegionalClassifierLevelByLevel(aLevel);
    int myLevel = aLevel - 1;

    if (myRegionalLevel == null)
    {
      throw new Exception(getMessage("ppp.planning.excpLevelConfigured", "" + myLevel));
    }

    return myRegionalLevel;

  }

  @Override
  public String returnTomainMenu()
  {
    return "options";
  }

  /**
   * @return the theirKey
   */
  public String getTheirKey()
  {
    return theirKey;
  }

  /**
   * @param theirKey the theirKey to set
   */
  public void setTheirKey(String theirKey)
  {
    this.theirKey = theirKey;
  }

  /**
   * @return the theirDescription
   */
  public String getTheirDescription()
  {
    return theirDescription;
  }

  /**
   * @param theirDescription the theirDescription to set
   */
  public void setTheirDescription(String theirDescription)
  {
    this.theirDescription = theirDescription;
  }

  /**
   * @return the theirZipCode
   */
  public String getTheirZipCode()
  {
    return theirZipCode;
  }

  /**
   * @param theirZipCode the theirZipCode to set
   */
  public void setTheirZipCode(String theirZipCode)
  {
    this.theirZipCode = theirZipCode;
  }

  /**
   * @return the isFrontier
   */
  public boolean isFrontier()
  {
    return isFrontier;
  }

  /**
   * @param isFrontier the isFrontier to set
   */
  public void setIsFrontier(boolean isFrontier)
  {
    this.isFrontier = isFrontier;
  }

  /**
   * @return the theirInegiKey
   */
  public String getTheirInegiKey()
  {
    return theirInegiKey;
  }

  /**
   * @param theirInegiKey the theirInegiKey to set
   */
  public void setTheirInegiKey(String theirInegiKey)
  {
    this.theirInegiKey = theirInegiKey;
  }

  /**
   * @return the theirNumberOfMen
   */
  public int getTheirNumberOfMen()
  {
    return theirNumberOfMen;
  }

  /**
   * @param theirNumberOfMen the theirNumberOfMen to set
   */
  public void setTheirNumberOfMen(int theirNumberOfMen)
  {
    this.theirNumberOfMen = theirNumberOfMen;
  }

  /**
   * @return the theirNumberOfWomen
   */
  public int getTheirNumberOfWomen()
  {
    return theirNumberOfWomen;
  }

  /**
   * @param theirNumberOfWomen the theirNumberOfWomen to set
   */
  public void setTheirNumberOfWomen(int theirNumberOfWomen)
  {
    this.theirNumberOfWomen = theirNumberOfWomen;
  }

  /**
   * @return the theirInegiYear
   */
  public int getTheirInegiYear()
  {
    return theirInegiYear;
  }

  /**
   * @param theirInegiYear the theirInegiYear to set
   */
  public void setTheirInegiYear(int theirInegiYear)
  {
    this.theirInegiYear = theirInegiYear;
  }

  /**
   * @return the theirSelectedRegionalLevel
   */
  public String getTheirSelectedRegionalLevel()
  {
    return theirSelectedRegionalLevel;
  }

  /**
   * @param theirSelectedRegionalLevel the theirSelectedRegionalLevel to set
   */
  public void setTheirSelectedRegionalLevel(String theirSelectedRegionalLevel)
  {
    this.theirSelectedRegionalLevel = theirSelectedRegionalLevel;
  }

  /**
   * @return the itsRegionalClassifier
   */
  public RegClassifManagement getItsRegionalClassifier()
  {
    return itsRegionalClassifier;
  }

  /**
   * @param itsRegionalClassifier the itsRegionalClassifier to set
   */
  public void setItsRegionalClassifier(RegClassifManagement itsRegionalClassifier)
  {
    this.itsRegionalClassifier = itsRegionalClassifier;
  }

  /**
   * @return the theirSelectedRegion
   */
  public String getTheirSelectedRegion()
  {
    return theirSelectedRegion;
  }

  /**
   * @param theirSelectedRegion the theirSelectedRegion to set
   */
  public void setTheirSelectedRegion(String theirSelectedRegion)
  {
    this.theirSelectedRegion = theirSelectedRegion;
  }

  /**
   * @return the theirRegion
   */
  public RegionalClassifierEntity getTheirRegion()
  {
    return theirRegion;
  }

  /**
   * @param theirRegion the theirRegion to set
   */
  public void setTheirRegion(RegionalClassifierEntity theirRegion)
  {
    this.theirRegion = theirRegion;
  }

  /**
   * @return the theirTreeNode
   */
  public TreeNode getTheirTreeNode()
  {
    return theirTreeNode;
  }

  /**
   * @param theirTreeNode the theirTreeNode to set
   */
  public void setTheirTreeNode(TreeNode theirTreeNode)
  {
    this.theirTreeNode = theirTreeNode;
  }

  /**
   * @return the itsTreeNodeComponent
   */
  public TreeNodeComponent getItsTreeNodeComponent()
  {
    return itsTreeNodeComponent;
  }

  /**
   * @param itsTreeNodeComponent the itsTreeNodeComponent to set
   */
  public void setItsTreeNodeComponent(TreeNodeComponent itsTreeNodeComponent)
  {
    this.itsTreeNodeComponent = itsTreeNodeComponent;
  }

  /**
   * @return the itsCompositeName
   */
  public Collection<String> getItsCompositeName()
  {
    return itsCompositeName;
  }

  /**
   * @param itsCompositeName the itsCompositeName to set
   */
  public void setItsCompositeName(Collection<String> itsCompositeName)
  {
    this.itsCompositeName = itsCompositeName;
  }

  /**
   * @return the theirFronterizo
   */
  public String getTheirFronterizo()
  {
    return theirFronterizo;
  }

  /**
   * @param theirFronterizo the theirFronterizo to set
   */
  public void setTheirFronterizo(String theirFronterizo)
  {
    this.theirFronterizo = theirFronterizo;
  }

  /**
   * @return the theirSelectedNode
   */
  public TreeNode getTheirSelectedNode()
  {
    return theirSelectedNode;
  }

  /**
   * @param theirSelectedNode the theirSelectedNode to set
   */
  public void setTheirSelectedNode(TreeNode theirSelectedNode)
  {
    this.theirSelectedNode = theirSelectedNode;
  }

  /**
   * @return the isLevelGenreDisabled
   */
  public boolean isIsLevelGenreDisabled()
  {
    return isLevelGenreDisabled;
  }

  /**
   * @param isLevelGenreDisabled the isLevelGenreDisabled to set
   */
  public void setIsLevelGenreDisabled(boolean isLevelGenreDisabled)
  {
    this.isLevelGenreDisabled = isLevelGenreDisabled;
  }

  /**
   * @return the isLevelZipCodeDisabled
   */
  public boolean isIsLevelZipCodeDisabled()
  {
    return isLevelZipCodeDisabled;
  }

  /**
   * @param isLevelZipCodeDisabled the isLevelZipCodeDisabled to set
   */
  public void setIsLevelZipCodeDisabled(boolean isLevelZipCodeDisabled)
  {
    this.isLevelZipCodeDisabled = isLevelZipCodeDisabled;
  }

  /**
   * @return the isLevelFrontierDisbled
   */
  public boolean isIsLevelFrontierDisbled()
  {
    return isLevelFrontierDisbled;
  }

  /**
   * @param isLevelFrontierDisbled the isLevelFrontierDisbled to set
   */
  public void setIsLevelFrontierDisbled(boolean isLevelFrontierDisbled)
  {
    this.isLevelFrontierDisbled = isLevelFrontierDisbled;
  }

  /**
   * @return the itsRegionalLevelClassifierManagement
   */
  public RegionalClassifierLevelManagement getItsRegionalLevelClassifierManagement()
  {
    return itsRegionalLevelClassifierManagement;
  }

  /**
   * @param itsRegionalLevelClassifierManagement the itsRegionalLevelClassifierManagement
   * to set
   */
  public void setItsRegionalLevelClassifierManagement(RegionalClassifierLevelManagement itsRegionalLevelClassifierManagement)
  {
    this.itsRegionalLevelClassifierManagement = itsRegionalLevelClassifierManagement;
  }

  /**
   * @return the theirComposedHeaderName
   */
  public String getTheirComposedHeaderName()
  {
    return theirComposedHeaderName;
  }

  /**
   * @param theirComposedHeaderName the theirComposedHeaderName to set
   */
  public void setTheirComposedHeaderName(String theirComposedHeaderName)
  {
    this.theirComposedHeaderName = theirComposedHeaderName;
  }

  /**
   * @return the theirSaveType
   */
  public SaveType getTheirSaveType()
  {
    return theirSaveType;
  }

  /**
   * @param theirSaveType the theirSaveType to set
   */
  public void setTheirSaveType(SaveType theirSaveType)
  {
    this.theirSaveType = theirSaveType;
  }

  /**
   * @return the theirRegionToSave
   */
  public RegionalClassifierEntity getTheirRegionToSave()
  {
    return theirRegionToSave;
  }

  /**
   * @param theirRegionToSave the theirRegionToSave to set
   */
  public void setTheirRegionToSave(RegionalClassifierEntity theirRegionToSave)
  {
    this.theirRegionToSave = theirRegionToSave;
  }

  /**
   * @return the theirLevelToSave
   */
  public RegionalLevelClassifierEntity getTheirLevelToSave()
  {
    return theirLevelToSave;
  }

  /**
   * @param theirLevelToSave the theirLevelToSave to set
   */
  public void setTheirLevelToSave(RegionalLevelClassifierEntity theirLevelToSave)
  {
    this.theirLevelToSave = theirLevelToSave;
  }

  /**
   * @return the isSaveButtonDisabled
   */
  public boolean isIsSaveButtonDisabled()
  {
    return isSaveButtonDisabled;
  }

  /**
   * @param isSaveButtonDisabled the isSaveButtonDisabled to set
   */
  public void setIsSaveButtonDisabled(boolean isSaveButtonDisabled)
  {
    this.isSaveButtonDisabled = isSaveButtonDisabled;
  }

  /**
   * @return the itsRegionalCatalogHelper
   */
  public RegionalCatalogUIHelper getItsRegionalCatalogHelper()
  {
    return itsRegionalCatalogHelper;
  }

  /**
   * @param itsRegionalCatalogHelper the itsRegionalCatalogHelper to set
   */
  public void setItsRegionalCatalogHelper(RegionalCatalogUIHelper itsRegionalCatalogHelper)
  {
    this.itsRegionalCatalogHelper = itsRegionalCatalogHelper;
  }

  public void deleteRegionalClassifier()
  {
    FacesMessage.Severity myMessageFaces = FacesMessage.SEVERITY_INFO;
    String myMessageUI = this.getMessage("ppp.progr.succesSave");
    try
    {

      if (theirSelectedNode != null)
      {
        TreeNodeDto myDto = (TreeNodeDto) theirSelectedNode.getData();
        RegionalClassifierEntity myRegion = new RegionalClassifierEntity();
        myRegion.setRegionalClassifierId(myDto.getItsIdentity());
        this.itsRegionalClassifier.deleteRegionalClassifier(myRegion);
        restartTreeNode();
        this.initRegionalCatalog();

      }
    }
    catch (Exception ex)
    {
      myMessageFaces = FacesMessage.SEVERITY_ERROR;
      myMessageUI = ex.getMessage();
    }
    finally
    {
      addMessageCurrentInstance(myMessageFaces,
              "",
              myMessageUI);
    }
  }
      public void restartTreeNode() throws NoSuchFieldException, IllegalAccessException {
        this.itsTreeNodeComponent.setItsTreeNode(null);
        this.initRegionalCatalog();
    }
}