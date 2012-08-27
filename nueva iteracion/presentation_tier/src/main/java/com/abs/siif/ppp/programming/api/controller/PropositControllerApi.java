/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  PropositControllerApi
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

import com.abs.siif.ppp.programming.controller.DeliveryFile;
import com.abs.siif.programming.entities.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.primefaces.event.DateSelectEvent;

/**
 *
 * @author luis.carreon
 */
public interface PropositControllerApi {

    /**
     * Metodo que se encarga de ralizar los calculos para obtener los acumulados
     * de mes a mes del componente
     */
    void blurPropMpp();

    /**
     * Limpia los elementos que contiene la forma de Entregables
     */
    void clearComponent();

    /**
     * Deshabilita los input de meses
     */
    void disableMonths();

    /**
     * @return the accProg
     */
    Map<String, String> getAccProg();

    /**
     * @return the activityProComp
     */
    Map<String, String> getActivityProComp();

    /**
     * @return the advanProg
     */
    Map<String, String> getAdvanProg();

    /**
     * @return the compNumb
     */
    String getCompNumb();

    /**
     * @return the componenet
     */
    ComponentEntity getComponent();

    /**
     * Extrae el siguiente contador
     *
     * @return int count
     */
    int getCount();

    /**
     * @return the deliveries
     */
    List<DeliveryEntity> getDeliveries();

    /**
     * @return the deliveryFile
     */
    DeliveryFile getDeliveryFile();

    /**
     * @return the draftProject
     */
    DraftProjectEntity getDraftProject();

    /**
     * @return the draftProjectId
     */
    Long getDraftProjectId();

    /**
     * @return the goalProComp
     */
    Map<String, String> getGoalProComp();

    String getId();

    /**
     * @return the itsSelectUMId
     */
    Long getItsSelectUMId();

    /**
     * @return the itsSelectedUM
     */
    UnitMeasureEntity getItsSelectedUM();

    /**
     * @return the itsUMs
     */
    Collection<UnitMeasureEntity> getItsSelectedUMs();

    String getMaxDate();

    String getMinDate();

    /**
     * @return the nextActivity
     */
    int getNextActivity();

    String getNextId();

    /**
     * @return the propositId
     */
    Long getPropositId();

    /**
     * @return the propositNew
     */
    String getPropositNew();

    /**
     *
     * @return quantity
     */
    String getQuantity();

    /**
     *
     * @return startDate
     */
    Date getStartDate();

    /**
     * Este metodo se encarga de cargar el entregable de la ficha De obtener los
     * catalogos de Unidad de Medida
     */
    void initProposit();

    /**
     *
     * @return disableAbr
     */
    boolean isDisableAbr();

    /**
     * Metodo que permite habilitar o deshabilitar en caso que el entregable que
     * se este tabajando ya esta guardado, encaso que se este colocand un nuevo
     * el boton se muestra deshabilitado;
     *
     * @return
     */
    boolean isDisableActiv();

    /**
     *
     * @return disableAgt
     */
    boolean isDisableAgt();

    /**
     *
     * @return itsBtnSrvSave
     */
    boolean getBtnSrvSave();

    /**
     *
     * @return disableDic
     */
    boolean isDisableDic();

    /**
     *
     * @return disableEn
     */
    boolean isDisableEn();

    /**
     *
     * @return disableFeb
     */
    boolean isDisableFeb();

    /**
     *
     * @return disableJul
     */
    boolean isDisableJul();

    /**
     *
     * @return disableJun
     */
    boolean isDisableJun();

    /**
     *
     * @return disableMar
     */
    boolean isDisableMar();

    /**
     *
     * @return disableMay
     */
    boolean isDisableMay();

    /**
     *
     * @return disableNov
     */
    boolean isDisableNov();

    /**
     *
     * @return disableOct
     */
    boolean isDisableOct();

    /**
     *
     * @return disableSep
     */
    boolean isDisableSep();

    /**
     * Ingresa el proposito al entregable y recarga el catalogo de propositos
     */
    void reloadProposit();

    /**
     * Se encarga de procesar la peticiÃ³n de guardado del componente sobre el
     * proposito seleccionado El componente que trea los datos es el componente
     * del control
     */
    void saveComponent();

    void selectStarDate(DateSelectEvent event);

    /**
     * @param accProg the accProg to set
     */
    void setAccProg(Map<String, String> accProg);

    /**
     * @param activityProComp the activityProComp to set
     */
    void setActivityProComp(Map<String, String> activityProComp);

