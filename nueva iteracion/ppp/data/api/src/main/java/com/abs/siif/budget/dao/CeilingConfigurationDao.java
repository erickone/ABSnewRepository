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
import com.abs.siif.planning.data.SaveType;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface CeilingConfigurationDao extends SIIFBaseDao<CeilingConfigurationEntity, Long>
{
    public List<CeilingConfigurationEntity> getAllCeilingConfigurations();
    
    public Long saveCeilingConfigWithBudgetKeyItems(CeilingConfigurationEntity aCeilingConfigurationEntity, SaveType aSaveType);
    
    public boolean isCeilingConfigKeyValid(String aKey);
    
    public void deleteCeiling(CeilingConfigurationEntity aCeilingConfigId);
    
    CeilingConfigurationEntity getCeilingConfigurationByYear(int aBudgetingYear);
    
    public List<CeilingConfigurationEntity> getAllCeilingConfigurationByYear(int aBudgetingYear);
}
