/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependencyLevelManagementImpl
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

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.planning.dao.DependencyLevelDao;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import com.abs.siif.planning.exception.DependencyLevelBussinesException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("dependencyLevelManagement")
public class DependencyLevelManagementImpl implements DependencyLevelManagement {

    @Resource(name = "dependecyLevelDao")
    private DependencyLevelDao itsDependencyLevelDao;

    @Override
    public DependenceLevelEntity getDependencyLevelByLevel(DependenceLevelEntity aDependenceLevelEntity) {
        return itsDependencyLevelDao.getDependencyLevelByLevel(aDependenceLevelEntity);
    }

    @Override
    public int getMaxLevel(BudgetKeyDefinitionEntity aBudgetKeyDefinition) {

        return itsDependencyLevelDao.getMaxLevel(aBudgetKeyDefinition);
    }

    @Override
    public DependenceLevelEntity getDependenceLevelWithColective(DependenceLevelEntity aDependencyLevel) {
        return itsDependencyLevelDao.getDependenceLevelWithColective(aDependencyLevel);
    }

    @Override
    public DependenceLevelEntity getDependenceLevelByLevelByAnnio(short aLevel) {
       int anAnnio=Integer.valueOf(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR)
                 .toString());
        DependenceLevelEntity myLevel = itsDependencyLevelDao.getDependenceLevelByLevelByAnnio(anAnnio, aLevel);

        if (myLevel == null) {
            throw new DependencyLevelBussinesException("ppp.planning.add.register.levelOut");
        }
        return myLevel;
    }
}
