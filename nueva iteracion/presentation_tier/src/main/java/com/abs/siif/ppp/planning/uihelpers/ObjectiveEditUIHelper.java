/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.common.dto.SampleEntityDto;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.*;
import com.abs.siif.planning.management.ObjectiveLevelManagement;
import com.abs.siif.planning.management.ObjectiveManagement;
import com.abs.siif.ppp.planning.exception.ObjectiveException;
import com.abs.siif.ppp.planning.exception.ObjectiveLevelException;
import com.abs.siif.ppp.planning.exception.SpecificObjectiveException;
import com.abs.siif.support.SearchList;
import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author hunabKu
 */
@Component("objectiveEditComponent")
public class ObjectiveEditUIHelper {

    @Resource(name = "objectiveManagement")
    private ObjectiveManagement theirObjectiveManagement;
    @Resource(name = "objectiveLevelManagement")
    private ObjectiveLevelManagement theirObjectiveLevelManagement;
    private String itsObjectiveName;
    private String itsObjectiveDefinition;
    private String itsObjectiveLevelDescription;
    private short itsObjectivePriority;
    private List<StrategyEntity> itsStrategies = new ArrayList<StrategyEntity>();
    private List<SampleEntityDto> itsProblems = new ArrayList<SampleEntityDto>();
    private SaveType theirObjectiveSaveType;
    private ObjectiveEntity theirSelectedObjective;
    private SampleEntityDto itsSelectedDeleteSpecificObjective;
    protected Boolean itsButtonSaveStrategyIsDisabled;
    protected Boolean itsButtonNewStrategyIsDisabled;
    protected String itsStrategyDescription;
    protected SaveType theirStrategySaveType;
    protected String itsSpecificObjectiveDescription;
    protected SaveType theirSpecificObjectiveSaveType;
    protected Boolean itsButtonSaveIsDisabled;
    protected Boolean itsButtonNewSpecificObjectiveIsDisabled;
    private String itsSelectedSpecificObjectiveId;
    private SpecificObjectiveEntity theirSelectedSpecificObjective;
    private SampleEntityDto itsSelectedStrategy;
    private List<SampleEntityDto> itsStrategiesBySpecificObjective = new ArrayList<SampleEntityDto>();
    private SampleEntityDto itsSelectedSpecificObjective;
    private List<SampleEntityDto> itsSpecificObjectives = new ArrayList<SampleEntityDto>();
    private String itsSelectedSpecificObjectiveIdForStrategy;
    private Map<String, List<SampleEntityDto>> theirStrategiesByObjective = new Hashtable<String, List<SampleEntityDto>>();
    private String itsSelectedStrategyIdForDelete;
    private String itsProblemDescription;
    private Boolean itsButtonSaveProblemIsDisabled;
    private Boolean itsButtonNewProblemIsDisabled;
    private ProblemEntity theirSelectedProblem;
    protected SaveType theirProblemSaveType;
    private SampleEntityDto itsSelectedProblem;
    private SampleEntityDto itsSelectedDeleteProblem;
    private Long theirFatherId;
    private Long theirLevelId;
    private String itsDescriptionFather;
    private String itsObjectiveKey;

    public String getItsObjectiveKey() {
        return itsObjectiveKey;
    }

    public void setItsObjectiveKey(String itsObjectiveKey) {
        this.itsObjectiveKey = itsObjectiveKey;
    }

    public String getItsDescriptionFather() {
        return itsDescriptionFather;
    }

    public void setItsDescriptionFather(String itsDescriptionFather) {
        this.itsDescriptionFather = itsDescriptionFather;
    }

    public SampleEntityDto getItsSelectedDeleteProblem() {
        return itsSelectedDeleteProblem;
    }

    public String setItsSelectedDeleteProblem(SampleEntityDto itsSelectedDeleteProblem) throws SpecificObjectiveException {
        this.itsSelectedDeleteProblem = itsSelectedDeleteProblem;
        String myMessage;
        myMessage = deleteProblem();
        return myMessage;
    }

    public SampleEntityDto getItsSelectedProblem() {
        return itsSelectedProblem;
    }

    public String getItsSelectedStrategyIdForDelete() {
        return itsSelectedStrategyIdForDelete;
    }

    public String setItsSelectedStrategyIdForDelete(String itsSelectedStrategyIdForDelete) {
        this.itsSelectedStrategyIdForDelete = itsSelectedStrategyIdForDelete;
        String myMessage;
        myMessage = deleteStrategy(itsSelectedStrategyIdForDelete);
        return myMessage;
    }

