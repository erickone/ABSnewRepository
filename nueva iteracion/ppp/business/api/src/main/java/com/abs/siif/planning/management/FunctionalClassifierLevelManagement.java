/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierLevelManagement
 *  Purpose:  [ short Description  ]
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
import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface FunctionalClassifierLevelManagement
{
    FunctionalLevelClassifier getFuncClassifierLevelByLevel(int aFCLevel);
    
    List<FunctionalLevelClassifier> getAllFunClassifierLevels();
    
    Long saveOrUpdate(FunctionalLevelClassifier anEntity, SaveType aSaveType);
    
    void deleteFunctionalCassifier(List<FunctionalLevelClassifier> anEntityList);
}
