/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  UbicationControllerApi
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

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.ppp.programming.uihelpers.UbicationPeopleHelper;
import java.util.List;

/**
 *
 * @author luis.carreon
 */
public interface UbicationControllerApi {

    void calculateTotal();

    void changeListenerCboUbication();

    void changeListenerLstUbication();

    /**
     * @return the BtnSaveVisibility
     */
    boolean getBtnSaveVisibility();

    /**
     * @return the idDraftProject
     */
    Long getIdDraftProject();

    /**
     * @return the itsItemsAmbitoCbo
     */
    List<RegionalLevelClassifierEntity> getItsItemsAmbitoCbo();

    /**
     * @return the itsMuniTotal
     */
    String getItsMuniTotal();

    /**
     * @return the itsRepPorc
     */
    String getItsRepPorc();

    /**
     * @return the itsSelectedAmbto
     */
    Long getItsSelectedAmbto();

    /**
     * @return the itsSelectedUbication
     */
    String getItsSelectedUbication();

    /**
     * @return the itsSelectedUbications
     */
    List<Long> getItsSelectedUbications();

    /**
     * @return the itsTotalMen
     */
    String getItsTotalMen();

    /**
     * @return the itsTotalWomen
     */
    String getItsTotalWomen();

    /**
     * @return the itsTotalpeople
     */
    String getItsTotalpeople();

    /**
     * @return the itsUbications
     */
    List<RegionalClassifierEntity> getItsUbications();

    /**
     * @return the tilteAmbito
     */
    String getTilteAmbito();

    /**
     * @return the ubicationPeople
     */
    List<UbicationPeopleHelper> getUbicationPeople();

    void initUbication();

    /**
     * @return the visibleList
     */
    boolean isVisibleList();

    /**
     * Method that
     */
    void saveUbications();

    /**
     * @param BtnSaveVisibility
     */
    void setBtnSaveVisibility(boolean btnVisibility);

    /**
     * @param idDraftProject the idDraftProject to set
     */
    void setIdDraftProject(Long idDraftProject);

    /**
     * @param itsItemsAmbitoCbo the itsItemsAmbitoCbo to set
     */
    void setItsItemsAmbitoCbo(List<RegionalLevelClassifierEntity> itsItemsAmbitoCbo);

    /**
     * @param itsMuniTotal the itsMuniTotal to set
     */
    void setItsMuniTotal(String itsMuniTotal);

    /**
     * @param itsRepPorc the itsRepPorc to set
     */
    void setItsRepPorc(String itsRepPorc);

    /**
     * @param itsSelectedAmbto the itsSelectedAmbto to set
     */
    void setItsSelectedAmbto(Long itsSelectedAmbto);

    /**
     * @param itsSelectedUbication the itsSelectedUbication to set
     */
    void setItsSelectedUbication(String itsSelectedUbication);

    /**
     * @param itsSelectedUbications the itsSelectedUbications to set
     */
    void setItsSelectedUbications(List<Long> itsSelectedUbications);

    /**
     * @param itsTotalMen the itsTotalMen to set
     */
    void setItsTotalMen(String itsTotalMen);

    /**
     * @param itsTotalWomen the itsTotalWomen to set
     */
    void setItsTotalWomen(String itsTotalWomen);

    /**
     * @param itsTotalpeople the itsTotalpeople to set
     */
    void setItsTotalpeople(String itsTotalpeople);

    /**
     * @param itsUbications the itsUbications to set
     */
    void setItsUbications(List<RegionalClassifierEntity> itsUbications);

    /**
     * @param tilteAmbito the tilteAmbito to set
     */
    void setTilteAmbito(String tilteAmbito);

    /**
     * @param ubicationPeople the ubicationPeople to set
     */
    void setUbicationPeople(List<UbicationPeopleHelper> ubicationPeople);

    /**
     * @param visibleList the visibleList to set
     */
    void setVisibleList(boolean visibleList);
    
}
