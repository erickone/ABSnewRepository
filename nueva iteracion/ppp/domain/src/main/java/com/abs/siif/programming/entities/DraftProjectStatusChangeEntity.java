package com.abs.siif.programming.entities;

import com.abs.siif.common.entities.SubProcessEntity;
import com.abs.siif.security.entities.ProfileEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Mapea la entidad cambio de estado de anteproyecto
 * 
 * MBA 20120619 : Se mapea la entidad con relación a la entidad del subproceso
 * Se agrega la propiedad draftProjectStatusChangeIsSystem para indicar si el cambio de estatus
 * es usado solo por el sistema o lo pueden ver todos
 * 
 */
@Entity
@Table(name = "siifpppcamestanteproy")
public class DraftProjectStatusChangeEntity implements Serializable, Comparable<DraftProjectStatusChangeEntity> {

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdCamEstAnteProyecto", nullable = false)
    private Long draftProjectStatusChangeId;

    // Identificador del estado inicial del anteproyecto
    @ManyToOne
    @JoinColumn(name = "IdEstatusInicial", nullable = false)
    private DraftProjectStatusEntity beginDraftProjectStatus;
    
    // Identificador del estado final del anteproyecto
    @ManyToOne
    @JoinColumn(name = "IdEstatusFinal", nullable = false)
    private DraftProjectStatusEntity finalDraftProjectStatus;

    // Bandera que indica si el cambio de estatus es por omisión
    @Column(name = "Omision", nullable = false)
    private Boolean draftProjectStatusChangeOmission;

    // Bandera que indica si el cambio de estatus es de sistema
    @Column(name = "DeSistema", nullable = false)
    private Boolean draftProjectStatusChangeIsSystem;
    
    // Generación de estructura para relacionar perfiles con cambios de estatus
    @ManyToMany
    @JoinTable(name = "siifpppPerCamEstAnteProy", joinColumns = {
        @JoinColumn(name = "IdCamEstAnteProyecto", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdPerfil",
        nullable = false, updatable = false)})
    private List<ProfileEntity> draftProjectStatusChangeProfile;    

    
    public List<ProfileEntity> getDraftProjectStatusChangeProfile() {
        return draftProjectStatusChangeProfile;
    }
 
    
    
    public DraftProjectStatusEntity getBeginDraftProjectStatus() {
        return beginDraftProjectStatus;
    }

    public void setBeginDraftProjectStatus(DraftProjectStatusEntity beginDraftProjectStatus) {
        this.beginDraftProjectStatus = beginDraftProjectStatus;
    }

    public Boolean getDraftProjectChangeOmission() {
        return draftProjectStatusChangeOmission;
    }

    public void setDraftProjectChangeOmission(Boolean draftProjectStatusChangeOmission) {
        this.draftProjectStatusChangeOmission = draftProjectStatusChangeOmission;
    }

    public Long getDraftProjectStatusChangeId() {
        return draftProjectStatusChangeId;
    }

    public void setDraftProjectStatusChangeId(Long draftProjectStatusChangeId) {
        this.draftProjectStatusChangeId = draftProjectStatusChangeId;
    }

    public DraftProjectStatusEntity getFinalDraftProjectStatus() {
        return finalDraftProjectStatus;
    }

    public void setFinalDraftProjectStatus(DraftProjectStatusEntity finalDraftProjectStatus) {
        this.finalDraftProjectStatus = finalDraftProjectStatus;
    }

    public Boolean getDraftProjectStatusChangeIsSystem() {
        return draftProjectStatusChangeIsSystem;
    }

    public void setDraftProjectStatusChangeIsSystem(Boolean draftProjectStatusChangeIsSystem) {
        this.draftProjectStatusChangeIsSystem = draftProjectStatusChangeIsSystem;
    }

    public Boolean getDraftProjectStatusChangeOmission() {
        return draftProjectStatusChangeOmission;
    }

    public void setDraftProjectStatusChangeOmission(Boolean draftProjectStatusChangeOmission) {
        this.draftProjectStatusChangeOmission = draftProjectStatusChangeOmission;
    }

    
    @Override
    public int compareTo(DraftProjectStatusChangeEntity anOtherEntity) {
        int result = -1;
        if (this.draftProjectStatusChangeId != null 
                && anOtherEntity.getDraftProjectStatusChangeId() != null) {
        result = this.draftProjectStatusChangeId.compareTo(
                anOtherEntity.getDraftProjectStatusChangeId());
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof DraftProjectStatusChangeEntity 
                 && this.draftProjectStatusChangeId != null 
                 && ((DraftProjectStatusChangeEntity) obj).draftProjectStatusChangeId != null) {
            result = this.draftProjectStatusChangeId.equals(
                    ((DraftProjectStatusChangeEntity) obj).draftProjectStatusChangeId);
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + (this.draftProjectStatusChangeId != null 
                ? this.draftProjectStatusChangeId.hashCode() : 0);
        return hash;
    }
        
}
