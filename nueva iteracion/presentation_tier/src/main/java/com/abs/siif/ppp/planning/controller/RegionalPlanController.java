package com.abs.siif.ppp.planning.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalPlanEntity;
import com.abs.siif.planning.management.RegionalPlanManagement;
import com.abs.siif.planning.validator.RegionalPlanValidator;
import com.abs.siif.ppp.planning.api.controller.RegionalPlanControllerApi;
import com.abs.siif.ppp.planning.uihelpers.RegionalPlanDataModel;
import com.abs.siif.ppp.planning.uihelpers.RegionalPlanUIHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jacob.flores
 */
@Controller("RegionalPlanController")
@Scope("session")
public class RegionalPlanController extends SIIFControllerBase 
implements Serializable, RegionalPlanControllerApi{

    private List<RegionalPlanEntity> itsRegionsToShow;
    private Long theirRegionId;
    private String theirRegionalObjectiveDescription;
    private Collection<RegionalClassifierEntity> theirRegionsList;
    private transient RegionalPlanManagement theirRegionalPlanManagement;
    RegionalPlanDataModel theirRegionalDataModel;
    private boolean theirInputsDisabled;
    private SaveType theirSaveType;
    private RegionalPlanEntity theirRegionalPlanEntity;
    @Resource(name = "regionPlanValidator")
    private transient RegionalPlanValidator theirPlanValidator;
  
    
    @Override
    public Long getTheirRegionId() {
        return theirRegionId;
    }

    @Override
    public void setTheirRegionId(Long theirRegionId) {
        this.theirRegionId = theirRegionId;
    }
    private RegionalPlanEntity[] theirSelectedPlans;

    @Override
    public RegionalPlanEntity[] getTheirSelectedPlans() {
        return theirSelectedPlans;
    }

    @Override
    public void setTheirSelectedPlans(RegionalPlanEntity[] theirSelectedPlans) {
        this.theirSelectedPlans = theirSelectedPlans;
    }

    @Override
    public RegionalPlanDataModel getTheirRegionalDataModel() {
        return theirRegionalDataModel;
    }

    @Override
    public boolean isTheirInputsDisabled() {
        return theirInputsDisabled;
    }

    @Override
    public void setTheirInputsDisabled(boolean theirInputsDisabled) {
        this.theirInputsDisabled = theirInputsDisabled;
    }

    @Override
    public void persistRegionalPlan() {
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_INFO;
        String myMessage = "";
        try {
            executeValidator();

            theirRegionalPlanEntity = RegionalPlanUIHelper.mapperRegionalEntity(theirRegionalPlanEntity.getRegionalPlanId(),
                    theirRegionalObjectiveDescription, theirRegionId);

            theirRegionalPlanEntity.setRegionalObjectiveNumber(getConsecutiveNumberRegionalPlan()+1);
            Long myNewIndentity = theirRegionalPlanManagement.persistEntity(theirRegionalPlanEntity);

            theirRegionalPlanEntity.setRegionalPlanId(myNewIndentity);
            RegionalPlanEntity myRegionalPlan = new RegionalPlanEntity();
            setObjectiveLevelData(myRegionalPlan, theirRegionalPlanEntity);

            updateDataModel(myRegionalPlan);

            myMessage = this.getMapKeyExcpetion("ppp.planning.succesSave");

        } catch (Exception ex) {
            theirRegionalPlanEntity.setRegionalPlanId(null);
            myMessage = getMapKeyExcpetion(ex.getMessage());
            mySeverity = FacesMessage.SEVERITY_ERROR;
        } finally {
            addMessageCurrentInstance(mySeverity, myMessage, null);
        }
    }

    private int getConsecutiveNumberRegionalPlan()
    {
        int number = 0;
        if(!(itsRegionsToShow==null || itsRegionsToShow.isEmpty()))
        {
            number = itsRegionsToShow.get(itsRegionsToShow.size()-1).getRegionalObjectiveNumber();
        }
        return number;
    }
   
    private void updateDataModel(RegionalPlanEntity anEntity) {

        List<RegionalPlanEntity> myRegions = (List<RegionalPlanEntity>) theirRegionalDataModel.getWrappedData();

        if (theirSaveType == SaveType.SAVE) {
            myRegions.add(anEntity);
        } else {
            int myIndex = RegionalPlanUIHelper.getIndexEditItem(myRegions, anEntity);
            RegionalPlanEntity myEntity = myRegions.get(myIndex);
            setObjectiveLevelData(myEntity, anEntity);
        }
        itsRegionsToShow = myRegions;
        theirRegionalDataModel = RegionalPlanUIHelper.populateData(myRegions);
        theirSaveType = SaveType.UPDATE;
    }

    private void executeValidator() {
        if (!theirPlanValidator.validateObjectiveNotEmpty(theirRegionalObjectiveDescription)) {
            throw new RuntimeException("El campo objetivo no puede quedar vacio");
        }
    }

    @Autowired(required = true)
    public RegionalPlanController(
            @Qualifier("regionalPlanManagement") RegionalPlanManagement aRegionalPlanManagement) {
        theirRegionalPlanManagement = aRegionalPlanManagement;
        this.theirRegionsList = theirRegionalPlanManagement.getAllRegionalClassifierRP();
        setDisabledInputs(Boolean.TRUE);
        theirRegionalPlanEntity = new RegionalPlanEntity();
    }

    /**
     * @return the theirRegionalObjectiveDescription
     */
    @Override
    public String getRegionalObjectiveDescription() {
        return theirRegionalObjectiveDescription;
    }

    /**
     * @param theirRegionalObjectiveDescription the
     * theirRegionalObjectiveDescription to set
     */
    @Override
    public void setRegionalObjectiveDescription(String theirRegionalObjectiveDescription) {
        this.theirRegionalObjectiveDescription = theirRegionalObjectiveDescription;
    }

    /**
     * @return the theirRegionsList
     */
    @Override
    public Collection<RegionalClassifierEntity> getRegionsList() {
        return theirRegionsList;
    }

    /**
     * @param theirRegionsList the theirRegionsList to set
     */
    @Override
    public void setRegionsList(Collection<RegionalClassifierEntity> aRegionsList) {
        this.theirRegionsList = aRegionsList;
    }

    @Override
    public void onRowSelect(SelectEvent event) 
    {
        RegionalPlanEntity myEntity = (RegionalPlanEntity) event.getObject();
        boolean myDisabledInputs = Boolean.FALSE;
        if (theirSelectedPlans.length > 1) {
            clearData();
            myDisabledInputs = Boolean.TRUE;
            theirSaveType = SaveType.NONE;
        } else {
            prepareEditEntity(myEntity);
            theirSaveType = SaveType.UPDATE;
        }

        setDisabledInputs(myDisabledInputs);

    }

    private void clearData() {
        theirRegionalObjectiveDescription = "";
    }

    private void setDisabledInputs(boolean aDisabled) {
        theirInputsDisabled = aDisabled;
    }

    @Override
    public void loadRegionalPlanByRegionalClassifier() {
        Collection<RegionalPlanEntity> myRegions = theirRegionalPlanManagement.getRegionalPlanByRegionalClassifier(theirRegionId);
        theirRegionalDataModel = RegionalPlanUIHelper.populateData(myRegions);
        itsRegionsToShow = new ArrayList<RegionalPlanEntity>(myRegions);
        clearData();
        setDisabledInputs(Boolean.TRUE);
    }

    @Override
    public void cancelPersist() {
        setDisabledInputs(Boolean.TRUE);
        clearData();
        theirSaveType = SaveType.NONE;
    }

    private void prepareEditEntity(RegionalPlanEntity anEntity) {
        theirRegionalObjectiveDescription = anEntity.getRegionalPlanObjective();
        theirRegionalPlanEntity.setRegionalPlanId(anEntity.getRegionalPlanId());
    }

    @Override
    public void prepareNewEntity() {
        try {
            if (theirRegionId.equals("")) {
                throw new RuntimeException("Seleccione una región para registrar un nuevo objetivo");
            }
            theirRegionalPlanEntity = new RegionalPlanEntity();
            theirSaveType = SaveType.SAVE;
            clearData();
            setDisabledInputs(Boolean.FALSE);
        } catch (Exception e) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
        }

    }

    private void setObjectiveLevelData(RegionalPlanEntity aTargetEntity, RegionalPlanEntity anEntity) {
        aTargetEntity.setRegionalPlanObjective(anEntity.getRegionalPlanObjective());
        aTargetEntity.setRegionalPlanId(anEntity.getRegionalPlanId());
        aTargetEntity.setRegionalClassifierPlan(anEntity.getRegionalClassifierPlan());
        aTargetEntity.setRegionalObjectiveNumber(anEntity.getRegionalObjectiveNumber());
    }

    @Override
    public void deleteRegionalPlans() {
        if (theirSelectedPlans.length <= 0) {
            throw new RuntimeException("Seleccione el registro a eliminar");
        }
        List<RegionalPlanEntity> myRegions = RegionalPlanUIHelper.mapperIdentities(theirSelectedPlans);
        theirRegionalPlanManagement.deleteRegionPlans(myRegions);
        deleteDataNotPersist(myRegions);
        clearData();
        setDisabledInputs(Boolean.TRUE);
    }

    private void deleteDataNotPersist(List<RegionalPlanEntity> anRegionaPlans) {
        List<RegionalPlanEntity> myRegions = (List<RegionalPlanEntity>) theirRegionalDataModel.getWrappedData();

        myRegions.removeAll(anRegionaPlans);
        itsRegionsToShow = myRegions;
        theirRegionalDataModel = RegionalPlanUIHelper.populateData(myRegions);
    }
        
}
