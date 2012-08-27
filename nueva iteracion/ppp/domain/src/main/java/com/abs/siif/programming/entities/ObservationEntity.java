package com.abs.siif.programming.entities;

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.security.entities.UserEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Israel Ruiz
 * 
 * Miguel Baizabal Aguirre : Mapeado
 */
@Entity
@Table(name = "siifpppprefichaobserv")
public class ObservationEntity
    implements Comparable<ObservationEntity>, Serializable{

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdPreFichaObservacion")
    private Long observationId;
    
    @Column(name = "Comentario", length = 150, nullable = false)
    private String commentary;

    @Column(name = "FechaComentario", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE) 
    private Date dateComment;
    
    @OneToOne
    //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "IdDependencia", nullable = true)
    private DependenceEntity observationDependence;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdUsuario", nullable = false)
    private UserEntity observationUser;
    
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdPreFicha", nullable = false)
    private InvPreFileEntity observationInvPreFile;
    
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdPerfil", nullable = false)
    private ProfileEntity observationProfile;
    
    /**
     * @return the idObservation
     */
    public Long getObservationId() {
        return observationId;
    }

    /**
     * @param idObservation the idObservation to set
     */
    public void setObservationId(Long observationId) {
        this.observationId = observationId;
    }

    /**
     * @return the commentary
     */
    public String getCommentary() {
        return commentary != null ? commentary.trim() : commentary;
    }

    /**
     * @param commentary the commentary to set
     */
    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    /**
     * @return the dateComment
     */
    public Date getDateComment() {
        return dateComment;
    }

    /**
     * @param dateComment the dateComment to set
     */
    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    /**
     * @return the urRegulatory
     */
    public DependenceEntity getObservationDependence() {
        return observationDependence;
    }

    /**
     * @param urRegulatory the urRegulatory to set
     */
    public void setObservationDependence(DependenceEntity observationDependence) {
        this.observationDependence = observationDependence;
    }

    public InvPreFileEntity getObservationInvPreFile() {
        return observationInvPreFile;
    }

    public void setObservationInvPreFile(InvPreFileEntity observationInvPreFile) {
        this.observationInvPreFile = observationInvPreFile;
    }

    public UserEntity getUserEntity() {
        return observationUser;
    }

    public void setUserEntity(UserEntity observationUser) {
        this.observationUser = observationUser;
    }

    public ProfileEntity getObservationProfile() {
        return observationProfile;
    }

    public void setObservationProfile(ProfileEntity observationProfile) {
        this.observationProfile = observationProfile;
    }

    @Override
    public int compareTo(ObservationEntity obj) {
        int result = -1;
        if (this.observationId != null && obj.observationId != null) {
            result = this.observationId.compareTo(obj.observationId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof ObservationEntity
                && this.observationId != null
                && ((ObservationEntity) obj).observationId != null) {

            result = this.observationId.equals(
                    ((ObservationEntity) obj).observationId);

        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.observationId != null
                ? this.observationId.hashCode() : 0);
        return hash;
    }
}
