package com.abs.siif.programming.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 */
@Entity
@Table(name="siifpppdimensionamiento")
public class SizingEntity implements
        Comparable<SizingEntity>, Serializable
{
     // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "Iddimensionamiento")
    private Long sizingId;
    
    // numero consecutivo
    @Column(name = "Consecutivo")
    private int sizingConsecutiveNumber;

    // Descripción
    @Column(name="Descripcion", length= 255)
    private String sizingDescription;
    
    //Cantidad
    @Column(name="Cantidad")
    private int sizingCantity;
    
    //Asignación Inicial
    @Column(name="Inicial")
    private int sizingInitial;
    
    // Asignacion Adicional
    @Column(name="Adicional")
    private int sizingAditional;
    
    // Suma
    @Column(name="Suma")
    private int sizingSummatory;
    
    //Porcentaje
    @Column(name="Porcentaje")
    private int sizingPercent;
    
    @ManyToOne
    @JoinColumn(name = "IdPreFicha", nullable = false)
    private InvPreFileEntity sizingInvPreFile;

    public int getSizingAditional()
    {
        return sizingAditional;
    }

    public void setSizingAditional(int sizingAditional)
    {
        this.sizingAditional = sizingAditional;
    }

    public int getSizingConsecutiveNumber()
    {
        return sizingConsecutiveNumber;
    }

    public void setSizingConsecutiveNumber(int sizingConsecutiveNumber)
    {
        this.sizingConsecutiveNumber = sizingConsecutiveNumber;
    }

    public String getSizingDescription()
    {
        return sizingDescription != null ? sizingDescription.trim():sizingDescription;
    }

    public void setSizingDescription(String sizingDescription)
    {
        this.sizingDescription = sizingDescription;
    }

    public Long getSizingId()
    {
        return sizingId;
    }

    public void setSizingId(Long sizingId)
    {
        this.sizingId = sizingId;
    }

    public int getSizingCantity()
    {
        return sizingCantity;
    }

    public void setSizingCantity(int sizingCantity)
    {
        this.sizingCantity = sizingCantity;
    }

    public double getSizingInitial()
    {
        return sizingInitial;
    }

    public void setSizingInitial(int sizingInitial)
    {
        this.sizingInitial = sizingInitial;
    }

    public InvPreFileEntity getInputInvPreFile()
    {
        return sizingInvPreFile;
    }

    public void setInputInvPreFile(InvPreFileEntity inputInvPreFile)
    {
        this.sizingInvPreFile = inputInvPreFile;
    }

    public double getSizingPercent()
    {
        return sizingPercent;
    }

    public void setSizingPercent(int sizingPercent)
    {
        this.sizingPercent = sizingPercent;
    }

    public double getSizingSummatory()
    {
        return sizingSummatory;
    }

    public void setSizingSummatory(int sizingSummatory)
    {
        this.sizingSummatory = sizingSummatory;
    }
    
    
    @Override
    public int compareTo(SizingEntity obj) {
        int result = -1;
        if (this.sizingId != null && obj.sizingId != null) {
            result = this.sizingId.compareTo(obj.sizingId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof SizingEntity
                && this.sizingId != null
                && ((SizingEntity) obj).sizingId != null) {

            result = this.sizingId.equals(
                    ((SizingEntity) obj).sizingId);

        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.sizingId != null
                ? this.sizingId.hashCode() : 0);
        return hash;
    }
    
}
