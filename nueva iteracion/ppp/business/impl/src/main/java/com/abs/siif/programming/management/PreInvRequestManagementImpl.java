/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.budget.dao.FederalDependenceDao;
import com.abs.siif.budget.entities.FederalDependenceEntity;
import com.abs.siif.budget.entities.PreInvRequestEntity;
import com.abs.siif.programming.dao.InvPreFileDao;
import com.abs.siif.budget.entities.RequestUploadFilesEntity;
import com.abs.siif.programming.dao.PreInvRequestDao;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jacob.flores
 */
@Service("preInvRequestManagement")
@Scope("session")
public class PreInvRequestManagementImpl implements PreInvRequestManagement, Serializable
{

  @Resource(name = "FederalDependenceDaoImpl")
  private transient FederalDependenceDao itsFederalDependenceDao;
  
  @Resource(name = "PreInvRequestDaoImpl")
  private transient PreInvRequestDao itsPreInvRequestDao;
  
  @Resource(name = "invPreFileDao")
  private transient InvPreFileDao itsInvPreFileDao;
  
  @Override
  public Collection<FederalDependenceEntity> getListOfFederalDependences()
  {
    return itsFederalDependenceDao.getListOfFederalDependences();
  }

  @Override
  public Collection<PreInvRequestEntity> getPreInvRequestByPreFileId(Long aPreFileId)
  {
    return itsPreInvRequestDao.getPreInvRequestByInvPreFileId(aPreFileId);
  }

  @Override
  //@Transactional(readOnly = false)
  public Long savePreInvRequest(PreInvRequestEntity aPreInvRequestEntity)
  {
    if(aPreInvRequestEntity.getPreInvRequestId() != null)
    {
      itsPreInvRequestDao.merge(aPreInvRequestEntity);
    }
    else
    {
      List<RequestUploadFilesEntity> temp = aPreInvRequestEntity.getUploadedFiles();  
      aPreInvRequestEntity.setUploadedFiles(null);
      itsPreInvRequestDao.savePreInvRequest(aPreInvRequestEntity);
      aPreInvRequestEntity.setUploadedFiles(temp);
      itsPreInvRequestDao.merge(aPreInvRequestEntity);
    }
    return aPreInvRequestEntity.getPreInvRequestId();
  }
  
  @Override
  public InvPreFileEntity getInvPreFileById(long invPreFileId){
        InvPreFileEntity invPreFileEntity = itsInvPreFileDao.getInvPreFileEntityById(invPreFileId);
        return invPreFileEntity;
  }

    @Override
    public boolean deleteUploadedFile(RequestUploadFilesEntity anEntity, Long idReq)
    {
        return itsPreInvRequestDao.deleteUploadedFile(anEntity, idReq);
    }
}