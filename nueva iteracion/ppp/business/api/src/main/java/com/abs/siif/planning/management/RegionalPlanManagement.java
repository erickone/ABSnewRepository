/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.management;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalPlanEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface RegionalPlanManagement {

    Collection<RegionalClassifierEntity> getAllRegionalClassifierRP();

    Collection<RegionalPlanEntity> getRegionalPlanByRegionalClassifier(Long aRegionalClassifierId);

    Long persistEntity(RegionalPlanEntity aRegionaPlan);

    public void deleteRegionPlans(List<RegionalPlanEntity> aRegionalPlans);
}
