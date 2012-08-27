/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.management;

import com.abs.siif.budget.dto.CeilingConfigurationDto;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.dto.ImportFileCeillingBudgetDto;
import com.abs.siif.budget.dto.ValidateDeleteCeilingBudgetDto;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.entities.CeillingBudgetEntity;
import java.util.List;

/**
 *
 * @author absvalenzuela
 */
public interface ImportCeilingBudgetManagement {
    
    public boolean validateFile(String pathFile, List<BudgetKeyItemEntity> objLayout);
    
    public List<CeilingConfigurationDto> getAllCeillingconfigurationForYear(int year);
    
    public List<CeillingBudgetEntity> executeImportCeilling(List<BudgetKeyItemEntity> objLayout, List<ImportFileCeillingBudgetDto> files);
    
    public List<CellCeilingBudgetDto> executeValidateFile(List<BudgetKeyItemEntity> objLayout, List<ImportFileCeillingBudgetDto> files);
    
    public boolean saveCeillingBudgetItem(ValidateDeleteCeilingBudgetDto deleteCeiling, List<CeillingBudgetEntity> dataFile);
    
    public List<List<CellCeilingBudgetDto>> readFiles(List<BudgetKeyItemEntity> objLayout, List<ImportFileCeillingBudgetDto> files);
     
}
