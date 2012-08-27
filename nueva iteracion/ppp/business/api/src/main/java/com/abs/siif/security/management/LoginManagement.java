/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  LoginMangementImpl
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

import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.security.entities.UserEntity;
import java.util.List;

/**
 *
 * @author luis.carreon
 */
public interface LoginManagement {
    
    List<UserEntity> verifyData(String aUserName, String aUserPsssword);
    
    List<ProfileEntity> getUserProfilesById(Long aUserId);
    
    void changePassword(String newPassword, String originalPassword, UserEntity anUser);

}
