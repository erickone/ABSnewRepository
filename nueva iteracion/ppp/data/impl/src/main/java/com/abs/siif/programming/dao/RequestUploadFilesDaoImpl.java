/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RequestUploadFilesDaoImpl
 *  Purpose:  [ short Description  ]
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
import com.abs.siif.budget.entities.RequestUploadFilesEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author FENIX-02
 */
@Repository("requestUploadFilesDao")
public class RequestUploadFilesDaoImpl extends SIIFBaseDaoImpl<RequestUploadFilesEntity, Long>
implements RequestUploadFilesDao{

    @Autowired
    private SessionFactory theirSessionFactory;
    
    @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RequestUploadFilesEntity> getUploadedFilesByReqId(Long id)
    {
        String myQueryHQL = "select distinct upf from RequestUploadFilesEntity upf "
                + " where upf.reqPreInversionId = :aReqId"
                + " order by upf.url asc ";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("aReqId", id);
        return myQuery.list();
    }

    @Transactional(readOnly = true)
    @Override
    public RequestUploadFilesEntity getUploadedFileByPath(String aPath)
    {
        String myQueryHQL = "select distinct upf from RequestUploadFilesEntity upf "
                + " where upf.requestUpLoadFilePath = :aPath";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setString("aPath", aPath);
        
        if (myQuery.list().size() > 0)
            return (RequestUploadFilesEntity) myQuery.list().get(0);
        else
            return null;
    }

}