    public String getItsSelectedSpecificObjectiveIdForStrategy() {
        return itsSelectedSpecificObjectiveIdForStrategy;
    }

    public void setItsSelectedSpecificObjectiveIdForStrategy(String itsSelectedSpecificObjectiveIdForStrategy) throws ObjectiveException {
        this.itsSelectedSpecificObjectiveIdForStrategy = itsSelectedSpecificObjectiveIdForStrategy;

    }

    public List<SampleEntityDto> getItsSpecificObjectives() {
        return itsSpecificObjectives;
    }

    public SampleEntityDto getItsSelectedStrategy() {
        return itsSelectedStrategy;
    }

    public void setItsSelectedStrategy(SampleEntityDto itsSelectedStrategy) throws ObjectiveException {
        this.itsSelectedStrategy = itsSelectedStrategy;

        setDefaultValuesForReflection("itsStrategyDescription", itsSelectedStrategy.getItsDescription(),
                "theirStrategySaveType", SaveType.UPDATE);
        setDisabledButtonsForReflection("itsButtonSaveStrategyIsDisabled", "itsButtonNewStrategyIsDisabled", Boolean.FALSE);

        setSelectedStrategyForEdit();
    }

    private void loadStrategiesForSpecificObjective() {
        itsStrategiesBySpecificObjective.clear();
        List<SampleEntityDto> myMap = getMapStrategiesForSpecificObjectiveId(itsSelectedSpecificObjectiveIdForStrategy);
        if (myMap == null) {
            return;
        }
        for (SampleEntityDto myDto : myMap) {
            Long myKey = myDto.getItsId();
            String myValue = myDto.getItsDescription();
            SampleEntityDto mySampleDto = new SampleEntityDto(myKey, myValue);
            mySampleDto.setItsGenericItemId(myKey.toString());
            itsStrategiesBySpecificObjective.add(mySampleDto);
        }


    }

    private void setSelectedStrategyForEdit() {
        List<SampleEntityDto> myMap = getMapStrategiesForSpecificObjectiveId(itsSelectedSpecificObjectiveIdForStrategy);
        if (myMap == null) {
            return;
        }
//        String myDescription = SearchList.
//        itsStrategyDescription = myDescription;
    }

    private List<SampleEntityDto> getMapStrategiesForSpecificObjectiveId(String aSpecificObjectiveId) {
        List<SampleEntityDto> myMap = theirStrategiesByObjective.get(aSpecificObjectiveId);
        return myMap;
    }

    public String getItsSelectedSpecificObjectiveId() {
        return itsSelectedSpecificObjectiveId;
    }

    public void setItsSelectedSpecificObjectiveId(String itsSelectedSpecificObjectiveId) {
        this.itsSelectedSpecificObjectiveId = itsSelectedSpecificObjectiveId;

    }

    public List<SampleEntityDto> getItsStrategiesBySpecificObjective() {

        return itsStrategiesBySpecificObjective;
    }

    public void setItsStrategiesBySpecificObjective(List<SampleEntityDto> itsStrategiesBySpecificObjective) {
        this.itsStrategiesBySpecificObjective = itsStrategiesBySpecificObjective;
    }

    public SampleEntityDto getItsSelectedDeleteSpecificObjective() {
        return itsSelectedDeleteSpecificObjective;
    }

    public String setItsSelectedDeleteSpecificObjective(SampleEntityDto itsSelectedDeleteSpecificObjective) throws SpecificObjectiveException {
        this.itsSelectedSpecificObjective = itsSelectedDeleteSpecificObjective;
        String myMessage;
        myMessage = deleteSpecificObjective();
        return myMessage;

    }

    public SampleEntityDto getItsSelectedSpecificObjective() {
        return itsSelectedSpecificObjective;
    }

    public void setItsSelectedSpecificObjective(SampleEntityDto itsSelectedSpecificObjective) throws ObjectiveException {
        this.itsSelectedSpecificObjective = itsSelectedSpecificObjective;

        setDefaultValuesForReflection("itsSpecificObjectiveDescription", itsSelectedSpecificObjective.getItsDescription(),
                "theirSpecificObjectiveSaveType", SaveType.UPDATE);
        setDisabledButtonsForReflection("itsButtonSaveIsDisabled", "itsButtonNewSpecificObjectiveIsDisabled", Boolean.FALSE);


    }

