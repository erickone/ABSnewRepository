/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.InvestmentLineEntity;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("investmentLineDao")
public class InvestmentLineDaoImpl extends SIIFBaseDaoImpl<InvestmentLineEntity, String>
        implements InvestmentLineDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Cacheable("investmentLinesAll")
    @Transactional(readOnly = true)
    @Override
    public Collection<InvestmentLineEntity> getInvestmentLines() {
        return super.getAllAndOrderByColumn("investLineDescription");
    }
    
    @Transactional(readOnly = true)
    @Override
    public InvestmentLineEntity getDefaultInvestmentLine() {
        String myQueryString = "FROM InvestmentLineEntity as line"
                + " WHERE line.investLineDescription like 'No seleccionado' ";

        Query myQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);
   
        return (InvestmentLineEntity)myQuery.uniqueResult();
    }
    
}
