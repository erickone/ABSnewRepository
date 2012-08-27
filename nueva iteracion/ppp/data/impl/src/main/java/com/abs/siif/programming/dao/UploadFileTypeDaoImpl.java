/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  UploadFileTypeDaoImpl
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
import com.abs.siif.budget.entities.UploadFileTypeEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FENIX-02
 */
@Repository("uploadFileTypeDao")
public class UploadFileTypeDaoImpl extends SIIFBaseDaoImpl<UploadFileTypeEntity,Long> 
implements UploadFileTypeDao{

    @Autowired
    private SessionFactory theirSessionFactory;
    
    @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public UploadFileTypeEntity getUploadFileTypeByKey(String aKey)
    {
        String myQueryHQL = "select distinct uft from UploadFileTypeEntity uft "
                + " where uft.upLoadFileTypeKey = :aKey";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setString("aKey", aKey);
        
        if (myQuery.list().size() > 0)
            return (UploadFileTypeEntity) myQuery.list().get(0);
        else
            return null;
    }
    
}
