package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.*;
import com.abs.siif.ppp.programming.api.controller.ClassifierControllerApi;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.api.controller.ProgMainCtrlApi;
import com.abs.siif.ppp.programming.dto.ObjJoinLTreeviewDtoComparator;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.ppp.programming.uihelpers.ClassifierUIHelper;
import com.abs.siif.ppp.programming.uihelpers.ComparatorFatherAndChild;
import com.abs.siif.ppp.programming.uihelpers.TreeViewAdministrativeClassifierUIHelper;
import com.abs.siif.ppp.programming.uihelpers.TreeviewFunctionalClassifierUIHelper;
import com.abs.siif.programming.entities.ClassifierEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.management.AdministrativeClassifierManagement;
import com.abs.siif.programming.management.ClassifierManagement;
import com.abs.siif.planning.management.FunctionalClassifierManagement;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jacob.flores / Erick "El Fenix" Leija
 */
@Scope("session")
@Controller("ClassifierController")
public class ClassifierController extends SIIFControllerBase implements Serializable, ClassifierControllerApi {

    @Resource(name = "programminMainController")
    private transient ProgMainCtrlApi myProgrammingMainController;
    @Resource(name = "draftProjectHeaderController")
    private transient DraftProjectHeaderControllerApi mydraftProjectHeaderController;
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
    private DraftProjectHeaderControllerApi theirdraftProjectHeaderController;
    private ProgMainCtrlApi theirProgrammingMainController;
    private AdministrativeClassifierManagement theirAdministrativeClassifierManagement;
    private FunctionalClassifierManagement theirFunctionalClassifierManagement;
    //Listas de de elementos jerarquizados
    private List<ObjectiveJoinLevelTreeviewDto> itsListHierarchicalAdministrative;
    private List<ObjectiveJoinLevelTreeviewDto> itsListHierarchicalFunctional;
    //Listas de Clasificador Administrativo
    private List<BranchEntity> myRootNodesAdminObjectives;
    private List<SectorEntity> itsListOfSectorObjectives;
    private List<DepencenceDto> itsListOfBudgeUnit;
    private List<DepencenceDto> itsListOfResponsibleUnit;
    private List<DepencenceDto> itsListOfExpenditureImplementationUnit;
    //Listas de Clasificador Funcional
    private List<ObjectiveJoinLevelTreeviewDto> itsListOfFinalities;
    private List<ObjectiveJoinLevelTreeviewDto> itsListOfFunctions;
    private List<ObjectiveJoinLevelTreeviewDto> itsListOfSubFunctions;
    @Resource
    private transient TreeviewFunctionalClassifierUIHelper theirTreeviewFunctionalClassifierUIHelper;
    private Long itsDependenceId;
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

    @Override
    public Long getTheirCurrentId() {
        return theirCurrentId;
    }

    @Override
    public void setTheirCurrentId(Long theirCurrentId) {
        this.theirCurrentId = theirCurrentId;
    }

    @Override
    public void init() 
    {
        Long myCurrentDraftProjectId = mydraftProjectHeaderController.getTheirCurrentDraftProjectId();
        theirCurrentId = null;
        itsListOfFinalities = new ArrayList<ObjectiveJoinLevelTreeviewDto>();
        itsListOfFunctions = new ArrayList<ObjectiveJoinLevelTreeviewDto>();
        itsListOfSubFunctions = new ArrayList<ObjectiveJoinLevelTreeviewDto>();
        this.theirProgrammingMainController = myProgrammingMainController;
        this.theirdraftProjectHeaderController = mydraftProjectHeaderController;
        this.theirAdministrativeClassifierManagement = myAdministrativeClassifierManagement;
        this.theirFunctionalClassifierManagement = myFunctionalClassifierManagement;
        loadAdministrativeAndFunctionalClassifier(myCurrentDraftProjectId);
        mydraftProjectHeaderController.setItsSelectedUEG(this.itsExpenditureImplementationUnitId);
    }

    private void loadAdministrativeAndFunctionalClassifier(Long aDraftProjectId) {
        loadAdministrativeClassifier();
        loadFunctionalClassifier(aDraftProjectId);
    }
    
