/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  SecurityViewAop
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.aop;

import com.abs.siif.base.async.AsyncListener;
import com.abs.siif.base.async.AsynchCall;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.security.viewenum.ViewConstrainsEnum;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author Israel Ruiz
 */
@Aspect
public class SecurityViewAop implements AsyncListener {

    @Around("execution(* com.abs.siif.ppp.programming.controller.ProgrammingMainController.getBtnSrvDelete(..))")
    public Object programmingDeleteDraft(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.DELETE_DRAF_PROJECT;
        return executRevieSecurity(pjp, constrain);
    }

    @Around("execution(* com.abs.siif.ppp.programming.controller.DraftProjectHeaderController.getBtnSrvSave(..))")
    public Object clasifierDraftProject(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.SAVE_CLASSIF_DRAFT;
        return executRevieSecurity(pjp, constrain);
    }

    @Around("execution(* com.abs.siif.ppp.programming.controller.PedVinculationController.getBtnSrvSave(..))")
    public Object pedVinculationDraftProject(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.SAVE_PED_DRAFT;
        return executRevieSecurity(pjp, constrain);
    }

    @Around("execution(* com.abs.siif.ppp.programming.controller.PedVinculationController.setBtnSrvSaveRegion(..))")
    public void pedVincDraftProjectSet(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.SAVE_PED_DRAFT;
        executeSetParam(pjp,constrain,"setBtnSrvSaveRegion");
    }
    
    @Around("execution(* com.abs.siif.ppp.programming.controller.PropositController.getBtnSrvSave(..))")
    public Object deliverablesDraftProject(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.SAVE_DELIVERABLES;
        return executRevieSecurity(pjp, constrain);
    }

    @Around("execution(* com.abs.siif.ppp.programming.controller.BudgetingController.getBtnSrvSave(..))")
    public Object budgetingDraftProject(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.SAVE_BUDGETING;
        return executRevieSecurity(pjp, constrain);
    }

    @Around("execution(* com.abs.siif.ppp.programming.controller.SeplanValidationController.getBtnSrvSave(..))")
    public Object seplanDraftProject(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.SAVE_SEPLAN;
        return executRevieSecurity(pjp, constrain);
    }

    @Around("execution(* com.abs.siif.ppp.programming.controller.SeplanValidationController.getBtnSrvChangeStatus(..))")
    public Object seplanChangeDraftProject(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.CHANGE_SEPLAN;
        return executRevieSecurity(pjp, constrain);
    }

    @Around("execution(* com.abs.siif.ppp.programming.controller.LogicFrameController.getBtnSrvSave(..))")
    public Object logicFrameDraftProject(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.SAVE_LOGIC_FRAME;
        return executRevieSecurity(pjp, constrain);
    }

    @Around("execution(* com.abs.siif.ppp.programming.controller.UbicationPreFileController.setBtnSrvSave(..))")
    public void ubicationDraftProject(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.SAVE_UBICATION;
        executeSetParam(pjp,constrain,"setBtnSrvSave");
    }

    @Around("execution(* com.abs.siif.ppp.programming.controller.InvPrefileSearchController.getBtnSrvSave(..))")
    public Object prioritizeInvPrefile(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.PRIORITIZE_INVPREFILE;
        return executRevieSecurity(pjp, constrain);
    }

    @Around("execution(* com.abs.siif.ppp.programming.controller.InvPreFileController.getBtnObservations(..))")
    public Object observationsInvPrefile(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.OBSERVATIONS_INVPREFILE;
        return executRevieSecurity(pjp, constrain);
    }
    
    @Around("execution(* com.abs.siif.ppp.programming.controller.ObservationController.getItsDeleteDisabled(..))")
    public Object deleteObservations(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.DELETE_OBSERVATIONS;
        return executRevieSecurity(pjp, constrain);
    }
    
    @Around("execution(* com.abs.siif.ppp.programming.controller.FinancialStructureController.isEnableAddRelation(..))")
    public Object saveFinStructInvPrefile(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.FIN_STRUCT_INVPREFILE;
        return executRevieSecurity(pjp, constrain);
    }
    
    @Around("execution(* com.abs.siif.ppp.programming.controller.FinancialStructureController.btnDisabledEdit(..))")
    public Object deleteFinStructInvPrefile(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.FIN_STRUCT_INVPREFILE;
        return executRevieSecurity(pjp, constrain);
    }
    
    @Around("execution(* com.abs.siif.ppp.programming.controller.FinancialStructureController.btnDisabledDelete(..))")
    public Object editFinStructInvPrefile(ProceedingJoinPoint pjp) throws Throwable {
        ViewConstrainsEnum constrain = ViewConstrainsEnum.FIN_STRUCT_INVPREFILE;
        return executRevieSecurity(pjp, constrain);
    }

    private Object executRevieSecurity(ProceedingJoinPoint pjp,
            ViewConstrainsEnum constrain) throws Throwable {

        List<ViewConstrainsEnum> constrains = (List<ViewConstrainsEnum>) SIIFContextBase.getParameterSession(
                SessionKeyEnum.VIEW_UI_COMPONENT_CONSTRAINS);

        Object result = null;

        if (constrains != null &&  constrains.contains(constrain)) {
            result = Boolean.TRUE;
        } else {
            result = pjp.proceed();
        }

        return result;
    }

    private void executeSetParam(ProceedingJoinPoint pjp,
            ViewConstrainsEnum constrain, String serviceCall) throws Throwable {
        List<ViewConstrainsEnum> constrains = (List<ViewConstrainsEnum>) SIIFContextBase.getParameterSession(
                SessionKeyEnum.VIEW_UI_COMPONENT_CONSTRAINS);
        AsynchCall asyCall = new AsynchCall();
        if (constrains != null && constrains.contains(constrain)) {
            Object obj = pjp.getTarget();
            asyCall.executCall(this, obj, serviceCall , new Object[]{true});
            while (!asyCall.isFinished()) {
                waitToWakeUp();
            }
        } else {
            pjp.proceed();
        }
    }

    @Override
    public synchronized void waitToWakeUp() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(SecurityViewAop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void notifyWakeUp() {
        this.notify();
    }
}
