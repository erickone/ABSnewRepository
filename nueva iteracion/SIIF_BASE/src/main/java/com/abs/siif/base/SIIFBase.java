/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.base;

import com.abs.siif.base.common.ResourceBundleMassage;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.security.entities.UserEntity;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**KeyContextEnum
 *
 * @author Israel Ruiz
 */
public abstract class SIIFBase {
    
    protected static final Logger log =  Logger.getGlobal();
    
    private static Map<String,Object> siffContext = new HashMap<String, Object>();
    
    
    final protected String getMapKeyExcpetion(String key){
        
        String data;
        try {
            data = ResourceBundleMassage.getString(key);
        } catch (Exception ex) {
            data = key;
        }
        return data;
    }

    final protected String getMessage(String key, String... arg) {

        String data;
        try {
            data = ResourceBundleMassage.getString(key, arg);
        } catch (Exception ex) {
            data = key;
        }
        return data;
    }

    public Locale getLocaleSIIF() {
        return Locale.US;
        //Locale.Builder().setLanguage("es").setRegion("MX").build();
    }

    protected void logInfoLevel(String mesagge) {
        log.log(Level.INFO, "{0} {1} {2}", new Object[]{new Date(), this.getClass(), mesagge});
    }

    protected void logWarningLevel(String mesagge) {
        log.log(Level.WARNING, "{0} {1} {2}", new Object[]{new Date(), this.getClass(), mesagge});
    }

    protected void logErrorLevel(String mesagge) {
        log.log(Level.SEVERE, "{0} {1} {2}", new Object[]{new Date(), this.getClass(), mesagge});
    }
    
   final protected UserEntity getUser() {
        UserEntity myUser = (UserEntity) SIIFContextBase.getParameterSession(SessionKeyEnum.USER); 
        return myUser;
    }

    final protected void setUser(UserEntity anUserEntity) {
        SIIFContextBase.setParameterSession(SessionKeyEnum.USER, anUserEntity);
    }
    
    final protected ProfileEntity getRole() {
        ProfileEntity myRole = (ProfileEntity) SIIFContextBase.getParameterSession(SessionKeyEnum.ROLE); 
        return myRole;
    }

    final protected void setRole(ProfileEntity aProfileEntity) {
        SIIFContextBase.setParameterSession(SessionKeyEnum.ROLE, aProfileEntity);
    }
 }