    public Boolean getItsButtonNewSpecificObjectiveIsDisabled() {
        return itsButtonNewSpecificObjectiveIsDisabled;
    }

    public void setItsButtonNewSpecificObjectiveIsDisabled(Boolean itsButtonNewSpecificObjectiveIsDisabled) {
        this.itsButtonNewSpecificObjectiveIsDisabled = itsButtonNewSpecificObjectiveIsDisabled;
    }

    public Boolean getItsButtonSaveIsDisabled() {
        return itsButtonSaveIsDisabled;
    }

    public void setItsButtonSaveIsDisabled(Boolean itsButtonSaveIsActive) {
        this.itsButtonSaveIsDisabled = itsButtonSaveIsActive;
    }

    public String getItsSpecificObjectiveDescription() {
        return itsSpecificObjectiveDescription;
    }

    public void setItsSpecificObjectiveDescription(String itsSpecificObjectiveDescription) {
        this.itsSpecificObjectiveDescription = itsSpecificObjectiveDescription;
    }

    public String getItsObjectiveDefinition() {
        return itsObjectiveDefinition;
    }

    public void setItsObjectiveDefinition(String itsObjectiveDefinition) {
        this.itsObjectiveDefinition = itsObjectiveDefinition;
    }

    public String getItsObjectiveLevelDescription() {
        return itsObjectiveLevelDescription;
    }

    public void setItsObjectiveLevelDescription(String itsObjectiveLevelDescription) {
        this.itsObjectiveLevelDescription = itsObjectiveLevelDescription;
    }

    public short getItsObjectivePriority() {
        return itsObjectivePriority;
    }

    public void setItsObjectivePriority(short itsObjectivePriority) {
        this.itsObjectivePriority = itsObjectivePriority;
    }

    public void setItsObjectiveName(String itsObjectiveName) {
        this.itsObjectiveName = itsObjectiveName;
    }

    public String getItsObjectiveName() {
        return itsObjectiveName;
    }

    public void prepareEditObjective(Long myObjectiveId) throws ObjectiveException {

        theirObjectiveSaveType = SaveType.UPDATE;
        itsSelectedSpecificObjectiveIdForStrategy = "";
        clearObjectiveData();

        loadDetailsObjective(myObjectiveId);
        loadProblems();
        loadSampleEntityDtoWithSpecificObjective();
    }

    private void loadDetailsObjective(Long myObjectiveId) {

        theirSelectedObjective = theirObjectiveManagement.getObjectiveEagerByIdentity(myObjectiveId);
        itsObjectiveName = theirSelectedObjective.getObjectiveName();
        itsObjectiveKey = theirSelectedObjective.getObjectiveKey();
        itsObjectiveDefinition = theirSelectedObjective.getObjectiveDefinition();
        itsObjectiveLevelDescription = theirSelectedObjective.getObjectiveLevel().getObjectiveLevelDescription();
        itsObjectivePriority = theirSelectedObjective.getObjectivePriority();
    }

    private void loadSampleEntityDtoWithSpecificObjective() {
        theirStrategiesByObjective = new Hashtable<String, List<SampleEntityDto>>();
        for (SpecificObjectiveEntity mySpecificObjective : theirSelectedObjective.getSpecificObjectives()) {
            SampleEntityDto myGenericItem = new SampleEntityDto(
                    mySpecificObjective.getSpecificObjectiveId(), mySpecificObjective.getSpecificObjectiveDescription());
            myGenericItem.setItsGenericItemId(mySpecificObjective.getSpecificObjectiveId().toString());
            this.itsSpecificObjectives.add(myGenericItem);

            if (mySpecificObjective.getStrategies().size() > 0) {
                List<SampleEntityDto> myHashMap = new ArrayList<SampleEntityDto>();
                for (StrategyEntity strategy : mySpecificObjective.getStrategies()) {
                    SampleEntityDto myDto = new SampleEntityDto(strategy.getStrategyId(), strategy.getStrategyDescription());
                    myDto.setItsGenericItemId(strategy.getStrategyId().toString());
                    myHashMap.add(myDto);
                }
                theirStrategiesByObjective.put(mySpecificObjective.getSpecificObjectiveId().toString(), myHashMap);
            }
        }
    }

