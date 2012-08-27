/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.api.controller;

import com.abs.siif.budget.dto.CeilingConfigurationDto;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.dto.ImportFileCeillingBudgetDto;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.entities.CeillingBudgetEntity;
import java.util.List;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author absvalenzuela
 */
public interface ImportCeilingBudgetApi {

    public void init();

    public String getCurrentOperationYear();

    public void setCurrentOperationYear(String year);

    /**
     * ******************************************************
     */
    // Metodos para habilitar y desabilitar botones
    public boolean getDisabledButtonValidate();

    public void setDisabledButtonValidate(boolean enable);

    public boolean getDisabledButtonImport();

    public void setDisabledButtonImport(boolean enable);

    public boolean getDisabledButtonPrintError();

    public void setDisabledButtonPrintError(boolean enable);

    public boolean getDisabledButtonDelete();

    public void setDisabledButtonDelete(boolean enable);

    public boolean getDisabledButtonBrowse();

    public void setDisabledButtonBrowse(boolean enable);

    public boolean getDisabledButtonAddFile();

    public void setDisabledButtonAddFile(boolean enable);
    
    public boolean getDisabledCheckBoxImport();
    
    public void setDisabledCheckBoxImport(boolean enable);
    
    public boolean getDisabledCheckboxErrores();
    
    public void setDisabledCheckboxErrores(boolean enable);

    public void processFileUpload(FileUploadEvent event);

    public String getCurrentFilePath();

    public void setCurrentFilePath(String filePath);

    public List<ImportFileCeillingBudgetDto> getLstArchivos();

    public void setLstArchivos(List<ImportFileCeillingBudgetDto> archivos);

    public void agregarArchivo();

    public String navigateToMainMenu();

    public String navigateToImportCeilingbudget(boolean bandera);

    public List<CeilingConfigurationDto> getConfigurationFilter();

    public void setConfigurationFilter(List<CeilingConfigurationDto> ceilingConfiguration);

    public Long getKeyCeilingBudgetId();

    public void setKeyCeilingBudgetId(Long keyCeilingBudget);

    public void changeDescription();

    public String getDescriptionFilter();

    public void setDescriptionFilter(String filter);

    public void executeImportFiles();

    public void executeValidationFiles();

    public void viewRowWithError();

    public List<CellCeilingBudgetDto> getLstCellWithError();

    public void setLstCellWithError(List<CellCeilingBudgetDto> objlst);

    public List<CeillingBudgetEntity> getLstCeilingBudget();

    public void setLstCeilingBudget(List<CeillingBudgetEntity> lstCeilingBudget);

    public void removeFileForList();

    public List<BudgetKeyItemEntity> getLayoutFile();

    public void setLayoutFile(List<BudgetKeyItemEntity> object);

    public CeilingConfigurationDto getCeilingConfiguration();

    public void setCeilingConfiguration(CeilingConfigurationDto object);

    public List<List<CellCeilingBudgetDto>> getFileInCellCeiling();

    public void setFileInCellCeiling(List<List<CellCeilingBudgetDto>> object);

    public boolean getIsCheckBoxClicked();

    public void setIsCheckBoxClicked(boolean enable);
    
    public boolean getIsCheckBoxImportClicked();

    public void setIsCheckBoxImportClicked(boolean enable);

    public String getFilesSeparateComma();

    public void reloadListFilterCeilingBudget();

    public boolean getDisabledComboFilterCeilingBudget();

    public void setDisabledComboFilterCeilingBudget(boolean object);  
    
}
