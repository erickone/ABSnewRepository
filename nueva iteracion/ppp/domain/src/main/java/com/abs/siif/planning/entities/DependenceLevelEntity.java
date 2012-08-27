package com.abs.siif.planning.entities;

import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.common.entities.ColectiveTypeEntity;import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * MBA 20120613 : Se agregan los atributos Programacion y EncuadrePlanEst
 * 
 */

@Entity
@Table(name = "siifAbsNivDependencia")
public class DependenceLevelEntity  implements 
        Comparable<DependenceLevelEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdNivDependencia")
    private Long dependenceLevelId;
    

    
    
    @Column(name = "Clave", length= 20)
    private String dependenceLevelkey;
    @Column(name = "Nivel", unique = true, nullable = false)
    private short dependenceLevel;
    @Column(name = "Descripcion", length = 150)
    private String dependenceLevelDescription;
    @Column(name = "PlanInstitucional")
    private boolean dependencyLevelHasInstitutionalPlan;
    @OneToMany(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            mappedBy = "dependenceLevel")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<DependenceEntity> dependenceLevelChilds;
    @Column(name = "Ramo")
    private boolean dependencyLevelIsBranch;
    @Column(name = "Sector")
    private boolean dependencyLevelIsSector;
    @Column(name = "UniResponsable")
    private boolean dependencyLevelIsResponsibleUnit;
    @Column(name = "UniEjecutora")
    private boolean dependencyLevelIsExecutionUnit;
    @Column(name = "UniPresupuestal")
    private boolean dependencyLevelIsBudgetUnit;
    @Column(name="Presupuesta")
    private boolean dependencyLevelHasBudget;
    @Column(name="Programacion")
    private boolean dependencyLevelHasProgramming;    
    @Column(name="EncuadrePlanEst")
    private boolean dependencyLevelHasObjectiveFraming;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="iddefcvepresupuestal")
    private BudgetKeyDefinitionEntity budgetKeyDefinitionLevels;
    
    @ManyToOne
    @JoinColumn(name="idtipocolectiva")
    private ColectiveTypeEntity colectiveType;

    @Column(name="Encclasifadminconac")
    private boolean dependencyLevelHasClassifFraming;    
    @Column(name="SolicitudPago")
    private boolean dependencyLevelHasPaymetRequest;
    @Column(name="TechoPresupuestal")
    private boolean dependencyLevelHasBudgetCeiling;    
    @Column(name="proyDependencia")
    private boolean dependencyLevelHasProyDependence;    
    @Column(name="Requisicion")
    private boolean dependencyLevelHasRequisition;   
    @Column(name="Historico")
    private boolean dependencyLevelIsHistorical;    
    @Column(name="Inventario")
    private boolean dependencyLevelHasInventory;    
    @Column(name="Empleado")
    private boolean dependencyLevelHasEmployee;
    @Column(name="UniClasificadora")
    private boolean dependencyLevelHasClassifierUnit;

    public BudgetKeyDefinitionEntity getBudgetKeyDefinitionLevels() {
        return budgetKeyDefinitionLevels;
    }

    public void setBudgetKeyDefinitionLevels(BudgetKeyDefinitionEntity budgetKeyDefinitionLevels) {
        this.budgetKeyDefinitionLevels = budgetKeyDefinitionLevels;
    }

    public Set<DependenceEntity> getDependenceLevelChilds() {
        return dependenceLevelChilds;
    }
    
    
    
    
    public ColectiveTypeEntity getColectiveType() {
        return colectiveType;
    }

    public void setColectiveType(ColectiveTypeEntity colectiveType) {
        this.colectiveType = colectiveType;
    }
    
     

    public short getDependenceLevel() {
        return dependenceLevel;
    }

    public void setDependenceLevel(short dependenceLevel) {
        this.dependenceLevel = dependenceLevel;
    }

    public boolean isDependencyLevelHasInstitutionalPlan() {
        return dependencyLevelHasInstitutionalPlan;
    }

    public void setDependencyLevelHasInstitutionalPlan(boolean dependencyLevelHasInstitutionalPlan) {
        this.dependencyLevelHasInstitutionalPlan = dependencyLevelHasInstitutionalPlan;
    }

    public boolean isDependencyLevelHasObjectiveFraming() {
        return dependencyLevelHasObjectiveFraming;
    }

    public void setDependencyLevelHasObjectiveFraming(boolean dependencyLevelHasObjectiveFraming) {
        this.dependencyLevelHasObjectiveFraming = dependencyLevelHasObjectiveFraming;
    }

    public boolean isDependencyLevelHasProgramming() {
        return dependencyLevelHasProgramming;
    }

    public void setDependencyLevelHasProgramming(boolean dependencyLevelHasProgramming) {
        this.dependencyLevelHasProgramming = dependencyLevelHasProgramming;
    }
        
    public boolean isDependencyLevelHasBudget() {
        return dependencyLevelHasBudget;
    }

    public void setDependencyLevelHasBudget(boolean aDependencyLevelHasBudget) {
        this.dependencyLevelHasBudget = aDependencyLevelHasBudget;
    }     
    
    public DependenceLevelEntity() {
        this.dependenceLevelChilds = new HashSet<DependenceEntity>();
    }

    public boolean isDependencyLevelIsBranch() {
        return dependencyLevelIsBranch;
    }

    public void setDependencyLevelIsBranch(boolean aDependencyLevelIsBranch) {
        this.dependencyLevelIsBranch = aDependencyLevelIsBranch;
    }

    public boolean isDependencyLevelIsBudgetUnit() {
        return dependencyLevelIsBudgetUnit;
    }

    public void setDependencyLevelIsBudgetUnit(boolean 
            aDependencyLevelIsBudgetUnit) {
        this.dependencyLevelIsBudgetUnit = aDependencyLevelIsBudgetUnit;
    }

    public boolean isDependencyLevelIsExecutionUnit() {
        return dependencyLevelIsExecutionUnit;
    }

    public void setDependencyLevelIsExecutionUnit(boolean 
            aDependencyLevelIsExecutionUnit) {
        this.dependencyLevelIsExecutionUnit = aDependencyLevelIsExecutionUnit;
    }

    public boolean isDependencyLevelIsResponsibleUnit() {
        return dependencyLevelIsResponsibleUnit;
    }

    public void setDependencyLevelIsResponsibleUnit(boolean 
            aDependencyLevelIsResponsibleUnit) {
        this.dependencyLevelIsResponsibleUnit = 
                aDependencyLevelIsResponsibleUnit;
    }

    public boolean isDependencyLevelIsSector() {
        return dependencyLevelIsSector;
    }

    public void setDependencyLevelIsSector(boolean aDependencyLevelIsSector) {
        this.dependencyLevelIsSector = aDependencyLevelIsSector;
    }

    public String getDependenceLevelkey() {
        return dependenceLevelkey != null ? dependenceLevelkey.trim() : dependenceLevelkey;
    }

    public void setDependenceLevelkey(String aDependenceLevelkey) {
        this.dependenceLevelkey = aDependenceLevelkey;
    }

    public String getDependenceLevelDescription() {
        return dependenceLevelDescription != null ? dependenceLevelDescription.trim() : dependenceLevelDescription;
    }

    public void setDependenceLevelDescription(String 
            aDependenceLevelDescription) {
        this.dependenceLevelDescription = aDependenceLevelDescription;
    }

    public boolean getInstitutionalPlan() {
        return dependencyLevelHasInstitutionalPlan;
    }

    public void setInstitutionalPlan(boolean 
            aDependencyLevelHasInstitutionalPlan) {
        this.dependencyLevelHasInstitutionalPlan = 
                aDependencyLevelHasInstitutionalPlan;
    }

    public Set<DependenceEntity> getDependencias() {
        return dependenceLevelChilds;
    }

    public void addDependence(DependenceEntity aDependence) {
        this.dependenceLevelChilds.add(aDependence);
        aDependence.setDependenceLevel(this);
    }

    public Long getDependenceLevelId() {
        return dependenceLevelId;
    }
    
    public boolean isDependencyLevelHasBudgetCeiling() {
        return dependencyLevelHasBudgetCeiling;
    }

    public void setDependencyLevelHasBudgetCeiling(boolean dependencyLevelHasBudgetCeiling) {
        this.dependencyLevelHasBudgetCeiling = dependencyLevelHasBudgetCeiling;
    }

    public boolean isDependencyLevelHasClassifFraming() {
        return dependencyLevelHasClassifFraming;
    }

    public void setDependencyLevelHasClassifFraming(boolean dependencyLevelHasClassifFraming) {
        this.dependencyLevelHasClassifFraming = dependencyLevelHasClassifFraming;
    }

    public boolean isDependencyLevelHasClassifierUnit() {
        return dependencyLevelHasClassifierUnit;
    }

    public void setDependencyLevelHasClassifierUnit(boolean dependencyLevelHasClassifierUnit) {
        this.dependencyLevelHasClassifierUnit = dependencyLevelHasClassifierUnit;
    }

    public boolean isDependencyLevelHasEmployee() {
        return dependencyLevelHasEmployee;
    }

    public void setDependencyLevelHasEmployee(boolean dependencyLevelHasEmployee) {
        this.dependencyLevelHasEmployee = dependencyLevelHasEmployee;
    }

    public boolean isDependencyLevelHasInventory() {
        return dependencyLevelHasInventory;
    }

    public void setDependencyLevelHasInventory(boolean dependencyLevelHasInventory) {
        this.dependencyLevelHasInventory = dependencyLevelHasInventory;
    }

    public boolean isDependencyLevelHasPaymetRequest() {
        return dependencyLevelHasPaymetRequest;
    }

    public void setDependencyLevelHasPaymetRequest(boolean dependencyLevelHasPaymetRequest) {
        this.dependencyLevelHasPaymetRequest = dependencyLevelHasPaymetRequest;
    }

    public boolean isDependencyLevelHasProyDependence() {
        return dependencyLevelHasProyDependence;
    }

    public void setDependencyLevelHasProyDependence(boolean dependencyLevelHasProyDependence) {
        this.dependencyLevelHasProyDependence = dependencyLevelHasProyDependence;
    }

    public boolean isDependencyLevelHasRequisition() {
        return dependencyLevelHasRequisition;
    }

    public void setDependencyLevelHasRequisition(boolean dependencyLevelHasRequisition) {
        this.dependencyLevelHasRequisition = dependencyLevelHasRequisition;
    }

    public boolean isDependencyLevelIsHistorical() {
        return dependencyLevelIsHistorical;
    }

    public void setDependencyLevelIsHistorical(boolean dependencyLevelIsHistorical) {
        this.dependencyLevelIsHistorical = dependencyLevelIsHistorical;
    }
           
    public void setDependenceLevelId(Long dependenceLevelId) {
        this.dependenceLevelId = dependenceLevelId;
    }

    
    
    @Override
    public int compareTo(DependenceLevelEntity obj)
    {
        int result = -1;
        if (this.dependenceLevelId != null && obj.dependenceLevelId != null) {
        result = this.dependenceLevelId.compareTo(obj.dependenceLevelId);
        }
        return result;
    }
    
     @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof DependenceLevelEntity 
                && this.dependenceLevelId != null 
                && ((DependenceLevelEntity) obj).dependenceLevelId != null) {
            result = this.dependenceLevelId.equals(
                    ((DependenceLevelEntity) obj).dependenceLevelId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + (this.dependenceLevelId != null ? 
                this.dependenceLevelId.hashCode() : 0);
        hash = 37 * hash + (this.dependenceLevelkey != null ? 
                this.dependenceLevelkey.hashCode() : 0);
        return hash;
    }
 
    
}
