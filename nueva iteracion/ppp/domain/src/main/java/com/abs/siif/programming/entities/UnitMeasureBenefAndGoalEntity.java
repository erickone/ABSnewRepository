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
@Table(name="siifpppunimedmetabenef")
@Entity
public class UnitMeasureBenefAndGoalEntity  implements 
        Serializable, Comparable<UnitMeasureBenefAndGoalEntity> {
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idunimedbenmeta")
    private Long unitMeasureBenefAndGoalId;
     
    @Column(name="descripcion",length=150)
    private String unitMeasureBenefAndGoalDescription;

    public String getUnitMeasureBenefAndGoalDescription() {
        return unitMeasureBenefAndGoalDescription != null ? unitMeasureBenefAndGoalDescription.trim(): unitMeasureBenefAndGoalDescription;
    }

    public void setUnitMeasureBenefAndGoalDescription(String unitMeasureBenefAndGoalDescription) {
        this.unitMeasureBenefAndGoalDescription = unitMeasureBenefAndGoalDescription;
    }

    public Long getUnitMeasureBenefAndGoalId() {
        return unitMeasureBenefAndGoalId;
    }

    public void setUnitMeasureBenefAndGoalId(Long unitMeasureBenefAndGoalId) {
        this.unitMeasureBenefAndGoalId = unitMeasureBenefAndGoalId;
    }

    @Override
    public int compareTo(UnitMeasureBenefAndGoalEntity obj) {
        int result = -1;
        if (this.unitMeasureBenefAndGoalId != null && obj.unitMeasureBenefAndGoalId != null) {
            result = this.unitMeasureBenefAndGoalId.compareTo(obj.unitMeasureBenefAndGoalId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof UnitMeasureBenefAndGoalEntity
                && this.unitMeasureBenefAndGoalId != null
                && ((UnitMeasureBenefAndGoalEntity) obj).unitMeasureBenefAndGoalId != null) {

            result = this.unitMeasureBenefAndGoalId.equals(
                    ((UnitMeasureBenefAndGoalEntity) obj).unitMeasureBenefAndGoalId);

        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.unitMeasureBenefAndGoalId != null
                ? this.unitMeasureBenefAndGoalId.hashCode() : 0);
        return hash;
    }
    
    
}
