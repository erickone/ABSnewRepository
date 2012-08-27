/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.dto.FinancialAdvancedBudgetKeyDto;
import com.abs.siif.programming.entities.BenefAndGoalsEntity;
import com.abs.siif.programming.entities.InputEntity;
import com.abs.siif.programming.entities.PhysicalAndFinancialProgramEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface PhysicalAndFinancialProgramDao extends SIIFBaseDao<PhysicalAndFinancialProgramEntity, Long>
{
  PhysicalAndFinancialProgramEntity getPhysicalAndFinancialProgramByPreFileId(Long aPreFileId);
  
  List<InputEntity> getAllInputInitialAsig(Long id);
  
  BenefAndGoalsEntity getBenefAndGoalsByInvPreFile(Long invPreFileId);
 
}