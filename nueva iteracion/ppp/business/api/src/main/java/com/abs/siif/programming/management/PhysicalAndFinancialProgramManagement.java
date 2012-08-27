/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.budget.entities.BudgetKeyEntity;
import com.abs.siif.budget.entities.MensualBudgetKeyEntity;
import com.abs.siif.programming.dto.FinancialAdvProgDto;
import com.abs.siif.programming.dto.PhysicalAndFinancialProgramDto;
import com.abs.siif.programming.entities.BenefAndGoalsEntity;
import com.abs.siif.programming.entities.InputEntity;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface PhysicalAndFinancialProgramManagement 
{
  /**
   * Este metodo se encvarga de obtener el dto de la pantalla de Programa Fisico Finan.
   * El dto debera de obtener el detalle que se encuantra en la base de datos referente 
   * a la Id de la Preficha de Inversión, Revisa de favor el contenido del DTO
   * @param aPreFileId
   * @return 
   */
  PhysicalAndFinancialProgramDto getPhysicalAndFinancialProgrambyPrefileId(Long aPreFileId);
  /**
   * Guarda la información que se manda dentro del DTO, colocando 
   * @param PhysicalAndFinancialProgramDto
   * @return 
   */
  String savePhysicalAndFinancialProgram(PhysicalAndFinancialProgramDto aPhysicalAndFinancialDto);
  
  List<InputEntity> getAllInputInitialAsig(Long id);
  
  BenefAndGoalsEntity getBenefAndGoalsByInvPreFile(Long invPreFileId);
  
  void saveFinancialAdvancedProg(List<MensualBudgetKeyEntity> mensualBudgetKeyList);
  
  List<BudgetKeyEntity> getFinancialAdvProg(Long invPreFileId);
 
  BudgetKeyEntity getBudgetKeyById(Long budgetKeyId);
 
}
