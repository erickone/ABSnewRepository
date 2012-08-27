/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  ObjectiveControllerApi
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

import com.abs.siif.common.dto.SampleEntityDto;
import com.abs.siif.planning.entities.StrategyEntity;
import com.abs.siif.ppp.planning.controller.OptionsController;
import com.abs.siif.ppp.planning.exception.ObjectiveException;
import com.abs.siif.ppp.planning.exception.SpecificObjectiveException;
import java.util.List;
import org.primefaces.model.TreeNode;

/**
 *
 * @author luis.carreon
 */
public interface ObjectiveControllerApi {

    void cancelProblem() throws ObjectiveException;

    void cancelSpecificObjective() throws ObjectiveException;

    void cancelStrategy() throws ObjectiveException;

    void deleteObjective();

    Boolean getItsButtonNewProblemIsDisabled();

    Boolean getItsButtonNewSpecificObjectiveIsDisabled();

    Boolean getItsButtonNewStrategyIsDisabled();

    Boolean getItsButtonSaveIsDisabled();

    Boolean getItsButtonSaveProblemIsDisabled();

    Boolean getItsButtonSaveStrategyIsDisabled();

    String getItsDescriptionFather();

    String getItsObjectiveDefinition();

    String getItsObjectiveLevelDescription();

    String getItsObjectiveName();

    short getItsObjectivePriority();

    String getItsProblemDescription();

    List<SampleEntityDto> getItsProblems();

    SampleEntityDto getItsSelectedDeleteProblem();

    SampleEntityDto getItsSelectedDeleteSpecificObjective();

    TreeNode getItsSelectedNode();

    SampleEntityDto getItsSelectedProblem();

    SampleEntityDto getItsSelectedSpecificObjective();

    String getItsSelectedSpecificObjectiveId();

    String getItsSelectedSpecificObjectiveIdForStrategy();

    SampleEntityDto getItsSelectedStrategy();

    String getItsSelectedStrategyIdForDelete();

    String getItsSpecificObjectiveDescription();

    List<SampleEntityDto> getItsSpecificObjectives();

    List<StrategyEntity> getItsStrategies();

    List<SampleEntityDto> getItsStrategiesBySpecificObjective();

    String getItsStrategyDescription();

    OptionsController getOptionsCopntroller();

    TreeNode getRoot();

    boolean isIsEncuadreButtonDisabled();

    boolean isItsTabViewDisabled();

    String navigateDepToObjLink();

    void populateStrategies() throws ObjectiveException;

    void prepareEditObjective() throws ObjectiveException;

    void prepareNewObjective() throws ObjectiveException;

    void prepareNewProblemData() throws ObjectiveException;

    void prepareNewSpecificObjectiveData() throws ObjectiveException;

    void prepareNewStrategyData() throws ObjectiveException;

    void saveObjective() throws ObjectiveException;

    void saveProblem() throws ObjectiveException, SpecificObjectiveException;

    void saveSpecificObjective() throws SpecificObjectiveException, ObjectiveException;

    void saveStrategy() throws ObjectiveException;

    void setIsEncuadreButtonDisabled(boolean flag);

    void setItsObjectiveDefinition(String itsObjectiveDefinition);

    void setItsObjectiveLevelDescription(String itsObjectiveLevelDescription);

    void setItsObjectiveName(String itsObjectiveName);

    void setItsObjectivePriority(short itsObjectivePriority);

    void setItsProblemDescription(String itsProblemDescription);

    void setItsSelectedDeleteProblem(SampleEntityDto itsSelectedDeleteProblem) throws SpecificObjectiveException;

    void setItsSelectedDeleteSpecificObjective(SampleEntityDto itsSelectedDeleteSpecificObjective) throws SpecificObjectiveException;

    void setItsSelectedNode(TreeNode itsSelectedNode);

    void setItsSelectedProblem(SampleEntityDto itsSelectedProblem) throws ObjectiveException;

    void setItsSelectedSpecificObjective(SampleEntityDto itsSelectedSpecificObjective) throws ObjectiveException;

    void setItsSelectedSpecificObjectiveId(String itsSelectedSpecificObjectiveId);

    void setItsSelectedSpecificObjectiveIdForStrategy(String itsSelectedSpecificObjectiveIdForStrategy) throws ObjectiveException;

    void setItsSelectedStrategy(SampleEntityDto itsSelectedStrategy) throws ObjectiveException;

    void setItsSelectedStrategyIdForDelete(String itsSelectedStrategyIdForDelete);

    void setItsSpecificObjectiveDescription(String itsSpecificObjectiveDescription);

    void setItsStrategiesBySpecificObjective(List<SampleEntityDto> itsStrategiesBySpecificObjective);

    void setItsStrategyDescription(String itsStrategyDescription);

    void setItsTabViewDisabled(boolean itsTabViewDisabled);

    void setOptionsController(OptionsController optionsController);
   
    String getItsObjectiveKey(); 
    
    void setItsObjectiveKey(String aObjectiveKey);
}
