/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author FENIX-01
 */
@Component("institutionalPlanComponent")
public class InstitutionalPlanUIHelper {
    
    public static InstitutionalPlanEntity setValuesOfEntity(
            Long anInstitutionalPlanId, String anInstitutionalNumPerOficial,
            Date anInstitutionalOfficialPubOnDailyDate, Date anInstitutionalstartDateOfActivities,
            Date anInstitutionalDateOfExtintion, String anInstitutionalFormOfExtintion,
            String anInstitutionalNumberOfLastModif, Date anInstitutionalDateOfLastModif,
            String anInstitutionalInternalReg, Date anInstitutionalInternalRegDate,
            Date anInstitutionalActualDate, String anInstitutionalOrganizational,
            String anInstitutionalLegalBasis, String anInstitutionalMission, String anInstitutionalVision,
            String anInstitutionalDiag, String anInstitutionalGeneralObjective, boolean anInstitutionalInvesEjec,
            boolean anInstitutionalInvesNorm, boolean anInstitutionaBlock, boolean anInstitutionalOrganism,
            DependenceEntity anDependence)
    {
        InstitutionalPlanEntity myInstitutionalPlan = new InstitutionalPlanEntity();
        if (anInstitutionalPlanId != null) {
            myInstitutionalPlan.setInstitutionalPlanId(anInstitutionalPlanId);
        }
        myInstitutionalPlan.setInstitutionaBlock(anInstitutionaBlock);
        myInstitutionalPlan.setInstitutionalActualDate(anInstitutionalActualDate);
        myInstitutionalPlan.setInstitutionalDateOfExtintion(anInstitutionalDateOfExtintion);
        myInstitutionalPlan.setInstitutionalDateOfLastModif(anInstitutionalDateOfLastModif);
        myInstitutionalPlan.setInstitutionalDiag(anInstitutionalDiag);
        myInstitutionalPlan.setInstitutionalFormOfExtintion(anInstitutionalFormOfExtintion);
        myInstitutionalPlan.setInstitutionalGeneralObjective(anInstitutionalGeneralObjective);
        myInstitutionalPlan.setInstitutionalInternalReg(anInstitutionalInternalReg);
        myInstitutionalPlan.setInstitutionalInternalRegDate(anInstitutionalInternalRegDate);
        myInstitutionalPlan.setHasInstitutionalInvesEjec(anInstitutionalInvesEjec);
        myInstitutionalPlan.setHasInstitutionalInvesNorm(anInstitutionalInvesNorm);
        myInstitutionalPlan.setInstitutionalLegalBasis(anInstitutionalLegalBasis);
        myInstitutionalPlan.setInstitutionalMission(anInstitutionalMission);
        myInstitutionalPlan.setInstitutionalNumPerOficial(anInstitutionalNumPerOficial);
        myInstitutionalPlan.setInstitutionalNumberOfLastModif(anInstitutionalNumberOfLastModif);
        myInstitutionalPlan.setInstitutionalOfficialPubOnDailyDate(anInstitutionalOfficialPubOnDailyDate);
        myInstitutionalPlan.setHasInstitutionalOrganism(anInstitutionalOrganism);
        myInstitutionalPlan.setInstitutionalOrganizational(anInstitutionalOrganizational);
        myInstitutionalPlan.setInstitutionalVision(anInstitutionalVision);
        myInstitutionalPlan.setInstitutionalstartDateOfActivities(anInstitutionalstartDateOfActivities);
        myInstitutionalPlan.setDependence(anDependence);
        
        return myInstitutionalPlan;
    }
    
}
