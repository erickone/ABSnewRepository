/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.entities.BranchAndSectorFrammingEntity;
import com.abs.siif.planning.entities.BranchSectorAndDependenceFrammingEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
@Repository("branchSectorAndDependenceFrammingDao")
public class BranchSectorAndDependenceFrammingDaoImpl
extends SIIFBaseDaoImpl<BranchSectorAndDependenceFrammingEntity,
        Long> implements BranchSectorAndDependenceFrammingDao
{

   @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public BranchSectorAndDependenceFrammingEntity getFrammingByDependence(DependenceEntity aDependenceId) 
    {
        
        String myQueryHQL = "select distinct frammingDep from BranchSectorAndDependenceFrammingEntity frammingDep"
                + " left join fetch frammingDep.branchSectorAndDependenceFrammingBranchSectorId"
                + " where frammingDep.branchSectorAndDependenceDependence = :dependenceId";

        Query myQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryHQL);

        myQuery.setLong("dependenceId", aDependenceId.getDependenceId());

        return (BranchSectorAndDependenceFrammingEntity) myQuery.uniqueResult();
    }
    
}
