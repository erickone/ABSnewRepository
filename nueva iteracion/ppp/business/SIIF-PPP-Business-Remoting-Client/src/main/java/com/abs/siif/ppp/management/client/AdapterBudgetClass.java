/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AdapterBudgetClass
 *  Purpose:  [ short Description  ]
 *
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.management.client;

import com.abs.siif.AdapterBaseSiif;
import com.abs.siif.budget.dto.*;
import com.abs.siif.budget.entities.*;
import com.abs.siif.budget.management.*;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.support.BudgetDraftProjectEnum;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component("budgetadapterClass")
public class AdapterBudgetClass extends AdapterBaseSiif implements
        BudgetDetailsKeyManagement,
        BudgetKeyAdditionalManagement,
        BudgetKeyConfigurationManagement,
        BudgetKeyDefinitionManagement,
        BudgetKeyItemManagement,
        CeilingBudgetManagement,
        CeilingConfigurationManagement,
        ImportCeilingBudgetManagement,
        PreparedReportImportCeilingBudgetManagement
{

    @Override
    public void persistBudgetDetailsKey(
            Collection<BudgetDetailsKeyEntity> anEntities){
        beanName = "budgetDetailsKeyManagement";
    }

    @Override
    public BudgetDetailsKeyEntity persistBudgetDetailsKey(
            BudgetDetailsKeyEntity anIdentity){
        beanName = "budgetDetailsKeyManagement";
        return null;
    }

    @Override
    public Collection<BudgetDetailsKeyEntity> getBudgetDetailsKeyByBudgetKey(
            BudgetKeyEntity anEntity){
        beanName = "budgetDetailsKeyManagement";
        return null;
    }

    @Override
    public void deleteBudgetDetailsKey(BudgetKeyEntity anIdentity){
        beanName = "budgetDetailsKeyManagement";
    }

    @Override
    public Collection<BudgetKeyAdditionalEntity>
            getBudgetKeyAdditionalsByObjectExpenseAndDependency
            (BudgetKeyAdditionalDto anAdditionalDto){
        beanName = "budgetKeyAdditionalManagement";
        return null;
    }

    @Override
    public double getSumByBudgetKey(BudgetKeyAdditionalDto anAdditionalDto){
        beanName = "budgetKeyAdditionalManagement";
        return 0.0D;
    }

    @Override
    public BudgetKeyAdditionalEntity getBudgetKeyAdditionalByIdentity(
            BudgetKeyAdditionalDto anAdditionalDto){
        beanName = "budgetKeyAdditionalManagement";
        return null;
    }

    @Override
    public BudgetKeyAdditionalEntity persist(BudgetKeyAdditionalEntity anEntity){
        beanName = "budgetKeyAdditionalManagement";
        return null;
    }

    @Override
    public void delete(BudgetKeyAdditionalEntity anEntity){
        beanName = "budgetKeyAdditionalManagement";
    }

    @Override
    public void deleteAditional(String aBudgetKey){
        beanName = "budgetKeyAdditionalManagement";
    }

    @Override
    public BudgetKeyAdditionalEntity getAdditional(String aBudgetKey){
        beanName = "budgetKeyAdditionalManagement";
        return null;
    }

    @Override
    public String getBudgetKey(Map<String,Long> anIdentities){
        beanName = "budgetKeyConfigurationManagement";
        return null;
    }

    @Override
    public Map<String,List<BudgetDetailsKeyEntity>> getBudgetKeyWithBudgetKeyItems(
            Map<String,Long> anIdentities){
        beanName = "budgetKeyConfigurationManagement";
        return null;
    }

    @Override
    public BudgetKeyDefinitionEntity getBudgetDefinitionByYear(int aYear){
        beanName = "budgetKeyDefinitionManagement";
        return null;
    }

    @Override
    public List<BudgetKeyItemEntity> getBudgetKeyAllItems(){
        beanName = "budgetKeyItemManagement";
        return null;
    }

    @Override
    public List<BudgetKeyItemEntity> getBudgetItemsRelatedCeilingConf(
            Long aCeilingConfId){
        beanName = "budgetKeyItemManagement";
        return null;
    }

    @Override
    public Long getBudgetForDraftProject(DependenceEntity denpendency,
            ObjectExpenseEntity objExpens, String dest,
            FinancingSourceEntity financialSource){
        beanName = "ceilingBudgetManagement";
        return null;
    }

    @Override
    public Long getBudgetAvailableForDraftProject(DependenceEntity denpendency,
            ObjectExpenseEntity objExpens, String dest,
            FinancingSourceEntity financialSource, boolean basics){
        beanName = "ceilingBudgetManagement";
        return null;
    }

    @Override
    public Long getFinancingSourceCeiling(
            FinancingSourceEntity myFinanceSourceSelected){
        beanName = "ceilingBudgetManagement";
        return null;
    }

    @Override
    public Long getFinancingSourceCeilingAvailable(
            FinancingSourceEntity myFinanceSourceSelected){
        beanName = "ceilingBudgetManagement";
        return null;
    }

    @Override
    public List<DepencenceDto> getKeyByDependency(DependenceEntity denpendency,
            List<BudgetDraftProjectEnum> budgetKey){
        beanName = "ceilingBudgetManagement";
        return null;
    }

    @Override
    public Long getBudgetForUR(DependenceEntity denpendency,
            ObjectExpenseEntity objExpens, String dest, FinancingSourceEntity financialSource){
        beanName = "ceilingBudgetManagement";
        return null;
    }

    @Override
    public Long getBudgetByUEG(DraftProjectEntity aDraftProject){
        beanName = "ceilingBudgetManagement";
        return null;
    }

    @Override
    public Long getAvailableCeilingBasics(
            String identitiesToserach, Long obj, String Dest){
        beanName = "ceilingBudgetManagement";
        return null;
    }

    @Override
    public Long getBudgetAvailableForInvPreFile(DependenceEntity denpendency){
        beanName = "ceilingBudgetManagement";
        return null;
    }

    @Override
    public List<CeilingConfigurationDto> getCeilingConfigurations(){
        beanName = "ceilingConfigurationManagement";
        return null;
    }

    @Override
    public Long saveCeilingConfigWithBudgetKeyItems(
            CeilingConfigurationEntity aCeilingConfigurationEntity, SaveType aSaveType){
        beanName = "ceilingConfigurationManagement";
        return null;
    }

    @Override
    public void delete(Long aCeilingConfigId){
        beanName = "ceilingConfigurationManagement";
    }

    @Override
    public boolean validateFile(
            String pathFile, List<BudgetKeyItemEntity> objLayout){
        beanName = "importCeilingBudgetManagement";
        return false;
    }

    @Override
    public List<CeilingConfigurationDto> getAllCeillingconfigurationForYear(
            int year){
        beanName = "importCeilingBudgetManagement";
        return null;
    }

    @Override
    public List<CeillingBudgetEntity> executeImportCeilling(
            List<BudgetKeyItemEntity> objLayout, List<ImportFileCeillingBudgetDto> files){
        beanName = "importCeilingBudgetManagement";
        return null;
    }

    @Override
    public List<CellCeilingBudgetDto> executeValidateFile(
            List<BudgetKeyItemEntity> objLayout, List<ImportFileCeillingBudgetDto> files){
        beanName = "importCeilingBudgetManagement";
        return null;
    }

    @Override
    public boolean saveCeillingBudgetItem(
            ValidateDeleteCeilingBudgetDto deleteCeiling, List<CeillingBudgetEntity> dataFile){
        beanName = "importCeilingBudgetManagement";
        return false;
    }

    @Override
    public List<List<CellCeilingBudgetDto>> readFiles(
            List<BudgetKeyItemEntity> objLayout, List<ImportFileCeillingBudgetDto> files){
        beanName = "importCeilingBudgetManagement";
        return null;
    }

    @Override
    public String createFooterColumnReport(List<BudgetKeyItemEntity> layout){
        beanName = "preparedReportImportCeilingBudgetManagement";
        return null;
    }

    @Override
    public List<BudgetKeyItemEntity> createLayoutReportImport(
            List<BudgetKeyItemEntity> layout){
        beanName = "preparedReportImportCeilingBudgetManagement";
        return null;
    }

    @Override
    public List<List<CellCeilingBudgetDto>> createReportWithError(
            List<BudgetKeyItemEntity> layout, List<List<CellCeilingBudgetDto>> listObject, List<CellCeilingBudgetDto> listObjectError){
        beanName = "preparedReportImportCeilingBudgetManagement";
        return null;
    }

    @Override
    public List<List<CellCeilingBudgetDto>> createReportError(
            List<BudgetKeyItemEntity> layout, List<List<CellCeilingBudgetDto>> listObject, List<CellCeilingBudgetDto> listObjectError){
        beanName = "preparedReportImportCeilingBudgetManagement";
        return null;
    }

    @Override
    public boolean createDocumentExcel(
            String fileName, List<BudgetKeyItemEntity> layout, List<List<CellCeilingBudgetDto>> listObject){
        beanName = "preparedReportImportCeilingBudgetManagement";
        return false;
    }
}
