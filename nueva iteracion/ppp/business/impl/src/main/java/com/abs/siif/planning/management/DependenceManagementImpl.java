/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.management;

import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("dependenceManagement")
public class DependenceManagementImpl implements DependenceManagement
{

  @Resource(name = "DependenceDao")
  private DependenceDao itsDependenceDao;

  @Override
  public List<DependenceEntity> getViewDepIsBudgetUnit()
  {
    return this.itsDependenceDao.getViewDepIsBudgetUnit();
  }

  @Override
  public List<DepencenceDto> getDependciesIsRespUnitByDependIdRelated(Long idDependency)
  {
    return this.itsDependenceDao.getDependciesIsRespUnitByDependIdRelated(idDependency);
  }

  @Override
  public List<DepencenceDto> getDependciesIsExecUnitByDependIdRelated(Long idDependency)
  {
    return this.itsDependenceDao.getDependciesIsExecUnitByDependIdRelated(idDependency);
  }

  /**
   * @return the theirDependenceDao
   */
  public DependenceDao getTheirDependenceDao()
  {
    return itsDependenceDao;
  }

  /**
   * @param theirDependenceDao the theirDependenceDao to set
   */
  public void setTheirDependenceDao(DependenceDao theirDependenceDao)
  {
    this.itsDependenceDao = theirDependenceDao;
  }

  @Override
  public List<DependenceEntity> getViewDependenciesTypeUR()
  {
    return this.itsDependenceDao.getViewDependenciesTypeUR();
  }

  @Override
  public List<DependenceEntity> getViewDepIsExecutionUnit()
  {
    return this.itsDependenceDao.getViewDepIsExecutionUnit();
  }

  @Override
  public DependenceEntity getDependenceById(Long aDependenceId)
  {
   return this.itsDependenceDao.getDependenceById(aDependenceId);
  }
}
