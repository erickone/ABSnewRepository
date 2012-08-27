/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.ActivityEntity;
import com.abs.siif.programming.entities.ComponentEntity;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Repository("activityDao")
public class ActivityDaoImpl extends SIIFBaseDaoImpl<ActivityEntity, Long>
            implements ActivityDao{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<ActivityEntity> getActivities() {
        return super.findAll();
    }


    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<ActivityEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    // MBA 20120514 : Se agrega código para que se elimine por Identificador del Componente
    @Transactional(readOnly = false)
    @Override
    public int deleteByComponent(ComponentEntity anEntity) {
        String myQueryHQL = "delete from ActivityEntity "
                + "where ComponentId = :componentId";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);

        myQuery.setLong("componentId", anEntity.getComponentId());
        
        return myQuery.executeUpdate();
    }

    @Transactional(readOnly = true)
    @Override
    public ActivityEntity getActivityById(Long anIdentity) {
        return findById(anIdentity, Boolean.TRUE);
    }

    // MBA 20120514 : Se agrega código para que se busque por Identificador del Componente
    @Transactional(readOnly = true)
    @Override
    public List<ActivityEntity> getActivityByComponent(ComponentEntity anEntity) {
        return this.findByCriteria(
                Restrictions.eq("component.componentId",
                                    anEntity.getComponentId()));
    }

    

}
