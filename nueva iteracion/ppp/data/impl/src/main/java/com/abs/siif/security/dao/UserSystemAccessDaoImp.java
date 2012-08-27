/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  UserSystemAccessDaoImp
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.security.entities.UserEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Francisco Luna
 */
@Repository("userSystemAccessDao")
public class UserSystemAccessDaoImp extends SIIFBaseDaoImpl<UserEntity, String>
        implements UserSystemAccessDao{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }
    
    @Transactional(readOnly = false)
    @Override
    public UserEntity save(UserEntity entity) {
        return super.save(entity);
    }
    
}
