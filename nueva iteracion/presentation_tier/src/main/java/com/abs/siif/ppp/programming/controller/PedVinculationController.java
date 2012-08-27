package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.entities.*;
import com.abs.siif.planning.management.InstitutionalPlanManagement;
import com.abs.siif.planning.management.ObjectiveManagement;
import com.abs.siif.planning.management.RegionalPlanManagement;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.api.controller.PedVinculationControllerApi;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.ppp.programming.uihelpers.ComparatorFatherAndChild;
import com.abs.siif.ppp.programming.uihelpers.RegionalDataModel;
import com.abs.siif.ppp.programming.uihelpers.RegionalPlanDataModel;
import com.abs.siif.ppp.programming.uihelpers.TreeviewObjectiveByDependenceIUHelper;
import com.abs.siif.programming.dto.RegionalClassifierPEDDto;
import com.abs.siif.programming.dto.RegionalPlanDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.PEDVinculationEntity;
import com.abs.siif.programming.entities.RegionalPlansOfPEDEntity;
import com.abs.siif.programming.management.DraftProjectManagement;
import com.abs.siif.programming.management.ObjectiveProgrammingManagement;
import com.abs.siif.programming.management.PedVinculationManagement;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Este componente muestra la informacion del Plan Estrategico y el plan
 * Instituional, asociado con la preficha seleccionada
 */
