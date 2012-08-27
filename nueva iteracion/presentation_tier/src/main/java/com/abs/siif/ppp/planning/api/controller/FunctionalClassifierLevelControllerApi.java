/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierLevelControllerApi
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

import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import com.abs.siif.ppp.planning.controller.OptionsController;
import com.abs.siif.ppp.planning.uihelpers.FunctionalClassifierLevelDataModel;
import java.util.List;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author FENIX-02
 */
public interface FunctionalClassifierLevelControllerApi
{
    public void init();
    
    public void populateFuncClassifLevels();
    
    public void onRowSelect(SelectEvent event);
    
    public void persistFuncClassifLevel();
    
    public void prepareNewFuncClassifLevel();
    
    public void prepareEditFuncClassifLevel(FunctionalLevelClassifier anEntity);
    
    public void clearData();
    
    public void cancelPersistFuncClassifLevel();
    
    public void deleteFuncClassifier();
    
    public String getSelectedRowsToDelete();
    
    public void setSelectedRowsToDelete(String selectedRowsToDelete);
    
    public FunctionalClassifierLevelDataModel getItsFCLDataModel();

    public void setItsFCLDataModel(FunctionalClassifierLevelDataModel itsFCLDataModel);

    public FunctionalLevelClassifier[] getItsSelectedFCLevels();

    public void setItsSelectedFCLevels(FunctionalLevelClassifier[] itsSelectedFCLevels);

    public FunctionalLevelClassifier getItsCurrentFCLevel();

    public void setItsCurrentFCLevel(FunctionalLevelClassifier itsCurrentFCLevel);

    public boolean isIsAddDisabled();

    public void setIsAddDisabled(boolean isAddDisabled);

    public boolean isItsEditInputsDisabled();

    public void setItsEditInputsDisabled(boolean itsEditInputsDisabled);

    public boolean isItsInputsDisabled();

    public void setItsInputsDisabled(boolean itsInputsDisabled);
}
