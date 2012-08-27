/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  BudgetingControllerApi
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.api.controller;

import com.abs.siif.budget.dto.BudgetingKeysDto;
import com.abs.siif.budget.dto.FinancingSourceDto;
import com.abs.siif.budget.entities.*;
import com.abs.siif.budget.management.BudgetDetailsKeyManagement;
import com.abs.siif.budget.management.BudgetKeyConfigurationManagement;
import com.abs.siif.budget.management.BudgetKeyDefinitionManagement;
import com.abs.siif.budget.management.CeilingBudgetManagement;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.ppp.programming.controller.BudgetingSummaryController;
import com.abs.siif.ppp.programming.uihelpers.BudgetingKeysDtoDataModel;
import com.abs.siif.programming.management.BudgetingManagement;
import com.abs.siif.programming.management.DraftProjectManagement;
import java.util.List;
import java.util.Map;

/**
 *
 * @author luis.carreon
 */
public interface BudgetingControllerApi {

    void anualMonths();

    void deleteBudgetingKey();

    /**
     * Este metodo sirve para igualar los meses segun lo que se haya puesto en enero
     *
     */
    void evenMonths();

    Long getItsBudgetingKeyId();

    BudgetingKeysDtoDataModel getItsBudgetingKeysModel();

    Long getItsDependenceId();

    Long getItsDraftProjectId();

    List<BudgetingKeysDto> getItsListOfBudgetingKeys();

    List<DestinyObjectExpenseRUBUEntity> getItsListOfDestinations();

    List<FinancingSourceDto> getItsListOfFinancingSourceDto();

    List<FinancingSourceEntity> getItsListOfFinancingSources();

    Map<String, String> getItsMonthlyAmounts();

    BudgetKeyEntity getItsMyBudgetKeyToModify();

    Long getItsMyObjectExpenseId();

    String getItsProgrammaticKey();

    BudgetingKeysDto getItsSelectedBudgetKeyRow();

    String getItsSelectedDestinationId();

    Long getItsSelectedFinancingSourceId();

    Long getItsSelectedObjectExpense();

    String getItsSummatoryCantity();
    
    Long getItsSummatoryAdititionalCantity();

    BudgetingManagement getTheirBudgetingManagement();

    BudgetKeyConfigurationManagement getTheirbudgetKeyConfigurationManagement();
    
    

    BudgetingSummaryController getTheirbudgetingSummaryController();

    /**
     * este metodo se ejecuta al cargar la pagina y sirve para inicializar
     * las combos
     */
    void initBudgeting();

    boolean isIsAditionalBudgeting();

    boolean getBtnSrvSave();

    boolean isItsModifyingABudgetKey();

    void modifyBudgetingKey();

    /**
     * Este Metodo nos guarda la clave Presupuestal
     */
    void saveBudgetingKey();

    void setIsAditionalBudgeting(boolean isAditionalBudgeting);

    void setBtnSrvSave(boolean itsBtnSrvSave);

    void setItsBudgetingKeyId(Long itsBudgetingKeyId);

    void setItsBudgetingKeysModel(BudgetingKeysDtoDataModel itsBudgetingKeysModel);

    void setItsDependenceId(Long itsDependenceId);

    void setItsDraftProjectId(Long itsDraftProjectId);

    void setItsListOfBudgetingKeys(List<BudgetingKeysDto> itsListOfBudgetingKeys);

    void setItsListOfDestinations(List<DestinyObjectExpenseRUBUEntity> itsListOfDestinations);

    void setItsListOfFinancingSourceDto(List<FinancingSourceDto> itsListOfFinancingSourceDto);

    void setItsListOfFinancingSources(List<FinancingSourceEntity> itsListOfFinancingSources);


    void setItsModifyingABudgetKey(boolean itsModifyingABudgetKey);

    void setItsMyBudgetKeyToModify(BudgetKeyEntity itsMyBudgetKeyToModify);

    void setItsMyObjectExpenseId(Long itsMyObjectExpenseId);

    void setItsProgrammaticKey(String itsProgrammaticKey);

    void setItsSelectedBudgetKeyRow(BudgetingKeysDto itsSelectedBudgetKeyRow);

    void setItsSelectedDestinationId(String itsSelectedDestinationId);

    void setItsSelectedFinancingSourceId(Long itsSelectedFinancingSourceId);

    void setItsSelectedObjectExpense(Long itsSelectedObjectExpense);