    /**
     * @param advanProg the advanProg to set
     */
    void setAdvanProg(Map<String, String> advanProg);

    void setCloseAction(ActivityEntity args);

    void setCloseProposit(String proposit);

    /**
     * @param compNumb the compNumb to set
     */
    void setCompNumb(String compNumb);

    /**
     * @param componenet the componenet to set
     */
    void setComponent(ComponentEntity componenet);

    /**
     * Resetea el contador
     */
    void setCount();

    void setDeleteCom(ComponentEntity component);

    /**
     * @param deliveries the deliveries to set
     */
    void setDeliveries(List<DeliveryEntity> deliveries);

    /**
     * @param deliveryFile the deliveryFile to set
     */
    void setDeliveryFile(DeliveryFile deliveryFile);

    /**
     * @param disableAbr the disableAbr to set
     */
    void setDisableAbr(boolean disableAbr);

    /**
     * @param disableActiv the disableActiv to set
     */
    void setDisableActiv(boolean disableActiv);

    /**
     * @param disableAgt the disableAgt to set
     */
    void setDisableAgt(boolean disableAgt);

    /**
     * @param disableDic the disableDic to set
     */
    void setDisableDic(boolean disableDic);

    /**
     * @param disableEn the disableEn to set
     */
    void setDisableEn(boolean disableEn);

    /**
     * @param disableFeb the disableFeb to set
     */
    void setDisableFeb(boolean disableFeb);

    /**
     * @param disableJul the disableJul to set
     */
    void setDisableJul(boolean disableJul);

    /**
     * @param disableJun the disableJun to set
     */
    void setDisableJun(boolean disableJun);

    /**
     * @param disableMar the disableMar to set
     */
    void setDisableMar(boolean disableMar);

    /**
     * @param disableMay the disableMay to set
     */
    void setDisableMay(boolean disableMay);

    /**
     * @param disableNov the disableNov to set
     */
    void setDisableNov(boolean disableNov);

    /**
     * @param disableOct the disableOct to set
     */
    void setDisableOct(boolean disableOct);

    /**
     * @param disableSep the disableSep to set
     */
    void setDisableSep(boolean disableSep);

    /**
     * @param draftProject the draftProject to set
     */
    void setDraftProject(DraftProjectEntity draftProject);

    /**
     * @param draftProjectId the draftProjectId to set
     */
    void setDraftProjectId(Long draftProjectId);

    /**
     * @param goalProComp the goalProComp to set
     */
    void setGoalProComp(Map<String, String> goalProComp);

    /**
     * @param itsBtnSrvSave the itsBtnSrvSave to set
     */
    void setBtnSrvSave(boolean itsBtnSrvSave);

    /**
     * @param itsSelectUMId the itsSelectUMId to set
     */
    void setItsSelectUMId(Long itsSelectUMId);

    /**
     * @param itsSelectedUM the itsSelectedUM to set
     */
    void setItsSelectedUM(UnitMeasureEntity itsSelectedUM);

    /**
     * @param itsUMs the itsUMs to set
     */
    void setItsSelectedUMs(Collection<UnitMeasureEntity> itsSelectedUMs);

    /**
     * @param nextActivity the nextActivity to set
     */
    void setNextActivity(int nextActivity);

    /**
     * @param propositId the propositId to set
     */
    void setPropositId(Long propositId);

    /**
     * Esta propieda se llama demanera parcial desde PrimeFaces por lo que a
     * partir de entrar se ejecuta la recarga del Catalogo
     *
     * @param propositNew the propositNew to set
     */
    void setPropositNew(String propositNew);

    /**
     *
     * @param quantity
     */
    void setQuantity(String quantity);

    void setSaveAction(ActivityEntity args);

    /**
     * @param selectedActivity the selectedActivity to set
     */
    void setSelectedActivity(ActivityEntity selectedActivity);

    /**
     * @param selectDeleteActivity the selectDeleteActivity to set
     */
    void setSelectedToDeleteAct(ActivityEntity selectedToDeleteAct);

    /**
     * @param startDate the startDate to set
     */
    void setStartDate(Date startDate);

    /**
     * Toma el componente selecionado para actualizarlo
     *
     * @param component
     */
    void setUpdateCom(ComponentEntity component);

    /**
     * Toma el componente selecionado para usuarlo en Actividades
     *
     * @param component
     */
    void setUpdateComNoDeleted(ComponentEntity component);

    void validateDatesToEnableMonths(DateSelectEvent event);

    void validateMaxComp();
    
}
