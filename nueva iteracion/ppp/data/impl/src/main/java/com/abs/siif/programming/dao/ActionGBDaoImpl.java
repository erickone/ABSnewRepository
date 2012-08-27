/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.ActionGBEntity;
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
@Repository("actionGBDao")
public class ActionGBDaoImpl extends SIIFBaseDaoImpl<ActionGBEntity, Long>
        implements ActionGBDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<ActionGBEntity> getActionGBs() {
        return super.getAllAndOrderByColumn("actionGBDescription");
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<ActionGBEntity> getActionsByBuildingConcept(Long anIdentity) {
        String myQueryString = "select distinct act from ActionGBEntity act"
                + " left join fetch act.buildingConceptEntity gb"
                + " where (gb.buildingConceptId=:idIdentity )";
        
        
        Query myQuery = itsSessionFactory.getCurrentSession().createQuery(myQueryString).
                setLong("idIdentity", anIdentity);
       
        
        return myQuery.list();
    }

    @Override
    public List<ActionGBEntity> getActionsNotWithIds(Long[] ids) {
        String myQueryString = "select distinct bc from ActionGBEntity bc"
                + " where bc.actionGBId not in (:ids)";
        Query myQuery = itsSessionFactory.getCurrentSession().createQuery(myQueryString).
                setParameterList("ids", ids);                
        
        return myQuery.list();
    }
}
