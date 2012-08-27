package com.abs.siif.planning.entities;

import com.abs.siif.budget.entities.BudgetKeyAdditionalEntity;
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.budget.entities.BudgetingCeilingEntity;
import com.abs.siif.common.entities.ColectiveEntity;
import com.abs.siif.common.entities.EmployeeEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 *
 * MBA 20120613 : Se agrega la relación ManyToMany entre DependenceEntity y
 * ObjectiveEntity Se quita el atributo mapeado hacia iddependenciacapplaninst y
 * sus propiedades get y set
 *
 */
@Entity
@Table(name = "siifAbsDependencia")
public class DependenceEntity implements
        Comparable<DependenceEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdDependencia")
    private Long dependenceId;
    @Column(name = "Clave", length = 20)
    private String dependenceKey;
    @Column(name = "nombrecorto",length=50)
    private String dependencyShortName;
    @Column(name = "fundamento")
    private String dependencyFundamental;
    @Column(name = "ClaveInterna", length = 20)
    private String dependenceHidden;
    @Column(name = "Descripcion", length = 150)
    private String dependenceDescription;
    @Column(name = "DescLarga", length = 500)
    private String dependencyDescLong;
    
    @OneToMany(mappedBy = "father")
    private Set<DependenceEntity> dependenceChilds;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dependence")
    private Set<InstitutionalPlanEntity> institutionalPlanChilds;
   
    @ManyToOne
    @JoinColumn(name = "IdNivDependencia")
    private DependenceLevelEntity dependenceLevel;
   
    @ManyToOne
    @JoinColumn(name = "IdPadre", nullable = true)
    private DependenceEntity father;
   
    @OneToMany(mappedBy = "draftProjectDependency")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<DraftProjectEntity> draftProjects;

    @OneToMany(mappedBy = "dependence")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<BudgetingCeilingEntity> budgetingCeilings;
    
    @ManyToMany(mappedBy="dependences")
