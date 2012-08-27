/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  BudgetingSummaryControllerApi
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

import com.abs.siif.budget.dto.BudgetSummaryDto;
import com.abs.siif.budget.dto.BudgetingDependenceTotalDto;
import com.abs.siif.budget.dto.BudgetingSummaryDto;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.programming.uihelpers.BudgetingSummaryDtoDataModel;
import java.util.List;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author luis.carreon
 */
public interface BudgetingSummaryControllerApi {

    Long getMyDependenceId();

    Long getMyDraftProjectId();

    List<ObjectExpenseEntity> getMyListOfObjectExpense();

    List<DepencenceDto> getMyListOfUEG();

    Long getMyObjectExpenseId();

    List<BudgetingSummaryDto> getMyrealList();

    BudgetingSummaryDto getSelectedRow();

    BudgetingSummaryDtoDataModel getTheirBudgetingSummaryDtoDataModel();

    /**
     * Este metodo es ek encargado de inicializar la lista de los capitulos que hay en
     * la base de datos, asi como sus techos presupuestales
     */
    void initListOfComponents();

    
    /**
     * Este metodo ejecuta el metodo para cargar la lista de las Dependencias
     * y sus presupuestados totales
     */
    void loadTheURSummary();
    
    
    /**
     * este Metodo se ejecuta para cargar la lista de los capitulos de Gasto
     */
    void loadTheSummary();
    /**
     * este metodo se ejcuta cuando se realiza un doble click en el registro
     * nota: solo soportado a partir de la version 3.3 de primefaces
     * @param event
     */
    
    
    void onRowDblselect(SelectEvent event);

    /**
     * este metodo se ejecuta cuando se selecciona un registro de la tabla
     * entonces de manera magica por medio del id del objeto gasto
     * nos traemos sus parientes de ultimo nivel, así como su encuadre con los
     * techos
     * @param event
     */
    void onRowSelect(SelectEvent event);

    void setMyDependenceId(Long myDependenceId);

    void setMyDraftProjectId(Long myDraftProjectId);

    void setMyListOfObjectExpense(List<ObjectExpenseEntity> myListOfObjectExpense);

    void setMyListOfUEG(List<DepencenceDto> myListOfUEG);

    void setMyObjectExpenseId(Long myObjectExpenseId);

    void setMyrealList(List<BudgetingSummaryDto> myrealList);

    void setSelectedRow(BudgetingSummaryDto selectedRow);
    
    Long getItsSelectedDependenceId();
     
    void setItsSelectedDependenceId(Long itsSelectedDependenceId);

    List<BudgetingDependenceTotalDto> getMyListOfBreakDownDependences();

    void setMyListOfBreakDownDependences(List<BudgetingDependenceTotalDto> myListOfBreakDownDependences);

    String getMyadditionalTotalDependenceStringFormat();

    void setMyadditionalTotalDependenceStringFormat(String myadditionalTotalDependenceStringFormat);

    String getMyInitialTotalDependenceStringFormat();

    void setMyInitialTotalDependenceStringFormat(String myInitialTotalDependenceStringFormat);

    String getMySumTotalDependenceStringformat();

    void setMySumTotalDependenceStringformat(String mySumTotalDependenceStringformat);
    
    boolean isItsMegaSummaryCheck();

    void setItsMegaSummaryCheck(boolean itsMegaSummaryCheck);
    
    boolean isItsComboDependenceCheck();

    void setItsComboDependenceCheck(boolean itsComboDependenceCheck);
}
