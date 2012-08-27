/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependenceLevelManagementImpl
 *  Purpose:  [ short Description ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.management;

import com.abs.siif.common.dao.ColectiveTypeDao;
import com.abs.siif.common.entities.ColectiveTypeEntity;
import com.abs.siif.planning.dao.DependenceLevelDao;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Este es el management para los niveles de las dependencias
 * aqui se realizan las modificaciones de las configuraciones
 * de los niveles de la dependencias
 * @author Erick Leija
 */
@Service("dependenceLevelManagement")
public class DependenceLevelManagementImpl implements DependenceLevelManagement
{
    
    @Resource(name = "dependenceLevelDao")
    private DependenceLevelDao theirDependenceLevelDao;
    
    @Resource(name = "colectiveTypeDao")
    private ColectiveTypeDao theirColectiveTypeDao;
    
    @Override
    public Map<String, List<?>> getAllDependenceLevelsAndSupportListByYear(int aYear) 
    {
        List<DependenceLevelEntity> myList = 
                theirDependenceLevelDao.getAllDependenceLevelsByYear(aYear);   
        List<ColectiveTypeEntity> myColectiveList = 
                new ArrayList<ColectiveTypeEntity>(theirColectiveTypeDao.getColectiveTypes());
        Map<String, List<?>> myMap = new HashMap<String, List<?>> ();
        myMap.put("Levels",myList);
        myMap.put("ColectiveTypes", myColectiveList);
        return myMap;
    }

    @Override
    public Long saveDependenceLevel(DependenceLevelEntity aDependenceEntity)
    {
        Long myIdToReturn;
        if (aDependenceEntity.getDependenceLevelId()==null)
        {
            myIdToReturn = 
                    ((DependenceLevelEntity)theirDependenceLevelDao.save(aDependenceEntity)).
                    getDependenceLevelId();
        }
        else
        {
            myIdToReturn = 
                    ((DependenceLevelEntity)theirDependenceLevelDao.persist(aDependenceEntity)).
                    getDependenceLevelId();
        }
        return myIdToReturn;
    }

    @Override
    public void deleteDependenceLevel(DependenceLevelEntity aDependenceEntity)
    {
        theirDependenceLevelDao.delete(aDependenceEntity);
    }
    
    
}
