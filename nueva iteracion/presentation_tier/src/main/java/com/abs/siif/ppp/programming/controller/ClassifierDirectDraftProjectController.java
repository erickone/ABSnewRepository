/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  ClassifierDirectDraftProjectController
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.BranchEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import com.abs.siif.planning.entities.SectorEntity;
import com.abs.siif.ppp.programming.api.controller.ClassifierControllerApi;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.ppp.programming.uihelpers.ClassifierUIHelper;
import com.abs.siif.ppp.programming.uihelpers.ComparatorFatherAndChild;
import com.abs.siif.ppp.programming.uihelpers.TreeViewAdministrativeClassifierUIHelper;
import com.abs.siif.ppp.programming.uihelpers.TreeviewFunctionalClassifierUIHelper;
import com.abs.siif.programming.dto.ClasificationDTO;
import com.abs.siif.programming.entities.ClassifierEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.management.AdministrativeClassifierManagement;
import com.abs.siif.programming.management.ClassifierManagement;
import com.abs.siif.programming.management.DirectCaptureDraftProject;
import com.abs.siif.planning.management.FunctionalClassifierManagement;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Jorge Urrea
 */
@Scope("session")
@Controller("classifierDirectDraftProjectController")
public class ClassifierDirectDraftProjectController extends SIIFControllerBase implements Serializable, ClassifierControllerApi{
    //@Resource(name = "programminMainController")
    //private transient ProgrammingMainController myProgrammingMainController;
    //@Resource(name = "draftProjectHeaderController")
    //private transient DraftProjectHeaderController mydraftProjectHeaderController;
    @Resource(name = "directDraftProjectController")
    //private transient DirectDraftProjectHeaderController mydraftProjectHeaderController;
    private transient DirectDraftProjectController mydraftProjectHeaderController;
     @Resource(name = "directCaptureDraftProject")
    private transient DirectCaptureDraftProject myDirectCaptureDraftProjectManagement;
    @Resource(name = "administrativeClassifierManagement")
    private transient AdministrativeClassifierManagement myAdministrativeClassifierManagement;
    @Resource(name = "functionalClassifierManagement")
    private transient FunctionalClassifierManagement myFunctionalClassifierManagement;
    @Resource(name = "treeViewAdministrativeClassifier")
    private transient TreeViewAdministrativeClassifierUIHelper theirTreeViewAdministrativeClassifier;
    @Resource(name = "classifierManagement")
    private transient ClassifierManagement itsClassifierManagement;
    private transient ClassifierUIHelper itsClassifierUIHelper = new ClassifierUIHelper();
    private transient ClassifierEntity itsClassifierEntity = new ClassifierEntity();
    //Estas son las inyecciones de los controllers donde se saca la información
    //de la Dependencia y el objetivo especifico
    private DirectDraftProjectController theirdraftProjectHeaderController;
    //private ProgrammingMainController theirProgrammingMainController;
    private AdministrativeClassifierManagement theirAdministrativeClassifierManagement;
    private FunctionalClassifierManagement theirFunctionalClassifierManagement;
    //Listas de de elementos jerarquizados
    private List<ObjectiveJoinLevelTreeviewDto> itsListHierarchicalAdministrative;
    private List<ObjectiveJoinLevelTreeviewDto> itsListHierarchicalFunctional;
    //Listas de Clasificador Administrativo
    private List<ObjectiveJoinLevelTreeviewDto> myRootNodesAdminObjectives;
    private List<DepencenceDto> rootNodesAdminObjectives;
    private List<ObjectiveJoinLevelTreeviewDto> itsListOfSectorObjectives;
    private List<DepencenceDto> listOfSectorObjectives;
    private List<ObjectiveJoinLevelTreeviewDto> itsListOfBudgeUnit;
    private List<DepencenceDto> listOfBudgeUnit;
    private List<ObjectiveJoinLevelTreeviewDto> itsListOfResponsibleUnit;
    private List<DepencenceDto> listOfResponsibleUnit;
    private List<ObjectiveJoinLevelTreeviewDto> itsListOfExpenditureImplementationUnit;
    private List<DepencenceDto> listOfExpenditureImplementationUnit;
    //Listas de Clasificador Funcional
    private List<ObjectiveJoinLevelTreeviewDto> itsListOfFinalities;
    private List<ObjectiveJoinLevelTreeviewDto> itsListOfFunctions;
    private List<ObjectiveJoinLevelTreeviewDto> itsListOfSubFunctions;
    @Resource
    private transient TreeviewFunctionalClassifierUIHelper theirTreeviewFunctionalClassifierUIHelper;
    private String itsDependenceId;
    private Long itsObjectiveId;
    //Id's de Clasificador Administrativo
    private Long itsBranchId;
    private Long itsSectorId;
    private Long itsBudgetUnitId;
    private Long itsResponsibleUnitId;
    private Long itsExpenditureImplementationUnitId;
    //Id's de Clasificador Funcional
    private Long itsFinalityId;
    private Long itsFunctionId;
    private Long itsSubFunctionId;
    private TreeNode itsSelectedNodeAdministrativeClassifier;
    private TreeNode RootAdministrativeClassifier;
    private TreeNode itsSelectedNodeFunctionalClassifier;
    private TreeNode RootFunctionalClassifier;
    private Long theirCurrentId;
    private Long dependenceId;