    public void prepareNewSpecificObjectiveData() throws ObjectiveException {
        setDefaultValuesForReflection("itsSpecificObjectiveDescription", "", "theirSpecificObjectiveSaveType", SaveType.SAVE);
        setDisabledButtonsForReflection("itsButtonSaveIsDisabled",
                "itsButtonNewSpecificObjectiveIsDisabled", Boolean.FALSE);

    }

    private void clearObjectiveData() throws ObjectiveException {

        itsObjectiveKey = "";
        itsObjectiveName = "";
        itsObjectiveDefinition = "";
        itsObjectivePriority = 0;
        itsSpecificObjectives.clear();

        setDefaultValuesForReflection("itsSpecificObjectiveDescription", "", "theirSpecificObjectiveSaveType", SaveType.NONE);
        setDisabledButtonsForReflection("itsButtonSaveIsDisabled",
                "itsButtonNewSpecificObjectiveIsDisabled", Boolean.TRUE);


        setDefaultValuesForReflection("itsStrategyDescription", "",
                "theirStrategySaveType", SaveType.NONE);
        setDisabledButtonsForReflection("itsButtonSaveStrategyIsDisabled", "itsButtonNewStrategyIsDisabled", Boolean.TRUE);

        setDefaultValuesForReflection("itsProblemDescription", "",
                "theirProblemSaveType", SaveType.NONE);
        setDisabledButtonsForReflection("itsButtonSaveProblemIsDisabled", "itsButtonNewProblemIsDisabled", Boolean.TRUE);

    }

    public void cancelSpecificObjective() throws ObjectiveException {
        setDefaultValuesForReflection("itsSpecificObjectiveDescription", "", "theirSpecificObjectiveSaveType", SaveType.NONE);
        setDisabledButtonsForReflection("itsButtonSaveIsDisabled",
                "itsButtonNewSpecificObjectiveIsDisabled", Boolean.TRUE);
    }

    private void updateItem(List<SampleEntityDto> aCollection, String aId, String aDescription) throws SpecificObjectiveException {
        if (aDescription.trim().equals("")) {
            throw new SpecificObjectiveException("ppp.planning.excpDescEmpty");
        }

        int myIndex = getIndexInCollection(aCollection, aId);
        if (myIndex < 0) {
            throw new SpecificObjectiveException("ppp.planning.excpRecordNotFound");
        }

        aCollection.get(myIndex).setItsDescription(aDescription);
    }

    private int getIndexInCollection(List<SampleEntityDto> aCollection, String anId) {
        int myIterator = -1;
        Boolean myMatch = Boolean.FALSE;

        for (Iterator<SampleEntityDto> it = aCollection.iterator(); ((it.hasNext()) && (!myMatch));) {
            SampleEntityDto myItemDto = it.next();
            myMatch = (anId.equals(myItemDto.getItsGenericItemId()));
            myIterator++;
        }

        return myIterator;
    }

    private void addGenericItem(List<SampleEntityDto> aCollection, String aDescription, Boolean aIsStrategy) throws ObjectiveException {
        if (aDescription.trim().equals("")) {
            throw new ObjectiveException("ppp.planning.excpDescEmpty");
        }

        SampleEntityDto mySampleEntityDto = new SampleEntityDto(aDescription);
        aCollection.add(mySampleEntityDto);

        if (aIsStrategy) {
            List<SampleEntityDto> myMap = getMapStrategiesForSpecificObjectiveId(itsSelectedSpecificObjectiveIdForStrategy);
            if (myMap == null) {
                myMap = new ArrayList<SampleEntityDto>();
            }
            myMap.add(mySampleEntityDto);
            theirStrategiesByObjective.put(itsSelectedSpecificObjectiveIdForStrategy, myMap);
        }

    }

    public void saveSpecificObjective() throws SpecificObjectiveException, ObjectiveException {
        switch (theirSpecificObjectiveSaveType) {
            case NONE: {
                break;
            }
            case SAVE: {
                addGenericItem(itsSpecificObjectives, itsSpecificObjectiveDescription, Boolean.FALSE);
                break;
            }
            case UPDATE: {
                updateItem(itsSpecificObjectives, itsSelectedSpecificObjective.getItsGenericItemId(), itsSpecificObjectiveDescription);
                break;
            }
        }

        setDefaultValuesForReflection("itsSpecificObjectiveDescription", "", "theirSpecificObjectiveSaveType", SaveType.NONE);
        setDisabledButtonsForReflection("itsButtonSaveIsDisabled",
                "itsButtonNewSpecificObjectiveIsDisabled", Boolean.TRUE);
    }

