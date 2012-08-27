/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CompositeValidation
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.aop.UserViewConditionDependencyAop;
import com.abs.siif.base.async.AsyncListener;
import com.abs.siif.base.async.AsynchCall;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.dto.ValidationNameEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Israel Ruiz
 */
@Component("composite")
@Scope("prototype")
public class CompositeValidation extends CompCeilingBudget
        implements AsyncListener {
    //Logger de la calse

    static final Logger logComp = Logger.getLogger(UserViewConditionDependencyAop.class.getName());
    private List<ValidateCompImport> leafs;
    private List<CellCeilingBudgetDto> entitiesValid;

    public CompositeValidation() {
        leafs = new ArrayList<ValidateCompImport>();
    }

    public void addValidator(ValidateCompImport leaf) {
        leafs.add(leaf);
    }

    @Override
    public List<CellCeilingBudgetDto> executeValidation(
            List<List<CellCeilingBudgetDto>> toValidateData) {
        List<CellCeilingBudgetDto> resultData =
                new ArrayList<CellCeilingBudgetDto>();

        AsynchCall asyCallRef;
        List<AsynchCall> asyCall = new ArrayList<AsynchCall>();

        for (int i = 0; i < toValidateData.size(); i++) {
            asyCallRef = new AsynchCall();
            ValidateCompImport validatorExec = null;
            try {

                for (ValidateCompImport validator : leafs) {

                    if (toValidateData.get(i) != null
                            && toValidateData.get(i).get(0) != null
                            && toValidateData.get(i).get(0).
                                getValidatorName() != null
                            && toValidateData.get(i).get(0).
                                getValidatorName().equals(
                        validator.getNameValidator().getBeanName())) {
                        
                        validatorExec = validator;
                        break;
                        
                    }

                }
                //En caso que el validador sea para la verificación
                // de la clave, se mandar toda la estructura
                if (validatorExec != null && validatorExec.getNameValidator()
                        == ValidationNameEnum.VALIDATION_UNIQUE_KEY) {

                    asyCallRef.executCall(this, validatorExec,
                            "executeValidation", toValidateData);

                } else if (validatorExec != null) {
                    asyCallRef.executCall(this, validatorExec,
                            "executeDataValidation", toValidateData.get(i));
                }
            } catch (Exception ex) {
                Logger.getLogger(CompositeValidation.class.getName()).
                        log(Level.SEVERE, null, ex);
            }

            asyCall.add(asyCallRef);

        }


        try {
            for (int i = 0; i < asyCall.size(); i++) {

                AsynchCall ref = asyCall.get(i);
                Thread asyn = ref.getThread();
                if (asyn.isAlive()) {
                    asyn.join(); // espera a que termine
                }

                if (ref.getResultCall() != null) {
                    resultData.addAll(
                            (List<CellCeilingBudgetDto>) ref.getResultCall());
                    asyCall.remove(i);
                    i = -1;
                    ref = null;
                }
            }
        } catch (Exception ex) {
            logComp.log(Level.SEVERE, "Error al ejecutar llamadas Async", ex);
        }

      
        this.entitiesValid = 
                (List<CellCeilingBudgetDto>) SIIFContextBase.getParameterSession(SessionKeyEnum.LIST_CEILLING_BUDGET);
        
        return resultData;
    }

    @Override
    public List<CellCeilingBudgetDto> executeDataValidation(
            List<CellCeilingBudgetDto> toValidateSets) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Requerido para mandar a esperar el thread mientras se ejecuta la llamada
     * Async
     */
    @Override
    public void waitToWakeUp() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                logComp.log(Level.SEVERE,
                        null, "Interrupcion en Wait");
            }
        }

    }

    /**
     * Requerido para sacar de la espera al thread
     */
    @Override
    public synchronized void notifyWakeUp() {
        notify();
    }

    /**
     * @return the entitiesValid
     */
    public List<CellCeilingBudgetDto> getEntitiesValid() {
        return entitiesValid;
    }

    /**
     * @param entitiesValid the entitiesValid to set
     */
    public void setEntitiesValid(List<CellCeilingBudgetDto> entitiesValid) {
        this.entitiesValid = entitiesValid;
    }
}
