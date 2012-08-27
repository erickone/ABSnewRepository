/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.SefinValidationDao;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.SefinValidationEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("SefinValidationManagement")
public class SefinValidationManagementImpl implements SefinValidationManagement, Serializable
{

  @Resource(name = "sefinValidationDao")
  SefinValidationDao itsValidationDao;

  @Override
  public List<Long> saveSefinValidation(List<SefinValidationEntity> aSefinValidationEntity)
  {
    return this.itsValidationDao.saveSefinValidation(aSefinValidationEntity);
  }

  @Override
  public List<SefinValidationEntity> getSefinValidationByComponent(ComponentEntity aComponentEntity)
  {
    return this.itsValidationDao.getSefinValidationByComponent(aComponentEntity);
  }
}