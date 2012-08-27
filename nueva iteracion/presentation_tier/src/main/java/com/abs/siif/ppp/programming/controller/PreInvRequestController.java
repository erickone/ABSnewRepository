package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.entities.FederalDependenceEntity;
import com.abs.siif.budget.entities.PreInvRequestEntity;
import com.abs.siif.budget.entities.RequestUploadFilesEntity;
import com.abs.siif.ppp.planning.uihelpers.RequestUploadFileDataModel;
import com.abs.siif.ppp.programming.api.controller.PreInvRequestControllerApi;
import com.abs.siif.ppp.programming.uihelpers.PreInvRequestUIHelper;
import com.abs.siif.programming.entities.FederalURRegulatoryEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.management.PreInvRequestManagement;
import com.abs.siif.programming.management.RequestUploadFilesManagement;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jacob.flores
 */
@Controller("PreInvRequestController")
@Scope("session")
public class PreInvRequestController extends SIIFControllerBase implements Serializable, PreInvRequestControllerApi
{
  @Resource(name="preInvRequestManagement")
  private transient PreInvRequestManagement itsPreInvRequestManagement;
  @Resource( name = "requestUploadFilesManagement")
  private transient RequestUploadFilesManagement itsRequestUploadFilesManagement;
 
  private InvPreFileEntity theirInvPreFileEntity;


  private PreInvRequestUIHelper itsPreInvRequestUIHelper = new PreInvRequestUIHelper();
  private int isIntegrationAct;
  private int isAcceptanceAct;
  private int isFeasibilityDictum;
  private int isEnvImpactDictum;
  private int isAccreditationOfProperty;
  private int isAuthOfUse;
  private int isSoilMechanicsStudy;
  private int isExecutiveProject;
  private int isCostBenefitStudy;
  private int isAdvanceLevel;
  private String theirOtherStudiesDescription;
  private String theirFederalDependenceSelected;
  private Long theirFederalDepSelectedId;
  private Collection<FederalDependenceEntity> theirFederalDependence;
  private String theirAuhtNumber;
  private String theirFileName;
  private Long theirFederalDependenceSelectedId;
  private Long theirPreFileId;
  private Long theirActualPreInvRequestId;
  private boolean isRdButtonActive;

  //Estas variables son para poblar y guardar valores del DataTable.
  private RequestUploadFileDataModel itsRUFDataModel = null;
  private RequestUploadFilesEntity itsSelectedRUFile = null;
  private List<RequestUploadFilesEntity> itsRUFileList = new ArrayList<RequestUploadFilesEntity>();
  
  //Objeto del archivo a guardar en la relacion.
  RequestUploadFilesEntity itsUploadedFile;

    @Override
  public void init()
  {
  
    theirInvPreFileEntity = new InvPreFileEntity();
    itsUploadedFile = new RequestUploadFilesEntity();
    theirFileName = "";

    if(this.theirPreFileId != null && theirPreFileId >0)
    {
        
      //TODO:Reemplazar por el id de la Preficha
      this.theirInvPreFileEntity.setInvPreFileId(theirPreFileId);
      InvPreFileEntity invPreFileById = itsPreInvRequestManagement.getInvPreFileById(theirPreFileId);
      FederalURRegulatoryEntity invPreFileURRegulatory = invPreFileById.getInvPreFileURRegulatory();
      if(invPreFileURRegulatory != null ){
           this.theirFederalDependenceSelected = invPreFileURRegulatory.getFederalUrRegulatoryKey() +" "+invPreFileURRegulatory.getFederalUrRegulatoryDescription();
           this.theirFederalDepSelectedId = invPreFileURRegulatory.getFederalUrRegulatoryId();
      }else{
           this.theirFederalDependenceSelected = "";
           this.theirFederalDepSelectedId = null;
      }    
      this.setFederalDependence(loadFederalDependencesList());
      prepareElements();
      activateRadioButtons();
    }
  }

  public PreInvRequestController(){}

