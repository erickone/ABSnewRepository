package com.abs.siif.ppp.planning.controller;



import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import com.abs.siif.planning.management.DependenceManagement;
import com.abs.siif.planning.management.InstitutionalPlanManagement;
import com.abs.siif.ppp.planning.api.controller.InstitutionalPlanHeaderControllerApi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 *
 * @author slave
 */
@Controller
@Scope("session")
public class InstitutionalPlanHeaderController implements Serializable, InstitutionalPlanHeaderControllerApi
{
    @Resource
    private transient InstitutionalPlanController theirInstitutionalPlan;

    List<DependenceEntity> itsListOfDependences = new ArrayList<DependenceEntity>();
    List<InstitutionalPlanEntity> itsListOfInstitutionalPlans = new ArrayList<InstitutionalPlanEntity>();
    DependenceEntity itsSelectedEntity = new DependenceEntity();
    InstitutionalPlanEntity myNewInstitutionalPlan = null;
    private Long aDependeceId;
    private String aDependenceName;
    private Long anInstitutionalPlanId;
    private boolean itsNewInstitutionalPlan;
    private Long anInstitutionalPlanToDeleteId;
    private boolean enabledNewButton;
    
    @Resource(name="dependenceManagement")
    private transient DependenceManagement theirDependence;
    @Resource(name="institutionalPlanManagement")
    private transient InstitutionalPlanManagement theirInstitutionalPlanMgr;

    @Override
    public List<DependenceEntity> getListOfDependences()
    {
       return itsListOfDependences;
    }

    @Override
    public DependenceEntity getFirstDependenceOfList()
    {
        List<DependenceEntity> myListOfDependences = new ArrayList<DependenceEntity>();
        myListOfDependences = getListOfDependences();
        itsSelectedEntity =  myListOfDependences.get(0);
        return itsSelectedEntity;
    }

    @Override
   public Long getADependeceId() {
        return aDependeceId;
    }

    @Override
   public void init()
   {
       itsListOfDependences.clear();
        for(DependenceEntity myDependenceData : theirDependence.getViewDependenciesTypeUR())
        {
            DependenceEntity myDependenceEntity = new DependenceEntity();
            myDependenceEntity.setDependenceId(myDependenceData.getDependenceId());
            myDependenceEntity.setDependenceDescription(myDependenceData.getDependenceKey() + " "
                    + myDependenceData.getDependenceDescription());
            itsListOfDependences.add(myDependenceEntity);
        }
       DependenceEntity myDependence = getFirstDependenceOfList();
       aDependeceId = myDependence.getDependenceId();
       populateInstitutionalPlanTable();
   }

    @Override
    public boolean isEnabledNewButton() {
        return enabledNewButton;
    }

    @Override
    public void setEnabledNewButton(boolean enabledNewButton) {
        this.enabledNewButton = enabledNewButton;
    }
   
