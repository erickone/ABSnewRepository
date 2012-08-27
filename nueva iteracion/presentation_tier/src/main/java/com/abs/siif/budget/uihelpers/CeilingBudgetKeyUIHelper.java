/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingBudgetKeyUIHelper
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.uihelpers;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.controller.CeilingBudgetKeyController;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.budget.exception.CeilingConfigurationBusinessException;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.support.SearchList;
import com.abs.siif.support.UtilValidations;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public class CeilingBudgetKeyUIHelper extends SIIFControllerBase{

    public static void validateData(CeilingBudgetKeyController aController){
        if(!UtilValidations.notNullOrBlank(aController.getItsCeilingConfigYear()))
        {
            throw new CeilingConfigurationBusinessException("ppp.budget.Year");
        }
        
        if(!UtilValidations.notNullOrBlank(aController.getItsCeilingConfigName()))
        {
            throw new CeilingConfigurationBusinessException("ppp.budget.Key");
        }
        
        if(!UtilValidations.notNullOrBlank(aController.getItsCeilingConfigDesc()))
        {
            throw new CeilingConfigurationBusinessException("ppp.budget.Desc");
        }
        
        if(aController.getItsChildsDualList().getTarget().isEmpty())
        {
            throw new CeilingConfigurationBusinessException("ppp.budget.Element");
        }
    }
    
    public static CeilingConfigurationEntity prepareCeilingConfiEntity(CeilingBudgetKeyController aController){
        CeilingConfigurationEntity myCeilingEntity = new CeilingConfigurationEntity();
        
        myCeilingEntity.setCeilingConfigName(aController.getItsCeilingConfigName());
        myCeilingEntity.setCeilingConfigDescription(aController.getItsCeilingConfigDesc());
        myCeilingEntity.setCeilingConfigBudgetKey(aController.getItsBudgetKeyDefinitionEntity());
        myCeilingEntity.setCeilingConfigActive(Boolean.TRUE);
        
        if(aController.getItsSaveType() == SaveType.UPDATE){
            myCeilingEntity.setCeilingConfigId(aController.getSelectedFather().getCeilingConfigId());
            myCeilingEntity.setCeilingConfigBudgetKey(aController.getSelectedFather().getBudgetKeyDefinition());
        }
        
        myCeilingEntity.setBudgetKeyItems(getTargetBudgetKeyItems(aController));        
          
        return myCeilingEntity;
    }
    
    public static List<BudgetKeyItemEntity> getTargetBudgetKeyItems(CeilingBudgetKeyController aController){
        List<BudgetKeyItemEntity> targetChild = new ArrayList<BudgetKeyItemEntity>();
        String[] array;   
        int sizeArray = aController.getItsChildsDualList().getTarget().size();
        array = new String[sizeArray];
        array = aController.getItsChildsDualList().getTarget().toArray(array);
        BudgetKeyItemEntity  refDep;
        for (String sId : array) {
            refDep = new BudgetKeyItemEntity();
            refDep.setBudgetKeyId(Long.parseLong(sId));
            refDep = SearchList.findObjectList(aController.getAllChilds(), refDep);
            targetChild.add(refDep);
        }
        return targetChild;
    }
}
        
