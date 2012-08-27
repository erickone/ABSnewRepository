/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  UserViewConditionDepArgAop
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.aop;

import com.abs.siif.base.SIIFBase;
import com.abs.siif.base.async.AsyncListener;
import com.abs.siif.base.async.AsynchCall;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.planning.comparators.DependenceDtoComparator;
import com.abs.siif.planning.dao.DependenceDaoFilters;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.security.dao.ViewUserModuleDao;
import com.abs.siif.security.dto.ViewConstrainsDto;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 * @author luis.carreon
 */
@Aspect
public class UserViewConditionDepArgAop extends SIIFBase implements AsyncListener {

    /**
     * Dao que se encar de proveer las restricciones del usuario
     */
    @Resource(name = "viewUserModuleDao")
    ViewUserModuleDao viewUserModuloDao;
    @Resource(name = "DependenceDao")
    private transient DependenceDaoFilters dependenceDao;
    //Logger de la calse
    Logger logAop = Logger.getLogger(UserViewConditionDependencyAop.class.getName());

    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getDependciesIsRespUnitByDependIdRelated(..))")
    public void createFilterIsRespUnit(JoinPoint joinPoint) {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.IS_RESP_UNIT_LEVEL.getLevel());

        Object[] myArgs = joinPoint.getArgs();
        Object[] myNewArgs = new Object[myArgs.length + 1];
        System.arraycopy(myArgs, 0, myNewArgs, 0, myArgs.length);

        exceuteProcess(viewConstrains, "getDependciesIsRespUnitByDependIdRelatedFilter", myNewArgs);
    }

    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getDependciesIsBudgetByDependIdRelated(..))")
    public void createFilterIsBudget(JoinPoint joinPoint) {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.IS_BUDGET_LEVEL.getLevel());

        Object[] myArgs = joinPoint.getArgs();
        Object[] myNewArgs = new Object[myArgs.length + 1];
        System.arraycopy(myArgs, 0, myNewArgs, 0, myArgs.length);

        exceuteProcess(viewConstrains, "getDependciesIsBudgetByDependIdRelatedFilter", myNewArgs);
    }

    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getDependciesIsExecUnitByDependIdRelated(..))")
    public void createFilterIsExecUnit(JoinPoint joinPoint) {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.IS_EXECUTION_LEVEL.getLevel());

        Object[] myArgs = joinPoint.getArgs();
        Object[] myNewArgs = new Object[myArgs.length + 1];
        System.arraycopy(myArgs, 0, myNewArgs, 0, myArgs.length);

        exceuteProcess(viewConstrains, "getDependciesIsExecUnitByDependIdRelatedFilter", myNewArgs);
    }

    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getDependciesHasInstPlanByDependIdRelated(..))")
    public void createFilterHasInstPlan(JoinPoint joinPoint) {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.HAS_INSTITUTIONAL_PLAN.getLevel());

        Object[] myArgs = joinPoint.getArgs();
        Object[] myNewArgs = new Object[myArgs.length + 1];
        System.arraycopy(myArgs, 0, myNewArgs, 0, myArgs.length);

        exceuteProcess(viewConstrains, "getDependciesHasInstPlanByDependIdRelatedFilter", myNewArgs);
    }

    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getDependciesIsBranchByDependIdRelated(..))")
    public void createFilterIsBranch(JoinPoint joinPoint) {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.IS_BRANCH.getLevel());

        Object[] myArgs = joinPoint.getArgs();
        Object[] myNewArgs = new Object[myArgs.length + 1];
        System.arraycopy(myArgs, 0, myNewArgs, 0, myArgs.length);

        exceuteProcess(viewConstrains, "getDependciesIsBranchByDependIdRelatedFilter", myNewArgs);
    }

    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getDependciesIsSectorByDependIdRelated(..))")
    public void createFilterIsSector(JoinPoint joinPoint) {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.IS_SECTOR.getLevel());

        Object[] myArgs = joinPoint.getArgs();
        Object[] myNewArgs = new Object[myArgs.length + 1];
        System.arraycopy(myArgs, 0, myNewArgs, 0, myArgs.length);

        exceuteProcess(viewConstrains, "getDependciesIsSectorByDependIdRelatedFilter", myNewArgs);
    }

    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getDependciesHasBudgetByDependIdRelated(..))")
    public void createFilterHasBudget(JoinPoint joinPoint) {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.HAS_BUDGET_LEVEL.getLevel());

        Object[] myArgs = joinPoint.getArgs();
        Object[] myNewArgs = new Object[myArgs.length + 1];
        System.arraycopy(myArgs, 0, myNewArgs, 0, myArgs.length);

        exceuteProcess(viewConstrains, "getDependciesHasBudgetByDependIdRelatedFilter", myNewArgs);
    }

    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getDependciesHasProgrammingByDependIdRelated(..))")
    public void createFilterHasProgramming(JoinPoint joinPoint) {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.HAS_PROGRAMMING_LEVEL.getLevel());

        Object[] myArgs = joinPoint.getArgs();
        Object[] myNewArgs = new Object[myArgs.length + 1];
        System.arraycopy(myArgs, 0, myNewArgs, 0, myArgs.length);

        exceuteProcess(viewConstrains, "getDependciesHasProgrammingByDependIdRelatedFilter", myNewArgs);
    }

    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getDependciesHasObjectiveFramingByDependIdRelated(..))")
    public void createFilterHasObjectiveFrame(JoinPoint joinPoint) {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.HAS_OBJECTIVE_FRAME.getLevel());

        Object[] myArgs = joinPoint.getArgs();
        Object[] myNewArgs = new Object[myArgs.length + 1];
        System.arraycopy(myArgs, 0, myNewArgs, 0, myArgs.length);

        exceuteProcess(viewConstrains, "getDependciesHasObjectiveFramingByDependIdRelatedFilter", myNewArgs);
    }

    private void exceuteProcess(List<ViewConstrainsDto> viewConstrains,
            String serviceCall, Object[] myNewArgs) {

        String filter;
        Comparator comKeyDep = DependenceDtoComparator.getInstance();

        Set<DepencenceDto> entitiesResult =
                new TreeSet<DepencenceDto>(comKeyDep);

        AsynchCall asyCallRef;
        List<AsynchCall> asyCall = new ArrayList<AsynchCall>();
        int ind = 1;
        for (ViewConstrainsDto constrain : viewConstrains) {

            if (constrain.getCondition().contains("%")) {

                filter = " and to_char(to_number("
                        + " replace(dependencia.clave,\" \", \"\"))) like '"
                        + constrain.getCondition() + "' ";
            } else {
                filter = " and to_char(to_number("
                        + " replace(dependencia.clave,\" \", \"\"))) = '"
                        + constrain.getCondition() + "' ";
            }

            try {
                myNewArgs[myNewArgs.length - 1] = filter;
                asyCallRef = new AsynchCall();
                asyCallRef.executCall(this, dependenceDao, serviceCall, myNewArgs);
                asyCall.add(asyCallRef);
                if (ind++ % 5 == 0) {

                    for (int i = 0; i < asyCall.size(); i++) {

                        AsynchCall ref = asyCall.get(i);
                        Thread asyn = ref.getThread();
                        if (asyn.isAlive()) {
                            asyn.join(); // espera a que termine
                        }

                        if (ref.getResultCall() != null) {
                            entitiesResult.addAll(
                                    (Collection<DepencenceDto>) ref.getResultCall());
                            asyCall.remove(i);
                            i = -1;
                            ref = null;
                        }
                    }
                    Thread.sleep(100);
                }


            } catch (Exception ex) {
                logAop.log(Level.SEVERE, "Error al ejecutar llamadas Async", ex);
            }

        }
        try {

            for (int i = 0; i < asyCall.size(); i++) {

                AsynchCall ref = asyCall.get(i);
                Thread asyn = ref.getThread();
                if (asyn.isAlive()) {
                    asyn.join(); // espera a que termine
                }

                if (ref.getResultCall() != null) {
                    entitiesResult.addAll(
                            (Collection<DepencenceDto>) ref.getResultCall());
                    asyCall.remove(i);
                    i = -1;
                    ref = null;
                }
            }
            // En caso que se halla generado al menos un filtro 
            //se coloca dicha información en session
            if (entitiesResult != null && entitiesResult.size() > 0) {
                SIIFContextBase.setParameterSession(
                        SessionKeyEnum.VIEW_USER_CONSTRAINS,
                        entitiesResult);
            }
        } catch (Exception ex) {
            logAop.log(Level.SEVERE, "Error al ejecutar llamadas Async", ex);
        }
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
                logAop.log(Level.SEVERE,
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
}