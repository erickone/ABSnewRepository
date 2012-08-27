/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.SignatureTypeEntity;

/**
 *
 * @author jacob.flores
 */
public interface SignaturesTypeDao
{
  public SignatureTypeEntity getSignaturesTypeByType(String aType);
}
