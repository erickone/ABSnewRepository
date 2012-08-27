/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.management;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface RegClassifManagement
{
   List<RegionalClassifierEntity> getRegionsByFatherId(RegionalClassifierEntity aRegionFather);

  RegionalClassifierEntity getRegionalClassifierById(RegionalClassifierEntity aRegionalClassifierId);

  Collection<RegionalClassifierEntity> getAllRegionalClassifier();

  Collection<RegionalClassifierEntity> getRegionalClassifierWithoutFather();
  
  Long saveOrUpdateRegion(RegionalClassifierEntity aRegionalClassifierEntity);
  
  void deleteRegionalClassifier(RegionalClassifierEntity aRegionalClassifierEntity);
}