  private void prepareElements()
  {
    //TODO: Cambiar en vez de una lista, sea un solo objeto al hacer un getById de una Entity externa
    List<PreInvRequestEntity> myPreInvRequest = new ArrayList<PreInvRequestEntity>();

    myPreInvRequest = (List<PreInvRequestEntity>)itsPreInvRequestManagement.getPreInvRequestByPreFileId(this.theirPreFileId);
    if(!myPreInvRequest.isEmpty())
    {
      this.setIsIntegrationAct(itsPreInvRequestUIHelper.converterBooleanExpresionToInt(
              myPreInvRequest.get(0).isIntegrationAct()));

      this.setIsAcceptanceAct(itsPreInvRequestUIHelper.converterBooleanExpresionToInt(
              myPreInvRequest.get(0).isAcceptanceAct()));

      this.setIsFeasibilityDictum(itsPreInvRequestUIHelper.converterBooleanExpresionToInt(
              myPreInvRequest.get(0).isFeasibilityDictum()));

      this.setIsEnvImpactDictum(itsPreInvRequestUIHelper.converterBooleanExpresionToInt(
              myPreInvRequest.get(0).isEnvImpactDictum()));

      this.setIsAccreditationOfProperty(itsPreInvRequestUIHelper.converterBooleanExpresionToInt(
              myPreInvRequest.get(0).isAccreditationOfProperty()));

      this.setIsAuthOfUse(itsPreInvRequestUIHelper.converterBooleanExpresionToInt(
              myPreInvRequest.get(0).isAuthoOfUse()));

      this.setIsSoilMechanicsStudy(itsPreInvRequestUIHelper.converterBooleanExpresionToInt(
              myPreInvRequest.get(0).isSoilMechanics()));

      this.setIsExecutiveProject(itsPreInvRequestUIHelper.converterBooleanExpresionToInt(
              myPreInvRequest.get(0).isExecutiveProject()));

      this.setIsCostBenefitStudy(itsPreInvRequestUIHelper.converterBooleanExpresionToInt(
              myPreInvRequest.get(0).isCostBenefitStudy()));

      this.setIsAdvanceLevel(itsPreInvRequestUIHelper.converterBooleanExpresionToInt(
              myPreInvRequest.get(0).isAdvanceLevel()));

      this.setOtherStudiesDescription(myPreInvRequest.get(0).getOtherStudiesDescription());

      this.setAuhtNumber(myPreInvRequest.get(0).getAuhtNumber());

      //this.setFileName(myPreInvRequest.get(0).getFileName());

      this.setFederalDependenceSelectedId(myPreInvRequest.get(0).getFederalDependenceId().getFederalUrRegulatoryId());
      
      this.setActualPreInvRequestId(myPreInvRequest.get(0).getPreInvRequestId());
      
      this.itsRUFDataModel = new RequestUploadFileDataModel(
                myPreInvRequest.get(0).getUploadedFiles());
      
      this.itsRUFileList = myPreInvRequest.get(0).getUploadedFiles();
      
    }
    else
    {
      this.setIsIntegrationAct(0);
      this.setIsAcceptanceAct(0);
      this.setIsFeasibilityDictum(0);
      this.setIsEnvImpactDictum(0);
      this.setIsAccreditationOfProperty(0);
      this.setIsAuthOfUse(0);
      this.setIsSoilMechanicsStudy(0);
      this.setIsExecutiveProject(0);
      this.setIsCostBenefitStudy(0);
      this.setIsAdvanceLevel(0);
      this.setOtherStudiesDescription("");
      this.setAuhtNumber("");
      this.setFileName("");
      this.setFederalDependenceSelectedId(new Long(0));
      this.itsRUFileList.clear();
      this.itsRUFDataModel = new RequestUploadFileDataModel();
      this.setActualPreInvRequestId(null);
    }

  }
    @Override
  public void savePreInvRequest()
  {
    PreInvRequestEntity myEntityToSave = new PreInvRequestEntity();

    myEntityToSave.setPreInvRequestId(this.getActualPreInvRequestId());
    myEntityToSave.setIntegrationAct(this.itsPreInvRequestUIHelper.converterIntExpresionToBoolean(
            this.getIsIntegrationAct()));

    myEntityToSave.setAcceptanceAct(this.itsPreInvRequestUIHelper.converterIntExpresionToBoolean(
            this.getIsAcceptanceAct()));

    myEntityToSave.setFeasibilityDictum(this.itsPreInvRequestUIHelper.converterIntExpresionToBoolean(
            this.getIsFeasibilityDictum()));

    myEntityToSave.setEnvImpactDictum(this.itsPreInvRequestUIHelper.converterIntExpresionToBoolean(
            this.getIsEnvImpactDictum()));

    myEntityToSave.setAccreditationOfProperty(this.itsPreInvRequestUIHelper.converterIntExpresionToBoolean(
            this.getIsAccreditationOfProperty()));

    myEntityToSave.setAuthoOfUse(this.itsPreInvRequestUIHelper.converterIntExpresionToBoolean(
            this.getIsAuthOfUse()));

    myEntityToSave.setSoilMechanics(this.itsPreInvRequestUIHelper.converterIntExpresionToBoolean(
            this.getIsSoilMechanicsStudy()));

    myEntityToSave.setExecutiveProject(this.itsPreInvRequestUIHelper.converterIntExpresionToBoolean(
            this.getIsExecutiveProject()));
    myEntityToSave.setCostBenefitStudy(this.itsPreInvRequestUIHelper.converterIntExpresionToBoolean(
            this.getIsCostBenefitStudy()));
    myEntityToSave.setAdvanceLevel(this.itsPreInvRequestUIHelper.converterIntExpresionToBoolean(
            this.getIsAdvanceLevel()));
    myEntityToSave.setOtherStudiesDescription(this.getOtherStudiesDescription());

    FederalDependenceEntity myFederalDependenceToSave = new FederalDependenceEntity();
    FederalURRegulatoryEntity myFederalUR = new FederalURRegulatoryEntity();
    myFederalUR.setFederalUrRegulatoryId(this.theirFederalDepSelectedId);
    myFederalDependenceToSave.setFederalDependenceId(this.theirFederalDepSelectedId);

    myEntityToSave.setFederalDependence(myFederalUR);
    myEntityToSave.setAuhtNumber(this.getAuhtNumber());
    InvPreFileEntity myInvPreFileToSave = new InvPreFileEntity();
    myInvPreFileToSave.setInvPreFileId(this.getPreFileId());
    myEntityToSave.setUploadedFiles(itsRUFileList);
    myEntityToSave.setPreInvRequestPreFile(myInvPreFileToSave);
    this.setActualPreInvRequestId(itsPreInvRequestManagement.savePreInvRequest(myEntityToSave));
    
    init();
  }

