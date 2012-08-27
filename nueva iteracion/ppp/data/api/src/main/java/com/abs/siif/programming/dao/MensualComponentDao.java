/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.MensualComponentEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Declaración de métodos para el detalle mensualizado de los componentes
 */
public interface MensualComponentDao extends SIIFBaseDao<MensualComponentEntity, Long> {

    Collection<MensualComponentEntity> getMensualComponents();

    void deleteAll(Collection<MensualComponentEntity> anEntities);

    int deleteByComponent(ComponentEntity anEntity);

    MensualComponentEntity getMensualComponentById(Long anIdentity);

    List<MensualComponentEntity> getMensualComponentByComponent(ComponentEntity anEntity);

    List<MensualComponentEntity> getMensualComponentByComponentAndMonth(ComponentEntity anEntity, String aMonth);

}
