/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  LogicFrameManagementImpl
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

import com.abs.siif.common.entities.DocumentTypeEntity;
import com.abs.siif.programming.dao.LogicFrameDao;
import com.abs.siif.programming.entities.LogicFrameEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Erick Leija
 */
@Service("logicFrameManagement")
public class LogicFrameManagementImpl 
implements LogicFrameManagement, Serializable
{

    @Resource(name = "logicFrameDao")
    private LogicFrameDao theirLogicFrameDao;

    @Override
    public Collection<LogicFrameEntity> getLogicFrameByDraftProjectID
            (Long aDraftProjectId) 
    {
        return theirLogicFrameDao.
                getLogicFrameByDraftProjectID(aDraftProjectId);
    }

    @Override
    public Long saveLogicFrameFileData(LogicFrameEntity aLogicFrameEntity) 
    {
        return theirLogicFrameDao.saveLogicFrameFileData(aLogicFrameEntity);
    }

    @Override
    public void deleteLogicFrameFileData(LogicFrameEntity aLogicFrameId) 
    {
       theirLogicFrameDao.deleteLogicFrameFileData(aLogicFrameId);
    }

    @Override
    public Long updateLogicFrameFileData(LogicFrameEntity aLogicFrameEntity) 
    {
        return theirLogicFrameDao.updateLogicFrameFileData(aLogicFrameEntity);
    }

    @Override
    public Collection<DocumentTypeEntity> getAllDocumentTypes() {
        return theirLogicFrameDao.getAllDocumentTypes();
    }
    
}
