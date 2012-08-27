/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyAdditionalManagementImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.management;

import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.budget.dao.BudgetKeyAdditionalDao;
import com.abs.siif.budget.dto.BudgetKeyAdditionalDto;
import com.abs.siif.budget.entities.BudgetKeyAdditionalEntity;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("budgetKeyAdditionalManagement")
public class BudgetKeyAdditionalManagementImpl implements
        BudgetKeyAdditionalManagement {

    @Resource(name = "budgetKeyAdditionalDao")
    private BudgetKeyAdditionalDao itsBudgetKeyAdditionalDao;

    @Override
    public Collection<BudgetKeyAdditionalEntity> getBudgetKeyAdditionalsByObjectExpenseAndDependency(BudgetKeyAdditionalDto anAdditionalDto) {
        return itsBudgetKeyAdditionalDao.getBudgetKeyAdditionalsByObjectExpenseAndDependency(anAdditionalDto);
    }

    @Override
    public double getSumByBudgetKey(BudgetKeyAdditionalDto anAdditionalDto) {
        return itsBudgetKeyAdditionalDao.getSumByBudgetKey(anAdditionalDto);
    }

    @Override
    public BudgetKeyAdditionalEntity getBudgetKeyAdditionalByIdentity(BudgetKeyAdditionalDto anAdditionalDto) {
        return itsBudgetKeyAdditionalDao.getBudgetKeyAdditionalByIdentity(anAdditionalDto);
    }

    @Override
    public BudgetKeyAdditionalEntity persist(BudgetKeyAdditionalEntity anEntity) 
    {
        List<BudgetKeyAdditionalEntity> others = 
                itsBudgetKeyAdditionalDao.findByCriteria( 
                Restrictions.eq("budgetKeyAdditionalKey",
               anEntity.getBudgetKeyAdditionalKey()));
        // Ya existe un dato adicional para dicha clave
        if(others != null && !others.isEmpty()){
            throw new BaseBusinessException("ppp.additional.exists");
        }
                
        if (anEntity.getBudgetKeyAdditionalId() == null)
        {
            anEntity = itsBudgetKeyAdditionalDao.save(anEntity);
        }
        else
        {
            anEntity = itsBudgetKeyAdditionalDao.merge(anEntity);
        }
        return anEntity;
    }

    @Override
    public void delete(BudgetKeyAdditionalEntity anEntity) {
       itsBudgetKeyAdditionalDao.delete(anEntity);
    }

  @Override
  public void deleteAditional(String aBudgetKey)
  {
    this.itsBudgetKeyAdditionalDao.deleteAditional(aBudgetKey);
  }

    @Override
    public BudgetKeyAdditionalEntity getAdditional(String aBudgetKey) {
       return itsBudgetKeyAdditionalDao.getBudgetKeyAdditional(aBudgetKey);
    }

  
}
