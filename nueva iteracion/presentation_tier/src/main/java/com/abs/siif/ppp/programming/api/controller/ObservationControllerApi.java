/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  ObservationControllerApi
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

import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.ppp.programming.uihelpers.ObservationDataModel;
import com.abs.siif.programming.dto.ObservationDto;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.security.entities.UserEntity;
import java.util.Date;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author luis.carreon
 */
public interface ObservationControllerApi {

    void clearData();

    void deleteObservation();

    ObservationDto getItsCurrentObservation();

    DepencenceDto getItsDependence();

    ObservationDataModel getItsFatherDataModel();

    List<ObservationDto> getItsFatherList();

    Long getItsInvPreFileId();

    ProfileEntity getItsProfile();

    UserEntity getItsUser();

    ObservationDto getSelectedFather();

    void init();

    boolean getItsDeleteDisabled();

    boolean isItsInputsDisabled();

    boolean isItsSaveDisabled();

    void onRowSelect(SelectEvent event);

    void onRowUnselect(UnselectEvent event);

    void prepareNewObservation();

    void saveObservation();

    void setItsCurrentObservation(ObservationDto itsCurrentObservation);

    void setItsDeleteDisabled(boolean itsDeleteDisabled);

    void setItsDependence(DepencenceDto itsDependence);

    void setItsFatherDataModel(ObservationDataModel itsFatherDataModel);

    void setItsFatherList(List<ObservationDto> itsFatherList);

    void setItsInputsDisabled(boolean itsInputsDisabled);

    void setItsInvPreFileId(Long itsInvPreFileId);

    void setItsProfile(ProfileEntity itsProfile);

    void setItsSaveDisabled(boolean itsSaveDisabled);

    void setItsUser(UserEntity itsUser);

    void setSelectedFather(ObservationDto selectedFather);
    
    public Date getItsCommentDate();

    public void setItsCommentDate(Date itsCommentDate);
}
