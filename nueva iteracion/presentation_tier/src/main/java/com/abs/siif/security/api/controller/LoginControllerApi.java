/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  LoginControllerApi
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

import com.abs.siif.ppp.programming.controller.BackPreFichaController;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.security.entities.UserEntity;
import java.util.List;

/**
 *
 * @author luis.carreon
 */
public interface LoginControllerApi {

    String autenticateUser();

    void cancel();

    void changePass();

    void clearLists();

    BackPreFichaController getBackFindPreFicha();

    Long getItsAProfileId();

    String getItsActionResult();

    String getItsConfirPassword();

    String getItsNewPassword();

    String getItsOriginalPassword();

    List<ProfileEntity> getItsProfilesResult();

    ProfileEntity getItsRoleSelected();

    String getItsUserDescription();

    List<UserEntity> getItsUserEntityResult();

    String getItsUserKey();

    String getItsUserPassword();

    void goToOptions();

    void init();

    boolean isIsRolesCboEnabled();

    void logOut();

    boolean passwordValidation();

    String redirectResult();

    String getKaptchaReceived();
    
    void setKaptchaReceived(String kaptcha);
    
    boolean validateCaptcha();
    
    void selectRole();

    void setBackFindPreFicha(BackPreFichaController backFindPreFicha);

    void setIsRolesCboEnabled(boolean isRolesCboEnabled);

    void setItsAProfileId(Long itsAProfileId);

    void setItsActionResult(String itsActionResult);

    void setItsConfirPassword(String itsConfirPassword);

    void setItsNewPassword(String itsNewPassword);

    void setItsOriginalPassword(String itsOriginalPassword);

    void setItsRoleSelected(ProfileEntity itsRoleSelected);

    void setItsUserDescription(String itsUserDescription);

    void setItsUserEntityResult(List<UserEntity> itsUserEntityResult);

    void setItsUserKey(String itsUserKey);

    void setItsUserPassword(String itsUserPassword);

    void validateUser() throws RuntimeException;
    
}