@Scope("session")
@Controller("pedVinculationController")
public class PedVinculationController
        extends SIIFControllerBase
        implements Serializable, PedVinculationControllerApi {

    //estos id's son del problema y del Objetivo Especifico
    private Long aSpecificObId;
    private Long aProblemId;
    private String aIndicatorId;
    //***********************
    private Long anObjectiveOfPI;
    private Long anIndicatorOfPI;
    private TreeNode itsPEDtree;
    private TreeNode itsSelectedNode;
    private String itsStrategicAxisPED;
    private String itsPurposePED;
    private Long itsDependenceId;
    private RegionalClassifierPEDDto selectedRow;
    private List<Long> SelectedPlanRow;
    private boolean btnSrvSave = false;
    private boolean btnSrvSaveRegion = false;
    //Id's necesarios para guardar la seleccion
    private Long itsStrategicAxisId;
    private Long itsProgramId;
    private Long itsSubProgramId;
    //*********************
    private String itsStrategicAxisName;
    private String itsStrategicAxisKey;
    private String itsStrategicAxisDefinition;
    //aqui se realiza la inyeccion de los nodos seleccionados en la programacion
    private ObjectiveJoinLevelTreeviewDto itsProgramSelected;
    private ObjectiveJoinLevelTreeviewDto itsSubProgramSelected;
    private ObjectiveJoinLevelTreeviewDto itsAxisSelected;
    private List<RegionalPlanDto> itsListOfRegionalPlanDto;
    private List<RegionalClassifierPEDDto> itsListOfRegionalDto;
    private List<ObjectiveJoinLevelTreeviewDto> itsMyhierarchicalList;
    //Esta Variable es la que contiene el Id de la Dependencia con la cual
    //se poblara el arbol de Vinculacion(Encuadre) de Planeacion Institucional
    //y Planeacion Estrategica
    //TODO: Hay que hacer la obtencion Dinamica de los Id Dependencia
    //Esta variable es la que contiene el Id de la ficha en la cual estamos
    //trabajando y por medio de la cual se va a realizar la busqueda en la
    //tabla de Vinculacion(Encuadre) de Planeacion Regional
    //la busqueda de las Regiones
    private final String itsTokenId = "123";
    // Esta es la Lista que con la que se va a llenar la lista de las regiones que
    // tiene la dependencia asignada por medio del encuadre
    private List<RegionalClassifierEntity> itsListOfRegions;
    // Esta es la lista de los objetivos especificos asignados al objetivo seleccionado
    private List<SpecificObjectiveEntity> itsListOfSpecificObjectives;
    private List<InstitutionalPlanObjectiveEntity> itsListOfObjectivesofPI;
    private List<ProblemEntity> itsListOfProblems;
    private List<InstitutionalPlanEntity> itsListOfPI;
    private List<ObjectiveJoinLevelTreeviewDto> myRootNodesObjectives;
    private List<ObjectiveJoinLevelTreeviewDto> myProgramsNodesObjectives;
    private List<ObjectiveJoinLevelTreeviewDto> mySubProgramsNodesObjectives;
    private List<RegionalPlanEntity> myListOfObjectivesPED;
    private List<ObjectiveIndicatorEntity> myListOfIndicatorsofSP;
    private RegionalDataModel myRegionalDataModel;
    private RegionalPlanDataModel myRegionalPlanDataModel;
    private ObjectiveManagement theirObjectiveManagement;
    @Resource(name = "draftProjectManagement")
    private transient DraftProjectManagement theirDraftProjectManagement;
    @Resource(name = "regionalPlanManagement")
    private transient RegionalPlanManagement theirRegClassifManagement;
    //Esta es la inyección de el componente que dibuja el Arbol
    @Resource(name = "treeviewObjectiveByDependence")
    private transient TreeviewObjectiveByDependenceIUHelper theirTvObjective;
    @Resource(name = "institutionalPlanManagement")
    private transient InstitutionalPlanManagement theirInstitutionalPlanManagement;
    @Resource(name = "pedVinculationManagement")
    private transient PedVinculationManagement theirPEDVinculationManagement;
    //Se hace la inyeccion en el constructor de la Clase para oibtener los datos necesarios
    private ObjectiveProgrammingManagement theirObjectiveProgrammingManagement;
    private DraftProjectHeaderControllerApi theirdraftProjectHeaderController;
    /**
     * Este metodo es el constructor del Controller el cual inicializa las
     * variables necesarias
     */
    @Autowired(required = true)
    public PedVinculationController(
            @Qualifier("objectiveProgrammingManagement") ObjectiveProgrammingManagement aObjectiveProgrammingManagement,
            @Qualifier("draftProjectHeaderController") DraftProjectHeaderControllerApi myDraftProjectHeaderController,
            @Qualifier("objectiveManagement") ObjectiveManagement myObjectiveManagement) {
        this.itsListOfRegions = new ArrayList<RegionalClassifierEntity>();
        this.itsListOfSpecificObjectives = new ArrayList<SpecificObjectiveEntity>();
        this.itsListOfProblems = new ArrayList<ProblemEntity>();
        this.itsListOfPI = new ArrayList<InstitutionalPlanEntity>();
        this.theirObjectiveProgrammingManagement = aObjectiveProgrammingManagement;
        this.theirdraftProjectHeaderController = myDraftProjectHeaderController;
        this.theirObjectiveManagement = myObjectiveManagement;
        this.myListOfObjectivesPED = new ArrayList<RegionalPlanEntity>();
        this.itsListOfRegionalDto = new ArrayList<RegionalClassifierPEDDto>();
        this.itsListOfRegionalPlanDto = new ArrayList<RegionalPlanDto>();
        this.SelectedPlanRow = new ArrayList<Long>();
        this.itsListOfObjectivesofPI = new ArrayList<InstitutionalPlanObjectiveEntity>();
    }

    @Override
    public void init() {
        btnSrvSave = false;
        itsListOfPI.clear();
        itsListOfPI = theirInstitutionalPlanManagement.getInstitutionalPlanByDependenceChildId(theirdraftProjectHeaderController.getIdDependency());
        itsListOfObjectivesofPI.clear();
        if (!(itsListOfPI == null) || (itsListOfPI.isEmpty())) {
            itsListOfObjectivesofPI = theirInstitutionalPlanManagement.searchInstPlanObjectives(itsListOfPI.get(itsListOfPI.size() - 1));
        }
        myRegionalDataModel = new RegionalDataModel();
        loadPEDVinculation();
        chargeSaveObjectOfPED();
        itsListOfRegions = new ArrayList<RegionalClassifierEntity>(theirRegClassifManagement.getAllRegionalClassifierRP());
        itsListOfRegionalDto.clear();
        List<RegionalPlansOfPEDEntity> myRegionSaved = theirPEDVinculationManagement.getRegionalPlanByDraftProjectId(theirdraftProjectHeaderController.getTheirCurrentDraftProjectId());
        Set<RegionalClassifierEntity> myRegionsWithOneOrMore = new HashSet<RegionalClassifierEntity>();
        for (RegionalPlansOfPEDEntity regiontoCheck : myRegionSaved) {
            myRegionsWithOneOrMore.add(regiontoCheck.getRegionalPlansOfPED().getRegionalClassifierPlan());
        }
        if (!(itsListOfRegions == null) || (itsListOfRegions.isEmpty())) {
            for (RegionalClassifierEntity myEntity : itsListOfRegions) {
                boolean myCheck = false;
                if (!(myRegionsWithOneOrMore == null) || (myRegionsWithOneOrMore.isEmpty())) {
                    for (RegionalClassifierEntity RegionToSetTrue : myRegionsWithOneOrMore) {
                        if (myEntity.getRegionalClassifierId().equals(RegionToSetTrue.getRegionalClassifierId())) {
                            myCheck = true;
                            break;
                        }
                    }
                }
                RegionalClassifierPEDDto myDTO = new RegionalClassifierPEDDto(myCheck, myEntity.getRegionalClassifierId(),
                        myEntity.getRegionalClassifierDescription());
                itsListOfRegionalDto.add(myDTO);
            }
        }
        itsListOfRegionalPlanDto.clear();
        myRegionalDataModel = new RegionalDataModel(itsListOfRegionalDto);

    }

    @Override
    public void chargeSaveObjectOfPED() {
        PEDVinculationEntity myPEDtoLoad = theirPEDVinculationManagement.getPEDVinculationById(theirdraftProjectHeaderController.getTheirCurrentDraftProjectId());
        if (myPEDtoLoad != null) {
            itsStrategicAxisId = myPEDtoLoad.getVinculationAxis().getObjectiveId();
            ComparatorFatherAndChild myComparator = new ComparatorFatherAndChild();
            ObjectiveJoinLevelTreeviewDto myfather =
                    new ObjectiveJoinLevelTreeviewDto(null, null, itsStrategicAxisId,
                    null, null, null, (short) 0, null, (short) 0, null);
            myProgramsNodesObjectives = (List<ObjectiveJoinLevelTreeviewDto>) SearchList.findAllinList(itsMyhierarchicalList, myfather, myComparator);

            ObjectiveEntity myObjective = theirObjectiveManagement.getObjectiveEagerByIdentity(itsStrategicAxisId);
            for (ProblemEntity anObjective : myObjective.getProblems()) {
                itsListOfProblems.add(anObjective);
            }

            itsProgramId = myPEDtoLoad.getVinculationProgram().getObjectiveId();

            ObjectiveJoinLevelTreeviewDto myProgramFather =
                    new ObjectiveJoinLevelTreeviewDto(null, null, itsProgramId, null,
                    null, null, (short) 0, null, (short) 0, null);
            mySubProgramsNodesObjectives = (List<ObjectiveJoinLevelTreeviewDto>) SearchList.findAllinList(itsMyhierarchicalList, myProgramFather, myComparator);
            itsSubProgramId = myPEDtoLoad.getVinculationSubProgram().getObjectiveId();
            LoadSpecificObjectives();

            if (!(myPEDtoLoad.getVinculationProblem() == null
                    || myPEDtoLoad.getVinculationProblem().getProblemId() == 0)) {
                aProblemId = myPEDtoLoad.getVinculationProblem().getProblemId();
            }
            if (!(myPEDtoLoad.getVinculationSpecificObjective() == null
                    || myPEDtoLoad.getVinculationSpecificObjective().getSpecificObjectiveId() == 0)) {
                aSpecificObId = myPEDtoLoad.getVinculationSpecificObjective().getSpecificObjectiveId();

            }
            if (myPEDtoLoad.getVinculationObjetivoPI() != null   ) {
                anObjectiveOfPI = myPEDtoLoad.getVinculationObjetivoPI().getInstitutionalPlanObjectiveId();

            } else{
                anObjectiveOfPI = -1L;
            }
        }
    }

    @Override
    public Long getAProblemId() {
        return aProblemId;
    }

    @Override
    public void setAProblemId(Long aProblemId) {
        this.aProblemId = aProblemId;
    }

    @Override
    public Long getASpecificObId() {
        return aSpecificObId;
    }

    @Override
    public void setASpecificObId(Long aSpecificObId) {
        this.aSpecificObId = aSpecificObId;
    }

    @Override
    public TreeNode getItsSelectedNode() {
        return itsSelectedNode;
    }

    @Override
    public void setItsSelectedNode(TreeNode itsSelectedNode) {
        this.itsSelectedNode = itsSelectedNode;
    }

    @Override
    public void LoadSpecificObjectives() {
        itsListOfSpecificObjectives.clear();
        if (itsSubProgramId > 0) {
            ObjectiveEntity myObjective = theirObjectiveManagement.getObjectiveSpecificObjAndIndicatorByIdentity(itsSubProgramId);
            myListOfIndicatorsofSP = new ArrayList<ObjectiveIndicatorEntity>(myObjective.getObjetiveIndicators());
            for (SpecificObjectiveEntity anObjective : myObjective.getSpecificObjectives()) {
                itsListOfSpecificObjectives.add(anObjective);
            }
        }

    }

    private void loadPEDVinculation() {

        DraftProjectEntity drafEntity = theirdraftProjectHeaderController.getItsDraftProjectEntity();
        List<ObjectiveEntity> alignment = null;
        ObjectiveEntity axisObj = null;
        ObjectiveEntity programmObj = null;
        ObjectiveEntity subProgramObj = null;
        
        if(drafEntity.getDraftProjectProgramming()!= null){
            alignment  = theirObjectiveManagement.getStateAlignmet(
                    drafEntity.getDraftProjectProgramming().getProgrammingObjective());
        }
        
        this.itsDependenceId = theirdraftProjectHeaderController.getIdDependency();
        

        String myKey;
        List<ObjectiveJoinLevelTreeviewDto> myDTOs;


        List<ObjectiveEntity> myObjectivesToCast = (List<ObjectiveEntity>) theirObjectiveProgrammingManagement.getAllObjectivesByDependenceId(itsDependenceId);
        myDTOs = new ArrayList<ObjectiveJoinLevelTreeviewDto>();
        ObjectiveJoinLevelTreeviewDto myWorkingDto;
        ObjectiveJoinLevelTreeviewDto firstFather = null;
        for (ObjectiveEntity myObjectiveCast : alignment) {
            
            Long myFatherId;
            if (myObjectiveCast.getFather() == null) {
                myFatherId = null;
            } else {
                myFatherId = myObjectiveCast.getFather().getObjectiveId();
            }
            myWorkingDto = new ObjectiveJoinLevelTreeviewDto(myObjectiveCast.getObjectiveKey() + "\t " + myObjectiveCast.getObjectiveName(),
                    myObjectiveCast.getObjectiveDefinition(), myObjectiveCast.getObjectiveId(),
                    myObjectiveCast.getObjectiveLevel().getObjectiveLevelId(),
                    myObjectiveCast.getObjectiveName(), myObjectiveCast.getObjectiveDefinition(), ((short) 1),
                    (myFatherId), ((short) 1), myObjectiveCast.getCompositeObjectiveKey());
            myDTOs.add(myWorkingDto);
            if (myWorkingDto.getItsFatherId() == null) {
                firstFather = myWorkingDto;
            }
        }
        itsMyhierarchicalList = myDTOs;
        myRootNodesObjectives = getRootNodesObjectives(myDTOs);
        
        if(alignment != null && !alignment.isEmpty() && alignment.size() == 3) {
           axisObj =  alignment.get(0);
           programmObj =  alignment.get(1);
           subProgramObj =  alignment.get(2);
           
           itsStrategicAxisId = axisObj.getObjectiveId();
           itsStrategicAxisName = axisObj.getObjectiveName();
           itsStrategicAxisKey = axisObj.getObjectiveKey() + " "+ axisObj.getObjectiveName();
           itsStrategicAxisDefinition = axisObj.getObjectiveDefinition();           
           itsProgramId = programmObj.getObjectiveId();
           itsSubProgramId = subProgramObj.getObjectiveId();
        }
        
        itsListOfProblems.clear();
        ObjectiveEntity myObjective = theirObjectiveManagement.getObjectiveEagerByIdentity(itsStrategicAxisId);
        for (ProblemEntity anObjective : myObjective.getProblems()) {
            itsListOfProblems.add(anObjective);
        }
        ComparatorFatherAndChild myComparator = new ComparatorFatherAndChild();
        ObjectiveJoinLevelTreeviewDto myfather = new ObjectiveJoinLevelTreeviewDto(null, null, itsStrategicAxisId, null, null, null, (short) 0, null, (short) 0, null);
        myProgramsNodesObjectives = (List<ObjectiveJoinLevelTreeviewDto>) SearchList.findAllinList(itsMyhierarchicalList, myfather, myComparator);
       
        loadSubPrograms();
        
        if (myProgramsNodesObjectives.size() == 1) {
            //ObjectiveJoinLevelTreeviewDto myProgram = myProgramsNodesObjectives.get(0);
            //itsProgramId = myProgram.getItsObjectiveId();
            //loadSubPrograms();
        } else {
            if (myProgramsNodesObjectives.isEmpty()) {
                mySubProgramsNodesObjectives.clear();
            } else {
                //itsProgramId = theirdraftProjectHeaderController.getItsProgramSelected().getItsObjectiveId();
                //loadSubPrograms();
            }
        }
    }

    @Override
    public void loadSubPrograms() {

        ComparatorFatherAndChild myComparator = new ComparatorFatherAndChild();
        ObjectiveJoinLevelTreeviewDto myProgramFather = new ObjectiveJoinLevelTreeviewDto(null, null, itsProgramId, null, null, null, (short) 0, null, (short) 0, null);
        mySubProgramsNodesObjectives = (List<ObjectiveJoinLevelTreeviewDto>) SearchList.findAllinList(itsMyhierarchicalList, myProgramFather, myComparator);
        if (mySubProgramsNodesObjectives.size() == 1) {
            ObjectiveJoinLevelTreeviewDto mySubProgram = mySubProgramsNodesObjectives.get(0);
            itsSubProgramId = mySubProgram.getItsObjectiveId();
            LoadSpecificObjectives();
        } else {
            if (mySubProgramsNodesObjectives.isEmpty()) {
                itsListOfSpecificObjectives.clear();
            } else {
                if (itsProgramId.equals(theirdraftProjectHeaderController.getItsProgramSelected().getItsObjectiveId())) {
                    itsSubProgramId = theirdraftProjectHeaderController.getItsSubProgramSelected().getItsObjectiveId();
                    LoadSpecificObjectives();
                } else {
                    itsListOfSpecificObjectives.clear();
                    itsSubProgramId = new Long(0);

                }

            }
        }
    }

    @Override
    public List<ObjectiveJoinLevelTreeviewDto> getMyRootNodesObjectives() {
        return myRootNodesObjectives;
    }

    @Override
    public void setMyRootNodesObjectives(List<ObjectiveJoinLevelTreeviewDto> myRootNodesObjectives) {
        this.myRootNodesObjectives = myRootNodesObjectives;
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
                itsStrategicAxisId = myObjective.getItsObjectiveId();
                aRootNodesObjectives.add(myObjective);
            }
        }
        return aRootNodesObjectives;
    }

    @Override
    public TreeNode getRoot() {
        theirTvObjective.restartNodeTreeview();
        TreeNode myRoot = theirTvObjective.getNodeTreeview(itsDependenceId);
        return myRoot;
    }

    /**
     * Este metodo se encarga de guarda la selecciÃ³n del problema y del
     * Objetivo Especifico, que el usuario captura en pantalla
     */
    @Override
    public void savePEDVinculation() {
        boolean myProblemModifisBlank = false;
        boolean mySPModifisBlank = false;
        boolean saveHerarchicalInfo = false;
        PEDVinculationEntity myPEDVinculation = theirPEDVinculationManagement.getPEDVinculationById(theirdraftProjectHeaderController.getTheirCurrentDraftProjectId());
        if (myPEDVinculation == null) {
            myPEDVinculation = new PEDVinculationEntity();
        }

        DraftProjectEntity myDraftProject = new DraftProjectEntity();
        myDraftProject.setDraftProjectId(theirdraftProjectHeaderController.getTheirCurrentDraftProjectId());
        myPEDVinculation.setDraftProjectId(myDraftProject);

        if (!(itsStrategicAxisId == null || (itsStrategicAxisId == 0))) {
            ObjectiveEntity myAxis = new ObjectiveEntity();
            myAxis.setObjectiveId(itsStrategicAxisId);
            myPEDVinculation.setVinculationAxis(myAxis);
        }

        if (!(itsProgramId == null || (itsProgramId == 0))) {
            ObjectiveEntity myProg = new ObjectiveEntity();
            myProg.setObjectiveId(itsProgramId);
            myPEDVinculation.setVinculationProgram(myProg);
        }

        if (!(itsSubProgramId == null || (itsSubProgramId == 0))) {
            ObjectiveEntity mySubProg = new ObjectiveEntity();
            mySubProg.setObjectiveId(itsSubProgramId);
            myPEDVinculation.setVinculationSubProgram(mySubProg);
        }

        if (!(aProblemId == null || (aProblemId == 0))) {
            ProblemEntity myProb = new ProblemEntity();
            myProb.seProblemId(aProblemId);
            myPEDVinculation.setVinculationProblem(myProb);
        }
        if (!(aSpecificObId == null || (aSpecificObId == 0))) {
            SpecificObjectiveEntity mySP = new SpecificObjectiveEntity();
            mySP.setSpecificObjectiveId(aSpecificObId);
            myPEDVinculation.setVinculationSpecificObjective(mySP);
        }
        
        if(anObjectiveOfPI != null && anObjectiveOfPI != -1L){
            InstitutionalPlanObjectiveEntity entityPI = new InstitutionalPlanObjectiveEntity();
            entityPI.setInstitutionalPlanObjectiveId(anObjectiveOfPI);
            myPEDVinculation.setVinculationObjetivoPI(entityPI);
        }else{
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.PED.ObjetivoPI"),
                    this.getMessage("ppp.progr.PED.ObjetivoPI")); 
        }



        try {

            theirPEDVinculationManagement.savePEDVinculation(myPEDVinculation);
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    this.getMessage("ppp.progr.PED.successSave"),
                    this.getMessage("ppp.progr.PED.successSave"));

        } catch (Exception exc) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_FATAL,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }
    }

    /**
     * este metodo crea un lista dummy para mostrar las regiones que existen en
     * la entidad del encuadre del Plan Regional
     *
     * @return
     */
    @Override
    public List<RegionalClassifierEntity> getItsListOfRegions() {
        itsListOfRegions.clear();
        return itsListOfRegions;
    }

    @Override
    public void setItsListOfRegions(List<RegionalClassifierEntity> itsListOfRegions) {
        this.itsListOfRegions = itsListOfRegions;
    }

    @Override
    public String getItsPurposePED() {
        return itsPurposePED;
    }

    @Override
    public void setItsPurposePED(String itsPurposePED) {
        this.itsPurposePED = itsPurposePED;
    }

    @Override
    public String getItsStrategicAxisPED() {
        return itsStrategicAxisPED;
    }

    @Override
    public void setItsStrategicAxisPED(String itsStrategicAxisPED) {
        this.itsStrategicAxisPED = itsStrategicAxisPED;
    }

    @Override
    public List<ProblemEntity> getItsListOfProblems() {
        return itsListOfProblems;
    }

    @Override
    public void setItsListOfProblems(List<ProblemEntity> itsListOfProblems) {
        this.itsListOfProblems = itsListOfProblems;
    }

    @Override
    public List<SpecificObjectiveEntity> getItsListOfSpecificObjectives() {
        return itsListOfSpecificObjectives;
    }

    @Override
    public void setItsListOfSpecificObjectives(List<SpecificObjectiveEntity> itsListOfSpecificObjectives) {
        this.itsListOfSpecificObjectives = itsListOfSpecificObjectives;
    }

    @Override
    public Long getAnObjectiveOfPI() {
        return anObjectiveOfPI;
    }

    @Override
    public void setAnObjectiveOfPI(Long anObjectiveOfPI) {
        this.anObjectiveOfPI = anObjectiveOfPI;
    }

    @Override
    public List<InstitutionalPlanEntity> getItsListOfPI() {
        itsListOfPI.clear();
        itsListOfPI = theirInstitutionalPlanManagement.getInstitutionalPlanByDependenceId(itsDependenceId);
        return itsListOfPI;
    }

    @Override
    public void setItsListOfPI(List<InstitutionalPlanEntity> itsListOfPI) {
        this.itsListOfPI = itsListOfPI;
    }

    @Override
    public Long getAnIndicatorOfPI() {
        return anIndicatorOfPI;
    }

    @Override
    public void setAnIndicatorOfPI(Long anIndicatorOfPI) {
        this.anIndicatorOfPI = anIndicatorOfPI;
    }

    @Override
    public List<ObjectiveJoinLevelTreeviewDto> getMyProgramsNodesObjectives() {
        return myProgramsNodesObjectives;
    }

    @Override
    public void setMyProgramsNodesObjectives(List<ObjectiveJoinLevelTreeviewDto> myProgramsNodesObjectives) {
        this.myProgramsNodesObjectives = myProgramsNodesObjectives;
    }

    @Override
    public Long getItsProgramId() {
        return itsProgramId;
    }

    @Override
    public void setItsProgramId(Long itsProgramId) {
        this.itsProgramId = itsProgramId;
    }

    @Override
    public List<ObjectiveJoinLevelTreeviewDto> getMySubProgramsNodesObjectives() {
        return mySubProgramsNodesObjectives;
    }

    @Override
    public void setMySubProgramsNodesObjectives(List<ObjectiveJoinLevelTreeviewDto> mySubProgramsNodesObjectives) {
        this.mySubProgramsNodesObjectives = mySubProgramsNodesObjectives;
    }

    @Override
    public Long getItsSubProgramId() {
        return itsSubProgramId;
    }

    @Override
    public void setItsSubProgramId(Long itsSubProgramId) {
        this.itsSubProgramId = itsSubProgramId;
    }

    @Override
    public ObjectiveJoinLevelTreeviewDto getItsAxisSelected() {
        return itsAxisSelected;
    }

    @Override
    public void setItsAxisSelected(ObjectiveJoinLevelTreeviewDto itsAxisSelected) {
        this.itsAxisSelected = itsAxisSelected;
    }

    @Override
    public ObjectiveJoinLevelTreeviewDto getItsProgramSelected() {
        return itsProgramSelected;
    }

    @Override
    public void setItsProgramSelected(ObjectiveJoinLevelTreeviewDto itsProgramSelected) {
        this.itsProgramSelected = itsProgramSelected;
    }

    @Override
    public ObjectiveJoinLevelTreeviewDto getItsSubProgramSelected() {
        return itsSubProgramSelected;
    }

    @Override
    public void setItsSubProgramSelected(ObjectiveJoinLevelTreeviewDto itsSubProgramSelected) {
        this.itsSubProgramSelected = itsSubProgramSelected;
    }

    @Override
    public String getItsStrategicAxisKey() {
        return itsStrategicAxisKey;
    }

    @Override
    public void setItsStrategicAxisKey(String itsStrategicAxisKey) {
        this.itsStrategicAxisKey = itsStrategicAxisKey;
    }

    @Override
    public String getItsStrategicAxisName() {
        return itsStrategicAxisName;
    }

    @Override
    public void setItsStrategicAxisName(String itsStrategicAxisName) {
        this.itsStrategicAxisName = itsStrategicAxisName;
    }

    @Override
    public String getItsStrategicAxisDefinition() {
        return itsStrategicAxisDefinition;
    }

    @Override
    public void setItsStrategicAxisDefinition(String itsStrategicAxisDefinition) {
        this.itsStrategicAxisDefinition = itsStrategicAxisDefinition;
    }

    @Override
    public List<RegionalPlanEntity> getMyListOfObjectivesPED() {
        return myListOfObjectivesPED;
    }

    @Override
    public void setMyListOfObjectivesPED(List<RegionalPlanEntity> myListOfObjectivesPED) {
        this.myListOfObjectivesPED = myListOfObjectivesPED;
    }

    @Override
    public void saveListOfObjectives() {
        if (isBtnSrvSaveRegion()) {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                            this.getMessage("ppp.security.errorPED"),
                            this.getMessage("ppp.security.errorPED"));
            return;
        }
        try {

            for (RegionalPlanDto myDto : itsListOfRegionalPlanDto) {
                theirPEDVinculationManagement.deleteAllRegionalPlansByDraftProjectIDAndRegionClassifierId(theirdraftProjectHeaderController.getTheirCurrentDraftProjectId(), myDto.getRegionalPlanId());
            }

            List<Long> DtoSelected = SelectedPlanRow;
            boolean region;
            if (DtoSelected.isEmpty()) {
                region = false;
            } else {
                region = true;
            }
            for (RegionalClassifierPEDDto myDTO : itsListOfRegionalDto) {
                if (myDTO.getRegionalClassifierId().equals(selectedRow.getRegionalClassifierId())) {
                    myDTO.setItsSelectedRegionalPlans(region);
                    break;
                }
            }
            myRegionalDataModel = new RegionalDataModel(itsListOfRegionalDto);
            for (int i = 0; i < DtoSelected.size(); i++) {
                RegionalPlansOfPEDEntity myRegionalToSave = new RegionalPlansOfPEDEntity();
                myRegionalToSave.setPEDdraftProjectId(theirdraftProjectHeaderController.getItsDraftProjectEntity());
                RegionalPlanEntity myPlan = new RegionalPlanEntity();
                myPlan.setRegionalPlanId(Long.parseLong(String.valueOf(DtoSelected.get(i))));
                myRegionalToSave.setRegionalPlansOfPED(myPlan);
                theirPEDVinculationManagement.saveRegionalPlanAndDraftProject(myRegionalToSave);
            }
        } catch (Exception exc) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_FATAL,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }

    }

    @Override
    public void updateListOfObjectives(SelectEvent event) {
        try {
            List<RegionalPlanEntity> Objectives = new ArrayList<RegionalPlanEntity>(theirRegClassifManagement.getRegionalPlanByRegionalClassifier(selectedRow.getRegionalClassifierId()));
            itsListOfRegionalPlanDto.clear();
            List<RegionalPlansOfPEDEntity> myRegionSaved = theirPEDVinculationManagement.getRegionalPlanByDraftProjectId(theirdraftProjectHeaderController.getTheirCurrentDraftProjectId());
            if (!(Objectives == null) || Objectives.isEmpty()) {
                SelectedPlanRow.clear();
                for (RegionalPlanEntity myEntity : Objectives) {
                    boolean myCheck = false;
                    if (!(myRegionSaved == null) || myRegionSaved.isEmpty()) {
                        for (RegionalPlansOfPEDEntity myRegion : myRegionSaved) {
                            if (myEntity.getRegionalPlanId().equals(myRegion.getRegionalPlansOfPED().getRegionalPlanId())) {
                                SelectedPlanRow.add(myRegion.getRegionalPlansOfPED().getRegionalPlanId());
                                myCheck = true;
                                break;
                            }
                        }
                    }
                    RegionalPlanDto myDto = new RegionalPlanDto(myCheck, myEntity.getRegionalPlanId(),
                            myEntity.getRegionalClassifierPlan(),
                            myEntity.getRegionalPlanObjective());
                    itsListOfRegionalPlanDto.add(myDto);

                }

            }
            myRegionalPlanDataModel = new RegionalPlanDataModel(itsListOfRegionalPlanDto);
        } catch (Exception exc) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_FATAL,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }
    }

    @Override
    public RegionalDataModel getMyRegionalDataModel() {
        return myRegionalDataModel;
    }

    @Override
    public void setSelectedRow(RegionalClassifierPEDDto selectedRow) {
        this.selectedRow = selectedRow;
    }

    @Override
    public List<Long> getSelectedPlanRow() {
        return SelectedPlanRow;
    }

    @Override
    public void setSelectedPlanRow(List<Long> SelectedPlanRow) {
        this.SelectedPlanRow = SelectedPlanRow;
    }

    @Override
    public RegionalClassifierPEDDto getSelectedRow() {
        return selectedRow;
    }

    @Override
    public List<RegionalClassifierPEDDto> getItsListOfRegionalDto() {
        return itsListOfRegionalDto;
    }

    @Override
    public void setItsListOfRegionalDto(List<RegionalClassifierPEDDto> itsListOfRegionalDto) {
        this.itsListOfRegionalDto = itsListOfRegionalDto;
    }

    @Override
    public List<RegionalPlanDto> getItsListOfRegionalPlanDto() {
        return itsListOfRegionalPlanDto;
    }

    @Override
    public void setItsListOfRegionalPlanDto(List<RegionalPlanDto> itsListOfRegionalPlanDto) {
        this.itsListOfRegionalPlanDto = itsListOfRegionalPlanDto;
    }

    @Override
    public RegionalPlanDataModel getMyRegionalPlanDataModel() {
        return myRegionalPlanDataModel;
    }

    @Override
    public void setMyRegionalPlanDataModel(RegionalPlanDataModel myRegionalPlanDataModel) {
        this.myRegionalPlanDataModel = myRegionalPlanDataModel;
    }

    @Override
    public List<InstitutionalPlanObjectiveEntity> getItsListOfObjectivesofPI() {
        return itsListOfObjectivesofPI;
    }

    @Override
    public void setItsListOfObjectivesofPI(List<InstitutionalPlanObjectiveEntity> itsListOfObjectivesofPI) {
        this.itsListOfObjectivesofPI = itsListOfObjectivesofPI;
    }

    @Override
    public List<ObjectiveIndicatorEntity> getMyListOfIndicatorsofSP() {
        return myListOfIndicatorsofSP;
    }

    @Override
    public void setMyListOfIndicatorsofSP(List<ObjectiveIndicatorEntity> myListOfIndicatorsofSP) {
        this.myListOfIndicatorsofSP = myListOfIndicatorsofSP;
    }

    @Override
    public String getAindicatorId() {
        return aIndicatorId;
    }

    @Override
    public void setAindicatorId(String aIndicatorId) {
        this.aIndicatorId = aIndicatorId;
    }

    @Override
    public boolean getBtnSrvSave() {
        return btnSrvSave;
    }

    @Override
    public void setBtnSrvSave(boolean itsBtnSrvSave) {
        this.btnSrvSave = itsBtnSrvSave;
    }

    /**
     * @return the btnSrvSaveRegion
     */
    public boolean isBtnSrvSaveRegion() {
        return btnSrvSaveRegion;
    }

    /**
     * @param btnSrvSaveRegion the btnSrvSaveRegion to set
     */
    public void setBtnSrvSaveRegion(boolean btnSrvSaveRegion) {
        this.btnSrvSaveRegion = btnSrvSaveRegion;
    }
}