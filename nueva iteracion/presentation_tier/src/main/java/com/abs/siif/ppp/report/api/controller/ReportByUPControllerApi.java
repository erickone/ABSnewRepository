/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ReportByUPControllerApi
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.report.api.controller;

import com.abs.siif.planning.entities.DependenceEntity;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface ReportByUPControllerApi
{
     public void changeReport();

    public String getImprimirReporte();

    public void setImprimirReporte(String imprimirReporte);

    public Long getUniPresupuestalId();

    public void setUniPresupuestalId(Long uniPresupuestalId);

    public List<DependenceEntity> getListOfBudgetDependences();

    public void setListOfBudgetDependences(List<DependenceEntity> listOfBudgetDependences);
}
