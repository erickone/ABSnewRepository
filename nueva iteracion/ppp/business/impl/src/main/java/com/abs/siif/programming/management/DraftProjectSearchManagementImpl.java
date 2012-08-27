/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dao.DraftProjectDao;
import com.abs.siif.programming.dto.DraftProjectSearchByDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("draftProjectSearchManagement")
@Scope("request")
public class DraftProjectSearchManagementImpl 
        implements DraftProjectSearchManagement, Serializable
{
 @Resource(name = "draftProjectDaoImpl")
    private DraftProjectDao itsDraftProjectDao;
 
  @Resource(name = "DependenceDao")
    private DependenceDao itsDependenceDao;
 
  @Override
  public List<DraftProjectSearchByDto> getFilteredDraftProjectDTO(DraftProjectEntity anEntity)
  {
    return (List<DraftProjectSearchByDto>) this.itsDraftProjectDao.getFilteredDraftProjectDTO(anEntity);
  }

  /**
   * @return the theirDraftProjectDao
   */
  public DraftProjectDao getDraftProjectDao()
  {
    return itsDraftProjectDao;
  }

  /**
   * @param theirDraftProjectDao the theirDraftProjectDao to set
   */
  public void setDraftProjectDao(DraftProjectDao aDraftProjectDao)
  {
    this.itsDraftProjectDao = aDraftProjectDao;
  }

 
  /**
   * @return the itsDependenceDao
   */
  public DependenceDao getItsDependenceDao()
  {
    return itsDependenceDao;
  }

  /**
   * @param itsDependenceDao the itsDependenceDao to set
   */
  public void setItsDependenceDao(DependenceDao itsDependenceDao)
  {
    this.itsDependenceDao = itsDependenceDao;
  }
  
}
