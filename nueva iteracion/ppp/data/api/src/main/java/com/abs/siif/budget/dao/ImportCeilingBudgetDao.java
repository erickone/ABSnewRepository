/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.dto.ValidateDeleteCeilingBudgetDto;
import com.abs.siif.budget.entities.CeillingBudgetEntity;
import java.util.List;

/**
 *
 * @author absvalenzuela
 */
public interface ImportCeilingBudgetDao extends SIIFBaseDao<CeillingBudgetEntity, Long> {

    boolean saveCeillingBudgetItem(ValidateDeleteCeilingBudgetDto deleteCeiling, List<CeillingBudgetEntity> dataFile);

    boolean saveCeillingBudgetItem(CeillingBudgetEntity dataEntity);
    //public 
    Long getSummatoryTotalOfCeilingsByChargeKey(int myYear, String chargeKey, String finSource);
    
    Long getSummatoryTotalOfCeilingsNotBasicObject(int myYear,  String chargeKey);
    
    Long getSummatoryTotalOfCeilingsInvestObjects(int myYear,  String chargeKey);
}
