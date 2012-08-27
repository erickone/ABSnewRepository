/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingBudgetManagement
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

import com.abs.siif.budget.entities.DestinationEntity;
import com.abs.siif.budget.entities.FinancingSourceEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.support.BudgetDraftProjectEnum;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface CeilingBudgetManagement {

    Long getBudgetForDraftProject(DependenceEntity denpendency,
            ObjectExpenseEntity objExpens, String dest,
            FinancingSourceEntity financialSource);

    Long getBudgetAvailableForDraftProject(DependenceEntity denpendency,
            ObjectExpenseEntity objExpens, String dest,
            FinancingSourceEntity financialSource, boolean basics);

    Long getFinancingSourceCeiling(FinancingSourceEntity myFinanceSourceSelected);

    Long getFinancingSourceCeilingAvailable(FinancingSourceEntity myFinanceSourceSelected);

    List<DepencenceDto> getKeyByDependency(DependenceEntity denpendency,
            List<BudgetDraftProjectEnum> budgetKey);

    Long getBudgetForUR(DependenceEntity denpendency,
            ObjectExpenseEntity objExpens, String dest, FinancingSourceEntity financialSource);

    Long getBudgetByUEG(DraftProjectEntity aDraftProject);

    Long getAvailableCeilingBasics(String identitiesToserach, Long obj, String Dest);
    
    Long getBudgetAvailableForInvPreFile(DependenceEntity denpendency);
}
