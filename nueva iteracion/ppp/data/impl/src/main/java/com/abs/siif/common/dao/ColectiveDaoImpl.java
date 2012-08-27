/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.common.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.common.entities.ColectiveEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jacob.flores
 */
@Repository("colectiveDao")
public class ColectiveDaoImpl extends SIIFBaseDaoImpl<ColectiveEntity, Long> implements ColectiveDao
{
  
  @Autowired
  private SessionFactory itsSessionFactory;

  @Transactional(readOnly = true)
  @Override
  public ColectiveEntity getColectiveById(ColectiveEntity aColectiveEntity)
  {
        String myQueryString = "from ColectiveEntity as colective"
            + " where colective.colectiveId  = :colectiveId ";
    Query mySQLQuery = getTheirSessionFactory().
            getCurrentSession().createQuery(myQueryString);

    mySQLQuery.setLong("colectiveId", aColectiveEntity.getColectiveId());

    return (ColectiveEntity) mySQLQuery.uniqueResult();
    
  }

  @Override
  public SessionFactory getTheirSessionFactory()
  {
    return this.itsSessionFactory;
  }
}