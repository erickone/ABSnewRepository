/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.DeliveryEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
@Repository("componentImpl")
public class ComponentDaoImpl extends SIIFBaseDaoImpl<ComponentEntity, Long>
        implements ComponentDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<ComponentEntity> getComponents() {
        return super.findAll();
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<ComponentEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    // MBA 20120514 : Se agrega código para que se elimine por Identificador del Entregable
    @Transactional(readOnly = false)
    @Override
    public int deleteByDelivery(DeliveryEntity anEntity) {
        String myQueryHQL = "delete from ComponentEntity "
                + "where DeliveryId = :deliveryId";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("deliveryId", anEntity.getDeliveryId());

        return myQuery.executeUpdate();
    }

    @Transactional(readOnly = true)
    @Override
    public ComponentEntity getComponentById(Long anIdentity) {
        return findById(anIdentity, Boolean.TRUE);
    }

    // MBA 20120514 : Se agrega código para que se busque por Identificador del Entregable
    @Transactional(readOnly = true)
    @Override
    public List<ComponentEntity> getComponentByDelivery(DeliveryEntity anEntity) {
        List<ComponentEntity> myResult = new ArrayList<ComponentEntity>();
        try {
            myResult = this.findByCriteria(
                    Restrictions.eq("delivery.deliveryId",
                    anEntity.getDeliveryId()));
        } catch (ConstraintViolationException ex) {
            throw new BaseBusinessException("ppp.progr.errorDeletingComponent");
        }
        return myResult;

    }
}
