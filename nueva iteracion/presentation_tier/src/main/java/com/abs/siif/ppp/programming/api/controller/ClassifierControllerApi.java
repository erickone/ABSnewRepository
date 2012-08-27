/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  ClassifierControllerApi
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

import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.BranchEntity;
import com.abs.siif.planning.entities.SectorEntity;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.ppp.programming.uihelpers.TreeViewAdministrativeClassifierUIHelper;
import com.abs.siif.programming.entities.ClassifierEntity;
import java.util.List;
import org.primefaces.model.TreeNode;

/**
 *
 * @author luis.carreon
 */
public interface ClassifierControllerApi {

    Long getItsBranchId();

    Long getItsBudgetUnitId();

    ClassifierEntity getItsClassifierEntity();

    Long getItsExpenditureImplementationUnitId();

    Long getItsFinalityId();

    Long getItsFunctionId();

    List<DepencenceDto> getItsListOfBudgeUnit();

    List<DepencenceDto> getItsListOfExpenditureImplementationUnit();

    List<ObjectiveJoinLevelTreeviewDto> getItsListOfFinalities();

    List<ObjectiveJoinLevelTreeviewDto> getItsListOfFunctions();

    List<DepencenceDto> getItsListOfResponsibleUnit();

    List<SectorEntity> getItsListOfSectorObjectives();

    List<ObjectiveJoinLevelTreeviewDto> getItsListOfSubFunctions();

    Long getItsResponsibleUnitId();

    Long getItsSectorId();

    TreeNode getItsSelectedNodeAdministrativeClassifier();

    TreeNode getItsSelectedNodeFunctionalClassifier();

    Long getItsSubFunctionId();

    TreeViewAdministrativeClassifierUIHelper getItsTreeViewAdministrativeClassifier();

    List<BranchEntity> getMyRootNodesAdminObjectives();

    TreeNode getRootAdministrativeClassifier();

    TreeNode getRootFunctionalClassifier();

    Long getTheirCurrentId();

    void init();

    /**
     * Este Metodo sirve para cargar los combos del Administrador Administrativo
     */
    void loadAdministrativeClassifier();

    List<ObjectiveJoinLevelTreeviewDto> loadCombos(Long aFatherId, List<ObjectiveJoinLevelTreeviewDto> aHierarchicalList);
    /**
     * Este Metodo sirve para cargar los combos del Clasificador Funcional
     */
    void loadFunctionalClassifier(Long aDraftProjectId);

    void loadFunctions();

    void loadSubFunction();

    void saveClassif();

    void setItsBranchId(Long itsBranchId);

    void setItsBudgetUnitId(Long itsBudgetUnitId);

    void setItsClassifierEntity(ClassifierEntity itsClassifierEntity);

    void setItsExpenditureImplementationUnitId(Long itsExpenditureImplementationUnitId);

    void setItsFinalityId(Long itsFinalityId);

    void setItsFunctionId(Long itsFunctionId);

    void setItsListOfBudgeUnit(List<DepencenceDto> itsListOfBudgeUnit);

    void setItsListOfExpenditureImplementationUnit(List<DepencenceDto> itsListOfExpenditureImplementationUnit);

    void setItsListOfFinalities(List<ObjectiveJoinLevelTreeviewDto> itsListOfFinalities);

    void setItsListOfFunctions(List<ObjectiveJoinLevelTreeviewDto> itsListOfFunctions);

    void setItsListOfResponsibleUnit(List<DepencenceDto> itsListOfResponsibleUnit);

    void setItsListOfSectorObjectives(List<SectorEntity> itsListOfSectorObjectives);

    void setItsListOfSubFunctions(List<ObjectiveJoinLevelTreeviewDto> itsListOfSubFunctions);

    void setItsResponsibleUnitId(Long itsResponsibleUnitId);

    void setItsSectorId(Long itsSectorId);

    void setItsSelectedNodeAdministrativeClassifier(TreeNode itsSelectedNodeAdministrativeClassifier);

    void setItsSelectedNodeFunctionalClassifier(TreeNode itsSelectedNodeFunctionalClassifier);

    void setItsSubFunctionId(Long itsSubFunctionId);

    void setItsTreeViewAdministrativeClassifier(TreeViewAdministrativeClassifierUIHelper itsTreeViewAdministrativeClassifier);

    void setMyRootNodesAdminObjectives(List<BranchEntity> myRootNodesAdminObjectives);

    void setRootFunctionalClassifier(TreeNode RootFunctionalClassifier);

    void setTheirCurrentId(Long theirCurrentId);
    
    

}
