/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.budget.entities.*;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dto.InitBudgetKeyPreFileDto;
import com.abs.siif.programming.entities.InputEntity;
import com.abs.siif.programming.entities.InvPreFileDetailEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * Interface of FinancialStructureManagment
 * @author abs71
 */
public interface FinancialStructureManagment {
   /**
   * Get list of ObjectExpenseEntity 
   * @return List of ObjectExpenseEntity
   */
   List<ObjectExpenseEntity> getAllObjectExpenses();
   
   /**
   * Get list of DestinationEntity 
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
    * Save a InputEntitys
    * @param inputEntitys coleccion de InputEntity to save
    */    
    List<Long>  saveInputEntitys(Collection<InputEntity> inputEntitys);
    
     CeillingBudgetEntity getTechoByCvePresupuestal(String cvePresupuestal);
    
    /**
     * Sumatoria de monto asignado por partida y destino 
     * de todas las prefichas
     * @param destinationEntity
     * @return valor de la sumatoria de montos asignados
     */
    BigDecimal getAmountAssigned(DestinationEntity destinationEntity);
    
    /**
     * extrae la cve presupuestal filtrada por la cve presupuestal
     * @param budgetKeyEntity
     * @return detalles de la clave presupuestal
     */
    BudgetKeyEntity getBudgetKeyByBudgetKey(BudgetKeyEntity budgetKeyEntity);
    
    /**
     * Extrae todas las fuentes de financiamiento
     * @return 
     */
    List<FinancingSourceEntity> getAllFinancingSource();
    
    /**
     * Sumatoria de montos por techo cve fuente
     * @param budgetKeyEntity
     * @return 
     */
    BigDecimal getSumaryTechoCveFuente(BudgetKeyEntity budgetKeyEntity);
    
    /**
     * Guarda clave presupuestal
     * @param budgetKeyEntity 
     */
    void SaveBudgetKey(BudgetKeyEntity budgetKeyEntity);
    
    /**
     * Guarda detalle de Preficha
     * @param invPreFileDetailEntity 
     */
    void SaveInvPreFileDetailDao(InvPreFileDetailEntity invPreFileDetailEntity);
    
    /**
     * Guardar las claves adicionales
     * @param budgetKeyAdditionalEntity 
     */
    void SaveBudgetKeyAdditional(BudgetKeyAdditionalEntity budgetKeyAdditionalEntity);
    
    
    List<InitBudgetKeyPreFileDto> getInitBudgetKeyPreFile(InvPreFileEntity invPreFileEntity);
    
    List<InitBudgetKeyPreFileDto> getInitBudgetKeyAdditional(InvPreFileEntity invPreFileEntity);

     void deletebudgetKey(BudgetKeyEntity budgetKeyEntity);
     
     void updatebudgetKeyMonthly(BudgetKeyEntity budgetKeyEntity);

    List<ObjectExpenseEntity> getBudgetingFramming(DependenceEntity father, Long idInvPrefile);
    
    List<DepencenceDto> getDependciesIsRespUnitByDependIdRelated(
            Long idDependency);
    Long getSummatoryTotalOfCeilingsInvestObjects(int myYear,  String chargeKey) ;
}
