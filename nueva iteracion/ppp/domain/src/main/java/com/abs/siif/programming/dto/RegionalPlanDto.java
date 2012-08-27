/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalPlanDto
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dto;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import java.io.Serializable;

/**
 *
 * @author Erick Leija
 */
public class RegionalPlanDto implements Serializable
{
    private boolean itsSelectedActive;
    private Long regionalPlanId;
    private RegionalClassifierEntity regionalClassifierPlan;
    private String regionalPlanObjective;

    public RegionalPlanDto(boolean itsSelectedActive, 
            Long regionalPlanId, 
            RegionalClassifierEntity regionalClassifierPlan, 
            String regionalPlanObjective) 
    {
        this.itsSelectedActive = itsSelectedActive;
        this.regionalPlanId = regionalPlanId;
        this.regionalClassifierPlan = regionalClassifierPlan;
        this.regionalPlanObjective = regionalPlanObjective;
    }
    
    
    
    

    public boolean isItsSelectedActive() {
        return itsSelectedActive;
    }

    public void setItsSelectedActive(boolean itsSelectedActive) {
        this.itsSelectedActive = itsSelectedActive;
    }

    public RegionalClassifierEntity getRegionalClassifierPlan() {
        return regionalClassifierPlan;
    }

    public void setRegionalClassifierPlan(RegionalClassifierEntity regionalClassifierPlan) {
        this.regionalClassifierPlan = regionalClassifierPlan;
    }

    public Long getRegionalPlanId() {
        return regionalPlanId;
    }

    public void setRegionalPlanId(Long regionalPlanId) {
        this.regionalPlanId = regionalPlanId;
    }

    public String getRegionalPlanObjective() {
        return regionalPlanObjective;
    }

    public void setRegionalPlanObjective(String regionalPlanObjective) {
        this.regionalPlanObjective = regionalPlanObjective;
    }
    
    
    
}
