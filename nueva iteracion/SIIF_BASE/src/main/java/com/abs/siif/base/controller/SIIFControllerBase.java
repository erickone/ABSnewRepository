/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.base.controller;

import com.abs.siif.base.SIIFBase;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Israel Ruiz
 */
public class SIIFControllerBase extends SIIFBase{
    
    protected void addMessageCurrentInstance(FacesMessage.Severity aSeverity, String aSummary, String aDetails) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(aSeverity, aSummary, aDetails));
    }
    
}
