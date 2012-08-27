/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingConfigurationDaoImpl
 *  Purpose:  Define la implementacion de los metodos para acceso a datos, 
 *            que se relacionan a la tabla de la configuracion 
 *            del techo presupuestal.
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
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.budget.entities.CeillingBudgetEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.metamodel.relational.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abs71
 */
@Repository("ceillingBudgetDao")
public class CeillingBudgetDaoImp extends SIIFBaseDaoImpl<CeilingConfigurationEntity, Long> 
implements CeillingBudgetDao{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public CeillingBudgetEntity getTechoByCvePresupuestal(String cve) {
      //return super.getAllAndOrderByColumn("ceilingConfigId");
        CeillingBudgetEntity celBudgetEntity = null;
        
        String myQueryHQL = "select CCE from CeillingBudgetEntity CCE"
                + " where CCE.claveCarga like '" + cve + "'";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryHQL);
        List list = mySQLQuery.list();
        if(list !=null && !list.isEmpty())
            celBudgetEntity = (CeillingBudgetEntity) list.get(0);
        return celBudgetEntity;
    }

    
}
