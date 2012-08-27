/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.ActivityEntity;
import com.abs.siif.programming.entities.ComponentEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Declaración de métodos para las actividades de los componentes
 */
public interface ActivityDao extends SIIFBaseDao<ActivityEntity, Long> {

    Collection<ActivityEntity> getActivities();

    
    void deleteAll(Collection<ActivityEntity> anEntities);

    int deleteByComponent(ComponentEntity anEntity);

    ActivityEntity getActivityById(Long anIdentity);

    List<ActivityEntity> getActivityByComponent(ComponentEntity anEntity);

}
