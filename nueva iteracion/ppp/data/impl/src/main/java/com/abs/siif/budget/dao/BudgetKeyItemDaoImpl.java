/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar Implementa los metodos de acceso a datos
 * para los elementos de la generación de la clave presupuestal.
 */
@Repository("budgetKeyItemDao")
public class BudgetKeyItemDaoImpl
        extends SIIFBaseDaoImpl<BudgetKeyItemEntity, Long>
        implements BudgetKeyItemDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public Collection<BudgetKeyItemEntity> getBudgetKeyItems() {
      
        return super.findAll();
    }

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BudgetKeyItemEntity> getBudgetItemsRelatedCeilingConf(Long aCeilingConfId)
    {
        String myQueryString = "select distinct BKI from BudgetKeyItemEntity BKI"
                + " left join fetch BKI.ceilingConfigurations CC"
                + " where CC.ceilingConfigId = :aCeilingConfId";
        
        Query myQuery = itsSessionFactory.getCurrentSession().createQuery(myQueryString);
        myQuery.setLong("aCeilingConfId", aCeilingConfId);
        return myQuery.list();
    }
}
