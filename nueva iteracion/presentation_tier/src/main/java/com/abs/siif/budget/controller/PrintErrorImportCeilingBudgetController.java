/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.controller;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.api.controller.ImportCeilingBudgetApi;
import com.abs.siif.budget.api.controller.PrintErrorImportCeilingBudgetApi;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.management.PreparedReportImportCeilingBudgetManagement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author absvalenzuela
 */
@Scope("session")
@Controller("printErrorImportCeilingBudgetController")
public class PrintErrorImportCeilingBudgetController extends SIIFControllerBase
        implements Serializable, PrintErrorImportCeilingBudgetApi {

    @Resource(name = "importCeilingBudgetController")
    private ImportCeilingBudgetApi importCeilingBudgetController;
    
    @Resource(name = "preparedReportImportCeilingBudgetManagement")
    private PreparedReportImportCeilingBudgetManagement itsPreparedReportImportCeilingBudgetService;

    private List<BudgetKeyItemEntity> layout = new ArrayList<BudgetKeyItemEntity>();

    private List<List<CellCeilingBudgetDto>> fileInCellCeiling;

    private String footerColumnReport;

    private String footerResponseReport;

    private boolean isOnlyError;

    @Override
    public void init() {
        footerResponseReport = "";
        //this.fileInCellCeiling = this.importCeilingBudgetController.getFileInCellCeiling();
        //this.layout = this.importCeilingBudgetController.getLayoutFile();
        //this.lstCellWithError = this.importCeilingBudgetController.getLstCellWithError();
        this.isOnlyError = this.importCeilingBudgetController.getIsCheckBoxClicked();
        
        footerColumnReport = itsPreparedReportImportCeilingBudgetService.createFooterColumnReport(this.importCeilingBudgetController.getLayoutFile());
        footerResponseReport = footerResponseReport + 
                this.getMessage("ppp.techo.printerrorceilingbudget.etiqueta.etiquetasi") + this.getMessage("ppp.techo.printerrorceilingbudget.etiqueta.separador") 
                + getMessage("ppp.techo.printerrorceilingbudget.si") + getMessage("ppp.techo.printerrorceilingbudget.separador");
        footerResponseReport = footerResponseReport + 
                this.getMessage("ppp.techo.printerrorceilingbudget.etiqueta.etiquetano") + this.getMessage("ppp.techo.printerrorceilingbudget.etiqueta.separador") 
                +getMessage("ppp.techo.printerrorceilingbudget.no");
        

        if (!isOnlyError) {
           fileInCellCeiling = itsPreparedReportImportCeilingBudgetService.createReportWithError(this.importCeilingBudgetController.getLayoutFile()
                   , this.importCeilingBudgetController.getFileInCellCeiling(), this.importCeilingBudgetController.getLstCellWithError());
                   //createAllReport();
        } else {
           fileInCellCeiling  = itsPreparedReportImportCeilingBudgetService.createReportError(this.importCeilingBudgetController.getLayoutFile()
                   , this.importCeilingBudgetController.getFileInCellCeiling(), this.importCeilingBudgetController.getLstCellWithError());
                   //createDataErrorReport();
        }
        
        layout = itsPreparedReportImportCeilingBudgetService.createLayoutReportImport(this.importCeilingBudgetController.getLayoutFile());
    }    

    @Override
    public ImportCeilingBudgetApi getImportCeilingBudgetController() {
        return importCeilingBudgetController;
    }

    @Override
    public void setImportCeilingBudgetController(ImportCeilingBudgetApi importCeilingBudgetController) {
        this.importCeilingBudgetController = importCeilingBudgetController;
    }

    @Override
    public List<List<CellCeilingBudgetDto>> getFileInCellCeiling() {
        return fileInCellCeiling;
    }

    @Override
    public void setFileInCellCeiling(List<List<CellCeilingBudgetDto>> object) {
        fileInCellCeiling = object;
    }

    @Override
    public List<BudgetKeyItemEntity> getLayout() {
        return layout;
    }

    @Override
    public void setLayout(List<BudgetKeyItemEntity> object) {
        this.layout = object;
    }

    @Override
    public String navigateToImportCeilingbudget() {
        return "importCeilingBudget";
    }

    @Override
    public String getFooterColumnReport() {
        return this.footerColumnReport;
    }

    @Override
    public void setFooterColumnReport(String object) {
        this.footerColumnReport = object;
    }

    @Override
    public String getFooterResponseReport() {
        return this.footerResponseReport;
    }

    @Override
    public void setFooterResponseReport(String object) {
        this.footerResponseReport = object;
    }

    @Override
    public void importToFileExcel() {
        String sFileName = SIIFContextBase.getParamContext(KeyContextEnum.PATH).toString();
        sFileName = sFileName + this.getImportCeilingBudgetController().getCeilingConfiguration().getCeilingConfigName();
        sFileName = sFileName + this.getMessage("ppp.techo.printerrorceilingbudget.importexcel.number"  ) + 
                    this.getMessage("ppp.techo.printerrorceilingbudget.importexcel.extend");
        
        boolean bandera = itsPreparedReportImportCeilingBudgetService.createDocumentExcel(sFileName, layout, fileInCellCeiling);
        
        if (bandera){
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    getMessage("ppp.techo.printerrorceilingbudget.importarexcel.exitoso", sFileName),
                    getMessage("ppp.techo.printerrorceilingbudget.importarexcel.exitoso", sFileName));


        } else {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    getMessage("ppp.techo.printerrorceilingbudget.importarexcel.error", sFileName),
                    getMessage("ppp.techo.printerrorceilingbudget.importarexcel.error", sFileName));
        }
        
    }
}
