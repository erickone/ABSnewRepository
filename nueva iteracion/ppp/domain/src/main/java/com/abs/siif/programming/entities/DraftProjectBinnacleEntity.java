package com.abs.siif.programming.entities;

import com.abs.siif.security.entities.UserEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Entity
@Table(name = "siifpppBitacoraAnteProy")
public class DraftProjectBinnacleEntity implements 
        Serializable, Comparable<DraftProjectBinnacleEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdBitacoraAnteProyecto", nullable = false)
    private Long draftProjectbinnacleId;
    
    // AnteProyecto
    @ManyToOne
    @JoinColumn(name = "IdAnteProyecto", nullable = false)
    private DraftProjectEntity draftProject;
    
    // Usuario
    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false)
    private UserEntity userBinnacle;
    
    // Estatus
    @ManyToOne
    @JoinColumn(name = "IdEstAnteProyecto", nullable = false)
    private DraftProjectStatusEntity draftProjectStatusBinnacle;

    @Column(name = "Fecha")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date draftProjectBinnacleDate;
    
    @Column(name="DiasReales", nullable=false)
    private int draftProjectBinnacleRealDay;

    @Column(name="Consecutivo", nullable=false)
    private int draftProjectBinnacleConsecutive;
    
    public DraftProjectStatusEntity getDraftProjectStatus() {
        return draftProjectStatusBinnacle;
    }

    public void setDraftProjectStatus(DraftProjectStatusEntity draftProjectStatusBinnacle) {
        this.draftProjectStatusBinnacle = draftProjectStatusBinnacle;
    }

    public DraftProjectEntity getDraftProject() {
        return draftProject;
    }

    public void setDraftProject(DraftProjectEntity draftProject) {
        this.draftProject = draftProject;
    }

    public Date getDraftProjectBinnacleDate() {
        return draftProjectBinnacleDate;
    }

    public void setDraftProjectBinnacleDate(Date draftProjectBinnacleDate) {
        this.draftProjectBinnacleDate = draftProjectBinnacleDate;
    }

    public int getDraftProjectBinnacleRealDay() {
        return draftProjectBinnacleRealDay;
    }

    public void setDraftProjectBinnacleRealDay(int draftProjectBinnacleRealDay) {
        this.draftProjectBinnacleRealDay = draftProjectBinnacleRealDay;
    }

    public Long getDraftProjectbinnacleId() {
        return draftProjectbinnacleId;
    }

    public void setDraftProjectbinnacleId(Long draftProjectbinnacleId) {
        this.draftProjectbinnacleId = draftProjectbinnacleId;
    }

    public UserEntity getUserBinnacle() {
        return userBinnacle;
    }

    public void setUserBinnacle(UserEntity userBinnacle) {
        this.userBinnacle = userBinnacle;
    }

    public int getDraftProjectBinnacleConsecutive() {
        return draftProjectBinnacleConsecutive;
    }

    public void setDraftProjectBinnacleConsecutive(int draftProjectBinnacleConsecutive) {
        this.draftProjectBinnacleConsecutive = draftProjectBinnacleConsecutive;
    }
    
    @Override
    public int compareTo(DraftProjectBinnacleEntity obj) {
        int result = -1;
        if (this.draftProjectbinnacleId != null && obj.getDraftProjectbinnacleId() != null) {
            result = draftProjectbinnacleId.compareTo(obj.getDraftProjectbinnacleId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj instanceof DraftProjectBinnacleEntity && this.draftProjectbinnacleId != null 
                && ((DraftProjectBinnacleEntity)obj).getDraftProjectbinnacleId() != null){
            result = draftProjectbinnacleId.equals(((DraftProjectBinnacleEntity)obj).getDraftProjectbinnacleId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.draftProjectbinnacleId != null ? this.draftProjectbinnacleId.hashCode() : 0);
        return hash;
    }

}
