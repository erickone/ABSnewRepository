/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyItemManagementImpl
 *  Purpose:  Interface to SiifpppElemento
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.management;

import com.abs.siif.base.management.ManagementBase;
import com.abs.siif.budget.dao.BudgetKeyItemDao;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("budgetKeyItemManagement")
public class BudgetKeyItemManagementImpl extends ManagementBase 
    implements BudgetKeyItemManagement, Serializable{
    
    @Resource(name = "budgetKeyItemDao")
    private transient BudgetKeyItemDao itsBudgetKeyItemDao;

    @Override
    public List<BudgetKeyItemEntity> getBudgetKeyAllItems()
    {
        List<BudgetKeyItemEntity> myBudget = (List<BudgetKeyItemEntity>) itsBudgetKeyItemDao.getBudgetKeyItems();
        return myBudget;
    }

    @Override
    public List<BudgetKeyItemEntity> getBudgetItemsRelatedCeilingConf(Long aCeilingConfId)
    {
        return itsBudgetKeyItemDao.getBudgetItemsRelatedCeilingConf(aCeilingConfId);
    }

}
