/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  FinancialStructureControllerApi
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

import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.programming.dto.ApplicationBudgetDto;
import com.abs.siif.ppp.programming.dto.RelationDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.util.List;

/**
 *
 * @author luis.carreon
 */
public interface FinancialStructureControllerApi {

    /**
     * Click al boton de agregar una nueva
     * fila ala tabla de asignacion presupuestal
     */
    void addApplicationBudgetRow();

    /**
     * Click al boton de agregar una nueva
     * fila ala tabla de asignacion presupuestal
     */
    void addRelationRow();

    /**
     * Calculates the total allocation
     */
    void calculateTotals();

    /**
     * Change selection of ubication combo
     */
    void changeListenerCboD();

    /**
     * Change selection of Partida combo
     */
    void changeListenerCboPartida();

    void cleanView();

    String convertToString(int value);

    /**
     * @return the adicionalEsp
     */
    String getAdicionalEsp();

    /**
     * @return the adicionalEst
     */
    String getAdicionalEst();

    /**
     * @return the adicionalFed
     */
    String getAdicionalFed();

    /**
     * @return the adicionalMun
     */
    String getAdicionalMun();

    /**
     * @return the adicionalPart
     */
    String getAdicionalPart();

    /**
     * @return the adicionalTotal
     */
    String getAdicionalTotal();

    /**
     * @return the list
     */
    ApplicationBudgetDto getAppDto();

    /**
     * @return the list
     */
    ApplicationBudgetDto getApplicationBudgetList();

    /**
     * @return the asigEsp
     */
    String getAsigEsp();

    /**
     * @return the asigEst
     */
    String getAsigEst();

    /**
     * @return the asigFed
     */
    String getAsigFed();

    /**
     * @return the asigMun
     */
    String getAsigMun();

    /**
     * @return the asigPart
     */
    String getAsigPart();

    /**
     * @return the asigTotal
     */
    String getAsigTotal();

    /**
     * @return the concept
     */
    String getConcept();

    /**
     * @return the ids
     */
    List<Long> getIdsAppBudget();

    /**
     * @return the itsItemsAmbitoCbo
     */
    List<ObjectExpenseEntity> getItsItemsPartidaCbo();

    /**
     * @return the relationList
     */
    List<RelationDto> getRelationList();

    /**
     * @return the selectesAppBudgetDto
     */
    ApplicationBudgetDto[] getSelectesAppBudgetDto();

    Long getTheirInvPreFileId();

    /**
     * @return the totalEspecie
     */
    String getTotalEspecie();

    /**
     * @return the totalEstatal
     */
    String getTotalEstatal();

    /**
     * @return the totalFederal
     */
    String getTotalFederal();

    /**
     * @return the totalMunicipal
     */
    String getTotalMunicipal();

    /**
     * @return the totalParticular
     */
    String getTotalParticular();

    /**
     * @return the totalTotal
     */
    String getTotalTotal();

    /**
     * Metodo init de la vista
     */
    void initFinancialStructureController();

    /**
     * @return the enableAcceptButton
     */
    boolean isEnableAcceptButton();

    /**
     * @return the enableAddRelation
     */
    boolean isEnableAddRelation();

    /**
     * @return the enableAddRow
     */
    boolean isEnableAddRow();

    /**
     * Save a FinancialStucture
     */
    void saveFinancialStrcuture();

    /**
     * @param adicionalEsp the adicionalEsp to set
     */
    void setAdicionalEsp(String adicionalEsp);

    /**
     * @param adicionalEst the adicionalEst to set
     */
    void setAdicionalEst(String adicionalEst);

    /**
     * @param adicionalFed the adicionalFed to set
     */
    void setAdicionalFed(String adicionalFed);

    /**
     * @param adicionalMun the adicionalMun to set
     */
    void setAdicionalMun(String adicionalMun);

    /**
     * @param adicionalPart the adicionalPart to set
     */
    void setAdicionalPart(String adicionalPart);

    /**
     * @param adicionalTotal the adicionalTotal to set
     */
    void setAdicionalTotal(String adicionalTotal);

    /**
     * @param list the list to set
     */
    void setAppDto(ApplicationBudgetDto appDto);

    /**
     * @param list the list to set
     */
    void setApplicationBudgetList(ApplicationBudgetDto applicationBudgetList);

