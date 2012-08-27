/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependencyValidatorImpl
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

import com.abs.siif.common.entities.StatusColectiveEntity;
import com.abs.siif.planning.dao.DependencyLevelDao;
import com.abs.siif.planning.entities.DependenceEntity;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Component("dependencyValidator")
public class DependencyValidatorImpl implements DependencyValidator {

    @Resource(name = "dependecyLevelDao")
    private DependencyLevelDao itsDependencyLevelDao;

    @Override
    public boolean notEmptyAnyAttributes(DependenceEntity anEntity) {
        boolean isAnyEmpty = false;

        String myKey = anEntity.getDependenceKey().trim();
        String myName = anEntity.getDependenceDescription().trim();
        String myShortName = anEntity.getDependencyShortName().trim();
        
        StatusColectiveEntity myStatusColective = anEntity.getColective()
                .getStatusColective();
        isAnyEmpty = ((myKey.equals(""))
                || (myName.equals(""))
                || (myShortName.equals(""))
              
                || (myStatusColective==null));

        return isAnyEmpty;
    }

    @Override
    public boolean notExceedLastLevel(DependenceEntity anEntity) {

        int myLastLevel = itsDependencyLevelDao.getMaxLevel(anEntity.
                getBudgetKeyDefinitionDependency());
        int myLevel = anEntity.getDependenceLevel().getDependenceLevel();
        return (myLevel > myLastLevel);
    }
}
