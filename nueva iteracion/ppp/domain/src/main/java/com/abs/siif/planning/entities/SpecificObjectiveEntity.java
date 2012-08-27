package com.abs.siif.planning.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "siifpppObjEspecifico")
public class SpecificObjectiveEntity  implements 
        Comparable<SpecificObjectiveEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdObjEspecifico", nullable = false)
    private Long specificObjectiveId;
    @Column(name = "Descripcion", length = 150)
    private String specificObjectiveDescription;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "specificObjective", orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<StrategyEntity> strategies;
    @ManyToOne
    @JoinColumn(name = "IdObjetivo", nullable = false)
    private ObjectiveEntity objective;

    public SpecificObjectiveEntity() {
        this.objective = new ObjectiveEntity();
        this.strategies = new HashSet<StrategyEntity>();
    }

    public Long getSpecificObjectiveId() {
        return this.specificObjectiveId;
    }

    public void setSpecificObjectiveDescription(String aSpecificObjectiveDescription) {
        this.specificObjectiveDescription = aSpecificObjectiveDescription;
    }

    public String getSpecificObjectiveDescription() {
        return this.specificObjectiveDescription != null ? specificObjectiveDescription.trim() : specificObjectiveDescription;
    }

    public ObjectiveEntity getObjective() {
        return this.objective;
    }

    public Set<StrategyEntity> getStrategies() {
        return Collections.unmodifiableSet(this.strategies);
    }

    protected void setObjective(ObjectiveEntity anObjective) {
        this.objective = anObjective;
    }

    public void AddStrategy(StrategyEntity aStrategy) {
        this.strategies.add(aStrategy);
        aStrategy.setSpecificObjective(this);
    }

    public void setSpecificObjectiveId(Long aSpecificId) {
        this.specificObjectiveId = aSpecificId;
    }

    @Override
    public int compareTo(SpecificObjectiveEntity obj)
    {
        int result = -1;
        if (this.specificObjectiveId != null 
                && obj.specificObjectiveId != null) {
        result = this.specificObjectiveId.compareTo(obj.specificObjectiveId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof SpecificObjectiveEntity 
                 && this.specificObjectiveId != null 
                 && ((SpecificObjectiveEntity) obj).specificObjectiveId != null) {
            result = this.specificObjectiveId.equals(
                    ((SpecificObjectiveEntity) obj).specificObjectiveId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + (this.specificObjectiveId != null 
                ? this.specificObjectiveId.hashCode() : 0);
        return hash;
    }
}
