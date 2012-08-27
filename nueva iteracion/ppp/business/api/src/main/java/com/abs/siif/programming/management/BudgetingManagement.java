/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.budget.dto.BudgetDispURDto;
import com.abs.siif.budget.dto.BudgetKeyBreakDownDto;
import com.abs.siif.budget.dto.BudgetingSummaryDto;
import com.abs.siif.budget.dto.DestinationDto;
import com.abs.siif.budget.entities.*;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import java.util.Collection;
import java.util.List;

/**
 * esta es la interfaz del management para el control de las operaciones de la
 * ventana de presupuestación
 *
 * @author Erick Leija
 */
public interface BudgetingManagement {

    List<DestinyObjectExpenseRUBUEntity> getTheBudgetingDestinationByObject(DependenceEntity aDependence, ObjectExpenseEntity aBudgetingDto);

    List<ObjectExpenseEntity> getTheBudgetingFramming(DependenceEntity aDependence, BudgetingSummaryDto aBudgetingDto, boolean investFlag);

    FinancingSourceEntity getFinancingSourceById(FinancingSourceEntity aFinancingSource);

    Collection<FinancingSourceEntity> getFinancingSourceByObjectExpense(Long anObjectExpenseId);

    Collection<DestinationEntity> getDestinyByObjectExpense(Long anObjectExpenseId);

    Long saveBudgetKey(BudgetKeyEntity aBudgetEntity, BudgetDispURDto dto, Boolean ficha);

    Long saveBudgetAndProjectFraming(BudgetKeyAndDraftProjectFramingEntity aBudgetEntity);

    void deleteABudgetAndProjectFraming(BudgetKeyAndDraftProjectFramingEntity aBudgerFramingEntity);

    BudgetKeyAndDraftProjectFramingEntity getABudgetAndProjectFramingByIds(Long aProjectId, Long aBudgetKeyId);

    Long getCeilingAvailableByDependenceId(Long aDependenceId);

    void persistBudgetKey(BudgetKeyEntity aBudgetEntity);

    ObjectExpenseEntity getObjectExpenseById(Long anObjectExpenseId);

    Collection<BudgetKeyEntity> getTheBudgetingKeysWithProgramaticKey(String aProgranmaticKey);

    void deleteBudgetProcess(BudgetDispURDto dto);
    
    void deleteBudgetKey(Long aBudgetKeyEntity);

    BudgetKeyEntity getBudgetEntityById(Long aBudgetEntiityId);

    void updateABudgetKeyEntity(BudgetKeyEntity myBudgetKey, BudgetDispURDto dto);

    DestinationEntity getDestinationByKey(String aKey);

    BudgetKeyBreakDownDto getBudgetDesgloce(Long lCvePptalId);

    boolean modifyCeilingBudget(BudgetDispURDto dto, BudgetKeyBreakDownDto objectDto);

    public DepencenceDto getDependenciesRespUnitBytDependIdRelated(Long dependenceId);
    
    List<DestinyObjectExpenseRUBUEntity> getTheBudgetingDestinationByObjectInv(DependenceEntity aDependence, ObjectExpenseEntity aBudgetingDto);
    
    DestinationDto getEncDepObjGasDest(Long anObjectExpenseId, Long aDependenceId, String aDestinyKey);
}
