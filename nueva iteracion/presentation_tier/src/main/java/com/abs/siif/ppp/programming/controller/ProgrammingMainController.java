package com.abs.siif.ppp.programming.controller;

import com.abs.siif.ppp.programming.api.controller.ProgMainCtrlApi;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.common.CloseSessionController;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import com.abs.siif.planning.entities.InstitutionalPlanObjectiveEntity;
import com.abs.siif.planning.management.InstitutionalPlanManagement;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.ppp.programming.uihelpers.TreeviewObjectiveByDependenceIUHelper;
import com.abs.siif.ppp.programming.uihelpers.TreeviewProgrammingUiHelper;
import com.abs.siif.programming.management.DependenceProgrammingManagement;
import com.abs.siif.programming.management.DraftProjectManagement;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Este metodo nos sirve para controlar la pagina principal de la Estructura de
 * la Programación
 *
 * @author Erick Leija
 */
@Scope("session")
@Controller("programminMainController")
public class ProgrammingMainController extends SIIFControllerBase
        implements Serializable, ProgMainCtrlApi {

    List<DependenceEntity> itsListOfDependences;
    List<InstitutionalPlanEntity> itsListOfInstitutionalPlans;
    private List<InstitutionalPlanObjectiveEntity> itsListOfObjectivesofPI;
    private TreeNode itsSelectedNode;
    private TreeNode itsSelectedNodeToken;
    private TreeNode itsTokenRoot;
    private TreeNode root;
    private Long aDependenceId;
    private Long aObjectiveOfPI;
    private Long anObjectiveId;
    private ObjectiveJoinLevelTreeviewDto itsProgramSelected;
    private ObjectiveJoinLevelTreeviewDto itsSubProgramSelected;
    private ObjectiveJoinLevelTreeviewDto itsAxisSelected;
    private String itsFatherDescription;
    private Boolean itsDisable;
    private Boolean disableTreeOptionsNew;
    private Boolean disableTreeOptionsEdit;
    private Boolean btnDelete;
    private String itsShortName;
   
    /**
     * Este Management se encarga de las operaciones que se realizan con el
     * arbol de las fichas
     */
    @Resource(name = "draftProjectManagement")
    private transient DraftProjectManagement theirDraftProjectManagement;
    /**
     * Esta inyección se encarga de las operaciones del combo de dependcencias
     */
    @Resource(name = "dependenceProgrammingManagement")
    private transient DependenceProgrammingManagement theirDependenceManagement;
    /**
     * Esta inyección se encarga de las operaciones del combo de los objetivos
     * de los planes institucionales
     */
    @Resource(name = "institutionalPlanManagement")
    private transient InstitutionalPlanManagement theirInstitutionalPlanManagement;
    /**
     * Esta inyección se encarga de las operaciones de pintar el arbol de los
     * objetivos
     */
    @Resource(name = "treeviewObjectiveByDependence")
    private transient TreeviewObjectiveByDependenceIUHelper theirTvObjective;
    @Resource(name = "treeviewProgramming")
    private transient TreeviewProgrammingUiHelper theirTvProgramming;
    @Resource(name = "draftProjectHeaderController")
    private transient DraftProjectHeaderControllerApi theirDraftProjectHeaderController;
    @Resource(name = "closeSessionController")
    private transient CloseSessionController theirCloseSessionController;

    @Override
    public Long getAnObjectiveId() {
        return anObjectiveId;
    }

    @Override
    public void setAnObjectiveId(Long anObjectiveId) {
        this.anObjectiveId = anObjectiveId;
    }

    @Override
    public void init() {

        theirTvObjective.restartNodeTreeview();
        theirTvProgramming.restartNodeTreeview();
        itsSelectedNodeToken = null;
        itsListOfDependences.clear();
        itsTokenRoot = null;
        root = null;
        aDependenceId = null;
        disableTreeOptionsNew = Boolean.TRUE;
        disableTreeOptionsEdit = Boolean.TRUE;
        btnDelete = Boolean.TRUE;
        itsFatherDescription = "";
        itsListOfDependences.clear();
        itsListOfDependences = theirDependenceManagement.getDependencesByUEG();
    }

    /**
     * este es el constructor de la clase el cual se encarga de inicializar
     * algunas listas que se necesitan para empezar la ejecucion de la misma
     */
    public ProgrammingMainController() {
        itsListOfDependences = new ArrayList<DependenceEntity>();
        itsListOfInstitutionalPlans = new ArrayList<InstitutionalPlanEntity>();
        this.itsListOfObjectivesofPI = new ArrayList<InstitutionalPlanObjectiveEntity>();
        itsListOfDependences.clear();
        //aqui le vas a poner la conexion al DAO para que te traiga todas las
        //dependencias que tienen marcada la opción de encuadre UEG. tomala papa
    }

    /**
     * Este metodo nos sirve para obtener el Id de la dependencia que estamos
     * trabajando
     *
     * @return Un String que contiene el Id de la dependencia Seleccionada
     */
    @Override
    public Long getADependenceId() {
        return aDependenceId;
    }

    /**
     * Este metodo nos sirve para definir el Id de la dependencia seleccionada
     *
     * @param aDependenceId
     */
    @Override
    public void setADependenceId(Long aDependenceId) {
        theirDraftProjectHeaderController.setIdDependency(aDependenceId);
        this.aDependenceId = aDependenceId;
    }

    /**
     * Este metodo nos sirve para obtener el Id del objetivo General
     * Seleccionado en el combo
     *
     * @return un String con el Id del Plan Institucional
     */
    @Override
    public Long getAObjectiveOfPI() {
        return aObjectiveOfPI;
    }

    /**
     * este metodo nos sirve para definir el Id del plan institucional
     * seleccionado
     *
     * @param aObjectiveOfPI
     */
    @Override
    public void setAObjectiveOfPI(Long aObjectiveOfPI) {
        this.aObjectiveOfPI = aObjectiveOfPI;
    }

    /**
     * Se define la lista de dependencias que estaran poblando el combo de las
     * dependencias
     *
     * @return regresa una lista de propiedades que seran utilizadas en la
     * presentacion
     */
    @Override
    public List<DependenceEntity> getItsListOfDependences() {
        return itsListOfDependences;
    }

    /**
     * aqui se define la lista de las dependencias que seran utlizadas
     *
     * @param itsListOfDependences
     */
    @Override
    public void setItsListOfDependences(List<DependenceEntity> itsListOfDependences) {
        this.itsListOfDependences = itsListOfDependences;
    }

    /**
     * En este metodo definimos la lista de los planes institucionales que seran
     * mostrados
     *
     * @return regresa la lista de planes institucionales que sea definida en la
     * clase
     */
    @Override
    public List<InstitutionalPlanEntity> getItsListOfInstitutionalPlans() {
        return itsListOfInstitutionalPlans;
    }

    /**
     * aqui se define la lista de los planes institucionales a mostrar
     *
     * @param itsListOfInstitutionalPlans
     */
    @Override
    public void setItsListOfInstitutionalPlans(List<InstitutionalPlanEntity> itsListOfInstitutionalPlans) {
        this.itsListOfInstitutionalPlans = itsListOfInstitutionalPlans;
    }

    /**
     * este metodo se encarga de llenar las listas necesarias para la utlizacion
     * de la pantalla(el combo de Objetivos Especificos, el Arbol de Solo
     * Programas y el arbol de las fichas)
     */
    @Override
    public void ListOfInstitutionalPlansAndObjectiveEntity() 
    {
        //Se inicializan las listas
        setDisableTreeOptionsNew(Boolean.TRUE);
        setDisableTreeOptionsEdit(Boolean.TRUE);
        btnDelete = Boolean.TRUE;
        itsListOfInstitutionalPlans.clear();
        root = null;
        itsTokenRoot = null;
        theirTvObjective.restartNodeTreeview();
        if (aDependenceId > 0) {
            DependenceEntity selected = new DependenceEntity();
            selected.setDependenceId(aDependenceId);
            selected = SearchList.findObjectList(itsListOfDependences, selected);
            this.itsFatherDescription = selected.getFather().getDependenceKey() + " "
                    + selected.getFather().getDependenceDescription();

            //se inicia el llenado de las listas
            itsListOfInstitutionalPlans = 
                    theirInstitutionalPlanManagement.
                    getInstitutionalPlanByDependenceChildId(aDependenceId);
            itsListOfObjectivesofPI.clear();
            if (!(itsListOfInstitutionalPlans == null) || 
                    (itsListOfInstitutionalPlans.isEmpty())) 
            {
                itsListOfObjectivesofPI = theirInstitutionalPlanManagement.
                        searchInstPlanObjectives
                        (itsListOfInstitutionalPlans.get
                        (itsListOfInstitutionalPlans.size() - 1));
            }

            theirTvObjective.restartNodeTreeview();
            root = theirTvObjective.getNodeTreeview(aDependenceId);
        }
    }

    /**
     * este metodo obtiene el arbol de objetivos que sera mostrado en la
     * presentación
     *
     * @return
     */
    @Override
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Este metodo obtiene el arbol de fichas que seran mostradas en la
     * presentación
     *
     * @return
     */
    @Override
    public TreeNode getItsTokenRoot() {
        if (itsTokenRoot != null) {
            TreeNode parent = itsTokenRoot.getParent();
        }
        return itsTokenRoot;
    }

    /**
     * con este metodo se obtiene el nodo seleccionado del arbol de objetivos
     *
     * @return
     */
    @Override
    public TreeNode getItsSelectedNode() {
        return itsSelectedNode;
    }

    /**
     * este metodo sirve para definir los datos del nodo seleccionado en la
     * presentacion
     *
     * @param itsSelectedNode
     */
    @Override
    public void setItsSelectedNode(TreeNode itsSelectedNode) {
        this.itsSelectedNode = itsSelectedNode;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public Boolean getDisableTreeOptionsNew() {
        return disableTreeOptionsNew;
    }
    
    /**
     * 
     * @param disableTreeOptionsNew 
     */
    @Override
    public void setDisableTreeOptionsNew(Boolean disableTreeOptionsNew) {
        this.disableTreeOptionsNew = disableTreeOptionsNew;
    }
    
    /**
    * 
    * @return 
    */
    @Override
    public Boolean getDisableTreeOptionsEdit() {
        return disableTreeOptionsEdit;
    }
    
    /**
     * 
     * @param disableTreeOptionsEdit 
     */
    @Override
    public void setDisableTreeOptionsEdit(Boolean disableTreeOptionsEdit) {
        this.disableTreeOptionsEdit = disableTreeOptionsEdit;
    }
    
    /**
     * Este metodo se ejecuta cuando seleccionamos un nodo del objetivos y a su
     * vez nos muestra el arbol de fichas, el cual se obtiene por el encuadre de
     * plan estrategico y la programación
     *
     * @param event
     */
    @Override
    public void onNodeSelect(NodeSelectEvent event) {
        setDisableTreeOptionsNew(Boolean.TRUE);
        setDisableTreeOptionsEdit(Boolean.TRUE);
        btnDelete = Boolean.TRUE;
        itsTokenRoot = null;
        if (itsSelectedNode.getData().getClass().toString().compareTo("class java.lang.String") != 0) {
            //se obtienen la lista de las fichas que resultan del encuadre el
            //Id que tiene el nodo seleccionado
            //para mandarlo como parametro en

            ObjectiveJoinLevelTreeviewDto theirSelectedNode =
                    (ObjectiveJoinLevelTreeviewDto) itsSelectedNode.getData();
            if (theirSelectedNode.getItsHasProgramaticLink() != null && 
                    theirSelectedNode.getItsHasProgramaticLink().compareTo("true") == 0) {
                anObjectiveId = theirSelectedNode.getItsObjectiveId();
                loadTreeview(aDependenceId, anObjectiveId);
            }
        }
    }
    /**
     * 
     * @param event 
     */
    @Override
    public void onNodeSelectTree(NodeSelectEvent event){       
         ObjectiveJoinLevelTreeviewDto theirSelectedNode =
                    (ObjectiveJoinLevelTreeviewDto) itsSelectedNodeToken.getData();
         setItsShortName(theirSelectedNode.getItsNodeText());
         if (theirSelectedNode.getItsHasProgramaticLink() != null) {
             setDisableTreeOptionsEdit(Boolean.FALSE);
             btnDelete = Boolean.FALSE;
             setDisableTreeOptionsNew(Boolean.TRUE);
         }else{
             setDisableTreeOptionsEdit(Boolean.TRUE);
             btnDelete = Boolean.TRUE;
             setDisableTreeOptionsNew(Boolean.FALSE);
         }
        
    }
    
    /**
     * 
     * @param event 
     */
    @Override
    public void onNodeUnselect(NodeUnselectEvent event){       
         setDisableTreeOptionsNew(Boolean.TRUE);
         setDisableTreeOptionsEdit(Boolean.TRUE);
         btnDelete = Boolean.TRUE;
    }

    @Override
    public void restartTreeview() 
    {
        if (!(itsSelectedNode.getData() == null)) {
            ObjectiveJoinLevelTreeviewDto theirSelectedNode =
                    (ObjectiveJoinLevelTreeviewDto) itsSelectedNode.getData();
            loadTreeview(aDependenceId, theirSelectedNode.getItsObjectiveId());
            setDisableTreeOptionsNew(Boolean.TRUE);
            setDisableTreeOptionsEdit(Boolean.TRUE);
            btnDelete = Boolean.TRUE;
        }
    }

    private void loadTreeview(Long aDependencyId, Long anObjectiveId) {
        theirTvProgramming.restartNodeTreeview();
        itsTokenRoot = theirTvProgramming.getNodeTreeview(aDependencyId, anObjectiveId);


    }

    /**
     * nos sirve para obtener los datos del nodo seleccionado en el arbol de
     * fichas
     *
     * @return regresa un objeto (TreeNode) que se puede castear como
     * (DraftProjectTreeviewDto)
     */
    @Override
    public TreeNode getItsSelectedNodeToken() {
        return itsSelectedNodeToken;
    }

    /**
     * sirve para definir cual nodo ha sido seleccionado
     *
     * @param itsSelectedNodeToken
     */
    @Override
    public void setItsSelectedNodeToken(TreeNode itsSelectedNodeToken) {
        this.itsSelectedNodeToken = itsSelectedNodeToken;
    }

    @Override
    public String navigateDraftProject() {
        theirDraftProjectHeaderController.setTheirObjectiveId(anObjectiveId);
        getDataForDraftProject();
        return "newDraftProject";

    }

    @Override
    public void preparateEditProgramming() {
        ObjectiveJoinLevelTreeviewDto myProgramm = (ObjectiveJoinLevelTreeviewDto) itsSelectedNodeToken.getData();
        Long myDraftProjectId = myProgramm.getItsObjectiveLevelId();
        Long myProgrammingId = myProgramm.getItsObjectiveId();
        theirDraftProjectHeaderController.setBtnSrvSave(false);
        theirDraftProjectHeaderController.setItsTabActiveIndex(0);
        theirDraftProjectHeaderController.setTheirProgrammingId(myProgrammingId);
        theirDraftProjectHeaderController.setTheirCurrentDraftProjectId(myDraftProjectId);
        theirDraftProjectHeaderController.setItsDisabledTab(false);
        getDataForDraftProject();
    }

    @Override
    public void getDataForDraftProject() {
        theirDraftProjectHeaderController.setItsSubProgramSelected((ObjectiveJoinLevelTreeviewDto) itsSelectedNode.getData());
        theirDraftProjectHeaderController.setItsProgramSelected((ObjectiveJoinLevelTreeviewDto) itsSelectedNode.getParent().getData());
        theirDraftProjectHeaderController.setItsAxisSelected((ObjectiveJoinLevelTreeviewDto) itsSelectedNode.getParent().getParent().getData());
        theirDraftProjectHeaderController.setIdDependency(aDependenceId);
        theirDraftProjectHeaderController.setTheirObjectiveId(anObjectiveId);
        theirDraftProjectHeaderController.setTheirObjectiveId(anObjectiveId);
    }

    @Override
    public void deleteDraftProject(){
        ObjectiveJoinLevelTreeviewDto myProgramm = (ObjectiveJoinLevelTreeviewDto) itsSelectedNodeToken.getData();
        theirDraftProjectManagement.deleteDraftProject(myProgramm.getItsObjectiveLevelId());
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
    public Boolean getItsDisable() {
        return itsDisable;
    }

    @Override
    public void setItsDisable(Boolean itsDisable) {
        this.itsDisable = itsDisable;
    }

    /**
     * formatea llave de la dependencua a 5 digitos
     *
     * @param key llave dependencia
     * @return llave formateada a 5 digitos
     */
    @Override
    public String formatDependenceKey(String key) {
        for (int i = key.length(); i < 5; i++) {
            key = "0" + key;
        }
        return key;
    }

    /**
     * @return the itsFatherDescription
     */
    @Override
    public String getFatherDescription() {
        return itsFatherDescription;
    }

    /**
     * @param itsFatherDescription the itsFatherDescription to set
     */
    @Override
    public void setFatherDescription(String itsFatherDescription) {
        this.itsFatherDescription = itsFatherDescription;
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
    public String getItsShortName()
    {
        return itsShortName;
    }

    @Override
    public void setItsShortName(String itsShortName)
    {
        this.itsShortName = itsShortName;
    }

    /**
     * @return the btnDelete
     */
    @Override
    public Boolean getBtnSrvDelete() {
        return btnDelete;
    }

    /**
     * @param btnDelete the btnDelete to set
     */
    @Override
    public void setBtnSrvDelete(Boolean btnDelete) {
        this.btnDelete = btnDelete;
    }
    
}
