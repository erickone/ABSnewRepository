/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.FederalDependenceEntity;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jacob.flores
 */
@Repository("FederalDependenceDaoImpl")
public class FederalDependenceDaoImpl extends SIIFBaseDaoImpl<FederalDependenceEntity, Long> 
                                      implements FederalDependenceDao
{
   @Autowired
    private SessionFactory theirSessionFactory;
   
   @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }
    
   @Cacheable("fereralDependenciesAll")
   @Transactional(readOnly = false)
   @Override
   public Collection<FederalDependenceEntity> getListOfFederalDependences()
   {
     return (Collection<FederalDependenceEntity>) super.getAllAndOrderByColumn("federalDependenceId");
   }
}