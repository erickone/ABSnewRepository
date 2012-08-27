/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  PedVinculationControllerApi
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

import com.abs.siif.planning.entities.*;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.ppp.programming.uihelpers.RegionalDataModel;
import com.abs.siif.ppp.programming.uihelpers.RegionalPlanDataModel;
import com.abs.siif.programming.dto.RegionalClassifierPEDDto;
import com.abs.siif.programming.dto.RegionalPlanDto;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

/**
 *
 * @author luis.carreon
 */
public interface PedVinculationControllerApi {

    void LoadSpecificObjectives();

    void chargeSaveObjectOfPED();

    Long getAProblemId();

    Long getASpecificObId();

    String getAindicatorId();

    Long getAnIndicatorOfPI();

    Long getAnObjectiveOfPI();

    ObjectiveJoinLevelTreeviewDto getItsAxisSelected();

    List<InstitutionalPlanObjectiveEntity> getItsListOfObjectivesofPI();

    List<InstitutionalPlanEntity> getItsListOfPI();

    List<ProblemEntity> getItsListOfProblems();

    List<RegionalClassifierPEDDto> getItsListOfRegionalDto();

    List<RegionalPlanDto> getItsListOfRegionalPlanDto();

    /**
     * este metodo crea un lista dummy para mostrar las regiones que existen en
     * la entidad del encuadre del Plan Regional
     *
     * @return
     */
    List<RegionalClassifierEntity> getItsListOfRegions();

    List<SpecificObjectiveEntity> getItsListOfSpecificObjectives();

    Long getItsProgramId();

    ObjectiveJoinLevelTreeviewDto getItsProgramSelected();

    String getItsPurposePED();

    TreeNode getItsSelectedNode();

    String getItsStrategicAxisDefinition();

    String getItsStrategicAxisKey();

    String getItsStrategicAxisName();

    String getItsStrategicAxisPED();

    Long getItsSubProgramId();

    ObjectiveJoinLevelTreeviewDto getItsSubProgramSelected();

    List<ObjectiveIndicatorEntity> getMyListOfIndicatorsofSP();

    List<RegionalPlanEntity> getMyListOfObjectivesPED();

    List<ObjectiveJoinLevelTreeviewDto> getMyProgramsNodesObjectives();

    RegionalDataModel getMyRegionalDataModel();

    RegionalPlanDataModel getMyRegionalPlanDataModel();

    List<ObjectiveJoinLevelTreeviewDto> getMyRootNodesObjectives();

    List<ObjectiveJoinLevelTreeviewDto> getMySubProgramsNodesObjectives();

    TreeNode getRoot();

    List<Long> getSelectedPlanRow();

    RegionalClassifierPEDDto getSelectedRow();

    void init();

    boolean getBtnSrvSave();

    void loadSubPrograms();

    void saveListOfObjectives();

    /**
     * Este metodo se encarga de guarda la selecciÃ³n del problema y del
     * Objetivo Especifico, que el usuario captura en pantalla
     */
    void savePEDVinculation();

    void setAProblemId(Long aProblemId);

    void setASpecificObId(Long aSpecificObId);

    void setAindicatorId(String aIndicatorId);

    void setAnIndicatorOfPI(Long anIndicatorOfPI);

    void setAnObjectiveOfPI(Long anObjectiveOfPI);

    void setItsAxisSelected(ObjectiveJoinLevelTreeviewDto itsAxisSelected);

    void setBtnSrvSave(boolean itsBtnSrvSave);

    void setItsListOfObjectivesofPI(List<InstitutionalPlanObjectiveEntity> itsListOfObjectivesofPI);

    void setItsListOfPI(List<InstitutionalPlanEntity> itsListOfPI);

    void setItsListOfProblems(List<ProblemEntity> itsListOfProblems);

    void setItsListOfRegionalDto(List<RegionalClassifierPEDDto> itsListOfRegionalDto);

    void setItsListOfRegionalPlanDto(List<RegionalPlanDto> itsListOfRegionalPlanDto);

    void setItsListOfRegions(List<RegionalClassifierEntity> itsListOfRegions);

    void setItsListOfSpecificObjectives(List<SpecificObjectiveEntity> itsListOfSpecificObjectives);

    void setItsProgramId(Long itsProgramId);

    void setItsProgramSelected(ObjectiveJoinLevelTreeviewDto itsProgramSelected);

    void setItsPurposePED(String itsPurposePED);

    void setItsSelectedNode(TreeNode itsSelectedNode);

    void setItsStrategicAxisDefinition(String itsStrategicAxisDefinition);

    void setItsStrategicAxisKey(String itsStrategicAxisKey);

    void setItsStrategicAxisName(String itsStrategicAxisName);

    void setItsStrategicAxisPED(String itsStrategicAxisPED);

    void setItsSubProgramId(Long itsSubProgramId);

    void setItsSubProgramSelected(ObjectiveJoinLevelTreeviewDto itsSubProgramSelected);

    void setMyListOfIndicatorsofSP(List<ObjectiveIndicatorEntity> myListOfIndicatorsofSP);

    void setMyListOfObjectivesPED(List<RegionalPlanEntity> myListOfObjectivesPED);

    void setMyProgramsNodesObjectives(List<ObjectiveJoinLevelTreeviewDto> myProgramsNodesObjectives);

    void setMyRegionalPlanDataModel(RegionalPlanDataModel myRegionalPlanDataModel);

    void setMyRootNodesObjectives(List<ObjectiveJoinLevelTreeviewDto> myRootNodesObjectives);

    void setMySubProgramsNodesObjectives(List<ObjectiveJoinLevelTreeviewDto> mySubProgramsNodesObjectives);

    void setSelectedPlanRow(List<Long> SelectedPlanRow);

    void setSelectedRow(RegionalClassifierPEDDto selectedRow);

    void updateListOfObjectives(SelectEvent event);
     /**
     * @return the btnSrvSaveRegion
     */
    boolean isBtnSrvSaveRegion();
    /**
     * @param btnSrvSaveRegion the btnSrvSaveRegion to set
     */
    void setBtnSrvSaveRegion(boolean btnSrvSaveRegion); 
}
