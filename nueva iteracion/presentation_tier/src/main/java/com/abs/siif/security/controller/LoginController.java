/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.security.controller;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.ppp.planning.controller.OptionsController;
import com.abs.siif.ppp.programming.controller.BackPreFichaController;
import com.abs.siif.security.api.controller.LoginControllerApi;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.security.entities.UserEntity;
import com.abs.siif.security.management.LoginManagement;
import com.abs.siif.security.management.ServConstrainsManagement;
import com.abs.siif.security.viewenum.ViewConstrainsEnum;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hunabKu
 */
@Controller("loginController")
@Scope("session")
public class LoginController extends SIIFControllerBase
        implements Serializable, LoginControllerApi {

    @Resource(name = "loginManagement")
    private transient LoginManagement loginManagement;
    @Resource(name = "servConstrainsManagement")
    private transient ServConstrainsManagement servConstrainsManagement;
    @Resource(name = "backPreFichaController")
    private transient BackPreFichaController backFindPreFicha;
    @Resource(name = "optionsController")
    private transient OptionsController optionsController;
    private String itsUserPassword;
    private String itsUserKey;
    private String itsActionResult = "";
    private String itsUserDescription;
    private String itsNewPassword;
    private String itsConfirPassword;
    private String itsOriginalPassword;
    private String kaptchaReceived;
    private Long itsAProfileId = 0L;
    private boolean isRolesCboEnabled = false;
    private boolean isPasswordCHanged = false;
    private ProfileEntity itsRoleSelected;
    private List<UserEntity> itsUserEntityResult;
    private List<ProfileEntity> itsProfilesResult;

    @Override
    public String getKaptchaReceived() {
        return kaptchaReceived;
    }

    @Override
    public void setKaptchaReceived(String kaptcha) {
        kaptchaReceived = kaptcha;
    }

    @Override
    public String getItsOriginalPassword() {
        return itsOriginalPassword;
    }

    @Override
    public void setItsOriginalPassword(String itsOriginalPassword) {
        this.itsOriginalPassword = itsOriginalPassword;
    }

    @Override
    public String getItsConfirPassword() {
        return itsConfirPassword;
    }

    @Override
    public void setItsConfirPassword(String itsConfirPassword) {
        this.itsConfirPassword = itsConfirPassword;
    }

    @Override
    public String getItsNewPassword() {
        return itsNewPassword;
    }

    @Override
    public void setItsNewPassword(String itsNewPassword) {
        this.itsNewPassword = itsNewPassword;
    }

    @Override
    public String getItsUserDescription() {
        return itsUserDescription;
    }

    @Override
    public void setItsUserDescription(String itsUserDescription) {
        this.itsUserDescription = itsUserDescription;
    }

    @Override
    public boolean isIsRolesCboEnabled() {
        return isRolesCboEnabled;
    }

    @Override
    public void setIsRolesCboEnabled(boolean isRolesCboEnabled) {
        this.isRolesCboEnabled = isRolesCboEnabled;
    }

    @Override
    public Long getItsAProfileId() {
        return itsAProfileId;
    }

    @Override
    public void setItsAProfileId(Long itsAProfileId) {
        this.itsAProfileId = itsAProfileId;
    }

    @Override
    public List<ProfileEntity> getItsProfilesResult() {
        return itsProfilesResult;
    }

    @Override
    public ProfileEntity getItsRoleSelected() {
        return itsRoleSelected;
    }

    @Override
    public void setItsRoleSelected(ProfileEntity itsRoleSelected) {
        this.itsRoleSelected = itsRoleSelected;
    }

    @Override
    public List<UserEntity> getItsUserEntityResult() {
        return itsUserEntityResult;
    }

    @Override
    public void setItsUserEntityResult(List<UserEntity> itsUserEntityResult) {
        this.itsUserEntityResult = itsUserEntityResult;
    }

    @Override
    public String getItsActionResult() {
        return itsActionResult;
    }

    @Override
    public void setItsActionResult(String itsActionResult) {
        this.itsActionResult = itsActionResult;
    }

    @Override
    public String getItsUserKey() {
        return itsUserKey;
    }

    @Override
    public void setItsUserKey(String itsUserKey) {
        this.itsUserKey = itsUserKey;
    }

    @Override
    public String getItsUserPassword() {
        return itsUserPassword;
    }

    @Override
    public void setItsUserPassword(String itsUserPassword) {
        this.itsUserPassword = itsUserPassword;
    }

    @Override
    public String autenticateUser() {
        initChangePassword();
        if ("options".equals(itsActionResult) && isPasswordCHanged == false) {
            itsActionResult = "";
            return "options";
        }
        return itsActionResult;
    }

    public void initChangePassword() {
        itsOriginalPassword = "";
        itsNewPassword = "";
        itsConfirPassword = "";
        kaptchaReceived = "";
    }

    @Override
    public void validateUser() throws RuntimeException {
        String myMessage = "";
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_INFO;
        itsActionResult = "";
        itsUserEntityResult = loginManagement.verifyData(itsUserKey, itsUserPassword);
        if (itsUserEntityResult != null && itsUserEntityResult.size() == 1) {
            setUser(itsUserEntityResult.get(0));
            itsUserDescription = itsUserEntityResult.get(0).getUserDescription();
            itsProfilesResult =
                    loginManagement.getUserProfilesById(itsUserEntityResult.get(0).getUserId());
            if (itsProfilesResult.size() == 1) {
                setRole(itsProfilesResult.get(0));
                itsRoleSelected = itsProfilesResult.get(0);
                optionsController.optionsInit();
                itsActionResult = redirectResult();
            } else if (itsProfilesResult.isEmpty()) {
                myMessage = "ppp.login.msgProfiles";
            } else {
                myMessage = "ppp.login.selectRole";
                itsActionResult = "profiles";
                isRolesCboEnabled = true;
            }
        } else if (itsUserKey.trim().length() == 0 || itsUserPassword.trim().length() == 0) {
            myMessage = "ppp.login.userEmpty";
            mySeverity = FacesMessage.SEVERITY_ERROR;
        } else {
            itsUserPassword = "";
            myMessage = "ppp.login.error";
            mySeverity = FacesMessage.SEVERITY_ERROR;
        }
        addMessageCurrentInstance(mySeverity,
                getMapKeyExcpetion(myMessage),
                getMapKeyExcpetion(myMessage));
    }

    @Override
    public boolean validateCaptcha() {
        if (kaptchaReceived != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext ext = context.getExternalContext();
            Map<String, Object> session = ext.getSessionMap(); 
            String kaptchaExpected = (String) session.get(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            if (kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {
                kaptchaReceived = "";
                return true;
            }
        }else if (kaptchaReceived.trim().equals("")){
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                getMessage("ppp.progr.Loggin.ErrorCaptcha.MessageRequired"),
                getMessage("ppp.progr.Loggin.ErrorCaptcha.MessageRequired"));
            return false;
        }
        addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                getMessage("ppp.progr.Loggin.ErrorCaptcha"),
                getMessage("ppp.progr.Loggin.ErrorCaptcha"));
        kaptchaReceived = "";
        return false;
    }

    @Override
    public void selectRole() {
        itsActionResult = "";
        if (itsAProfileId != null && itsAProfileId != 0) {
            for (ProfileEntity myRole : itsProfilesResult) {
                if (myRole.getProfileId().equals(itsAProfileId)) {
                    itsRoleSelected = myRole;
                    setRole(itsRoleSelected);

                    //Coloca las restricciones de componentes visuales para
                    // el Rol que esta ingresando
                    List<ViewConstrainsEnum> constrains =
                            servConstrainsManagement.getViewConstrainsUIComponent();
                    SIIFContextBase.setParameterSession(
                            SessionKeyEnum.VIEW_UI_COMPONENT_CONSTRAINS,
                            constrains);

                    optionsController.optionsInit();
                    itsActionResult = redirectResult();
                    break;
                }
            }
        } else {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    getMapKeyExcpetion("ppp.login.selectRole"),
                    getMapKeyExcpetion("ppp.login.selectRole"));
        }
    }

    @Override
    public void cancel() {
        setUser(null);
        itsActionResult = "returnlogin";
        isRolesCboEnabled = false;
        this.init();
    }

    @Override
    public void init() {
        if (getUser() == null) {
            itsAProfileId = 0L;
            isRolesCboEnabled = false;
            itsUserKey = "";
            itsUserPassword = "";
            clearLists();
            if (!itsActionResult.equals("returnlogin")) {
                itsActionResult = "";
            }
        }
        if (itsActionResult.equals("login") && isPasswordCHanged != true) {
            SIIFContextBase.invalidateContext();
        } else {
            isPasswordCHanged = false;
        }
        if (itsActionResult.equals("")) {
            SIIFContextBase.invalidateContext();
        }
    }

    @Override
    public void clearLists() {
        if (itsProfilesResult != null) {
            itsProfilesResult.clear();
        }
        if (itsUserEntityResult != null) {
            itsUserEntityResult.clear();
        }
    }

    @Override
    public String redirectResult() {
        return "options";
    }

    @Override
    public void logOut() {
        itsActionResult = "login";
        backFindPreFicha.setFromConsult(false);
    }

    @Override
    public void goToOptions() {
        itsActionResult = this.redirectResult();
        backFindPreFicha.setFromConsult(false);
    }

    @Override
    public BackPreFichaController getBackFindPreFicha() {
        return backFindPreFicha;
    }

    @Override
    public void setBackFindPreFicha(BackPreFichaController backFindPreFicha) {
        this.backFindPreFicha = backFindPreFicha;
    }

    @Override
    public void changePass() {
        if (validateCaptcha()) {
            itsActionResult = "";
            UserEntity myUser;
            String myMessage = getMessage("ppp.header.changePassSucces");
            FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_INFO;
            validation:
            if (itsNewPassword != null && itsConfirPassword != null) {
                if (this.passwordValidation()) {
                    break validation;
                }
                if (itsNewPassword.equals(itsConfirPassword)) {
                    myUser = getUser();
                    try {
                        loginManagement.changePassword(itsNewPassword, itsOriginalPassword, myUser);
                        SIIFContextBase.invalidateContext();
                        isPasswordCHanged = true;
                        itsActionResult = "login";
                    } catch (RuntimeException ex) {
                        mySeverity = FacesMessage.SEVERITY_ERROR;
                        myMessage = getMapKeyExcpetion("ppp.header.actualPassError");
                    }
                } else {
                    mySeverity = FacesMessage.SEVERITY_ERROR;
                    myMessage = getMapKeyExcpetion("ppp.header.passwordError");
                }
                addMessageCurrentInstance(mySeverity,
                        myMessage,
                        myMessage);
            }
        }
    }

    @Override
    public boolean passwordValidation() {
        boolean myResult = false;
        FacesMessage.Severity mySeverity = FacesMessage.SEVERITY_ERROR;
        if (itsNewPassword.equals("")) {
            addMessageCurrentInstance(mySeverity,
                    getMessage("ppp.header.passRequired", "Nueva contraseña"),
                    getMessage("ppp.header.passRequired", "Nueva contraseña"));
            myResult = true;
        }
        if (itsConfirPassword.equals("")) {
            addMessageCurrentInstance(mySeverity,
                    getMessage("ppp.header.passRequired", "Confirmación"),
                    getMessage("ppp.header.passRequired", "confirmación"));
            myResult = true;
        }
        if (itsOriginalPassword.equals("")) {
            addMessageCurrentInstance(mySeverity,
                    getMessage("ppp.header.passRequired", "Contraseña"),
                    getMessage("ppp.header.passRequired", "Contraseña"));
            myResult = true;
        }
        if ((itsConfirPassword.length() < 4) || (itsNewPassword.length() < 4)) {
            addMessageCurrentInstance(mySeverity,
                    getMapKeyExcpetion("ppp.header.minLength"),
                    getMapKeyExcpetion("ppp.header.minLength"));
            myResult = true;
        }
        return myResult;
    }
}
