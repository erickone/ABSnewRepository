/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  RenumerationController
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.management.DependenceManagement;
import com.abs.siif.programming.dto.RenumerationDto;
import com.abs.siif.programming.management.RenumerationManagement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author luis.carreon
 */
@Controller("renumerationController")
@Scope("session")
public class RenumerationController extends SIIFControllerBase
        implements Serializable {

    @Resource(name = "dependenceManagement")
    private transient DependenceManagement dependenceManagement;
    @Resource(name = "renumerationManagement")
    private transient RenumerationManagement renumerationManagement;
    private Long aDependenceId;
    private List<RenumerationDto> myRenumerationDtos = new ArrayList<RenumerationDto>();
    private List<DependenceEntity> itsListOfDependences;

    public void init() {
        itsListOfDependences = dependenceManagement.getViewDependenciesTypeUR();
        this.setADependenceId(0L);
        if (myRenumerationDtos != null) {
            myRenumerationDtos.clear();
        }
    }

    public void loadDraftprojects() {
        myRenumerationDtos = renumerationManagement.getDraftprojectsByUP(aDependenceId);
    }

    public void validateValues() {
        try {
            myRenumerationDtos = renumerationManagement.validateKeysChanged(myRenumerationDtos);

        } catch (BaseBusinessException ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    getMapKeyExcpetion(ex.getMessage()),
                    getMapKeyExcpetion(ex.getMessage()));
        }
    }

    public Long getaDependenceId() {
        return aDependenceId;
    }

    public void setaDependenceId(Long aDependenceId) {
        this.aDependenceId = aDependenceId;
    }

    public List<RenumerationDto> getMyRenumerationDtos() {
        return myRenumerationDtos;
    }

    public void setMyRenumerationDtos(List<RenumerationDto> myRenumerationDtos) {
        this.myRenumerationDtos = myRenumerationDtos;
    }

    public Long getADependenceId() {
        return aDependenceId;
    }

    public void setADependenceId(Long aDependenceId) {
        this.aDependenceId = aDependenceId;
    }

    public List<DependenceEntity> getItsListOfDependences() {
        return itsListOfDependences;
    }

    public void setItsListOfDependences(List<DependenceEntity> itsListOfDependences) {
        this.itsListOfDependences = itsListOfDependences;
    }

    public boolean getIsEnabledRenumeration() {
        for (RenumerationDto renume : myRenumerationDtos) {
            if (renume.getIsValueChanged()) {
                return false;
            }
        }
        return true;
    }

    public String formatDependenceKey(String key) {
        for (int i = key.length(); i < 5; i++) {
            key = "0" + key;
        }
        return key;
    }

    public String returnToOptions() {
        return "options";
    }
}
