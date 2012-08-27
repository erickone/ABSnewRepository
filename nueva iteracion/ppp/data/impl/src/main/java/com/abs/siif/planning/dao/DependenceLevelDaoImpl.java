/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependenceLevelDaoImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.BudgetKeyAndDraftProjectFramingEntity;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author Erick Leija
 */
@Repository("dependenceLevelDao")
public class DependenceLevelDaoImpl extends SIIFBaseDaoImpl<DependenceLevelEntity,
        Long> implements DependenceLevelDao
{
    @Autowired
    private SessionFactory theirSessionFactory;
    
    @Override
    public SessionFactory getTheirSessionFactory() 
    {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceLevelEntity> getAllDependenceLevelsByYear(int aYear)
    {
         String myQueryString = "from DependenceLevelEntity as DepLevel"
                + " left join fetch DepLevel.budgetKeyDefinitionLevels BudgetDefinition" 
                + " where (BudgetDefinition.presupuestalKeyDefinitionYear  = :year)";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("year", aYear);

        return (List<DependenceLevelEntity>) mySQLQuery.list();
    }
   
}
