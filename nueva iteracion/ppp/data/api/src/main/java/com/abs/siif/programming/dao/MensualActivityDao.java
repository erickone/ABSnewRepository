/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.ActivityEntity;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.MensualActivityEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Declaración de métodos para el detalle mensualizado de las actividades de los componentes
 */
public interface MensualActivityDao extends SIIFBaseDao<MensualActivityEntity, Long> {

    Collection<MensualActivityEntity> getMensualActivities();

    void deleteAll(Collection<MensualActivityEntity> anEntities);

    int deleteByActivity(ActivityEntity anEntity);

    MensualActivityEntity getMensualActivityById(Long anIdentity);

    List<MensualActivityEntity> getMensualActivityByActivity(ActivityEntity anEntity);

}
