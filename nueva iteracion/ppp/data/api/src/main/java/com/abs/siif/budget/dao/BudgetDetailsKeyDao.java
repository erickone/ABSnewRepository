/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.entities.BudgetDetailsKeyEntity;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface BudgetDetailsKeyDao extends
        SIIFBaseDao<BudgetDetailsKeyEntity, Long> {

    
    Collection<BudgetDetailsKeyEntity> getBudgetDetailsKeyByBudgetKey(
            BudgetKeyEntity anEntity);
    
    void deleteBudgetDetailsKey(BudgetKeyEntity anEntity);
    
}
