/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyItemManagement
 *  Purpose:  Interface for business logic related to Budget Key Items.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.management;

import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface BudgetKeyItemManagement
{
   public List<BudgetKeyItemEntity> getBudgetKeyAllItems(); 
   
   public List<BudgetKeyItemEntity> getBudgetItemsRelatedCeilingConf(Long aCeilingConfId);
}