    /**
     * @param asigEsp the asigEsp to set
     */
    void setAsigEsp(String asigEsp);

    /**
     * @param setAsigEst the setAsigEst to set
     */
    void setAsigEst(String asigEst);

    /**
     * @param asigFed the asigFed to set
     */
    void setAsigFed(String asigFed);

    /**
     * @param asigMun the asigMun to set
     */
    void setAsigMun(String asigMun);

    /**
     * @param asigMun the asigMun to set
     */
    void setAsigPart(String asigPart);

    /**
     * @param asigTotal the asigTotal to set
     */
    void setAsigTotal(String asigTotal);

    /**
     * @param concept the concept to set
     */
    void setConcept(String concept);

    /**
     * @param enableAcceptButton the enableAcceptButton to set
     */
    void setEnableAcceptButton(boolean enableAcceptButton);

    /**
     * @param enableAddRelation the enableAddRelation to set
     */
    void setEnableAddRelation(boolean enableAddRelation);

    /**
     * @param enableAddRow the enableAddRow to set
     */
    void setEnableAddRow(boolean enableAddRow);

    /**
     * @param ids the ids to set
     */
    void setIdsAppBudget(List<Long> idsAppBudget);

    /**
     * @param itsItemsAmbitoCbo the itsItemsAmbitoCbo to set
     */
    void setItsItemsPartidaCbo(List<ObjectExpenseEntity> itsItemsPartidaCbo);

    /**
     * @param relationList the relationList to set
     */
    void setRelationList(List<RelationDto> relationList);

    /**
     * @param selectesAppBudgetDto the selectesAppBudgetDto to set
     */
    void setSelectesAppBudgetDto(ApplicationBudgetDto[] selectesAppBudgetDto);

    void setTheirInvPreFileId(Long theirInvPreFileId);

    /**
     * @param totalEspecie the totalEspecie to set
     */
    void setTotalEspecie(String totalEspecie);

    /**
     * @param totalFederal the totalFederal to set
     */
    void setTotalEstatal(String totalEstatal);

    /**
     * @param totalFederal the totalFederal to set
     */
    void setTotalFederal(String totalFederal);

    /**
     * @param totalMunicipal the totalMunicipal to set
     */
    void setTotalMunicipal(String totalMunicipal);

    /**
     * @param totalParticular the totalParticular to set
     */
    void setTotalParticular(String totalParticular);

    /**
     * @param totalTotal the totalTotal to set
     */
    void setTotalTotal(String totalTotal);

    /**
     * REaliza la suma de los totales y formatea el resultado
     * @param values valores a sumar
     * @return resultado de la suma formateada
     */
    String sumFormat(String... values);
    
    /**
     * si el metodo calcular termino
     *
     * @param calculateFinish
     */
    void setFinish(boolean finish);
    
     /**
     * si el metodo calcular termino
     *
     * @return calculateFinish
     */
    boolean isFinish();
    
    /**
     * Se cambio la seleccion de unidad ejecutora de gasto
     */
    void changeListenerCboUEG();
    
    /**
     * Guarda la relacion dada de alta, se guarda la clave presupuestal
     * y cual es su asignacion estatal
     */
    void saveRelation();
    
    /**
     * cambia seleccion del combo fuentes de financiaiento.
     */
    void changeListenerCboFinancialSource();
    
    
    DraftProjectEntity getTheirDraftProjectEntity();

    void setTheirDraftProjectEntity(DraftProjectEntity theirDraftProjectEntity);
    
    boolean isChecked();

    void setChecked(boolean checked);  
    
    Long getObjetoGasto();

    void setObjetoGasto(Long objetoGasto);   
    

    String getCvePresupuestal();

    void setCvePresupuestal(String cvePresupuestal);

    List<DependenceEntity> getItsItemsUEGCbo();

    void setItsItemsUEGCbo(List<DependenceEntity> itsItemsUEGCbo);
      
    RelationDto getTheirDeleteRelation();
    
    void setTheirDeleteRelation(RelationDto theirDeleteRelation);
    
    RelationDto getTheirEditRelation();
    
    void setTheirEditRelation(RelationDto theirEditRelation);
    
    void cancelRelation();
    
    void editRelation();
    
    void deleteRelation();
                   
    boolean btnDisabledDelete(int index);

    boolean btnDisabledEdit(int index);
    
}
