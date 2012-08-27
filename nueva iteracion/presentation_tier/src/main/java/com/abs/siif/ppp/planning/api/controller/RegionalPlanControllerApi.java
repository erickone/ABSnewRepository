/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  RegionalPlanControllerApi
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

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalPlanEntity;
import com.abs.siif.ppp.planning.uihelpers.RegionalPlanDataModel;
import java.util.Collection;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author luis.carreon
 */
public interface RegionalPlanControllerApi {

    void cancelPersist();

    void deleteRegionalPlans();

    /**
     * @return the theirRegionalObjectiveDescription
     */
    String getRegionalObjectiveDescription();

    /**
     * @return the theirRegionsList
     */
    Collection<RegionalClassifierEntity> getRegionsList();

    Long getTheirRegionId();

    RegionalPlanDataModel getTheirRegionalDataModel();

    RegionalPlanEntity[] getTheirSelectedPlans();

    boolean isTheirInputsDisabled();

    void loadRegionalPlanByRegionalClassifier();

    void onRowSelect(SelectEvent event);

    void persistRegionalPlan();

    void prepareNewEntity();

    /**
     * @param theirRegionalObjectiveDescription the
     * theirRegionalObjectiveDescription to set
     */
    void setRegionalObjectiveDescription(String theirRegionalObjectiveDescription);

    /**
     * @param theirRegionsList the theirRegionsList to set
     */
    void setRegionsList(Collection<RegionalClassifierEntity> aRegionsList);

    void setTheirInputsDisabled(boolean theirInputsDisabled);

    void setTheirRegionId(Long theirRegionId);

    void setTheirSelectedPlans(RegionalPlanEntity[] theirSelectedPlans);
    
}
