/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalClassifierLevelControllerApi
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

import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.ppp.planning.uihelpers.RegionalClassifierLevelDataModel;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author FENIX-02
 */
public interface RegionalClassifierLevelControllerApi
{
    public void init();
    
    public void populateRegClassifLevels();
    
    public void onRowSelect(SelectEvent event);
    
    public void persistRegClassifLevel();
    
    public void prepareNewRegClassifLevel();
    
    public void prepareEditRegClassifLevel(RegionalLevelClassifierEntity anEntity);
    
    public void clearData();
    
    public void cancelPersistFuncClassifLevel();
    
    public void deleteRegClassifier();

    public String getSelectedRowsToDelete();

    public void setSelectedRowsToDelete(String selectedRowsToDelete);
    
    public boolean isIsAddDisabled();

    public void setIsAddDisabled(boolean isAddDisabled);

    public RegionalLevelClassifierEntity getItsCurrentRCLevel();

    public void setItsCurrentRCLevel(RegionalLevelClassifierEntity itsCurrentRCLevel);

    public boolean isItsEditInputsDisabled();

    public void setItsEditInputsDisabled(boolean itsEditInputsDisabled);

    public boolean isItsInputsDisabled();

    public void setItsInputsDisabled(boolean itsInputsDisabled);

    public RegionalClassifierLevelDataModel getItsRCLDataModel();

    public void setItsRCLDataModel(RegionalClassifierLevelDataModel itsRCLDataModel);

    public RegionalLevelClassifierEntity[] getItsSelectedRCLevels();

    public void setItsSelectedRCLevels(RegionalLevelClassifierEntity[] itsSelectedRCLevels);
}
