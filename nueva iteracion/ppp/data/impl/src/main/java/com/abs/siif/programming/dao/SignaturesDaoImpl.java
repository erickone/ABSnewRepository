/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.SignaturesEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Repository("signaturesDao")
public class SignaturesDaoImpl extends SIIFBaseDaoImpl<SignaturesEntity, String>
        implements SignaturesDao
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
  public List<SignaturesEntity> getSignaturesByDraftProjectId(DraftProjectEntity aDraftPtojectId)
  {
    String myHqlQuery = "select DISTINCT Sign from SignaturesEntity Sign"
            + " left join fetch Sign.signatureType SignType"
            + " left join fetch Sign.draftProject DP";

    Filter mySignatureDraftProjectIdFilter = theirSessionFactory.getCurrentSession().enableFilter("SignatureDraftProjectIdFilter");
    mySignatureDraftProjectIdFilter.setParameter("SignatureDraftProjectIdParam", aDraftPtojectId.getDraftProjectId());

    Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myHqlQuery);
    return (List<SignaturesEntity>) myQuery.list();
  }

  @Transactional(readOnly = false)
  @Override
  public List<String> saveOrUpdateSignatures(List<SignaturesEntity> aSignaturesEntityList)
  {
    List find = null;
    try
    {
      for (SignaturesEntity sign : aSignaturesEntityList)
      {
        if (sign.getSignatureId() != Long.valueOf(0) && sign.getSignatureId() != null)
        {
          super.merge(sign);
        }
        else
        {
          super.save(sign);
        }
      }
      find = getSignaturesByDraftProjectId(aSignaturesEntityList.iterator().next().getDraftProject());
    }
    catch (Exception ex)
    {
      Logger.getLogger(SignaturesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return find;
  }

  /**
   * @param theirSessionFactory the theirSessionFactory to set
   */
  public void setTheirSessionFactory(SessionFactory theirSessionFactory)
  {
    this.theirSessionFactory = theirSessionFactory;
  }
}
