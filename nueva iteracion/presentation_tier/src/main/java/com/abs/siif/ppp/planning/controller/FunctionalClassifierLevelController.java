/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FuntionalClassifierLevelController
 *  Purpose:  Controller para la vista del catalogo de niveles de clasificador
 *            funcional.
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
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import com.abs.siif.planning.exception.FuncClassifLevelBussinessException;
import com.abs.siif.planning.management.FunctionalClassifierLevelManagement;
import com.abs.siif.ppp.planning.api.controller.FunctionalClassifierLevelControllerApi;
import com.abs.siif.ppp.planning.uihelpers.FunctionalClassifierLevelDataModel;
import com.abs.siif.ppp.planning.uihelpers.FunctionalClassifierLevelUIHelper;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FENIX-02
 */
@Controller("functionalClassifierLevelController")
@Scope("session")
public class FunctionalClassifierLevelController extends SIIFControllerBase 
        implements Serializable, FunctionalClassifierLevelControllerApi {

    @Resource( name = "functionalClassifierLevelManagement")
    private FunctionalClassifierLevelManagement itsFunctionalClassifierLevelManagement;
   
    
    private FunctionalClassifierLevelDataModel itsFCLDataModel = null;
    private FunctionalLevelClassifier[] itsSelectedFCLevels;
    private FunctionalLevelClassifier itsCurrentFCLevel= new FunctionalLevelClassifier();;
    private boolean isAddDisabled;
    private boolean itsInputsDisabled;
    private boolean itsEditInputsDisabled;
    private SaveType theirSaveType;
    private String selectedRowsToDelete = "";

    @Override
    public void init(){
        populateFuncClassifLevels();
        isAddDisabled = Boolean.FALSE;
        setDisabled(Boolean.TRUE, Boolean.TRUE);
        clearData();
    } 
    
    @Override
    public void populateFuncClassifLevels() {
        itsFCLDataModel = new FunctionalClassifierLevelDataModel(
                itsFunctionalClassifierLevelManagement.getAllFunClassifierLevels());
    }
    
    @Override
    public void onRowSelect(SelectEvent event) {
        FunctionalLevelClassifier myEntity = (FunctionalLevelClassifier) event.getObject();
        //setSelectedRowsToDelete contains the description of the selected rows
        //in case user wants to deleteFunctionalCassifier them.
        List<FunctionalLevelClassifier> myFuncClassifierLevels = 
                    FunctionalClassifierLevelUIHelper.mapperIdentities(itsSelectedFCLevels);
        selectedRowsToDelete = "";
            for (FunctionalLevelClassifier myFC : myFuncClassifierLevels){
                selectedRowsToDelete = selectedRowsToDelete.concat(myFC.getFunctionalLevelClassifierDescription());
                selectedRowsToDelete = selectedRowsToDelete.concat(" ");
                setSelectedRowsToDelete(selectedRowsToDelete);
            }
            
        if (itsSelectedFCLevels.length > 1) {
            isAddDisabled = Boolean.TRUE;
            clearData();
            setDisabled(Boolean.TRUE, Boolean.TRUE);
        } else {
            isAddDisabled = Boolean.FALSE;
            prepareEditFuncClassifLevel(myEntity);
        }
    }
 
    @Override
    public void persistFuncClassifLevel() {
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_INFO;
        String myMessage = "";
        try {
            FunctionalClassifierLevelUIHelper.validateFCLevelData(itsCurrentFCLevel);
            Long myNewIndentity = itsFunctionalClassifierLevelManagement.saveOrUpdate(itsCurrentFCLevel, theirSaveType);
            myMessage = this.getMapKeyExcpetion("ppp.planning.succesSave");
            prepareNewFuncClassifLevel();
            
        } catch (Exception ex) {
            itsCurrentFCLevel.setFunctionalLevelClassifierId(null);
            myMessage = getMapKeyExcpetion(ex.getMessage());
            mySeverity = FacesMessage.SEVERITY_ERROR;
        } finally {
            addMessageCurrentInstance(mySeverity, myMessage, null);
            init();
        }
    }
    
    
    @Override
    public void prepareNewFuncClassifLevel() {
        theirSaveType = SaveType.SAVE;
        clearData();
        setDisabled(Boolean.FALSE, Boolean.FALSE);
    }
    
    @Override
    public void prepareEditFuncClassifLevel(FunctionalLevelClassifier anEntity) {
        clearData();
        theirSaveType = SaveType.UPDATE;
        itsCurrentFCLevel.setFunctionalLevelClassifierId(anEntity.getFunctionalLevelClassifierId());
        itsCurrentFCLevel.setFunctionalLevelClassifierYear(anEntity.getFunctionalLevelClassifierYear());
        itsCurrentFCLevel.setFunctionalLevelClassifier(anEntity.getFunctionalLevelClassifier());
        itsCurrentFCLevel.setFunctionalLevelClassifierKey(anEntity.getFunctionalLevelClassifierKey());
        itsCurrentFCLevel.setFunctionalLevelClassifierDescription(anEntity.getFunctionalLevelClassifierDescription());
        itsCurrentFCLevel.setFunctionalLevelClassifierIsEncPlaneacion(anEntity.isFunctionalLevelClassifierIsEncPlaneacion());
        setDisabled(Boolean.FALSE, Boolean.TRUE);
    }
     
    @Override
    public void clearData() {
        itsSelectedFCLevels = null;
        itsCurrentFCLevel.setFunctionalLevelClassifierYear(Integer.parseInt(
                SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()));
        itsCurrentFCLevel.setFunctionalLevelClassifierDescription("");
        itsCurrentFCLevel.setFunctionalLevelClassifierIsEncPlaneacion(Boolean.FALSE);
        itsCurrentFCLevel.setFunctionalLevelClassifierId(null);
        itsCurrentFCLevel.setFunctionalLevelClassifierKey("");
        itsCurrentFCLevel.setFunctionalLevelClassifier(Integer.valueOf(String.valueOf(itsFCLDataModel.getRowCount() + 1 )));
    }
    
    private void setDisabled(boolean anInputsDisabled, boolean anEditInputsDisabled) {
        itsInputsDisabled = anInputsDisabled;
        itsEditInputsDisabled = anEditInputsDisabled;
    }
    
    @Override
    public void cancelPersistFuncClassifLevel() {
        theirSaveType = SaveType.NONE;
        clearData();
        setDisabled(Boolean.TRUE, Boolean.TRUE);
    }
    
    @Override
     public void deleteFuncClassifier() {
        String myMessage = this.getMapKeyExcpetion("ppp.planning.succesDelete");
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_ERROR;
        try {
            if (itsSelectedFCLevels.length <= 0) {

                throw new FuncClassifLevelBussinessException(getMapKeyExcpetion("ppp.planning.selectRecords"));
            }
            List<FunctionalLevelClassifier> myFuncClassifierLevels = 
                    FunctionalClassifierLevelUIHelper.mapperIdentities(itsSelectedFCLevels);
            itsFunctionalClassifierLevelManagement.deleteFunctionalCassifier(myFuncClassifierLevels);
            populateFuncClassifLevels();
            mySeverity = FacesMessage.SEVERITY_INFO;
            clearData();
            isAddDisabled = Boolean.FALSE;
            setDisabled(Boolean.TRUE, Boolean.TRUE);
        }catch (BaseBusinessException ex) {
            myMessage = getMapKeyExcpetion(ex.getMessage());
        } finally {
            addMessageCurrentInstance(mySeverity, myMessage, "");
            populateFuncClassifLevels();
        }
    }
    
    @Override
    public String getSelectedRowsToDelete()
    {
        return selectedRowsToDelete;
    }

    @Override
    public void setSelectedRowsToDelete(String selectedRowsToDelete)
    {
        this.selectedRowsToDelete = selectedRowsToDelete;
    }
    
    @Override
    public FunctionalClassifierLevelDataModel getItsFCLDataModel()
    {
        return itsFCLDataModel;
    }

    @Override
    public void setItsFCLDataModel(FunctionalClassifierLevelDataModel itsFCLDataModel)
    {
        this.itsFCLDataModel = itsFCLDataModel;
    }

    @Override
    public FunctionalLevelClassifier[] getItsSelectedFCLevels()
    {
        return itsSelectedFCLevels;
    }

    @Override
    public void setItsSelectedFCLevels(FunctionalLevelClassifier[] itsSelectedFCLevels)
    {
        this.itsSelectedFCLevels = itsSelectedFCLevels;
    }

    @Override
    public FunctionalLevelClassifier getItsCurrentFCLevel()
    {
        return itsCurrentFCLevel;
    }

    @Override
    public void setItsCurrentFCLevel(FunctionalLevelClassifier itsCurrentFCLevel)
    {
        this.itsCurrentFCLevel = itsCurrentFCLevel;
    }

    @Override
    public boolean isIsAddDisabled()
    {
        return isAddDisabled;
    }

    @Override
    public void setIsAddDisabled(boolean isAddDisabled)
    {
        this.isAddDisabled = isAddDisabled;
    }

    @Override
    public boolean isItsEditInputsDisabled()
    {
        return itsEditInputsDisabled;
    }

    @Override
    public void setItsEditInputsDisabled(boolean itsEditInputsDisabled)
    {
        this.itsEditInputsDisabled = itsEditInputsDisabled;
    }

    @Override
    public boolean isItsInputsDisabled()
    {
        return itsInputsDisabled;
    }

    @Override
    public void setItsInputsDisabled(boolean itsInputsDisabled)
    {
        this.itsInputsDisabled = itsInputsDisabled;
    }
    
}
