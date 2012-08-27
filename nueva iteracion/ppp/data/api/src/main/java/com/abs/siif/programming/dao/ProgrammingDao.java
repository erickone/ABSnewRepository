/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.programming.entities.ProgrammingEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface ProgrammingDao extends SIIFBaseDao<ProgrammingEntity, Long> {
    
    Collection<ProgrammingEntity> getProgrammingByObjectiveId(Long anIdentity);
    
    Collection<ProgrammingEntity> getProgramminByDependenceAndObjective(Long aDependencyId,Long aObjectiveId);
    
    List<ProgrammingEntity> getHierarchicalPrograms(List<Long> anIdentities);

    List<ProgrammingEntity> getHierarchicalPrograms(Long anIdentity);
     
    List<ProgrammingEntity> getHierarchicalProgramsByEntities(List<ProgrammingEntity> anIdentity);
}
