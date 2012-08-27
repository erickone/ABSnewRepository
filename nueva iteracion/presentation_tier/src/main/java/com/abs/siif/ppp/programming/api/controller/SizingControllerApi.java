/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  SizingControllerApi
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

import com.abs.siif.ppp.programming.uihelpers.SizingDataModel;
import com.abs.siif.programming.dto.SizingDto;
import com.abs.siif.programming.entities.SizingEntity;
import com.abs.siif.programming.management.SizingManagement;
import java.util.List;

/**
 *
 * @author luis.carreon
 */
public interface SizingControllerApi {

    void calculatePercent();

    /**
     * Cancelar alguna accion
     */
    void cancelSelection();

    void deleteSizing();

    int findMaxPercent(List<SizingDto> mylistTofind);

    int getCount();

    List<SizingEntity> getItsListOfSizingEntities();

    List<SizingDto> getItsListOfSizingEntitiesDto();

    SizingDataModel getItsListSizingmodel();

    Long getItsPrefileId();

    SizingDto getItsSelectedRow();

    String getItsSizingAmount();

    String getItsSizingDesc();

    String getItsSizingIniAsig();

    String getItsSizingIniPetition();

    String getItsSizingPercentage();

    String getItsSizingSum();

    String getItsSizingTotalAmount();

    String getItsSizingTotalIniAsig();

    String getItsSizingTotalIniPetition();

    String getItsSizingTotalPercentage();

    String getItsSizingTotalSum();

    int getItsSummatoryOfAditional();

    int getItsSummatoryOfAditionals();

    int getItsSummatoryOfAsignations();

    SizingManagement getTheirSizingManagement();

    void initSizing();

    void saveSizingEntity();

    /**
     * Seleccion alguna fila de la tabla se carga para editar o ser eliminada
     */
    void selectedRow();

    void setCount(int count);

    void setItsListOfSizingEntities(List<SizingEntity> itsListOfSizingEntities);

    void setItsListOfSizingEntitiesDto(List<SizingDto> itsListOfSizingEntitiesDto);

    void setItsListSizingmodel(SizingDataModel itsListSizingmodel);

    void setItsPrefileId(Long itsPrefileId);

    void setItsSelectedRow(SizingDto itsSelectedRow);

    void setItsSizingAmount(String itsSizingAmount);

    void setItsSizingDesc(String itsSizingDesc);

    void setItsSizingIniAsig(String itsSizingIniAsig);

    void setItsSizingIniPetition(String itsSizingIniPetition);

    void setItsSizingPercentage(String itsSizingPercentage);

    void setItsSizingSum(String itsSizingSum);

    void setItsSizingTotalAmount(String itsSizingTotalAmount);

    void setItsSizingTotalIniAsig(String itsSizingTotalIniAsig);

    void setItsSizingTotalIniPetition(String itsSizingTotalIniPetition);

    void setItsSizingTotalPercentage(String itsSizingTotalPercentage);

    void setItsSizingTotalSum(String itsSizingTotalSum);

    void setItsSummatoryOfAditional(int itsSummatoryOfAditional);

    void setItsSummatoryOfAditionals(int itsSummatoryOfAditionals);

    void setItsSummatoryOfAsignations(int itsSummatoryOfAsignations);

    void setTheirSizingManagement(SizingManagement theirSizingManagement);
    
    void setNumber(int number);
    
}