    void setItsSummatoryCantity(String itsSummatoryCantity);
    
    void setItsSummatoryAdititionalCantity(Long itsSummatoryAdititionalCantity);

    void setMonthlyAmounts(Map<String, String> amonthlyAmounts);

    void setTheirBudgetingManagement(BudgetingManagement theirBudgetingManagement);

    void setTheirbudgetKeyConfigurationManagement(BudgetKeyConfigurationManagement theirbudgetKeyConfigurationManagement);

    void setTheirbudgetingSummaryController(BudgetingSummaryController theirbudgetingSummaryController);

    String getItsMyJustification();

    void setItsMyJustification(String itsMyJustification);
    
    void chargeOfCombos();
    
    List<BudgetingKeysDto> getMyListOfDtos(String aProgrammaticKey);
    
    String getItsTheMessageBox();
    
    void setItsTheMessageBox(String itsTheMessageBox);
    
    void prepareTheMessage();
    
    void selectActionToDo();
    
    boolean isItsJustificationTextBoxReadOnly();
    
    void setItsJustificationTextBoxReadOnly(boolean aJustificationTextBoxReadOnly);
    
    void totalOfSummatory();
    
    void budgetingCeilingAmountBasic();
    
    void budgetingCeilingAmount();
    
    void financingCeilingAmount();
    
    String getItsMyCeilingToshow();
    
     void setItsMyCeilingToshow(String itsMyCeilingToshow);
     
    Long getItsMyAvailableToMakeOp();
    
   void setItsMyAvailableToMakeOp(Long itsMyAvailableToMakeOp);
   
    String getItsMyFinAvailableToShow();
    
    void setItsMyFinAvailableToShow(String itsMyFinAvailableToShow);
    
    String getItsMyFinCeilingToShow();
    
    void setItsMyFinCeilingToShow(String itsMyFinCeilingToShow);

    public DraftProjectManagement getTheirDraftProjectManagement();

    public void setTheirDraftProjectManagement(DraftProjectManagement theirDraftProjectManagement);
    
     BudgetKeyDefinitionManagement getTheirBudgetKeyDefinitionManagement();
     
    void setTheirBudgetKeyDefinitionManagement(BudgetKeyDefinitionManagement theirBudgetKeyDefinitionManagement);

    CeilingBudgetManagement getTheirCeilingBudgetManagement();

    void setTheirCeilingBudgetManagement(CeilingBudgetManagement theirCeilingBudgetManagement);
    
    String getItsMyCeilingAvailableToShow();
     
    void setItsMyCeilingAvailableToShow(String itsMyCeilingAvailableToShow);
    
    String getItsMyBasicAvailable();

    void setItsMyBasicAvailable(String itsMyBasicAvailable);

    String getItsMyBasicCeiling();

    void setItsMyBasicCeiling(String itsMyBasicCeiling);

    String getItsMyBasicCeilingUsed();

    void setItsMyBasicCeilingUsed(String itsMyBasicCeilingUsed);
    
    List<ObjectExpenseEntity> getItsListOfFrammingObjectExpense();

    void setItsListOfFrammingObjectExpense(List<ObjectExpenseEntity> itsListOfFrammingObjectExpense);
    
    Long getMyResultCeilingBudget();

    void setMyResultCeilingBudget(Long myResultCeilingBudget);
    
    String getMySumTotalStringFormat();

    void setMySumTotalStringFormat(String mySumTotalStringFormat);
    
    public Long getitsTotalByChapter();

    public void setitsTotalByChapter(Long myTotalByChapter);
    
    public String getItsTotalPerChapterString();

    public void setItsTotalPerChapterString(String itsTotalPerChapterString);
    Long getMyBasicDiference();

    void setMyBasicDiference(Long myBasicDiference);
    
    Long getMyAvailableREsult();
    
    void setMyAvailableREsult(Long myAvailableREsult);
    
    String getItsBudgetKeyToModify();

    void setItsBudgetKeyToModify(String itsBudgetKeyToModify);
    
    public DepencenceDto getItsUrDependence();

    public void setItsUrDependence(DepencenceDto itsUrDependence);

    public ObjectExpenseEntity getObjectExpenseSelected();

    public void setObjectExpenseSelected(ObjectExpenseEntity objectExpenseSelected);
    
    public void disableAditional();
    
    void setItsSummatoryAdititionalQuantityAux(String input);
    
    String getItsSummatoryAdititionalQuantityAux();
}
