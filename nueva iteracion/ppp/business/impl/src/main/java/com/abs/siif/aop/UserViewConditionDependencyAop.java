/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  UserViewDependency
 *  Purpose:  This class is responsible to create the Constrains for Security 
 *  about UserViewDependency
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
import com.abs.siif.support.AliasEntityEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.planning.comparators.DependenceKeyComparator;
import com.abs.siif.planning.dao.DependenceDaoFilters;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.security.dao.ViewUserModuleDao;
import com.abs.siif.security.dto.ViewConstrainsDto;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Clase que trabaja sobre el concepto de Aspecto para interceptar las llamadas
 * que se dirigen a la ejecución de Dependencias (DAO) con la finalidad de
 * obtener las restricciones de los datos indicados por la configuración del
 * usuario
 *
 * @author Israel Ruiz
 */
@Aspect
public class UserViewConditionDependencyAop extends SIIFBase implements AsyncListener {

    /**
     * Dao que se encar de proveer las restricciones del usuario
     */
    @Resource(name = "viewUserModuleDao")
    ViewUserModuleDao viewUserModuloDao;
    /**
     * Dao al cual se estará inceptando sus llamadas
     */
    @Resource(name = "DependenceDao")
    DependenceDaoFilters dependenceDao;
    //Logger de la calse
    Logger logAop = Logger.getLogger(UserViewConditionDependencyAop.class.getName());