//    @JoinTable(name = "siifpppencclasifadminobj", joinColumns = {
//        @JoinColumn(name = "IdDependencia", nullable = false, updatable = false)},
//    inverseJoinColumns = {
//        @JoinColumn(name = "IdObjetivo",
//        nullable = false, updatable = false)})
    private List<ObjectiveEntity> objectives;
    /**
     * Unidades ejecutora del gasto puede ser una o varias
     */
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "siifpppprefichadep", joinColumns = {
//        @JoinColumn(name = "IdDependencia", nullable = false, updatable = false)
//    },
//    inverseJoinColumns = {
//        @JoinColumn(name = "IdPreficha",
//        nullable = false, updatable = false)
//    })
    @ManyToMany(fetch = FetchType.LAZY,mappedBy="unitExecSpending")
    private List<InvPreFileEntity> invPreFileDependence;
    
    
    @OneToMany(mappedBy = "bugKeyAdditionalDependency",fetch= FetchType.LAZY)
    private List<BudgetKeyAdditionalEntity> budgetKeyAdditionals;

    public List<BudgetKeyAdditionalEntity> getBudgetKeyAdditionals() {
        return budgetKeyAdditionals;
    }

    public void setBudgetKeyAdditionals(List<BudgetKeyAdditionalEntity> budgetKeyAdditionals) {
        this.budgetKeyAdditionals = budgetKeyAdditionals;
    }

    public Set<BudgetingCeilingEntity> getBudgetingCeilings() {
        return budgetingCeilings;
    }

    public void setBudgetingCeilings(Set<BudgetingCeilingEntity> budgetingCeilings) {
        this.budgetingCeilings = budgetingCeilings;
    }
    
    

    public String getDependencyDescLong() {
        return dependencyDescLong;
    }

    public void setDependencyDescLong(String dependencyDescLong) {
        this.dependencyDescLong = dependencyDescLong;
    }
    /**
     * Elementos agregados con el manejo del clasificador administrativo
     */
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdTipoClasifDep", nullable = true)
    private AdministrativeClassifierTypeEntity administrativeClassifierType;
   
    @OneToOne(fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "idcolectiva", nullable = true)  
    private ColectiveEntity colective;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddefcvepresupuestal")   
    private BudgetKeyDefinitionEntity budgetKeyDefinitionDependency;
    
    @OneToMany(mappedBy = "dependencyEmployee", fetch = FetchType.LAZY)
    private List<EmployeeEntity> employees;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "idempleado",nullable=true)    
    private EmployeeEntity responsible;
    
    @Column(name = "ejecutora")
    private boolean dependencyIsExecute;
    
    @Column(name = "normativa")
    private boolean dependencyIsNormative;

    public BudgetKeyDefinitionEntity getBudgetKeyDefinitionDependency() {
        return budgetKeyDefinitionDependency;
    }

    public void setBudgetKeyDefinitionDependency(BudgetKeyDefinitionEntity budgetKeyDefinitionDependency) {
        this.budgetKeyDefinitionDependency = budgetKeyDefinitionDependency;
    }
    
    

    public boolean isDependencyIsExecute() {
        return dependencyIsExecute;
    }

    public void setDependencyIsExecute(boolean dependencyIsExecute) {
        this.dependencyIsExecute = dependencyIsExecute;
    }

    public boolean isDependencyIsNormative() {
        return dependencyIsNormative;
    }

    public void setDependencyIsNormative(boolean dependencyIsNormative) {
        this.dependencyIsNormative = dependencyIsNormative;
    }

    public String getDependencyFundamental() {
        return dependencyFundamental;
    }

    public void setDependencyFundamental(String dependencyFundamental) {
        this.dependencyFundamental = dependencyFundamental;
    }

    public String getDependencyShortName() {
        return dependencyShortName;
    }

    public void setDependencyShortName(String dependencyShortName) {
        this.dependencyShortName = dependencyShortName;
    }

    public EmployeeEntity getResponsible() {
        return responsible;
    }

    public void setResponsible(EmployeeEntity responsible) {
        this.responsible = responsible;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    

    public ColectiveEntity getColective() {
        return colective;
    }

    public void setColective(ColectiveEntity colective) {
        this.colective = colective;
    }

    public AdministrativeClassifierTypeEntity getAdministrativeClassifierType() {
        return administrativeClassifierType;
    }

    public void setAdministrativeClassifierType(AdministrativeClassifierTypeEntity administrativeClassifierType) {
        this.administrativeClassifierType = administrativeClassifierType;
    }

    public String getDependenceHidden() {
        return dependenceHidden != null ? dependenceHidden.trim() : dependenceHidden;
    }

    public void setDependenceHidden(String dependenceHidden) {
        this.dependenceHidden = dependenceHidden;
    }

    public List<InvPreFileEntity> getInvPreFileDependence() {
        return invPreFileDependence;
    }

    public void setInvPreFileDependence(List<InvPreFileEntity> invPreFileDependence) {
        this.invPreFileDependence = invPreFileDependence;
    }

    public List<ObjectiveEntity> getObjectives() {
        return objectives;
    }

    public Set<DraftProjectEntity> getDraftProjects() {
        return draftProjects;
    }

    public DependenceEntity() {
        this.dependenceChilds = new HashSet<DependenceEntity>();
        this.institutionalPlanChilds = new HashSet<InstitutionalPlanEntity>();
    }

    public Long getDependenceId() {
        return dependenceId;
    }

    public void setDependenceId(Long aDependenceId) {
        this.dependenceId = aDependenceId;
    }

    public Set<DependenceEntity> getDependenceChilds() {
        return this.dependenceChilds;
    }

    public Set<InstitutionalPlanEntity> getInstitutionalPlans() {
        return this.getInstitutionalPlanChilds();
    }

    public String getDependenceDescription() {
        return dependenceDescription != null ? dependenceDescription.trim() : dependenceDescription;
    }

    public void setDependenceDescription(String aDependenceDescription) {
        this.dependenceDescription = aDependenceDescription;
    }

    public String getDependenceKey() {
        return dependenceKey != null ? dependenceKey.trim() : dependenceKey;
    }

    public void setDependenceKey(String aDependenceKey) {
        this.dependenceKey = aDependenceKey;
    }

    public DependenceLevelEntity getDependenceLevel() {
        return dependenceLevel;
    }

    public void setDependenceLevel(DependenceLevelEntity aDependenceLevel) {
        this.dependenceLevel = aDependenceLevel;
    }

    public DependenceEntity getFather() {
        return father;
    }

    public void setFather(DependenceEntity aDependence) {
        this.father = aDependence;
    }

    public void addChild(DependenceEntity aDependence) {
        this.getDependenceChilds().add(aDependence);
        aDependence.setFather(this);
    }

    public void AddInstitutionalPlans(InstitutionalPlanEntity aInstitutionaPlan) {
        this.getInstitutionalPlanChilds().add(aInstitutionaPlan);
        aInstitutionaPlan.setDependence(this);
    }

    public void AssignFather(DependenceEntity aDependenceFather) {
        this.setFather(aDependenceFather);
        if (aDependenceFather != null) {
            aDependenceFather.addChild(this);
        }
    }

    public void AssignDependenceLevel(DependenceLevelEntity aDependenceLevel) {
        this.setDependenceLevel(aDependenceLevel);
        aDependenceLevel.addDependence(this);
    }

    /**
     * @param dependenceChilds the dependenceChilds to set
     */
    public void setDependenceChilds(Set<DependenceEntity> dependenceChilds) {
        this.dependenceChilds = dependenceChilds;
    }

    /**
     * @return the institutionalPlanChilds
     */
    public Set<InstitutionalPlanEntity> getInstitutionalPlanChilds() {
        return institutionalPlanChilds;
    }

    /**
     * @param institutionalPlanChilds the institutionalPlanChilds to set
     */
    public void setInstitutionalPlanChilds(Set<InstitutionalPlanEntity> institutionalPlanChilds) {
        this.institutionalPlanChilds = institutionalPlanChilds;
    }

    /**
     * @param draftProjects the draftProjects to set
     */
    public void setDraftProjects(Set<DraftProjectEntity> draftProjects) {
        this.draftProjects = draftProjects;
    }

    @Override
    public int compareTo(DependenceEntity obj) {
        int result = -1;
        if (this.getDependenceId() != null && obj.getDependenceId() != null) {
            result = this.getDependenceId().compareTo(obj.getDependenceId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof DependenceEntity && this.getDependenceId() != null
                && ((DependenceEntity) obj).getDependenceId() != null) {
            result = this.getDependenceId().equals(
                    ((DependenceEntity) obj).getDependenceId());
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.getDependenceId() != null
                ? this.getDependenceId().hashCode() : 0);
        hash = 37 * hash + (this.getDependenceKey() != null
                ? this.getDependenceKey().hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return this.dependenceId.toString();
    }

    /**
     * @return the theirComposedDependenceName
     */
    public String getComposedDependenceName() {
        String myComposedDependenceName = this.getDependenceKey() + " " + this.getDependenceDescription();
        return myComposedDependenceName;
    }
}
