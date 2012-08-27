/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingConfigurationManagementImpl
 *  Purpose:  Operations related to Ceiling Budget Configuration.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.management;

import com.abs.siif.budget.dao.CeilingConfigurationDao;
import com.abs.siif.budget.dto.CeilingConfigurationDto;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.budget.exception.CeilingConfigurationBusinessException;
import com.abs.siif.budget.validator.CeilingConfigValidator;
import com.abs.siif.planning.data.SaveType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("ceilingConfigurationManagement")
public class CeilingConfigurationManagementImpl implements CeilingConfigurationManagement, Serializable{

    @Resource(name = "ceilingConfigurationDao")
    private CeilingConfigurationDao itsCeilingConfigurationDao;
    @Resource(name = "ceilingConfigValidator")
    private CeilingConfigValidator itsCeilingConfigValidator;
    
    @Override
    public List<CeilingConfigurationDto> getCeilingConfigurations()
    {
        List<CeilingConfigurationDto> myCeilingList = new ArrayList<CeilingConfigurationDto>();
        CeilingConfigurationDto myDto = null;
        List<CeilingConfigurationEntity> myCeilingEntityList;
        myCeilingEntityList = itsCeilingConfigurationDao.getAllCeilingConfigurations();
            for (CeilingConfigurationEntity ceiling : myCeilingEntityList){
                myDto = new CeilingConfigurationDto();
                myDto.setCeilingConfigId(ceiling.getCeilingConfigId());
                myDto.setCeilingConfigName(ceiling.getCeilingConfigName());
                myDto.setIsCeilingConfigActive(ceiling.isCeilingConfigActive());
                myDto.setCeilingConfigDesc(ceiling.getCeilingConfigDescription());
                myDto.setBudgetKeyItems(ceiling.getBudgetKeyItems());
                myDto.setBudgetKeyDefinition(ceiling.getCeilingConfigBudgetKey());
                //myDto.setCompositeCeilingConfigDesc(ceiling.getBudgetKeyItems());
                myCeilingList.add(myDto);
            }
        return myCeilingList;
    }

    @Override
    public Long saveCeilingConfigWithBudgetKeyItems(CeilingConfigurationEntity aCeilingConfigurationEntity, SaveType aSaveType)
    {
        if (aSaveType == SaveType.SAVE)
            if (!itsCeilingConfigValidator.isCeilingConfigKeyValid(aCeilingConfigurationEntity)) {
                throw new CeilingConfigurationBusinessException("ppp.budget.repeatedKey");
            }
            return itsCeilingConfigurationDao.saveCeilingConfigWithBudgetKeyItems(aCeilingConfigurationEntity, aSaveType);
    }

    @Override
    public void delete(Long aCeilingConfigId)
    {
        CeilingConfigurationEntity myCeiling = itsCeilingConfigurationDao.findById(aCeilingConfigId, true);
        itsCeilingConfigurationDao.deleteCeiling(myCeiling);
    }

}
