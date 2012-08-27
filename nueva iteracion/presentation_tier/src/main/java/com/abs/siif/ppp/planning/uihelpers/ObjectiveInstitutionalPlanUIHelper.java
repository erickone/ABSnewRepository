/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  ObjectiveInstitutionalPlanUIHelper
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.common.dto.SampleEntityDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Francisco Luna
 */
@Component("objectiveInstitutionalPlanUIHelper")
public class ObjectiveInstitutionalPlanUIHelper {
    
    protected String specificObjectiveDescription;
    private List<SampleEntityDto> specificObjectives = new ArrayList<SampleEntityDto>();
    private List<SampleEntityDto> specificObjectivesToDelete = new ArrayList<SampleEntityDto>();
    private SampleEntityDto selectedSpecificObjective;
    private SampleEntityDto selectedDeleteSpecificObjective;
    private boolean btnAgregarVisibility;
    private boolean btnCancelarVisibility;
    private boolean btnNuevoVisibility;
    private boolean gridEditObjective;

    public List<SampleEntityDto> getSpecificObjectivesToDelete() {
        return specificObjectivesToDelete;
    }

    public void setSpecificObjectivesToDelete(List<SampleEntityDto> specificObjectivesToDelete) {
        this.specificObjectivesToDelete = specificObjectivesToDelete;
    }

    public boolean isGridEditObjective() {
        return gridEditObjective;
    }

    public void setGridEditObjective(boolean gridEditObjective) {
        this.gridEditObjective = gridEditObjective;
    }

    public boolean getBtnAgregarVisibility() {
        return btnAgregarVisibility;
    }

    public void setBtnAgregarVisibility(boolean btnAgregarVisibility) {
        this.btnAgregarVisibility = btnAgregarVisibility;
    }

    public boolean getBtnCancelarVisibility() {
        return btnCancelarVisibility;
    }

    public void setBtnCancelarVisibility(boolean btnCancelarVisibility) {
        this.btnCancelarVisibility = btnCancelarVisibility;
    }

    public boolean getBtnNuevoVisibility() {
        return btnNuevoVisibility;
    }

    public void setBtnNuevoVisibility(boolean btnNuevoVisibility) {
        this.btnNuevoVisibility = btnNuevoVisibility;
    }

    public SampleEntityDto getSelectedDeleteSpecificObjective() {
        return selectedDeleteSpecificObjective;
    }

    public void setSelectedDeleteSpecificObjective(SampleEntityDto selectedDeleteSpecificObjective) {
        this.selectedDeleteSpecificObjective = selectedDeleteSpecificObjective;
    }

    public SampleEntityDto getSelectedSpecificObjective() {
        return selectedSpecificObjective;
    }

    public void setSelectedSpecificObjective(SampleEntityDto selectedSpecificObjective) {
        this.selectedSpecificObjective = selectedSpecificObjective;
    }

    public List<SampleEntityDto> getSpecificObjectives() {
        return specificObjectives;
    }

    public void setSpecificObjectives(List<SampleEntityDto> itsSpecificObjectives) {
        this.specificObjectives = itsSpecificObjectives;
    }
    

    public String getSpecificObjectiveDescription() {
        return specificObjectiveDescription;
    }

    public void setSpecificObjectiveDescription(String specificObjectiveDescription) {
        this.specificObjectiveDescription = specificObjectiveDescription;
    }
}
