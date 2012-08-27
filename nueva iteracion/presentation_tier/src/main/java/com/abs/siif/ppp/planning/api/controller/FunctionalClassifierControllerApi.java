/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierControllerApi
 *  Purpose:  Api for FunctionalClassfier Controller.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.api.controller;

import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.dto.FunctionalClassifierDto;
import org.primefaces.model.TreeNode;

/**
 *
 * @author FENIX-02
 */
public interface FunctionalClassifierControllerApi
{
    public void init();
    
    public TreeNode getItsSelectedNode();
    
    public void setItsSelectedNode(TreeNode itsSelectedNode);
    
    public TreeNode getRoot();
    
    public void prepareNewFuncClassifier();
    
    public void saveFuncClassifier();
    
    public void prepareEditFuncClassifier();
    
    public void deleteFunctionalClassifier();
    
    public void clearValues();
    
    public String getItsFuncClassfierDefinicion();
    
    public void setItsFuncClassfierDefinicion(String itsFuncClassfierDefinicion);

    public String getItsFuncClassfierDesc();

    public void setItsFuncClassfierDesc(String itsFuncClassfierDesc);

    public String getItsFuncClassfierKey();

    public void setItsFuncClassfierKey(String itsFuncClassfierKey);

    public SaveType getTheirObjectiveSaveType();

    public void setTheirObjectiveSaveType(SaveType theirObjectiveSaveType);

    public FunctionalClassifierDto getItsFather();

    public void setItsFather(FunctionalClassifierDto myFather);

    public String getItsFatherDescription();

    public void setItsFatherDescription(String itsFatherDescription);

    public boolean isIsSaveDisabled();

    public void setIsSaveDisabled(boolean isSaveDisabled);
    
    public String getItsHeader();

    public void setItsHeader(String itsHeader);

    public String getItsDialogHeader();

    public void setItsDialogHeader(String accion);
}
