/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyAdditionalDaoImpl
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
import com.abs.siif.budget.dto.BudgetKeyAdditionalDto;
import com.abs.siif.budget.entities.BudgetKeyAdditionalEntity;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("budgetKeyAdditionalDao")
public class BudgetKeyAdditionalDaoImpl extends SIIFBaseDaoImpl<BudgetKeyAdditionalEntity, Long>
        implements BudgetKeyAdditionalDao
{

  @Autowired
  private SessionFactory itsSessionFactory;

  @Override
  public SessionFactory getTheirSessionFactory()
  {
    return itsSessionFactory;
  }

  @Transactional(readOnly = true)
  @Override
  public Collection<BudgetKeyAdditionalEntity> getBudgetKeyAdditionalsByObjectExpenseAndDependency(BudgetKeyAdditionalDto anAdditionalDto)
  {
    Long myKey = anAdditionalDto.getBudgetKeyAdditional().
            getBudgetKeyAdditionalDraftProject().getDraftProjectId();



    String myQueryString = "select additional from BudgetKeyAdditionalEntity additional"
            + " left join fetch additional.budgetKeyAdditionalObjectExpense"
            + " where ( additional.budgetKeyAdditionalDraftProject = :key)";

    Query myQuery = getTheirSessionFactory().
            getCurrentSession().createQuery(myQueryString);

    myQuery.setLong("key", myKey);

    return myQuery.list();

  }

  @Transactional(readOnly = true)
  @Override
  public BudgetKeyAdditionalEntity getBudgetKeyAdditionalByIdentity(BudgetKeyAdditionalDto anAdditionalDto)
  {
    return super.findById(anAdditionalDto.getBudgetKeyAdditional().getBudgetKeyAdditionalId(), true);
  }

  @Transactional(readOnly = true)
  @Override
  public double getSumByBudgetKey(BudgetKeyAdditionalDto anAdditionalDto)
  {
    Long myKey = anAdditionalDto.getDependency().getDependenceId();

    StringBuilder myStringBuffer = new StringBuilder();
    myStringBuffer.append(myKey);
    myStringBuffer.append("%");

    int myObj = anAdditionalDto.getObjectExpense().getObjectExpenseKey();


    String myQueryString = "select sum(additional.budgetKeyAmount) from BudgetKeyAdditionalEntity"
            + " additional"
            + " where (( additional.bugKeyAdditionalDependency = :dependency) and"
            + " (additional.budgetKeyAdditionalObjectExpense.objectExpenseKey >= :obj"
            + " and additional.budgetKeyAdditionalObjectExpense.objectExpenseKey < :obj2 ))";

    Query myQuery = getTheirSessionFactory().
            getCurrentSession().createQuery(myQueryString);

    myQuery.setLong("dependency", myKey);
    myQuery.setInteger("obj", myObj);
    myQuery.setInteger("obj2", myObj + 1000);

    double mySum = 0;
    Object myResult = myQuery.uniqueResult();

    if (myResult != null)
    {
      mySum = (Double) myResult;
    }

    return mySum;
  }

  @Transactional(readOnly = true)
  @Override
  public void deleteAditional(String aBudgetKey)
  {
    String myStrQuery = "delete from siifpppcvepptaladicional"
            + " where clave = '" + aBudgetKey + "'";

    SQLQuery myQuery = itsSessionFactory.getCurrentSession().createSQLQuery(myStrQuery);
    //myQuery.setString("aBudgetKey", aBudgetKey);

    myQuery.executeUpdate();

  }
  @Transactional(readOnly = true)
    @Override
    public BudgetKeyAdditionalEntity getBudgetKeyAdditional(String aBudgetKeyId) 
    {
        String myQueryString = "from BudgetKeyAdditionalEntity as budget"
                + " where budget.budgetKeyAdditionalKey = :budgetKey ";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setString("budgetKey", aBudgetKeyId);

        return (BudgetKeyAdditionalEntity)mySQLQuery.uniqueResult();
    }
  
}
