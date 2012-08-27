package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.RegionalPlanEntity;
import java.util.Collection;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
public interface RegionalPlanDao extends SIIFBaseDao<RegionalPlanEntity, Long> {

    Collection<RegionalPlanEntity> getAllRegionalPlan();

    

    RegionalPlanEntity getRegionalPlanById(Long anIdentity);

    Collection<RegionalPlanEntity> getAllRegionalPlan(Long aRegionalClassifierId);
}
