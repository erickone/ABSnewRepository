package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.util.List;

public interface ObjectiveProgrammingDao extends SIIFBaseDao<ObjectiveEntity, Long> {

    List<ObjectiveEntity> getObjectivesByDependencyId(Long anDependenceId);

    List<ObjectiveEntity> getObjectivesByDependency(DependenceEntity anDependence);

    List<ObjectiveEntity> getHierarchicalObjectives(List<Long> anIdentities);

    List<ObjectiveEntity> getHierarchicalObjectives(Long anIdentity);
     
    List<ObjectiveEntity> getHierarchicalObjectivesByEntities(List<ObjectiveEntity> anIdentity);
   
    
}