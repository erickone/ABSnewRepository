/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.UnitMeasureEntity;
import java.util.Collection;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Declaración de métodos para las Unidades de Medida
 */
public interface UnitMeasureDao extends SIIFBaseDao<UnitMeasureEntity, Long> {

    Collection<UnitMeasureEntity> getAllUnitMeasure();

    void saveAll(Collection<UnitMeasureEntity> anEntities);

    void deleteAll(Collection<UnitMeasureEntity> anEntities);

    UnitMeasureEntity getUnitMeasureById(Long anIdentity);

}
