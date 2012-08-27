/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.budget.dto.BudgetKeyBreakDownDto;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import com.abs.siif.budget.entities.FinancingSourceEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface BudgetKeyDao
{
    Long saveBudgetKey(BudgetKeyEntity aBudgetEntity);
    void persistBudgetKey(BudgetKeyEntity aBudgetEntity);
    Collection<BudgetKeyEntity> getBudgetKeysWithProgrammaticKey(String aProgrammaticKey);
    Long getBudgetKeysSummaryAmountsByFinancingSouce(FinancingSourceEntity aFinancingSource);
    void deleteBudgetKey(Long aBudgetKey);
    BudgetKeyEntity getBudgetKeyById(Long aBudgetKeyId);
    void updateBudgetKey(BudgetKeyEntity aBudgetKey);
    void insertInBudgetKeyDetail(BudgetKeyEntity aBudgetKeyEntity);
    void updateInBudgetKeyDetail(BudgetKeyEntity aBudgetKeyEntity);
    /**
     * Extrae datos de la cve programatica filtrada por la clave programatica generada
     * @param BudgetKey
     * @return entity de cve presupuestal
     */
    BudgetKeyEntity getBudgetKeyByBudgetKey(BudgetKeyEntity budgetKeyEntity);
    
    BudgetKeyBreakDownDto getBudgetDesgloce(Long lCvePptalId);
    List<BudgetKeyEntity> getBudgetKeyByInvPreFileId(Long invPreFileId);
    
    /**
     * Este metodo se trae la clave presupuestal y su desgloce.
     * @param aKey
     * @return 
     */
    List<BudgetKeyBreakDownDto> getBudgetKeyWithBreakDown(String aKey, String other);
    
    /**
     * Este metodo actualizara la informacion de el desgloce de la clave pptal.
     */
    boolean updateBudgetKeyWithBreakDown(List<BudgetKeyBreakDownDto> aList);
}
