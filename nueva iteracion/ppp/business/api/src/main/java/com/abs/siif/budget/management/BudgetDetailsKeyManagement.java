/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.management;

import com.abs.siif.budget.entities.BudgetDetailsKeyEntity;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface BudgetDetailsKeyManagement {

    public void persistBudgetDetailsKey(Collection<BudgetDetailsKeyEntity> anEntities);

      BudgetDetailsKeyEntity persistBudgetDetailsKey(BudgetDetailsKeyEntity anIdentity);

    Collection<BudgetDetailsKeyEntity> getBudgetDetailsKeyByBudgetKey(
            BudgetKeyEntity anEntity);
    
    void deleteBudgetDetailsKey(BudgetKeyEntity anIdentity);
    
}
