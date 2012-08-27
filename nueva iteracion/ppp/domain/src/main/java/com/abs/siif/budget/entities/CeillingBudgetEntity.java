/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.entities;

import com.abs.siif.support.FormatNumber;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author absvalenzuela
 */
@Entity
@Table(name = "siifppptechopptal")
public class CeillingBudgetEntity implements Serializable{
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idtechopptal")
    private Long techoPresupuestalId;
    
    @Column(name="monto")
    private long monto;
    
    @Column(name="ClaveCarga")
    private String claveCarga;
    
    @Column (name="esftefto")
    private boolean esFuenteFinanciamiento;
    
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name = "idconftechopptal", nullable = false, updatable = false)
     private CeilingConfigurationEntity ceilingConfigId;

    public String getClaveCarga() {
        return claveCarga != null ? claveCarga.trim() :
               claveCarga;
    }

    public void setClaveCarga(String claveCarga) {
        this.claveCarga = claveCarga;
    }

    public boolean isEsFuenteFinanciamiento() {
        return esFuenteFinanciamiento;
    }

    public void setEsFuenteFinanciamiento(boolean esFuenteFinanciamiento) {
        this.esFuenteFinanciamiento = esFuenteFinanciamiento;
    }

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
    }

    public Long getTechoPresupuestalId() {
        return techoPresupuestalId;
    }

    public void setTechoPresupuestalId(Long techoPresupuestalId) {
        this.techoPresupuestalId = techoPresupuestalId;
    }
    
    public String getMontoToString(){
        return monto > 0 ? FormatNumber.formatNumber(String.valueOf(monto)) : "0";
    }

    public CeilingConfigurationEntity getCeilingConfigId() {
        return ceilingConfigId;
    }

    public void setCeilingConfigId(CeilingConfigurationEntity ceilingConfigId) {
        this.ceilingConfigId = ceilingConfigId;
    }
    
    
    
    
}
