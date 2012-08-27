/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.DeliveryEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Se realiza la definición completa de cada uno de los métodos para los entregables
 */
@Repository("deliveryDaoImpl")
public class DeliveryDaoImpl extends SIIFBaseDaoImpl<DeliveryEntity, Long>
            implements DeliveryDao, Serializable{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DeliveryEntity> getDeliveries() {
        return super.getAllAndOrderByColumn("proposit");
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<DeliveryEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    @Transactional(readOnly = true)
    @Override
    public DeliveryEntity getDeliveryById(Long anIdentity) {
        return findById(anIdentity, Boolean.TRUE);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DeliveryEntity> getDeliveriesByDraftProject(DraftProjectEntity draftProj) 
    {
        String myQueryString = "select distinct deliver from DeliveryEntity as deliver"
                + " left join fetch deliver.components"
                + " where deliver.draftProject.draftProjectId = " + 
                    draftProj.getDraftProjectId() + "";
        
        return this.find(myQueryString);
    }
}
