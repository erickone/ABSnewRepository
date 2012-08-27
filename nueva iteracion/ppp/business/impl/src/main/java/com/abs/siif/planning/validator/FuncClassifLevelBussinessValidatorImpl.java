/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FuncClassifLevelBussinessValidatorImpl
 *  Purpose:  This class contains several validations regarding the Functional
 *            Classifier Level.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.planning.validator;

import com.abs.siif.planning.comparators.FuncClassifLevelComparator;
import com.abs.siif.planning.dao.FunctionalClassifierLevelDao;
import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author FENIX-02
 */
@Component("funcClassifLevelBussinessValidator")
public class FuncClassifLevelBussinessValidatorImpl implements FuncClassifLevelBussinessValidator{

    @Resource(name = "FunctionalClassifierLevelDao")
    private FunctionalClassifierLevelDao itsFunctionalClassifierLevelDao;
    
    /**
     * Este metodo valida si el parametro recibido tiene un nivel de valor 
     * continuo al ultimo existente.
     * @param anFCLevel
     * @return 
     */
    @Override
    public boolean validateFuncClassifLevelInSequence(FunctionalLevelClassifier anFCLevel)
    {
        boolean myInSequence = Boolean.TRUE;
        if (anFCLevel.getFunctionalLevelClassifierId() == null) {
            int myLastLevel = itsFunctionalClassifierLevelDao.getLastObjectiveLevel();
            myLastLevel = (int) (myLastLevel + 1);
            myInSequence = (myLastLevel == anFCLevel.getFunctionalLevelClassifier());
        }
        return myInSequence;
    }

    /**
     * Este metodo valida que solo exista un nivel con el Encuadre activado.
     * @param anFCLevel
     * @return 
     */
    @Override
    public boolean existFuncClassifLevelWithRelationshipPlanEst(FunctionalLevelClassifier anFCLevel)
    {
        boolean myExistObjectiveLevel = Boolean.FALSE;
        FunctionalLevelClassifier myFCLevel = null;

        if (anFCLevel.isFunctionalLevelClassifierIsEncPlaneacion()) {
            myFCLevel = itsFunctionalClassifierLevelDao.getFuncClassifLevelRelationshipPlanEst();
            myExistObjectiveLevel = ((myFCLevel != null)
                    && (!myFCLevel.getFunctionalLevelClassifierId().equals(anFCLevel.getFunctionalLevelClassifierId())));
        }

        return myExistObjectiveLevel;
    }

    /**
     * Este metodo valida que el o los elemntos a eliminar sean el o los ultimos
     * basados en el nivel.
     * @param anEntitieslist
     * @return 
     */
    @Override
    public boolean canDeleteFuncClassifLevels(List<FunctionalLevelClassifier> anEntitieslist)
    {
        boolean myCanDelete = Boolean.TRUE;
        int myIndex = anEntitieslist.size() - 1;
        int myBeforeLevel = -1;
        Comparator comKeyObj = new FuncClassifLevelComparator();
        Collections.sort(anEntitieslist, comKeyObj);
        myBeforeLevel = itsFunctionalClassifierLevelDao.getLastObjectiveLevel();

        while ((myCanDelete) && (myIndex >= 0)) {
            myCanDelete = (myBeforeLevel == anEntitieslist.get(myIndex).getFunctionalLevelClassifier());
            myBeforeLevel--;
            myIndex--;
        }

        return myCanDelete;
    }

    /**
     * Este metodo valida si la clave del FCEntity recibido no existe ya en BD.
     * @param anFCLevel
     * @return 
     */
    @Override
    public boolean isFuncClassifKeyValid(FunctionalLevelClassifier anFCLevel)
    {
        boolean isValidKey;
        isValidKey = itsFunctionalClassifierLevelDao.isFuncClassifKeyValid(
                anFCLevel.getFunctionalLevelClassifierKey());
        return isValidKey;
    }

}
