/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.UnitMeasureGoalEntity;
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
@Repository("unitMeasureGoalDao")
public class UnitMeasureGoalDaoImpl 
extends SIIFBaseDaoImpl<UnitMeasureGoalEntity, Long> implements UnitMeasureGoalDao {

    @Autowired
    private SessionFactory itsSessionFactory;
    
    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Cacheable("unitMeasureGoalsAll")
    @Transactional(readOnly=true)
    @Override
    public Collection<UnitMeasureGoalEntity> getUnitMeasureGoals() {
       return super.getAllAndOrderByColumn("unitMeasureGoalDescription");
    }
    
}
