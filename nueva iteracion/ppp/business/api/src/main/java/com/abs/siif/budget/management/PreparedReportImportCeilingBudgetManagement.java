/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.management;

import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import java.util.List;

/**
 *
 * @author absvalenzuela
 */
public interface PreparedReportImportCeilingBudgetManagement {

    public String createFooterColumnReport(List<BudgetKeyItemEntity> layout);

    public List<BudgetKeyItemEntity> createLayoutReportImport(List<BudgetKeyItemEntity> layout);

    public List<List<CellCeilingBudgetDto>> createReportWithError(List<BudgetKeyItemEntity> layout, List<List<CellCeilingBudgetDto>> listObject, List<CellCeilingBudgetDto> listObjectError);

    public List<List<CellCeilingBudgetDto>> createReportError(List<BudgetKeyItemEntity> layout, List<List<CellCeilingBudgetDto>> listObject, List<CellCeilingBudgetDto> listObjectError);
    
    public boolean createDocumentExcel(String fileName, List<BudgetKeyItemEntity> layout, List<List<CellCeilingBudgetDto>> listObject);
}
