/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.api.controller;

import com.abs.siif.budget.controller.ImportCeilingBudgetController;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import java.util.List;

/**
 *
 * @author absvalenzuela
 */
public interface PrintErrorImportCeilingBudgetApi {

    public ImportCeilingBudgetApi getImportCeilingBudgetController();

    public void setImportCeilingBudgetController(ImportCeilingBudgetApi importCeilingBudget);

    public void init();
    
    public List<List<CellCeilingBudgetDto>> getFileInCellCeiling();
    
    public void setFileInCellCeiling(List<List<CellCeilingBudgetDto>> object);
    
    public List<BudgetKeyItemEntity> getLayout();
    
    public void setLayout(List<BudgetKeyItemEntity> object);
    
    public String navigateToImportCeilingbudget(); 
    
    public String getFooterColumnReport();
    
    public void setFooterColumnReport(String object);
    
    public String getFooterResponseReport();
    
    public void setFooterResponseReport(String object);
    
    public void importToFileExcel();    
}
