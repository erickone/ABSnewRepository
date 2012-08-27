/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.ActivityEntity;
import com.abs.siif.programming.entities.MensualActivityEntity;
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
@Repository("mensualActivityDao")
public class MensualActivityDaoImpl extends SIIFBaseDaoImpl<MensualActivityEntity, Long>
        implements MensualActivityDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<MensualActivityEntity> getMensualActivities() {
        return super.findAll();
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<MensualActivityEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    // MBA 20120514 : Se agrega código para que se elimine por Identificador de la actividad
    @Transactional(readOnly = false)
    @Override
    public int deleteByActivity(ActivityEntity anEntity) {

        String myQueryHQL = "delete from MensualActivityEntity "
                + "where activity.ActivityId = :activityId";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("activityId", anEntity.getActivityId());

        return myQuery.executeUpdate();
    }

    @Transactional(readOnly = true)
    @Override
    public MensualActivityEntity getMensualActivityById(Long anIdentity) {
        return findById(anIdentity, Boolean.TRUE);
    }

    // MBA 20120514 : Se agrega código para que se busque por Identificador de la actividad
    @Transactional(readOnly = true)
    @Override
    public List<MensualActivityEntity> getMensualActivityByActivity(
            ActivityEntity anEntity) {

        return this.findByCriteria(
                Restrictions.eq("activity.activityId",
                anEntity.getActivityId()));
    }
}
