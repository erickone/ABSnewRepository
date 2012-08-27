/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierManagementImpl
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

import com.abs.siif.planning.dao.FunctionalClassifierDao;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import com.abs.siif.planning.exception.FuncClassifLevelBussinessException;
import com.abs.siif.programming.dao.FunctionalClassifierProgrammingDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("functionalClassifierManagement")
public class FunctionalClassifierManagementImpl implements FunctionalClassifierManagement, Serializable{

    @Resource(name = "functionalClassifierDao")
    private FunctionalClassifierDao theirFuncClassifierDao;
    @Resource(name="functionalClassifierProgrammingDao")
    private FunctionalClassifierProgrammingDao theirFunctionalClassifier;
    
    
    @Override
    public List<FunctionalClassifierEntity> getAllFuncClassifiers()
    {
        return theirFuncClassifierDao.getAllFuncClassifiers();
    }

    @Override
    public FunctionalClassifierEntity getFunctionalClassifierById(Long aFuncClassifierId)
    {
        return theirFuncClassifierDao.findById(aFuncClassifierId, true);
    }

    @Override
    public Long Save(FunctionalClassifierEntity anFCData, SaveType aSaveType)
    {
        return theirFuncClassifierDao.Save(anFCData, aSaveType);
    }

    @Override
    public void deleteFunctionalClassifier(Long aFuncClassifierId)
    {
        FunctionalClassifierEntity myFCtoDelete = null;
        myFCtoDelete = getFuncClassifierByIdWithObjs(aFuncClassifierId);
        
        if(myFCtoDelete.getFuntionalClassifierObjectives().isEmpty()) {
            theirFuncClassifierDao.deleteFunctionalClassifier(myFCtoDelete);
        }
        else {
            throw new FuncClassifLevelBussinessException("ppp.planning.deleteUnsuccesful");
        }
    }

    /**
     * Erick Leija
     * @param aObjectiveId
     * @return 
     */
    @Override
    public Collection<FunctionalClassifierEntity> getMyFunctionalClassifierAncestry(Long aObjectiveId)
    {
        List<FunctionalClassifierEntity> myListOfFunctionalClassifierEntity = new ArrayList<FunctionalClassifierEntity> 
               (theirFunctionalClassifier.getFunctionalClassifiersByObjectiveId(aObjectiveId));
       
       return theirFunctionalClassifier.getHierarchicalFunctionalClassifierByEntities(myListOfFunctionalClassifierEntity);
    }

    @Override
    public FunctionalClassifierEntity getFuncClassifierByIdWithObjs(Long anIdentity)
    {
        return theirFuncClassifierDao.getFuncClassifierByIdWithObjs(anIdentity);
    }

    /**
     * Este metodo regresa el numero consecutivo siguiente para un clasificador
     * nuevo.
     * En caso que no haya un idPadre significa que se esta dando de alta un 
     * clasificador de primer nivel.
     * @param idPadre
     * @return 
     */
    @Override
    public Long getNextFuncClassifierKey(String IdFC) {
        return theirFuncClassifierDao.getNextFuncClassifierKey(IdFC);
    }

}
