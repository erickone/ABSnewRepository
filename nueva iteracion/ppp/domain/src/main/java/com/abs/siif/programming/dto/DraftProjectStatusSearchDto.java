/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DraftProjectStatusSearchDto
 *  Purpose:  [ Dto para el traslado de los elementos requeridos
 * para la busqueda de los posibles status en la gestion de anteproyecto ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dto;

import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.security.entities.ProfileEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 * Dto para el traslado de los elementos requeridos
 * para la busqueda de los posibles status en la gestion de anteproyecto
 */
public class DraftProjectStatusSearchDto implements Serializable {
    
    private DraftProjectEntity draftProject;
    private ProfileEntity profile;

    public DraftProjectEntity getDraftProject() {
        return draftProject;
    }

    public void setDraftProject(DraftProjectEntity draftProject) {
        this.draftProject = draftProject;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }
    
    
}
