/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierController
 *  Purpose:  Control the functions to the Functional Controller view.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.ppp.planning.controller;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.dto.FunctionalClassifierDto;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import com.abs.siif.planning.management.FunctionalClassifierLevelManagement;
import com.abs.siif.planning.management.FunctionalClassifierManagement;
import com.abs.siif.ppp.planning.api.controller.FunctionalClassifierControllerApi;
import com.abs.siif.ppp.planning.uihelpers.TreeViewFuncClassifierUIHelper;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FENIX-02
 */
@Controller("functionalClassifierController")
@Scope("session")
public class FunctionalClassifierController extends SIIFControllerBase 
    implements Serializable, FunctionalClassifierControllerApi{

    @Resource(name = "treeViewFuncClassifierUIHelper")
    private transient TreeViewFuncClassifierUIHelper theirTvFuncClassifier;
    @Resource(name = "optionsController")
    private transient OptionsController optionsController;
    @Resource(name = "functionalClassifierManagement")
    private transient FunctionalClassifierManagement itsFunctionalClassifierManagement;
    @Resource(name = "functionalClassifierLevelManagement")
    private transient FunctionalClassifierLevelManagement itsFunctionalClassifierLevelManagement;
    
    private TreeNode itsSelectedNode;
    private String itsFuncClassfierKey = "";
    private String itsFuncClassfierDesc = "";
    private String itsFuncClassfierDefinicion = "";
    private SaveType itsFuncClassifierSaveType;
    private FunctionalClassifierDto itsSelectedNodeDto = new FunctionalClassifierDto();
    private String itsFatherDescription = "";
    private FunctionalClassifierEntity itsFatherEntity = new FunctionalClassifierEntity();
    private FunctionalClassifierEntity itsSaveFCEntity = new FunctionalClassifierEntity();
    private FunctionalLevelClassifier itsFCLevel = new FunctionalLevelClassifier();
    private boolean isSaveDisabled = true;
    //Mensajes
    private String itsHeader;
    private String itsDialogHeader;

    @Override
    public void init(){
        theirTvFuncClassifier.restartNodeTreeview();
    }
    
    @Override
    public TreeNode getItsSelectedNode()
    {
        return itsSelectedNode;
    }

    @Override
    public void setItsSelectedNode(TreeNode itsSelectedNode)
    {
        this.itsSelectedNode = itsSelectedNode;
    }
    
    @Override
    public TreeNode getRoot() {
        String myRootName = this.getMessage("ppp.profr.Admin") + " " + optionsController.getYear();
        TreeNode myRoot = theirTvFuncClassifier.getNodeTreeview(myRootName);
        return myRoot;
        }
    
    @Override
    public void prepareNewFuncClassifier()  {
        clearValues();
        setTheirObjectiveSaveType(SaveType.SAVE);
        this.isSaveDisabled = false;
        itsSelectedNodeDto = getNode();
        this.itsFatherDescription = itsSelectedNodeDto.getItsNodeText();
        try {
            //Esta condicion es para un nuevo hijo de Administracion 2013.
            if (itsSelectedNodeDto.getItsFuncClassifierId() != null) {
                itsFatherEntity = itsFunctionalClassifierManagement.
                        getFunctionalClassifierById(itsSelectedNodeDto.
                        getItsFuncClassifierId());
                getNextFuncClassifierKey(itsSelectedNodeDto
                        .getItsFuncClassifierId().toString());
            }
            else {
                itsFatherEntity = null;
                getNextFuncClassifierKey(null);
            }
            
            Long myFCId = null;
            if (itsSelectedNodeDto.getItsFuncClassifierLevel() > 0) {
                    myFCId = itsSelectedNodeDto.getItsFuncClassifierId();
                    int myNextLevel = (int) (itsSelectedNodeDto.getItsFuncClassifierLevel() + 1);
                    itsFCLevel = getFunctionalLevel(myNextLevel);
                } else {
                    int myInitialLevel = 1;
                    itsFCLevel = itsFunctionalClassifierLevelManagement.
                            getFuncClassifierLevelByLevel(myInitialLevel);
                }
            setItsDialogHeader("Alta");
            
        }
        catch (Exception ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, "", 
                    getMapKeyExcpetion(ex.getMessage()));
        }
    }
    
    private FunctionalLevelClassifier getFunctionalLevel(int aLevel) throws Exception {
        itsFCLevel = itsFunctionalClassifierLevelManagement.getFuncClassifierLevelByLevel(aLevel);
        short myLevel = (short) (aLevel - 1);

        if (itsFCLevel == null) {
            throw new Exception(getMessage("ppp.planning.excpLevelConfigured", "" + myLevel));
        }

        return itsFCLevel;
    }
    
    private String getNextFuncClassifierKey(String idPadre){
        Long next = itsFunctionalClassifierManagement.getNextFuncClassifierKey(idPadre);
        next++;
        String key = itsSelectedNodeDto.getItsFuncClassifierKey();
        //En caso de que key sea nulo, esta dando de alta un FC de nivel 1.
        if (key != null){
            key = key.concat("."+next.toString());
        }
        else{
            key = next.toString();
        }
        setItsFuncClassfierKey(key);
        return this.itsFuncClassfierKey;
    }
    private FunctionalClassifierDto getNode() {
        FunctionalClassifierDto mySelectedNode = new FunctionalClassifierDto();
        if (isFuncClassifierTreeviewDto(SaveType.SAVE)) {
            mySelectedNode = (FunctionalClassifierDto) itsSelectedNode.getData();

        } else {
            short myInitialLevel = 0;
            mySelectedNode.setItsFuncClassifierLevel(myInitialLevel);
            mySelectedNode.setItsNodeText(itsSelectedNode.getData().toString());
        }

        return mySelectedNode;
    }
    
    private Boolean isFuncClassifierTreeviewDto(SaveType aSaveType) {
        try {
            FunctionalClassifierDto mySelectedNode = new FunctionalClassifierDto();
            if (aSaveType == SaveType.SAVE) {
                mySelectedNode = (FunctionalClassifierDto) itsSelectedNode.getData();
            } else {
                mySelectedNode = (FunctionalClassifierDto) itsSelectedNode.getParent().getData();
            }

            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public void saveFuncClassifier() {
        try {
            String myMessage;
            myMessage = this.getMessage("ppp.planning.SavedFuncClassifier");
            switch (itsFuncClassifierSaveType) {
            case NONE: {
                break;
            }
            case SAVE: {
                itsSaveFCEntity = new FunctionalClassifierEntity();
                itsSaveFCEntity.setFunctionalClassifierKey(this.itsFuncClassfierKey);
                itsSaveFCEntity.setFunctionalClassifierDescription(this.itsFuncClassfierDesc);
                itsSaveFCEntity.setFunctionalClassifierDefinitionCONAC(this.itsFuncClassfierDefinicion);
                if (itsFatherEntity != null) {
                    itsSaveFCEntity.setFather(itsFatherEntity);
                }
                itsSaveFCEntity.setFunctionalLevelClassifier(itsFCLevel);
                break;
            }
            case UPDATE: {
                itsSaveFCEntity.setFunctionalClassifierKey(this.itsFuncClassfierKey);
                itsSaveFCEntity.setFunctionalClassifierDescription(this.itsFuncClassfierDesc);
                itsSaveFCEntity.setFunctionalClassifierDefinitionCONAC(this.itsFuncClassfierDefinicion);
                break;
            }
        }
            Long myNewObjectiveId = itsFunctionalClassifierManagement.Save(itsSaveFCEntity, itsFuncClassifierSaveType);
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, "", getMapKeyExcpetion(myMessage));
            theirTvFuncClassifier.restartNodeTreeview();
        } catch (Exception ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, null, getMapKeyExcpetion(ex.getMessage()));
        }
    }
    
    @Override
    public void prepareEditFuncClassifier() {
        try {
            clearValues();
            setTheirObjectiveSaveType(SaveType.UPDATE);
            itsSelectedNodeDto =  (FunctionalClassifierDto) itsSelectedNode.getData();
            itsSaveFCEntity = itsSelectedNodeDto.getFunctionalClassifierEntity();
            //Esta condicion es para un editar hijos de Administracion 2013.
            if (itsSelectedNodeDto.getItsFatherId() != null){
                itsFatherEntity = itsFunctionalClassifierManagement.getFunctionalClassifierById(itsSelectedNodeDto.getItsFatherId());
                this.itsFatherDescription = itsFatherEntity.getCompositeFuncClassifierKey();
                itsSaveFCEntity.setFather(itsFatherEntity);
            }
            else{
                this.itsFatherDescription = "Nulo";
            }
            itsFCLevel = getFunctionalLevel(itsSelectedNodeDto.getItsFuncClassifierLevel());
            itsSaveFCEntity.setFunctionalLevelClassifier(itsFCLevel);
            itsSaveFCEntity.setFuntionalClassifierObjectives(itsSelectedNodeDto.getFuntionalClassifierObjectives());
            this.itsFuncClassfierDefinicion = itsSelectedNodeDto.getItsFuncClassifierDefinition();
            this.itsFuncClassfierDesc = itsSelectedNodeDto.getItsFuncClassifierDescription();
            this.itsFuncClassfierKey = itsSelectedNodeDto.getItsFuncClassifierKey();
            this.isSaveDisabled = false;
            setItsDialogHeader("Edicion");
        } catch (Exception e) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, null, getMapKeyExcpetion("ppp.planning.excpNotEditable"));
        }

    }
    
    @Override
    public void deleteFunctionalClassifier() {
        itsSelectedNodeDto = getNode();
        String myMessage = this.getMapKeyExcpetion("ppp.planning.succesDelete");
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_ERROR;
        if (itsSelectedNodeDto != null) {
            try{
                itsSelectedNodeDto = (FunctionalClassifierDto) itsSelectedNode.getData();
                itsFunctionalClassifierManagement.deleteFunctionalClassifier(itsSelectedNodeDto.getItsFuncClassifierId());
                mySeverity = FacesMessage.SEVERITY_INFO;
            }
            catch(Exception e){
                myMessage = getMapKeyExcpetion(e.getMessage());
            } 
            finally {
                addMessageCurrentInstance(mySeverity, myMessage, "");
                theirTvFuncClassifier.restartNodeTreeview();
            }
        }
    }
    
    @Override
    public void clearValues(){
        this.itsFCLevel = new FunctionalLevelClassifier();
        this.itsSelectedNodeDto = new FunctionalClassifierDto();
        this.itsFatherEntity = new FunctionalClassifierEntity();
        this.itsFuncClassfierDefinicion = "";
        this.itsFuncClassfierDesc = "";
        this.itsFuncClassfierKey = "";
        this.itsFatherDescription = "";
    }
    
    @Override
    public String getItsFuncClassfierDefinicion()
    {
        return itsFuncClassfierDefinicion;
    }

    @Override
    public void setItsFuncClassfierDefinicion(String itsFuncClassfierDefinicion)
    {
        this.itsFuncClassfierDefinicion = itsFuncClassfierDefinicion;
    }

    @Override
    public String getItsFuncClassfierDesc()
    {
        return itsFuncClassfierDesc;
    }

    @Override
    public void setItsFuncClassfierDesc(String itsFuncClassfierDesc)
    {
        this.itsFuncClassfierDesc = itsFuncClassfierDesc;
    }

    @Override
    public String getItsFuncClassfierKey()
    {
        return itsFuncClassfierKey;
    }

    @Override
    public void setItsFuncClassfierKey(String itsFuncClassfierKey)
    {
        this.itsFuncClassfierKey = itsFuncClassfierKey;
    }

    @Override
    public SaveType getTheirObjectiveSaveType()
    {
        return itsFuncClassifierSaveType;
    }

    @Override
    public void setTheirObjectiveSaveType(SaveType theirObjectiveSaveType)
    {
        this.itsFuncClassifierSaveType = theirObjectiveSaveType;
    }

    @Override
    public FunctionalClassifierDto getItsFather()
    {
        return itsSelectedNodeDto;
    }

    @Override
    public void setItsFather(FunctionalClassifierDto myFather)
    {
        this.itsSelectedNodeDto = myFather;
    }

    @Override
    public String getItsFatherDescription()
    {
        return itsFatherDescription;
    }

    @Override
    public void setItsFatherDescription(String itsFatherDescription)
    {
        this.itsFatherDescription = itsFatherDescription;
    }

    @Override
    public boolean isIsSaveDisabled()
    {
        return isSaveDisabled;
    }

    @Override
    public void setIsSaveDisabled(boolean isSaveDisabled)
    {
        this.isSaveDisabled = isSaveDisabled;
    }

    @Override
    public String getItsHeader() {
        return this.getMessage("ppp.planning.funClassifier", new String[]{
                SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()
                    });
    }

    @Override
    public void setItsHeader(String itsHeader) {
        this.itsHeader = itsHeader;
    }

    @Override
    public String getItsDialogHeader() {
        return itsDialogHeader;
    }

    @Override
    public void setItsDialogHeader(String accion) {
        this.itsDialogHeader = this.getMessage("ppp.planning.funClassifierDialogHeader", new String[]{
                accion,  itsFCLevel.getFunctionalLevelClassifierDescription(),
                SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()
                    });
    }
    
}
