/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  PEDVinculationManagementImpl
 *  Purpose:  [ esta es la implementacion de las acciones que se realizaran
 *  para esta entidad en especial, aqui se describen todos los pasos que conllevan
 *  cada una de las acciones]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.PEDVinculationDao;
import com.abs.siif.programming.dao.RegionalPlanWithDraftProjectDao;
import com.abs.siif.programming.entities.PEDVinculationEntity;
import com.abs.siif.programming.entities.RegionalPlansOfPEDEntity;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Erick Leija
 */
@Service("pedVinculationManagement")
public class PedVinculationManagementImpl implements PedVinculationManagement
{
    @Resource(name="pedVinculationDao")
    PEDVinculationDao theirPEDVinculationDao;
    @Resource(name="regionalPlanWithDraftProjectDao")
    RegionalPlanWithDraftProjectDao theirRegionalPlanWithDraftProjectDao;
    
    @Override
    public PEDVinculationEntity getPEDVinculationById(Long anDraftProjectId) 
    {
        return theirPEDVinculationDao.getPEDVinculationById(anDraftProjectId);
    }

    @Override
    public Long savePEDVinculation(PEDVinculationEntity aPEDVinculation) 
    {
        return theirPEDVinculationDao.savePEDVinculation(aPEDVinculation);
    }

    @Override
    public Long saveRegionalPlanAndDraftProject(RegionalPlansOfPEDEntity anEntity) 
    {
        Long myPEDId;
        if (anEntity.getPEDregionalPlanId()==null)
        {
            myPEDId = theirRegionalPlanWithDraftProjectDao.saveRegionalPlanAndDraftProject(anEntity);
        }    
        else
        {
            myPEDId = theirRegionalPlanWithDraftProjectDao.persistsaveRegionalPlanAndDraftProject(anEntity);
        }
       return myPEDId;
    }

    @Override
    public List<RegionalPlansOfPEDEntity> getRegionalPlanByDraftProjectId(Long aDraftProjectId) 
    {
        return theirRegionalPlanWithDraftProjectDao.getRegionalPlanByDraftProjectId(aDraftProjectId);
    }
    
    @Override
    public String deleteAllRegionalPlansByDraftProjectIDAndRegionClassifierId(Long aDraftProjectId,Long aRegionalClassifier)
    {
        return theirRegionalPlanWithDraftProjectDao.deleteAllRegionalPlansByDraftProjectIDAndRegionClassifierId(aDraftProjectId, aRegionalClassifier);
    }
    
}