    public void saveProblem() throws ObjectiveException, SpecificObjectiveException {

        switch (theirProblemSaveType) {
            case NONE: {
                break;
            }
            case SAVE: {
                addGenericItem(itsProblems, itsProblemDescription, Boolean.FALSE);
                break;
            }
            case UPDATE: {
                updateItem(itsProblems, itsSelectedProblem.getItsGenericItemId(), itsProblemDescription);
                break;
            }
        }
        setDefaultValuesForReflection("itsProblemDescription", "",
                "theirProblemSaveType", SaveType.NONE);
        setDisabledButtonsForReflection("itsButtonSaveProblemIsDisabled", "itsButtonNewProblemIsDisabled", Boolean.TRUE);
    }

    public void saveStrategy() throws ObjectiveException {

        switch (theirStrategySaveType) {
            case NONE: {
                break;
            }
            case SAVE: {
                addGenericItem(itsStrategiesBySpecificObjective, itsStrategyDescription, Boolean.TRUE);
                break;
            }
            case UPDATE: {
                updateStrategy();
                break;
            }
        }
        setDefaultValuesForReflection("itsStrategyDescription", "",
                "theirStrategySaveType", SaveType.NONE);
        setDisabledButtonsForReflection("itsButtonSaveStrategyIsDisabled", "itsButtonNewStrategyIsDisabled", Boolean.TRUE);
    }

    public void cancelStrategy() throws ObjectiveException {
        setDefaultValuesForReflection("itsStrategyDescription", "",
                "theirStrategySaveType", SaveType.NONE);
        setDisabledButtonsForReflection("itsButtonSaveStrategyIsDisabled", "itsButtonNewStrategyIsDisabled", Boolean.TRUE);


    }

    public Boolean getItsButtonNewStrategyIsDisabled() {
        return itsButtonNewStrategyIsDisabled;
    }

    public void setItsButtonNewStrategyIsDisabled(Boolean itsButtonNewStrategyIsDisabled) {
        this.itsButtonNewStrategyIsDisabled = itsButtonNewStrategyIsDisabled;
    }

    public Boolean getItsButtonSaveStrategyIsDisabled() {
        return itsButtonSaveStrategyIsDisabled;
    }

    public void setItsButtonSaveStrategyIsDisabled(Boolean itsButtonSaveStrategyIsActive) {
        this.itsButtonSaveStrategyIsDisabled = itsButtonSaveStrategyIsActive;
    }

    public List<StrategyEntity> getItsStrategies() {
        return itsStrategies;
    }

    public void prepareNewStrategyData() throws ObjectiveException {
        if (itsSelectedSpecificObjectiveIdForStrategy.trim().equals("")) {
            throw new ObjectiveException("ppp.planning.excpInvalidSO");
        }

        setDefaultValuesForReflection("itsStrategyDescription", "",
                "theirStrategySaveType", SaveType.SAVE);
        setDisabledButtonsForReflection("itsButtonSaveStrategyIsDisabled", "itsButtonNewStrategyIsDisabled", Boolean.FALSE);

    }

    public String getItsStrategyDescription() {
        return itsStrategyDescription;
    }

    public void setItsStrategyDescription(String itsStrategyDescription) {
        this.itsStrategyDescription = itsStrategyDescription;
    }

    public String saveObjective() throws ObjectiveException {
        ObjectiveEntity myObjective = new ObjectiveEntity();
        ObjectiveLevelEntity objectiveLevel = new ObjectiveLevelEntity();
        Long myObjectiveLevelId = null;
        switch (theirObjectiveSaveType) {
            case NONE: {
                break;
            }
            case SAVE: {
                myObjectiveLevelId = theirLevelId;
                if (theirFatherId != null) {
                    ObjectiveEntity myFather = new ObjectiveEntity();
                    myFather.setObjectiveId(theirFatherId);
                    myObjective.AssignFather(myFather);
                }
                break;
            }
            case UPDATE: {
                ObjectiveEntity myObjectiveFather = new ObjectiveEntity();
                if (theirFatherId != null) {
                    if (theirSelectedObjective.getFather() != null) {
                        myObjectiveFather.setObjectiveId(theirSelectedObjective.getFather().getObjectiveId());
                        myObjective.AssignFather(myObjectiveFather);
                    }
                }
                myObjective.setObjectiveId(theirSelectedObjective.getObjectiveId());
                myObjective.setObjectiveKey(theirSelectedObjective.getObjectiveKey());
                myObjectiveLevelId = theirSelectedObjective.getObjectiveLevel().getObjectiveLevelId();
                break;
            }
        }
        objectiveLevel.setObjectiveLevelId(myObjectiveLevelId);
        myObjective.AssignObjectiveLevel(objectiveLevel);
        String myMessage;
        myMessage = SaveOrUpdateObjective(myObjective);
        return myMessage;
    }

