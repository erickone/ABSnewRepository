/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierLevelDao
 *  Purpose:  This file contains the queries related to functional classifier 
 *            level.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface FunctionalClassifierLevelDao extends SIIFBaseDao<FunctionalLevelClassifier, Long>
{
    FunctionalLevelClassifier getFuncClassifierLevelByLevel(int aFCLevel);
    
    List<FunctionalLevelClassifier> getAllFunClassifierLevels();
    
    Long saveOrUpdate(FunctionalLevelClassifier anEntity);
    
    int getLastObjectiveLevel();

    FunctionalLevelClassifier getFuncClassifLevelRelationshipPlanEst();
    
    void delete(List<FunctionalLevelClassifier> anIdentities);
    
    boolean isFuncClassifKeyValid(String aKey);
}
