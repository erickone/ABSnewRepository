/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.api.controller;

import com.abs.siif.budget.controller.ImportCeilingBudgetController;
import com.abs.siif.budget.entities.CeillingBudgetEntity;
import java.util.List;

/**
 *
 * @author absvalenzuela
 */
public interface ValidationImportCeilingBudgetApi {
    
    public void init();
    
    public ImportCeilingBudgetApi getImportCeilingBudgetController();
    
    public void setImportCeilingBudgetController(ImportCeilingBudgetApi importCeilingBudget);
    
    public List<CeillingBudgetEntity> getLstImportCeilingbudget();
    
    public void setLstImportCeilingbudget(List<CeillingBudgetEntity> object);
    
    public String navigateMainMenu();
    
    public Long getDischargeBudget();
    
    public String getDischargeBudgetString();
    
    public void setDischargeBudget(Long dischargeBudget);
    
    public String getIngressBudget();
    
    public void setIngressBudget(String ingressBudget);
    
    public String getDifferentIngressAndPlusBudgetString();
    
    public void calculateDifferentIngressAndPlusBudgetString();
   
    public void acceptImportCeilingBudget();
    
    public String getConceptLayout();
    
    public void setConceptLayout(String object);
    
    public Long getCountRow();
    
    // Metodos para habilitar y desabilitar botones
    public boolean getDisabledButtonAceptarTecho();

    public void setDisabledButtonAceptarTecho(boolean enable);

    public boolean getDisabledButtonRechazarTecho();

    public void setDisabledButtonRechazarTecho(boolean enable);
    
    public String navigateMenuCeiling();
    
    public boolean getIsCheckBoxDeleteCurrentCeilingClicked();
    
    public void setIsCheckBoxDeleteCurrentCeilingClicked(boolean bandera);
    
}
