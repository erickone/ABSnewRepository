/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.SignaturesTypeDao;
import com.abs.siif.programming.entities.SignatureTypeEntity;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("signaturesTypeManagement")
public class SignaturesTypeManagementImpl implements SignaturesTypeManagement
{
  @Resource(name = "signaturesTypeDao")
  SignaturesTypeDao signaturesTypeDao;

  @Override
  public SignatureTypeEntity getSignaturesTypeByType(String aType)
  {
    return this.signaturesTypeDao.getSignaturesTypeByType(aType);
  }

  /**
   * @return the signaturesTypeDao
   */
  public SignaturesTypeDao getSignaturesTypeDao()
  {
    return signaturesTypeDao;
  }

  /**
   * @param signaturesTypeDao the signaturesTypeDao to set
   */
  public void setSignaturesTypeDao(SignaturesTypeDao signaturesTypeDao)
  {
    this.signaturesTypeDao = signaturesTypeDao;
  }
  
}
