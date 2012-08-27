package com.abs.siif.programming.entities;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal
 */
@Entity
@Table(name = "siifpppanteproyclasifreg")
public class DraftProjRegionalClassifierEntity
        implements Comparable<DraftProjRegionalClassifierEntity>, Serializable {

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdAnteproyClasifReg", nullable = false)
    private Long idDraftProjClasifReg;
    
    // Identificador del Anteproyecto
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdAnteproyecto", nullable = false)
    private DraftProjectEntity draftProjRegClassifDraftProject;

    // Identificador del Clasificador Regional
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdClasifRegional", nullable = false)
    private RegionalClassifierEntity draftProjectRegionalClassifierRegClassif;
    
    @Override
    public int compareTo(DraftProjRegionalClassifierEntity obj) {
        return idDraftProjClasifReg.compareTo(obj.getIdDraftProjClasifReg());
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof DraftProjRegionalClassifierEntity) {
            result = ((DraftProjRegionalClassifierEntity) obj).getIdDraftProjClasifReg().equals(this.idDraftProjClasifReg);
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.idDraftProjClasifReg != null ? this.idDraftProjClasifReg.hashCode() : 0);
        return hash;
    }

    /**
     * @return the idDraftProjClasifReg
     */
    public Long getIdDraftProjClasifReg() {
        return idDraftProjClasifReg;
    }

    /**
     * @param idDraftProjClasifReg the idDraftProjClasifReg to set
     */
    public void setIdDraftProjClasifReg(Long idDraftProjClasifReg) {
        this.idDraftProjClasifReg = idDraftProjClasifReg;
    }

    public DraftProjectEntity getDraftProjRegClassifDraftProject() {
        return draftProjRegClassifDraftProject;
    }

    public void setDraftProjRegClassifDraftProject(DraftProjectEntity draftProjRegClassifDraftProject) {
        this.draftProjRegClassifDraftProject = draftProjRegClassifDraftProject;
    }

    public RegionalClassifierEntity getDraftProjectRegionalClassifierRegClassif() {
        return draftProjectRegionalClassifierRegClassif;
    }

    public void setDraftProjectRegionalClassifierRegClassif(RegionalClassifierEntity draftProjectRegionalClassifierRegClassif) {
        this.draftProjectRegionalClassifierRegClassif = draftProjectRegionalClassifierRegClassif;
    }
    
}