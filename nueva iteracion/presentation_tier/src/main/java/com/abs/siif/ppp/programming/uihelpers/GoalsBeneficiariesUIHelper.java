/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.budget.data.ExecutionPeriodType;
import com.abs.siif.ppp.programming.controller.GoalsBeneficiariesController;
import com.abs.siif.programming.entities.*;
import com.abs.siif.support.FormatNumber;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class GoalsBeneficiariesUIHelper {

    public static List<BuildingConceptEntity> convertBuildingConceptInList(Collection<BuildingConceptEntity> aCollectionEntities) {
        return new ArrayList<BuildingConceptEntity>(aCollectionEntities);
    }

    public static List<ActionGBEntity> convertActionGBInList(Collection<ActionGBEntity> aCollectionEntities) {
        return new ArrayList<ActionGBEntity>(aCollectionEntities);
    }

    public static BenefAndGoalsEntity mapperEntity(GoalsBeneficiariesController aController) {

        if (isAnyFieldEmpty(aController)) {
            throw new RuntimeException("common.all.required");
        }

        BenefAndGoalsEntity myBenefAndGoal = new BenefAndGoalsEntity();
        ExecutionPeriodType myPeriod = (aController.getTheirSelectedExecutionTime().equals("0")
                ? ExecutionPeriodType.ANNUAL : ExecutionPeriodType.MULTI_YEAR);
        UnitMeasureGoalEntity myUnitMeasureGoal = new UnitMeasureGoalEntity();
        UnitMeasureBenefAndGoalEntity myUnitMeasureBenefAndGoal = new UnitMeasureBenefAndGoalEntity();
        GeneralConceptEntity myGeneralConcept = new GeneralConceptEntity();
        BuildingConceptEntity myBuildingConcept = new BuildingConceptEntity();
        ActionGBEntity myAction = new ActionGBEntity();
        InvestmentLineEntity myLine = new InvestmentLineEntity();
        MacroBuildEntity myMacro = new MacroBuildEntity();
        VulnerableGroupEntity myGroup = new VulnerableGroupEntity();
        InvPreFileEntity myInvPreFile = new InvPreFileEntity();


        myUnitMeasureGoal.setUnitMeasureGoalId(aController.getTheirSelectedUnitGoalId());
        myUnitMeasureBenefAndGoal.setUnitMeasureBenefAndGoalId(aController.getTheirSelectedUnitBenefAndGoalId());
        myGeneralConcept.setGeneralConceptId(aController.getTheirSelectedGeneralConceptId());
        myBuildingConcept.setBuildingConceptId(aController.getTheirSelectedBuildingConceptId());
        myAction.setActionGBId(aController.getTheirSelectedActionId());
        myLine.setInvestLineId(aController.getTheirSelectedInvestmentLineId());
        myMacro.setMacroBuildId(aController.getTheirSelectedMacroBuild());
        myGroup.setVulnerableGroupId(aController.getTheirSelectedVulnerableGroupId());
        myInvPreFile.setInvPreFileId(aController.getInvPreFileId());


        myBenefAndGoal.setExecutionPeriod(myPeriod);

        if (myPeriod == ExecutionPeriodType.MULTI_YEAR) {
            myBenefAndGoal.setTotalGoal(Double.valueOf(FormatNumber.removeFormat(aController.getTheirTotalGoal())));
            myBenefAndGoal.setGoalByYear(Double.valueOf(FormatNumber.removeFormat(aController.getTheirGoalAnnual())));
        } else {
            myBenefAndGoal.setGoalByYear(Double.valueOf(FormatNumber.removeFormat(aController.getTheirGoalAnnual())));
        }

        myBenefAndGoal.setUnitMeasureGoal(myUnitMeasureGoal);
        myBenefAndGoal.setBenefDirects(Double.valueOf(FormatNumber.removeFormat(aController.getTheirDirects())));
        myBenefAndGoal.setUnitMeasureBenef(myUnitMeasureBenefAndGoal);
        myBenefAndGoal.setBenefIndirects(Double.valueOf(FormatNumber.removeFormat(aController.getTheirIndirects())));
        myBenefAndGoal.setVulnerableGroupBenefAndGoals(myGroup);
        myBenefAndGoal.setGeneralConceptBenefAndGoals(myGeneralConcept);
        myBenefAndGoal.setBuildingConceptBenefAndGoals(myBuildingConcept);
        myBenefAndGoal.setActionGBBenefAndGoals(myAction);
        myBenefAndGoal.setInvestmentLineBenefAndGoals(myLine);
        myBenefAndGoal.setMacroBuildBenefAndGoals(myMacro);
        myBenefAndGoal.setInvPreFile(myInvPreFile);

        Long myIdentity = (!aController.getTheirCurrentId().equals(0l)
                ? aController.getTheirCurrentId() : null);

        myBenefAndGoal.setBenefAndGoalId(myIdentity);
        return myBenefAndGoal;
    }

    private static boolean isAnyFieldEmpty(GoalsBeneficiariesController aController) {
        boolean isEmpty = ((aController.getTheirSelectedUnitGoalId().equals(0l))
                || (aController.getTheirDirects().trim().equals(""))
                || (aController.getTheirSelectedUnitBenefAndGoalId().equals(0l))
                || (aController.getTheirIndirects().trim().equals(""))
                || (aController.getTheirSelectedGeneralConceptId().equals(0l))
                || (aController.getTheirSelectedBuildingConceptId().equals(0l))
                || (aController.getTheirSelectedActionId().equals(0l))
                || (aController.getTheirSelectedInvestmentLineId().equals(0l))
                || (aController.getTheirSelectedExecutionTime().trim().equals("")));


        if (aController.getTheirSelectedExecutionTime().equals("1")) {
            isEmpty = ((isEmpty)
                    || (aController.getTheirTotalGoal().trim().equals(""))
                    || (aController.getTheirGoalAnnual().trim().equals("")));
        } else {
            isEmpty = ((isEmpty)
                    || (aController.getTheirGoalAnnual().trim().equals("")));
        }

        return isEmpty;
    }
}