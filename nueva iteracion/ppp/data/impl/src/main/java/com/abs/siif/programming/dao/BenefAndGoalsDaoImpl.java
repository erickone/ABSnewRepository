/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.BenefAndGoalsEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("benefAndGoalDao")
public class BenefAndGoalsDaoImpl extends SIIFBaseDaoImpl<BenefAndGoalsEntity, Long>
        implements BenefAndGoalsDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public BenefAndGoalsEntity getBenefAndGoalsByInvPreFile(Long invPreFileId) {
        String myQueryString = "select b from BenefAndGoalsEntity b"
                + " left join fetch b.invPreFile inv"
                + " where (inv.invPreFileId=:invPreFileId )";
        myQueryString = String.format(myQueryString, invPreFileId.toString());
        BenefAndGoalsEntity myBenefAndGoals = null;
        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("invPreFileId", invPreFileId);
        List myResults = mySQLQuery.list();

        if (myResults.size() > 0) {
            myBenefAndGoals = (BenefAndGoalsEntity) myResults.get(0);
        }

        return myBenefAndGoals;
    }
}
