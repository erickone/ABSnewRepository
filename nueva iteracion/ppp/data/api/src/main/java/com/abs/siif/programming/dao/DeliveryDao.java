/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.DeliveryEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Declaración de métodos para los entregables
 */
public interface DeliveryDao extends SIIFBaseDao<DeliveryEntity, Long> {

    Collection<DeliveryEntity> getDeliveries();

    void deleteAll(Collection<DeliveryEntity> anEntities);

    DeliveryEntity getDeliveryById(Long anIdentity);

    List<DeliveryEntity> getDeliveriesByDraftProject(DraftProjectEntity draftProj);

}
