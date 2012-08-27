/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.BudgetDetailsKeyEntity;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import java.io.Serializable;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("budgetDetailsKeyDao")
public class BudgetDetailsKeyDaoImpl extends SIIFBaseDaoImpl<BudgetDetailsKeyEntity, Long>
        implements BudgetDetailsKeyDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Transactional(readOnly=true)
    @Override
    public Collection<BudgetDetailsKeyEntity> 
            getBudgetDetailsKeyByBudgetKey(BudgetKeyEntity anEntity) {
        String myQueryString = "select budget from BudgetDetailsKeyEntity budget"
                + " where (budget.budgetKey.budgetKeyId=:budgetId)";

        Query myQuery = super.createQuery(myQueryString);
        myQuery.setLong("budgetId", anEntity.getBudgetKeyId());

        return myQuery.list();
    }
    
    @Transactional(readOnly=false)
    @Override
    public void deleteBudgetDetailsKey(BudgetKeyEntity anEntity) 
    {
        String myQueryString = "DELETE BudgetDetailsKeyEntity budget"
                + " where (budget.budgetKey.budgetKeyId=:budgetId)";

        Query myQuery = super.createQuery(myQueryString);
        myQuery.setLong("budgetId", anEntity.getBudgetKeyId());

        myQuery.executeUpdate();
    }
    
    
    
}
