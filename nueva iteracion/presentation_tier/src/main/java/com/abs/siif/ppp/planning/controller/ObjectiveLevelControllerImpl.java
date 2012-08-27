package com.abs.siif.ppp.planning.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import com.abs.siif.planning.management.ObjectiveLevelManagement;
import com.abs.siif.ppp.planning.api.controller.ObjectiveLevelControllerImplApi;
import com.abs.siif.ppp.planning.exception.ObjectiveLevelControllerException;
import com.abs.siif.ppp.planning.uihelpers.ObjectiveLevelDataModel;
import com.abs.siif.ppp.planning.uihelpers.ObjectiveLevelUIHelper;
import com.abs.siif.utility.SaveType;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Componente de comunicación entre la GUI y Business
 *
 * @author Juan Antonio Zavala Aguilar / Erick Leija Cardenas
 */
@Controller("objectiveLevelController")
@Scope("session")
public class ObjectiveLevelControllerImpl extends SIIFControllerBase implements Serializable, ObjectiveLevelControllerImplApi {

    private transient ObjectiveLevelManagement theirObjectiveLevelManagement;
    private transient ObjectiveLevelDataModel itsObjectiveLevels;
    private SaveType theirSaveType;
    private String itsObjectiveLevelId;
    private boolean itsInputsDisabled;
    private boolean itsInputsDisabledUEG;
    private boolean itsEditInputsDisabled;
    private ObjectiveLevelEntity[] itsSelectedObjectives;
    private boolean itsAddObjectiveLevelDisabled;
    private ObjectiveLevelEntity itsCurrentObjectiveLevel;

    @Resource( name = "optionsController")
    private transient OptionsController optionsController;

    @Override
    public ObjectiveLevelEntity getItsCurrentObjectiveLevel() {
        return itsCurrentObjectiveLevel;
    }

    @Override
    public void setItsCurrentObjectiveLevel(ObjectiveLevelEntity itsCurrentObjectiveLevel) {
        this.itsCurrentObjectiveLevel = itsCurrentObjectiveLevel;
    }

    @Override
    public boolean isItsAddObjectiveLevelDisabled() {
        return itsAddObjectiveLevelDisabled;
    }

    @Override
    public void setItsAddObjectiveLevelDisabled(boolean itsAddObjectiveLevelDisabled) {
        this.itsAddObjectiveLevelDisabled = itsAddObjectiveLevelDisabled;
    }

    @Override
    public ObjectiveLevelEntity[] getItsSelectedObjectives() {
        return itsSelectedObjectives;
    }

    @Override
    public void setItsSelectedObjectives(ObjectiveLevelEntity[] itsSelectedObjectives) {
        this.itsSelectedObjectives = itsSelectedObjectives;
    }

    @Override
    public boolean isItsRequeridFields() {
        return itsRequeridFields;
    }

    @Override
    public void setItsRequeridFields(boolean itsRequeridFields) {
        this.itsRequeridFields = itsRequeridFields;
    }
    private boolean itsRequeridFields;

    @Override
    public boolean isItsEditInputsDisabled() {
        return itsEditInputsDisabled;
    }

    @Override
    public void setItsEditInputsDisabled(boolean itsEditInputsDisabled) {
        this.itsEditInputsDisabled = itsEditInputsDisabled;
    }

    private void prepareEditObjectiveLevel(ObjectiveLevelEntity anEntity) {
        clearObjectiveData();
        theirSaveType = SaveType.Update;
        setObjectiveLevelData(itsCurrentObjectiveLevel, anEntity);
        setDisabled(Boolean.FALSE, Boolean.TRUE);
    }

    private void setObjectiveLevelData(ObjectiveLevelEntity aTargetEntity, ObjectiveLevelEntity anEntity) {
        aTargetEntity.setObjectiveLevel(anEntity.getObjectiveLevel());
        aTargetEntity.setObjectiveLevelDescription(anEntity.getObjectiveLevelDescription().trim());
        aTargetEntity.setObjectiveLevelHasIndicator(anEntity.getObjectiveLevelHasIndicator());
        aTargetEntity.setObjectiveLevelId(anEntity.getObjectiveLevelId());
        aTargetEntity.setObjectiveLevelKey(anEntity.getObjectiveLevelKey().trim());
        aTargetEntity.setObjectiveLevelShowBudgetKey(anEntity.getObjectiveLevelShowBudgetKey());
    }

