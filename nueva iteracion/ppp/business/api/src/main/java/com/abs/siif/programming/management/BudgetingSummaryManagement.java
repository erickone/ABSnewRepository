/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.budget.dto.BudgetKeyAdditionalDto;
import com.abs.siif.budget.dto.BudgetSummaryDto;
import com.abs.siif.budget.dto.BudgetingDependenceTotalDto;
import com.abs.siif.budget.dto.BudgetingSummaryDto;
import com.abs.siif.budget.entities.BudgetingCeilingEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.entities.ComponentEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface BudgetingSummaryManagement
{
    List<DepencenceDto> getTheRelatedUEGs(DependenceEntity aDependence);
    List<ComponentEntity> getCompByDraftProId(Long DraftProjectId);    
    DependenceEntity getDependenceById(Long aDependenceId);
    List<ObjectExpenseEntity> getObjectExpenseRoots();
    BudgetSummaryDto getAllAmountsOfChapters(String aProgrammaticKey,String aditionalFlag);
    Collection<ObjectExpenseEntity> getTheObjectExpenseHeirachy(Long myObjectExpenseId);
    Long getTheLevelofTheSpecificParId();
    Long getAvailableAdditional(BudgetKeyAdditionalDto anAdditionalDto);
    List<BudgetingDependenceTotalDto> getMyDependenceSummaryDTO
            (List<DepencenceDto> myListOfDependences,Long DrafProjectId);
    List<BudgetingSummaryDto> getTheMegaSummaryByUR
            (List<DepencenceDto> myListOfDependences,Long DrafProjectId, 
            List<ObjectExpenseEntity> myObjectExpenseRoots);
}
