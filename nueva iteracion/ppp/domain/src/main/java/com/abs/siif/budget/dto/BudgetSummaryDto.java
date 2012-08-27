/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dto;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 * Regresa la suma del monto original, el monto adicional y la suma de ambos
 */
public class BudgetSummaryDto {
    
    private Double originalAmount;
    private Double additionalAmount;
    private Double sum;
    
    public BudgetSummaryDto()
    {
        originalAmount=0d;
        additionalAmount=0d;
        sum=0d;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Double getAdditionalAmount() {
        return additionalAmount;
    }

    public void setAdditionalAmount(Double additionalAmount) {
        this.additionalAmount = additionalAmount;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
    
    
}
