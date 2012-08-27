/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DestinyObjectExpenseRUBUDaoImpl
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
import com.abs.siif.budget.dto.BudgetingSummaryDto;
import com.abs.siif.budget.entities.DestinationEntity;
import com.abs.siif.budget.entities.DestinyObjectExpenseRUBUEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.comparators.DependenceDtoComparator;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
 @Repository("destinyObjectExpenseRUBUDao")
public class DestinyObjectExpenseRUBUDaoImpl
 extends SIIFBaseDaoImpl<DestinyObjectExpenseRUBUEntity, Long>
 implements DestinyObjectExpenseRUBUDao
{
     @Autowired
     private SessionFactory theirSessionFactory;

     @Transactional(readOnly = true)
     @Override
     public SessionFactory getTheirSessionFactory()
     {
         return theirSessionFactory;
     }
     
    @Transactional(readOnly = true) 
    @Override
    public Collection<DestinyObjectExpenseRUBUEntity> getFrammingByRUkey(DepencenceDto aDependence,
    BudgetingSummaryDto aBudgetingDto, boolean investFlag)
    {
         String myQueryString;
         Query mySQLQuery;
        if (aBudgetingDto == null)
        {
            myQueryString = "SELECT DISTINCT DESTOBJUR FROM DestinyObjectExpenseRUBUEntity DESTOBJUR"
                    + " left join fetch DESTOBJUR.destinyObjectEpenseRUBUObject"
                    + " where ((DESTOBJUR.destinyObjectExpenseRUBUDependence = :dependenceKey) and"
                    + " (DESTOBJUR.destinyObjectEpenseRUBUInvest = :investFlag))";

            mySQLQuery = getTheirSessionFactory().
                    getCurrentSession().createQuery(myQueryString);

            mySQLQuery.setLong("dependenceKey", aDependence.getIdDependency());
            mySQLQuery.setBoolean("investFlag", investFlag);
        }
        else
        {
            myQueryString = "SELECT DISTINCT DESTOBJUR FROM DestinyObjectExpenseRUBUEntity DESTOBJUR"
                + " left join fetch DESTOBJUR.destinyObjectEpenseRUBUObject"
                + " where ((DESTOBJUR.destinyObjectExpenseRUBUDependence = :dependenceKey) and"
                + " ((DESTOBJUR.destinyObjectEpenseRUBUObject.objectExpenseKey > :objectKey1) and"
                + " (DESTOBJUR.destinyObjectEpenseRUBUObject.objectExpenseKey < :objectKey2))"
                + " and (DESTOBJUR.destinyObjectEpenseRUBUInvest = :investFlag))";

            mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("dependenceKey", aDependence.getIdDependency());
        mySQLQuery.setInteger("objectKey1", aBudgetingDto.getItsBudgetingSummaryChapter()-1);
        mySQLQuery.setInteger("objectKey2", aBudgetingDto.getItsBudgetingSummaryChapter()+1000);
        mySQLQuery.setBoolean("investFlag", investFlag);
        }
        return mySQLQuery.list();
        
    }

    @Transactional(readOnly = true) 
    @Override
    public Collection<DestinyObjectExpenseRUBUEntity> getDestinationByObject(DepencenceDto aDependence,
    ObjectExpenseEntity aBudgetingDto)
    {
        
        String myQueryString = "SELECT DISTINCT DESTOBJUR FROM DestinyObjectExpenseRUBUEntity DESTOBJUR"
                + " left join fetch DESTOBJUR.destinyObjectEpenseRUBUObject"
                + " where ((DESTOBJUR.destinyObjectExpenseRUBUDependence = :dependenceKey) and"
                + " (DESTOBJUR.destinyObjectEpenseRUBUObject.objectExpenseKey = :objectKey)"
                + " and (DESTOBJUR.destinyObjectEpenseRUBUInvest='f'))"
                + " Order by DESTOBJUR.destinyObjectEpenseRUBUDestiny ASC";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("dependenceKey", aDependence.getIdDependency());
        mySQLQuery.setInteger("objectKey", aBudgetingDto.getObjectExpenseKey());

        return mySQLQuery.list();
    }
    
    @Transactional(readOnly = true) 
    @Override
    public Collection<DestinyObjectExpenseRUBUEntity> getDestinationByObjectInv(DepencenceDto aDependence,
    ObjectExpenseEntity aBudgetingDto)
    {
        
        String myQueryString = "SELECT DISTINCT DESTOBJUR FROM DestinyObjectExpenseRUBUEntity DESTOBJUR"
                + " left join fetch DESTOBJUR.destinyObjectEpenseRUBUObject"
                + " where ((DESTOBJUR.destinyObjectExpenseRUBUDependence = :dependenceKey) and"
                + " (DESTOBJUR.destinyObjectEpenseRUBUObject.objectExpenseKey = :objectKey)"
                + " and (DESTOBJUR.destinyObjectEpenseRUBUInvest='t'))"
                + " Order by DESTOBJUR.destinyObjectEpenseRUBUDestiny ASC";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("dependenceKey", aDependence.getIdDependency());
        mySQLQuery.setInteger("objectKey", aBudgetingDto.getObjectExpenseKey());

        return mySQLQuery.list();
    }  
    
    
    
}
