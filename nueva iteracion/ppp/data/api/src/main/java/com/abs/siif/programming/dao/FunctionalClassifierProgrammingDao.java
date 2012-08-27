/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 * Mapea los servicios para acceso a datos que tendra el clasificador funcional
 */
public interface FunctionalClassifierProgrammingDao {
    
    Collection<FunctionalClassifierEntity> getFunctionalClassifiersByObjectiveId(Long anObjectiveId);
    
    List<FunctionalClassifierEntity> getHierarchicalFunctionalClassifier(List<Long> anIdentities);

    List<FunctionalClassifierEntity> getHierarchicalFunctionalClassifier(Long anIdentity);
     
    List<FunctionalClassifierEntity> getHierarchicalFunctionalClassifierByEntities(List<FunctionalClassifierEntity> anIdentity);
    
}
