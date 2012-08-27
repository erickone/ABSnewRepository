package com.abs.siif.planning.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * MBA 20120613 : Se cambia de nombre el atributo EncuadreUEG por EncuadreClasifAdmin
 * 
 */

@Entity
@Table(name = "siifpppNivelObjetivo")
public class ObjectiveLevelEntity  implements 
        Serializable, Comparable<ObjectiveLevelEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdNivelObjetivo")
    private Long objectiveLevelId;
    @Column(name = "EncuadreClasifAdmin")
    private boolean objectiveLevelShowBudgetKey;
    @Column(name = "Clave", unique = true, nullable = false, length = 20)
    private String objectiveLevelKey;
    @Column(name = "Descripcion", length = 150)
    private String objectiveLevelDescription;
    @Column(name = "Nivel", unique = true, nullable = false)
    private short objectiveLevel;
    @Column(name = "CapIndicador")
    private boolean objectiveLevelHasIndicator;
    
    @Column(name="CapClasifFuncional")
    private boolean objectiveLevelHasFunctionalClassifier;

    @Column(name="CapProgramacion")
    private boolean objectiveLevelHasProgramming;
    
    
    @OneToMany(mappedBy = "objectiveLevel")
    private List<ObjectiveEntity> objectives = new ArrayList<ObjectiveEntity>();

    @ManyToOne
    @JoinColumn(name = "IdNivClasifFuncional", nullable = true)
    private FunctionalLevelClassifier functionalClassifier;

    public FunctionalLevelClassifier getFunctionalClassifier() {
        return functionalClassifier;
    }

    public void setFunctionalClassifier(FunctionalLevelClassifier 
            aFunctionalClassifier) {
        this.functionalClassifier = aFunctionalClassifier;
    }
     
     
    public boolean isObjectiveLevelHasFunctionalClassifier() {
        return objectiveLevelHasFunctionalClassifier;
    }

    public void setObjectiveLevelHasFunctionalClassifier(boolean 
            anObjectiveLevelHasFunctionalClassifier) {
        this.objectiveLevelHasFunctionalClassifier = 
                anObjectiveLevelHasFunctionalClassifier;
    }
     
    
    public short getObjectiveLevel() {
        return objectiveLevel;
    }

    public void setObjectiveLevel(short anObjectiveLevel) {
        this.objectiveLevel = anObjectiveLevel;
    }

    public String getObjectiveLevelDescription() {
        return objectiveLevelDescription != null ? objectiveLevelDescription.trim() : objectiveLevelDescription;
    }

    public void setObjectiveLevelDescription(String anObjectiveLevelDescription) {
        this.objectiveLevelDescription = anObjectiveLevelDescription;
    }

    public boolean getObjectiveLevelHasIndicator() {
        return objectiveLevelHasIndicator;
    }

    public void setObjectiveLevelHasIndicator(boolean anHasObjectiveLevelHasIndicator) {
        this.objectiveLevelHasIndicator = anHasObjectiveLevelHasIndicator;
    }

    public String getObjectiveLevelKey() {
        return objectiveLevelKey != null ? objectiveLevelKey.trim() : objectiveLevelKey;
    }

    public void setObjectiveLevelKey(String anObjectiveLevelKey) {
        this.objectiveLevelKey = anObjectiveLevelKey;
    }

    public boolean getObjectiveLevelShowBudgetKey() {
        return this.objectiveLevelShowBudgetKey;
    }

    public void setObjectiveLevelShowBudgetKey(boolean anHasObjectiveLevelShowBudgetKey) {
        this.objectiveLevelShowBudgetKey = anHasObjectiveLevelShowBudgetKey;
    }

    public Long getObjectiveLevelId() {
        return this.objectiveLevelId;
    }

    public void setObjectiveLevelId(Long anObjectiveLevelId) {
        this.objectiveLevelId = anObjectiveLevelId;
    }

    public boolean isObjectiveLevelHasProgramming() {
        return objectiveLevelHasProgramming;
    }

    public void setObjectiveLevelHasProgramming(boolean anObjectiveLevelHasProgramming) {
        this.objectiveLevelHasProgramming = anObjectiveLevelHasProgramming;
    }
    
    public void assignObjective(Object anObjectiveEntity) {
        this.objectives.add((ObjectiveEntity) anObjectiveEntity);
    }
    
    public void AddObjective(ObjectiveEntity anObjective) {
        this.objectives.add(anObjective);
        anObjective.setObjectiveLevel(this);
    }

    public List<ObjectiveEntity> getObjectives() {
        return Collections.unmodifiableList(this.objectives);
    }
    
    @Override
    public int compareTo(ObjectiveLevelEntity anEntity) {
        int result = -1;
        if (this.objectiveLevelId != null && anEntity.objectiveLevelId != null) {
        result = this.objectiveLevelId.compareTo(anEntity.objectiveLevelId);
        }
        return result;
    }
        
    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if (obj instanceof ObjectiveLevelEntity && this.objectiveLevelId != null 
                && ((ObjectiveLevelEntity) obj).objectiveLevelId != null) {
            result = this.objectiveLevelId.equals(
                    ((ObjectiveLevelEntity) obj).objectiveLevelId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 71 * hash + (this.objectiveLevelId != null 
                ? this.objectiveLevelId.hashCode() : 0);
        hash = 71 * hash + (this.objectiveLevelKey != null 
                ? this.objectiveLevelKey.hashCode() : 0);
        return hash;
    }
}
