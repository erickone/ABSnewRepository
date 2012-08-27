/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  ObjectiveLevelControllerImplApi
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.api.controller;

import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import com.abs.siif.ppp.planning.controller.OptionsController;
import com.abs.siif.ppp.planning.uihelpers.ObjectiveLevelDataModel;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author luis.carreon
 */
public interface ObjectiveLevelControllerImplApi {

    void cancelPersistObjectiveLevel();

    void deleteObjectives();

    ObjectiveLevelEntity getItsCurrentObjectiveLevel();

    String getItsObjectiveLevelId();

    ObjectiveLevelDataModel getItsObjectiveLevels();

    ObjectiveLevelEntity[] getItsSelectedObjectives();

    OptionsController getOptionsController();

    boolean isItsAddObjectiveLevelDisabled();

    boolean isItsEditInputsDisabled();

    boolean isItsInputsDisabled();

    boolean isItsInputsDisabledUEG();

    boolean isItsRequeridFields();

    void onRowSelect(SelectEvent event);

    void persistObjectiveLevel();

    void prepareNewObjectiveLevel();

    void setItsAddObjectiveLevelDisabled(boolean itsAddObjectiveLevelDisabled);

    void setItsCurrentObjectiveLevel(ObjectiveLevelEntity itsCurrentObjectiveLevel);

    void setItsEditInputsDisabled(boolean itsEditInputsDisabled);

    void setItsInputsDisabled(boolean itsInputsDisabled);

    void setItsInputsDisabledUEG(boolean itsInputsDisabledUEG);

    void setItsObjectiveLevelId(String itsObjectiveLevelId);

    void setItsRequeridFields(boolean itsRequeridFields);

    void setItsSelectedObjectives(ObjectiveLevelEntity[] itsSelectedObjectives);

    void setOptionsController(OptionsController optionsController);
    
}
