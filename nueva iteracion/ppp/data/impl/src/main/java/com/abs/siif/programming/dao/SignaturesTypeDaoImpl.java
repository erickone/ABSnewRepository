/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.SignatureTypeEntity;
import java.util.List;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jacob.flores
 */
@Repository("signaturesTypeDao")
public class SignaturesTypeDaoImpl extends SIIFBaseDaoImpl<SignatureTypeEntity, String>
implements SignaturesTypeDao
{
  @Autowired
  private SessionFactory theirSessionFactory;
    
  @Transactional(readOnly = true)
  @Override
  public SessionFactory getTheirSessionFactory()
  {
    return this.theirSessionFactory;
  }
 
  @Transactional(readOnly = true)
  @Override
  public SignatureTypeEntity getSignaturesTypeByType(String aType)
  {
    String myHqlQuery = "select DISTINCT SignType from SignatureTypeEntity SignType";

    Filter mySignatureTypeFilter = theirSessionFactory.getCurrentSession().enableFilter("SignatureTypeFilter");
    mySignatureTypeFilter.setParameter("SignatureTypeParam", aType);

    Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myHqlQuery);
    List<SignatureTypeEntity> myList = (List<SignatureTypeEntity>) myQuery.list();
    return  myList.get(0);
  }

  /**
   * @param theirSessionFactory the theirSessionFactory to set
   */
  public void setTheirSessionFactory(SessionFactory theirSessionFactory)
  {
    this.theirSessionFactory = theirSessionFactory;
  }
  
}
