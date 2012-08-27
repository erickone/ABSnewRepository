/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author jacob.flores
 */
public interface DraftProjectBinnacleManagement
{
  Collection<DraftProjectBinnacleEntity> getDraftProjectBinnaclebyDraftProjectId(Long aDraftProjectID);
  void saveDraftProjectBinnacle(DraftProjectBinnacleEntity aDraftProjectBinnacleEntity);
  Date getDateOfLastStatus(Long aDraftPtojectId, Long StatusId);
  void updateDraftProjectStatus(DraftProjectBinnacleEntity aDraftBinnacle);
}
