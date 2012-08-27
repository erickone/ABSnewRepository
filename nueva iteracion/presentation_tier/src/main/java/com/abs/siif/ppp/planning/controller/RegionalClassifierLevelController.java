/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalClassifierLevelController
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

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.planning.exception.FuncClassifLevelBussinessException;
import com.abs.siif.planning.management.RegionalClassifierLevelManagement;
import com.abs.siif.ppp.planning.api.controller.RegionalClassifierLevelControllerApi;
import com.abs.siif.ppp.planning.uihelpers.RegionalClassifierLevelDataModel;
import com.abs.siif.ppp.planning.uihelpers.RegionalClassifierLevelUIHelper;
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
@Controller("regionalClassifierLevelController")
@Scope("session")
public class RegionalClassifierLevelController extends SIIFControllerBase 
        implements Serializable, RegionalClassifierLevelControllerApi{

    @Resource( name = "regionalClassifierLevelManagement")
    private RegionalClassifierLevelManagement itsRegionalClassifierLevelManagement;
    
    private RegionalClassifierLevelDataModel itsRCLDataModel = null;
    private RegionalLevelClassifierEntity[] itsSelectedRCLevels;
    private RegionalLevelClassifierEntity itsCurrentRCLevel= new RegionalLevelClassifierEntity();;
    private boolean isAddDisabled;
    private boolean itsInputsDisabled;
    private boolean itsEditInputsDisabled;
    private SaveType theirSaveType;
    private String selectedRowsToDelete = "";
    
    @Override
    public void init(){
        populateRegClassifLevels();
        isAddDisabled = Boolean.FALSE;
        setDisabled(Boolean.TRUE, Boolean.TRUE);
        clearData();
    } 
    
    @Override
    public void populateRegClassifLevels() {
        itsRCLDataModel = new RegionalClassifierLevelDataModel(
                itsRegionalClassifierLevelManagement.getAllRegionalClassifierLevels());
    }
    
    @Override
    public void onRowSelect(SelectEvent event) {
        RegionalLevelClassifierEntity myEntity = (RegionalLevelClassifierEntity) event.getObject();
        //setSelectedRowsToDelete contains the description of the selected rows
        //in case user wants to deleteRegionalClassifier them.
        List<RegionalLevelClassifierEntity> myFuncClassifierLevels = 
                    RegionalClassifierLevelUIHelper.mapperIdentities(itsSelectedRCLevels);
        selectedRowsToDelete = "";
            for (RegionalLevelClassifierEntity myRCL : myFuncClassifierLevels){
                selectedRowsToDelete = selectedRowsToDelete.concat(myRCL.getRegionalLevelClassifierDescription());
                selectedRowsToDelete = selectedRowsToDelete.concat(" ");
                setSelectedRowsToDelete(selectedRowsToDelete);
            }
        
        if (itsSelectedRCLevels.length > 1) {
            isAddDisabled = Boolean.TRUE;
            clearData();
            setDisabled(Boolean.TRUE, Boolean.TRUE);
        } else {
            isAddDisabled = Boolean.FALSE;
            prepareEditRegClassifLevel(myEntity);
        }
    }
    
    @Override
    public void persistRegClassifLevel() {
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_INFO;
        String myMessage = "";
        try {
            RegionalClassifierLevelUIHelper.validateRCLevelData(itsCurrentRCLevel);
            Long myNewIndentity = itsRegionalClassifierLevelManagement.saveOrUpdate(itsCurrentRCLevel, theirSaveType);
            myMessage = this.getMapKeyExcpetion("ppp.planning.succesSave");
            prepareNewRegClassifLevel();
            
        } catch (Exception ex) {
            itsCurrentRCLevel.setRegionalLevelClassifierId(null);
            myMessage = getMapKeyExcpetion(ex.getMessage());
            mySeverity = FacesMessage.SEVERITY_ERROR;
        } finally {
            addMessageCurrentInstance(mySeverity, myMessage, null);
            init();
        }
    }
    
    @Override
    public void prepareNewRegClassifLevel() {
        theirSaveType = SaveType.SAVE;
        clearData();
        setDisabled(Boolean.FALSE, Boolean.FALSE);
    }
    
    @Override
    public void prepareEditRegClassifLevel(RegionalLevelClassifierEntity anEntity) {
        clearData();
        theirSaveType = SaveType.UPDATE;
        itsCurrentRCLevel.setRegionalLevelClassifierId(anEntity.getRegionalLevelClassifierId());
        itsCurrentRCLevel.setRegionalLevelClassifierYear(anEntity.getRegionalLevelClassifierYear());
        itsCurrentRCLevel.setRegionalLevelClassifierLevel(anEntity.getRegionalLevelClassifierLevel());
        itsCurrentRCLevel.setRegionalLevelClassifierKey(anEntity.getRegionalLevelClassifierKey());
        itsCurrentRCLevel.setRegionalLevelClassifierDescription(anEntity.getRegionalLevelClassifierDescription());
        itsCurrentRCLevel.setRegionalLevelClassifierIsCensus(anEntity.isRegionalLevelClassifierIsCensus());
        itsCurrentRCLevel.setRegionalLevelClassifierIsGender(anEntity.isRegionalLevelClassifierIsGender());
        itsCurrentRCLevel.setRegionalLevelClassifierIsState(anEntity.isRegionalLevelClassifierIsState());
        itsCurrentRCLevel.setRegionalLevelClassifierIsMunicipality(anEntity.isRegionalLevelClassifierIsMunicipality());
        itsCurrentRCLevel.setRegionalLevelClassifierIsPostalCode(anEntity.isRegionalLevelClassifierIsPostalCode());
        setDisabled(Boolean.FALSE, Boolean.TRUE);
    }
    
    @Override
    public void clearData() {
        itsSelectedRCLevels = null;
        itsCurrentRCLevel.setRegionalLevelClassifierYear(Integer.parseInt(
                SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()));
        itsCurrentRCLevel.setRegionalLevelClassifierId(null);
        itsCurrentRCLevel.setRegionalLevelClassifierKey("");
        itsCurrentRCLevel.setRegionalLevelClassifierDescription("");
        itsCurrentRCLevel.setRegionalLevelClassifierIsCensus(Boolean.FALSE);
        itsCurrentRCLevel.setRegionalLevelClassifierIsGender(Boolean.FALSE);
        itsCurrentRCLevel.setRegionalLevelClassifierIsState(Boolean.FALSE);
        itsCurrentRCLevel.setRegionalLevelClassifierIsMunicipality(Boolean.FALSE);
        itsCurrentRCLevel.setRegionalLevelClassifierIsPostalCode(Boolean.FALSE);
        itsCurrentRCLevel.setRegionalLevelClassifierLevel(Integer.valueOf(String.valueOf(itsRCLDataModel.getRowCount() + 1 )));
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
    public void deleteRegClassifier() {
        String myMessage = this.getMapKeyExcpetion("ppp.planning.succesDelete");
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_ERROR;
        try {
            if (itsSelectedRCLevels.length <= 0) {

                throw new FuncClassifLevelBussinessException(getMapKeyExcpetion("ppp.planning.selectRecords"));
            }
            List<RegionalLevelClassifierEntity> myFuncClassifierLevels = 
                    RegionalClassifierLevelUIHelper.mapperIdentities(itsSelectedRCLevels);
            itsRegionalClassifierLevelManagement.deleteRegionalClassifier(myFuncClassifierLevels);
            mySeverity = FacesMessage.SEVERITY_INFO;
            clearData();
            isAddDisabled = Boolean.FALSE;
            setDisabled(Boolean.TRUE, Boolean.TRUE);
        } catch (BaseBusinessException ex) {
            myMessage = getMapKeyExcpetion(ex.getMessage());
        }finally {
            addMessageCurrentInstance(mySeverity, myMessage, "");
            populateRegClassifLevels();
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
    public RegionalLevelClassifierEntity getItsCurrentRCLevel()
    {
        return itsCurrentRCLevel;
    }

    @Override
    public void setItsCurrentRCLevel(RegionalLevelClassifierEntity itsCurrentRCLevel)
    {
        this.itsCurrentRCLevel = itsCurrentRCLevel;
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

    @Override
    public RegionalClassifierLevelDataModel getItsRCLDataModel()
    {
        return itsRCLDataModel;
    }

    @Override
    public void setItsRCLDataModel(RegionalClassifierLevelDataModel itsRCLDataModel)
    {
        this.itsRCLDataModel = itsRCLDataModel;
    }

    @Override
    public RegionalLevelClassifierEntity[] getItsSelectedRCLevels()
    {
        return itsSelectedRCLevels;
    }

    @Override
    public void setItsSelectedRCLevels(RegionalLevelClassifierEntity[] itsSelectedRCLevels)
    {
        this.itsSelectedRCLevels = itsSelectedRCLevels;
    }
    
}
