/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  LoginMangement
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

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.security.dao.ProfileDao;
import com.abs.siif.security.dao.UserDao;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.security.entities.UserEntity;
import com.abs.siif.support.EncrypterClass;
import java.io.File;
import java.io.Serializable;
import java.security.KeyPair;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author luis.carreon
 */
@Service("loginManagement")
public class LoginMangementImpl implements LoginManagement, Serializable {

    @Resource(name = "userDao")
    private transient UserDao userDao;
    @Resource(name = "profileDao")
    private transient ProfileDao profileDao;

    @Override
    public List<UserEntity> verifyData(String aUserName, String aUserPsssword) {
        List<UserEntity> myResult = userDao.verifyData(aUserName);
        if (myResult != null && myResult.size() > 0) {
            if (aUserPsssword.equals(myResult.get(0).getUserPassword())){
                    //decryptPassword(myResult.get(0).getUserPassword()))) {
                return myResult;
            }
        }
        return null;
    }

    @Override
    public List<ProfileEntity> getUserProfilesById(Long aUserId) {
        return profileDao.getUserProfilesById(aUserId);
    }

    @Override
    public void changePassword(String newPassword, String originalPassword, UserEntity anUser) {
        if (originalPassword.equals(anUser.getUserPassword())) {
                //decryptPassword(anUser.getUserPassword()))) {
            //anUser.setUserPassword(encryptPassword(newPassword));
            anUser.setUserPassword(newPassword);
            userDao.merge(anUser);
        } else {
            throw new RuntimeException("");
        }
    }

    public static String decryptPassword(String aEncryptedPasssword) {
        try {
            File f = new File((String)SIIFContextBase.getParamContext(KeyContextEnum.PATH));
            KeyPair myKeyPair = EncrypterClass.generateKeys(f.getParent());
            byte[] myEncArray =
                    EncrypterClass.decrypt(aEncryptedPasssword, myKeyPair.getPrivate(), "RSA");
            return new String(myEncArray, "UTF8");
        } catch (Exception ex) {
            return "";
        }
    }

    public static String encryptPassword(String aPasssword) {
        try {
            File f = new File((String)SIIFContextBase.getParamContext(KeyContextEnum.PATH));
            KeyPair myKeyPair = EncrypterClass.generateKeys(f.getParent());
            byte[] myByteArray = aPasssword.getBytes("UTF8");
            return EncrypterClass.encrypt(myByteArray, myKeyPair.getPublic(), "RSA");
        } catch (Exception ex) {
            return "";
        }
    }
}
