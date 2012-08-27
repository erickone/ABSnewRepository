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
import com.abs.siif.budget.dto.CeilingConfigurationDto;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.dto.ImportFileCeillingBudgetDto;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.entities.CeillingBudgetEntity;
import com.abs.siif.budget.management.BudgetKeyItemManagement;
import com.abs.siif.budget.management.ImportCeilingBudgetManagement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author absvalenzuela
 */
@Scope("session")
@Controller("importCeilingBudgetController")
public class ImportCeilingBudgetController extends SIIFControllerBase
        implements Serializable, ImportCeilingBudgetApi {

    private String currentOperationYear = "";

    private String currentFilePath = "";

    private String currentFileName = "";

    private List<ImportFileCeillingBudgetDto> lstArchivos;

    private List<CeilingConfigurationDto> configurationFilter;

    private List<CellCeilingBudgetDto> lstCellWithError;

    private List<CeillingBudgetEntity> lstCeilingBudget;

    private List<BudgetKeyItemEntity> layout = new ArrayList<BudgetKeyItemEntity>();

    private List<List<CellCeilingBudgetDto>> fileInCellCeiling;

    private Long keyCeilingBudgetId;

    private String descriptionFilter;

    private CeilingConfigurationDto ceilingConfiguration;

    @Resource(name = "importCeilingBudgetManagement")
    private ImportCeilingBudgetManagement itsImportCeilingBudgetService;

    @Resource(name = "budgetKeyItemManagement")
    private BudgetKeyItemManagement itsBudgeKeyItemManagement;

    private boolean isCheckBoxClicked;

    private boolean isCheckBoxImportClicked;

    /**
     * *******************************
     */
    // Banderas de Botones
    private boolean disableButtonValidate;

    private boolean disabledButtonImport;

    private boolean disabledButtonPrintError;

    private boolean disabledButtonDelete;

    private boolean disabledButtonBrowse;

    private boolean disabledButtonAddFile;

    private boolean disabledComboFilterCeilingBudget;

    private boolean disabledCheckBoxImport;

    private boolean disabledCheckboxErrores;

    @Override
    public void init() {

        fileInCellCeiling = null;
        lstArchivos = new ArrayList<ImportFileCeillingBudgetDto>();
        lstCellWithError = new ArrayList<CellCeilingBudgetDto>();
        lstCeilingBudget = new ArrayList<CeillingBudgetEntity>();

        if (this.getCurrentOperationYear().equals("")) {
            this.setCurrentOperationYear(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString());
        }
        this.configurationFilter = itsImportCeilingBudgetService.getAllCeillingconfigurationForYear(Integer.parseInt(this.getCurrentOperationYear()));

        this.setCurrentFilePath("");
        this.currentFileName = "";
        this.keyCeilingBudgetId = 0L;
        this.descriptionFilter = "";
        this.setDisabledButtonValidate(Boolean.TRUE);

        this.setDisabledButtonPrintError(Boolean.TRUE);
        this.setDisabledButtonDelete(Boolean.TRUE);
        this.setDisabledComboFilterCeilingBudget(Boolean.FALSE);

        this.setDisabledButtonAddFile(Boolean.TRUE);
        this.setIsCheckBoxClicked(Boolean.TRUE);
        this.setIsCheckBoxImportClicked(Boolean.TRUE);
        this.setDisabledButtonImport(Boolean.TRUE);

        this.setDisabledCheckboxErrores(Boolean.TRUE);
        this.setDisabledCheckBoxImport(Boolean.TRUE);
    }

    @Override
    public String getCurrentOperationYear() {
        return this.currentOperationYear;
    }

    @Override
    public void setCurrentOperationYear(String year) {
        this.currentOperationYear = year;
    }

    @Override
    public boolean getDisabledButtonValidate() {
        return this.disableButtonValidate;
    }

    @Override
    public void setDisabledButtonValidate(boolean enable) {
        this.disableButtonValidate = enable;
    }

    @Override
    public boolean getDisabledButtonImport() {
        return this.disabledButtonImport;
    }

    @Override
    public void setDisabledButtonImport(boolean enable) {
        this.disabledButtonImport = enable;
    }

    @Override
    public boolean getDisabledButtonPrintError() {
        return this.disabledButtonPrintError;
    }

    @Override
    public void setDisabledButtonPrintError(boolean enable) {
        this.disabledButtonPrintError = enable;
    }

    @Override
    public boolean getDisabledButtonDelete() {
        return this.disabledButtonDelete;
    }

    @Override
    public void setDisabledButtonDelete(boolean enable) {
        this.disabledButtonDelete = enable;
    }

    @Override
    public boolean getDisabledButtonBrowse() {
        return this.disabledButtonBrowse;
    }

    @Override
    public void setDisabledButtonBrowse(boolean enable) {
        this.disabledButtonBrowse = enable;
    }

    @Override
    public boolean getDisabledButtonAddFile() {
        return this.disabledButtonAddFile;
    }

    @Override
    public void setDisabledButtonAddFile(boolean enable) {
        this.disabledButtonAddFile = enable;
    }

    @Override
    public boolean getDisabledCheckBoxImport() {
        return this.disabledCheckBoxImport;
    }

    @Override
    public void setDisabledCheckBoxImport(boolean enable) {
        this.disabledCheckBoxImport = enable;
    }

    @Override
    public boolean getDisabledCheckboxErrores() {
        return this.disabledCheckboxErrores;
    }

    @Override
    public void setDisabledCheckboxErrores(boolean enable) {
        this.disabledCheckboxErrores = enable;
    }

    @Override
    public String getCurrentFilePath() {
        return currentFilePath;
    }

    @Override
    public void setCurrentFilePath(String filePath) {
        this.currentFilePath = filePath;
    }

    @Override
    public List<ImportFileCeillingBudgetDto> getLstArchivos() {
        return lstArchivos;
    }

    @Override
    public void setLstArchivos(List<ImportFileCeillingBudgetDto> archivos) {
        this.lstArchivos = archivos;
    }

    @Override
    public void agregarArchivo() {
        boolean bExistFileInList = false;
        for (ImportFileCeillingBudgetDto file : lstArchivos) {

            if (file.getFileName().equals(this.currentFileName)) {
                bExistFileInList = true;
            }
        }

        if (!bExistFileInList) {

            boolean archivoValido = itsImportCeilingBudgetService.validateFile(currentFilePath, layout);
            if (archivoValido) {
                ImportFileCeillingBudgetDto objfile = new ImportFileCeillingBudgetDto();
                File file = new File(currentFilePath);
                objfile.setPathFile(this.currentFilePath);
                objfile.setFileName(file.getName());
                objfile.setSizeFile(file.length());
                this.lstArchivos.add(objfile);

                this.setCurrentFilePath("");
                this.currentFileName = "";
                this.setDisabledButtonAddFile(Boolean.TRUE);
                this.setDisabledComboFilterCeilingBudget(Boolean.TRUE);
                this.setDisabledButtonDelete(Boolean.FALSE);
                this.setDisabledButtonValidate(Boolean.FALSE);
            } else {
                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        getMessage("ppp.techo.cargatechopresupuestal.errorfile.layout", currentFileName),
                        getMessage("ppp.techo.cargatechopresupuestal.errorfile.layout", currentFileName));
            }
        } else {
            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                    getMessage("ppp.techo.cargatechopresupuestal.errorfile.existente", currentFileName),
                    getMessage("ppp.techo.cargatechopresupuestal.errorfile.existente", currentFileName));
        }
    }

    @Override
    public void processFileUpload(FileUploadEvent event) {


        try {
            UploadedFile sFileExcel = event.getFile();

            InputStream inputStream = new BufferedInputStream(sFileExcel.getInputstream());

            File file = new File(SIIFContextBase.getParamContext(KeyContextEnum.PATH) + sFileExcel.getFileName());

            this.currentFilePath = file.getAbsolutePath().toString();
            currentFileName = file.getName();
            FileOutputStream fout = new FileOutputStream(file);

            while (inputStream.available() != 0) {

                fout.write(inputStream.read());

            }
            fout.close();
            this.setDisabledButtonAddFile(Boolean.FALSE);

        } catch (Exception ex) {
        }
    }

    @Override
    public String navigateToMainMenu() {
        return "options";
    }

    @Override
    public String navigateToImportCeilingbudget(boolean bandera) {
        String nextPage = "";

        if (this.isCheckBoxImportClicked && bandera) {
            nextPage = "validationImportCeilingbBdget";
        } else {
            nextPage = "printErrorImportCeilingBudget";
        }

        return nextPage;
    }

    @Override
    public List<CeilingConfigurationDto> getConfigurationFilter() {
        return configurationFilter;
    }

    @Override
    public void setConfigurationFilter(List<CeilingConfigurationDto> configurationFilter) {
        this.configurationFilter = configurationFilter;
    }

    @Override
    public Long getKeyCeilingBudgetId() {
        return keyCeilingBudgetId;
    }

    @Override
    public void setKeyCeilingBudgetId(Long keyCeilingBudget) {
        this.keyCeilingBudgetId = keyCeilingBudget;
    }

    @Override
    public List<CellCeilingBudgetDto> getLstCellWithError() {
        return lstCellWithError;
    }

    @Override
    public void setLstCellWithError(List<CellCeilingBudgetDto> lstCellWithError) {
        this.lstCellWithError = lstCellWithError;
    }

    @Override
    public void changeDescription() {
        boolean bandera = true;

        for (CeilingConfigurationDto ceilingConf : configurationFilter) {

            if (ceilingConf.getCeilingConfigId().longValue() == this.getKeyCeilingBudgetId().longValue()) {
                this.descriptionFilter = ceilingConf.getCeilingConfigDesc();
                this.setCeilingConfiguration(ceilingConf);
                layout = itsBudgeKeyItemManagement.getBudgetItemsRelatedCeilingConf(getKeyCeilingBudgetId());
                this.setDisabledButtonAddFile(Boolean.FALSE);
                bandera = false;
                break;
            }
        }
        if (bandera) {
            this.descriptionFilter = "";
        }

    }

    @Override
    public String getDescriptionFilter() {
        return this.descriptionFilter;
    }

    @Override
    public void setDescriptionFilter(String filter) {
        this.descriptionFilter = filter;
    }

    @Override
    public void executeImportFiles() {
        lstCeilingBudget = itsImportCeilingBudgetService.executeImportCeilling(layout, lstArchivos);
    }

    @Override
    public void executeValidationFiles() {
        this.fileInCellCeiling = itsImportCeilingBudgetService.readFiles(layout, lstArchivos);
        lstCellWithError = itsImportCeilingBudgetService.executeValidateFile(layout, lstArchivos);
        this.setDisabledCheckboxErrores(Boolean.FALSE);
        this.setDisabledCheckBoxImport(Boolean.FALSE);
        // Cambio de Techo para que se muetren los 2 botones de impresion de errores y el
        // boton de importar el archivo cuando tenga errores.        
        if (lstCellWithError.isEmpty()) {
            this.setIsCheckBoxImportClicked(Boolean.TRUE);
            this.setDisabledButtonPrintError(Boolean.TRUE);
            this.setDisabledButtonImport(Boolean.FALSE);

            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    getMessage("ppp.techo.importceilingbudget.import.validacion"),
                    getMessage("ppp.techo.importceilingbudget.import.validacion"));

            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    getMessage("ppp.techo.importceilingbudget.import.exitoso", String.valueOf(fileInCellCeiling.size())),
                    getMessage("ppp.techo.importceilingbudget.import.exitoso", String.valueOf(fileInCellCeiling.size())));

        } else {
            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                    getMessage("ppp.techo.importceilingbudget.import.validacion"),
                    getMessage("ppp.techo.importceilingbudget.import.validacion"));
            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                    getMessage("ppp.techo.importceilingbudget.import.exitoso", String.valueOf(fileInCellCeiling.size() - lstCellWithError.size())),
                    getMessage("ppp.techo.importceilingbudget.import.exitoso", String.valueOf(fileInCellCeiling.size() - lstCellWithError.size())));
            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                    getMessage("ppp.techo.importceilingbudget.import.error", String.valueOf(lstCellWithError.size())),
                    getMessage("ppp.techo.importceilingbudget.import.error", String.valueOf(lstCellWithError.size())));
            this.setIsCheckBoxImportClicked(Boolean.FALSE);
            this.setDisabledButtonImport(Boolean.FALSE);
            this.setDisabledButtonPrintError(Boolean.FALSE);
        }
    }

    @Override
    public void viewRowWithError() {
    }

    @Override
    public List<CeillingBudgetEntity> getLstCeilingBudget() {
        return lstCeilingBudget;
    }

    @Override
    public void setLstCeilingBudget(List<CeillingBudgetEntity> lstCeilingBudget) {
        this.lstCeilingBudget = lstCeilingBudget;
    }

    @Override
    public void removeFileForList() {
        lstArchivos.removeAll(lstArchivos);
        this.setDisabledButtonValidate(Boolean.TRUE);
        this.setDisabledButtonPrintError(Boolean.TRUE);
        this.setDisabledButtonImport(Boolean.TRUE);
        this.setDisabledButtonDelete(Boolean.TRUE);
        this.setIsCheckBoxClicked(Boolean.TRUE);
        this.setIsCheckBoxImportClicked(Boolean.TRUE);
        this.setDisabledComboFilterCeilingBudget(Boolean.FALSE);
        this.setDisabledCheckboxErrores(Boolean.TRUE);
        this.setDisabledCheckBoxImport(Boolean.TRUE);
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                getMessage("ppp.techo.importceilingbudget.remove.exitoso"),
                getMessage("ppp.techo.importceilingbudget.remove.exitoso"));
    }

    @Override
    public List<BudgetKeyItemEntity> getLayoutFile() {
        return this.layout;
    }

    @Override
    public void setLayoutFile(List<BudgetKeyItemEntity> object) {
        this.layout = object;
    }

    @Override
    public CeilingConfigurationDto getCeilingConfiguration() {
        return this.ceilingConfiguration;
    }

    @Override
    public void setCeilingConfiguration(CeilingConfigurationDto object) {
        this.ceilingConfiguration = object;
    }

    @Override
    public List<List<CellCeilingBudgetDto>> getFileInCellCeiling() {
        return this.fileInCellCeiling;
    }

    @Override
    public void setFileInCellCeiling(List<List<CellCeilingBudgetDto>> object) {
        this.fileInCellCeiling = object;
    }

    @Override
    public boolean getIsCheckBoxClicked() {
        return this.isCheckBoxClicked;
    }

    @Override
    public void setIsCheckBoxClicked(boolean enable) {
        this.isCheckBoxClicked = enable;
    }

    @Override
    public boolean getIsCheckBoxImportClicked() {
        return this.isCheckBoxImportClicked;
    }

    @Override
    public void setIsCheckBoxImportClicked(boolean enable) {
        this.isCheckBoxImportClicked = enable;
    }

    @Override
    public String getFilesSeparateComma() {
        String sFilename = "";
        for (ImportFileCeillingBudgetDto object : this.lstArchivos) {
            sFilename = sFilename + object.getFileName() + ",";
        }

        return sFilename.substring(0, sFilename.length() - 1);
    }

    @Override
    public void reloadListFilterCeilingBudget() {
        this.setCurrentOperationYear(this.getCurrentOperationYear());
        init();
    }

    @Override
    public boolean getDisabledComboFilterCeilingBudget() {
        return this.disabledComboFilterCeilingBudget;
    }

    @Override
    public void setDisabledComboFilterCeilingBudget(boolean object) {
        this.disabledComboFilterCeilingBudget = object;
    }
}