  private Collection<FederalDependenceEntity> loadFederalDependencesList()
  {
    Collection<FederalDependenceEntity> myCollection;
    myCollection = itsPreInvRequestManagement.getListOfFederalDependences();
    return myCollection;
  }
    @Override
  public void loadFile(FileUploadEvent event) throws IOException
  {
    try
    {
            UploadedFile arq = event.getFile();

            InputStream in = new BufferedInputStream(arq.getInputstream());

            File file = new File(SIIFContextBase.getParamContext(KeyContextEnum.PATH) + arq.getFileName());

            this.theirFileName = file.getAbsolutePath().toString();

            FileOutputStream fout = new FileOutputStream(file);

            while (in.available() != 0)
            {
                fout.write(in.read());
            }
            fout.close();
            
            saveUploadedFile();
            
            savePreInvRequest();
            
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                            getMessage("ppp.planning.successAdd", file.getName()),
                            getMessage("ppp.planning.successAdd", file.getName()));
    }
    catch (Exception ex)
    {
        addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(ex.getMessage()),
                    this.getMessage(ex.getMessage()));
    }
  }
    
    @Override
    public void saveUploadedFile(){
        
        itsUploadedFile.setRequestUpLoadFilePath(this.theirFileName);
        itsUploadedFile = itsRequestUploadFilesManagement.saveUploadedFile(itsUploadedFile);
        for (RequestUploadFilesEntity file: itsRUFileList){
            if (file.getRequestUpLoadFileId() == itsUploadedFile.getRequestUpLoadFileId())
                return;
        }
        itsRUFileList.add(itsUploadedFile);
    }
    
    public void deleteDocument(){
        try{
            if (itsPreInvRequestManagement.deleteUploadedFile(itsSelectedRUFile, theirActualPreInvRequestId)){
                addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                                getMessage("ppp.progr.LogicFrame.successDelete", itsSelectedRUFile.getRequestUpLoadFilePath()),
                                getMessage("ppp.progr.LogicFrame.successDelete", itsSelectedRUFile.getRequestUpLoadFilePath()));
                init();
            }
        }
        catch(Exception ex){
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(ex.getMessage()),
                    this.getMessage(ex.getMessage()));
        }
    }
    //Este es el metodo para descargar el archivo desde el servidor
    @Override
     public StreamedContent getFileStreamedContent () throws Exception 
     {
         
        StreamedContent file = null; 
        
        try 
        {
            InputStream is = new BufferedInputStream(
                    new FileInputStream(itsSelectedRUFile.getRequestUpLoadFilePath()));
            String type = getType("file:" + itsSelectedRUFile.getRequestUpLoadFilePath());
            file = new DefaultStreamedContent(is, type,itsSelectedRUFile.getRequestUpLoadFilePath());
            
        } 
        catch (Exception exc) 
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));

        }
        
        return file;
     }
    
    
     public static String getType(String fileUrl) throws Exception {
        URL u = new URL(fileUrl);
        URLConnection uc = u.openConnection();
        String type = uc.getContentType();
        
        return type;
    }
    
    @Override
  public void activateRadioButtons()
  {
    if(this.isAdvanceLevel == 1)
    {
      this.isRdButtonActive = false;
    }
    else
    {
      this.isRdButtonActive = true;
    }
  }
  /**
   * @return the isIntegrationAct
   */
    @Override
  public int getIsIntegrationAct()
  {
    return isIntegrationAct;
  }
    
  /**
   * 
   * @return theirFederalDependenceSelected
   */
  public String getTheirFederalDependenceSelected() {
        return theirFederalDependenceSelected;
  }

  /**
   * 
   * @return  theirFederalDepSelectedId
   */
  public Long getTheirFederalDepSelectedId() {
    return theirFederalDepSelectedId;
  }

  /**
   * 
   * @param theirFederalDepSelectedId 
   */
  public void setTheirFederalDepSelectedId(Long theirFederalDepSelectedId) {
    this.theirFederalDepSelectedId = theirFederalDepSelectedId;
  }
  
  /**
   * 
   * @param theirFederalDependenceSelected 
   */
  public void setTheirFederalDependenceSelected(String theirFederalDependenceSelected) {
        this.theirFederalDependenceSelected = theirFederalDependenceSelected;
  }

  /**
   * @param isIntegrationAct the isIntegrationAct to set
   */
    @Override
  public void setIsIntegrationAct(int isIntegrationAct)
  {
    this.isIntegrationAct = isIntegrationAct;
  }

  /**
   * @return the isAcceptanceAct
   */
    @Override
  public int getIsAcceptanceAct()
  {
    return isAcceptanceAct;
  }

  /**
   * @param isAcceptanceAct the isAcceptanceAct to set
   */
    @Override
  public void setIsAcceptanceAct(int isAcceptanceAct)
  {
    this.isAcceptanceAct = isAcceptanceAct;
  }

  /**
   * @return the isFeasibilityDictum
   */
    @Override
  public int getIsFeasibilityDictum()
  {
    return isFeasibilityDictum;
  }

  /**
   * @param isFeasibilityDictum the isFeasibilityDictum to set
   */
    @Override
  public void setIsFeasibilityDictum(int isFeasibilityDictum)
  {
    this.isFeasibilityDictum = isFeasibilityDictum;
  }

  /**
   * @return the isEnvImpactDictum
   */
    @Override
  public int getIsEnvImpactDictum()
  {
    return isEnvImpactDictum;
  }

  /**
   * @param isEnvImpactDictum the isEnvImpactDictum to set
   */
    @Override
  public void setIsEnvImpactDictum(int isEnvImpactDictum)
  {
    this.isEnvImpactDictum = isEnvImpactDictum;
  }

  /**
   * @return the isAccreditationOfProperty
   */
    @Override
  public int getIsAccreditationOfProperty()
  {
    return isAccreditationOfProperty;
  }

  /**
   * @param isAccreditationOfProperty the isAccreditationOfProperty to set
   */
    @Override
  public void setIsAccreditationOfProperty(int isAccreditationOfProperty)
  {
    this.isAccreditationOfProperty = isAccreditationOfProperty;
  }

  /**
   * @return the isAuthOfUse
   */
    @Override
  public int getIsAuthOfUse()
  {
    return isAuthOfUse;
  }

  /**
   * @param isAuthOfUse the isAuthOfUse to set
   */
    @Override
  public void setIsAuthOfUse(int isAuthOfUse)
  {
    this.isAuthOfUse = isAuthOfUse;
  }

  /**
   * @return the isSoilMechanicsStudy
   */
    @Override
  public int getIsSoilMechanicsStudy()
  {
    return isSoilMechanicsStudy;
  }

  /**
   * @param isSoilMechanicsStudy the isSoilMechanicsStudy to set
   */
    @Override
  public void setIsSoilMechanicsStudy(int isSoilMechanicsStudy)
  {
    this.isSoilMechanicsStudy = isSoilMechanicsStudy;
  }

  /**
   * @return the isExecutiveProject
   */
    @Override
  public int getIsExecutiveProject()
  {
    return isExecutiveProject;
  }

  /**
   * @param isExecutiveProject the isExecutiveProject to set
   */
    @Override
  public void setIsExecutiveProject(int isExecutiveProject)
  {
    this.isExecutiveProject = isExecutiveProject;
  }

  /**
   * @return the isCostBenefitStudy
   */
    @Override
  public int getIsCostBenefitStudy()
  {
    return isCostBenefitStudy;
  }

  /**
   * @param isCostBenefitStudy the isCostBenefitStudy to set
   */
    @Override
  public void setIsCostBenefitStudy(int isCostBenefitStudy)
  {
    this.isCostBenefitStudy = isCostBenefitStudy;
  }

  /**
   * @return the isAdvanceLevel
   */
    @Override
  public int getIsAdvanceLevel()
  {
    return isAdvanceLevel;
  }

  /**
   * @param isAdvanceLevel the isAdvanceLevel to set
   */
    @Override
  public void setIsAdvanceLevel(int isAdvanceLevel)
  {
    this.isAdvanceLevel = isAdvanceLevel;
  }

  /**
   * @return the theirOtherStudiesDescription
   */
    @Override
  public String getOtherStudiesDescription()
  {
    return theirOtherStudiesDescription;
  }

  /**
   * @param theirOtherStudiesDescription the theirOtherStudiesDescription to set
   */
    @Override
  public void setOtherStudiesDescription(String anOtherStudiesDescription)
  {
    this.theirOtherStudiesDescription = anOtherStudiesDescription;
  }

  /**
   * @return the theirFederalDependence
   */
    @Override
  public Collection<FederalDependenceEntity> getFederalDependence()
  {
    return theirFederalDependence;
  }

  /**
   * @param theirFederalDependence the theirFederalDependence to set
   */
    @Override
  public void setFederalDependence(Collection<FederalDependenceEntity> aFederalDependence)
  {
    this.theirFederalDependence = aFederalDependence;
  }

  /**
   * @return the theirAuhtNumber
   */
    @Override
  public String getAuhtNumber()
  {
    return theirAuhtNumber;
  }

  /**
   * @param theirAuhtNumber the theirAuhtNumber to set
   */
    @Override
  public void setAuhtNumber(String anAuhtNumber)
  {
    this.theirAuhtNumber = anAuhtNumber;
  }

  /**
   * @return the theirFileName
   */
    @Override
  public String getFileName()
  {
    return theirFileName;
  }

  /**
   * @param theirFileName the theirFileName to set
   */
    @Override
  public void setFileName(String aFileName)
  {
    this.theirFileName = aFileName;
  }

  /**
   * @return the theirFederalDependenceSelectedId
   */
    @Override
  public Long getFederalDependenceSelectedId()
  {
    return theirFederalDependenceSelectedId;
  }

  /**
   * @param theirFederalDependenceSelectedId the theirFederalDependenceSelectedId to set
   */
    @Override
  public void setFederalDependenceSelectedId(Long aFederalDependenceSelectedId)
  {
    this.theirFederalDependenceSelectedId = aFederalDependenceSelectedId;
  }

  /**
   * @return the theirPreFileId
   */
    @Override
  public Long getPreFileId() {
    return theirPreFileId;
  }

  /**
   * @param theirPreFileId the theirPreFileId to set
   */
    @Override
  public void setPreFileId(Long aPreFileId) {
    this.theirPreFileId = aPreFileId;
  }

  /**
   * @return the theirInvPreFileEntity
   */
    @Override
  public InvPreFileEntity getTheirInvPreFileEntity() {
    return theirInvPreFileEntity;
  }

  /**
   * @param theirInvPreFileEntity the theirInvPreFileEntity to set
   */
    @Override
  public void setTheirInvPreFileEntity(InvPreFileEntity theirInvPreFileEntity) {
    this.theirInvPreFileEntity = theirInvPreFileEntity;
  }

  /**
   * @return the theirActualPreInvRquestId
   */
    @Override
  public Long getActualPreInvRequestId() {
    return theirActualPreInvRequestId;
  }

  /**
   * @param theirActualPreInvRquestId the theirActualPreInvRquestId to set
   */
    @Override
  public void setActualPreInvRequestId(Long anActualPreInvRquestId) {
    this.theirActualPreInvRequestId = anActualPreInvRquestId;
  }

  /**
   * @return the activateRdButtons
   */
    @Override
  public boolean getIsRdButtonActive() {
    return isRdButtonActive;
  }

  /**
   * @param activateRdButtons the activateRdButtons to set
   */
    @Override
  public void setIsRdButtonActive(boolean anActivateValue) {
    this.isRdButtonActive = anActivateValue;
  }
    public RequestUploadFileDataModel getItsRUFDataModel()
    {
        return itsRUFDataModel;
    }

    public void setItsRUFDataModel(RequestUploadFileDataModel itsRUFDataModel)
    {
        this.itsRUFDataModel = itsRUFDataModel;
    }

    public RequestUploadFilesEntity getItsSelectedRUFile()
    {
        return itsSelectedRUFile;
    }

    public void setItsSelectedRUFile(RequestUploadFilesEntity itsSelectedRUFile)
    {
        this.itsSelectedRUFile = itsSelectedRUFile;
    }

}