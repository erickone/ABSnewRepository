/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CompCeilingBudget
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

import com.abs.siif.base.management.ManagementBase;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.dto.ValidationNameEnum;
import java.util.List;

/**
 *
 * @author Israel Ruiz
 */
public abstract class CompCeilingBudget extends ManagementBase implements ValidateCompImport {

    protected ValidationNameEnum name;

    /**
     * Obtiene el nomnbre
     *
     * @return
     */
    @Override
    public final ValidationNameEnum getNameValidator() {
        return name;
    }

    /**
     * Coloca el nombre del validador
     *
     * @param name
     */
    @Override
    public final void setNameValidator(ValidationNameEnum name) {
        this.name = name;
    }

    @Override
    public void addValidator(ValidateCompImport leaf) {
        // No requiere implementación
    }

    /**
     * @return the entitiesValid
     */
    @Override
    public List<CellCeilingBudgetDto> getEntitiesValid() {
        return null;
    }
}
