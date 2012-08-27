package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Entity
@Table(name = "siifabsunimedida")
public class UnitMeasureEntity
    implements Comparable<UnitMeasureEntity>, Serializable{

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdUniMedida")
    private Long unitMeasureId;
    @Column(name = "Clave", length = 20, nullable = false)
    private String unitMeasureKey;
    @Column(name = "Descripcion", length = 150, nullable = false)
    private String unitMeasureDescription;

    @OneToMany(mappedBy = "unitmeasure")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<UnitMeasureEntity> unitmeasure;
    
    

    public String getUnitMeasureDescription() {
        return unitMeasureDescription != null ? unitMeasureDescription.trim():unitMeasureDescription;
    }

    public void setUnitMeasureDescription(String aUnitMeasureDescription) {
        this.unitMeasureDescription = aUnitMeasureDescription;
    }

    public String getUnitMeasureKey() {
        return unitMeasureKey != null ? unitMeasureKey.trim():unitMeasureKey;
    }

    public void setUnitMeasureKey(String aUnitMeasureKey) {
        this.unitMeasureKey = aUnitMeasureKey;
    }

    public Set<UnitMeasureEntity> getUnitmeasure() {
        return unitmeasure;
    }

    public void setUnitmeasure(Set<UnitMeasureEntity> aUnitmeasure) {
        this.unitmeasure = aUnitmeasure;
    }

    public Long getUnitMeasureId() {
        return unitMeasureId;
    }

    public void setUnitMeasureId(Long aUnitMeasureId) {
        this.unitMeasureId = aUnitMeasureId;
    }

    @Override
    public int compareTo(UnitMeasureEntity obj) {
        int result = -1;
        if (this.unitMeasureId != null && obj.getUnitMeasureId() != null) {
            result = this.unitMeasureId.compareTo(obj.getUnitMeasureId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj instanceof UnitMeasureEntity && this.unitMeasureId != null 
                && ((UnitMeasureEntity)obj).getUnitMeasureId() != null){
            result = this.unitMeasureId.equals(
                    ((UnitMeasureEntity)obj).getUnitMeasureId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.unitMeasureId != null ? this.unitMeasureId.hashCode() : 0);
        return hash;
    }

}
