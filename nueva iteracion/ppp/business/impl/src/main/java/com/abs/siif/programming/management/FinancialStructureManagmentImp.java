/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  EncuadreController
 *  Purpose:   Controlar la Relación de Programas con Unidad Ejecutora del 
 *             Gasto (UEG)
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.management;

import com.abs.siif.base.async.AsynchCall;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.budget.dao.*;
import com.abs.siif.budget.entities.*;
import com.abs.siif.budget.management.CeilingBudgetManagement;
import com.abs.siif.budget.management.CeilingBudgetManagementImpl;
import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dao.FinancialStructureDao;
import com.abs.siif.programming.dao.InputDao;
import com.abs.siif.programming.dao.InvPreFileDetailDao;
import com.abs.siif.programming.dto.InitBudgetKeyPreFileDto;
import com.abs.siif.programming.entities.InputEntity;
import com.abs.siif.programming.entities.InvPreFileDetailEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.support.BudgetDraftProjectEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Implementation of interface FinancialStructureManagment
 *
 * @author abs71
 */
@Service("financialStructureManagment")
public class FinancialStructureManagmentImp implements FinancialStructureManagment {

    @Resource(name = "financialStructureDao")
    private FinancialStructureDao theirFinancialStructureDaoImpl;
    @Resource(name = "inputDao")
    private InputDao theirInputDaoImpl;
    @Resource(name = "ceilingConfigurationDao")
    CeilingConfigurationDao theirceilingConfigurationDao;
    @Resource(name = "DependenceDao")
    DependenceDao theirDependenceDao;
    @Resource(name = "ceillingBudgetManagement")
    CeilingBudgetManagement theirCeilingBudgetManagement;
    @Resource(name = "ceillingBudgetDao")
    CeillingBudgetDao theiCeillingBudgetDao;
    @Resource(name = "budgetKeyDao")
    BudgetKeyDao theirbudBudgetKeyDao;
    @Resource(name = "financingSourceDao")
    FinancingSourceDao theiFinancialSourceDao;
    @Resource(name = "invPreFileDetailDao")
    InvPreFileDetailDao theiInvPreFileDetailDao;
    @Resource(name = "budgetKeyAdditionalDao")
    private BudgetKeyAdditionalDao itsBudgetKeyAdditionalDao;
    @Resource(name="importCeilingBudgetDao")
    private ImportCeilingBudgetDao theiImportCeilingBudgetDao;

