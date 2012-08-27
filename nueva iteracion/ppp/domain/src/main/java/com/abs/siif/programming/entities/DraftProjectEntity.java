package com.abs.siif.programming.entities;

import com.abs.siif.budget.entities.BudgetKeyAndDraftProjectFramingEntity;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import com.abs.siif.planning.data.DraftFileType;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.security.entities.UserEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name = "siifpppanteproyecto")
@FilterDefs
        ({
@FilterDef(name="DraftProjectYearFilter", parameters = @ParamDef(name = "drafProjectYearParam", type = "string")),
@FilterDef(name="DraftProjectDependencyFilter", parameters = @ParamDef(name = "DraftProjectDependencyParam", type = "long")),
@FilterDef(name="DraftProjectNumberFilter", parameters = @ParamDef(name = "DraftProjectNumberParam", type = "string")),
@FilterDef(name="DraftProjectShortNameFilter", parameters = @ParamDef(name = "DraftProjectShortNameParam", type = "string")),
@FilterDef(name="DraftProjectActiveFilter", parameters = @ParamDef(name = "DraftProjectActiveParam", type = "string"))
})

@Filters
        ( {
    @Filter(name="DraftProjectYearFilter", condition="year(FechaInicio) = :drafProjectYearParam"),
    @Filter(name="DraftProjectDependencyFilter", condition="IdDependencia = :DraftProjectDependencyParam"),
    @Filter(name="DraftProjectNumberFilter", condition="Clave like :DraftProjectNumberParam"),
    @Filter(name="DraftProjectShortNameFilter", condition="NombreCorto like :DraftProjectShortNameParam"),
    @Filter(name="DraftProjectActiveFilter", condition="Activo = :DraftProjectActiveParam"),
} )
public class DraftProjectEntity implements 
        Serializable, Comparable<DraftProjectEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdAnteProyecto", nullable = false)
    private Long draftProjectId;
    @Column(name = "NombreCorto", length = 50, nullable = false)
    private String draftProjectShortName;
    @Column(name = "Nombre", length = 100, nullable = false)
    private String draftProjectName;
    @Column(name = "Descripcion", nullable = false, length = 150)
    private String draftProjectDescription;
    @Column(name = "Clave", length= 20)
    private String draftProjectKey;
    @Column(name = "FundamentoLegal", length = 100, nullable = true)
    private String draftProjectBaseLegal;
    @Column(name = "TipoFicha")
    @Enumerated(EnumType.ORDINAL)
    private DraftFileType draftFileType;
    @ManyToOne
    @JoinColumn(name = "IdTipoAnteProyecto", nullable = false)
    private DraftProjectTypeEntity draftProjectType;
    @ManyToOne
    @JoinColumn(name = "IdEstatusAnteProyecto", nullable = false)
    private DraftProjectStatusEntity draftProjectStatus;
    @ManyToOne
    @JoinColumn(name = "IdEstadoAnteProyecto", nullable = false)
    private DraftProjectStateEntity draftProjectState;
    @Column(name = "Activo", nullable = false)
    private boolean isDraftProjectActive;
    // (MBA 20120619) 
    // TODO : Quitar este atributo, debido a que el dato se mostrará solamente
    // en pantalla, obteniéndolo de las ubicaciones (siifabsnivregional)
    @Column(name = "IdAmbitoAnteProyecto", nullable = true)
    private Long draftProjectScopeId;
    
    @ManyToOne
    @JoinColumn(name = "IdDependencia", nullable = true)
    private DependenceEntity draftProjectDependency;
    @Column(name = "FechaInicio", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date draftProjectStartDate;
    @Column(name = "FechaFin", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date draftProjectEndDate;
    @Column(name = "proposito", nullable = false, length = 500)
    private String draftProjectPurpose;
    @OneToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "IdProgramacion", nullable = true)
    private ProgrammingEntity draftProjectProgramming;

    @OneToMany(mappedBy = "draftProject")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<DraftProjectBinnacleEntity> draftProjectBinnacleDraftProject;

    @OneToMany(mappedBy = "draftProjRegClassifDraftProject")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<DraftProjRegionalClassifierEntity> draftProjRegClassifDraftProject;

    // Generación de estructura para relacionar anteproyecto con grupos vulnerables
    @ManyToMany
    @JoinTable(name = "siifpppanteproygpovulner", joinColumns = {
        @JoinColumn(name = "idanteproyecto", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "idgpovulnerable",
        nullable = false, updatable = false)})
    private List<VulnerableGroupEntity> draftProjectVulnerableGroup;
    
    // Generación de estructura para relacionar anteproyecto con Claves
    // presupuestales
    @OneToMany(mappedBy = "draftProjectIdBudgetKeyAndDraftPro")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<BudgetKeyAndDraftProjectFramingEntity> draftProjectBudgetDetail;
    
    public  Set<DraftProjectBinnacleEntity> getDraftProjectBinnacleDraftProject()
    {
      return this.draftProjectBinnacleDraftProject;
    }
    
    

    
        public ProgrammingEntity getDraftProjectProgramming() {
        return draftProjectProgramming;
    }

    public void setDraftProjectProgramming(ProgrammingEntity aDraftProjectProgramming) {
        this.draftProjectProgramming = aDraftProjectProgramming;
    }

    public Date getDraftProjectEndDate() {
        return draftProjectEndDate;
    }

    public void setDraftProjectEndDate(Date aDraftProjectEndDate) {
        this.draftProjectEndDate = aDraftProjectEndDate;
    }

    public Date getDraftProjectStartDate() {
        return draftProjectStartDate;
    }

    public void setDraftProjectStartDate(Date aDraftProjectStartDate) {
        this.draftProjectStartDate = aDraftProjectStartDate;
    }

    public DependenceEntity getDraftProjectDependency() {
        return draftProjectDependency;
    }

    public void setDraftProjectDependency(DependenceEntity aDraftProjectDependency) {
        this.draftProjectDependency = aDraftProjectDependency;
    }

    public DraftProjectTypeEntity getDraftProjectType() {
        return draftProjectType;
    }

    public DraftProjectStatusEntity getDraftProjectStatus() {
        return draftProjectStatus;
    }

    public void setDraftProjectStatus(DraftProjectStatusEntity aDraftProjectStatus) {
        this.draftProjectStatus = aDraftProjectStatus;
    }

    public void setDraftProjectType(DraftProjectTypeEntity aDraftProjectType) {
        this.draftProjectType = aDraftProjectType;
    }

    public DraftFileType getDraftFileType() {
        return draftFileType;
    }

    public void setDraftFileType(DraftFileType aDraftFileType) {
        this.draftFileType = aDraftFileType;
    }

    public String getDraftProjectBaseLegal() {
        return draftProjectBaseLegal != null ? draftProjectBaseLegal.trim() : draftProjectBaseLegal;
    }

    public void setDraftProjectBaseLegal(String aDraftProjectBaseLegal) {
        this.draftProjectBaseLegal = aDraftProjectBaseLegal;
    }

    public String getDraftProjectDescription() {
        return draftProjectDescription != null ? draftProjectDescription.trim() : draftProjectDescription;
    }

    public void setDraftProjectDescription(String aDraftProjectDescription) {
        this.draftProjectDescription = aDraftProjectDescription;
    }

    public Long getDraftProjectId() {
        return draftProjectId;
    }

    public String getDraftProjectKey() {
        return draftProjectKey != null ? draftProjectKey.trim() : draftProjectKey;
    }

    public void setDraftProjectKey(String aDraftProjectKey) {
        this.draftProjectKey = aDraftProjectKey;
    }

    public String getDraftProjectName() {
        return draftProjectName != null ? draftProjectName.trim() : draftProjectName;
    }

    public void setDraftProjectName(String aDraftProjectName) {
        this.draftProjectName = aDraftProjectName;
    }

    public String getDraftProjectShortName() {
        return draftProjectShortName != null ? draftProjectShortName.trim() : draftProjectShortName;
    }

    public void setDraftProjectShortName(String aDraftProjectShortName) {
        this.draftProjectShortName = aDraftProjectShortName;
    }

    public String getDraftProjectPurpose() {
        return draftProjectPurpose != null ? draftProjectPurpose.trim() : draftProjectPurpose;
    }

    public void setDraftProjectPurpose(String aDraftProjectPurpose) {
        this.draftProjectPurpose = aDraftProjectPurpose;
    }        
    
    @Override
    public int compareTo(DraftProjectEntity anOtherEntity) {

        int result = -1;
        if (this.draftProjectId != null
                && anOtherEntity.getDraftProjectId() != null) {
            result = this.draftProjectId.compareTo(anOtherEntity.getDraftProjectId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof DraftProjectEntity && this.draftProjectId != null
                && ((DraftProjectEntity) obj).draftProjectId != null) {
            result = this.draftProjectId.equals(
                    ((DraftProjectEntity) obj).draftProjectId);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.draftProjectId != null
                ? this.draftProjectId.hashCode() : 0);
        return hash;
    }

    /**
     * @param draftProjectId the draftProjectId to set
     */
    public void setDraftProjectId(Long draftProjectId) {
        this.draftProjectId = draftProjectId;
    }

  /**
   * @return the draftProjectState
   */
  public DraftProjectStateEntity getDraftProjectState()
  {
    return draftProjectState;
  }

  /**
   * @param draftProjectState the draftProjectState to set
   */
  public void setDraftProjectState(DraftProjectStateEntity draftProjectState)
  {
    this.draftProjectState = draftProjectState;
  }
  
  // Estos son los setters y getters de la Pestaña de PED--No Tocar--

    /**
     * @return the draftProjectVulnerableGroup
     */
    public List<VulnerableGroupEntity> getDraftProjectVulnerableGroup() {
        return draftProjectVulnerableGroup;
    }

    /**
     * @param draftProjectVulnerableGroup the draftProjectVulnerableGroup to set
     */
    public void setDraftProjectVulnerableGroup(List<VulnerableGroupEntity> draftProjectVulnerableGroup) {
        this.draftProjectVulnerableGroup = draftProjectVulnerableGroup;
    }

    public boolean isIsDraftProjectActive()
    {
        return isDraftProjectActive;
    }

    public void setIsDraftProjectActive(boolean isDraftProjectActive)
    {
        this.isDraftProjectActive = isDraftProjectActive;
    }

    public Set<BudgetKeyAndDraftProjectFramingEntity> getDraftProjectBudgetDetail() {
        return draftProjectBudgetDetail;
    }

    public void setDraftProjectBudgetDetail(Set<BudgetKeyAndDraftProjectFramingEntity> draftProjectBudgetDetail) {
        this.draftProjectBudgetDetail = draftProjectBudgetDetail;
    }
    
    
}
