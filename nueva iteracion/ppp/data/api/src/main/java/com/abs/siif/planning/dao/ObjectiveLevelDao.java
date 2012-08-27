package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import java.io.Serializable;
import java.util.List;

public interface ObjectiveLevelDao extends SIIFBaseDao<ObjectiveLevelEntity, Long> {

    List<ObjectiveLevelEntity> getAllObjectiveLevels();

    Long saveOrUpdate(ObjectiveLevelEntity anOjectiveLevel);

    void delete(ObjectiveLevelEntity anIdentity);

    void delete(List<ObjectiveLevelEntity> anIdentities);

    ObjectiveLevelEntity getObjetiveLevelByIdentity(Long anObjectiveLevelId);

    ObjectiveLevelEntity getObjectiveLevelByLevel(short aLevel);

    short getLastObjectiveLevel();

    ObjectiveLevelEntity getObjectiveLevelRelationshipUEG();
}