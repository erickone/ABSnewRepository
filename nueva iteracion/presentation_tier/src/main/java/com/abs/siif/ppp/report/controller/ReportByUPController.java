/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ReportByUPController
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.ppp.report.controller;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.management.DependenceManagement;
import com.abs.siif.ppp.report.api.controller.ReportByUPControllerApi;
import com.abs.siif.programming.dto.UrlConnectionReportDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FENIX-02
 */
@Scope("session")
@Controller("reportByUPController")
public class ReportByUPController extends SIIFControllerBase
        implements Serializable,ReportByUPControllerApi{
    
    @Resource(name = "dependenceManagement")
    private transient DependenceManagement itsDependenceManagement;
    
    private String imprimirReporte;
    private Long uniPresupuestalId;
    private List<DependenceEntity> listOfBudgetDependences;
    
    @Override
    public void changeReport(){
        UrlConnectionReportDTO objUrlServer = (UrlConnectionReportDTO) SIIFContextBase.getParamContext(KeyContextEnum.URLSERVERREPORT);
        objUrlServer.setTodo(this.getMessage("ppp.progr.techppptalVsObjGasto.report.todoTrue"));
        objUrlServer.setTipoFicha(this.getMessage("ppp.progr.techppptalVsObjGasto.report.byUP"));
        objUrlServer.setIddependencia(uniPresupuestalId.toString());
        setImprimirReporte(objUrlServer.getUrlDocumentoWithDependencie());
    }

    @Override
    public String getImprimirReporte()
    {
        return imprimirReporte;
    }

    @Override
    public void setImprimirReporte(String imprimirReporte)
    {
        this.imprimirReporte = imprimirReporte;
    }

    @Override
    public Long getUniPresupuestalId()
    {
        return uniPresupuestalId;
    }

    @Override
    public void setUniPresupuestalId(Long uniPresupuestalId)
    {
        this.uniPresupuestalId = uniPresupuestalId;
    }

    @Override
    public List<DependenceEntity> getListOfBudgetDependences()
    {
        this.setListOfBudgetDependences(this.itsDependenceManagement.getViewDepIsBudgetUnit());
        return listOfBudgetDependences;
    }

    @Override
    public void setListOfBudgetDependences(List<DependenceEntity> listOfBudgetDependences)
    {
        this.listOfBudgetDependences = listOfBudgetDependences;
    }
    
}
