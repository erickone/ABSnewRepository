/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  DirectDraftProjectController
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
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.programming.management.DirectCaptureDraftProject;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.primefaces.event.FlowEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Jorge Urrea
 */
@Controller("directDraftProjectController")
@Scope("session")
public class DirectDraftProjectController extends SIIFControllerBase implements Serializable{
    
    private String itsResponsibleUnitId;
    private List<DependenceEntity> itsListOfResponsibleUnit;
    
    @Resource(name="directCaptureDraftProject")
    private DirectCaptureDraftProject directCaptureProject;

    public String getItsResponsibleUnitId() {
        return itsResponsibleUnitId;
    }

    public void setItsResponsibleUnitId(String itsResponsibleUnitId) {
        this.itsResponsibleUnitId = itsResponsibleUnitId;
    }

    public List<DependenceEntity> getItsListOfResponsibleUnit() {
        return itsListOfResponsibleUnit;
    }

    public void setItsListOfResponsibleUnit(List<DependenceEntity> itsListOfResponsibleUnit) {
        this.itsListOfResponsibleUnit = itsListOfResponsibleUnit;
    }
    
    public void init(){
      itsListOfResponsibleUnit = directCaptureProject.getDraftProjectDependencesByUEG();
        
    }
    
    public String onFlowProcess(FlowEvent event) {  
            return event.getNewStep();          
    }  
    
}
