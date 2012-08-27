/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingConfigurationDao
 *  Purpose:  Define los metodos para acceso a datos, que se relacionan a 
 *            la tabla de la configuracion del techo presupuestal.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.budget.entities.CeillingBudgetEntity;

/**
 *
 * @author abs71
 */
public interface CeillingBudgetDao extends SIIFBaseDao<CeilingConfigurationEntity, Long> {
    
    /**
     * Extre el techo para una clave dada
     * @param cve clave a buscar en techos
     * @return entity de techos
     */
    CeillingBudgetEntity getTechoByCvePresupuestal(String cve);
}
