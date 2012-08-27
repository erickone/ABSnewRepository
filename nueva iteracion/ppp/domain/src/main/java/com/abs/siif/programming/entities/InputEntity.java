package com.abs.siif.programming.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
@Entity
@Table(name = "siifpppaportacionprefich")
public class InputEntity implements
        Comparable<InputEntity>, Serializable {

   // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdAportacionPreFicha")
    private Long inputId;

    // Identificador de la PreFicha
    @ManyToOne
    @JoinColumn(name = "IdPreFicha", nullable = false)
    private InvPreFileEntity inputInvPreFile;

    // Tipo de aportación (puede ser Inicial o adicional)
    @Column(name = "Tipo", length = 50, nullable = false)
    private String inputType;

    // Federal
    @Column(name = "Federal")
    private double inputFederal;

    // Estatal
    @Column(name = "Estatal")
    private double inputState;

    // Municipal
    @Column(name = "Municipal")
    private double inputMunicipality;

    // Particular
    @Column(name = "Particular")
    private double inputParticular;

    // Especie
    @Column(name = "Especie")
    private double inputSpecie;

    public double getInputFederal() {
        return inputFederal;
    }

    public void setInputFederal(double inputFederal) {
        this.inputFederal = inputFederal;
    }

    public Long getInputId() {
        return inputId;
    }

    public void setInputId(Long inputId) {
        this.inputId = inputId;
    }

    public InvPreFileEntity getInputInvPreFile() {
        return inputInvPreFile;
    }

    public void setInputInvPreFile(InvPreFileEntity inputInvPreFile) {
        this.inputInvPreFile = inputInvPreFile;
    }

    public double getInputMunicipality() {
        return inputMunicipality;
    }

    public void setInputMunicipality(double inputMunicipality) {
        this.inputMunicipality = inputMunicipality;
    }

    public double getInputParticular() {
        return inputParticular;
    }

    public void setInputParticular(double inputParticular) {
        this.inputParticular = inputParticular;
    }

    public double getInputSpecie() {
        return inputSpecie;
    }

    public void setInputSpecie(double inputSpecie) {
        this.inputSpecie = inputSpecie;
    }

    public double getInputState() {
        return inputState;
    }

    public void setInputState(double inputState) {
        this.inputState = inputState;
    }

    public String getInputType() {
        return inputType != null ? inputType.trim() : inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }    

    @Override
    public int compareTo(InputEntity anOtherEntity) {
        int result = -1;
        if (this.inputId != null 
                && anOtherEntity.inputId != null) {
            result = this.inputId.compareTo(
                anOtherEntity.inputId);
        }
        return result;
    }
    
    @Override 
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof InputEntity 
                 && this.inputId != null
                 && ((InputEntity) obj).inputId != null) {
            result = this.inputId.equals(
                    ((InputEntity) obj).inputId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + (this.inputId != null 
                ? this.inputId.hashCode() : 0);
        return hash;
    }

}
