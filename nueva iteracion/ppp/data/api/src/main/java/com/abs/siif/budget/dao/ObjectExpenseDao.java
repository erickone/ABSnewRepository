/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.dto.BudgetSummaryDto;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface ObjectExpenseDao extends SIIFBaseDao<ObjectExpenseEntity, Long>{
    
    List<ObjectExpenseEntity> getHierarchicalObjectives(List<Long> anIdentities);
    
    BudgetSummaryDto getAllAmountsOfChapters(String programmaticKey, String aditionalFlag);
    
    Collection<ObjectExpenseEntity> getLastLevelObjects(Long anObjectExpenseid);
    
    List<ObjectExpenseEntity> getObjectExpenseBySpecificParByObjectIdRelatedFilter(
            Long idObjectExpense, String filter);
    
    List<ObjectExpenseEntity> getObjectExpenseBySpecificParByObjectIdRelated(Long idObjectExpense);
    
    List<ObjectExpenseEntity> getObjectExpenseByGenericParByObjectIdRelatedFilter(Long idObjectExpense, String filter);
    
    List<ObjectExpenseEntity> getObjectExpenseByGenericParByObjectIdRelated(Long idObjectExpense);
    
    List<ObjectExpenseEntity> getObjectExpenseByConceptByObjectIdRelatedFilter(Long idObjectExpense, String filter);
    
    List<ObjectExpenseEntity> getObjectExpenseByConceptByObjectIdRelated(Long idObjectExpense);
    
    List<ObjectExpenseEntity> getObjectExpensesNotWithIds(Long[] ids);
    
    
}
