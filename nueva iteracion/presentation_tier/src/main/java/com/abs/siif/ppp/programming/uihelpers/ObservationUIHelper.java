/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ObservationUIHelper
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.programming.controller.ObservationController;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.entities.ObservationEntity;
import java.io.Serializable;
import javax.faces.application.FacesMessage;

/**
 *
 * @author FENIX-02
 */
public class ObservationUIHelper extends SIIFControllerBase implements Serializable{
    
    public static void validateData(ObservationController aController)   {
        if (aController.getItsCurrentObservation().getComentario().isEmpty()) {
            throw new BaseBusinessException("ppp.progr.Observation.NoComment");
        }
        if (aController.getItsInvPreFileId() == null) {
            throw new BaseBusinessException("ppp.progr.Observation.NoInvPreFile");
        }
    }
    
    public static ObservationEntity mapperEntityToSave(ObservationController aController){
        
        ObservationEntity myEntity = new ObservationEntity();
        myEntity.setCommentary(aController.getItsCurrentObservation().getComentario());
        myEntity.setUserEntity(aController.getItsUser());
        myEntity.setObservationProfile(aController.getItsProfile());
        myEntity.setDateComment(aController.getItsCommentDate());
        
        InvPreFileEntity myIPF = new InvPreFileEntity();
        myIPF.setInvPreFileId(aController.getItsInvPreFileId());
        myEntity.setObservationInvPreFile(myIPF);
        
        DependenceEntity myDep = new DependenceEntity();
        myDep.setDependenceId(aController.getItsDependence().getIdDependency());
        myEntity.setObservationDependence(myDep);
        
        return myEntity;
    }
    
}
