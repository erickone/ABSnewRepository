/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilllingBudgetManagement
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.management;

import com.abs.siif.base.async.AsyncListener;
import com.abs.siif.base.async.AsynchCall;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.budget.dao.*;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.budget.entities.DestinationEntity;
import com.abs.siif.budget.entities.FinancingSourceEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dao.DraftProjectDao;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.support.BudgetDraftProjectEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Israel Ruiz
 */
@Service("ceillingBudgetManagement")
public class CeilingBudgetManagementImpl implements AsyncListener, CeilingBudgetManagement {

    @Resource(name = "ceilingConfigurationDao")
    CeilingConfigurationDao theirceilingConfigurationDao;
    @Resource(name = "importCeilingBudgetDao")
    ImportCeilingBudgetDao theirImportCeilingBudgetDao;
    @Resource(name = "budgetKeyDao")
    BudgetKeyDao theirBudgetKeyDao;
    @Resource(name = "financingSourceDao")
    FinancingSourceDao theirFinancingSourceDao;
    @Resource(name = "draftProjectDaoImpl")
    DraftProjectDao theirDraftProjectDaoImpl;
    @Resource(name = "destinationDao")
    DestinationDao  theirDestinationDao;
    static final Logger logComp = Logger.getLogger(CeilingBudgetManagementImpl.class.getName());


    
    /**
     * Obtiene el disponible que se tiene en los techos, obtiniendo la la clave 
     * con la que se ingresos los montos
     * @param denpendency Parta ficha entra una dependencia UEG
     * @param objExpens
     * @param dest
     * @return
     */
    @Override
    public Long getBudgetForDraftProject(DependenceEntity denpendency,
            ObjectExpenseEntity objExpens, String dest, FinancingSourceEntity financialSource) {

        return getBudget( denpendency, objExpens,  dest,  financialSource,  false);
    }
    
