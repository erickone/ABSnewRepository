package com.abs.siif.ppp.planning.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.common.dto.SampleEntityDto;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.dto.ObjectiveTreeviewDto;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import com.abs.siif.planning.entities.StrategyEntity;
import com.abs.siif.planning.management.ObjectiveLevelManagement;
import com.abs.siif.planning.management.ObjectiveManagement;
import com.abs.siif.ppp.planning.api.controller.ObjectiveControllerApi;
import com.abs.siif.ppp.planning.exception.ObjectiveException;
import com.abs.siif.ppp.planning.exception.ObjectiveLevelException;
import com.abs.siif.ppp.planning.exception.SpecificObjectiveException;
import com.abs.siif.ppp.planning.uihelpers.ObjectiveEditUIHelper;
import com.abs.siif.ppp.planning.uihelpers.TreeviewObjectiveUIHelper;
import com.abs.siif.ppp.programming.controller.DepToObjLinkController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class ObjectiveController extends SIIFControllerBase
        implements Serializable, ObjectiveControllerApi {

    @Resource(name = "treeviewObjectiveComponent")
    private transient TreeviewObjectiveUIHelper theirTvObjective;
    @Resource(name = "objectiveEditComponent")
    private transient ObjectiveEditUIHelper theirObjectiveEditComponent;
    private TreeNode itsSelectedNode;
    private boolean itsTabViewDisabled = Boolean.FALSE;
    private boolean isEncuadreButtonDisabled = true;
    @Resource(name = "optionsController")
    private transient OptionsController optionsController;
    @Resource(name = "depToObjLinkController")
    private DepToObjLinkController itsDepToObjLinkController;
    @Resource(name = "objectiveLevelManagement")
    ObjectiveLevelManagement itsObjectiveLevel;
    @Resource(name = "objectiveManagement")
    ObjectiveManagement itsObjective;
    private String itsObjectiveKey;
    private TreeNode itsTreeNode;

    @Override
    public OptionsController getOptionsCopntroller() {
        return optionsController;
    }

    @Override
    public void setOptionsController(OptionsController optionsController) {
        this.optionsController = optionsController;
    }

    @Override
    public String getItsDescriptionFather() {
        return theirObjectiveEditComponent.getItsDescriptionFather();
    }

    @Override
    public boolean isItsTabViewDisabled() {
        return itsTabViewDisabled;
    }

    @Override
    public void setItsTabViewDisabled(boolean itsTabViewDisabled) {
        this.itsTabViewDisabled = itsTabViewDisabled;
    }

    @Override
    public boolean isIsEncuadreButtonDisabled() {
        return isEncuadreButtonDisabled;
    }

    @Override
    public void setIsEncuadreButtonDisabled(boolean flag) {
        this.isEncuadreButtonDisabled = flag;
    }

    @Override
    public String getItsSelectedStrategyIdForDelete() {
        return theirObjectiveEditComponent.getItsSelectedStrategyIdForDelete();
    }

    @Override
    public void setItsSelectedStrategyIdForDelete(String itsSelectedStrategyIdForDelete) {
        String myMessage;
        myMessage =
                this.theirObjectiveEditComponent.setItsSelectedStrategyIdForDelete(itsSelectedStrategyIdForDelete);
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, "", getMessage(myMessage, "La Estrategia"));
    }

    @Override
    public String getItsSelectedSpecificObjectiveIdForStrategy() {
        return theirObjectiveEditComponent.getItsSelectedSpecificObjectiveIdForStrategy();
    }

    @Override
    public void setItsSelectedSpecificObjectiveIdForStrategy(String itsSelectedSpecificObjectiveIdForStrategy) throws ObjectiveException {
        this.theirObjectiveEditComponent.setItsSelectedSpecificObjectiveIdForStrategy(itsSelectedSpecificObjectiveIdForStrategy);
    }

    @Override
    public SampleEntityDto getItsSelectedStrategy() {

        return theirObjectiveEditComponent.getItsSelectedStrategy();
    }

    @Override
    public void setItsSelectedStrategy(SampleEntityDto itsSelectedStrategy) throws ObjectiveException {
        this.theirObjectiveEditComponent.setItsSelectedStrategy(itsSelectedStrategy);
    }

    @Override
    public List<SampleEntityDto> getItsStrategiesBySpecificObjective() {
        return theirObjectiveEditComponent.getItsStrategiesBySpecificObjective();
    }

    @Override
    public void setItsStrategiesBySpecificObjective(List<SampleEntityDto> itsStrategiesBySpecificObjective) {
        theirObjectiveEditComponent.setItsStrategiesBySpecificObjective(itsStrategiesBySpecificObjective);

    }

    @Override
    public String getItsSelectedSpecificObjectiveId() {
        return theirObjectiveEditComponent.getItsSelectedSpecificObjectiveId();
    }

    @Override
    public void setItsSelectedSpecificObjectiveId(String itsSelectedSpecificObjectiveId) {
        this.theirObjectiveEditComponent.setItsSelectedSpecificObjectiveId(itsSelectedSpecificObjectiveId);
    }

    @Override
    public SampleEntityDto getItsSelectedDeleteSpecificObjective() {
        return theirObjectiveEditComponent.getItsSelectedDeleteSpecificObjective();
    }

    public void init() {
        String myRootName = this.getMessage("ppp.profr.Admin") + " " + optionsController.getYear();
        itsTreeNode = theirTvObjective.getNodeTreeview(myRootName);
    }

    @Override
    public void setItsSelectedDeleteSpecificObjective(SampleEntityDto itsSelectedDeleteSpecificObjective) throws SpecificObjectiveException {
        String myMessage;
        myMessage =
                theirObjectiveEditComponent.setItsSelectedDeleteSpecificObjective(itsSelectedDeleteSpecificObjective);
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, myMessage, getMessage(myMessage, "El Objetivo especifico"));

    }

    @Override
    public SampleEntityDto getItsSelectedSpecificObjective() {
        return theirObjectiveEditComponent.getItsSelectedSpecificObjective();
    }

    @Override
    public void setItsSelectedSpecificObjective(SampleEntityDto itsSelectedSpecificObjective) throws ObjectiveException {
        theirObjectiveEditComponent.setItsSelectedSpecificObjective(itsSelectedSpecificObjective);
    }

    @Override
    public String getItsSpecificObjectiveDescription() {
        return theirObjectiveEditComponent.getItsSpecificObjectiveDescription();
    }

    @Override
    public void setItsSpecificObjectiveDescription(String itsSpecificObjectiveDescription) {
        this.theirObjectiveEditComponent.setItsSpecificObjectiveDescription(itsSpecificObjectiveDescription);
    }

    @Override
    public List<SampleEntityDto> getItsSpecificObjectives() {
        return theirObjectiveEditComponent.getItsSpecificObjectives();
    }

    @Override
    public String getItsObjectiveName() {
        return theirObjectiveEditComponent.getItsObjectiveName();
    }

    @Override
    public void setItsObjectiveName(String itsObjectiveName) {
        theirObjectiveEditComponent.setItsObjectiveName(itsObjectiveName);
    }

    @Override
    public String getItsObjectiveDefinition() {
        return theirObjectiveEditComponent.getItsObjectiveDefinition();
    }

    @Override
    public void setItsObjectiveDefinition(String itsObjectiveDefinition) {
        theirObjectiveEditComponent.setItsObjectiveDefinition(itsObjectiveDefinition);
    }

    @Override
    public String getItsObjectiveLevelDescription() {
        return theirObjectiveEditComponent.getItsObjectiveLevelDescription();
    }

    @Override
    public void setItsObjectiveLevelDescription(String itsObjectiveLevelDescription) {
        theirObjectiveEditComponent.setItsObjectiveLevelDescription(itsObjectiveLevelDescription);
    }

    @Override
    public short getItsObjectivePriority() {
        return theirObjectiveEditComponent.getItsObjectivePriority();
    }

    @Override
    public void setItsObjectivePriority(short itsObjectivePriority) {
        theirObjectiveEditComponent.setItsObjectivePriority(itsObjectivePriority);
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
    public TreeNode getRoot() {

        return itsTreeNode;
    }

    @Override
    public void prepareEditObjective() throws ObjectiveException {
        try {
            itsTabViewDisabled = Boolean.FALSE;
            theirObjectiveEditComponent.initialInputs();
            ObjectiveTreeviewDto myObjectiveDto = (ObjectiveTreeviewDto) itsSelectedNode.getData();

            String myFatherName = "";
            if (!isObjectiveTreeviewDto(SaveType.UPDATE)) {
                if (itsSelectedNode.getParent().getData().getClass().toString().compareTo("class java.lang.String") == 0) {
                    myFatherName = itsSelectedNode.getParent().getData().toString();
                    theirObjectiveEditComponent.setItsDescriptionFather(myFatherName);
                }


            } else {
                ObjectiveTreeviewDto myFather = (ObjectiveTreeviewDto) itsSelectedNode.getParent().getData();
                myFatherName = myFather.getItsNodeText();
                theirObjectiveEditComponent.setItsDescriptionFather(myFatherName);
                theirObjectiveEditComponent.setTheirFatherId(myFather.getItsObjectiveId());
            }

            //this is to validate the Encuadre button.
            ObjectiveLevelEntity objLevel = itsObjectiveLevel.getObjectiveLevelByLevel(myObjectiveDto.getItsObjectiveLevel());
            setIsEncuadreButtonDisabled(!objLevel.getObjectiveLevelShowBudgetKey());

            //send ObjectiveEntity to DepToObjLinkController
            ObjectiveEntity obj = itsObjective.getObjectiveByIdentity(myObjectiveDto.getItsObjectiveId());
            itsDepToObjLinkController.setItsObjectiveEntity(obj);

            Long myObjectiveId = myObjectiveDto.getItsObjectiveId();
            theirObjectiveEditComponent.prepareEditObjective(myObjectiveId);
        } catch (Exception e) {
            itsTabViewDisabled = Boolean.TRUE;
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, null, getMapKeyExcpetion("ppp.planning.excpNotEditable"));
        }

    }

    @Override
    public void prepareNewSpecificObjectiveData() throws ObjectiveException {

        theirObjectiveEditComponent.prepareNewSpecificObjectiveData();
    }

    @Override
    public Boolean getItsButtonSaveIsDisabled() {
        return theirObjectiveEditComponent.getItsButtonSaveIsDisabled();
    }

    @Override
    public Boolean getItsButtonNewSpecificObjectiveIsDisabled() {
        return theirObjectiveEditComponent.getItsButtonNewSpecificObjectiveIsDisabled();
    }

    @Override
    public void cancelSpecificObjective() throws ObjectiveException {
        theirObjectiveEditComponent.cancelSpecificObjective();
    }

    @Override
    public void saveSpecificObjective() throws SpecificObjectiveException, ObjectiveException {
        try {
            theirObjectiveEditComponent.saveSpecificObjective();
        } catch (Exception ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, "", getMapKeyExcpetion(ex.getMessage()));
        }
    }

    @Override
    public void saveStrategy() throws ObjectiveException {
        try {
            theirObjectiveEditComponent.saveStrategy();
        } catch (Exception ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, "", getMapKeyExcpetion(ex.getMessage()));
        }
    }

    @Override
    public void saveProblem() throws ObjectiveException, SpecificObjectiveException {
        try {
            theirObjectiveEditComponent.saveProblem();
        } catch (Exception ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, "", getMapKeyExcpetion(ex.getMessage()));
        }
    }

    @Override
    public String getItsStrategyDescription() {
        return theirObjectiveEditComponent.getItsStrategyDescription();
    }

    @Override
    public void setItsStrategyDescription(String itsStrategyDescription) {
        this.theirObjectiveEditComponent.setItsStrategyDescription(itsStrategyDescription);
    }

    @Override
    public List<StrategyEntity> getItsStrategies() {
        return theirObjectiveEditComponent.getItsStrategies();
    }

    @Override
    public void prepareNewStrategyData() throws ObjectiveException {
        try {
            theirObjectiveEditComponent.prepareNewStrategyData();

        } catch (ObjectiveException ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, null, getMapKeyExcpetion(ex.getMessage()));
        }
    }

    @Override
    public Boolean getItsButtonSaveStrategyIsDisabled() {
        return theirObjectiveEditComponent.getItsButtonSaveStrategyIsDisabled();
    }

    @Override
    public Boolean getItsButtonNewStrategyIsDisabled() {
        return theirObjectiveEditComponent.getItsButtonNewStrategyIsDisabled();
    }

    @Override
    public void cancelStrategy() throws ObjectiveException {
        theirObjectiveEditComponent.cancelStrategy();
    }

    @Override
    public void saveObjective() throws ObjectiveException {
        try {
            String myMessage;
            myMessage = theirObjectiveEditComponent.saveObjective();
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, "", getMapKeyExcpetion(myMessage));
            theirTvObjective.restartNodeTreeview();
        } catch (ObjectiveException ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, null, getMapKeyExcpetion(ex.getMessage()));
        }


    }

    @Override
    public void populateStrategies() throws ObjectiveException {
        theirObjectiveEditComponent.populateStrategy();

    }

    @Override
    public String getItsProblemDescription() {
        return theirObjectiveEditComponent.getItsProblemDescription();
    }

    @Override
    public void setItsProblemDescription(String itsProblemDescription) {
        this.theirObjectiveEditComponent.setItsProblemDescription(itsProblemDescription);
    }

    @Override
    public List<SampleEntityDto> getItsProblems() {
        return theirObjectiveEditComponent.getItsProblems();
    }

    @Override
    public void prepareNewProblemData() throws ObjectiveException {
        theirObjectiveEditComponent.prepareNewProblemData();
    }

    @Override
    public Boolean getItsButtonSaveProblemIsDisabled() {
        return theirObjectiveEditComponent.getItsButtonSaveProblemIsDisabled();
    }

    @Override
    public Boolean getItsButtonNewProblemIsDisabled() {
        return theirObjectiveEditComponent.getItsButtonNewProblemIsDisabled();
    }

    @Override
    public SampleEntityDto getItsSelectedProblem() {
        return theirObjectiveEditComponent.getItsSelectedProblem();
    }

    @Override
    public void setItsSelectedProblem(SampleEntityDto itsSelectedProblem) throws ObjectiveException {
        theirObjectiveEditComponent.setItsSelectedProblem(itsSelectedProblem);
    }

    @Override
    public SampleEntityDto getItsSelectedDeleteProblem() {
        return theirObjectiveEditComponent.getItsSelectedDeleteProblem();
    }

    @Override
    public void setItsSelectedDeleteProblem(SampleEntityDto itsSelectedDeleteProblem) throws SpecificObjectiveException {
        String myMessage;
        myMessage =
                this.theirObjectiveEditComponent.setItsSelectedDeleteProblem(itsSelectedDeleteProblem);
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO, "", getMessage(myMessage, "El Problema"));

    }

    @Override
    public void cancelProblem() throws ObjectiveException {
        theirObjectiveEditComponent.cancelProblem();
    }

    @Override
    public void prepareNewObjective() throws ObjectiveException {
        ObjectiveTreeviewDto mySelectedNode = getNode();
        String myFatherName = mySelectedNode.getItsNodeText();
        theirObjectiveEditComponent.initialInputs();
        try {
            itsTabViewDisabled = Boolean.FALSE;
            Long myObjectiveId = null;
            Long myObjectiveLevelId = null;

            ObjectiveLevelEntity myObjectiveLevel = null;

            if (mySelectedNode.getItsObjectiveLevel() > 0) {
                myObjectiveId = mySelectedNode.getItsObjectiveId();
                short myNextLevel = (short) (mySelectedNode.getItsObjectiveLevel() + 1);
                myObjectiveLevel = getObjectiveLevel(myNextLevel);
            } else {
                short myInitialLevel = 1;
                myObjectiveLevel = theirObjectiveEditComponent.getObjectiveLevel(myInitialLevel);
            }

            theirObjectiveEditComponent.initialInputs();
            myObjectiveLevelId = myObjectiveLevel.getObjectiveLevelId();
            theirObjectiveEditComponent.prepareNewObjective(myObjectiveId, myObjectiveLevelId,
                    myObjectiveLevel.getObjectiveLevelDescription(), myFatherName);

        } catch (ObjectiveLevelException ex) {

            theirObjectiveEditComponent.setItsDescriptionFather(myFatherName);
            itsTabViewDisabled = Boolean.TRUE;
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, "", getMapKeyExcpetion(ex.getMessage()));

        }

    }

    private ObjectiveLevelEntity getObjectiveLevel(short aLevel) throws ObjectiveLevelException {
        ObjectiveLevelEntity myObjectiveLevel = theirObjectiveEditComponent.getObjectiveLevel(aLevel);
        short myLevel = (short) (aLevel - 1);

        if (myObjectiveLevel == null) {
            throw new ObjectiveLevelException(getMessage("ppp.planning.excpLevelConfigured", "" + myLevel));
        }

        return myObjectiveLevel;
    }

    private Boolean isObjectiveTreeviewDto(SaveType aSaveType) {
        try {
            ObjectiveTreeviewDto mySelectedNode = new ObjectiveTreeviewDto();
            if (aSaveType == aSaveType.SAVE) {
                mySelectedNode = (ObjectiveTreeviewDto) itsSelectedNode.getData();
            } else {
                mySelectedNode = (ObjectiveTreeviewDto) itsSelectedNode.getParent().getData();
            }

            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private ObjectiveTreeviewDto getNode() {
        ObjectiveTreeviewDto mySelectedNode = new ObjectiveTreeviewDto();
        if (isObjectiveTreeviewDto(SaveType.SAVE)) {
            mySelectedNode = (ObjectiveTreeviewDto) itsSelectedNode.getData();

        } else {
            short myInitialLevel = 0;
            mySelectedNode.setItsObjectiveLevel(myInitialLevel);
            mySelectedNode.setItsNodeText(itsSelectedNode.getData().toString());
        }

        return mySelectedNode;
    }

    @Override
    public void deleteObjective() {
        try {
            itsTabViewDisabled = Boolean.FALSE;
            ObjectiveTreeviewDto mySelectedNode = getNode();
            if (mySelectedNode != null) {
                mySelectedNode = (ObjectiveTreeviewDto) itsSelectedNode.getData();

                theirObjectiveEditComponent.deleteSpecificObjective(mySelectedNode.getItsObjectiveId());
                // theirTvObjective.restartNodeTreeview();
                init();
            }
        } catch (Exception ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, "", ex.getMessage());
        }
    }

    @Override
    public String navigateDepToObjLink() {
        itsDepToObjLinkController.setItsEncuadreLevelDesc(this.getItsObjectiveLevelDescription());
        itsDepToObjLinkController.setItsEncuadreName(this.getItsObjectiveName());
        return "DepToObjLink";

    }

    @Override
    public String getItsObjectiveKey() {
        return theirObjectiveEditComponent.getItsObjectiveKey();
    }

    @Override
    public void setItsObjectiveKey(String aObjectiveKey) {
        theirObjectiveEditComponent.setItsObjectiveKey(aObjectiveKey);
    }
}