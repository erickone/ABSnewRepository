/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import com.abs.siif.budget.entities.DestinationEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dto.InitBudgetKeyPreFileDto;
import com.abs.siif.programming.entities.InputEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author abs71
 */
public interface FinancialStructureDao extends SIIFBaseDao<ObjectExpenseEntity, Long> {
    /**
     * 
     * @return List of ObjectExpenseEntity
     */
      List<ObjectExpenseEntity> getAllObjectExpenses();
      
   /**
   * Get list of DestinationEntity 
   * @param id of ObjectExpenses
   * @return List of DestinationEntity
   */
   List<DestinationEntity> getAllDestinationByObjExp( Long id);
   
   /**
    * Get list of InputEntity
    * @param id of PreFile
    * @return 
    */
   List<InputEntity> getAllInputByPreFile(Long id);
   
   /**
    * Sumatoria de montos asignados por dependencia y destino
    * @param destinationEntity
    * @return valor de la sumatoria de montos asignados
    */
   BigDecimal getAmountAssigned(DestinationEntity destinationEntity);
   
   
   /**
    * Sumatoria de los montos por techo cve presuspuestal fuente
    * @param destinationEntity
    * @return 
    */
   BigDecimal getSumaryTechoCveFuente(BudgetKeyEntity destinationEntity);
   
   List<InitBudgetKeyPreFileDto> getInitBudgetKeyPreFile(InvPreFileEntity invPreFileEntity);
   
   List<InitBudgetKeyPreFileDto> getInitBudgetKeyAdditional(InvPreFileEntity invPreFileEntity);

    void deletebudgetKey(BudgetKeyEntity budgetKeyEntity);
    
    void updatebudgetKeyMonthly(BudgetKeyEntity budgetKeyEntity);

    List<ObjectExpenseEntity> getBudgetingFramming(DependenceEntity father, Long idInvPrefile);
    
}