    @Override
    public Long getBudgetForUR(DependenceEntity denpendency,
            ObjectExpenseEntity objExpens, String dest, FinancingSourceEntity financialSource) {

        return getBudget( denpendency, objExpens,  dest,  financialSource,  true);
    }
    
    
    private Long getBudget(DependenceEntity denpendency,
            ObjectExpenseEntity objExpens, String dest, FinancingSourceEntity financialSource,
            boolean total) {

        /**
         * consultar la clave de presupuesto esta devuelve una lista de
         * elementos de presupuestación apartir de ella generas una lista con el
         * enumerador BudgetDraftProjectEnum
         */
        CeilingConfigurationEntity myConfiguration =
                theirceilingConfigurationDao.getCeilingConfigurationByYear(Integer.parseInt(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()));
        if (myConfiguration == null) {
            return (long) 0;
        }
        List<BudgetDraftProjectEnum> budgetKey = new ArrayList<BudgetDraftProjectEnum>();
        String keyToSearch = "";
        for (BudgetKeyItemEntity items : myConfiguration.getBudgetKeyItems()) {

            BudgetDraftProjectEnum myEnum = BudgetDraftProjectEnum.valueOf(items.getBudgetKeyEntity());
            budgetKey.clear();
            budgetKey.add(myEnum);
            if (myEnum.getType().equals("DependenceEntity")) {

                List<DepencenceDto> resultData = new ArrayList<DepencenceDto>();
                resultData = getKeyByDependency(denpendency, budgetKey);
                DepencenceDto myDependence = resultData.get(0);
                if(keyToSearch.isEmpty())
                {
                keyToSearch += myDependence.getClave().replace(" ", "");
                }
                else
                {
                    keyToSearch += " " + myDependence.getClave().replace(" ", "");
                }
            } else {
                if (myEnum.getType().equals("ObjectExpenseEntity")) {
                    List<ObjectExpenseEntity> resultData2 = new ArrayList<ObjectExpenseEntity>();
                    resultData2 = getKeyByObjectExpense(objExpens, budgetKey);
                    ObjectExpenseEntity myObjectExpense = resultData2.get(0);
                    if(keyToSearch.isEmpty())
                    {
                    keyToSearch += myObjectExpense.getObjectExpenseKey();
                    }
                    else
                    {
                      keyToSearch += " " +  myObjectExpense.getObjectExpenseKey();
                    }
                } else {
                    if (myEnum.getType().equals("DestinationEntity")) {
                        if(keyToSearch.isEmpty())
                        {
                        keyToSearch += dest;
                        }
                        else
                        {
                          keyToSearch += " " +  dest;
                        }
                    } else {
                        if (myEnum.getType().equals("FinancingSourceEntity")) {
                            FinancingSourceEntity resultData3 = new FinancingSourceEntity();
                            resultData3 = getKeyByFinSource(financialSource, budgetKey);
                            keyToSearch += resultData3.getFinancingSourceKey();
                        }

                    }

                }
            }
        }

        int myYear = Integer.parseInt(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString());
        if (total)
        {
            return theirImportCeilingBudgetDao.getSummatoryTotalOfCeilingsNotBasicObject(myYear, keyToSearch);
        }
        else
        {
            return theirImportCeilingBudgetDao.getSummatoryTotalOfCeilingsByChargeKey(myYear, keyToSearch, "f");
        }
        
    }
    /**
     * Este metodo sirve para obtener el disponible del techo presupuestal
     * @param denpendency
     * @param objExpens
     * @param dest
     * @param financialSource
     * @return 
     */
      @Override
    public Long getBudgetAvailableForDraftProject(DependenceEntity denpendency, 
    ObjectExpenseEntity objExpens, String dest, FinancingSourceEntity financialSource, boolean basics)
    {
            List<BudgetDraftProjectEnum> budgetKey = new ArrayList<BudgetDraftProjectEnum>();
            BudgetDraftProjectEnum myEnum = BudgetDraftProjectEnum.valueOf("UR");
            budgetKey.clear();
            budgetKey.add(myEnum);
            List<DepencenceDto> resultData = new ArrayList<DepencenceDto>();
            resultData = getKeyByDependency(denpendency, budgetKey);
            
            if (resultData.size()==1)
            {
                DepencenceDto myURDto = resultData.get(0);
                DependenceEntity myURDependenceEntity = new DependenceEntity();
                myURDependenceEntity.setDependenceId(myURDto.getIdDependency());
                myEnum = BudgetDraftProjectEnum.valueOf("UEG");
                budgetKey.clear();
                budgetKey.add(myEnum);
                resultData = getKeyByDependency(myURDependenceEntity, budgetKey);
                String myKeyToSearch="";
                for(DepencenceDto myDepUEG: resultData)
                {
                    if (myKeyToSearch.equals(""))
                    {
                    myKeyToSearch = myDepUEG.getIdDependency().toString();
                    }
                    else
                    {
                        myKeyToSearch+= "," + myDepUEG.getIdDependency().toString();
                    }
                }
                if (myKeyToSearch.equals(""))
                {
                    return (long) 0;
                }
                 else
                {
                    if (basics) 
                    {
                        return theirDraftProjectDaoImpl.getAvailableCeilingBasics(myKeyToSearch, objExpens.getObjectExpenseId(), dest);
                    } 
                    else 
                    {
                        return (long) theirDraftProjectDaoImpl.getAvailableCeilingByUEGIds(myKeyToSearch);
                    }
                }
            }
            else
            {
                return (long) 0;
            }

    }

      /**
       * Este metodo sirve para obtener los TEchos de las fuentes de financiamiento
       * @param myFinanceSourceSelected
       * @return 
       */
    @Override
    public Long getFinancingSourceCeiling(FinancingSourceEntity myFinanceSourceSelected) {
        int myYear = Integer.parseInt(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString());
       
       
        FinancingSourceEntity myFinancingSource =
                theirFinancingSourceDao.getFinancingSourceById(myFinanceSourceSelected.getFinancingSourceId());
        String keyToSearch = myFinancingSource.getFinancingSourceKey();
        return theirImportCeilingBudgetDao.getSummatoryTotalOfCeilingsByChargeKey(myYear, myFinancingSource.getFinancingSourceKey(), "t");
    }