    public void populateInstitutionalPlanTable()
    {
        itsListOfInstitutionalPlans.clear();


        DependenceEntity myDependenceListOfIP = (DependenceEntity)theirDependence.getDependenceById(aDependeceId);
        this.aDependenceName= myDependenceListOfIP.getDependenceDescription();


         for (InstitutionalPlanEntity myInstitutionalPlanData : myDependenceListOfIP.getInstitutionalPlans())
         {
            InstitutionalPlanEntity myInstitutionalPlanEntity = new InstitutionalPlanEntity();
            myInstitutionalPlanEntity.setInstitutionalPlanId(myInstitutionalPlanData.getInstitutionalPlanId());
            myInstitutionalPlanEntity.setInstitutionalGeneralObjective(myInstitutionalPlanData.getInstitutionalGeneralObjective());
            itsListOfInstitutionalPlans.add(myInstitutionalPlanEntity);
            myInstitutionalPlanEntity = null;
            this.itsNewInstitutionalPlan = false;
            this.setAnInstitutionalPlanId( myInstitutionalPlanData.getInstitutionalPlanId());
            
         }
         if (itsNewInstitutionalPlan)
        {
            myNewInstitutionalPlan = new InstitutionalPlanEntity();
            itsNewInstitutionalPlan=false;
        }
        else
        {
           myNewInstitutionalPlan = theirInstitutionalPlanMgr.getInstitutionalPlanById(anInstitutionalPlanId);
        }
        makeSettersAndGetters();
        Collections.sort(itsListOfInstitutionalPlans, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                InstitutionalPlanEntity e1 = (InstitutionalPlanEntity) o1;
                InstitutionalPlanEntity e2 = (InstitutionalPlanEntity) o2;
                return e1.getInstitutionalPlanId().compareTo(e2.getInstitutionalPlanId());
            }
        });
        if(itsListOfInstitutionalPlans.size() == 1){
           setEnabledNewButton(Boolean.TRUE);
        }else{
           setEnabledNewButton(Boolean.FALSE);
        }


    }
    @Override
    public void setADependeceId(Long aDependeceId) {
        this.aDependeceId = aDependeceId;
    }

    @Override
    public List<InstitutionalPlanEntity> getItsListOfInstitutionalPlans() {
        return itsListOfInstitutionalPlans;
    }

    @Override
    public void setItsListOfInstitutionalPlans(List<InstitutionalPlanEntity> itsListOfInstitutionalPlans) {
        this.itsListOfInstitutionalPlans = itsListOfInstitutionalPlans;
    }

    @Override
    public String getADepdenceName() {
        return aDependenceName;
    }

    @Override
    public void setADepdenceName(String aDepdenceName) {
        this.aDependenceName = aDepdenceName;
    }


    @Override
    public Long getAnInstitutionalPlanId() {
        return anInstitutionalPlanId;
    }

    @Override
    public void setAnInstitutionalPlanId(Long anInstitutionalPlanId) {
        this.anInstitutionalPlanId = anInstitutionalPlanId;

    }

    @Override
    public void makeSettersAndGetters()
    {
        theirInstitutionalPlan.setItsInstitutionalPlanId(myNewInstitutionalPlan.getInstitutionalPlanId());
        theirInstitutionalPlan.setItsInstitutionalDependence(aDependeceId);
        theirInstitutionalPlan.setItsInstitutionaBlock(myNewInstitutionalPlan.getInstitutionaBlock());
        theirInstitutionalPlan.setItsInstitutionalActualDate(myNewInstitutionalPlan.getInstitutionalActualDate());
        theirInstitutionalPlan.setItsInstitutionalDateOfExtintion(myNewInstitutionalPlan.getInstitutionalDateOfExtintion());
        theirInstitutionalPlan.setItsInstitutionalDateOfLastModif(myNewInstitutionalPlan.getInstitutionalDateOfLastModif());
        theirInstitutionalPlan.setItsInstitutionalDiag(myNewInstitutionalPlan.getInstitutionalDiag());
        theirInstitutionalPlan.setItsInstitutionalFormOfExtintion(myNewInstitutionalPlan.getInstitutionalFormOfExtintion());
        theirInstitutionalPlan.setItsInstitutionalGeneralObjective(myNewInstitutionalPlan.getInstitutionalGeneralObjective());
        theirInstitutionalPlan.setItsInstitutionalInternalReg(myNewInstitutionalPlan.getInstitutionalInternalReg());
        theirInstitutionalPlan.setItsInstitutionalInternalRegDate(myNewInstitutionalPlan.getInstitutionalInternalRegDate());
        theirInstitutionalPlan.setItsInstitutionalInvesEjec(myNewInstitutionalPlan.getHasInstitutionalInvesEjec());
        theirInstitutionalPlan.setItsInstitutionalInvesNorm(myNewInstitutionalPlan.getHasInstitutionalInvesNorm());
        theirInstitutionalPlan.setItsInstitutionalLegalBasis(myNewInstitutionalPlan.getInstitutionalLegalBasis());
        theirInstitutionalPlan.setItsInstitutionalMission(myNewInstitutionalPlan.getInstitutionalMission());
        theirInstitutionalPlan.setItsInstitutionalNumPerOficial(myNewInstitutionalPlan.getInstitutionalNumPerOficial());
        theirInstitutionalPlan.setItsInstitutionalNumberOfLastModif(myNewInstitutionalPlan.getInstitutionalNumberOfLastModif());
        theirInstitutionalPlan.setItsInstitutionalOfficialPubOnDailyDate(myNewInstitutionalPlan.getInstitutionalOfficialPubOnDailyDate());
        theirInstitutionalPlan.setItsInstitutionalOrganism(myNewInstitutionalPlan.getHasInstitutionalOrganism());
        theirInstitutionalPlan.setItsInstitutionalOrganizational(myNewInstitutionalPlan.getInstitutionalOrganizational());
        theirInstitutionalPlan.setItsInstitutionalVision(myNewInstitutionalPlan.getInstitutionalVision());
        theirInstitutionalPlan.setItsInstitutionalstartDateOfActivities(myNewInstitutionalPlan.getInstitutionalstartDateOfActivities());

    }

    @Override
    public boolean isItsNewInstitutionalPlan() {
        return itsNewInstitutionalPlan;
    }

    @Override
    public void setItsNewInstitutionalPlan(boolean itsNewInstitutionalPlan) {
        this.itsNewInstitutionalPlan = itsNewInstitutionalPlan;
    }

    @Override
    public Long getAnInstitutionalPlanToDeleteId() {
        return anInstitutionalPlanToDeleteId;
    }

    @Override
    public void setAnInstitutionalPlanToDeleteId(Long anInstitutionalPlanToDeleteId) {
        this.anInstitutionalPlanToDeleteId = anInstitutionalPlanToDeleteId;
        theirInstitutionalPlan.setItsInstitutionalToDeleteId(anInstitutionalPlanToDeleteId);
        theirInstitutionalPlan.deleteAnInstitutionalPlan();
        populateInstitutionalPlanTable();
        setEnabledNewButton(Boolean.TRUE);
    }

    @Override
    public void executePopulation()
    {
        itsListOfInstitutionalPlans.clear();
        populateInstitutionalPlanTable();
    }

    @Override
   public void SaveOrUpdateAnInstitutionalPlan()
   {
       Long myId;
       myId = theirInstitutionalPlan.SaveOrUpdateAnInstitutionalPlan();
       theirInstitutionalPlan.saveObjectives();
       theirInstitutionalPlan.setItsInstitutionalPlanId(myId);
       populateInstitutionalPlanTable();
       }

}