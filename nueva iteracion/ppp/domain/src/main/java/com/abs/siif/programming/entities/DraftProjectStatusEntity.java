package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar Mapea la entidad estado de ante proyecto
 */
@Entity
@Table(name = "siifpppEstatusAnteProy")
public class DraftProjectStatusEntity implements
        Serializable, Comparable<DraftProjectStatusEntity> {

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdEstatusAnteProyecto", nullable = false)
    private Long draftProjectStatusId;
    // Descripción del estado de anteproyecto
    @Column(name = "Descripcion", nullable = false, length = 150)
    private String draftProjectStatusDescription;
    // Días estimados para la realización del estado
    @Column(name = "DiasEstimados", nullable = false)
    private Integer draftProjectStatusEstimatedDays;
    //Consecutivo para determinar flujo.
    //TODO: Esta implementación es temporal
    @Column(name = "consecutivoStatus")
    private Integer draftProjectStatusConsecutiveStatus;
    // Mapeo con entidad anteproyecto (DraftProject)
    @OneToMany(mappedBy = "draftProjectStatus")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<DraftProjectEntity> draftProjects;
    // Mapeo con entidad bitácora de anteproyecto (DraftProjectBinnacle)
    @OneToMany(mappedBy = "draftProjectStatusBinnacle")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<DraftProjectBinnacleEntity> draftProjectBinnacleStatusEnt;
    // Mapeo con entidad config de estatus de anteproyecto (DraftProjectStatusConfig)
    @OneToOne(mappedBy="draftProjectStatusConfig",fetch= FetchType.EAGER) 
    private DraftProjectStatusConfigEntity draftProjectStatusConfigEnt;
    // Mapeo con entidad cambio de estatus de anteproyecto (DraftProjectStatusChange) - Inicial
    @OneToMany(mappedBy = "beginDraftProjectStatus")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<DraftProjectStatusChangeEntity> draftProjectStatusChangeBegin;
    // Mapeo con entidad cambio de estatus de anteproyecto (DraftProjectStatusChange) - Final
    @OneToMany(mappedBy = "finalDraftProjectStatus")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<DraftProjectStatusChangeEntity> draftProjectStatusChangeEnd;

    public DraftProjectStatusConfigEntity getDraftProjectStatusConfigEnt() {
        return draftProjectStatusConfigEnt;
    }

    public void setDraftProjectStatusConfigEnt(DraftProjectStatusConfigEntity draftProjectStatusConfigEnt) {
        this.draftProjectStatusConfigEnt = draftProjectStatusConfigEnt;
    }

    

    public Set<DraftProjectEntity> getDraftProjects() {
        return draftProjects;
    }

    public String getDraftProjectStatusdescription() {
        return draftProjectStatusDescription != null ? draftProjectStatusDescription.trim() : draftProjectStatusDescription ;
    }

    public void setDraftProjectStatusdescription(String aDraftProjectStatusDescription) {
        this.draftProjectStatusDescription = aDraftProjectStatusDescription;
    }

    public Long getDraftProjectStatusId() {
        return draftProjectStatusId;
    }

    public void setDraftProjectStatusId(Long aDraftProjectStatusId) {
        this.draftProjectStatusId = aDraftProjectStatusId;
    }

    public String getDraftProjectStatusDescription() {
        return draftProjectStatusDescription != null ? draftProjectStatusDescription.trim() : draftProjectStatusDescription;
    }

    public void setDraftProjectStatusDescription(String draftProjectStatusDescription) {
        this.draftProjectStatusDescription = draftProjectStatusDescription;
    }

    public Integer getDraftProjectStatusEstimatedDays() {
        return this.draftProjectStatusEstimatedDays;
    }

    public void setDraftProjectStatusEstimatedDays(Integer draftProjectStatusEstimatedDays) {
        this.draftProjectStatusEstimatedDays = draftProjectStatusEstimatedDays;
    }

    public Set<DraftProjectBinnacleEntity> getDraftProjectBinnacleStatusEnt() {
        return draftProjectBinnacleStatusEnt;
    }

    public void setDraftProjectBinnacleStatusEnt(Set<DraftProjectBinnacleEntity> draftProjectBinnacleStatusEnt) {
        this.draftProjectBinnacleStatusEnt = draftProjectBinnacleStatusEnt;
    }

    public Set<DraftProjectStatusChangeEntity> getDraftProjectStatusChangeBegin() {
        return draftProjectStatusChangeBegin;
    }

    public void setDraftProjectStatusChangeBegin(Set<DraftProjectStatusChangeEntity> draftProjectStatusChangeBegin) {
        this.draftProjectStatusChangeBegin = draftProjectStatusChangeBegin;
    }

    public Set<DraftProjectStatusChangeEntity> getDraftProjectStatusChangeEnd() {
        return draftProjectStatusChangeEnd;
    }

    public void setDraftProjectStatusChangeEnd(Set<DraftProjectStatusChangeEntity> draftProjectStatusChangeEnd) {
        this.draftProjectStatusChangeEnd = draftProjectStatusChangeEnd;
    }

    @Override
    public int compareTo(DraftProjectStatusEntity anOtherEntity) {
        int result = -1;
        if (this.draftProjectStatusId != null
                && anOtherEntity.getDraftProjectStatusId() != null) {
            result = this.draftProjectStatusId.compareTo(
                    anOtherEntity.getDraftProjectStatusId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof DraftProjectStatusEntity
                && this.draftProjectStatusId != null
                && ((DraftProjectStatusEntity) obj).draftProjectStatusId != null) {
            result = this.draftProjectStatusId.equals(
                    ((DraftProjectStatusEntity) obj).draftProjectStatusId);
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.draftProjectStatusId != null
                ? this.draftProjectStatusId.hashCode() : 0);
        return hash;
    }

    /**
     * @return the draftProjectStatusConsecutiveStatus
     */
    public Integer getDraftProjectStatusConsecutiveStatus() {
        return draftProjectStatusConsecutiveStatus;
    }

    /**
     * @param draftProjectStatusConsecutiveStatus the
     * draftProjectStatusConsecutiveStatus to set
     */
    public void setDraftProjectStatusConsecutiveStatus(Integer draftProjectStatusConsecutiveStatus) {
        this.draftProjectStatusConsecutiveStatus = draftProjectStatusConsecutiveStatus;
    }
}
