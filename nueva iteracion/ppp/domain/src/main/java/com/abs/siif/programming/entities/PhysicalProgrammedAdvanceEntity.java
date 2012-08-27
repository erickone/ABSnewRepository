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
 * Miguel Baizabal Aguirre
 */


@Entity
@Table(name = "siifpppavancefisprog")
public class PhysicalProgrammedAdvanceEntity 
        implements Serializable, Comparable<PhysicalProgrammedAdvanceEntity>
{

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdAvanceFisProg", nullable = false)
    private Long physicalProgrammedAdvanceId;

    // Identificador de la PreFicha
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdPreFicha", nullable = false)
    private InvPreFileEntity physicalProgrammedAdvancePreFile;
  
    @Column(name = "Porcentaje", nullable = false)
    private double physicalProgrammedAdvancePercent;

    @Column(name = "Mes", nullable = false)
    private int physicalProgrammedAdvanceMonth;

    public Long getPhysicalProgrammedAdvanceId() {
        return physicalProgrammedAdvanceId;
    }

    public void setPhysicalProgrammedAdvanceId(Long physicalProgrammedAdvanceId) {
        this.physicalProgrammedAdvanceId = physicalProgrammedAdvanceId;
    }

    public double getPhysicalProgrammedAdvancePercent() {
        return physicalProgrammedAdvancePercent;
    }

    public void setPhysicalProgrammedAdvancePercent(double physicalProgrammedAdvancePercent) {
        this.physicalProgrammedAdvancePercent = physicalProgrammedAdvancePercent;
    }

    public InvPreFileEntity getPhysicalProgrammedAdvancePreFile() {
        return physicalProgrammedAdvancePreFile;
    }

    public void setPhysicalProgrammedAdvancePreFile(InvPreFileEntity physicalProgrammedAdvancePreFile) {
        this.physicalProgrammedAdvancePreFile = physicalProgrammedAdvancePreFile;
    }

    public int getPhysicalProgrammedAdvanceMonth() {
        return physicalProgrammedAdvanceMonth;
    }

    public void setPhysicalProgrammedAdvanceMonth(int physicalProgrammedAdvanceMonth) {
        this.physicalProgrammedAdvanceMonth = physicalProgrammedAdvanceMonth;
    }    

    @Override
    public int compareTo(PhysicalProgrammedAdvanceEntity obj) {
        int result = -1;
        if (this.physicalProgrammedAdvanceId != null && obj.physicalProgrammedAdvanceId != null) {
            result = this.physicalProgrammedAdvanceId.compareTo(obj.physicalProgrammedAdvanceId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof PhysicalProgrammedAdvanceEntity
                && this.physicalProgrammedAdvanceId != null
                && ((PhysicalProgrammedAdvanceEntity) obj).physicalProgrammedAdvanceId != null) {

            result = this.physicalProgrammedAdvanceId.equals(
                    ((PhysicalProgrammedAdvanceEntity) obj).physicalProgrammedAdvanceId);

        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.physicalProgrammedAdvanceId != null
                ? this.physicalProgrammedAdvanceId.hashCode() : 0);
        return hash;
    }

}
