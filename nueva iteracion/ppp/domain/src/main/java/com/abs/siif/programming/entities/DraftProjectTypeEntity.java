package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar / Miguel Baizabal Entidad de dominio que
 * mapea el tipo de anteproyecto
 */
@Entity
@Table(name = "siifpppTipoAnteProy")
public class DraftProjectTypeEntity implements 
        Serializable, Comparable<DraftProjectTypeEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdTipoAnteProyecto", nullable = false)
    private Long draftProjectTypeId;
    @Column(name = "Descripcion", nullable = false, length = 150)
    private String descriptionDraftProjectType;
    @Column(name = "Proceso")
    private boolean isProceso;
    @Column(name = "Proyecto")
    private boolean isProyecto;
    @OneToMany(mappedBy = "draftProjectType")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<DraftProjectEntity> draftProjects;

    public DraftProjectTypeEntity() {
        this.draftProjects = new HashSet<DraftProjectEntity>();
    }

    public Set<DraftProjectEntity> getDraftProjects() {
        return draftProjects;
    }

    public String getDescriptionProjectType() {
        return descriptionDraftProjectType != null ? descriptionDraftProjectType.trim() : descriptionDraftProjectType;
    }

    public void setDescriptionProjectType(String aDescriptionDraftProjectType) {
        this.descriptionDraftProjectType = aDescriptionDraftProjectType;
    }

    public Long getDraftProjectTypeId() {
        return draftProjectTypeId;
    }

    public void setDraftProjectTypeId(Long aDraftProjectTypeId) {
        this.draftProjectTypeId = aDraftProjectTypeId;
    }

    public boolean isIsProceso()
    {
        return isProceso;
    }

    public void setIsProceso(boolean isProceso)
    {
        this.isProceso = isProceso;
    }

    public boolean isIsProyecto()
    {
        return isProyecto;
    }

    public void setIsProyecto(boolean isProyecto)
    {
        this.isProyecto = isProyecto;
    }

    @Override
    public int compareTo(DraftProjectTypeEntity anOtherEntity) {
        int result = -1;
        if (this.draftProjectTypeId != null 
                && anOtherEntity.draftProjectTypeId != null) {
            result = this.draftProjectTypeId.compareTo(
                anOtherEntity.draftProjectTypeId);
        }
        return result;
    }
    
    @Override 
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof DraftProjectTypeEntity 
                 && this.draftProjectTypeId != null
                 && ((DraftProjectTypeEntity) obj).draftProjectTypeId != null) {
            result = this.draftProjectTypeId.equals(
                    ((DraftProjectTypeEntity) obj).draftProjectTypeId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + (this.draftProjectTypeId != null 
                ? this.draftProjectTypeId.hashCode() : 0);
        return hash;
    }
}
