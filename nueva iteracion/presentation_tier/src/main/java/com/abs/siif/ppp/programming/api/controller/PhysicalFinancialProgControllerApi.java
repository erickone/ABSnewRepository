/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  PhysicalFinancialProgControllerApi
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

import com.abs.siif.programming.dto.FinancialAdvProgDto;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.primefaces.event.DateSelectEvent;

/**
 *
 * @author luis.carreon
 */
public interface PhysicalFinancialProgControllerApi {

    /**
     * metodo encargado de calcular el total del avance financiero en la seccion de
     * aportaciones
     */
    void calcTotalFinancialAdvanced();

    /**
     * metodo encargado de calcular el total del avance fisico en la
     * seccion de aportaciones
     */
    void calcTotalPhysicAdvanced();

    /**
     * metodo encargado de calcular el total del avance fisico programado
     */
    void calcTotalPhysicalAdvan();

    void disabledMonths();

    /**
     * Metodo encargado validar cuales meses del avance fisico programado estaran
     * habilitados
     */
    void enabledMonths();

   
    /**
     * metodo que calcula la diferencia en meses entre la fecha de inicio y la
     * fecha final de la obra
     */
    void getCalcDuracionTime();

    /**
     *
     * @return duracion
     */
    int getDuracion();

    /**
     *
     * @return duracionTime
     */
    String getDuracionTime();

    /**
     *
     * @return el mensaje para el header de avance financiero programado para
     */
    String getFinancialAdvancedHeader();

    /**
     *
     * @return financialCurrent
     */
    String getFinancialCurrent();

    /**
     *
     * @return financialToExcuteNextYears
     */
    String getFinancialToExcuteNextYears();

    /**
     *
     * @return financialToExecute
     */
    String getFinancialToExecute();

    /**
     *
     * @return financialTotal
     */
    String getFinancialTotal();

    /**
     *
     * @return InvPreFileEntity
     */
    InvPreFileEntity getInvPreFileEntity();

    /**
     *
     * @return theirPreFileId
     */
    Long getInvPreFileId();

    /**
     *
     * @return
     */
    String getMaxDateStartDate();

    /**
     * Regresa la fecha minina dependiendo del año seleccionado
     * del combo del optionsController
     * @return miDate
     */
    String getMinDateStartDate();
    
       /**
     *
     * @return
     */
    String getMaxDateEndDate();

    /**
     * Regresa la fecha minina dependiendo del año seleccionado
     * del combo del optionsController
     * @return miDate
     */
    String getMinDateEndDate();

    /**
     *
     * @return physicalAdvan
     */
    Map<String, String> getPhysicalAdvan();

    /**
     *
     * @return el mensaje para el header de avance ficico programado para
     */
    String getPhysicalAdvancedHeader();

    /**
     *
     * @return physicalCurrent
     */
    String getPhysicalCurrent();

    /**
     *
     * @return theirPhysicalFinancialEndDate
     */
    Date getPhysicalFinancialEndDate();

    /**
     *
     * @return theirPhysicalFinancialStartDate
     */
    Date getPhysicalFinancialStartDate();

    /**
     *
     * @return physicalToExcuteNextYears
     */
    String getPhysicalToExcuteNextYears();

    /**
     *
     * @return physicalToExecute
     */
    String getPhysicalToExecute();

    /**
     *
     * @return physicalTotal
     */
    String getPhysicalTotal();

    /**
     *
     * @return processType
     */
    int getProcessType();

    /**
     *
     * @return el mensaje para el header por ejecutar en la tabla de la seccion
     * de aportaciones
     */
    String getToExecHeader();

    /**
     *
     * @return visibilityMsj
     */
    String getVisibilityMsj();

    /**
     * metodo que settea el valor seleccionado en la fecha final
     * @param event
     */
    void handleDateSelectEndDate(DateSelectEvent event);

    /**
     * metodo que settea el valor seleccionado en la fecha de inicio
     * @param event
     */
    void handleDateSelectStartDate(DateSelectEvent event);

    /**
     * metodo encargado de inicializar la pantalla de programa fisico financiero
     */
    void init();

    /**
     *
     * @return disableAbr
     */
    boolean isDisableAbr();