    @Override
    public boolean isItsInputsDisabled() {
        return itsInputsDisabled;
    }

    @Override
    public void setItsInputsDisabled(boolean itsInputsDisabled) {
        this.itsInputsDisabled = itsInputsDisabled;
    }

    @Override
    public boolean isItsInputsDisabledUEG() {
        return itsInputsDisabledUEG;
    }

    @Override
    public void setItsInputsDisabledUEG(boolean itsInputsDisabledUEG) {
        this.itsInputsDisabledUEG = itsInputsDisabledUEG;
    }

    @Override
    public String getItsObjectiveLevelId() {
        return itsObjectiveLevelId;
    }

    @Override
    public void setItsObjectiveLevelId(String itsObjectiveLevelId) {
        this.itsObjectiveLevelId = itsObjectiveLevelId;
    }

    @Autowired(required = true)
    public ObjectiveLevelControllerImpl(
            @Qualifier("objectiveLevelManagement") ObjectiveLevelManagement anObjectiveLevelManagement) {

        this.theirObjectiveLevelManagement = anObjectiveLevelManagement;
        populateObjectiveLevels();
        itsRequeridFields = Boolean.TRUE;
        itsAddObjectiveLevelDisabled = Boolean.FALSE;
        setDisabled(Boolean.TRUE, Boolean.TRUE);
        itsCurrentObjectiveLevel = new ObjectiveLevelEntity();
        itsCurrentObjectiveLevel.setObjectiveLevel(Short.valueOf(String.valueOf(itsObjectiveLevels.getRowCount() + 1 )));
//            itsCurrentObjectiveLevel.setObjectiveLevelKey(String.valueOf(itsObjectiveLevels.getRowCount() + 1 ));
        clearObjectiveData();
    }

    private void populateObjectiveLevels() {
        itsObjectiveLevels =
                ObjectiveLevelUIHelper.populateObjectiveLevels(theirObjectiveLevelManagement.getAllObjectiveLevels());
    }

    @Override
    public ObjectiveLevelDataModel getItsObjectiveLevels() {
        return itsObjectiveLevels;
    }

    @Override
    public void persistObjectiveLevel() {
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_INFO;
        String myMessage = "";
        try {
            Long myNewIndentity = theirObjectiveLevelManagement.saveOrUpdate(itsCurrentObjectiveLevel);

            itsCurrentObjectiveLevel.setObjectiveLevelId(myNewIndentity);
            ObjectiveLevelEntity myObjectiveLevel = new ObjectiveLevelEntity();
            setObjectiveLevelData(myObjectiveLevel, itsCurrentObjectiveLevel);

            updateObjectiveLevels(myObjectiveLevel);


            myMessage = this.getMapKeyExcpetion("ppp.planning.succesSave");
            prepareNewObjectiveLevel();
        } catch (Exception ex) {
            itsCurrentObjectiveLevel.setObjectiveLevelId(null);
            myMessage = getMapKeyExcpetion(ex.getMessage());
            mySeverity = FacesMessage.SEVERITY_ERROR;
        } finally {
            addMessageCurrentInstance(mySeverity, myMessage, null);
        }
    }

    private void updateObjectiveLevels(ObjectiveLevelEntity anEntity) {

        List<ObjectiveLevelEntity> myObjectives = (List<ObjectiveLevelEntity>) itsObjectiveLevels.getWrappedData();

        if (theirSaveType == SaveType.Save) {
            myObjectives.add(anEntity);
        } else {
            int myIndex = ObjectiveLevelUIHelper.getIndexEditItem(myObjectives, anEntity);
            ObjectiveLevelEntity myEntity = myObjectives.get(myIndex);
            setObjectiveLevelData(myEntity, anEntity);
        }
        ObjectiveLevelUIHelper.populateObjectiveLevels(myObjectives);
        theirSaveType = SaveType.Update;
    }

    private void setDisabled(boolean anInputsDisabled, boolean anEditInputsDisabled) {
        itsInputsDisabled = anInputsDisabled;
//        itsInputsDisabledUEG = anInputsDisabled;
        itsEditInputsDisabled = anEditInputsDisabled;
    }

