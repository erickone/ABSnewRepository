package com.abs.siif.programming.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Mapea la entidad configuración de estado de anteproyecto
 */
@Entity
@Table(name = "siifpppEstConfigAnteProy")
public class DraftProjectStatusConfigEntity implements 
        Serializable, Comparable<DraftProjectStatusConfigEntity> {
    
    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdEstConfigAnteProyecto", nullable = false)
    private Long draftProjectStatusConfigId;
    
    // Identificador del estado de anteproyecto 
    @OneToOne
   @JoinColumn(name = "IdEstatusAnteproyecto", nullable = true)
    private DraftProjectStatusEntity draftProjectStatusConfig;

    // Se agregan comentarios a la entidad y relación entre perfiles y cambios de estatus de anteproyecto
    // Bandera que indica si el estatus es inicial
    @Column(name = "Inicial")
    private boolean draftProjectStatusConfigIsInitial;

    // Bandera que indica si el estatus es de validación SEFIN
    @Column(name = "ValidaSEFIN")
    private boolean draftProjectStatusConfigIsValidateSEFIN;

    // Bandera que indica si el estatus es de validación SEPLAN
    @Column(name = "ValidaSEPLAN")
    private boolean draftProjectStatusConfigIsValidateSEPLAN;

    // Bandera que indica si el estatus es de autorización
    @Column(name = "Autoriza")
    private boolean draftProjectStatusConfigIsAuthorized;

    // Bandera que indica si el estatus es de cancelación
    @Column(name = "Cancelado")
    private boolean draftProjectStatusConfigIsCancelled;
    
    // Bandera que indica si el estatus es final
    @Column(name = "Final")
    private boolean draftProjectStatusConfigIsFinal;

    public DraftProjectStatusEntity getDraftProjectStatusConfig() {
        return draftProjectStatusConfig;
    }

    public void setDraftProjectStatusConfig(DraftProjectStatusEntity draftProjectStatusConfig) {
        this.draftProjectStatusConfig = draftProjectStatusConfig;
    }

    public Long getDraftProjectStatusConfigId() {
        return draftProjectStatusConfigId;
    }

    public void setDraftProjectStatusConfigId(Long draftProjectStatusConfigId) {
        this.draftProjectStatusConfigId = draftProjectStatusConfigId;
    }

    public boolean isDraftProjectStatusConfigIsAuthorized() {
        return draftProjectStatusConfigIsAuthorized;
    }

    public void setDraftProjectStatusConfigIsAuthorized(boolean draftProjectStatusConfigIsAuthorized) {
        this.draftProjectStatusConfigIsAuthorized = draftProjectStatusConfigIsAuthorized;
    }

    public boolean isDraftProjectStatusConfigIsCancelled() {
        return draftProjectStatusConfigIsCancelled;
    }

    public void setDraftProjectStatusConfigIsCancelled(boolean draftProjectStatusConfigIsCancelled) {
        this.draftProjectStatusConfigIsCancelled = draftProjectStatusConfigIsCancelled;
    }

    public boolean isDraftProjectStatusConfigIsFinal() {
        return draftProjectStatusConfigIsFinal;
    }

    public void setDraftProjectStatusConfigIsFinal(boolean draftProjectStatusConfigIsFinal) {
        this.draftProjectStatusConfigIsFinal = draftProjectStatusConfigIsFinal;
    }

    public boolean isDraftProjectStatusConfigIsInitial() {
        return draftProjectStatusConfigIsInitial;
    }

    public void setDraftProjectStatusConfigIsInitial(boolean draftProjectStatusConfigIsInitial) {
        this.draftProjectStatusConfigIsInitial = draftProjectStatusConfigIsInitial;
    }

    public boolean isDraftProjectStatusConfigIsValidateSEFIN() {
        return draftProjectStatusConfigIsValidateSEFIN;
    }

    public void setDraftProjectStatusConfigIsValidateSEFIN(boolean draftProjectStatusConfigIsValidateSEFIN) {
        this.draftProjectStatusConfigIsValidateSEFIN = draftProjectStatusConfigIsValidateSEFIN;
    }

    public boolean isDraftProjectStatusConfigIsValidateSEPLAN() {
        return draftProjectStatusConfigIsValidateSEPLAN;
    }

    public void setDraftProjectStatusConfigIsValidateSEPLAN(boolean draftProjectStatusConfigIsValidateSEPLAN) {
        this.draftProjectStatusConfigIsValidateSEPLAN = draftProjectStatusConfigIsValidateSEPLAN;
    }
      
    @Override
    public int compareTo(DraftProjectStatusConfigEntity anOtherEntity) {
        int result = -1;
        if (this.draftProjectStatusConfigId != null 
                && anOtherEntity.getDraftProjectStatusConfigId() != null) {
        result = this.draftProjectStatusConfigId.compareTo(
                anOtherEntity.getDraftProjectStatusConfigId());
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof DraftProjectStatusConfigEntity 
                 && this.draftProjectStatusConfigId != null 
                 && ((DraftProjectStatusConfigEntity) obj).draftProjectStatusConfigId != null) {
            result = this.draftProjectStatusConfigId.equals(
                    ((DraftProjectStatusConfigEntity) obj).draftProjectStatusConfigId);
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + (this.draftProjectStatusConfigId != null 
                ? this.draftProjectStatusConfigId.hashCode() : 0);
        return hash;
    }
    
}
