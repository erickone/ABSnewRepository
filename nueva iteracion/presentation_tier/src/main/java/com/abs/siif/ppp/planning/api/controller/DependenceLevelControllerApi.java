/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependenceLevelControllerApi
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.api.controller;

import com.abs.siif.common.entities.ColectiveTypeEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import com.abs.siif.ppp.planning.uihelpers.DependenceLevelDataModel;
import java.util.List;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Erick Leija
 */
public interface DependenceLevelControllerApi
{
    //Aqui se encuentran todos los metodos del controller****************************************
    void init();
    
    void cleanScreen();
    
    void saveLevelOfDependence();
    
    void deleteLevelOfDependence();
    
    void prepareNewLevelOfDependence();
    
    int getTheLastLevel();
    
    void onRowSelect(SelectEvent event);
    
    
    //Aqui comienza todos los Getters************************************************************
    List<DependenceLevelEntity> getItsListOfDependenceLevels();
    
    int getItsLevel();

    String getItsLevelDescription();

    boolean isItsLevelHasBranch();

    boolean isItsLevelHasCeilingBudget();

    boolean isItsLevelHasClassifierUnit();

    boolean isItsLevelHasEmployee();

    boolean isItsLevelHasFramingAdminClassif();
    
    boolean isItsLevelHasHistorical();

    boolean isItsLevelHasInstitutionalPlan();

    boolean isItsLevelHasInventory();

    boolean isItsLevelHasPaymentRequest();

    boolean isItsLevelHasProyDependence();

    boolean isItsLevelHasRequisition();

    boolean isItsLevelHasResponsibleUnit();

    boolean isItsLevelHasSector();

    boolean isItsLevelHasUEG();

    String getItsLevelKey();

    List<ColectiveTypeEntity> getItsListOfColectiveType();

    Long getItsSelectedColectiveType();

    int getItsYearOfEjecution();
    
    DependenceLevelDataModel getItsMyDependenceLevelDataModel();
    
    DependenceLevelEntity getItsSelectedRow();
    
    //*******************************************************************************************
    
    //Aqui comienza todos los Setters************************************************************
    void setItsListOfDependenceLevels(List<DependenceLevelEntity> itsListOfDependenceLevels);

    void setItsLevel(int itsLevel);

    void setItsLevelDescription(String itsLevelDescription);

    void setItsLevelHasBranch(boolean itsLevelHasBranch);

    void setItsLevelHasCeilingBudget(boolean itsLevelHasCeilingBudget);

    void setItsLevelHasClassifierUnit(boolean itsLevelHasClassifierUnit);

    void setItsLevelHasEmployee(boolean itsLevelHasEmployee);

    void setItsLevelHasFramingAdminClassif(boolean itsLevelHasFramingAdminClassif);

    void setItsLevelHasHistorical(boolean itsLevelHasHistorical);

    void setItsLevelHasInstitutionalPlan(boolean itsLevelHasInstitutionalPlan);

    void setItsLevelHasInventory(boolean itsLevelHasInventory);

    void setItsLevelHasPaymentRequest(boolean itsLevelHasPaymentRequest);

    void setItsLevelHasProyDependence(boolean itsLevelHasProyDependence);

    void setItsLevelHasRequisition(boolean itsLevelHasRequisition);
    
    void setItsLevelHasResponsibleUnit(boolean itsLevelHasResponsibleUnit);

    void setItsLevelHasSector(boolean itsLevelHasSector);

    void setItsLevelHasUEG(boolean itsLevelHasUEG);

    void setItsLevelKey(String itsLevelKey);

    void setItsListOfColectiveType(List<ColectiveTypeEntity> itsListOfColectiveType);

    void setItsSelectedColectiveType(Long itsSelectedColectiveType);
    
    void setItsYearOfEjecution(int itsYearOfEjecution);
    
    void setItsMyDependenceLevelDataModel(DependenceLevelDataModel itsMyDependenceLevelDataModel);
    
    void setItsSelectedRow(DependenceLevelEntity itsSelectedRow);
    //******************************************************************************************
    
}
