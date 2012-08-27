package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.io.Serializable;
import java.util.List;

public interface ObjectiveDao extends SIIFBaseDao<ObjectiveEntity, Long> {

    List<ObjectiveEntity> GetAllObjectives();

    Long save(ObjectiveEntity anObjective,SaveType aSaveType);

    void delete(ObjectiveEntity anObjective);

    ObjectiveEntity getObjectiveByIdentity(Long anObjectiveId);

    void save(List<ObjectiveEntity> aEntities);

    void delete(List<ObjectiveEntity> aEntities);

    ObjectiveEntity getObjectiveEagerByIdentity(Long anObjectiveId);
    
    ObjectiveEntity getObjectiveSpecificObjAndIndicatorByIdentity(Long anObjectiveId);
      
    List<DependenceEntity> getChildsRelatedObjList(Long anObjectiveId);
}