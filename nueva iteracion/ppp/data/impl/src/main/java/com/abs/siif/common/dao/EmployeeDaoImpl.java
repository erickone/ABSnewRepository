/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  EmployeeDaoImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.common.entities.EmployeeEntity;
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
@Repository("employeeDao")
public class EmployeeDaoImpl extends SIIFBaseDaoImpl<EmployeeEntity, String> implements EmployeeDao
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
  public Collection<EmployeeEntity> getEmployeeByDefCve(BudgetKeyDefinitionEntity aBudgetKeyDefinition)
  {


    String myQueryString = "select emp from EmployeeEntity emp"
            + " left outer join emp.colectiveEmployee colective"
            + " where (emp.budgetKeyDefinitionEntityEmployee."
            + "presupuestalKeyDefinitionYear='%s')";

    myQueryString = String.format(myQueryString, aBudgetKeyDefinition.getPresupuestalKeyDefinitionYear());

    return super.find(myQueryString);
  }

  @Transactional(readOnly = true)
  @Override
  public EmployeeEntity getEmployeeById(EmployeeEntity anEmployeeEntity)
  {
    String myQueryString = "from EmployeeEntity as employee"
            + " where employee.employeeId  = :employeeId ";
    Query mySQLQuery = getTheirSessionFactory().
            getCurrentSession().createQuery(myQueryString);

    mySQLQuery.setLong("employeeId", anEmployeeEntity.getEmployeeId());

    return (EmployeeEntity) mySQLQuery.uniqueResult();
    
  }
}
