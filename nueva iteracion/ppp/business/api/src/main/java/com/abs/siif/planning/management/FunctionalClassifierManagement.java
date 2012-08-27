/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierManagement
 *  Purpose:  This is the management for Functional Classifier.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.management;

import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface FunctionalClassifierManagement
{
    List<FunctionalClassifierEntity> getAllFuncClassifiers();
    
    FunctionalClassifierEntity getFunctionalClassifierById(Long aFuncClassifierId);
    
    Long Save(FunctionalClassifierEntity anFCData,SaveType aSaveType);
    
    void deleteFunctionalClassifier(Long aFuncClassifierId);
    
    FunctionalClassifierEntity getFuncClassifierByIdWithObjs(Long anIdentity);
    
    Long getNextFuncClassifierKey(String IdFC);
    
    /**
     * Erick Leija
     * @param aObjectiveId
     * @return 
     */
    Collection<FunctionalClassifierEntity> getMyFunctionalClassifierAncestry(Long aObjectiveId);
}
