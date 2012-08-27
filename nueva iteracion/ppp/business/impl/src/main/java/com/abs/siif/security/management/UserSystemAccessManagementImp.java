/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  UserSystemAccessManagementImp
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.management;

import com.abs.siif.security.dao.UserSystemAccessDao;
import com.abs.siif.security.entities.UserEntity;
import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Francisco Luna
 */
@Service("userSystemAccessManagement")
public class UserSystemAccessManagementImp implements UserSystemAccessManagement, Serializable{
    
    @Resource(name ="userSystemAccessDao")
    private transient UserSystemAccessDao userSystemAccessDao;

    @Override
    public void saveUser(UserEntity userEntity) {
        userSystemAccessDao.save(userEntity);
    }
    
}
