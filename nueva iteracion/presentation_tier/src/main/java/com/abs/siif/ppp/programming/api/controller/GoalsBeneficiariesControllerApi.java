/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  GoalsBeneficiariesControllerApi
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.api.controller;

import com.abs.siif.programming.entities.ActionGBEntity;
import com.abs.siif.programming.entities.BuildingConceptEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author luis.carreon
 */
public interface GoalsBeneficiariesControllerApi {

    /**
     * evgento cuando se cambia la seleccion anualo multianual
     */
    void changeOptGoals();

    void cleanView();

    boolean getDisableMultGoal();

    boolean getDisableYearGoal();

    Long getInvPreFileId();

    List<ActionGBEntity> getTheirActions();

    List<BuildingConceptEntity> getTheirBuildingConcepts();

    Long getTheirCurrentId();

    String getTheirDirects();

    String getTheirGoalAnnual();

    String getTheirIndirects();

    Long getTheirSelectedActionId();

    Long getTheirSelectedBuildingConceptId();

    String getTheirSelectedExecutionTime();

    Long getTheirSelectedGeneralConceptId();

    Long getTheirSelectedInvestmentLineId();

    Long getTheirSelectedMacroBuild();

    Long getTheirSelectedUnitBenefAndGoalId();

    Long getTheirSelectedUnitGoalId();

    Long getTheirSelectedVulnerableGroupId();

    Map<String, List<?>> getTheirSupportList();

    String getTheirTotalGoal();

    void initSupportList();

    void loadActionsGB();

    void loadBuidingConceptsByEdit();

    void loadBuildingConcepts();

    void persistGoalAndBenef();

    void setDisableMultGoal(boolean disableMultGoal);

    void setDisableYearGoal(boolean disableYearGoal);

    void setInvPreFileId(Long InvPreFileId);

    void setTheirActions(List<ActionGBEntity> theirActions);

    void setTheirBuildingConcepts(List<BuildingConceptEntity> theirBuildingConcepts);

    void setTheirCurrentId(Long theirCurrentId);

    void setTheirDirects(String theirDirects);

    void setTheirGoalAnnual(String theirGoalAnnual);

    void setTheirIndirects(String theirIndirects);

    void setTheirSelectedActionId(Long theirSelectedActionId);

    void setTheirSelectedBuildingConceptId(Long theirSelectedBuildingConceptId);

    void setTheirSelectedExecutionTime(String theirSelectedExecutionTime);

    void setTheirSelectedGeneralConceptId(Long theirSelectedGeneralConceptId);

    void setTheirSelectedInvestmentLineId(Long theirSelectedInvestmentLineId);

    void setTheirSelectedMacroBuild(Long theirSelectedMacroBuild);

    void setTheirSelectedUnitBenefAndGoalId(Long theirSelectedUnitBenefAndGoalId);

    void setTheirSelectedUnitGoalId(Long theirSelectedUnitGoalId);

    void setTheirSelectedVulnerableGroupId(Long theirSelectedVulnerableGroupId);

    void setTheirSupportList(Map<String, List<?>> theirSupportList);

    void setTheirTotalGoal(String theirTotalGoal);
    
}
