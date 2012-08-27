package com.abs.siif.programming.entities;

import com.abs.siif.planning.entities.DependenceEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.*;

/**
 *
 * @author Israel Ruiz
 */
@Entity
@Table(name = "siifppppreficha")
@FilterDefs({
    @FilterDef(name = "InvPreFileNumberFilter", parameters =
    @ParamDef(name = "invPreFileNumberParam", type = "string")),
    @FilterDef(name = "InvPreFileNameFilter", parameters =
    @ParamDef(name = "invPreFileNameParam", type = "string")),
    @FilterDef(name = "InvPreFileDescriptionFilter", parameters =
    @ParamDef(name = "invPreFileDescriptionParam", type = "string")),
    @FilterDef(name = "InvPreFileIdFilter", parameters =
    @ParamDef(name = "invPreFileIdParam", type = "long")),
    @FilterDef(name = "InvPreFileUREFilter", parameters =
    @ParamDef(name = "InvPreFileUREParam", type = "long")),})
@Filters({
    @Filter(name = "InvPreFileNumberFilter", condition = "Folio like :invPreFileNumberParam"),
    @Filter(name = "InvPreFileNameFilter", condition = "Nombre like :invPreFileNameParam"),
    @Filter(name = "InvPreFileDescriptionFilter", condition = "Descripcion like :invPreFileDescriptionParam"),
    @Filter(name = "InvPreFileIdFilter", condition = "IdPreFicha = :invPreFileIdParam"),
    @Filter(name = "InvPreFileUREFilter", condition = "IdUREjecutora = :InvPreFileUREParam")
})
public class InvPreFileEntity
        implements Comparable<InvPreFileEntity>, Serializable {

  @OneToOne(mappedBy = "invPreFile")
  private BenefAndGoalsEntity benefAndGoalsEntity;
  @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdPreFicha")
    private Long invPreFileId;
  /**
   * Tipo de Preficha de Inversión Refire si es Obra o Acción
   */
  @Column(name = "Obra")
  private boolean build;
  @Column(name = "Accion")
  private boolean action;
  /**
   * Nivel de desagregación hay 3 diferentes niveles
   */
  @Column(name = "ProyEspecifico")
  private boolean especificProyect;
  @Column(name = "Programa")
  private boolean programm;
  @Column(name = "Fondo")
  private boolean fund;
  /**
   * Generada con folio unico por dependencia y unico por organismo sera de ocho digitos
   */
  @Column(name = "Folio", length = 8, nullable = false)
  private String folio;
  /**
   * dato capturable por el cliente no puede repetirse.
   */
  @Column(name = "Prioridad", length = 10, nullable = false)
  private String priority;
  /**
   * Nombre de la preficha
   */
  @Column(name = "Nombre", length = 100, nullable = false)
  private String name;
  /**
   * Descripción de la preficha
   */
  @Column(name = "Descripcion", length = 150, nullable = false)
  private String description;
  /**
   * Descripcion de los beneficios
   */
  @Column(name = "DescBeneficios", length = 150, nullable = false)
  private String descBenefits;
  /**
   * Unidad Ejecutora del Gasto Es la misma que la del Anteproyecto en caso de no hacerse
   * desde cero
   */
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "IdUREjecutora", nullable = false)//unidad responsable Ejecutora del Gasto
  private DependenceEntity invPreFileUExecuting;
  /**
   * Unidad Responsable Normativa
   */
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "IdURNormativaFed", nullable = false)
  private FederalURRegulatoryEntity invPreFileURRegulatory;
  /**
   * Promotor que se obtiene de un catalogo directo
   */
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "IdPromotor", nullable = false)
  private PromoterEntity promoter;
  /**
   * Anteproyecto con el cual se relaciona
   */
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "IdAnteProyecto", nullable = false)
  private DraftProjectEntity invPreFileDraftProject;
  /**
   * Componente que será impactado por la preficha
   */
  @ManyToOne(cascade =
  {
    CascadeType.ALL
  }, fetch = FetchType.LAZY)
  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "IdComponente", nullable = true)
  private ComponentEntity invPreFileComponent;
  /**
   * Unidades ejecutora del gasto puede ser una o varias Por definir con Cesar y Alberto;
   */
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "siifpppprefichadep", joinColumns =
  {
    @JoinColumn(name = "IdPreFicha", nullable = false, updatable = false)
  },
  inverseJoinColumns =
  {
    @JoinColumn(name = "IdDependencia",
    nullable = false, updatable = false)
  })
  private List<DependenceEntity> unitExecSpending;
  @OneToMany(mappedBy = "physicalAndFinancialProgramInvPreFile", fetch = FetchType.LAZY)
  @Cascade(
  {
    org.hibernate.annotations.CascadeType.SAVE_UPDATE
  })
  private Set<PhysicalAndFinancialProgramEntity> physicalAndFinancialProgramInvPreFile;
  @OneToMany(mappedBy = "inputPhysicalAndFinInvPreFile")
  @Cascade(
  {
    org.hibernate.annotations.CascadeType.SAVE_UPDATE
  })
  private Set<InputPhysicalAndFinEntity> inputPhysicalAndFinInvPreFile;
  @OneToMany(mappedBy = "inputInvPreFile", fetch = FetchType.LAZY)
 /* @Cascade(
  {
    org.hibernate.annotations.CascadeType.SAVE_UPDATE
  })*/
  private List<InputEntity> inputEntity;

    public Long getInvPreFileId() {
        return invPreFileId;
    }

    public void setInvPreFileId(Long invPreFileId) {
        this.invPreFileId = invPreFileId;
    }

    /**
     * @return the build
     */
    public boolean isBuild() {
        return build;
    }

    /**
     * @param build the build to set
     */
    public void setBuild(boolean build) {
        this.build = build;
    }

    /**
     * @return the action
     */
    public boolean isAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(boolean action) {
        this.action = action;
    }

    /**
     * @return the folio
     */
    public String getFolio() {
        return folio != null ? folio.trim() : folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority != null ? priority.trim() : priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name != null ? name.trim() : name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description != null ? description.trim() : description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the descBenefits
     */
    public String getDescBenefits() {
        return descBenefits != null ? descBenefits.trim() : descBenefits;
    }

    /**
     * @param descBenefits the descBenefits to set
     */
    public void setDescBenefits(String descBenefits) {
        this.descBenefits = descBenefits;
    }

    /**
     * @return the especificProyect
     */
    public boolean isEspecificProyect() {
        return especificProyect;
    }

    /**
     * @param especificProyect the especificProyect to set
     */
    public void setEspecificProyect(boolean especificProyect) {
        this.especificProyect = especificProyect;
    }

    /**
     * @return the programm
     */
    public boolean isProgramm() {
        return programm;
    }

    /**
     * @param programm the programm to set
     */
    public void setProgramm(boolean programm) {
        this.programm = programm;
    }

    /**
     * @return the fund
     */
    public boolean isFund() {
        return fund;
    }

    /**
     * @param fund the fund to set
     */
    public void setFund(boolean fund) {
        this.fund = fund;
    }

    /**
     * @return the promoter
     */
    public PromoterEntity getPromoter() {
        return promoter;
    }

    /**
     * @param promoter the promoter to set
     */
    public void setPromoter(PromoterEntity promoter) {
        this.promoter = promoter;
    }

    /**
     * @return the invPreFileDraftProject
     */
    public DraftProjectEntity getInvPreFileDraftProject() {
        return invPreFileDraftProject;
    }

    /**
     * @param invPreFileDraftProject the invPreFileDraftProject to set
     */
    public void setInvPreFileDraftProject(DraftProjectEntity invPreFileDraftProject) {
        this.invPreFileDraftProject = invPreFileDraftProject;
    }

    /**
     * @return the invPreFileUExecuting
     */
    public DependenceEntity getInvPreFileUExecuting() {
        return invPreFileUExecuting;
    }

    /**
     * @param invPreFileUExecuting the invPreFileUExecuting to set
     */
    public void setInvPreFileUExecuting(DependenceEntity invPreFileUExecuting) {
        this.invPreFileUExecuting = invPreFileUExecuting;
    }

    public ComponentEntity getInvPreFileComponent() {
        return invPreFileComponent;
    }

    public void setInvPreFileComponent(ComponentEntity invPreFileComponent) {
        this.invPreFileComponent = invPreFileComponent;
    }

  public FederalURRegulatoryEntity getInvPreFileURRegulatory()
  {
    return invPreFileURRegulatory;
  }

  public void setInvPreFileURRegulatory(FederalURRegulatoryEntity invPreFileURRegulatory)
  {
    this.invPreFileURRegulatory = invPreFileURRegulatory;
  }

    public List<DependenceEntity> getUnitExecSpending() {
        return unitExecSpending;
    }

    public void setUnitExecSpending(List<DependenceEntity> unitExecSpending) {
        this.unitExecSpending = unitExecSpending;
    }

    public Set<InputPhysicalAndFinEntity> getInputPhysicalAndFinInvPreFile() {
        return inputPhysicalAndFinInvPreFile;
    }

    public void setInputPhysicalAndFinInvPreFile(Set<InputPhysicalAndFinEntity> inputPhysicalAndFinInvPreFile) {
        this.inputPhysicalAndFinInvPreFile = inputPhysicalAndFinInvPreFile;
    }

    public Set<PhysicalAndFinancialProgramEntity> getPhysicalAndFinancialProgramInvPreFile() {
        return physicalAndFinancialProgramInvPreFile;
    }

    public void setPhysicalAndFinancialProgramInvPreFile(Set<PhysicalAndFinancialProgramEntity> physicalAndFinancialProgramInvPreFile) {
        this.physicalAndFinancialProgramInvPreFile = physicalAndFinancialProgramInvPreFile;
    }

    @Override
    public int compareTo(InvPreFileEntity obj) {
        return this.invPreFileId.compareTo(obj.getInvPreFileId());
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof InvPreFileEntity) {
            this.invPreFileId.equals(((InvPreFileEntity) obj).getInvPreFileId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.invPreFileId != null
                ? this.invPreFileId.hashCode() : 0);
        return hash;
    }

    /**
     * @return the inputEntity
     */
    public List<InputEntity> getInputEntity() {
        return inputEntity;
    }

    /**
     * @param inputEntity the inputEntity to set
     */
    public void setInputEntity(List<InputEntity> inputEntity) {
        this.inputEntity = inputEntity;
    }
}
