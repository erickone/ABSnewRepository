/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependencyLevelDaoImpl
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

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;
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
@Repository("dependecyLevelDao")
public class DependencyLevelDaoImpl
        extends SIIFBaseDaoImpl<DependenceLevelEntity, String>
        implements DependencyLevelDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public DependenceLevelEntity getDependencyLevelByLevel(DependenceLevelEntity aDependenceLevelEntity) {
        String myQueryString = "select dep from DependenceLevelEntity dep"
                + " where ((dep.dependenceLevel="
                + aDependenceLevelEntity.getDependenceLevelId() + ") and"
                + "(dep.budgetKeyDefinitionLevels."
                + "presupuestalKeyDefinitionYear=" + 
                SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR)
                .toString() +"))";

        DependenceLevelEntity myLevel = null;

        List myList = super.find(myQueryString);

        if (myList.size() > 0) {
            myLevel = (DependenceLevelEntity) myList.get(0);
        }

        return myLevel;
    }

    @Transactional(readOnly = true)
    @Override
    public int getMaxLevel(BudgetKeyDefinitionEntity aBudgetKeyDefinition) {
        String myQueryHQL = "select MAX(dep.dependenceLevel)"
                + " from DependenceLevelEntity dep"
                + " where (dep.budgetKeyDefinitionLevels."
                + "presupuestalKeyDefinitionYear=" + aBudgetKeyDefinition.getPresupuestalKeyDefinitionYear() + ")";

        Query myQuery = itsSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        Short myLastLevel = 0;
        List myList = myQuery.list();
        if (myList.get(0) != null) {
            myLastLevel = (Short) myList.get(0);
        }
        return myLastLevel;
    }

    @Transactional(readOnly = true)
    @Override
    public DependenceLevelEntity getDependenceLevelWithColective(DependenceLevelEntity aDependencyLevel) {
        String myQueryString = "select dep from DependenceLevelEntity dep"
                + " inner join dep.colectiveType colType"
                + " where (dep.dependenceLevelId='"
                + aDependencyLevel.getDependenceLevelId() + "')";

        DependenceLevelEntity myLevel = null;

        List myList = super.find(myQueryString);

        if (myList.size() > 0) {
            myLevel = (DependenceLevelEntity) myList.get(0);
        }

        return myLevel;
    }

    @Transactional(readOnly = true)
    @Override
    public DependenceLevelEntity getDependenceLevelByLevelByAnnio(int anAnnio, short aLevel) {
        String myQueryString = "select dep"
                + " from DependenceLevelEntity dep"
                + " where ((dep.budgetKeyDefinitionLevels."
                + "presupuestalKeyDefinitionYear=" + anAnnio + ")"
                + "and(dep.dependenceLevel=" + aLevel + "))";
        DependenceLevelEntity myLevel = null;

        List myList = super.find(myQueryString);

        if (myList.size() > 0) {
            myLevel = (DependenceLevelEntity) myList.get(0);
        }

        return myLevel;
    }
}
