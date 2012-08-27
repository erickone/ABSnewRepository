/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingBudgetKeyController
 *  Purpose:  Controller for the operations related to 
 *              US CA-PPP028-001-001.- 
 *              Catalogo de Claves de Techos Presupuestales
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.controller;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.budget.api.controller.CeilingBudgetKeyApi;
import com.abs.siif.budget.dto.CeilingConfigurationDto;
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.budget.exception.CeilingConfigurationBusinessException;
import com.abs.siif.budget.management.BudgetKeyDefinitionManagement;
import com.abs.siif.budget.management.BudgetKeyItemManagement;
import com.abs.siif.budget.management.CeilingConfigurationManagement;
import com.abs.siif.budget.uihelpers.CeilingBudgetKeyDataModel;
import com.abs.siif.budget.uihelpers.CeilingBudgetKeyUIHelper;
import com.abs.siif.planning.data.SaveType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FENIX-02
 */
@Controller("ceilingBudgetKeyController")
@Scope("session")
public class CeilingBudgetKeyController extends SIIFControllerBase 
    implements Serializable, CeilingBudgetKeyApi{

    @Resource(name = "budgetKeyItemManagement")
    private BudgetKeyItemManagement itsBudgeKeyItemManagement;
    @Resource(name = "ceilingConfigurationManagement")
    private CeilingConfigurationManagement itsCeilingConfigurationManagement;
    @Resource(name = "budgetKeyDefinitionManagement")
    private BudgetKeyDefinitionManagement itsBudgetKeyDefinitionManagement;
    
    private String itsCeilingConfigYear;
    private String itsCeilingConfigName;
    private String itsCeilingConfigDescInitial;
    private String itsCeilingConfigDesc;
    private boolean isKeydisabled = true;
    private SaveType itsSaveType;
    private BudgetKeyDefinitionEntity itsBudgetKeyDefinitionEntity = null;
            
    //Estas variables son para poblar y guardar valores del DataTable.
    private CeilingBudgetKeyDataModel itsFatherDataModel;
    private List<CeilingConfigurationDto> itsFatherList;  
    private CeilingConfigurationDto selectedFather;
    
    //Esta variable es para poblar el PickList.
    private DualListModel<BudgetKeyItemEntity> itsChildsDualList;
    List<BudgetKeyItemEntity> childsSource = new ArrayList<BudgetKeyItemEntity>();  
    List<BudgetKeyItemEntity> childsTarget = new ArrayList<BudgetKeyItemEntity>();
    List<BudgetKeyItemEntity> allChilds = new ArrayList<BudgetKeyItemEntity>();
    
    @Override
    public void init(){
        itsCeilingConfigDescInitial = this.getMessage("ppp.budget.ceilingBudgetDescription.default");
        itsFatherList = itsCeilingConfigurationManagement.getCeilingConfigurations();
        itsFatherDataModel = new CeilingBudgetKeyDataModel(itsFatherList);
        allChilds = itsBudgeKeyItemManagement.getBudgetKeyAllItems();
        childsSource.addAll(allChilds);
        itsCeilingConfigDesc = itsCeilingConfigDescInitial;
        itsChildsDualList = new DualListModel<BudgetKeyItemEntity>(allChilds, childsTarget);
        setItsCeilingConfigYear(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString());
        if (itsBudgetKeyDefinitionEntity == null)
            itsBudgetKeyDefinitionEntity = itsBudgetKeyDefinitionManagement
                    .getBudgetDefinitionByYear(Integer.parseInt(itsCeilingConfigYear));
    }
    
    @Override
    public void onRowSelect(SelectEvent event) {
        childsSource.clear();
        childsSource.addAll(allChilds);
        //childsTarget.clear();
        Long ceilingId = selectedFather.getCeilingConfigId();
        childsTarget = selectedFather.getBudgetKeyItems();
        
        checkLists();
        itsChildsDualList.setSource(childsSource);
        itsChildsDualList.setTarget(childsTarget);
        
        setItsCeilingConfigYear(new Integer(selectedFather.getBudgetKeyDefinition().getPresupuestalKeyDefinitionYear()).toString());
        setItsCeilingConfigName(selectedFather.getCeilingConfigName());
        setItsCeilingConfigDesc(selectedFather.getCeilingConfigDesc());
        itsSaveType = SaveType.UPDATE;
        isKeydisabled = true;
    }  
  
    @Override
    public void onRowUnselect(UnselectEvent event) { 
        itsChildsDualList = null;
    }  
    
    private void checkLists(){
        for (BudgetKeyItemEntity s : childsTarget) {  
            if (childsSource.contains(s)) {  
                childsSource.remove(s);  
            }  
        }
    }
    
    @Override
    public void newCeilingBudgetKey(){
        clearData(); 
        isKeydisabled = false;
        itsSaveType = SaveType.SAVE;
    }
    
    @Override
    public void updateDesc(){
        List<BudgetKeyItemEntity> myTarget = CeilingBudgetKeyUIHelper.getTargetBudgetKeyItems(this);
        String items = "";
        for (BudgetKeyItemEntity myTmp : myTarget){
            String myNewItem = myTmp.getBudgetKeyEntity();
            items = items.concat(" " + myNewItem);
        }
        itsCeilingConfigDesc = itsCeilingConfigDescInitial;
        itsCeilingConfigDesc = itsCeilingConfigDesc.concat(items);
    }
    
    @Override
    public void saveCeilingBudgetKey(){
        FacesMessage.Severity myMessageFaces=FacesMessage.SEVERITY_INFO;
        String myMessageUI = null;
        try {
                CeilingBudgetKeyUIHelper.validateData(this);

                CeilingConfigurationEntity mySavedCeiling = CeilingBudgetKeyUIHelper.prepareCeilingConfiEntity(this);
                Long myObjectiveId = itsCeilingConfigurationManagement.saveCeilingConfigWithBudgetKeyItems(mySavedCeiling, itsSaveType);
                myMessageUI=this.getMessage("ppp.budget.CeilingSaved");
                isKeydisabled = true;
                clearData();
                itsFatherList = itsCeilingConfigurationManagement.getCeilingConfigurations();
                itsFatherDataModel = new CeilingBudgetKeyDataModel(itsFatherList);
               
            } catch (Exception e) {
                myMessageUI=this.getMessage(e.getMessage());
                myMessageFaces=FacesMessage.SEVERITY_ERROR;
            } finally {
                addMessageCurrentInstance(myMessageFaces,
                        myMessageUI,
                        myMessageUI);
            }
    }
    
    @Override
    public void deleteCeilings() {
        String myMessage = "";
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_ERROR;
        try {
            if (selectedFather == null) {

                throw new CeilingConfigurationBusinessException("ppp.budget.selectRecords");
            }
            itsCeilingConfigurationManagement.delete(selectedFather.getCeilingConfigId());
            mySeverity = FacesMessage.SEVERITY_INFO;
            clearData();
            itsFatherList = itsCeilingConfigurationManagement.getCeilingConfigurations();
            itsFatherDataModel = new CeilingBudgetKeyDataModel(itsFatherList);
            isKeydisabled = true;
            myMessage = this.getMapKeyExcpetion("ppp.budget.succesDelete");
        } catch (BaseBusinessException ex) {
            myMessage = getMapKeyExcpetion(ex.getMessage());
        } catch (CeilingConfigurationBusinessException e) {
            myMessage = this.getMessage(e.getMessage());
        } finally {
            addMessageCurrentInstance(mySeverity, myMessage, "");
        }
    }
    
    @Override
    public void clearData(){
        childsSource.clear();
        childsTarget.clear();
        selectedFather = null;
        itsChildsDualList.setSource(allChilds);
        itsChildsDualList.setTarget(childsTarget);
        setItsCeilingConfigYear(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString());
        setItsCeilingConfigName(null);
    }
    
    @Override
    public DualListModel<BudgetKeyItemEntity> getItsChildsDualList()
    {
        return itsChildsDualList;
    }

    @Override
    public void setItsChildsDualList(DualListModel<BudgetKeyItemEntity> itsChildsDualList)
    {
        this.itsChildsDualList = itsChildsDualList;
    }

    @Override
    public CeilingBudgetKeyDataModel getItsFatherDataModel()
    {
        return itsFatherDataModel;
    }

    @Override
    public void setItsFatherDataModel(CeilingBudgetKeyDataModel itsFatherDataModel)
    {
        this.itsFatherDataModel = itsFatherDataModel;
    }

    @Override
    public CeilingConfigurationDto getSelectedFather()
    {
        return selectedFather;
    }

    @Override
    public void setSelectedFather(CeilingConfigurationDto selectedFather)
    {
        this.selectedFather = selectedFather;
    }

    @Override
    public String getItsCeilingConfigDesc()
    {
        return itsCeilingConfigDesc;
    }

    @Override
    public void setItsCeilingConfigDesc(String itsCeilingConfigDesc)
    {
        this.itsCeilingConfigDesc = itsCeilingConfigDesc;
    }

    @Override
    public String getItsCeilingConfigName()
    {
        return itsCeilingConfigName;
    }

    @Override
    public void setItsCeilingConfigName(String itsCeilingConfigName)
    {
        this.itsCeilingConfigName = itsCeilingConfigName;
    }

    @Override
    public String getItsCeilingConfigYear()
    {
        return itsCeilingConfigYear;
    }

    @Override
    public void setItsCeilingConfigYear(String itsCeilingConfigYear)
    {
        this.itsCeilingConfigYear = itsCeilingConfigYear;
    }

    @Override
    public List<BudgetKeyItemEntity> getAllChilds()
    {
        return allChilds;
    }

    @Override
    public void setAllChilds(List<BudgetKeyItemEntity> allChilds)
    {
        this.allChilds = allChilds;
    }

    @Override
    public SaveType getItsSaveType()
    {
        return itsSaveType;
    }

    @Override
    public void setItsSaveType(SaveType itsSaveType)
    {
        this.itsSaveType = itsSaveType;
    }

    @Override
    public boolean isIsKeydisabled()
    {
        return isKeydisabled;
    }

    @Override
    public void setIsKeydisabled(boolean isKeydisabled)
    {
        this.isKeydisabled = isKeydisabled;
    }

    @Override
    public BudgetKeyDefinitionEntity getItsBudgetKeyDefinitionEntity()
    {
        return itsBudgetKeyDefinitionEntity;
    }

    @Override
    public void setItsBudgetKeyDefinitionEntity(BudgetKeyDefinitionEntity itsBudgetKeyDefinitionEntity)
    {
        this.itsBudgetKeyDefinitionEntity = itsBudgetKeyDefinitionEntity;
    }
    
}
