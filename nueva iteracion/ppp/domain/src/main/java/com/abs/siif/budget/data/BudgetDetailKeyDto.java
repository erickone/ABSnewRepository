/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.data;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 * Regresa el id y clave del elemento que conforma la clave presupuestal
 */
public class BudgetDetailKeyDto {
    
    private String partialKey;
    private Long realId;
    private Long budgetKeyConfigurationId;

    public Long getBudgetKeyConfigurationId() {
        return budgetKeyConfigurationId;
    }

    public void setBudgetKeyConfigurationId(Long budgetKeyConfigurationId) {
        this.budgetKeyConfigurationId = budgetKeyConfigurationId;
    }
    

    public String getPartialKey() {
        return partialKey;
    }

    public void setPartialKey(String partialKey) {
        this.partialKey = partialKey;
    }

    public Long getRealId() {
        return realId;
    }

    public void setRealId(Long realId) {
        this.realId = realId;
    }
    
    
}
