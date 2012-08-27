/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.api.controller.ImportCeilingBudgetApi;
import com.abs.siif.budget.api.controller.ValidationImportCeilingBudgetApi;
import com.abs.siif.budget.dto.ValidateDeleteCeilingBudgetDto;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.budget.entities.CeillingBudgetEntity;
import com.abs.siif.budget.management.ImportCeilingBudgetManagement;
import com.abs.siif.support.FormatNumber;
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
@Controller("validationImportCeilingBudgetController")
public class ValidationImportCeilingBudgetController extends SIIFControllerBase
        implements Serializable, ValidationImportCeilingBudgetApi {

    @Resource(name = "importCeilingBudgetController")
    private ImportCeilingBudgetApi importCeilingBudgetController;

    private List<CeillingBudgetEntity> lstImportCeilingbudget;

    @Resource(name = "importCeilingBudgetManagement")
    private ImportCeilingBudgetManagement itsImportCeilingBudgetService;
    
    private ValidateDeleteCeilingBudgetDto objdeleteCeiling;

    private String conceptoLayout = null;

    private Long dischargeBudget;

    private String ingressBudget;

    private Long differentIngressAndPlusBudget;

    private Long countRow;

    private boolean disabledButtonAceptarTecho;

    private boolean disabledButtonRechazarTecho;

    private boolean IsCheckBoxDeleteCurrentCeilingClicked;

    public void init() {
        lstImportCeilingbudget = this.importCeilingBudgetController.getLstCeilingBudget();
        dischargeBudget = new Long(0);
        countRow = 1L;
        this.setDisabledButtonAceptarTecho(false);
        this.setDisabledButtonRechazarTecho(false);

        List<CeillingBudgetEntity> lstCeiling = new ArrayList<CeillingBudgetEntity>();

        for (CeillingBudgetEntity objEntity : lstImportCeilingbudget) {
            dischargeBudget = dischargeBudget + objEntity.getMonto();

            CeilingConfigurationEntity newObject = new CeilingConfigurationEntity();
            newObject.setCeilingConfigId(importCeilingBudgetController.getCeilingConfiguration().getCeilingConfigId());

            objEntity.setCeilingConfigId(newObject);
            objEntity.setTechoPresupuestalId(countRow++);
            lstCeiling.add(objEntity);
        }
        lstImportCeilingbudget = lstCeiling;
        conceptoLayout = this.configurationConceptoLayout(importCeilingBudgetController.getLayoutFile());
        this.setIsCheckBoxDeleteCurrentCeilingClicked(false);

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
    public List<CeillingBudgetEntity> getLstImportCeilingbudget() {
        return lstImportCeilingbudget;
    }

    @Override
    public void setLstImportCeilingbudget(List<CeillingBudgetEntity> object) {
        lstImportCeilingbudget = object;
    }

    @Override
    public String navigateMainMenu() {
        return "options";
    }

    @Override
    public String navigateMenuCeiling() {
        return "importCeilingBudget";
    }

    @Override
    public Long getDischargeBudget() {
        return this.dischargeBudget;
    }

    @Override
    public String getDischargeBudgetString() {
        return FormatNumber.formatNumber(this.dischargeBudget.toString());
    }

    @Override
    public void setDischargeBudget(Long DischargeBudget) {
        this.dischargeBudget = DischargeBudget;
    }

    @Override
    public String getIngressBudget() {
        return this.ingressBudget;
    }

    @Override
    public void setIngressBudget(String ingressBudget) {
        this.ingressBudget = ingressBudget;
    }

    @Override
    public void calculateDifferentIngressAndPlusBudgetString() {
        Long ingress = 0L;
        try{
        ingress = ingressBudget!= null ? Long.parseLong(ingressBudget.replaceAll(",", "")) : 0L;
        }catch(Exception ex){}
               
        this.differentIngressAndPlusBudget = ingress - this.getDischargeBudget() ;        
    }
    
    @Override
    public String getDifferentIngressAndPlusBudgetString (){
        return this.differentIngressAndPlusBudget != null ? 
                FormatNumber.formatNumber(this.differentIngressAndPlusBudget.toString()) : "";
    }

    @Override
    public void acceptImportCeilingBudget() {
        
        objdeleteCeiling = new ValidateDeleteCeilingBudgetDto();
        
        objdeleteCeiling.setDeleteCeilingBudget(this.getIsCheckBoxDeleteCurrentCeilingClicked());
        
        if (itsImportCeilingBudgetService.saveCeillingBudgetItem(objdeleteCeiling, lstImportCeilingbudget)) {

            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    getMessage("ppp.techo.validationimportceilingbudget.exitoso"),
                    getMessage("ppp.techo.validationimportceilingbudget.exitoso"));

            this.setDisabledButtonAceptarTecho(true);
            this.setDisabledButtonRechazarTecho(true);

        } else {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    getMessage("ppp.techo.validationimportceilingbudget.error"),
                    getMessage("ppp.techo.validationimportceilingbudget.error"));
        }
    }

    private String configurationConceptoLayout(List<BudgetKeyItemEntity> object) {
        String concepto = "";

        for (BudgetKeyItemEntity entity : object) {
            concepto = concepto + entity.getBudgetKeyName() + ",";
        }

        if (concepto.length() > 0) {
            concepto = concepto.substring(0, concepto.length() - 1);
        }
        return concepto;
    }

    @Override
    public String getConceptLayout() {
        return this.conceptoLayout;
    }

    @Override
    public void setConceptLayout(String object) {
        this.conceptoLayout = object;
    }

    @Override
    public Long getCountRow() {
        return this.countRow;
    }

    @Override
    public boolean getDisabledButtonAceptarTecho() {
        return disabledButtonAceptarTecho;
    }

    @Override
    public void setDisabledButtonAceptarTecho(boolean enable) {
        this.disabledButtonAceptarTecho = enable;
    }

    @Override
    public boolean getDisabledButtonRechazarTecho() {
        return disabledButtonRechazarTecho;
    }

    @Override
    public void setDisabledButtonRechazarTecho(boolean enable) {
        this.disabledButtonRechazarTecho = enable;
    }

    @Override
    public boolean getIsCheckBoxDeleteCurrentCeilingClicked() {
        return IsCheckBoxDeleteCurrentCeilingClicked;
    }

    @Override
    public void setIsCheckBoxDeleteCurrentCeilingClicked(boolean bandera) {
        this.IsCheckBoxDeleteCurrentCeilingClicked = bandera;
    }
}
