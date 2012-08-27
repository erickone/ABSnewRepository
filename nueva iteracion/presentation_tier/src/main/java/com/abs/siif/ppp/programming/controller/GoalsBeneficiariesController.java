package com.abs.siif.ppp.programming.controller;

import com.abs.siif.ppp.programming.api.controller.GoalsBeneficiariesControllerApi;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.data.ExecutionPeriodType;
import com.abs.siif.planning.comparators.BuildingConceptDescpComparator;
import com.abs.siif.ppp.programming.api.controller.FinancialStructureControllerApi;
import com.abs.siif.ppp.programming.uihelpers.GoalsBeneficiariesUIHelper;
import com.abs.siif.programming.entities.ActionGBEntity;
import com.abs.siif.programming.entities.BenefAndGoalsEntity;
import com.abs.siif.programming.entities.BuildingConceptEntity;
import com.abs.siif.programming.management.ActionGBManagement;
import com.abs.siif.programming.management.BenefAndGoalsManagement;
import com.abs.siif.programming.management.BuildingConceptManagement;
import com.abs.siif.support.FormatNumber;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FENIX-02
 */
@Controller("goalsBeneficiariesController")
@Scope("session")
public class GoalsBeneficiariesController extends SIIFControllerBase
        implements Serializable, GoalsBeneficiariesControllerApi {
    /*
     * theirGoalBenefType value is used to define the Anual and MultiAnual
     * values
     */

    private Long theirSelectedGeneralConceptId;
    private Map<String, List<?>> theirSupportList;
    private List<BuildingConceptEntity> theirBuildingConcepts;
    private Long theirSelectedBuildingConceptId;
    @Resource(name = "benefAndGoalsManagement")
    private transient BenefAndGoalsManagement itsBenefAndGoalsManagement;
    @Resource(name = "buildingConceptManagement")
    private transient BuildingConceptManagement itsBuildingConceptManagement;
    @Resource(name = "actionGBManagement")
    private transient ActionGBManagement itsActionGBManagement;
    @Resource(name="financialStructureController")
    private transient FinancialStructureControllerApi theirfinFinancialStructureController;
    private List<ActionGBEntity> theirActions;
    private Long theirSelectedUnitGoalId;
    private Long theirSelectedUnitBenefAndGoalId;
    private Long theirSelectedVulnerableGroupId;
    private Long theirSelectedActionId;
    private Long theirSelectedInvestmentLineId;
    private Long theirSelectedMacroBuild;
    private String theirSelectedExecutionTime = "";
    private String theirTotalGoal;
    private String theirGoalAnnual;
    private String theirDirects;
    private String theirIndirects;
    private Long InvPreFileId = new Long(0);
    private Long theirCurrentId = new Long(0);
    private boolean disableYearGoal;
    private boolean disableMultGoal;

    @Override
    public Long getTheirCurrentId() {
        return theirCurrentId;
    }

    @Override
    public void setTheirCurrentId(Long theirCurrentId) {
        this.theirCurrentId = theirCurrentId;
    }

    @Override
    public Long getInvPreFileId() {
        return InvPreFileId;
    }

    @Override
    public void setInvPreFileId(Long InvPreFileId) {
        this.InvPreFileId = InvPreFileId;
    }

    @Override
    public String getTheirDirects() {
        return theirDirects;
    }

    @Override
    public void setTheirDirects(String theirDirects) {
        this.theirDirects = theirDirects;
    }

    @Override
    public String getTheirGoalAnnual() {
        return theirGoalAnnual;
    }

    @Override
    public void setTheirGoalAnnual(String theirGoalAnnual) {
        this.theirGoalAnnual = theirGoalAnnual;
    }

    @Override
    public String getTheirIndirects() {
        return theirIndirects;
    }

    @Override
    public void setTheirIndirects(String theirIndirects) {
        this.theirIndirects = theirIndirects;
    }

    @Override
    public String getTheirSelectedExecutionTime() {
        return theirSelectedExecutionTime;
    }

    @Override
    public void setTheirSelectedExecutionTime(String theirSelectedExecutionTime) {
        this.theirSelectedExecutionTime = theirSelectedExecutionTime;
    }

    @Override
    public String getTheirTotalGoal() {
        return theirTotalGoal;
    }

    @Override
    public void setTheirTotalGoal(String theirTotalGoal) {
        this.theirTotalGoal = theirTotalGoal;
    }

    @Override
    public Long getTheirSelectedActionId() {
        return theirSelectedActionId;
    }

    @Override
    public void setTheirSelectedActionId(Long theirSelectedActionId) {
        this.theirSelectedActionId = theirSelectedActionId;
    }

    @Override
    public Long getTheirSelectedInvestmentLineId() {
        return theirSelectedInvestmentLineId;
    }

    @Override
    public void setTheirSelectedInvestmentLineId(Long theirSelectedInvestmentLineId) {
        this.theirSelectedInvestmentLineId = theirSelectedInvestmentLineId;
    }

    @Override
    public Long getTheirSelectedMacroBuild() {
        return theirSelectedMacroBuild;
    }

    @Override
    public void setTheirSelectedMacroBuild(Long theirSelectedMacroBuild) {
        this.theirSelectedMacroBuild = theirSelectedMacroBuild;
    }

    @Override
    public Long getTheirSelectedUnitBenefAndGoalId() {
        return theirSelectedUnitBenefAndGoalId;
    }

    @Override
    public void setTheirSelectedUnitBenefAndGoalId(Long theirSelectedUnitBenefAndGoalId) {
        this.theirSelectedUnitBenefAndGoalId = theirSelectedUnitBenefAndGoalId;
    }

    @Override
    public Long getTheirSelectedUnitGoalId() {
        return theirSelectedUnitGoalId;
    }

    @Override
    public void setTheirSelectedUnitGoalId(Long theirSelectedUnitGoalId) {
        this.theirSelectedUnitGoalId = theirSelectedUnitGoalId;
    }

    @Override
    public Long getTheirSelectedVulnerableGroupId() {
        return theirSelectedVulnerableGroupId;
    }

    @Override
    public void setTheirSelectedVulnerableGroupId(Long theirSelectedVulnerableGroupId) {
        this.theirSelectedVulnerableGroupId = theirSelectedVulnerableGroupId;
    }

    @Override
    public List<ActionGBEntity> getTheirActions() {
        return theirActions;
    }

    @Override
    public void setTheirActions(List<ActionGBEntity> theirActions) {
        this.theirActions = theirActions;
    }

    @Override
    public List<BuildingConceptEntity> getTheirBuildingConcepts() {
        if(theirBuildingConcepts!=null)
            Collections.sort(theirBuildingConcepts, BuildingConceptDescpComparator.getInstance());
        return theirBuildingConcepts;
    }

    @Override
    public Long getTheirSelectedBuildingConceptId() {
        return theirSelectedBuildingConceptId;
    }

    @Override
    public void setTheirSelectedBuildingConceptId(Long theirSelectedBuildingConceptId) {
        this.theirSelectedBuildingConceptId = theirSelectedBuildingConceptId;
    }

    @Override
    public void setTheirBuildingConcepts(List<BuildingConceptEntity> theirBuildingConcepts) {
        this.theirBuildingConcepts = theirBuildingConcepts;
    }

    @Override
    public Long getTheirSelectedGeneralConceptId() {
        return theirSelectedGeneralConceptId;
    }

    @Override
    public void setTheirSelectedGeneralConceptId(Long theirSelectedGeneralConceptId) {
        this.theirSelectedGeneralConceptId = theirSelectedGeneralConceptId;
    }

    @Override
    public Map<String, List<?>> getTheirSupportList() {
        return theirSupportList;
    }

    @Override
    public void setTheirSupportList(Map<String, List<?>> theirSupportList) {
        this.theirSupportList = theirSupportList;
    }

    @Override
    public void loadBuildingConcepts() {
        Collection<BuildingConceptEntity> myBuildingConcepts = itsBuildingConceptManagement.getBuildingConceptsByConceptGeneral(theirSelectedGeneralConceptId);
        theirBuildingConcepts = GoalsBeneficiariesUIHelper.convertBuildingConceptInList(myBuildingConcepts);
        theirActions = new ArrayList<ActionGBEntity>();
        theirSelectedBuildingConceptId = new Long(0);
        theirSelectedActionId = new Long(0);
    }

    @Override
    public void loadBuidingConceptsByEdit() {
        Collection<BuildingConceptEntity> myBuildingConcepts = itsBuildingConceptManagement.getBuildingConceptsByConceptGeneral(theirSelectedGeneralConceptId);
        theirBuildingConcepts = GoalsBeneficiariesUIHelper.convertBuildingConceptInList(myBuildingConcepts);
        loadActionsGB();
        changeOptGoals();
    }

    @Override
    public void loadActionsGB() {
        Collection<ActionGBEntity> myActions = itsActionGBManagement.getActionsByBuildingConcept(theirSelectedBuildingConceptId);
        theirActions = GoalsBeneficiariesUIHelper.convertActionGBInList(myActions);
    }

    @Override
    public void initSupportList() {
        cleanView();
        changeOptGoals();
        theirSupportList = itsBenefAndGoalsManagement.getSupportBenGoalList();
        if (InvPreFileId != null && InvPreFileId > 0) {

            BenefAndGoalsEntity myBenefAndGoal = itsBenefAndGoalsManagement.getBenefGoalsByInvPreFile(InvPreFileId);

            if (myBenefAndGoal != null) {
                String myPeriodTime = (myBenefAndGoal.getExecutionPeriod()
                        == ExecutionPeriodType.ANNUAL ? "0" : "1");

                theirCurrentId = myBenefAndGoal.getBenefAndGoalId();
                setTheirSelectedExecutionTime(myPeriodTime);
                setTheirSelectedUnitGoalId(myBenefAndGoal.getUnitMeasureGoal().getUnitMeasureGoalId());
                setTheirGoalAnnual(FormatNumber.formatNumberDecimal(String.valueOf(myBenefAndGoal.getGoalByYear())));
                setTheirTotalGoal(FormatNumber.formatNumberDecimal(String.valueOf(myBenefAndGoal.getTotalGoal())));
                setTheirDirects(FormatNumber.formatNumberDecimal(String.valueOf(myBenefAndGoal.getBenefDirects())));
                setTheirSelectedUnitBenefAndGoalId(myBenefAndGoal.getUnitMeasureBenef().getUnitMeasureBenefAndGoalId());
                setTheirIndirects(FormatNumber.formatNumberDecimal(String.valueOf(myBenefAndGoal.getBenefIndirects())));
                setTheirSelectedVulnerableGroupId(myBenefAndGoal.getVulnerableGroupBenefAndGoals().getVulnerableGroupId());
                setTheirSelectedGeneralConceptId(myBenefAndGoal.getGeneralConceptBenefAndGoals().getGeneralConceptId());
                setTheirSelectedBuildingConceptId(myBenefAndGoal.getBuildingConceptBenefAndGoals().getBuildingConceptId());
                setTheirSelectedActionId(myBenefAndGoal.getActionGBBenefAndGoals().getActionGBId());
                setTheirSelectedInvestmentLineId(myBenefAndGoal.getInvestmentLineBenefAndGoals().getInvestLineId());
                setTheirSelectedMacroBuild(myBenefAndGoal.getMacroBuildBenefAndGoals().getMacroBuildId());
                loadBuidingConceptsByEdit();
            }
            else{
                setTheirCurrentId(0L);
            }
        }
    }

    @Override
    public void cleanView() {
        setTheirSelectedExecutionTime("");
        setTheirSelectedUnitGoalId(new Long(0));
        setTheirGoalAnnual("");
        setTheirTotalGoal("");
        setTheirDirects("");
        setTheirSelectedUnitBenefAndGoalId(new Long(0));
        setTheirIndirects("");
        setTheirSelectedVulnerableGroupId(new Long(0));
        setTheirSelectedGeneralConceptId(new Long(0));
        setTheirSelectedBuildingConceptId(new Long(0));
        setTheirSelectedActionId(new Long(0));
        setTheirSelectedInvestmentLineId(new Long(0));
        setTheirSelectedMacroBuild(new Long(0));
    }

    @Override
    public void persistGoalAndBenef() {
        String myMessage = this.getMessage("ppp.planning.succesSave");
        Severity myMessageFace = FacesMessage.SEVERITY_INFO;
        try {
            BenefAndGoalsEntity myBenefAndGoal = GoalsBeneficiariesUIHelper.mapperEntity(this);
            theirCurrentId = itsBenefAndGoalsManagement.persist(myBenefAndGoal);
            

        } catch (Exception e) {
            myMessage = this.getMessage(e.getMessage());
            myMessageFace = FacesMessage.SEVERITY_ERROR;
        } finally {
            addMessageCurrentInstance(myMessageFace,
                    myMessage, myMessage);
        }

    }

    /**
     * evgento cuando se cambia la seleccion anualo multianual
     */
    @Override
    public void changeOptGoals() {
        if (getTheirSelectedExecutionTime().equals("0")) {
            setDisableYearGoal(false);
            setDisableMultGoal(true);
            setTheirTotalGoal("");
        } else {
            if (getTheirSelectedExecutionTime().equals("1")) {
                setDisableYearGoal(false);
                setDisableMultGoal(false);
                
            } else {
                setDisableYearGoal(true);
                setDisableMultGoal(true);
                setTheirTotalGoal("");
                setTheirGoalAnnual("");
            }
        }
    }

    @Override
    public void setDisableMultGoal(boolean disableMultGoal) {
        this.disableMultGoal = disableMultGoal;
    }

    @Override
    public boolean getDisableMultGoal() {
        return disableMultGoal;
    }

    @Override
    public void setDisableYearGoal(boolean disableYearGoal) {
        this.disableYearGoal = disableYearGoal;
    }

    @Override
    public boolean getDisableYearGoal() {
        return disableYearGoal;
    }
}
