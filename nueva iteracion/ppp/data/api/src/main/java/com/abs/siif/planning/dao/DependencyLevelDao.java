/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependencyLevelDao
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

import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface DependencyLevelDao {

    /*
     * Retorna un nivel de dependencia de acuerdo a un nivel en especifico
     */
    DependenceLevelEntity getDependencyLevelByLevel(DependenceLevelEntity aDependenceLevelEntity);

    /*
     * Retorna el ultimo nivel disponible para la estructura de niveles
     */
    int getMaxLevel(BudgetKeyDefinitionEntity aBudgetKeyDefinition);

    DependenceLevelEntity getDependenceLevelWithColective(DependenceLevelEntity 
            aDependencyLevel);
    
    DependenceLevelEntity getDependenceLevelByLevelByAnnio(int anAnnio,short aLevel);
}