    private String SaveOrUpdateObjective(ObjectiveEntity myObjective) throws ObjectiveException {
        if ((itsObjectiveName.trim().equals(""))
                || (itsObjectiveKey.trim().equals(""))) {
            throw new ObjectiveException("ppp.planning.strategicPlanNotException");
        }

        myObjective.setObjectiveKey(itsObjectiveKey);
        myObjective.setObjectiveName(itsObjectiveName);
        myObjective.setObjectiveDefinition(itsObjectiveDefinition);
        myObjective.setObjectivePriority(itsObjectivePriority);


        for (SampleEntityDto genericItem : itsSpecificObjectives) {
            List<SampleEntityDto> myStrategies = theirStrategiesByObjective.get(genericItem.getItsGenericItemId());


            SpecificObjectiveEntity mySpecificObjective = new SpecificObjectiveEntity();
            String myDescription = genericItem.getItsDescription();
            Long mySpecificObjectiveId = genericItem.getItsId();
            mySpecificObjective.setSpecificObjectiveId(mySpecificObjectiveId);
            mySpecificObjective.setSpecificObjectiveDescription(myDescription);

            if (myStrategies != null) {
                for (SampleEntityDto myDto : myStrategies) {
                    String myStrategyDescription = myDto.getItsDescription();
                    Long myStrategyId = myDto.getItsId();
                    StrategyEntity myStrategy = new StrategyEntity();

                    myStrategy.setStrategyDescription(myStrategyDescription);
                    myStrategy.setStrategyId(myStrategyId);
                    mySpecificObjective.AddStrategy(myStrategy);
                }

            }
            myObjective.AddSpecificObjective(mySpecificObjective);
        }

        for (SampleEntityDto SampleEntityDto : itsProblems) {
            ProblemEntity myProblem = new ProblemEntity();
            myProblem.seProblemId(SampleEntityDto.getItsId());
            myProblem.setProblemDescription(SampleEntityDto.getItsDescription());

            myObjective.AddProblem(myProblem);
        }


        if (myObjective.getObjectiveId() != null) {
            myObjective.setDependences(theirObjectiveManagement.getChildsRelatedObjList(myObjective.getObjectiveId()));
            myObjective.setObjectiveFunctionalClassifiers(theirObjectiveManagement.getFunctionalClassifiersByObjectiveId(myObjective.getObjectiveId()));

        }

        Long myNewObjectiveId = theirObjectiveManagement.Save(myObjective, theirObjectiveSaveType);

        if (theirObjectiveSaveType == SaveType.SAVE) {
            theirSelectedObjective = new ObjectiveEntity();
            theirSelectedObjective.setObjectiveId(myNewObjectiveId);
            theirSelectedObjective.AssignObjectiveLevel(myObjective.getObjectiveLevel());
            if (myObjective.getFather() != null) {
                theirSelectedObjective.AssignFather(myObjective.getFather());
            }

            theirObjectiveSaveType = SaveType.UPDATE;
        }
        return "ppp.planning.succesSave";
    }

    private void setDefaultValuesForReflection(String aFieldDescription, String aFieldDescriptionValue, String aFieldSaveType, SaveType saveType) throws ObjectiveException {
        try {
            Field myFieldDescription = ObjectiveEditUIHelper.class.getDeclaredField(aFieldDescription);
            myFieldDescription.set(this, aFieldDescriptionValue);

            Field myFieldSaveType = ObjectiveEditUIHelper.class.getDeclaredField(aFieldSaveType);
            myFieldSaveType.set(this, saveType);

        } catch (IllegalArgumentException ex) {
            throw new ObjectiveException("ppp.planning.excpAccess", ex);
        } catch (IllegalAccessException ex) {
            throw new ObjectiveException("ppp.planning.excpAccess", ex);
        } catch (NoSuchFieldException ex) {
            throw new ObjectiveException("ppp.planning.excpField", ex);
        } catch (SecurityException ex) {
            throw new ObjectiveException("ppp.planning.excpSecurity", ex);
        }
    }

