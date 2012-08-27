/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyAndDraftProjectFramingDaoImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.BudgetKeyAndDraftProjectFramingEntity;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Erick Leija
 */
@Repository("budgetKeyAndDraftProjectFramingDao")
public class BudgetKeyAndDraftProjectFramingDaoImpl 
extends SIIFBaseDaoImpl<BudgetKeyAndDraftProjectFramingEntity, Long> 
implements BudgetKeyAndDraftProjectFramingDao
{
    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }
    
    @Transactional(readOnly = false)
    @Override
    public Long saveBudgetKeyAndDraftProjectFraming(BudgetKeyAndDraftProjectFramingEntity aBudgetFraming) 
    {
        BudgetKeyAndDraftProjectFramingEntity myBudgetKey = super.save(aBudgetFraming);
        return myBudgetKey.getBudgetKeyAndDraftProId();
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteBudgetKeyAndDraftProjectFraming(BudgetKeyAndDraftProjectFramingEntity aBudgetFraming) 
    {
        this.delete(aBudgetFraming);
    }
    
    @Transactional(readOnly = true)
    @Override
    public BudgetKeyAndDraftProjectFramingEntity getBudgetKeyAndDraftProjectFramingByProjectIDAndBudgetId
            (Long aProjectId, Long aBudgetKeyId) 
    {
        String myQueryString = "from BudgetKeyAndDraftProjectFramingEntity as budget"
                + " where (budget.draftProjectIdBudgetKeyAndDraftPro  = :draftKeyId) and"
                + " (budget.budgetKeyIdBudgetKeyAndDraftPro  = :budgetKeyId)";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("budgetKeyId", aBudgetKeyId);
         mySQLQuery.setLong("draftKeyId", aProjectId);

        return (BudgetKeyAndDraftProjectFramingEntity) mySQLQuery.uniqueResult();
    }
    
    @Transactional(readOnly = false)
    @Override
    public Long getCeilingAvailableByDependenceId(Long aDependenceId)
    {
        
        String myQueryString = "select sum(budget.budgetKeyIdBudgetKeyAndDraftPro.originalAmount) from"
                + " BudgetKeyAndDraftProjectFramingEntity as budget"
                + " where budget.draftProjectIdBudgetKeyAndDraftPro.draftProjectDependency = :dependencyId";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("dependencyId", aDependenceId);
        Long myREsult;
        if (mySQLQuery.uniqueResult()==null)
        {
            myREsult = (long) 0;
        }
        else
        {
            myREsult= ((Double)mySQLQuery.uniqueResult()).longValue();
        }

        return myREsult;
    }
}
