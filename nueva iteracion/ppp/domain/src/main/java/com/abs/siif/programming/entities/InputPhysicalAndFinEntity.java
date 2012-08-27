package com.abs.siif.programming.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ABS15
 */
@Entity
@Table(name = "siifpppaportacfisfinan")
public class InputPhysicalAndFinEntity implements
        Comparable<InputPhysicalAndFinEntity>, Serializable{

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idaportacionfisfin")
    private Long inputPhysicalAndFinEntityId;

    // Identificador de la PreFicha
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdPreFicha", nullable = false)
    private InvPreFileEntity inputPhysicalAndFinInvPreFile;
    
    // Físico a la Fecha
    @Column(name = "FisFecha")
    private double physicalToDate;
    // Físico para Ejecución
    @Column(name = "FisEjecucion")
    private double physicalToExec;
    // Físico Sig. Anio
    @Column(name = "FisSigAnio")
    private double phisicalToExecNextYear;
    // Financiero a la Fecha
    @Column(name = "FinanFecha")
    private double finantialToDate;
    // Financiero para Ejecución
    @Column(name = "FinanEjecucion")
    private double finantialToExec;
    // Financiero Sig. Año
    @Column(name = "FinanSigAnio")
    private double finantialToExecNextYear;

    public double getFinantialToDate() {
        return finantialToDate;
    }

    public void setFinantialToDate(double finantialToDate) {
        this.finantialToDate = finantialToDate;
    }

    public double getFinantialToExec() {
        return finantialToExec;
    }

    public void setFinantialToExec(double finantialToExec) {
        this.finantialToExec = finantialToExec;
    }

    public double getFinantialToExecNextYear() {
        return finantialToExecNextYear;
    }

    public void setFinantialToExecNextYear(double finantialToExecNextYear) {
        this.finantialToExecNextYear = finantialToExecNextYear;
    }

    public Long getInputPhysicalAndFinEntityId() {
        return inputPhysicalAndFinEntityId;
    }

    public void setInputPhysicalAndFinEntityId(Long inputPhysicalAndFinEntityId) {
        this.inputPhysicalAndFinEntityId = inputPhysicalAndFinEntityId;
    }

    public double getPhisicalToExecNextYear() {
        return phisicalToExecNextYear;
    }

    public void setPhisicalToExecNextYear(double phisicalToExecNextYear) {
        this.phisicalToExecNextYear = phisicalToExecNextYear;
    }

    public double getPhysicalToDate() {
        return physicalToDate;
    }

    public void setPhysicalToDate(double physicalToDate) {
        this.physicalToDate = physicalToDate;
    }

    public double getPhysicalToExec() {
        return physicalToExec;
    }

    public void setPhysicalToExec(double physicalToExec) {
        this.physicalToExec = physicalToExec;
    }

    public InvPreFileEntity getInputPhysicalAndFinInvPreFile() {
        return inputPhysicalAndFinInvPreFile;
    }

    public void setInputPhysicalAndFinInvPreFile(InvPreFileEntity inputPhysicalAndFinInvPreFile) {
        this.inputPhysicalAndFinInvPreFile = inputPhysicalAndFinInvPreFile;
    }
   
    @Override
    public int compareTo(InputPhysicalAndFinEntity anOtherEntity) {
        int result = -1;
        if (this.inputPhysicalAndFinEntityId != null 
                && anOtherEntity.inputPhysicalAndFinEntityId != null) {
            result = this.inputPhysicalAndFinEntityId.compareTo(
                anOtherEntity.inputPhysicalAndFinEntityId);
        }
        return result;
    }
    
    @Override 
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof InputPhysicalAndFinEntity 
                 && this.inputPhysicalAndFinEntityId != null
                 && ((InputPhysicalAndFinEntity) obj).inputPhysicalAndFinEntityId != null) {
            result = this.inputPhysicalAndFinEntityId.equals(
                    ((InputPhysicalAndFinEntity) obj).inputPhysicalAndFinEntityId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + (this.inputPhysicalAndFinEntityId != null 
                ? this.inputPhysicalAndFinEntityId.hashCode() : 0);
        return hash;
    }
    
}
