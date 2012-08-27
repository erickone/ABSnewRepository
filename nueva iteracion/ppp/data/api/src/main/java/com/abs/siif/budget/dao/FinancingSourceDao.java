/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.entities.FinancingSourceEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface FinancingSourceDao extends SIIFBaseDao<FinancingSourceEntity, Long>
{
    Collection<FinancingSourceEntity> getFinancingSourceByObjectExpense(Long anObjectExpenseId);
    
    FinancingSourceEntity getFinancingSourceById(Long anFinSourceId);
    
    /**
     * Extrae todas las fuentes de financiamineto
     * @return lista de fuentes de financiamiento
     */
    List<FinancingSourceEntity> getAllFinancingSource();
}
