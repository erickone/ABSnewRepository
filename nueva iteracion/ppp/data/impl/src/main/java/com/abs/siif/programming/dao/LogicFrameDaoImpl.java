/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  LogicFrameDaoImpl
 *  Purpose:  [ Este archivo, es una implemetación de la clase llamada
 *  LogicFrameDao, y ella se desglosan las operaciones que se realizarán en 
 *  la base datos, implementar y extender la clase base que se encargara de 
 *  hacer las operaciones comunes a todas las implemetaciones de los Dao]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.common.entities.DocumentTypeEntity;
import com.abs.siif.programming.entities.LogicFrameEntity;
import java.io.Serializable;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
@Repository("logicFrameDao")
public class LogicFrameDaoImpl 
extends SIIFBaseDaoImpl<LogicFrameEntity, Long> 
implements LogicFrameDao
{
    @Autowired
    private SessionFactory theirSessionFactory;


    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() 
    {
        return theirSessionFactory;
    }
    
    @Transactional(readOnly = true)
    @Override
    public Collection<LogicFrameEntity> getLogicFrameByDraftProjectID(Long aDraftProjectId) 
    {
       String myQueryHQL = "select distinct LFE from LogicFrameEntity LFE"
               + " left join fetch LFE.logicFrameDraftProject"
               + " left join fetch LFE.logicFrameDocumentType"
               + " left join fetch LFE.logicFrameUserId"
               + " where LFE.logicFrameDraftProject = " + aDraftProjectId + "" ;
       return super.find(myQueryHQL);
    }

    @Transactional(readOnly = false)
    @Override
    public Long saveLogicFrameFileData(LogicFrameEntity aLogicFrameEntity) 
    {
        
        Long myId = null;
        if (aLogicFrameEntity.getLogicFrameId() == null || aLogicFrameEntity.getLogicFrameId().equals("")) 
        {
            myId = super.save(aLogicFrameEntity).getLogicFrameId();
        } else 
        {
            myId = super.merge(aLogicFrameEntity).getLogicFrameId();
        }
        return myId;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void deleteLogicFrameFileData(LogicFrameEntity aLogicFrameId) 
    {
        //TODO: implementar el borrado con un HQL delete en lugar de utlizar 
        //el super.delete, para simplificar las cosas
        super.delete(aLogicFrameId);
    }

    @Transactional(readOnly = false)
    @Override
    public Long updateLogicFrameFileData(LogicFrameEntity aLogicFrameEntity) 
    {
        
        return super.merge(aLogicFrameEntity).getLogicFrameId();
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DocumentTypeEntity> getAllDocumentTypes() 
    {
         String myQueryHQL = "select distinct doc from DocumentTypeEntity doc"
                + " order by doc.documentTypeDescription asc ";
        return super.find(myQueryHQL);
    }
    
}
