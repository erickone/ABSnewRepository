package com.abs.siif.planning.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * MBA 20120613 : Se quita la relación ManyToMany entre InstitutionalPlanEntity
 * y ObjectiveEntity por DependenceEntity y ObjectiveEntity
 *
 */
@Entity
@Table(name = "siifpppObjetivo")
public class ObjectiveEntity implements
        Comparable<ObjectiveEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdObjetivo")
    private Long objectiveId;
    @Column(name = "Nombre", length = 500)
    private String objectiveName;
    @Column(name = "clave", length = 20)
    private String objectiveKey;
    @Column(name = "Definicion", length = 500)
    private String objectiveDefinition;
    @Column(name = "Prioridad")
    private short objectivePriority;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objectiveFather", fetch = FetchType.LAZY)
    List<ObjectiveEntity> objectiveChilds;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNivelObjetivo", nullable = false)
    private ObjectiveLevelEntity objectiveLevel;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "objective",
    orphanRemoval = true, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<SpecificObjectiveEntity> specificObjectives;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "objective",
    orphanRemoval = true, fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<ProblemEntity> problems;
    @OneToMany(mappedBy = "objective", fetch = FetchType.LAZY)
    private Collection<ObjectiveIndicatorEntity> objetiveIndicators;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPadre", nullable = true)
    private ObjectiveEntity objectiveFather;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "siifpppencclasifadminobj", joinColumns = {
        @JoinColumn(name = "IdObjetivo", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdDependencia",
        nullable = false, updatable = false)})
    private List<DependenceEntity> dependences = new ArrayList<DependenceEntity>();

    public List<DependenceEntity> getDependences() {
        return dependences;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SIIFPPPEncObjClasifFunc", joinColumns = {
        @JoinColumn(name = "IdObjetivo", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdClasifFuncional",
        nullable = false, updatable = false)})
    private Set<FunctionalClassifierEntity> objectiveFunctionalClassifiers;

    public Set<FunctionalClassifierEntity> getObjectiveFunctionalClassifiers() {
        return objectiveFunctionalClassifiers;
    }

    public void setObjectiveFunctionalClassifiers(Set<FunctionalClassifierEntity> objectiveFunctionalClassifiers) {
        this.objectiveFunctionalClassifiers = objectiveFunctionalClassifiers;
    }

    public ObjectiveEntity() {

        this.objectiveChilds = new ArrayList<ObjectiveEntity>();
        this.specificObjectives = new HashSet<SpecificObjectiveEntity>();
        this.problems = new HashSet<ProblemEntity>();
    }

    public String getObjectiveKey() {
        return objectiveKey != null ? objectiveKey.trim() : objectiveKey;
    }

    public void setObjectiveKey(String objectiveKey) {
        this.objectiveKey = objectiveKey;
    }

    public Long getObjectiveId() {
        return this.objectiveId;
    }

    public void setObjectiveId(Long anObjectiveId) {
        this.objectiveId = anObjectiveId;
    }

    public String getObjectiveName() {
        return this.objectiveName != null ? objectiveName.trim() : objectiveName;
    }

    public void setObjectiveName(String anObjectiveName) {
        this.objectiveName = anObjectiveName;
    }

    public String getObjectiveDefinition() {
        return this.objectiveDefinition != null ? objectiveDefinition.trim() : objectiveDefinition;
    }

    public void setObjectiveDefinition(String anObjectiveDefinition) {
        this.objectiveDefinition = anObjectiveDefinition;
    }

    public short getObjectivePriority() {
        return this.objectivePriority;
    }

    public void setObjectivePriority(short anObjectivePriority) {
        this.objectivePriority = anObjectivePriority;
    }

    public void setStrategicPlans(List<ObjectiveEntity> anObjectiveChilds) {
        this.objectiveChilds = anObjectiveChilds;
    }

    public void AddChild(ObjectiveEntity anObjective) {
        this.objectiveChilds.add(anObjective);
        anObjective.objectiveFather = this;
    }

    protected void setObjectiveLevel(ObjectiveLevelEntity anObjectiveLevel) {
        this.objectiveLevel = anObjectiveLevel;
    }

    public void AssignObjectiveLevel(ObjectiveLevelEntity anObjectiveLevel) {
        this.objectiveLevel = anObjectiveLevel;
        anObjectiveLevel.AddObjective(this);
    }

    public ObjectiveEntity getFather() {
        return objectiveFather;
    }

    public ObjectiveLevelEntity getObjectiveLevel() {
        return objectiveLevel;
    }

    public List<ObjectiveEntity> getObjectiveChilds() {
        //return Collections.unmodifiableList(this.objectiveChilds);
        return this.objectiveChilds;
    }

    public void AssignFather(ObjectiveEntity anObjectiveFather) {
        this.objectiveFather = anObjectiveFather;
        if (anObjectiveFather != null) {
            anObjectiveFather.AddChild(this);
        }
    }

    public void AddProblem(ProblemEntity aProblem) {
        this.problems.add(aProblem);
        aProblem.setObjective(this);
    }

    public Set<ProblemEntity> getProblems() {
        return Collections.unmodifiableSet(this.problems);
    }

    public void AddSpecificObjective(SpecificObjectiveEntity aSpecificObjective) {
        this.specificObjectives.add(aSpecificObjective);
        aSpecificObjective.setObjective(this);
    }

    public Set<SpecificObjectiveEntity> getSpecificObjectives() {
        return Collections.unmodifiableSet(this.specificObjectives);
    }

    public Collection<ObjectiveIndicatorEntity> getObjetiveIndicators() {
        return objetiveIndicators;
    }

    public void setObjetiveIndicators(Collection<ObjectiveIndicatorEntity> objetiveIndicators) {
        this.objetiveIndicators = objetiveIndicators;
    }

    @Override
    public int compareTo(ObjectiveEntity obj) {
        int result = -1;
        if (this.objectiveId != null && obj.objectiveId != null) {
            result = this.objectiveId.compareTo(obj.objectiveId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof ObjectiveEntity && this.objectiveId != null
                && ((ObjectiveEntity) obj).objectiveId != null) {
            result = this.objectiveId.equals(
                    ((ObjectiveEntity) obj).objectiveId);
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.objectiveId != null
                ? this.objectiveId.hashCode() : 0);
        return hash;
    }

    /**
     * @param dependences the dependences to set
     */
    public void setDependences(List<DependenceEntity> dependences) {
        this.dependences = dependences;
    }

    //TODO:
    public String getCompositeObjectiveKey() {
        String myCompositeKey = "";
        if (this.objectiveKey != null) {
            myCompositeKey = this.objectiveKey + " " + this.objectiveName;

        } else {
            myCompositeKey = this.objectiveName;
        }
        return myCompositeKey;
    }
}
