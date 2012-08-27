/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyDefinitionManagementImpl
 *  Purpose:  Business Operations for Budget Key Definition.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.management;

import com.abs.siif.budget.dao.BudgetKeyDefinitionDao;
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("budgetKeyDefinitionManagement")
public class BudgetKeyDefinitionManagementImpl implements BudgetKeyDefinitionManagement, Serializable{

    @Resource(name = "budgetKeyDefinitionDao")
    private BudgetKeyDefinitionDao itsBudgetKeyDefinitionDao;
    
    @Override
    public BudgetKeyDefinitionEntity getBudgetDefinitionByYear(int aYear)
    {
        return itsBudgetKeyDefinitionDao.getBudgetDefinitionByYear(aYear);
    }

}
