/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingConfigValidator
 *  Purpose:  Validator for Business operations regarding Ceiling Configuration.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.validator;

import com.abs.siif.budget.dao.CeilingConfigurationDao;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author FENIX-02
 */
@Component("ceilingConfigValidator")
public class CeilingConfigValidatorImpl implements CeilingConfigValidator{
    
    @Resource(name = "ceilingConfigurationDao")
    private CeilingConfigurationDao itsCeilingConfigurationDao;
    
    /**
     * Este metodo valida si la clave del FCEntity recibido no existe ya en BD.
     * @param anFCLevel
     * @return 
     */
    @Override
    public boolean isCeilingConfigKeyValid(CeilingConfigurationEntity anCeilingConfig)
    {
        boolean isValidKey;
        isValidKey = itsCeilingConfigurationDao.isCeilingConfigKeyValid(
                anCeilingConfig.getCeilingConfigName());
        return isValidKey;
    }

}
