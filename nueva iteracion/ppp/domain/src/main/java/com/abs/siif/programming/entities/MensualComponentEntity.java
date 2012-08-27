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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Entity
@Table(name = "siifpppcomponentemens")
public class MensualComponentEntity implements 
        Comparable<MensualComponentEntity>, Serializable{
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdComponenteMens")
    private Long mensualComponentId;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "IdComponente", nullable = false)
    private ComponentEntity component;
    /**
     * Valor que se colocara como programado
     */
    @Column(name = "MetaProgramada", nullable = false)
    private double programmedGoal;
    
    /**
     * Los conceptos programados deberan de tener la siguiente 
     * nomenclatura de acuerdo a los nombres dados por los enums
     * @see com.abs.siif.base.common.MonthsEnum
     * @see com.abs.siif.base.common.IniToTotalEnum
     */
    @Column(name = "ConceptoProgramacion", length = 100, nullable = false)
    private String conceptProg;

    public double getProgrammedGoal() {
        return programmedGoal;
    }

    public void setProgrammedGoal(double aProgrammedGoal) {
        this.programmedGoal = aProgrammedGoal;
    }

   
    
    public ComponentEntity getComponent() {
        return component;
    }

    public void setComponent(ComponentEntity aComponent) {
        this.component = aComponent;
    }

    public Long getMensualComponentId() {
        return mensualComponentId;
    }

    public void setMensualComponentId(Long aMensualComponentId) {
        this.mensualComponentId = aMensualComponentId;
    }

    /**
     * @return the conceptProg
     */
    public String getConceptProg() {
        return conceptProg != null ? conceptProg.trim() : conceptProg ;
    }

    /**
     * @param conceptProg the conceptProg to set
     */
    public void setConceptProg(String aConceptProg) {
        this.conceptProg = aConceptProg;
    }

    @Override
    public int compareTo(MensualComponentEntity obj)
    {
        int result = -1;
        if (this.mensualComponentId != null && obj.mensualComponentId != null) {
            result = this.mensualComponentId.compareTo(obj.mensualComponentId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof MensualComponentEntity 
                 && this.mensualComponentId != null
                 && ((MensualComponentEntity) obj).mensualComponentId != null) {
            result = this.mensualComponentId.equals(
                    ((MensualComponentEntity) obj).mensualComponentId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + (this.mensualComponentId != null 
                ? this.mensualComponentId.hashCode() : 0);
        return hash;
    }
}
