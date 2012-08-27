/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierLevelManagementImpl
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

import com.abs.siif.planning.dao.FunctionalClassifierLevelDao;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import com.abs.siif.planning.exception.FuncClassifLevelBussinessException;
import com.abs.siif.planning.validator.FuncClassifLevelBussinessValidator;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("functionalClassifierLevelManagement")
public class FunctionalClassifierLevelManagementImpl implements FunctionalClassifierLevelManagement, Serializable{

    @Resource(name = "FunctionalClassifierLevelDao")
    private FunctionalClassifierLevelDao itsFunctionalClassifierLevelDao;
    @Resource(name = "funcClassifLevelBussinessValidator")
    private FuncClassifLevelBussinessValidator itsFuncClassifLevelBussinessValidator;
    
    @Override
    public FunctionalLevelClassifier getFuncClassifierLevelByLevel(int aFCLevel)
    {
        return itsFunctionalClassifierLevelDao.getFuncClassifierLevelByLevel(aFCLevel);
    }

    @Override
    public List<FunctionalLevelClassifier> getAllFunClassifierLevels()
    {
        return itsFunctionalClassifierLevelDao.getAllFunClassifierLevels();
    }

    @Override
    public Long saveOrUpdate(FunctionalLevelClassifier anEntity, SaveType aSaveType)
    {
        if (!itsFuncClassifLevelBussinessValidator.validateFuncClassifLevelInSequence(anEntity)) {
            throw new FuncClassifLevelBussinessException("ppp.planning.notSecuency");
        }

        if (itsFuncClassifLevelBussinessValidator.existFuncClassifLevelWithRelationshipPlanEst(anEntity)) {
            throw new FuncClassifLevelBussinessException("ppp.planning.uniquePlanEst");
        }
        
        if (aSaveType == SaveType.SAVE)
            if (!itsFuncClassifLevelBussinessValidator.isFuncClassifKeyValid(anEntity)) {
                throw new FuncClassifLevelBussinessException("ppp.planning.repeatedKey");
            }
        
        Long myKey = itsFunctionalClassifierLevelDao.saveOrUpdate(anEntity);

        return myKey;
    }

    @Override
    public void deleteFunctionalCassifier(List<FunctionalLevelClassifier> anEntityList)
    {
        if (!itsFuncClassifLevelBussinessValidator.canDeleteFuncClassifLevels(anEntityList)) {
            throw new FuncClassifLevelBussinessException("ppp.planning.notSecuencyDelete");
        }

        itsFunctionalClassifierLevelDao.delete(anEntityList);
    }

}