    private void setDisabledButtonsForReflection(String aButtonSaveName,
            String aButtonNewName, Boolean aIsDisabled) throws ObjectiveException {
        try {


            Field myFieldButtonSave = ObjectiveEditUIHelper.class.getDeclaredField(aButtonSaveName);
            myFieldButtonSave.set(this, aIsDisabled);

            Field myFieldButtonNew = ObjectiveEditUIHelper.class.getDeclaredField(aButtonNewName);
            myFieldButtonNew.set(this, !aIsDisabled);

        } catch (IllegalArgumentException ex) {
            throw new ObjectiveException("ppp.planning.excpAccess", ex);
        } catch (IllegalAccessException ex) {
            throw new ObjectiveException("ppp.planning.excpAccess", ex);
        } catch (NoSuchFieldException ex) {
            throw new ObjectiveException("ppp.planning.excpField", ex);
        } catch (SecurityException ex) {
            throw new ObjectiveException("ppp.planning.excpSecurity", ex);
        }
    }

    public void populateStrategy() throws ObjectiveException {
        itsStrategiesBySpecificObjective = new ArrayList<SampleEntityDto>();
        setDefaultValuesForReflection("itsStrategyDescription", "",
                "theirStrategySaveType", SaveType.NONE);
        setDisabledButtonsForReflection("itsButtonSaveStrategyIsDisabled", "itsButtonNewStrategyIsDisabled", Boolean.TRUE);

        if (itsSelectedSpecificObjectiveIdForStrategy.equals("")) {
            return;
        }

        loadStrategiesForSpecificObjective();
    }

    private void updateStrategy() throws ObjectiveException {
        if (itsStrategyDescription.trim().equals("")) {
            throw new ObjectiveException("ppp.planning.excpDescEmpty");
        }

        List<SampleEntityDto> myMap = theirStrategiesByObjective.get(itsSelectedSpecificObjectiveIdForStrategy);
        if (myMap == null) {
            return;
        }

        int myIndex = SearchList.indexList(myMap, itsSelectedStrategy);
        if (myIndex >= 0) {
            myMap.get(myIndex).setItsDescription(itsStrategyDescription);
        }

        loadStrategiesForSpecificObjective();
    }

    public String deleteSpecificObjective() throws SpecificObjectiveException {
        int myIndex = getIndexInCollection(itsSpecificObjectives, itsSelectedSpecificObjective.getItsGenericItemId());
        if (myIndex < 0) {
            throw new SpecificObjectiveException("ppp.planning.excpRecordNotFound");
        }
        itsSpecificObjectives.remove(myIndex);

        itsSelectedSpecificObjectiveIdForStrategy = "";
        itsStrategiesBySpecificObjective.clear();
        return "ppp.planning.successDel";

    }

    private String deleteProblem() throws SpecificObjectiveException {
        int myIndex = getIndexInCollection(itsProblems, itsSelectedDeleteProblem.getItsGenericItemId());
        if (myIndex < 0) {
            throw new SpecificObjectiveException("ppp.planning.excpRecordNotFound");
        }
        itsProblems.remove(myIndex);
        return "ppp.planning.successDel";
    }

    private String deleteStrategy(String itsSelectedStrategyIdForDelete) {
        List<SampleEntityDto> myMap = theirStrategiesByObjective.get(itsSelectedSpecificObjectiveIdForStrategy);
        if (myMap == null) {
            return "";
        }

        SampleEntityDto myDto = new SampleEntityDto(
                Long.parseLong(itsSelectedStrategyIdForDelete), "");
        myDto.setItsGenericItemId(itsSelectedStrategyIdForDelete);
        int myIndex = SearchList.indexList(myMap, myDto);

        if (myIndex >= 0) {
            itsStrategiesBySpecificObjective.remove(myIndex);


            Collection<SampleEntityDto> myCollection = theirStrategiesByObjective.get(itsSelectedSpecificObjectiveIdForStrategy);

            int i = 0;
            myIndex = -1;
            while ((i <= myCollection.size() - 1) && (myIndex < 0)) {
                myIndex = SearchList.indexList(myCollection, myDto);
                i++;
            }
            theirStrategiesByObjective.get(itsSelectedSpecificObjectiveIdForStrategy).remove(myIndex);
        }

        loadStrategiesForSpecificObjective();

        return "ppp.planning.successDel";
    }