    @Override
    public Long getTheirCurrentId() {
        return theirCurrentId;
    }

    @Override
    public void setTheirCurrentId(Long theirCurrentId) {
        this.theirCurrentId = theirCurrentId;
    }

    public List<DepencenceDto> getListOfExpenditureImplementationUnit() {
        return listOfExpenditureImplementationUnit;
    }

    public void setListOfExpenditureImplementationUnit(List<DepencenceDto> listOfExpenditureImplementationUnit) {
        this.listOfExpenditureImplementationUnit = listOfExpenditureImplementationUnit;
    }

    public List<DepencenceDto> getRootNodesAdminObjectives() {
        return rootNodesAdminObjectives;
    }

    public void setRootNodesAdminObjectives(List<DepencenceDto> rootNodesAdminObjectives) {
        this.rootNodesAdminObjectives = rootNodesAdminObjectives;
    }

    public List<DepencenceDto> getListOfSectorObjectives() {
        return listOfSectorObjectives;
    }

    public void setListOfSectorObjectives(List<DepencenceDto> listOfSectorObjectives) {
        this.listOfSectorObjectives = listOfSectorObjectives;
    }
    
    @Override
    public void init() {      
        //String myCurrentDraftProjectId = "";//mydraftProjectHeaderController.getTheirCurrentDraftProjectId();
        theirCurrentId = 0L;
        itsListOfFinalities = new ArrayList<ObjectiveJoinLevelTreeviewDto>();
        itsListOfFunctions = new ArrayList<ObjectiveJoinLevelTreeviewDto>();
        itsListOfSubFunctions = new ArrayList<ObjectiveJoinLevelTreeviewDto>();
        //this.theirProgrammingMainController = myProgrammingMainController;
        this.theirdraftProjectHeaderController = mydraftProjectHeaderController;
        this.theirAdministrativeClassifierManagement = myAdministrativeClassifierManagement;
        this.theirFunctionalClassifierManagement = myFunctionalClassifierManagement;
        this.itsDependenceId = theirdraftProjectHeaderController.getItsResponsibleUnitId();
        DependenceEntity dep = new DependenceEntity();       
        dep.setDependenceId(Long.parseLong(this.itsDependenceId));
        this.dependenceId = myDirectCaptureDraftProjectManagement.GetUEGbyUR(dep);
        loadAdministrativeAndFunctionalClassifier();
        //mydraftProjectHeaderController.setItsSelectedUEG(this.itsExpenditureImplementationUnitId);
    }

    private void loadAdministrativeAndFunctionalClassifier() {
        loadAdministrativeClassifier();
        //loadFunctionalClassifier(aDraftProjectId);
    }

    /**
     * Este Metodo sirve para cargar los combos del Administrador Administrativo
     */
    @Override
    public void loadAdministrativeClassifier() {
        /*this.itsDependenceId = theirdraftProjectHeaderController.getItsResponsibleUnitId();
        DependenceEntity dep = new DependenceEntity();
        dep.setDependenceId(Long.parseLong(itsDependenceId));*/
        ClasificationDTO clasificationDTO = myDirectCaptureDraftProjectManagement.getCatalogs(this.dependenceId);
        listOfBudgeUnit = clasificationDTO.getBudgetUnitList();
        listOfExpenditureImplementationUnit = clasificationDTO.getExecuteUnitList();
        rootNodesAdminObjectives = clasificationDTO.getBranchList();
        listOfSectorObjectives = clasificationDTO.getSectorList();
        loadFunctionalClassifier(this.dependenceId);
    }


    public List<DepencenceDto> getListOfBudgeUnit() {
        return listOfBudgeUnit;
    }

    public void setListOfBudgeUnit(List<DepencenceDto> listOfBudgeUnit) {
        this.listOfBudgeUnit = listOfBudgeUnit;
    }
    
