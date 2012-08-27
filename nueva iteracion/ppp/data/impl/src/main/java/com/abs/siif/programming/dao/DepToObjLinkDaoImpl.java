/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DepToObjLinkDaoImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.dao.ImportCeilingBudgetDaoImpl;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FENIX-02
 */
@Repository("depToObjLinkDao")
public class DepToObjLinkDaoImpl extends SIIFBaseDaoImpl<DependenceEntity, Long> 
    implements DepToObjLinkDao
{

    @Autowired
    private SessionFactory theirSessionFactory;
    
    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }

    /**
     * Trae todos las dependencias que tengan un determinado nivel.
     * @param aDependenceLevel
     * @return 
     */
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getFathersList()
    {
        DependenceLevelEntity aDependenceLevelEntity = getFatherLevel();
        Long aDependenceLevel = aDependenceLevelEntity.getDependenceLevelId();
        String myQueryHQL = "select distinct dep from DependenceEntity dep" 
                + " where dep.dependenceLevel = :aDependenceLevel"
                + " order by dep.dependenceKey asc ";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        
        myQuery.setLong("aDependenceLevel", aDependenceLevel);
        
        return myQuery.list();
    }

    /**
     * Trae el nivel del padre que esta encuadrado con la
     * planeacion estrategica.
     * @return 
     */
    @Transactional(readOnly = true)
    @Override
    public DependenceLevelEntity getFatherLevel()
    {
        String myQueryHQL = "select distinct dep from DependenceLevelEntity dep" 
                + " where dep.dependencyLevelHasObjectiveFraming = 't'";
        
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        DependenceLevelEntity aDependenceLevelEntity = (DependenceLevelEntity) myQuery.list().get(0);
        
        Short level = aDependenceLevelEntity.getDependenceLevel();
        level--;
        String myQueryString = "select distinct dep from DependenceLevelEntity dep" 
                + " where dep.dependenceLevel = :level";
        
        myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);
        myQuery.setShort("level", level);
        List resultLst = myQuery.list();
        
        if(resultLst!= null && resultLst.size()>0){
            aDependenceLevelEntity = (DependenceLevelEntity) myQuery.list().get(0);
        }
        
        return aDependenceLevelEntity;
    }

    /**
     * Este metodo traera todas las dependencias hijas de un idPadre.
     * @param aFatherId
     * @return 
     */
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getChildsList(Long aFatherId)
    {
        String myQueryString = "select distinct dep from DependenceEntity dep" 
                + " where dep.father = :aFatherId";
        
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);
        myQuery.setLong("aFatherId", aFatherId);
        return myQuery.list();
    }

    /**
     * Este metodo se trae las dependencias relacionadas a un objetivo.
     * @param anObjectiveId
     * @return 
     */
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getChildsRelatedObjList(Long aDepFatherId, Long anObjectiveId)
    {
        String myQueryString = "select distinct dep from DependenceEntity dep"
                + " left join fetch dep.objectives ob"
                + " where dep.father = :aDepFatherId "
                + " and ob.objectiveId = :anObjectiveId";
        
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);
        myQuery.setLong("aDepFatherId", aDepFatherId);
        myQuery.setLong("anObjectiveId", anObjectiveId);
        return myQuery.list();
    }

    @Transactional(readOnly = false)
    @Override
    public boolean saveDepToObjRelation(ObjectiveEntity anObjectiveEntity, Long idpadre)
    {
        boolean isOK = false;
        try{
            String myQueryString = "DELETE FROM siifpppencclasifadminobj "
                    + "WHERE idobjetivo = :idObj "
                    + "and iddependencia IN "
                    + "(SELECT iddependencia FROM siifabsdependencia "
                    + "where idpadre = :idpadre);";

            SQLQuery myQuery = theirSessionFactory.getCurrentSession().createSQLQuery(myQueryString);
            myQuery.setLong("idObj", anObjectiveEntity.getObjectiveId());
            myQuery.setLong("idpadre", idpadre);
            myQuery.executeUpdate();
            
            String myQueryHQL = "insert into siifpppencclasifadminobj "
                        + " (iddependencia, idobjetivo) values (:iddep, :idobj)";
            
            for (DependenceEntity dep : anObjectiveEntity.getDependences()) {
                
                SQLQuery mySQLQuery = theirSessionFactory.getCurrentSession().createSQLQuery(myQueryHQL);
                mySQLQuery.setLong("iddep", dep.getDependenceId());
                mySQLQuery.setLong("idobj", anObjectiveEntity.getObjectiveId());
                
                mySQLQuery.executeUpdate();
            }
            isOK = true;
        }
        catch(Exception ex){
            Logger.getLogger(ImportCeilingBudgetDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isOK;
    }
    
}