    private void addMessageGenericItemDelete(FacesMessage.Severity aSeverity, String aSummary, String aDetails) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(aSeverity, aSummary, aDetails));


    }

    public void cancelProblem() throws ObjectiveException {
        setDefaultValuesForReflection("itsProblemDescription", "",
                "theirProblemSaveType", SaveType.NONE);
        setDisabledButtonsForReflection("itsButtonSaveProblemIsDisabled", "itsButtonNewProblemIsDisabled", Boolean.TRUE);


    }

    public void prepareNewProblemData() throws ObjectiveException {
        setDefaultValuesForReflection("itsProblemDescription", "",
                "theirProblemSaveType", SaveType.SAVE);
        setDisabledButtonsForReflection("itsButtonSaveProblemIsDisabled", "itsButtonNewProblemIsDisabled", Boolean.FALSE);

    }

    public void setItsSelectedProblem(SampleEntityDto itsSelectedProblem) throws ObjectiveException {
        this.itsSelectedProblem = itsSelectedProblem;

        setDefaultValuesForReflection("itsProblemDescription", itsSelectedProblem.getItsDescription(),
                "theirProblemSaveType", SaveType.UPDATE);
        setDisabledButtonsForReflection("itsButtonSaveProblemIsDisabled", "itsButtonNewProblemIsDisabled", Boolean.FALSE);

    }

    private void setDisabledButtonsProblemSave(Boolean aIsDisabled) {
        itsButtonSaveProblemIsDisabled = aIsDisabled;
        itsButtonNewProblemIsDisabled = !aIsDisabled;
    }

    private void setDefaultValuesForProblemData(Boolean aIsDisabled) {
        itsProblemDescription = "";
        setDisabledButtonsProblemSave(aIsDisabled);
    }

    public Boolean getItsButtonNewProblemIsDisabled() {
        return itsButtonNewProblemIsDisabled;
    }

    public void setItsButtonNewProblemIsDisabled(Boolean itsButtonNewProblemIsDisabled) {
        this.itsButtonNewProblemIsDisabled = itsButtonNewProblemIsDisabled;
    }

    public Boolean getItsButtonSaveProblemIsDisabled() {
        return itsButtonSaveProblemIsDisabled;
    }

    public void setItsButtonSaveProblemIsDisabled(Boolean itsButtonSaveProblemIsActive) {
        this.itsButtonSaveProblemIsDisabled = itsButtonSaveProblemIsActive;
    }

    public List<SampleEntityDto> getItsProblems() {
        return itsProblems;
    }

    public String getItsProblemDescription() {
        return itsProblemDescription;
    }

    public void setItsProblemDescription(String itsProblemDescription) {
        this.itsProblemDescription = itsProblemDescription;
    }

    private void loadProblems() {

        itsProblems.clear();
        for (ProblemEntity problem : theirSelectedObjective.getProblems()) {
            SampleEntityDto myDto = new SampleEntityDto(problem.getProblemId(), problem.getProblemDescription());
            myDto.setItsGenericItemId(problem.getProblemId().toString());
            this.itsProblems.add(myDto);
        }

    }

    public Long getTheirFatherId() {
        return theirFatherId;
    }

    public void setTheirFatherId(Long theirFatherId) {
        this.theirFatherId = theirFatherId;
    }

    public void prepareNewObjective(Long aFatherId, Long aLevelId, String aLevelDescription,
            String aDescriptionFather) throws ObjectiveException {

        theirObjectiveSaveType = SaveType.SAVE;
        theirFatherId = aFatherId;
        theirLevelId = aLevelId;
        itsObjectiveLevelDescription = aLevelDescription;
        itsDescriptionFather = aDescriptionFather;
    }

    public void initialInputs() throws ObjectiveException {
        clearObjectiveData();
        itsProblems.clear();
        itsSpecificObjectives.clear();
        itsStrategies.clear();
        itsStrategiesBySpecificObjective.clear();
        itsDescriptionFather = "";

    }

    public void deleteSpecificObjective(Long itsObjectiveId) {
        theirObjectiveManagement.deleteObjective(itsObjectiveId);
    }

    public ObjectiveLevelEntity getObjectiveLevel(short aLevel) throws ObjectiveLevelException {

        ObjectiveLevelEntity myObjectiveLevel = theirObjectiveLevelManagement.getObjectiveLevelByLevel(aLevel);

        return myObjectiveLevel;
    }
}