    @Override
    public void loadAdministrativeClassifier() 
    {
        try
        {
        this.itsDependenceId = theirdraftProjectHeaderController.getIdDependency();
        DependenceEntity myDependence = new DependenceEntity();
        myDependence.setDependenceId(itsDependenceId);
        itsListOfExpenditureImplementationUnit = 
                theirAdministrativeClassifierManagement.getMyUEGsByDependenceRelated(itsDependenceId);
        itsListOfBudgeUnit = 
                theirAdministrativeClassifierManagement.getMyUPByDependenceRelated(itsDependenceId);
        itsListOfResponsibleUnit = 
                theirAdministrativeClassifierManagement.getMyURByDependenceRelated(itsDependenceId);
        List<DepencenceDto> myDependeWithFraming =
                theirAdministrativeClassifierManagement.getMyFrammingSectorByDependenceRelated(itsDependenceId);
         BranchSectorAndDependenceFrammingEntity myFramming;
        if (myDependeWithFraming == null || myDependeWithFraming.size() < 1)
        {
            DependenceEntity mydepFramming = new DependenceEntity();
            mydepFramming.setDependenceId(myDependeWithFraming.get(0).getIdDependency());
            myFramming =
                    theirAdministrativeClassifierManagement.getMyFrammingByDependenceId(mydepFramming);
        } else
        {
            myFramming =
                    theirAdministrativeClassifierManagement.getMyFrammingByDependenceId(myDependence);
        }
        
        myRootNodesAdminObjectives=new ArrayList<BranchEntity>();
        myRootNodesAdminObjectives.add(myFramming.getBranchSectorAndDependenceFrammingBranchSectorId().getBranchAndSectorFrammingBranch());
        itsListOfSectorObjectives=new ArrayList<SectorEntity>();
        itsListOfSectorObjectives.add(myFramming.getBranchSectorAndDependenceFrammingBranchSectorId().getBranchAndSectorFrammingSector());
        } catch (Exception exc)
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }
        
    }
    
    @Override
    public List<ObjectiveJoinLevelTreeviewDto> loadCombos(Long aFatherId, List<ObjectiveJoinLevelTreeviewDto> aHierarchicalList) {
        ComparatorFatherAndChild myComparator = new ComparatorFatherAndChild();
        ObjectiveJoinLevelTreeviewDto myfather = new ObjectiveJoinLevelTreeviewDto(null, null, aFatherId, null, null, null, (short) 0, null, (short) 0, null);
        return (List<ObjectiveJoinLevelTreeviewDto>) SearchList.findAllinList(aHierarchicalList, myfather, myComparator);

    }

    /**
     * Este Metodo sirve para cargar los combos del Clasificador Funcional
     */
    @Override
    public void loadFunctionalClassifier(Long aDraftProjectId) 
    {

        try
        {
            this.itsObjectiveId = theirProgrammingMainController.getAnObjectiveId();

            List<ObjectiveJoinLevelTreeviewDto> myDTOs;
            List<FunctionalClassifierEntity> myObjectivesToCast = 
                    (List<FunctionalClassifierEntity>) theirFunctionalClassifierManagement.
                    getMyFunctionalClassifierAncestry(itsObjectiveId);
            myDTOs = new ArrayList<ObjectiveJoinLevelTreeviewDto>();
            ObjectiveJoinLevelTreeviewDto myWorkingDto;

            for (Object myObjectiveCast : myObjectivesToCast)
            {
                Object[] myObjectFields = (Object[]) myObjectiveCast;
                if (myObjectFields[5] != null)
                {
                    myWorkingDto = new ObjectiveJoinLevelTreeviewDto(((String) myObjectFields[3]),
                            ((String) myObjectFields[2]), Long.parseLong(myObjectFields[0].toString()), 
                            Long.parseLong(myObjectFields[1].toString()),
                            ((String) myObjectFields[3]), ((String) myObjectFields[4]), 
                            ((short) 1), Long.parseLong(myObjectFields[5].toString()),
                            ((short) 1), ((String) myObjectFields[4]));
                } else
                {
                    myWorkingDto = new ObjectiveJoinLevelTreeviewDto(((String) myObjectFields[3]),
                            ((String) myObjectFields[2]), 
                            Long.parseLong(myObjectFields[0].toString()), 
                            Long.parseLong(myObjectFields[1].toString()),
                            ((String) myObjectFields[3]), ((String) myObjectFields[4]), 
                            ((short) 1), null,
                            ((short) 1), ((String) myObjectFields[4]));
                }
                myDTOs.add(myWorkingDto);
            }
            itsListHierarchicalFunctional = myDTOs;
            itsListOfFinalities = getRootNodesObjectives(myDTOs);

            ClassifierEntity myEditClassifier;

            if (aDraftProjectId != null)
            {

                myEditClassifier = 
                        itsClassifierManagement.
                        getClassifierByDraftProyectId(aDraftProjectId);
                if (myEditClassifier.getClassifierId() != null)
                {
                    theirCurrentId = myEditClassifier.getClassifierId();
                    itsFinalityId = myEditClassifier.getFinalidad().getFunctionalClassifierId();
                    itsSubFunctionId = myEditClassifier.getSubFuncion().getFunctionalClassifierId();
                    itsFunctionId = myEditClassifier.getFuncion().getFunctionalClassifierId();
                } else
                {//Este else es para cuando se edita una preficha que no ha guardado un Clasificador Funcional.
                    itsFinalityId = null;
                    itsFunctionId = null;
                    itsSubFunctionId = null;
                }

                loadFunctions();
            } else
            { //Este else sera para cuando se crea una nueva preficha
                itsFinalityId = null;
                itsFunctionId = null;
                itsSubFunctionId = null;
                itsListOfFinalities.clear();
                itsListOfFunctions.clear();
                itsListOfSubFunctions.clear();
            }
        } catch (Exception exc)
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }
        
    }

    @Override
    public void loadFunctions() {
        if (itsFinalityId != null) {
            itsListOfFunctions = loadCombos(itsFinalityId, itsListHierarchicalFunctional);

            itsListOfSubFunctions.clear();
        }

        loadSubFunction();

    }

    @Override
    public void loadSubFunction() {
        if (itsFunctionId != null) {
            itsListOfSubFunctions = loadCombos(itsFunctionId, itsListHierarchicalFunctional);
        }

    }
    //aqui termina el clasificador funcional

    @Override
    public TreeNode getRootAdministrativeClassifier() {
        return theirTreeViewAdministrativeClassifier.getNodeTreeview(theirdraftProjectHeaderController.getIdDependency());
    }

    @Override
    public TreeNode getRootFunctionalClassifier() {
        return theirTreeviewFunctionalClassifierUIHelper.getNodeTreeview(theirProgrammingMainController.getAnObjectiveId());
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
                myDraftEntity.setDraftProjectId(mydraftProjectHeaderController.getTheirCurrentDraftProjectId());
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

    @Override
    public List<DepencenceDto> getItsListOfBudgeUnit() {
        return itsListOfBudgeUnit;
    }

    @Override
    public void setItsListOfBudgeUnit(List<DepencenceDto> itsListOfBudgeUnit) {
        this.itsListOfBudgeUnit = itsListOfBudgeUnit;
    }

    @Override
    public List<DepencenceDto> getItsListOfExpenditureImplementationUnit() {
        return itsListOfExpenditureImplementationUnit;
    }

    @Override
    public void setItsListOfExpenditureImplementationUnit(List<DepencenceDto> itsListOfExpenditureImplementationUnit) {
        this.itsListOfExpenditureImplementationUnit = itsListOfExpenditureImplementationUnit;
    }

    @Override
    public List<DepencenceDto> getItsListOfResponsibleUnit() {
        return itsListOfResponsibleUnit;
    }

    @Override
    public void setItsListOfResponsibleUnit(List<DepencenceDto> itsListOfResponsibleUnit) {
        this.itsListOfResponsibleUnit = itsListOfResponsibleUnit;
    }

    @Override
    public List<SectorEntity> getItsListOfSectorObjectives() {
        return itsListOfSectorObjectives;
    }

    @Override
    public void setItsListOfSectorObjectives(List<SectorEntity> itsListOfSectorObjectives) {
        this.itsListOfSectorObjectives = itsListOfSectorObjectives;
    }

    @Override
    public List<BranchEntity> getMyRootNodesAdminObjectives() {
        return myRootNodesAdminObjectives;
    }

    @Override
    public void setMyRootNodesAdminObjectives(List<BranchEntity> myRootNodesAdminObjectives) {
        this.myRootNodesAdminObjectives = myRootNodesAdminObjectives;
    }

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
        Collections.sort(itsListOfFinalities, ObjJoinLTreeviewDtoComparator.getInstance());
        return itsListOfFinalities;
    }

    @Override
    public void setItsListOfFinalities(List<ObjectiveJoinLevelTreeviewDto> itsListOfFinalities) {
        this.itsListOfFinalities = itsListOfFinalities;
    }

    @Override
    public List<ObjectiveJoinLevelTreeviewDto> getItsListOfFunctions() {
        Collections.sort(itsListOfFunctions, ObjJoinLTreeviewDtoComparator.getInstance());
        return itsListOfFunctions;
    }

    @Override
    public void setItsListOfFunctions(List<ObjectiveJoinLevelTreeviewDto> itsListOfFunctions) {
        this.itsListOfFunctions = itsListOfFunctions;
    }

    @Override
    public List<ObjectiveJoinLevelTreeviewDto> getItsListOfSubFunctions() {
        Collections.sort(itsListOfSubFunctions, ObjJoinLTreeviewDtoComparator.getInstance());
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

}