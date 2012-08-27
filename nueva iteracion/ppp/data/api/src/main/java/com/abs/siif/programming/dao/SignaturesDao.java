/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.SignaturesEntity;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface SignaturesDao
{
  public List<SignaturesEntity> getSignaturesByDraftProjectId(DraftProjectEntity aDraftPtojectId);
  public List<String> saveOrUpdateSignatures(List<SignaturesEntity> aSignaturesEntityList);
}
