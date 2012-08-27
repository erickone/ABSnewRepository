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

@Entity
@Table(name = "siifpppEstrategia")
public class StrategyEntity  implements 
        Comparable<StrategyEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdEstrategia")
    private Long strategyId;
    @ManyToOne
    @JoinColumn(name = "IdObjEspecifico", nullable = false)
    private SpecificObjectiveEntity specificObjective;
    @Column(name = "Descripcion", length = 150)
    private String strategyDescription;

    public Long getStrategyId() {
        return this.strategyId;
    }

    public SpecificObjectiveEntity getSpecificObjective() {
        return this.specificObjective;
    }

    public String getStrategyDescription() {
        return this.strategyDescription != null ? strategyDescription.trim() : strategyDescription;
    }

    public void setStrategyDescription(String aStrategyDescription) {
        this.strategyDescription = aStrategyDescription;
    }

    public void setSpecificObjective(SpecificObjectiveEntity aSpecificObjective) {
        this.specificObjective = aSpecificObjective;
    }

    public void setStrategyId(Long aStrategyId) {
        this.strategyId = aStrategyId;
    }

    @Override
    public int compareTo(StrategyEntity obj)
    {
        int result = -1;
        if (this.strategyId != null && obj.strategyId != null) {
        result = this.strategyId.compareTo(obj.strategyId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
         boolean result = false;
         if (obj instanceof StrategyEntity && this.strategyId != null
                 && ((StrategyEntity) obj).strategyId != null) {
            result = this.strategyId.equals(
                    ((StrategyEntity) obj).strategyId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 73 * hash + (this.strategyId != null 
                ? this.strategyId.hashCode() : 0);
        return hash;
    }
}
