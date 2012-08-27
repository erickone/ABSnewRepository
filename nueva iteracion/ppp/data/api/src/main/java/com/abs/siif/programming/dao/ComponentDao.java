/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.DeliveryEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Declaración de métodos para los componentes
 */
public interface ComponentDao extends SIIFBaseDao<ComponentEntity, Long> {

    Collection<ComponentEntity> getComponents();

    void deleteAll(Collection<ComponentEntity> anEntities);

    int deleteByDelivery(DeliveryEntity anEntity);

    ComponentEntity getComponentById(Long anIdentity);

    List<ComponentEntity> getComponentByDelivery(DeliveryEntity anEntity);
}