    @Override
    public List<ObjectiveJoinLevelTreeviewDto> loadCombos(Long aFatherId, List<ObjectiveJoinLevelTreeviewDto> aHierarchicalList) {
        ComparatorFatherAndChild myComparator = new ComparatorFatherAndChild();
        ObjectiveJoinLevelTreeviewDto myfather = new ObjectiveJoinLevelTreeviewDto(null, null,aFatherId, null, null, null, (short) 0, null, (short) 0, null);
        return (List<ObjectiveJoinLevelTreeviewDto>) SearchList.findAllinList(aHierarchicalList, myfather, myComparator);

    }

    /**
     * Este Metodo sirve para cargar los combos del Clasificador Funcional
     */
    @Override
    public void loadFunctionalClassifier(Long aDraftProjectId) {

        //this.itsObjectiveId = theirProgrammingMainController.getAnObjectiveId();

        List<ObjectiveJoinLevelTreeviewDto> myDTOs;
        Collection<FunctionalClassifierEntity> myObjectivesToCast = myDirectCaptureDraftProjectManagement.getMyAncestry(this.dependenceId);
        myDTOs = new ArrayList<ObjectiveJoinLevelTreeviewDto>();
        ObjectiveJoinLevelTreeviewDto myWorkingDto;

        for (Object myObjectiveCast : myObjectivesToCast) {
            Object[] myObjectFields = (Object[]) myObjectiveCast;
            myWorkingDto = new ObjectiveJoinLevelTreeviewDto(
                    ((String) myObjectFields[3]), 
                    ((String) myObjectFields[2]), 
                    (Long.parseLong(myObjectFields[0].toString())), 
                    (Long.parseLong(myObjectFields[1].toString())), 
                    ((String) myObjectFields[3]), 
                    ((String) myObjectFields[4]), 
                    ((short) 1), 
                    (Long.parseLong(myObjectFields[5].toString())), 
                    ((short) 1), 
                    ((String) myObjectFields[4]));
            
            myDTOs.add(myWorkingDto);
        }
        itsListHierarchicalFunctional = myDTOs;
        itsListOfFinalities = getRootNodesObjectives(myDTOs);

        ClassifierEntity myEditClassifier;

        if (aDraftProjectId != null) {

            myEditClassifier = itsClassifierManagement.getClassifierByDraftProyectId(aDraftProjectId);
            if (myEditClassifier.getClassifierId() != null) {
                theirCurrentId = myEditClassifier.getClassifierId();
                itsFinalityId = myEditClassifier.getFinalidad().getFunctionalClassifierId();
                itsSubFunctionId = myEditClassifier.getSubFuncion().getFunctionalClassifierId();
                itsFunctionId = myEditClassifier.getFuncion().getFunctionalClassifierId();
            }
            else {//Este else es para cuando se edita una preficha que no ha guardado un Clasificador Funcional.
            itsFinalityId = 0L;
            itsFunctionId = 0L;
            itsSubFunctionId = 0L;
        }

            loadFunctions();
        } else { //Este else sera para cuando se crea una nueva preficha
            itsFinalityId = 0L;
            itsFunctionId = 0L;
            itsSubFunctionId = 0L;
            itsListOfFinalities.clear();
            itsListOfFunctions.clear();
            itsListOfSubFunctions.clear();
        }

    }

    @Override
    public void loadFunctions() {
        if (itsFinalityId!=null){
        itsListOfFunctions = loadCombos(itsFinalityId, itsListHierarchicalFunctional);
 
            itsListOfSubFunctions.clear();
        }

        loadSubFunction();

//        if (itsListOfFunctions.size() == 1) {
//            ObjectiveJoinLevelTreeviewDto myProgram = itsListOfFunctions.get(0);
//            //itsFunctionId = myProgram.getItsObjectiveId();
//          
//        } else {
//            itsFunctionId = "";
//            itsListOfSubFunctions.clear();
//        }
    }

    @Override
    public void loadSubFunction() {
        if (itsFunctionId != null) {
            itsListOfSubFunctions = loadCombos(itsFunctionId, itsListHierarchicalFunctional);
        }
//      
        //      if (itsListOfSubFunctions.size() == 1) {
//            ObjectiveJoinLevelTreeviewDto myProgram = itsListOfSubFunctions.get(0);
//            //itsSubFunctionId = myProgram.getItsObjectiveId();
//        }
    }
    //aqui termina el clasificador funcional

    @Override
    public TreeNode getRootAdministrativeClassifier() {
        return theirTreeViewAdministrativeClassifier.getNodeTreeview(Long.parseLong(theirdraftProjectHeaderController.getItsResponsibleUnitId()));
    }

