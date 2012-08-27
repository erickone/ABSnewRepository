/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ObservationManagement
 *  Purpose:  Controller for the Observations catalog related to InvPreFile.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.ppp.programming.api.controller.ObservationControllerApi;
import com.abs.siif.ppp.programming.uihelpers.ObservationDataModel;
import com.abs.siif.ppp.programming.uihelpers.ObservationUIHelper;
import com.abs.siif.programming.dto.ObservationDto;
import com.abs.siif.programming.entities.ObservationEntity;
import com.abs.siif.programming.management.ObservationManagement;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.security.entities.UserEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FENIX-02
 */
@Controller("observationController")
@Scope("session")
public class ObservationController extends SIIFControllerBase
        implements Serializable, ObservationControllerApi{
    
    @Resource(name = "observationManagement")
    private ObservationManagement itsObservationManagement;
    
    private Long itsInvPreFileId;
    private UserEntity itsUser;
    private ProfileEntity itsProfile;
    private DepencenceDto itsDependence;
    private Date itsCommentDate;
    private ObservationDto itsCurrentObservation;
    private boolean itsInputsDisabled = true;
    private boolean itsSaveDisabled = true;
    private boolean itsDeleteDisabled = true;
    
    //Estas variables son para poblar y guardar valores del DataTable.
    private ObservationDataModel itsFatherDataModel;
    private List<ObservationDto> itsFatherList;  
    private ObservationDto selectedFather;
    
    
    @Override
    public void init(){
        if (itsInvPreFileId != null) {
            itsFatherList = itsObservationManagement.getObservationsByInvPreFileId(itsInvPreFileId);
        }
        else {
            itsFatherList = new ArrayList<ObservationDto>();
            FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_INFO;
            String myMessage = "";
            try {
                throw new BaseBusinessException("ppp.progr.Observation.NoInvPreFile");
            } catch (BaseBusinessException ex) {
                myMessage = getMapKeyExcpetion(ex.getMessage());
            } finally {
                addMessageCurrentInstance(mySeverity, myMessage, null);
            }
        }
        itsCommentDate = new Date();
        itsCurrentObservation = new ObservationDto();
        selectedFather = new ObservationDto();
        itsFatherDataModel = new ObservationDataModel(itsFatherList);
        itsUser = (UserEntity) SIIFContextBase.getParameterSession(SessionKeyEnum.USER);
        itsProfile = (ProfileEntity) SIIFContextBase.getParameterSession(SessionKeyEnum.ROLE);
        itsDependence = itsObservationManagement.getDependenceByUserId(itsUser.getUserId());
        clearData();
    }

    @Override
    public void onRowSelect(SelectEvent event) {
        //Composed User Name
        itsCurrentObservation.setNombre(selectedFather.getNombre());
        itsCurrentObservation.setApepaterno(selectedFather.getApepaterno());
        itsCurrentObservation.setApematerno(selectedFather.getApematerno());
        //Composed Dependence Name
        itsCurrentObservation.setClave(selectedFather.getClave());
        itsCurrentObservation.setDescripcion(selectedFather.getDescripcion());
        
        itsCurrentObservation.setFechacomentario(selectedFather.getFechacomentario());
        itsCurrentObservation.setUserdesc(selectedFather.getUserdesc());
        itsCurrentObservation.setComentario(selectedFather.getComentario());
        itsCurrentObservation.setIdprefichaobservacion(selectedFather.getIdprefichaobservacion());
        itsInputsDisabled = true;
        itsSaveDisabled = true;
    }  
    
    @Override
    public void saveObservation(){
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_INFO;
        String myMessage = "";
        try {
            ObservationUIHelper.validateData(this);
            ObservationEntity myNewIndentity = 
                    itsObservationManagement.saveObservation
                    (ObservationUIHelper.mapperEntityToSave(this));
            myMessage = this.getMapKeyExcpetion("ppp.planning.succesSave");
            
        } catch (Exception ex) {
            itsCurrentObservation.setIdprefichaobservacion(0);
            myMessage = getMapKeyExcpetion(ex.getMessage());
            mySeverity = FacesMessage.SEVERITY_ERROR;
        } finally {
            addMessageCurrentInstance(mySeverity, myMessage, null);
            itsInputsDisabled = true;
            itsSaveDisabled = true;
            init();
        }
    }
    
    @Override
    public void onRowUnselect(UnselectEvent event) { 
        selectedFather = null;
    } 
    
    @Override
    public void prepareNewObservation() {
        itsInputsDisabled = false;
        itsSaveDisabled = false;
        getItsCommentDate();
        clearData();
    }
    
    @Override
    public void clearData() {
        selectedFather = null;
        //Composed User Name
        itsCurrentObservation.setNombre(itsUser.getUserColective().getColectiveName());
        itsCurrentObservation.setApepaterno(itsUser.getUserColective().getColectiveLastName());
        itsCurrentObservation.setApematerno(itsUser.getUserColective().getColectiveMiddleName());
        //Composed Dependence Name
        itsCurrentObservation.setClave(itsDependence.getClave());
        itsCurrentObservation.setDescripcion(itsDependence.getNameDepend());
        itsCurrentObservation.setFechacomentario(itsCommentDate);
        itsCurrentObservation.setUserdesc(itsProfile.getProfileDescription());
        itsCurrentObservation.setComentario("");
    }
    
    @Override
    public void deleteObservation(){
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_INFO;
        String myMessage = this.getMapKeyExcpetion("ppp.planning.succesDelete");
        try {
            if (selectedFather == null) {

                throw new BaseBusinessException(getMapKeyExcpetion("ppp.progr.Observation.selectRecord"));
            }
            ObservationEntity obsToDelete = new ObservationEntity();
            obsToDelete.setObservationId(selectedFather.getIdprefichaobservacion().longValue());
            itsObservationManagement.deleteObservation(obsToDelete);
            
        }catch (Exception ex) {
            mySeverity = FacesMessage.SEVERITY_ERROR;
            myMessage = getMapKeyExcpetion(ex.getMessage());
        } finally {
            addMessageCurrentInstance(mySeverity, myMessage, null);
            init();
        }
    }
    
    @Override
    public ObservationDataModel getItsFatherDataModel() {
        return itsFatherDataModel;
    }

    @Override
    public void setItsFatherDataModel(ObservationDataModel itsFatherDataModel) {
        this.itsFatherDataModel = itsFatherDataModel;
    }

    @Override
    public List<ObservationDto> getItsFatherList() {
        return itsFatherList;
    }

    @Override
    public void setItsFatherList(List<ObservationDto> itsFatherList) {
        this.itsFatherList = itsFatherList;
    }

    @Override
    public ObservationDto getSelectedFather() {
        return selectedFather;
    }

    @Override
    public void setSelectedFather(ObservationDto selectedFather) {
        this.selectedFather = selectedFather;
    }

    @Override
    public Long getItsInvPreFileId() {
        return itsInvPreFileId;
    }

    @Override
    public void setItsInvPreFileId(Long itsInvPreFileId) {
        this.itsInvPreFileId = itsInvPreFileId;
    }

    @Override
    public UserEntity getItsUser() {
        return itsUser;
    }

    @Override
    public void setItsUser(UserEntity itsUser) {
        this.itsUser = itsUser;
    }

    @Override
    public ObservationDto getItsCurrentObservation() {
        return itsCurrentObservation;
    }

    @Override
    public void setItsCurrentObservation(ObservationDto itsCurrentObservation) {
        this.itsCurrentObservation = itsCurrentObservation;
    }

    @Override
    public boolean isItsInputsDisabled() {
        return itsInputsDisabled;
    }

    @Override
    public void setItsInputsDisabled(boolean itsInputsDisabled) {
        this.itsInputsDisabled = itsInputsDisabled;
    }

    @Override
    public boolean isItsSaveDisabled() {
        return itsSaveDisabled;
    }

    @Override
    public void setItsSaveDisabled(boolean itsSaveDisabled) {
        this.itsSaveDisabled = itsSaveDisabled;
    }

    @Override
    public ProfileEntity getItsProfile() {
        return itsProfile;
    }

    @Override
    public void setItsProfile(ProfileEntity itsProfile) {
        this.itsProfile = itsProfile;
    }

    @Override
    public DepencenceDto getItsDependence() {
        return itsDependence;
    }

    @Override
    public void setItsDependence(DepencenceDto itsDependence) {
        this.itsDependence = itsDependence;
    }

    @Override
    public boolean getItsDeleteDisabled() {
        return itsDeleteDisabled;
    }

    @Override
    public void setItsDeleteDisabled(boolean itsDeleteDisabled) {
        this.itsDeleteDisabled = itsDeleteDisabled;
    }

    @Override
    public Date getItsCommentDate() {
        return itsCommentDate = new Date();
    }

    @Override
    public void setItsCommentDate(Date itsCommentDate) {
        this.itsCommentDate = itsCommentDate;
    }
    
}
