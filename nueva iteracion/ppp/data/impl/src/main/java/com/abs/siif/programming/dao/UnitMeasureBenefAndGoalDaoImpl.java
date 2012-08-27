/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.UnitMeasureBenefAndGoalEntity;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("unitMeasureBenefAndGoalDao")
public class UnitMeasureBenefAndGoalDaoImpl
        extends SIIFBaseDaoImpl<UnitMeasureBenefAndGoalEntity, Long> implements UnitMeasureBenefAndGoalDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Cacheable("unitMeasureBenefAndGoalsAll")
    @Transactional(readOnly = true)
    @Override
    public Collection<UnitMeasureBenefAndGoalEntity> getUnitMeasureBenefAndGoals() {
        return super.getAllAndOrderByColumn("unitMeasureBenefAndGoalDescription");

    }
}
