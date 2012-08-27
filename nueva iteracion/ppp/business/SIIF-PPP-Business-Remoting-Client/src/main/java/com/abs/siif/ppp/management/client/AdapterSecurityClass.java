/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.management.client;

import com.abs.siif.AdapterBaseSiif;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.security.entities.UserEntity;
import com.abs.siif.security.management.LoginManagement;
import com.abs.siif.security.management.ServConstrainsManagement;
import com.abs.siif.security.management.UserSystemAccessManagement;
import com.abs.siif.security.viewenum.ViewConstrainsEnum;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Israel Ruiz
 */
@Component("securityAdapterClass")
public class AdapterSecurityClass  extends AdapterBaseSiif implements
        LoginManagement, ServConstrainsManagement, UserSystemAccessManagement{

    @Override
    public List<UserEntity> verifyData(String aUserName, String aUserPsssword) {
         beanName = "loginManagement";
        return null;
    }

    @Override
    public List<ProfileEntity> getUserProfilesById(Long aUserId) {
          beanName = "loginManagement";
        return null;
    }

 
    @Override
    public List<ViewConstrainsEnum> getViewConstrainsUIComponent() {
        beanName = "servConstrainsManagement";
        return null;
    }

    @Override
    public void saveUser(UserEntity userEntity) {
       beanName = "userSystemAccessManagement";
    }
    
    @Override
    public void changePassword(String newPassword, 
            String originalPassword, UserEntity anUser){
        beanName = "loginManagement";
    }
}
