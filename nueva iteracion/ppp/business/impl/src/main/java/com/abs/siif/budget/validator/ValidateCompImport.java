/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.dto.ValidationNameEnum;
import java.util.List;

/**
 *
 * @author Israel Ruiz
 */
public interface ValidateCompImport {

    void addValidator(ValidateCompImport leaf);

    /**
     * Ejecuta la revisión sobre un grupo especifico datos que se relaciona a
     * layout de la carga de techos
     *
     * @param toValidateSets
     * @return
     */
    List<CellCeilingBudgetDto> executeDataValidation(List<CellCeilingBudgetDto> toValidateSets);

    /**
     * Metodo que se encarga de ralizar la validacion de datos sobre datos que
     * corresponde a los techos
     *
     * @param toValidateSets
     * @return
     */
    List<CellCeilingBudgetDto> executeValidation(List<List<CellCeilingBudgetDto>> toValidateSets);

    /**
     * @return the entitiesValid
     */
    List<CellCeilingBudgetDto> getEntitiesValid();

    /**
     * Obtiene el nomnbre
     *
     * @return
     */
    ValidationNameEnum getNameValidator();

    /**
     * Coloca el nombre del validador
     *
     * @param name
     */
    void setNameValidator(ValidationNameEnum name);
    
}
