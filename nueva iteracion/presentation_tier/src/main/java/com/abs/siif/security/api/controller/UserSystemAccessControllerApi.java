/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  UserSystemAccessControllerApi
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.api.controller;

import com.abs.siif.security.dto.UserSystemAccessDto;
import com.abs.siif.security.management.UserSystemAccessManagement;

/**
 *
 * @author Francisco Luna
 */
public interface UserSystemAccessControllerApi {
    
    UserSystemAccessManagement getUserSysAccManage();
    
    void setUserSysAccManage(UserSystemAccessManagement userSysAccManage);
    
    UserSystemAccessDto getAccessDto();
    
    void setAccessDto(UserSystemAccessDto accessDto);
    
    void initViewElements();
    
    void regContrasena();
    
    boolean validateInputs();
    
    void saveUserSystemAccess();
    
}
