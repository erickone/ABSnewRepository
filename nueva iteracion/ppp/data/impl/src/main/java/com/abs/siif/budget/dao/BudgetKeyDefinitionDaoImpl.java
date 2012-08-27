/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyDefinitionDaoImpl
 *  Purpose:   Operations for Budget Key Definition.
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
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FENIX-02
 */
@Repository("budgetKeyDefinitionDao")
public class BudgetKeyDefinitionDaoImpl extends SIIFBaseDaoImpl<BudgetKeyDefinitionEntity, Long> 
implements BudgetKeyDefinitionDao{
    
     @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public BudgetKeyDefinitionEntity getBudgetDefinitionByYear(int aYear)
    {
        String myQueryHQL = "select distinct BKD from BudgetKeyDefinitionEntity BKD"
                + " where BKD.presupuestalKeyDefinitionYear = :aYear";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryHQL);

        mySQLQuery.setInteger("aYear", aYear);

        return (BudgetKeyDefinitionEntity) mySQLQuery.list().get(0);
    }

}