    /**
     * Get list of ObjectExpenseEntity
     *
     * @return List of ObjectExpenseEntity
     */
    @Override
    public List<ObjectExpenseEntity> getAllObjectExpenses() {
        List<ObjectExpenseEntity> objectExpenseEntitys = null;
        try {
            objectExpenseEntitys = theirFinancialStructureDaoImpl.getAllObjectExpenses();
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureManagmentImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objectExpenseEntitys;
    }

    /**
     * Get list of DestinationEntity
     *
     * @return List of DestinationEntity
     */
    @Override
    public List<DestinationEntity> getAllDestinationByObjExp(Long id) {

        List<DestinationEntity> destinationEntitys = null;
        try {
            destinationEntitys = theirFinancialStructureDaoImpl.getAllDestinationByObjExp(id);
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureManagmentImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return destinationEntitys;
    }

    /**
     * Get list of InputEntity
     *
     * @param id of PreFile
     * @return
     */
    @Override
    public List<InputEntity> getAllInputByPreFile(Long id) {

        List<InputEntity> inputEntitys = null;
        try {
            inputEntitys = theirFinancialStructureDaoImpl.getAllInputByPreFile(id);
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureManagmentImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inputEntitys;
    }

    /**
     * Save a InputEntitys
     *
     * @param inputEntitys coleccion de InputEntity to save
     */
    @Override
    public List<Long> saveInputEntitys(Collection<InputEntity> inputEntitys) {

        List<Long> saveInputEntitys = null;
        try {
            saveInputEntitys = theirInputDaoImpl.saveInputEntitys(inputEntitys);
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureManagmentImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saveInputEntitys;
    }

    /**
     * Extrae techo por clave presupuestal
     *
     * @param cvePresupuestal
     * @return
     */
    @Override
    public CeillingBudgetEntity getTechoByCvePresupuestal(String cvePresupuestal) {
        return theiCeillingBudgetDao.getTechoByCvePresupuestal(cvePresupuestal);
    }

    /**
     * Extrea el monto asignado
     *
     * @param destinationEntity
     * @return
     */
    @Override
    public BigDecimal getAmountAssigned(DestinationEntity destinationEntity) {
        return theirFinancialStructureDaoImpl.getAmountAssigned(destinationEntity);
    }

    /**
     * Extrae la clave presupuestal por clave
     *
     * @param budgetKeyEntity
     * @return
     */
    @Override
    public BudgetKeyEntity getBudgetKeyByBudgetKey(BudgetKeyEntity budgetKeyEntity) {
        return theirbudBudgetKeyDao.getBudgetKeyByBudgetKey(budgetKeyEntity);
    }

    /**
     * Extrae todas las fuentes de financiamiento
     *
     * @return
     */
    @Override
    public List<FinancingSourceEntity> getAllFinancingSource() {
        return theiFinancialSourceDao.getAllFinancingSource();
    }

    /**
     * Extre la sumatoria de Techo asignado por fuente de financiamiento
     *
     * @param budgetKeyEntity
     * @return
     */
    @Override
    public BigDecimal getSumaryTechoCveFuente(BudgetKeyEntity budgetKeyEntity) {
        return theirFinancialStructureDaoImpl.getSumaryTechoCveFuente(budgetKeyEntity);
    }

    /**
     * Guarda clave presupuestal generada
     *
     * @param budgetKeyEntity
     */
    @Override
    public void SaveBudgetKey(BudgetKeyEntity budgetKeyEntity) {
        theirbudBudgetKeyDao.saveBudgetKey(budgetKeyEntity);
    }

    /**
     * Guarda las aportaciones configuradas
     *
     * @param invPreFileDetailEntity
     */
    @Override
    public void SaveInvPreFileDetailDao(InvPreFileDetailEntity invPreFileDetailEntity) {
        theiInvPreFileDetailDao.saveInvPreFileDetailDao(invPreFileDetailEntity);
    }

    /**
     * Guarda clave presupuestal para adicional
     *
     * @param budgetKeyAdditionalEntity
     */
    @Override
    public void SaveBudgetKeyAdditional(BudgetKeyAdditionalEntity budgetKeyAdditionalEntity) {
        itsBudgetKeyAdditionalDao.save(budgetKeyAdditionalEntity);
    }

    /**
     * Extrae las lcaves presupuestales ya configuradas para la preficha
     *
     * @param invPreFileEntity
     * @return
     */
    @Override
    public List<InitBudgetKeyPreFileDto> getInitBudgetKeyPreFile(InvPreFileEntity invPreFileEntity) {
        return theirFinancialStructureDaoImpl.getInitBudgetKeyPreFile(invPreFileEntity);
    }

    /**
     * Extrae las claves presupouestales adicionales configuradas para la
     * preficha
     *
     * @param invPreFileEntity
     * @return
     */
    @Override
    public List<InitBudgetKeyPreFileDto> getInitBudgetKeyAdditional(InvPreFileEntity invPreFileEntity) {
        return theirFinancialStructureDaoImpl.getInitBudgetKeyAdditional(invPreFileEntity);
    }

    @Override
    public void deletebudgetKey(BudgetKeyEntity budgetKeyEntity) {
        theirFinancialStructureDaoImpl.deletebudgetKey(budgetKeyEntity);
    }

    @Override
    public void updatebudgetKeyMonthly(BudgetKeyEntity budgetKeyEntity) {
        theirFinancialStructureDaoImpl.updatebudgetKeyMonthly(budgetKeyEntity);
    }

    @Override
    public List<ObjectExpenseEntity> getBudgetingFramming(DependenceEntity father, Long idInvPrefile) {
        return theirFinancialStructureDaoImpl.getBudgetingFramming(father, idInvPrefile);
    }
    
    @Override
    public List<DepencenceDto> getDependciesIsRespUnitByDependIdRelated(
            Long idDependency){
        
       return  theirDependenceDao.getDependciesIsRespUnitByDependIdRelated(idDependency); 

    }
    
    @Override
    public Long getSummatoryTotalOfCeilingsInvestObjects(int myYear,  String chargeKey) 
    {
        return theiImportCeilingBudgetDao.getSummatoryTotalOfCeilingsInvestObjects(myYear,chargeKey);
    }
}
