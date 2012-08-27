/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  UserGeneralDataController
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.security.dto.UserSystemAccessDto;
import com.abs.siif.security.entities.UserEntity;
import com.abs.siif.security.management.UserSystemAccessManagement;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * Clase que se encarga de lo relativo a la vista "Acceso al Sistema"
 * @version 1.0 19-Junio-2012
 * @author Francisco Luna
 */
@Controller("userSystemAccessController")
@Scope("session")
public class UserSystemAccessController extends SIIFControllerBase{ 
    
    @Resource(name = "userSystemAccessManagement")
    private UserSystemAccessManagement userSysAccManage;
    private UserSystemAccessDto accessDto;

    
    public UserSystemAccessManagement getUserSysAccManage() {
        return userSysAccManage;
    }

    public void setUserSysAccManage(UserSystemAccessManagement userSysAccManage) {
        this.userSysAccManage = userSysAccManage;
    }

    public UserSystemAccessDto getAccessDto() {
        return accessDto;
    }

    public void setAccessDto(UserSystemAccessDto accessDto) {
        this.accessDto = accessDto;
    }    
    
    public void initViewElements(){
        setAccessDto(new UserSystemAccessDto());
    }
    
    public void regContrasena(){
    }
    
    public boolean validateInputs(){
        boolean validInputs = true;
        
        if(getAccessDto().getContrasena().length() < 8){
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("security.systemAcces.msg.contraLenght"),
                        this.getMessage("security.systemAcces.msg.contraLenght"));
             validInputs = false;
        }
        if(!getAccessDto().getContrasena().equals(getAccessDto().getConfirmacion())) {
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("security.systemAcces.msg.contraConf"),
                        this.getMessage("security.systemAcces.msg.contraConf"));
             validInputs = false;
        }
        if(getAccessDto().getClave().equals(getAccessDto().getClaveVirtual())){
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("security.systemAcces.msg.claveVirtual"),
                        this.getMessage("security.systemAcces.msg.claveVirtual"));
             validInputs = false;
        }
        if(getAccessDto().getContrasenaVirtual().length() < 8){
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("security.systemAcces.msg.contraVirtualLength"),
                        this.getMessage("security.systemAcces.msg.contraVirtualLength"));
             validInputs = false;
        }
        if(getAccessDto().getContrasenaVirtual().length() < 8){
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("security.systemAcces.msg.contraVirtualLength"),
                        this.getMessage("security.systemAcces.msg.contraVirtualLength"));
             validInputs = false;
        }
        if(!getAccessDto().getContrasenaVirtual().equals(getAccessDto().getConfirmacionVirtual())) {
             addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("security.systemAcces.msg.contraVirtualConf"),
                        this.getMessage("security.systemAcces.msg.contraVirtualConf"));
             validInputs = false;
        }
        return validInputs;
    }
    
    public void saveUserSystemAccess(){
        UserEntity user = new UserEntity();
        user.setUserName(getAccessDto().getDescripcion());
        //user.set
        userSysAccManage.saveUser(null);
    }
    
}
