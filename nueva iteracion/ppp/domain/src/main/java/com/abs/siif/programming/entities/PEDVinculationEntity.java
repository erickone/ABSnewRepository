/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  PEDVinculationEntity
 *  Purpose:  [ esta entidad guaradara la información del encuadre que el
 *  usuario seleccionara, y que sera persitida cada vez que el usuario desee
 *  hacer un cambio]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.entities;

import com.abs.siif.planning.entities.InstitutionalPlanObjectiveEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.planning.entities.ProblemEntity;
import com.abs.siif.planning.entities.SpecificObjectiveEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 */
@Entity
@Table(name = "siifpppvinculacionPED")
public class PEDVinculationEntity implements 
        Serializable, Comparable<PEDVinculationEntity>
{
    
     @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdVinculacionPED", nullable = false)
    private Long vinculationId;
    
    @ManyToOne
    @JoinColumn(name="IdAnteProyecto",nullable = true)
    private DraftProjectEntity draftProjectId;
     
    @ManyToOne
    @JoinColumn(name = "IdEjeEstrategico", nullable = true)
    private ObjectiveEntity vinculationAxis;
    
    @ManyToOne
    @JoinColumn(name = "IdPrograma", nullable = true)
    private ObjectiveEntity vinculationProgram;
    
    @ManyToOne
    @JoinColumn(name = "IdSubprograma", nullable = true)
    private ObjectiveEntity vinculationSubProgram;
    
    @OneToOne
    @JoinColumn(name="IdProblema",nullable = true)
    private ProblemEntity vinculationProblem;
    
    @OneToOne
    @JoinColumn(name="IdObjEspecifico",nullable = true)
    private SpecificObjectiveEntity vinculationSpecificObjective;

    @OneToOne
    @JoinColumn(name="idobjetivopi")
    private InstitutionalPlanObjectiveEntity vinculationObjetivoPI;
            
    public DraftProjectEntity getDraftProjectId() {
        return draftProjectId;
    }

    public void setDraftProjectId(DraftProjectEntity draftProjectId) {
        this.draftProjectId = draftProjectId;
    }

    public ObjectiveEntity getVinculationAxis() {
        return vinculationAxis;
    }

    public void setVinculationAxis(ObjectiveEntity vinculationAxis) {
        this.vinculationAxis = vinculationAxis;
    }

    public Long getVinculationId() {
        return vinculationId;
    }

    public void setVinculationId(Long vinculationId) {
        this.vinculationId = vinculationId;
    }

    public ProblemEntity getVinculationProblem() {
        return vinculationProblem;
    }

    public void setVinculationProblem(ProblemEntity vinculationProblem) {
        this.vinculationProblem = vinculationProblem;
    }

    public ObjectiveEntity getVinculationProgram() {
        return vinculationProgram;
    }

    public void setVinculationProgram(ObjectiveEntity vinculationProgram) {
        this.vinculationProgram = vinculationProgram;
    }

    public SpecificObjectiveEntity getVinculationSpecificObjective() {
        return vinculationSpecificObjective;
    }

    public void setVinculationSpecificObjective
            (SpecificObjectiveEntity vinculationSpecificObjective) 
    {
        this.vinculationSpecificObjective = vinculationSpecificObjective;
    }

    public ObjectiveEntity getVinculationSubProgram() {
        return vinculationSubProgram;
    }

    public void setVinculationSubProgram(ObjectiveEntity vinculationSubProgram) 
    {
        this.vinculationSubProgram = vinculationSubProgram;
    }

     @Override
    public int compareTo(PEDVinculationEntity anOtherEntity) 
     {
        int result = -1;
        if (this.vinculationId != null
                && anOtherEntity.vinculationId != null) {
            result = 
          this.vinculationId.compareTo(anOtherEntity.getVinculationId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof PEDVinculationEntity && this.vinculationId != null
                && ((PEDVinculationEntity) obj).vinculationId != null) {
            result = this.vinculationId.equals(
                    ((PEDVinculationEntity) obj).vinculationId);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.vinculationId != null
                ? this.vinculationId.hashCode() : 0);
        return hash;
    }

    /**
     * @return the vinculationObjetivoPI
     */
    public InstitutionalPlanObjectiveEntity getVinculationObjetivoPI() {
        return vinculationObjetivoPI;
    }

    /**
     * @param vinculationObjetivoPI the vinculationObjetivoPI to set
     */
    public void setVinculationObjetivoPI(InstitutionalPlanObjectiveEntity vinculationObjetivoPI) {
        this.vinculationObjetivoPI = vinculationObjetivoPI;
    }
    
   
}
