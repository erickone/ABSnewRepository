package com.abs.siif.planning.management;

import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.util.List;
import java.util.Set;

public interface ObjectiveManagement {

    List<ObjectiveEntity> GetAllObjectives();

    ObjectiveEntity getObjectiveById(Long anIdObjective);

    ObjectiveEntity getObjectiveByIdentity(Long anIdObjective);
    
    void deleteObjective(Long anIdObjective);

    void updateObjective(Long anObjectiveId, String anObjectiveName, String anObjectiveDefinition, Short anObjectivePriority,
            Long anObjectiveLevelId, Long aFatherId);
 

    ObjectiveEntity getObjectiveEagerByIdentity(Long anObjectiveId);
    
    ObjectiveEntity getObjectiveSpecificObjAndIndicatorByIdentity(Long anObjectiveId);

    public Long Save(ObjectiveEntity anObjectiveData,SaveType aSaveType);
    
    List<DependenceEntity> getChildsRelatedObjList(Long anObjectiveId);
    
    Set<FunctionalClassifierEntity>getFunctionalClassifiersByObjectiveId(Long anObjectiveId);
    
    List<ObjectiveEntity> getStateAlignmet(ObjectiveEntity subProgram);
}
