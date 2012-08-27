/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyAdditionalDao
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

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.dto.BudgetKeyAdditionalDto;
import com.abs.siif.budget.entities.BudgetKeyAdditionalEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface BudgetKeyAdditionalDao extends 
        SIIFBaseDao<BudgetKeyAdditionalEntity, Long> {
    
    Collection<BudgetKeyAdditionalEntity> 
            getBudgetKeyAdditionalsByObjectExpenseAndDependency
            (BudgetKeyAdditionalDto anAdditionalDto);
    
    double getSumByBudgetKey(BudgetKeyAdditionalDto anAdditionalDto);
    
    BudgetKeyAdditionalEntity getBudgetKeyAdditionalByIdentity(
            BudgetKeyAdditionalDto anAdditionalDto);
    
    void deleteAditional(String aBudgetKey);
    
    BudgetKeyAdditionalEntity getBudgetKeyAdditional(String aBudgetKeyId); 
    
}
