/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.management;

import com.abs.siif.planning.dao.RegionalClassifierDao;
import com.abs.siif.planning.dao.RegionalPlanDao;
import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalPlanEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jacob.flores
 */
@Service("regionalPlanManagement")
@Scope("session")
public class RegionalPlanManagementImpl implements RegionalPlanManagement, Serializable {
    
    @Resource(name = "RegionalClassifierDao")
    private transient RegionalClassifierDao itsRegionalClassifierDao;
    @Resource(name = "RegionalPlanDao")
    private transient RegionalPlanDao itsRegionalPlanDao;
    
    @Override
    public Collection<RegionalClassifierEntity> getAllRegionalClassifierRP() {
        return itsRegionalClassifierDao.getAllRegionalClassifier();
    }
    
    @Override
    public Collection<RegionalPlanEntity> getRegionalPlanByRegionalClassifier(Long aRegionalClassifierId) {
        return itsRegionalPlanDao.getAllRegionalPlan(aRegionalClassifierId);
    }
    
    @Override
    public Long persistEntity(RegionalPlanEntity aRegionaPlan) {
        RegionalPlanEntity myRegionalPlan = new RegionalPlanEntity();
        if (aRegionaPlan.getRegionalPlanId() != null) {
            myRegionalPlan = itsRegionalPlanDao.merge(aRegionaPlan);
        } else {
            myRegionalPlan = itsRegionalPlanDao.persist(aRegionaPlan);
        }
        
        return myRegionalPlan.getRegionalPlanId();
    }
    
    @Transactional(readOnly = false)
    @Override
    public void deleteRegionPlans(List<RegionalPlanEntity> aRegionalPlans) {
        for (RegionalPlanEntity myRegion : aRegionalPlans) {
            RegionalPlanEntity myRegionDelete = itsRegionalPlanDao.findById(myRegion.getRegionalPlanId(), true);
            itsRegionalPlanDao.delete(myRegionDelete);
        }
    }
}
