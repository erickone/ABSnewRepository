/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalClassifierPEDDto
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

import java.io.Serializable;

/**
 *
 * @author Erick Leija
 */
public class RegionalClassifierPEDDto implements Serializable
{
    private boolean itsSelectedRegionalPlans;
    private Long regionalClassifierId;
    private String regionalClassifierDescription;

    public RegionalClassifierPEDDto(boolean itsSelectedRegionalPlans, 
            Long regionalClassifierId, 
            String regionalClassifierDescription) 
    {
        this.itsSelectedRegionalPlans = itsSelectedRegionalPlans;
        this.regionalClassifierId = regionalClassifierId;
        this.regionalClassifierDescription = regionalClassifierDescription;
    }
    
    public boolean isItsSelectedRegionalPlans() {
        return itsSelectedRegionalPlans;
    }

    public void setItsSelectedRegionalPlans(boolean itsSelectedRegionalPlans) {
        this.itsSelectedRegionalPlans = itsSelectedRegionalPlans;
    }

    public String getRegionalClassifierDescription() {
        return regionalClassifierDescription;
    }

    public void setRegionalClassifierDescription(String regionalClassifierDescription) {
        this.regionalClassifierDescription = regionalClassifierDescription;
    }

    public Long getRegionalClassifierId() {
        return regionalClassifierId;
    }

    public void setRegionalClassifierId(Long regionalClassifierId) {
        this.regionalClassifierId = regionalClassifierId;
    }    
    
}