    @Override
    public void prepareNewObjectiveLevel() {
        theirSaveType = SaveType.Save;
        clearObjectiveData();
        setDisabled(Boolean.FALSE, Boolean.FALSE);
    }

    @Override
    public void cancelPersistObjectiveLevel() {
        theirSaveType = SaveType.None;
        itsRequeridFields = false;
        clearObjectiveData();
        setDisabled(Boolean.TRUE, Boolean.TRUE);
    }

    private void clearObjectiveData() {
        itsSelectedObjectives = null;
        itsCurrentObjectiveLevel.setObjectiveLevelDescription("");
        itsCurrentObjectiveLevel.setObjectiveLevelHasIndicator(Boolean.FALSE);
        itsCurrentObjectiveLevel.setObjectiveLevelId(null);
        itsCurrentObjectiveLevel.setObjectiveLevelKey("");
        itsCurrentObjectiveLevel.setObjectiveLevelShowBudgetKey(Boolean.FALSE);
        itsCurrentObjectiveLevel.setObjectiveLevel(Short.valueOf(String.valueOf(itsObjectiveLevels.getRowCount() + 1 )));
//        itsCurrentObjectiveLevel.setObjectiveLevelKey(String.valueOf(itsObjectiveLevels.getRowCount() + 1 ));

//        itsCurrentObjectiveLevel.setObjectiveLevel((short) 0);
    }

    @Override
    public void deleteObjectives() {
        String myMessage = this.getMapKeyExcpetion("ppp.planning.succesDelete");
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_ERROR;
        try {
            if (itsSelectedObjectives.length <= 0) {

                throw new ObjectiveLevelControllerException(getMapKeyExcpetion("ppp.planning.selectRecords"));
            }
            List<ObjectiveLevelEntity> myObjectiveLevels = ObjectiveLevelUIHelper.mapperIdentities(itsSelectedObjectives);
            theirObjectiveLevelManagement.delete(myObjectiveLevels);
            deleteObjectiveLevelsNotPersist(myObjectiveLevels);
            mySeverity = FacesMessage.SEVERITY_INFO;
            clearObjectiveData();
            itsCurrentObjectiveLevel.setObjectiveLevel(Short.valueOf(String.valueOf(itsObjectiveLevels.getRowCount() - myObjectiveLevels.size() + 1 )));
//            itsCurrentObjectiveLevel.setObjectiveLevelKey(String.valueOf(itsObjectiveLevels.getRowCount() - myObjectiveLevels.size() + 1 ));
            itsAddObjectiveLevelDisabled = Boolean.FALSE;
            setDisabled(Boolean.TRUE, Boolean.TRUE);
        } catch (BaseBusinessException ex) {
            myMessage = getMapKeyExcpetion(ex.getMessage());
        } catch (ObjectiveLevelControllerException e) {
            myMessage = getMapKeyExcpetion(e.getMessage());
        } finally {
            addMessageCurrentInstance(mySeverity, myMessage, "");
            populateObjectiveLevels();
        }
    }

    private void deleteObjectiveLevelsNotPersist(List<ObjectiveLevelEntity> anObjectiveLevels) {
        List<ObjectiveLevelEntity> myObjectiveLevels = (List<ObjectiveLevelEntity>) itsObjectiveLevels.getWrappedData();

        myObjectiveLevels.removeAll(anObjectiveLevels);
        ObjectiveLevelUIHelper.populateObjectiveLevels(myObjectiveLevels);
    }

    @Override
    public void onRowSelect(SelectEvent event) {
        ObjectiveLevelEntity myEntity = (ObjectiveLevelEntity) event.getObject();
        if (itsSelectedObjectives.length > 1) {
            itsAddObjectiveLevelDisabled = Boolean.TRUE;
            clearObjectiveData();
            setDisabled(Boolean.TRUE, Boolean.TRUE);
        } else {
            itsAddObjectiveLevelDisabled = Boolean.FALSE;
            prepareEditObjectiveLevel(myEntity);
        }
    }

    @Override
    public OptionsController getOptionsController(){
        return optionsController;
    }

    @Override
    public void setOptionsController(OptionsController optionsController){
        this.optionsController=optionsController;
    }
}