    @Override
    public TreeNode getRootFunctionalClassifier() {
        return theirTreeviewFunctionalClassifierUIHelper.getNodeTreeview(null);//theirProgrammingMainController.getAnObjectiveId());
    }

    @Override
    public void setRootFunctionalClassifier(TreeNode RootFunctionalClassifier) {
        this.RootFunctionalClassifier = RootFunctionalClassifier;
    }

    @Override
    public TreeNode getItsSelectedNodeAdministrativeClassifier() {
        return itsSelectedNodeAdministrativeClassifier;
    }

    @Override
    public void setItsSelectedNodeAdministrativeClassifier(TreeNode itsSelectedNodeAdministrativeClassifier) {
        this.itsSelectedNodeAdministrativeClassifier = itsSelectedNodeAdministrativeClassifier;
    }

    @Override
    public TreeNode getItsSelectedNodeFunctionalClassifier() {
        return itsSelectedNodeFunctionalClassifier;
    }

    @Override
    public void setItsSelectedNodeFunctionalClassifier(TreeNode itsSelectedNodeFunctionalClassifier) {
        this.itsSelectedNodeFunctionalClassifier = itsSelectedNodeFunctionalClassifier;
    }

    @Override
    public TreeViewAdministrativeClassifierUIHelper getItsTreeViewAdministrativeClassifier() {
        return theirTreeViewAdministrativeClassifier;
    }

    @Override
    public void setItsTreeViewAdministrativeClassifier(TreeViewAdministrativeClassifierUIHelper itsTreeViewAdministrativeClassifier) {
        this.theirTreeViewAdministrativeClassifier = itsTreeViewAdministrativeClassifier;
    }

    /**
     * Busca dentro de una la lista de objetivos cuales objetivos son de primer
     * nivel (donde objective.getFather==null)
     *
     * @param anObjectives lista de objetivos de donde se desea se obtengan los
     * nodos de primer nivel
     * @return Retorna una nueva lista con objetivos de primer nivel donde
     * (objective.getFather==null)
     */
    private List<ObjectiveJoinLevelTreeviewDto> getRootNodesObjectives(List<ObjectiveJoinLevelTreeviewDto> anObjectives) {
        List<ObjectiveJoinLevelTreeviewDto> aRootNodesObjectives = new ArrayList<ObjectiveJoinLevelTreeviewDto>();

        for (ObjectiveJoinLevelTreeviewDto myObjective : anObjectives) {
            if (myObjective.getItsFatherId() == null) {
                itsBranchId = myObjective.getItsObjectiveId();
                aRootNodesObjectives.add(myObjective);
            }
        }
        return aRootNodesObjectives;
    }

    @Override
    public void saveClassif() {

        if (itsClassifierUIHelper.validateClassifier((ClassifierControllerApi)this)) {

            FacesMessage.Severity myMessageFaces = FacesMessage.SEVERITY_INFO;
            String myMessageUI = this.getMessage("ppp.progr.ClassifierSaved");
            try {
                itsClassifierEntity = ClassifierUIHelper.prepareClassifierEntity(this);
                DraftProjectEntity myDraftEntity = new DraftProjectEntity();
                //myDraftEntity.setDraftProjectId(mydraftProjectHeaderController.getTheirCurrentDraftProjectId());
                itsClassifierEntity.setClassifierDraftProject(myDraftEntity);
                theirCurrentId = itsClassifierManagement.saveClassifier(itsClassifierEntity).getClassifierId();

            } catch (Exception e) {
                myMessageUI = this.getMessage(e.getMessage());
                myMessageFaces = FacesMessage.SEVERITY_ERROR;
            } finally {
                addMessageCurrentInstance(myMessageFaces,
                        myMessageUI,
                        myMessageUI);
            }
        }
    }

    @Override
    public Long getItsBranchId() {
        return itsBranchId;
    }

    @Override
    public void setItsBranchId(Long itsBranchId) {
        this.itsBranchId = itsBranchId;
    }

    @Override
    public Long getItsBudgetUnitId() {
        return itsBudgetUnitId;
    }

    @Override
    public void setItsBudgetUnitId(Long itsBudgetUnitId) {
        this.itsBudgetUnitId = itsBudgetUnitId;
    }

    @Override
    public Long getItsExpenditureImplementationUnitId() {
        return itsExpenditureImplementationUnitId;
    }

    @Override
    public void setItsExpenditureImplementationUnitId(Long itsExpenditureImplementationUnitId) {
        this.itsExpenditureImplementationUnitId = itsExpenditureImplementationUnitId;
    }

    @Override
    public Long getItsResponsibleUnitId() {
        return itsResponsibleUnitId;
    }

