/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import com.abs.siif.ppp.programming.api.controller.ClassifierControllerApi;
import com.abs.siif.programming.entities.ClassifierEntity;
import com.abs.siif.support.UtilValidations;
import java.io.Serializable;
import javax.faces.application.FacesMessage;

/**
 *
 * @author FENIX-02
 */
public class ClassifierUIHelper extends SIIFControllerBase implements Serializable {

    public Boolean validateClassifier(ClassifierControllerApi aClassifierController) {
        Boolean isValid = true;
        /*
         * if(!UtilValidations.notNullOrBlank(aClassifierController.getItsBudgetUnitId()))
         * { addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
         * this.getMessage("ppp.progr.InvPreFileErrType"),
         * this.getMessage("ppp.progr.InvPreFileErrType")); isValid = false; }
         * if(!UtilValidations.notNullOrBlank(aClassifierController.getItsResponsibleUnitId()))
         * { addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
         * this.getMessage("ppp.progr.InvPreFileErrType"),
         * this.getMessage("ppp.progr.InvPreFileErrType")); isValid = false; }
         * if(!UtilValidations.notNullOrBlank(aClassifierController.getItsExpenditureImplementationUnitId()))
         * { addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
         * this.getMessage("ppp.progr.InvPreFileErrType"),
         * this.getMessage("ppp.progr.InvPreFileErrType")); isValid = false; }
         * if(!UtilValidations.notNullOrBlank(aClassifierController.getItsBranchId()))
         * { addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
         * this.getMessage("ppp.progr.InvPreFileErrType"),
         * this.getMessage("ppp.progr.InvPreFileErrType")); isValid = false; }
         * if(!UtilValidations.notNullOrBlank(aClassifierController.getItsSectorId()))
         * { addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
         * this.getMessage("ppp.progr.InvPreFileErrType"),
         * this.getMessage("ppp.progr.InvPreFileErrType")); isValid = false;
        }
         */
        if (!UtilValidations.notNullOrBlank(aClassifierController.getItsFinalityId())) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.ClassifierFin"),
                    this.getMessage("ppp.progr.ClassifierFin"));
            isValid = false;
        }
        if (!UtilValidations.notNullOrBlank(aClassifierController.getItsFunctionId())) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.ClassifierFun"),
                    this.getMessage("ppp.progr.ClassifierFun"));
            isValid = false;
        }
        if (!UtilValidations.notNullOrBlank(aClassifierController.getItsSubFunctionId())) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.ClassifierSubFun"),
                    this.getMessage("ppp.progr.ClassifierSubFun"));
            isValid = false;
        }
        return isValid;
    }

    public static ClassifierEntity prepareClassifierEntity(ClassifierControllerApi aClassifierController) {
        ClassifierEntity myClassifier = aClassifierController.getItsClassifierEntity();

        FunctionalClassifierEntity myTmpFunctional = new FunctionalClassifierEntity();
        myTmpFunctional.setFunctionalClassifierId(aClassifierController.getItsFinalityId());
        myClassifier.setFinalidad(myTmpFunctional);

        myTmpFunctional = new FunctionalClassifierEntity();
        myTmpFunctional.setFunctionalClassifierId(aClassifierController.getItsFunctionId());
        myClassifier.setFuncion(myTmpFunctional);

        myTmpFunctional = new FunctionalClassifierEntity();
        myTmpFunctional.setFunctionalClassifierId(aClassifierController.getItsSubFunctionId());
        myClassifier.setSubFuncion(myTmpFunctional);

        Long myClassifierId=aClassifierController.getTheirCurrentId();
        myClassifier.setClassifierId(myClassifierId);
        
        
        return myClassifier;
    }
}
