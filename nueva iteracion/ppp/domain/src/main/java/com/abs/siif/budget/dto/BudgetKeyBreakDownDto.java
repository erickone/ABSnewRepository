/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dto;

import java.math.BigDecimal;

/**
 *
 * @author Erick Leija
 */
public class BudgetKeyBreakDownDto 
{
    private int idcvepptaldesglose;
    private int idcvepresupuestal;
    private BigDecimal monto;
    private BigDecimal basico;
    private BigDecimal nobasico;
    //clave de siifpppcvepresupuestal
    private String clave;

    public int getIdcvepptaldesglose() {
        return idcvepptaldesglose;
    }

    public void setIdcvepptaldesglose(int idcvepptaldesglose) {
        this.idcvepptaldesglose = idcvepptaldesglose;
    }

    public int getIdcvepresupuestal() {
        return idcvepresupuestal;
    }

    public void setIdcvepresupuestal(int idcvepresupuestal) {
        this.idcvepresupuestal = idcvepresupuestal;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getBasico() {
        return basico;
    }

    public void setBasico(BigDecimal basico) {
        this.basico = basico;
    }

    public BigDecimal getNobasico() {
        return nobasico;
    }

    public void setNobasico(BigDecimal nobasico) {
        this.nobasico = nobasico;
    }

    public String getClave()
    {
        return clave;
    }

    public void setClave(String clave)
    {
        this.clave = clave;
    }

}
