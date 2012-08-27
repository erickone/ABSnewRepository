package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 */
@Repository("InstitutionalPlanDao")
public class InstitutionalPlanDaoImpl extends SIIFBaseDaoImpl<InstitutionalPlanEntity, Long>
        implements InstitutionalPlanDao {

    @Autowired
    SessionFactory theirSessionFactory;

    /**
     * With SaveInstitutionalPlan you can, save or update your entity en the DB,
     * the paremeters: Only recieve an InstitutionalPlanEntity, to save it, and
     * dont return any parameter saveInstitutionalPlan(InstitutionalPlanEntity
     * anEntity)
     */
    @Transactional(readOnly = false)
    @Override
    public void deleteInstitutionalPlan(InstitutionalPlanEntity anInstitutionalPlan) {
        theirSessionFactory.getCurrentSession().delete(anInstitutionalPlan);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceId(Long aDependenceId) {
        String myQueryHQL = "select  distinct IPE from InstitutionalPlanEntity IPE"
                + " where IPE.dependence.dependenceId = (:dependenceId)";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryHQL);

        mySQLQuery.setLong("dependenceId", aDependenceId);

        return mySQLQuery.list();
    }

    @Transactional(readOnly = true)
    @Override
    public InstitutionalPlanEntity getInstitutionalPlanById(Long anInstitutionalPlanId) {
        String myQueryHQL = "select distinct IPE from InstitutionalPlanEntity IPE"
                + " where IPE.institutionalPlanId = (:anInstitutionalPlanId)";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);

        myQuery.setLong("anInstitutionalPlanId", anInstitutionalPlanId);

        List<InstitutionalPlanEntity> myInstitutionalPlanList = (List<InstitutionalPlanEntity>) myQuery.list();
        InstitutionalPlanEntity myInstitutionalPlan = myInstitutionalPlanList.get(0);

        return myInstitutionalPlan;
    }

    @Transactional(readOnly = true)
    @Override
    public void closeTheCurrentSession() {
        theirSessionFactory.getCurrentSession().close();
    }

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstitutionalPlanEntity> getInstitutionalPlansByDependency(DependenceEntity aDependency) {
        String myQueryString = "select distinct IPE from InstitutionalPlanEntity IPE"
                + " where IPE.dependence.dependenceId = : getDependenceId ";
        
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString).
                setLong("getDependenceId", aDependency.getDependenceId());
               

        return myQuery.list();
    }
}
