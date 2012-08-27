/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  UbicationPreFileControllerApi
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
public interface UbicationPreFileControllerApi {

    void calculateTotal();

    void changeListenerCboUbication();

    void changeListenerLstUbication();

    void cleanFromAnteProj();

    void cleanFromPreFicha();

    void clearBottomTable();

    void enableDisableBtnSave();

    /**
     * @return the BtnSaveVisibility
     */
    boolean getBtnSaveVisibility();

    /**
     * @return the itsIdDraftProject
     */
    Long getItsIdDraftProject();

    /**
     * @return the itsItemsAmbitoCbo
     */
    List<RegionalLevelClassifierEntity> getItsItemsAmbitoCbo();

    /**
     * @return the itsMuniTotal
     */
    String getItsMuniTotal();

    String getItsRepInePorc();

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
    Long getItsSelectedUbication();

    /**
     * @return the itsSelectedUbications
     */
    List<String> getItsSelectedUbications();

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
     * @return the lblSelected
     */
    String getLblSelected();

    /**
     * @return the prefileId
     */
    Long getTheirInvPreFileId();

    /**
     * @return the tilteAmbito
     */
    String getTilteAmbito();

    /**
     * @return the titleAmbSelected
     */
    String getTitleAmbSelected();

    /**
     * @return the ubicationPeople
     */
    List<UbicationPeopleHelper> getUbicationPeople();

    void initAmbiCbo();

    void initUbication();

    boolean getBtnSrvSave();

    /**
     * @return the visibleList
     */
    boolean isVisibleList();

    void selectedListenerCboUbication();

    /**
     * @param BtnSaveVisibility
     */
    void setBtnSaveVisibility(boolean btnVisibility);

    void setBtnSrvSave(boolean btnSrvSave);

    /**
     * @param itsIdDraftProject the itsIdDraftProject to set
     */
    void setItsIdDraftProject(Long itsIdDraftProject);

    /**
     * @param itsItemsAmbitoCbo the itsItemsAmbitoCbo to set
     */
    void setItsItemsAmbitoCbo(List<RegionalLevelClassifierEntity> itsItemsAmbitoCbo);

    /**
     * @param itsMuniTotal the itsMuniTotal to set
     */
    void setItsMuniTotal(String itsMuniTotal);

    void setItsRepInePorc(String itsRepInePorc);

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
    void setItsSelectedUbication(Long itsSelectedUbication);

    /**
     * @param itsSelectedUbications the itsSelectedUbications to set
     */
    void setItsSelectedUbications(List<String> itsSelectedUbications);

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
     * @param lblSelected the lblSelected to set
     */
    void setLblSelected(String lblSelected);

    /**
     * @param theirInvPreFileId the preFileId to set
     */
    void setTheirInvPreFileId(Long theirInvPreFileId);

    /**
     * @param tilteAmbito the tilteAmbito to set
     */
    void setTilteAmbito(String tilteAmbito);

    /**
     * @param titleAmbSelected the titleAmbSelected to set
     */
    void setTitleAmbSelected(Long id);

    /**
     * @param ubicationPeople the ubicationPeople to set
     */
    void setUbicationPeople(List<UbicationPeopleHelper> ubicationPeople);

    /**
     * @param visibleList the visibleList to set
     */
    void setVisibleList(boolean visibleList);
    
}
