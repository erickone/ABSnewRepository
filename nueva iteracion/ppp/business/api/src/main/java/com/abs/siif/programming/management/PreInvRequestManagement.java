/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.budget.entities.FederalDependenceEntity;
import com.abs.siif.budget.entities.PreInvRequestEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.budget.entities.RequestUploadFilesEntity;
import java.util.Collection;

/**
 *
 * @author jacob.flores
 */
public interface PreInvRequestManagement
{
  Collection<FederalDependenceEntity> getListOfFederalDependences();
  Collection<PreInvRequestEntity> getPreInvRequestByPreFileId(Long aPreFileId);
  InvPreFileEntity getInvPreFileById(long invPreFileId);
  public Long savePreInvRequest(PreInvRequestEntity aPreInvRequestEntity);
  
  /**
   * Este metodo elimina la relacion hacia el documento que fue previamente subido.
   * @param anEntity
   * @return 
   */
  public boolean deleteUploadedFile(RequestUploadFilesEntity anEntity, Long idReq);
}
