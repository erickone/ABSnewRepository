/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author abs71
 */
public class InitBudgetKeyPreFileDto implements Serializable {
    
    private String clavepresupuestal;
    private String fuentefinanciamiento;
    private String vencimiento;
    private BigDecimal techocve;
    private BigDecimal asignadoestatal;
    private BigDecimal asignadoadicional;
    private int objetoid;
    private int cveid;

    public int getCveid() {
        return cveid;
    }

    public void setCveid(int cveid) {
        this.cveid = cveid;
    }

    
    public int getObjetoId() {
        return objetoid;
    }

    public void setObjetoId(int objetoId) {
        this.objetoid = objetoId;
    }

    
    public BigDecimal getAsignadoAdicional() {
        return asignadoadicional;
    }

    public void setAsignadoAdicional(BigDecimal asignadoadicional) {
        this.asignadoadicional = asignadoadicional;
    }

    public BigDecimal getAsignadoEstatal() {
        return asignadoestatal;
    }

    public void setAsignadoEstatal(BigDecimal asignadoestatal) {
        this.asignadoestatal = asignadoestatal;
    }

    public String getClavePresupuestal() {
        return clavepresupuestal;
    }

    public void setClavePresupuestal(String clavepresupuestal) {
        this.clavepresupuestal = clavepresupuestal;
    }

    public String getFuenteFinanciamiento() {
        return fuentefinanciamiento;
    }

    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuentefinanciamiento = fuenteFinanciamiento;
    }

    public BigDecimal getTechoCve() {
        return techocve;
    }

    public void setTechoCve(BigDecimal techoCve) {
        this.techocve = techoCve;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }
    
    
}
