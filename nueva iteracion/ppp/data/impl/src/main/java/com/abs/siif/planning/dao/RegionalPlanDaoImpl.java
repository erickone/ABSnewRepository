package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.entities.RegionalPlanEntity;
import java.io.Serializable;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
@Repository("RegionalPlanDao")
public class RegionalPlanDaoImpl extends SIIFBaseDaoImpl<RegionalPlanEntity, Long>
        implements RegionalPlanDao, Serializable {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<RegionalPlanEntity> getAllRegionalPlan() {
        return super.getAllAndOrderByColumn("regionalPlanObjective");
    }

    @Transactional(readOnly = false)
    @Override
    public void saveAll(Collection<RegionalPlanEntity> anEntities) {
        super.saveAll(anEntities);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<RegionalPlanEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    @Transactional(readOnly = true)
    @Override
    public RegionalPlanEntity getRegionalPlanById(Long anIdentity) {
        return findById(anIdentity, Boolean.TRUE);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<RegionalPlanEntity> getAllRegionalPlan(Long aRegionalClassifierId) {
         String myQueryHQL = "select plans from RegionalPlanEntity plans"
                 + " left join fetch plans.regionalClassifierPlan regions"
                 + " where (regions.regionalClassifierId=" + aRegionalClassifierId + ")"
                 + " order by plans.regionalObjectiveNumber  asc ";
         return super.find(myQueryHQL);
    }
}