    /**
     * Este metodo sirve para obetner el disponible para la fuente de financiamiento dada
     * @param myFinanceSourceSelected
     * @return 
     */
    @Override
    public Long getFinancingSourceCeilingAvailable(FinancingSourceEntity myFinanceSourceSelected) {

        return theirBudgetKeyDao.getBudgetKeysSummaryAmountsByFinancingSouce(myFinanceSourceSelected);
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

    public List<DepencenceDto> getKeyByDependency(DependenceEntity denpendency,
            List<BudgetDraftProjectEnum> budgetKey) {

        List<DepencenceDto> resultData = new ArrayList<DepencenceDto>();
        AsynchCall execCall;
        Object beanExecute;
        List<AsynchCall> asyCall = new ArrayList<AsynchCall>();

        for (BudgetDraftProjectEnum dat : budgetKey) {
            execCall = new AsynchCall();
            if (denpendency.getClass().getCanonicalName().contains(dat.getType())) {
                beanExecute = SIIFContextBase.getAppContext().getBean(dat.getBeanName());
                try {

                    execCall.executCall(this, beanExecute,
                            dat.getServiceCall(),
                            denpendency.getDependenceId());

                } catch (Exception ex) {
                    Logger.getLogger(CeilingBudgetManagementImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                asyCall.add(execCall);
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
                    resultData.addAll(
                            (List<DepencenceDto>) ref.getResultCall());
                    asyCall.remove(i);
                    i = -1;
                }
            }
        } catch (Exception ex) {
            logComp.log(Level.SEVERE, "Error al ejecutar llamadas Async", ex);
        }
       
        return resultData;
    }

    private List<ObjectExpenseEntity> getKeyByObjectExpense(ObjectExpenseEntity objectExpense,
            List<BudgetDraftProjectEnum> budgetKey) {

        List<ObjectExpenseEntity> resultData = new ArrayList<ObjectExpenseEntity>();
        AsynchCall execCall;
        Object beanExecute;
        List<AsynchCall> asyCall = new ArrayList<AsynchCall>();

        for (BudgetDraftProjectEnum dat : budgetKey) {
            execCall = new AsynchCall();
            if (objectExpense.getClass().getCanonicalName().contains(dat.getType())) {
                beanExecute = SIIFContextBase.getAppContext().getBean(dat.getBeanName());
                try {

                    execCall.executCall(this, beanExecute,
                            dat.getServiceCall(),
                            objectExpense.getObjectExpenseId());

                } catch (Exception ex) {
                    Logger.getLogger(CeilingBudgetManagementImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                asyCall.add(execCall);
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
                    resultData.addAll(
                            (List<ObjectExpenseEntity>) ref.getResultCall());
                    asyCall.remove(i);
                    i = -1;
                }
            }
        } catch (Exception ex) {
            logComp.log(Level.SEVERE, "Error al ejecutar llamadas Async", ex);
        }

        return resultData;
    }

    private DestinationEntity getKeyByDestination(DestinationEntity destiny,
            List<BudgetDraftProjectEnum> budgetKey) {

        DestinationEntity resultData = new DestinationEntity();
        AsynchCall execCall;
        Object beanExecute;
        List<AsynchCall> asyCall = new ArrayList<AsynchCall>();

        for (BudgetDraftProjectEnum dat : budgetKey) {
            execCall = new AsynchCall();
            if (destiny.getClass().getCanonicalName().contains(dat.getType())) {
                beanExecute = SIIFContextBase.getAppContext().getBean(dat.getBeanName());
                try {

                    execCall.executCall(this, beanExecute,
                            dat.getServiceCall(),
                            destiny.getDestinationId());

                } catch (Exception ex) {
                    Logger.getLogger(CeilingBudgetManagementImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                asyCall.add(execCall);
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
                    resultData = (DestinationEntity) ref.getResultCall();
                    asyCall.remove(i);
                    i = -1;
                }
            }
        } catch (Exception ex) {
            logComp.log(Level.SEVERE, "Error al ejecutar llamadas Async", ex);
        }

        return resultData;
    }

    private FinancingSourceEntity getKeyByFinSource(FinancingSourceEntity fin,
            List<BudgetDraftProjectEnum> budgetKey) {

        FinancingSourceEntity resultData = new FinancingSourceEntity();
        AsynchCall execCall;
        Object beanExecute;
        List<AsynchCall> asyCall = new ArrayList<AsynchCall>();

        for (BudgetDraftProjectEnum dat : budgetKey) {
            execCall = new AsynchCall();
            if (fin.getClass().getCanonicalName().contains(dat.getType())) {
                beanExecute = SIIFContextBase.getAppContext().getBean(dat.getBeanName());
                try {

                    execCall.executCall(this, beanExecute,
                            dat.getServiceCall(),
                            fin.getFinancingSourceId());

                } catch (Exception ex) {
                    Logger.getLogger(CeilingBudgetManagementImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                asyCall.add(execCall);
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
                    resultData = (FinancingSourceEntity) ref.getResultCall();
                    asyCall.remove(i);
                    i = -1;
                }
            }
        } catch (Exception ex) {
            logComp.log(Level.SEVERE, "Error al ejecutar llamadas Async", ex);
        }
        return resultData;
    }

  @Override
  public Long getBudgetByUEG(DraftProjectEntity aDraftProject)
  {
    return this.theirDraftProjectDaoImpl.getBudgetByUEG(aDraftProject);
  }
    @Override
    public Long getAvailableCeilingBasics(String identitiesToserach, Long obj, String Dest) {
       return theirDraftProjectDaoImpl.getAvailableCeilingBasics(identitiesToserach, obj, Dest);
    }   
    
    /**
     * Este metodo sirve para obtener el disponible del techo presupuestal de inversion 
     * para prefichas
     * @param denpendency
     * @param objExpens
     * @param dest
     * @param financialSource
     * @return 
     */     
    @Override
    public Long getBudgetAvailableForInvPreFile(DependenceEntity denpendency)
    {
            List<BudgetDraftProjectEnum> budgetKey = new ArrayList<BudgetDraftProjectEnum>();
            BudgetDraftProjectEnum myEnum = BudgetDraftProjectEnum.valueOf("UR");
            budgetKey.clear();
            budgetKey.add(myEnum);
            List<DepencenceDto> resultData = new ArrayList<DepencenceDto>();
            resultData = getKeyByDependency(denpendency, budgetKey);
            
            if (resultData.size()==1)
            {
                DepencenceDto myURDto = resultData.get(0);
                DependenceEntity myURDependenceEntity = new DependenceEntity();
                myURDependenceEntity.setDependenceId(myURDto.getIdDependency());
                myEnum = BudgetDraftProjectEnum.valueOf("UEG");
                budgetKey.clear();
                budgetKey.add(myEnum);
                resultData = getKeyByDependency(myURDependenceEntity, budgetKey);
                String myKeyToSearch="";
                for(DepencenceDto myDepUEG: resultData)
                {
                    if (myKeyToSearch.equals(""))
                    {
                    myKeyToSearch = myDepUEG.getIdDependency().toString();
                    }
                    else
                    {
                        myKeyToSearch+= "," + myDepUEG.getIdDependency().toString();
                    }
                }
                if (myKeyToSearch.equals(""))
                {
                    return (long) 0;
                }
                 else
                {
                    return (long) theirDraftProjectDaoImpl.getInvPreFileAvailableCeilingByUEGIds(myKeyToSearch);                    
                }
            }
            else
            {
                return (long) 0;
            }
    }
}
