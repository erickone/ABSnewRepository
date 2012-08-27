/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierDao
 *  Purpose:  [ short Description  ]
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
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface FunctionalClassifierDao extends SIIFBaseDao<FunctionalClassifierEntity, Long>
{
    FunctionalClassifierEntity getFuncClassifierByIdWithObjs(Long anIdentity);
    
    List<FunctionalClassifierEntity> getAllFuncClassifiers();
    
    Long Save(FunctionalClassifierEntity anFCData,SaveType aSaveType);
    
    void deleteFunctionalClassifier(FunctionalClassifierEntity aFuncClassifier);
    
    Long getNextFuncClassifierKey(String IdFC);
}
