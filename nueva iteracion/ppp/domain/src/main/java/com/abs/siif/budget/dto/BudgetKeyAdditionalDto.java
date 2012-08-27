/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyAdditionalDto
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.dto;

import com.abs.siif.budget.entities.BudgetKeyAdditionalEntity;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.entities.DependenceEntity;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class BudgetKeyAdditionalDto {
 
    private BudgetKeyAdditionalEntity budgetKeyAdditional;
    private DependenceEntity dependency;
    private ObjectExpenseEntity objectExpense;
    

    public BudgetKeyAdditionalEntity getBudgetKeyAdditional() {
        return budgetKeyAdditional;
    }

    public void setBudgetKeyAdditional(BudgetKeyAdditionalEntity budgetKeyAdditional) {
        this.budgetKeyAdditional = budgetKeyAdditional;
    }

    
    
    public DependenceEntity getDependency() {
        return dependency;
    }

    public void setDependency(DependenceEntity dependency) {
        this.dependency = dependency;
    }

    public ObjectExpenseEntity getObjectExpense() {
        return objectExpense;
    }

    public void setObjectExpense(ObjectExpenseEntity objectExpense) {
        this.objectExpense = objectExpense;
    }
    
    
}
