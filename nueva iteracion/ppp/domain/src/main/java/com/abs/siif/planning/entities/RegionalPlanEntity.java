package com.abs.siif.planning.entities;

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
 * @author Miguel Baizabal Aguirre
 */

@Entity
@Table(name = "siifpppplanregional")
public class RegionalPlanEntity implements 
        Comparable<RegionalPlanEntity>, Serializable {
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdPlanRegional")
    private Long regionalPlanId;
    
    @ManyToOne
    @JoinColumn(name = "IdClasifRegional", nullable = true)
    private RegionalClassifierEntity regionalClassifierPlan;

    @Column(name = "numobjetivo")
    private int regionalObjectiveNumber;
    
    @Column(name = "Objetivo", length = 100, nullable = true)
    private String regionalPlanObjective;

    public RegionalClassifierEntity getRegionalClassifierPlan() {
        return regionalClassifierPlan;
    }

    public void setRegionalClassifierPlan(RegionalClassifierEntity 
            aRegionalClassifierPlan) {
        this.regionalClassifierPlan = aRegionalClassifierPlan;
    }
    
    public Long getRegionalPlanId() {
        return regionalPlanId;
    }

    public void setRegionalPlanId(Long aRegionalPlanId) {
        this.regionalPlanId = aRegionalPlanId;
    }

    public String getRegionalPlanObjective() {
        return regionalPlanObjective != null ? regionalPlanObjective.trim() : regionalPlanObjective;
    }

    public void setRegionalPlanObjective(String aRegionalPlanObjective) {
        this.regionalPlanObjective = aRegionalPlanObjective;
    }

    public int getRegionalObjectiveNumber() {
        return regionalObjectiveNumber;
    }

    public void setRegionalObjectiveNumber(int regionalObjectiveNumber) {
        this.regionalObjectiveNumber = regionalObjectiveNumber;
    }
    
    
    @Override
    public int compareTo(RegionalPlanEntity obj)
    {
        int result = -1;
        if (this.regionalPlanId != null && obj.regionalPlanId != null) {
        result = this.regionalPlanId.compareTo(obj.regionalPlanId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof RegionalPlanEntity && this.regionalPlanId != null 
                 && ((RegionalPlanEntity) obj).regionalPlanId != null) {
            result = this.regionalPlanId.equals(
                    ((RegionalPlanEntity) obj).regionalPlanId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + (this.regionalPlanId != null 
                ? this.regionalPlanId.hashCode() : 0);
        return hash;
    }
    
}