    /**
     * Se encarga de ejecutar el proceso de seguridad para los datos cuando se
     * invoca las depencencias que son de tipo prosupuiestal
     * @Before("execution(*
     * com.abs.siif.planning.dao.DependenceDao.getViewHasBudgetDependencies(..))")
     */
    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getViewHasBudgetDependencies(..))")
    public void createFilterViewHasBudget() {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.HAS_BUDGET_LEVEL.getLevel());

        exceuteProcess(viewConstrains, "getViewHasBudgetFilter");
    }

    /**
     * Se encarga de ejecutar el proceso de seguridad para los datos cuando se
     * invoca las depencencias que son de tipo UR @Before("execution(*
     * com.abs.siif.planning.dao.DependenceDao.getViewDependenciesTypeUR(..))")
     */
    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getViewDependenciesTypeUR(..))")
    public void createFilterIsRespUnitLevel() {
        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.IS_RESP_UNIT_LEVEL.getLevel());
        exceuteProcess(viewConstrains, "getViewDepTypeURFilter");
    }

    /**
     * Se encarga de ejecutar el proceso de seguridad para los datos cuando se
     * invoca las depencencias que son de tipo tiene programación
     * @Before("execution(*
     * com.abs.siif.planning.dao.DependenceDao.getViewDepHasProgramming(..))")
     */
    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getViewDepHasProgramming(..))")
    public void createFilterHasProgramming() {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.HAS_PROGRAMMING_LEVEL.getLevel());

        exceuteProcess(viewConstrains, "getViewDepHasProgrammingFilter");
    }

    /**
     * Se encarga de ejecutar el proceso de seguridad para los datos cuando se
     * invoca las depencencias que son de tipo es Unidad de Ejecución
     * @Before("execution(*
     * com.abs.siif.planning.dao.DependenceDao.getViewDepIsExecutionUnit(..))")
     */
    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getViewDepIsExecutionUnit(..))")
    public void createFilterIsExecutionUnit() {

        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.IS_EXECUTION_LEVEL.getLevel());

        exceuteProcess(viewConstrains, "getViewDepIsExecutionUnitFilter");
    }

    /**
     * Se encarga de ejecutar el proceso de seguridad para los datos cuando se
     * invoca las depencencias que son de tipo es Institucional Plan
     * @Before("execution(*
     * com.abs.siif.planning.dao.DependenceDao.getViewDepHasInstitutionalPlan(..))")
     */
    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getViewDepHasInstitutionalPlan(..))")
    public void createFilterHasInstitucionalPlan() {
        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.HAS_INSTITUTIONAL_PLAN.getLevel());

        exceuteProcess(viewConstrains, "getViewDepHasInstitutionalPlanFilter");
    }

    /**
     * Se encarga de ejecutar el proceso de seguridad para los datos cuando se
     * invoca las depencencias que son de tipo es filial @Before("execution(*
     * com.abs.siif.planning.dao.DependenceDao.getViewDepIsBranch(..))")
     */
    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getViewDepIsBranch(..))")
    public void createFilterIsBranch() {
        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.IS_BRANCH.getLevel());

        exceuteProcess(viewConstrains, "getViewDepIsBranchFilter");
    }

    /**
     * Se encarga de ejecutar el proceso de seguridad para los datos cuando se
     * invoca las depencencias que son de tipo es Sector @Before("execution(*
     * com.abs.siif.planning.dao.DependenceDao.getViewDepIsSector(..))")
     */
    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getViewDepIsSector(..))")
    public void createFilterIsSector() {
        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.IS_SECTOR.getLevel());

        exceuteProcess(viewConstrains, "getViewDepIsSectorFilter");
    }

    /**
     * Se encarga de ejecutar el proceso de seguridad para los datos cuando se
     * invoca las depencencias que son de tipo Tiene encuadre con Objeto
     * @Before("execution(*
     * com.abs.siif.planning.dao.DependenceDao.getViewDepHasObjFrame(..))")
     */
    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getViewDepHasObjFrame(..))")
    public void createFilterHasObjFrame() {
        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.HAS_OBJECTIVE_FRAME.getLevel());

        exceuteProcess(viewConstrains, "getViewDepHasObjFrameFilter");
    }

    /**
     * Aspecto que se encarga de meter seguridad en la vista de las dependencias
     * que son de tipo presupuestal
     */
    @Before("execution(* com.abs.siif.planning.dao.DependenceDao.getViewDepIsBudgetUnit(..))")
    public void createFilterIsBudgetUnit() {
        List<ViewConstrainsDto> viewConstrains =
                viewUserModuloDao.getUserConstrains(
                UserViewEnumDependcy.IS_BUDGET_LEVEL.getLevel());

        exceuteProcess(viewConstrains, "getViewDepIsBudgetUnitFilter");
    }

    /**
     * Procesa la ejecución para los queries en base a las restricciones
     * indicadas y sobre la llamad del filtro en particular
     *
     * @param viewConstrains
     * @param serviceCall
     */
    @SuppressWarnings("SleepWhileInLoop")
    private void exceuteProcess(List<ViewConstrainsDto> viewConstrains,
            String serviceCall) {

        String filter;
        Comparator comKeyDep = DependenceKeyComparator.getInstance();
        AliasEntityEnum elementEnum;

        Set<DependenceEntity> entitiesResult =
                new TreeSet<DependenceEntity>(comKeyDep);

        AsynchCall asyCallRef;
        List<AsynchCall> asyCall = new ArrayList<AsynchCall>();
        int ind = 1;
        for (ViewConstrainsDto constrain : viewConstrains) {
            elementEnum = AliasEntityEnum.getEnumData(
                    constrain.getEntityName().trim());
            if (constrain.getCondition().contains("%")) {
                filter = "cast( cast(" + elementEnum.name().trim() + "."
                        + elementEnum.getEntityClave() + " as int ) as string )"
                        + " like '" + constrain.getCondition().trim() + "' ";
            } else {
                filter = elementEnum.name() + "."
                        + elementEnum.getEntityClave()
                        + " = '" + constrain.getCondition() + "'";
            }

            try {
                asyCallRef = new AsynchCall();
                asyCallRef.executCall(this, dependenceDao, serviceCall, filter);
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
                                    (Collection<DependenceEntity>) ref.getResultCall());
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
                            (Collection<DependenceEntity>) ref.getResultCall());
                    asyCall.remove(i);
                    i = -1;
                    ref = null;
                }
            }
        } catch (Exception ex) {
            logAop.log(Level.SEVERE, "Error al ejecutar llamadas Async", ex);
        }

        // En caso que se halla generado al menos un filtro 
        //se coloca dicha información en session
        if (entitiesResult != null) {
            SIIFContextBase.setParameterSession(
                    SessionKeyEnum.VIEW_USER_CONSTRAINS,
                    entitiesResult);
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
