/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.FinancingSourceEntity;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
@Repository("financingSourceDao")
public class FinancingSourceDaoImpl
extends SIIFBaseDaoImpl<FinancingSourceEntity, Long>
implements FinancingSourceDao
{

    @Autowired
    private SessionFactory theirSessionFactory;
    
    @Transactional(readOnly=true)
    @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }

    @Transactional(readOnly=true)
    @Override
    public Collection<FinancingSourceEntity> getFinancingSourceByObjectExpense(Long anObjectExpenseId)
    {
         String myQueryString = "SELECT b.idfuentefinanciamiento,b.descripcion,b.clave FROM siifpppobjgastofuefinan"
                + " as a inner join siifpppfuefinanciamiento as b on a.idfuentefinanciamiento=b.idfuentefinanciamiento"
                + " where a.idobjetogasto = :objectExpenseId";
         
        SQLQuery mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);

        mySQLQuery.setLong("objectExpenseId", anObjectExpenseId);

        return mySQLQuery.list();
    }
    
    @Transactional(readOnly=true)
    @Override
    public FinancingSourceEntity getFinancingSourceById(Long anFinSourceId)
    {
          String myQueryString = "from FinancingSourceEntity as fin  "
                + "  where fin.financingSourceId = :finId ";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("finId", anFinSourceId );
        
        return (FinancingSourceEntity) mySQLQuery.uniqueResult();
    }
    
    @Transactional(readOnly=true)
    @Override
    public List<FinancingSourceEntity> getAllFinancingSource(){
        return this.findAll();
        
    }
    
}