    @Override
    public void setItsResponsibleUnitId(Long itsResponsibleUnitId) {
        this.itsResponsibleUnitId = itsResponsibleUnitId;
    }

    @Override
    public Long getItsSectorId() {
        return itsSectorId;
    }

    @Override
    public void setItsSectorId(Long itsSectorId) {
        this.itsSectorId = itsSectorId;
    }

//    @Override
//    public List<ObjectiveJoinLevelTreeviewDto> getItsListOfBudgeUnit() {
//        return itsListOfBudgeUnit;
//    }
//
//    @Override
//    public void setItsListOfBudgeUnit(List<ObjectiveJoinLevelTreeviewDto> itsListOfBudgeUnit) {
//        this.itsListOfBudgeUnit = itsListOfBudgeUnit;
//    }
//
//    @Override
//    public List<ObjectiveJoinLevelTreeviewDto> getItsListOfExpenditureImplementationUnit() {
//        return itsListOfExpenditureImplementationUnit;
//    }
//
//    @Override
//    public void setItsListOfExpenditureImplementationUnit(List<ObjectiveJoinLevelTreeviewDto> itsListOfExpenditureImplementationUnit) {
//        this.itsListOfExpenditureImplementationUnit = itsListOfExpenditureImplementationUnit;
//    }
//
//    @Override
//    public List<ObjectiveJoinLevelTreeviewDto> getItsListOfResponsibleUnit() {
//        return itsListOfResponsibleUnit;
//    }
//
//    @Override
//    public void setItsListOfResponsibleUnit(List<ObjectiveJoinLevelTreeviewDto> itsListOfResponsibleUnit) {
//        this.itsListOfResponsibleUnit = itsListOfResponsibleUnit;
//    }


    @Override
    public Long getItsFinalityId() {
        return itsFinalityId;
    }

    @Override
    public void setItsFinalityId(Long itsFinalityId) {
        this.itsFinalityId = itsFinalityId;
    }

    @Override
    public Long getItsFunctionId() {
        return itsFunctionId;
    }

    @Override
    public void setItsFunctionId(Long itsFunctionId) {
        this.itsFunctionId = itsFunctionId;
    }

    @Override
    public Long getItsSubFunctionId() {
        return itsSubFunctionId;
    }

    @Override
    public void setItsSubFunctionId(Long itsSubFunctionId) {
        this.itsSubFunctionId = itsSubFunctionId;
    }

    @Override
    public List<ObjectiveJoinLevelTreeviewDto> getItsListOfFinalities() {
        return itsListOfFinalities;
    }

    @Override
    public void setItsListOfFinalities(List<ObjectiveJoinLevelTreeviewDto> itsListOfFinalities) {
        this.itsListOfFinalities = itsListOfFinalities;
    }

    @Override
    public List<ObjectiveJoinLevelTreeviewDto> getItsListOfFunctions() {
        return itsListOfFunctions;
    }

    @Override
    public void setItsListOfFunctions(List<ObjectiveJoinLevelTreeviewDto> itsListOfFunctions) {
        this.itsListOfFunctions = itsListOfFunctions;
    }

    @Override
    public List<ObjectiveJoinLevelTreeviewDto> getItsListOfSubFunctions() {
        return itsListOfSubFunctions;
    }

    @Override
    public void setItsListOfSubFunctions(List<ObjectiveJoinLevelTreeviewDto> itsListOfSubFunctions) {
        this.itsListOfSubFunctions = itsListOfSubFunctions;
    }

    @Override
    public ClassifierEntity getItsClassifierEntity() {
        return itsClassifierEntity;
    }

    @Override
    public void setItsClassifierEntity(ClassifierEntity itsClassifierEntity) {
        this.itsClassifierEntity = itsClassifierEntity;
    }

    @Override
    public List<DepencenceDto> getItsListOfBudgeUnit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DepencenceDto> getItsListOfExpenditureImplementationUnit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DepencenceDto> getItsListOfResponsibleUnit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void setItsListOfBudgeUnit(List<DepencenceDto> itsListOfBudgeUnit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setItsListOfExpenditureImplementationUnit(List<DepencenceDto> itsListOfExpenditureImplementationUnit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setItsListOfResponsibleUnit(List<DepencenceDto> itsListOfResponsibleUnit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SectorEntity> getItsListOfSectorObjectives() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BranchEntity> getMyRootNodesAdminObjectives() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setItsListOfSectorObjectives(List<SectorEntity> itsListOfSectorObjectives) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMyRootNodesAdminObjectives(List<BranchEntity> myRootNodesAdminObjectives) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
