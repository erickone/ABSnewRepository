package com.abs.siif.programming.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Table(name="siifpppunimedmeta")
@Entity
public class UnitMeasureGoalEntity 
        implements Serializable, Comparable<UnitMeasureGoalEntity> {
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idunimedmeta")
    private Long unitMeasureGoalId;
     
    @Column(name="descripcion",length=150)
    private String unitMeasureGoalDescription;

    public String getUnitMeasureGoalDescription() {
        return unitMeasureGoalDescription != null ? unitMeasureGoalDescription.trim():unitMeasureGoalDescription;
    }

    public void setUnitMeasureGoalDescription(String unitMeasureGoalDescription) {
        this.unitMeasureGoalDescription = unitMeasureGoalDescription;
    }

    public Long getUnitMeasureGoalId() {
        return unitMeasureGoalId;
    }

    public void setUnitMeasureGoalId(Long unitMeasureGoalId) {
        this.unitMeasureGoalId = unitMeasureGoalId;
    }
    
    @Override
    public int compareTo(UnitMeasureGoalEntity obj) {
        int result = -1;
        if (this.unitMeasureGoalId != null && obj.getUnitMeasureGoalId() != null) {
            result = this.unitMeasureGoalId.compareTo(obj.getUnitMeasureGoalId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj instanceof UnitMeasureGoalEntity && this.unitMeasureGoalId != null 
                && ((UnitMeasureGoalEntity)obj).getUnitMeasureGoalId() != null){
            result = this.unitMeasureGoalId.equals(
                    ((UnitMeasureGoalEntity)obj).getUnitMeasureGoalId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.unitMeasureGoalId != null ? this.unitMeasureGoalId.hashCode() : 0);
        return hash;
    }
    
}
