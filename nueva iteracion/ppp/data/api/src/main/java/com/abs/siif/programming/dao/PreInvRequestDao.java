/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.entities.PreInvRequestEntity;
import com.abs.siif.budget.entities.RequestUploadFilesEntity;
import java.util.Collection;

/**
 *
 * @author jacob.flores
 */
public interface PreInvRequestDao extends SIIFBaseDao<PreInvRequestEntity, Long>
{
  void savePreInvRequest(PreInvRequestEntity aPreInvRequestEntity);
  Collection<PreInvRequestEntity> getPreInvRequestByInvPreFileId(Long aInvPrefileId);
  
  /**
   * Este metodo elimina la relacion hacia el documento que fue subido.
   */
  public boolean deleteUploadedFile(RequestUploadFilesEntity anEntity, Long idReq);
}
