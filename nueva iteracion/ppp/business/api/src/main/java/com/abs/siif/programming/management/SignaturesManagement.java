/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.SignaturesEntity;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface SignaturesManagement
{

  public List<SignaturesEntity> getSignaturesByDraftProjectId(DraftProjectEntity aDraftPtojectId);

  public List<String> saveOrUpdateSignatures(List<SignaturesEntity> aSignaturesEntityList);
  
  public String getSignantByDependenceId(DependenceEntity aDependenceEntity);
}