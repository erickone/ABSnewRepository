/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.DraftProjectBinnacleDao;
import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("draftProjectBinnacleManagement")

public class DraftProjectBinnacleManagementImpl implements DraftProjectBinnacleManagement, Serializable
{
  @Resource(name="draftProjectBinnacleDaoImpl")
  private DraftProjectBinnacleDao itsDraftProjectBinnacleDao;


  @Override
  public Collection<DraftProjectBinnacleEntity> getDraftProjectBinnaclebyDraftProjectId(Long aDraftProjectID)
  {
    return itsDraftProjectBinnacleDao.getDraftProjectBinnacleByDraftProjectId(aDraftProjectID);
  }

  @Override
  public void saveDraftProjectBinnacle(DraftProjectBinnacleEntity aDraftProjectBinnacleEntity)
  {
    itsDraftProjectBinnacleDao.save(aDraftProjectBinnacleEntity);
  }

  @Override
  public Date getDateOfLastStatus(Long aDraftPtojectId, Long StatusId) 
  {
   return itsDraftProjectBinnacleDao.getDateOfLastStatus(aDraftPtojectId, StatusId);
  }

  @Override
  public void updateDraftProjectStatus(DraftProjectBinnacleEntity aDraftBinnacle) 
  {
   itsDraftProjectBinnacleDao.updateDraftProjectStatus(aDraftBinnacle);
  }
  
}