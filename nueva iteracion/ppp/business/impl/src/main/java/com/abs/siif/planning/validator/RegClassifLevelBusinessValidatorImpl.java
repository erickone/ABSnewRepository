/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegClassifLevelBusinessValidatorImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.planning.validator;

import com.abs.siif.planning.comparators.ReglClassifLevelComparator;
import com.abs.siif.planning.dao.RegionalLevelClassifierDao;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author FENIX-02
 */
@Component("regClassifLevelBusinessValidator")
public class RegClassifLevelBusinessValidatorImpl implements RegClassifLevelBusinessValidator{

    @Resource(name = "regionalLevelClassifierDao")
    private RegionalLevelClassifierDao itsRegionalLevelClassifierDao;
    
    /**
     * Este metodo valida si el parametro recibido tiene un nivel de valor 
     * continuo al ultimo existente.
     * @param anRCLevel
     * @return 
     */
    @Override
    public boolean validateRegClassifLevelInSequence(RegionalLevelClassifierEntity anRCLevel)
    {
        boolean myInSequence = Boolean.TRUE;
        if (anRCLevel.getRegionalLevelClassifierId() == null) {
            int myLastLevel = itsRegionalLevelClassifierDao.getLastRegionalLevel();
            myLastLevel = (int) (myLastLevel + 1);
            myInSequence = (myLastLevel == anRCLevel.getRegionalLevelClassifierLevel());
        }
        return myInSequence;
    }

    @Override
    public boolean existRegClassifLevelWithCensusActive(RegionalLevelClassifierEntity anRCLevel)
    {
        boolean myExistObjectiveLevel = Boolean.FALSE;
        RegionalLevelClassifierEntity myRCLevel = null;

        if (anRCLevel.isRegionalLevelClassifierIsCensus()) {
            myRCLevel = itsRegionalLevelClassifierDao.existRegClassifLevelWithCensusActive(anRCLevel);
            myExistObjectiveLevel = ((myRCLevel != null)
                    && (!myRCLevel.getRegionalLevelClassifierId().equals(anRCLevel.getRegionalLevelClassifierId())));
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
    public boolean canDeleteRegClassifLevels(List<RegionalLevelClassifierEntity> anEntitieslist)
    {
        boolean myCanDelete = Boolean.TRUE;
        int myIndex = anEntitieslist.size() - 1;
        int myBeforeLevel = -1;
        Comparator comKeyObj = new ReglClassifLevelComparator();
        Collections.sort(anEntitieslist, comKeyObj);
        myBeforeLevel = itsRegionalLevelClassifierDao.getLastRegionalLevel();

        while ((myCanDelete) && (myIndex >= 0)) {
            myCanDelete = (myBeforeLevel == anEntitieslist.get(myIndex).getRegionalLevelClassifierLevel());
            myBeforeLevel--;
            myIndex--;
        }

        return myCanDelete;
    }

    /**
     * Este metodo valida si la clave del RCEntity recibido no existe ya en BD.
     * @param anRCLevel
     * @return 
     */
    @Override
    public boolean isRegClassifKeyValid(RegionalLevelClassifierEntity anRCLevel)
    {
        boolean isValidKey;
        isValidKey = itsRegionalLevelClassifierDao.isRegClassifKeyValid(
                anRCLevel.getRegionalLevelClassifierKey());
        return isValidKey;
    }

    @Override
    public boolean existRegClassifLevelWithGenderActive(RegionalLevelClassifierEntity anRCLevel)
    {
        boolean myExistObjectiveLevel = Boolean.FALSE;
        RegionalLevelClassifierEntity myRCLevel = null;

        if (anRCLevel.isRegionalLevelClassifierIsGender()) {
            myRCLevel = itsRegionalLevelClassifierDao.existRegClassifLevelWithGenderActive(anRCLevel);
            myExistObjectiveLevel = ((myRCLevel != null)
                    && (!myRCLevel.getRegionalLevelClassifierId().equals(anRCLevel.getRegionalLevelClassifierId())));
        }

        return myExistObjectiveLevel;
    }

    @Override
    public boolean existRegClassifLevelWithStateActive(RegionalLevelClassifierEntity anRCLevel)
    {
        boolean myExistObjectiveLevel = Boolean.FALSE;
        RegionalLevelClassifierEntity myRCLevel = null;

        if (anRCLevel.isRegionalLevelClassifierIsState()) {
            myRCLevel = itsRegionalLevelClassifierDao.existRegClassifLevelWithStateActive(anRCLevel);
            myExistObjectiveLevel = ((myRCLevel != null)
                    && (!myRCLevel.getRegionalLevelClassifierId().equals(anRCLevel.getRegionalLevelClassifierId())));
        }

        return myExistObjectiveLevel;
    }

    @Override
    public boolean existRegClassifLevelWithMunicipalityActive(RegionalLevelClassifierEntity anRCLevel)
    {
        boolean myExistObjectiveLevel = Boolean.FALSE;
        RegionalLevelClassifierEntity myRCLevel = null;

        if (anRCLevel.isRegionalLevelClassifierIsMunicipality()) {
            myRCLevel = itsRegionalLevelClassifierDao.existRegClassifLevelWithMunicipalityActive(anRCLevel);
            myExistObjectiveLevel = ((myRCLevel != null)
                    && (!myRCLevel.getRegionalLevelClassifierId().equals(anRCLevel.getRegionalLevelClassifierId())));
        }

        return myExistObjectiveLevel;
    }

    @Override
    public boolean existRegClassifLevelWithPostalCodeActive(RegionalLevelClassifierEntity anRCLevel)
    {
        boolean myExistObjectiveLevel = Boolean.FALSE;
        RegionalLevelClassifierEntity myRCLevel = null;

        if (anRCLevel.isRegionalLevelClassifierIsPostalCode()) {
            myRCLevel = itsRegionalLevelClassifierDao.existRegClassifLevelWithPostalCodeActive(anRCLevel);
            myExistObjectiveLevel = ((myRCLevel != null)
                    && (!myRCLevel.getRegionalLevelClassifierId().equals(anRCLevel.getRegionalLevelClassifierId())));
        }

        return myExistObjectiveLevel;
    }

}
