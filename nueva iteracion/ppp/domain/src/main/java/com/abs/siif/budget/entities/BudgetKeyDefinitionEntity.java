package com.abs.siif.budget.entities;

import com.abs.siif.common.entities.EmployeeEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Luis Agustin Carreón
 */
@Entity
@Table(name = "siifpppDefCvePresupuestal")
public class BudgetKeyDefinitionEntity implements
        Serializable, Comparable<BudgetKeyDefinitionEntity> {
   
   
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdDefCvePresupuestal")
    private Long presupuestalKeyDefinitionId;

    @Column(name="descripcion", length = 150)
    String presupuestalKeyDefinitionDescription;

    @Column(name="Mascara", length = 100)
    String presupuestalKeyDefinitionMask;

    @Column(name="Anio")
    int presupuestalKeyDefinitionYear;

    @ManyToOne
    @JoinColumn(name = "IdDefcveestado", nullable = false)
    private BudgetKeyDefinitionStatusEntity definitionKeyStatusEntity;

    @OneToMany(mappedBy = "budgetKeyDefinitionBudgetKey")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE}) private
    Set<BudgetKeyEntity> budgetKeyDefinitionBudgetKey;
    
    
    @OneToMany(mappedBy = "budgetKeyDefinitionDependency")
    private Set<DependenceEntity> dependencies;
    
     @OneToMany(mappedBy = "budgetKeyDefinitionEntityEmployee",fetch= FetchType.LAZY)
    private List<EmployeeEntity> employees;

      @OneToMany(mappedBy = "budgetKeyDefinitionLevels")
    private List<DependenceLevelEntity> dependencyLevels;
      
      
 

    public Set<BudgetKeyEntity> getBudgetKeyDefinitionBudgetKey() {
        return budgetKeyDefinitionBudgetKey;
    }
       
       

    public List<DependenceLevelEntity> getDependencyLevels() {
        return dependencyLevels;
    }
       
     
    public List<EmployeeEntity> getEmployees() {
        return employees;
    }
     
     
     

    public Set<DependenceEntity> getDependencies() {
        return dependencies;
    }
    
    

    public BudgetKeyDefinitionStatusEntity getDefinitionKeyStatusEntity() {
        return definitionKeyStatusEntity;
    }

    public void setDefinitionKeyStatusEntity(BudgetKeyDefinitionStatusEntity definitionKeyStatusEntity) {
        this.definitionKeyStatusEntity = definitionKeyStatusEntity;
    }

    public String getPresupuestalKeyDefinitionDescription() {
        return presupuestalKeyDefinitionDescription != null ? presupuestalKeyDefinitionDescription.trim() :
               presupuestalKeyDefinitionDescription;
    }

    public void setPresupuestalKeyDefinitionDescription(String presupuestalKeyDefinitionDescription) {
        this.presupuestalKeyDefinitionDescription = presupuestalKeyDefinitionDescription;
    }

    public Long getPresupuestalKeyDefinitionId() {
        return presupuestalKeyDefinitionId;
    }

    public void setPresupuestalKeyDefinitionId(Long presupuestalKeyDefinitionId) {
        this.presupuestalKeyDefinitionId = presupuestalKeyDefinitionId;
    }

    public String getPresupuestalKeyDefinitionMask() {
        return presupuestalKeyDefinitionMask != null ? presupuestalKeyDefinitionMask :
               presupuestalKeyDefinitionMask;
    }

    public void setPresupuestalKeyDefinitionMask(String presupuestalKeyDefinitionMask) {
        this.presupuestalKeyDefinitionMask = presupuestalKeyDefinitionMask;
    }

    public int getPresupuestalKeyDefinitionYear() {
        return presupuestalKeyDefinitionYear;
    }

    public void setPresupuestalKeyDefinitionYear(int presupuestalKeyDefinitionYear) {
        this.presupuestalKeyDefinitionYear = presupuestalKeyDefinitionYear;
    }


    @Override
    public int compareTo(BudgetKeyDefinitionEntity anOtherEntity) {
        return this.presupuestalKeyDefinitionId.compareTo(
                anOtherEntity.presupuestalKeyDefinitionId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DestinationEntity) {
            return this.presupuestalKeyDefinitionId.equals(
                    ((BudgetKeyDefinitionEntity) obj).presupuestalKeyDefinitionId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.presupuestalKeyDefinitionId != null
                ? this.presupuestalKeyDefinitionId.hashCode() : 0);
        return hash;
    }

}
