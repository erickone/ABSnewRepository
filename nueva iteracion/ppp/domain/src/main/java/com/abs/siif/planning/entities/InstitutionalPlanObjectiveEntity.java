/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  InstitutionalPlanObjectiveEntity
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Francisco Luna
 * 
 * Esta entidad almacena los diferentes objetivos que puede contener una Planeación Institucional del
 * Clasificador Administrativo designada para ello.
 * 
 */
@Entity
@Table(name = "siifpppobjplaninst")
public class InstitutionalPlanObjectiveEntity implements 
        Comparable<InstitutionalPlanObjectiveEntity>, Serializable{
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idobjplaninst", nullable = false)
    private Long institutionalPlanObjectiveId;
    @ManyToOne
    @JoinColumn(name = "IdPlanInstitucional")
    private InstitutionalPlanEntity institutionalPlanEntity;
    @Column(name = "objgeneral", length = 255, nullable = false)
    private String objectiveDescription;

    public InstitutionalPlanEntity getInstitutionalPlanEntity() {
        return institutionalPlanEntity;
    }

    public void setInstitutionalPlanEntity(InstitutionalPlanEntity institutionalPlanEntity) {
        this.institutionalPlanEntity = institutionalPlanEntity;
    }

    public String getObjectiveDescription() {
        return objectiveDescription != null ? objectiveDescription.trim() : objectiveDescription;
    }

    public void setObjectiveDescription(String objectiveDescription) {
        this.objectiveDescription = objectiveDescription;
    }

    public Long getInstitutionalPlanObjectiveId() {
        return institutionalPlanObjectiveId;
    }

    public void setInstitutionalPlanObjectiveId
            (Long institutionalPlanObjectiveId) {
        this.institutionalPlanObjectiveId = institutionalPlanObjectiveId;
    }

    @Override
    public int compareTo(InstitutionalPlanObjectiveEntity obj) {
        int result = -1;
        if (getInstitutionalPlanObjectiveId() != null 
                && obj.getInstitutionalPlanObjectiveId() != null) {
            result = this.getInstitutionalPlanObjectiveId()
                    .compareTo(obj.getInstitutionalPlanObjectiveId());
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof InstitutionalPlanObjectiveEntity 
                && getInstitutionalPlanObjectiveId() != null
                && ((InstitutionalPlanObjectiveEntity) obj)
                    .getInstitutionalPlanObjectiveId() != null) {
            
            result = getInstitutionalPlanObjectiveId().equals(
                    ((InstitutionalPlanObjectiveEntity) obj)
                        .getInstitutionalPlanObjectiveId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (getInstitutionalPlanObjectiveId() != null
                ? getInstitutionalPlanObjectiveId().hashCode() : 0);
        return hash;
    }
    
}
