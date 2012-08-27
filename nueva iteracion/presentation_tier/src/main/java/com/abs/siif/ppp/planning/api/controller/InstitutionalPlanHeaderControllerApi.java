/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  InstitutionalPlanHeaderControllerApi
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.api.controller;

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import java.util.List;

/**
 *
 * @author luis.carreon
 */
public interface InstitutionalPlanHeaderControllerApi {

    void SaveOrUpdateAnInstitutionalPlan();

    void executePopulation();

    String getADepdenceName();

    Long getADependeceId();

    Long getAnInstitutionalPlanId();

    Long getAnInstitutionalPlanToDeleteId();

    DependenceEntity getFirstDependenceOfList();

    List<InstitutionalPlanEntity> getItsListOfInstitutionalPlans();

    List<DependenceEntity> getListOfDependences();

    void init();

    boolean isEnabledNewButton();

    boolean isItsNewInstitutionalPlan();

    void makeSettersAndGetters();

    void setADepdenceName(String aDepdenceName);

    void setADependeceId(Long aDependeceId);

    void setAnInstitutionalPlanId(Long anInstitutionalPlanId);

    void setAnInstitutionalPlanToDeleteId(Long anInstitutionalPlanToDeleteId);

    void setEnabledNewButton(boolean enabledNewButton);

    void setItsListOfInstitutionalPlans(List<InstitutionalPlanEntity> itsListOfInstitutionalPlans);

    void setItsNewInstitutionalPlan(boolean itsNewInstitutionalPlan);
    
}
