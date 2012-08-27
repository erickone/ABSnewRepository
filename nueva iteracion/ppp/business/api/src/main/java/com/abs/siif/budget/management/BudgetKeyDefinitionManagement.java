/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyDefinitionManagement
 *  Purpose:  Interface for Budget Key Definition
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.management;

import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;

/**
 *
 * @author FENIX-02
 */
public interface BudgetKeyDefinitionManagement
{
    public BudgetKeyDefinitionEntity getBudgetDefinitionByYear(int aYear);
}
