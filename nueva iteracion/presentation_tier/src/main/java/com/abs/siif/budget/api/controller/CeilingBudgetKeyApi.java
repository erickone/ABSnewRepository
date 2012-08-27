/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingBudgetKeyApi
 *  Purpose:  API for CeilingBudgetKeyController.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.api.controller;

import com.abs.siif.budget.dto.CeilingConfigurationDto;
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.uihelpers.CeilingBudgetKeyDataModel;
import com.abs.siif.planning.data.SaveType;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author FENIX-02
 */
public interface CeilingBudgetKeyApi
{
    public void init();
    
    public void onRowSelect(SelectEvent event);
    
    public void onRowUnselect(UnselectEvent event);
    
    public void newCeilingBudgetKey();
    
    public void updateDesc();
    
    public void saveCeilingBudgetKey();
    
    public void deleteCeilings();
    
    public void clearData();
    
    public DualListModel<BudgetKeyItemEntity> getItsChildsDualList();
    
    public void setItsChildsDualList(DualListModel<BudgetKeyItemEntity> itsChildsDualList);
    
    public CeilingBudgetKeyDataModel getItsFatherDataModel();
    
    public void setItsFatherDataModel(CeilingBudgetKeyDataModel itsFatherDataModel);
    
    public CeilingConfigurationDto getSelectedFather();
    
    public void setSelectedFather(CeilingConfigurationDto selectedFather);
    
    public String getItsCeilingConfigDesc();
    
    public void setItsCeilingConfigDesc(String itsCeilingConfigDesc);
    
    public String getItsCeilingConfigName();
    
    public void setItsCeilingConfigName(String itsCeilingConfigName);
    
    public String getItsCeilingConfigYear();
    
    public void setItsCeilingConfigYear(String itsCeilingConfigYear);
    
    public List<BudgetKeyItemEntity> getAllChilds();
    
    public void setAllChilds(List<BudgetKeyItemEntity> allChilds);
    
    public SaveType getItsSaveType();
    
    public void setItsSaveType(SaveType itsSaveType);
    
    public boolean isIsKeydisabled();
    
    public void setIsKeydisabled(boolean isKeydisabled);
    
    public BudgetKeyDefinitionEntity getItsBudgetKeyDefinitionEntity();
    
    public void setItsBudgetKeyDefinitionEntity(BudgetKeyDefinitionEntity itsBudgetKeyDefinitionEntity);
    
}
