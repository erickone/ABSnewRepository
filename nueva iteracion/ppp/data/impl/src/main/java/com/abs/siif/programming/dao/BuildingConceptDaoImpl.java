/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.BuildingConceptEntity;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("buildingConceptDao")
public class BuildingConceptDaoImpl extends SIIFBaseDaoImpl<BuildingConceptEntity, Long> implements BuildingConceptDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<BuildingConceptEntity> getBuildingConceptsByConceptGeneral(Long anIdentity) {
        String myQueryString = "select distinct bc from BuildingConceptEntity bc"
                + " left join fetch bc.generalConceptEntity gc"
                + " where (gc.generalConceptId=:anIdentity)";

        Query myQuery = itsSessionFactory.getCurrentSession().createQuery(myQueryString).
                setLong("anIdentity", anIdentity);
        return myQuery.list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<BuildingConceptEntity> getBuildingConceptsNotWithIds(Long[] ids) {
        String myQueryString = "select distinct bc from BuildingConceptEntity bc"
                + " where bc.buildingConceptId not in (:ids)";
        Query myQuery = itsSessionFactory.getCurrentSession().createQuery(myQueryString).
                setParameterList("ids", ids);                
        
        return myQuery.list();
    }
}
