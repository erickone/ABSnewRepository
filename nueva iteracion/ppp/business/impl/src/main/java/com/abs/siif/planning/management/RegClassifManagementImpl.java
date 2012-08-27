/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.management;

import com.abs.siif.planning.dao.RegionalClassifierDao;
import com.abs.siif.planning.entities.RegionalClassifierEntity;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("RegClassifManagement")
public class RegClassifManagementImpl implements RegClassifManagement
{
  
  @Resource(name = "RegionalClassifierDao")
   private transient RegionalClassifierDao itsRegionalClassifierDao;
  
    /**
   * @return the itsRegionalClassifierDao
   */
  public RegionalClassifierDao getItsRegionalClassifierDao()
  {
    return itsRegionalClassifierDao;
  }

  /**
   * @param itsRegionalClassifierDao the itsRegionalClassifierDao to set
   */
  public void setItsRegionalClassifierDao(RegionalClassifierDao itsRegionalClassifierDao)
  {
    this.itsRegionalClassifierDao = itsRegionalClassifierDao;
  }

  @Override
  public List<RegionalClassifierEntity> getRegionsByFatherId(RegionalClassifierEntity aRegionFather)
  {
    return this.itsRegionalClassifierDao.getRegionsByFatherId(aRegionFather);
  }

  @Override
  public RegionalClassifierEntity getRegionalClassifierById(RegionalClassifierEntity aRegionalClassifierId)
  {
    return this.itsRegionalClassifierDao.getRegionalClassifierById(aRegionalClassifierId.getRegionalClassifierId());
  }

  @Override
  public Collection<RegionalClassifierEntity> getAllRegionalClassifier()
  {
    return this.itsRegionalClassifierDao.getAllRegionalClassifier();
  }

  @Override
  public Collection<RegionalClassifierEntity> getRegionalClassifierWithoutFather()
  {
    return this.itsRegionalClassifierDao.getRegionalClassifierWithoutFather();
  }

  @Override
  public Long saveOrUpdateRegion(RegionalClassifierEntity aRegionalClassifierEntity)
  {
    return this.itsRegionalClassifierDao.saveOrUpdateRegion(aRegionalClassifierEntity);
  }

  @Override
  public void deleteRegionalClassifier(RegionalClassifierEntity aRegionalClassifierEntity)
  {
    this.itsRegionalClassifierDao.delete(aRegionalClassifierEntity);
  }
}