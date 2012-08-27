/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.common;

import com.abs.siif.common.dao.ColectiveDao;
import com.abs.siif.common.entities.ColectiveEntity;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("colectiveManagement")
public class ColectiveManagementImpl implements ColectiveManagement
{

  @Resource(name = "colectiveDao")
  private ColectiveDao itsColectiveDao;

  @Override
  public ColectiveEntity getColectiveById(ColectiveEntity aColectiveEntity)
  {
    return this.itsColectiveDao.getColectiveById(aColectiveEntity);
  }
}