    /**
     *
     * @return disableAgo
     */
    boolean isDisableAgo();

    /**
     *
     * @return disableDic
     */
    boolean isDisableDic();

    /**
     *
     * @return disableEne
     */
    boolean isDisableEne();

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
     *
     * @return enabledCalendars
     */
    boolean isEnabledCalendars();

    /**
     *
     * @return enabledCurrent
     */
    boolean isEnabledCurrent();

    /**
     *
     * @return enabledToExecuteNextYears
     */
    boolean isEnabledToExecuteNextYears();

    /**
     * Metodo encargado de hacer el salvado de la información
     */
    void saveFinancialAdv(boolean btn);

    /**
     * Metodo encargado de consultar la informacion
     */
    void searchData();

    /**
     * metodo para validar los campos de apotaciones segun los radioButtons de
     * nueva o en proceso y anual o multianual
     */
    void selectOption();


    /**
     *
     * @param disableAbr
     */
    void setDisableAbr(boolean disableAbr);

    /**
     *
     * @param disableAgo
     */
    void setDisableAgo(boolean disableAgo);

    /**
     *
     * @param disableDic
     */
    void setDisableDic(boolean disableDic);

    /**
     *
     * @param disableEne
     */
    void setDisableEne(boolean disableEne);

    /**
     *
     * @param disableFeb
     */
    void setDisableFeb(boolean disableFeb);

    /**
     *
     * @param disableJul
     */
    void setDisableJul(boolean disableJul);

    /**
     *
     * @param disableJun
     */
    void setDisableJun(boolean disableJun);

    /**
     *
     * @param disableMar
     */
    void setDisableMar(boolean disableMar);

    /**
     *
     * @param disableMay
     */
    void setDisableMay(boolean disableMay);

    /**
     *
     * @param disableNov
     */
    void setDisableNov(boolean disableNov);

    /**
     *
     * @param disableOct
     */
    void setDisableOct(boolean disableOct);

    /**
     *
     * @param disableSep
     */
    void setDisableSep(boolean disableSep);

    /**
     *
     * @param duracion
     */
    void setDuracion(int duracion);

    /**
     *
     * @param duracionTime
     */
    void setDuracionTime(String duracionTime);

    /**
     *
     * @param enabledCalendars
     */
    void setEnabledCalendars(boolean enabledCalendars);

    /**
     *
     * @param financialCurrent
     */
    void setFinancialCurrent(String financialCurrent);

    /**
     *
     * @param financialToExcuteNextYears
     */
    void setFinancialToExcuteNextYears(String financialToExcuteNextYears);

    /**
     *
     * @param financialToExecute
     */
    void setFinancialToExecute(String financialToExecute);

    /**
     *
     * @param financialTotal
     */
    void setFinancialTotal(String financialTotal);

    /**
     *
     * @param invPreFileEntity
     */
    void setInvPreFileEntity(InvPreFileEntity invPreFileEntity);

    /**
     *
     * @param theirPreFileId
     */
    void setInvPreFileId(Long theirPreFileId);

    /**
     *
     * @param physicalAdvan
     */
    void setPhysicalAdvan(Map<String, String> physicalAdvan);

    /**
     *
     * @param physicalCurrent
     */
    void setPhysicalCurrent(String physicalCurrent);

    /**
     *
     * @param aDraftEndDate
     */
    void setPhysicalFinancialEndDate(Date aDraftEndDate);

    /**
     *
     * @param aDraftStartDate
     */
    void setPhysicalFinancialStartDate(Date aDraftStartDate);

    /**
     *
     * @param physicalToExcuteNextYears
     */
    void setPhysicalToExcuteNextYears(String physicalToExcuteNextYears);

    /**
     *
     * @param physicalToExecute
     */
    void setPhysicalToExecute(String physicalToExecute);

    /**
     *
     * @param physicalTotal
     */
    void setPhysicalTotal(String physicalTotal);

    /**
     *
     * @param processType
     */
    void setProcessType(int processType);

    /**
     *
     * @param visibilityMsj
     */
    void setVisibilityMsj(String visibilityMsj);
    
    /**
     * metodo encargado de validar los campos obligatorios para guardar la inf.
     * @return
     */
    boolean validateInputs();
    
}
