/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DepToObjLinkManagementImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.programming.management;

import com.abs.siif.base.management.ManagementBase;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.programming.dao.DepToObjLinkDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("depToObjLinkManagement")
public class DepToObjLinkManagementImpl extends ManagementBase 
    implements DepToObjLinkManagement, Serializable{

    @Resource(name = "depToObjLinkDao")
    private transient DepToObjLinkDao itsDepToObjLinkDao;
    
    @Override
    public boolean saveGeneralDataInvPreFile(DependenceEntity aDependenceEntity)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DepencenceDto> getFathersList()
    {
        List<DepencenceDto> myFatherList = new ArrayList<DepencenceDto>();
        DepencenceDto myDto = null;
        List<DependenceEntity> myDepEntityList;
        myDepEntityList = itsDepToObjLinkDao.getFathersList();
            for (DependenceEntity dep : myDepEntityList){
                myDto = new DepencenceDto();
                myDto.setClave(dep.getDependenceKey());
                myDto.setIdDependency(dep.getDependenceId());
                myDto.setNameDepend(dep.getDependenceDescription());
                myFatherList.add(myDto);
            }
        return myFatherList;
    }

    @Override
    public List<DependenceEntity> getChildsList(Long idFather)
    {
        List<DependenceEntity> myChildDepEntityList;
        myChildDepEntityList = itsDepToObjLinkDao.getChildsList(idFather);
        return myChildDepEntityList;
    }

    @Override
    public List<DependenceEntity> getChildsRelatedObj(Long idDepFather, Long idObjective)
    {
       List<DependenceEntity> myChildDepEntityObjList;
       myChildDepEntityObjList = itsDepToObjLinkDao.getChildsRelatedObjList( idDepFather, idObjective);
       return myChildDepEntityObjList;
    }

    @Override
    public boolean saveObjectiveWithDependencies(ObjectiveEntity anObjectiveEntity, Long idpadre)
    {
        return itsDepToObjLinkDao.saveDepToObjRelation(anObjectiveEntity, idpadre);
    }


}
