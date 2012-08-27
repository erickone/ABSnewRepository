package com.abs.siif.security.entities;

import com.abs.siif.programming.entities.DraftProjectStatusChangeEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Entity
@Table(name = "siifsegperfil")
public class ProfileEntity implements 
        Serializable, Comparable<ProfileEntity>{
    
    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdPerfil", nullable = false)
    private Long profileId;
    
    // Descripción del perfil
    @Column(name="Descripcion",nullable=false,length=150)
    private String profileDescription;
    
    // Generación de estructura para relacionar perfiles con usuarios
    @ManyToMany
    @JoinTable(name = "siifsegusuperfil", joinColumns = {
        @JoinColumn(name = "IdPerfil", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdUsuario",
        nullable = false, updatable = false)})
    private List<UserEntity> profileUser;

    // Generación de estructura para relacionar perfiles con cambios de estatus
    @ManyToMany
    @JoinTable(name = "siifpppPerCamEstAnteProy", joinColumns = {
        @JoinColumn(name = "IdPerfil", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdCamEstAnteProyecto",
        nullable = false, updatable = false)})
    private List<DraftProjectStatusChangeEntity> profileDraftProjectStatusChange;    
    
    public String getProfileDescription() {
        return profileDescription != null ?
                profileDescription.trim(): profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public Long getProfileId() {
        return profileId ;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public List<UserEntity> getProfileUser() {
        return profileUser;
    }

    public void setProfileUser(List<UserEntity> profileUser) {
        this.profileUser = profileUser;
    }
    
    @Override
    public int compareTo(ProfileEntity obj)
    {
        int result = -1;
        if (this.profileId != null && obj.profileId != null) {
        result = this.profileId.compareTo(obj.profileId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
         boolean result = false;
         if (obj instanceof ProfileEntity && this.profileId != null
                 && ((ProfileEntity) obj).profileId != null) {
            result = this.profileId.equals(
                    ((ProfileEntity) obj).profileId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 73 * hash + (this.profileId != null 
                ? this.profileId.hashCode() : 0);
        return hash;
    }
    
}
