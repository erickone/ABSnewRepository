/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyAdditionalDao
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

import com.abs.siif.budget.dto.BudgetKeyAdditionalDto;
import com.abs.siif.budget.entities.BudgetKeyAdditionalEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface BudgetKeyAdditionalManagement  {
    
    /*
     * Retorna todos los registros que hagan match con la clave que se envie
     * atravez del dto (anAdditionalDto.budgetKeyAdditional.budgetKeyAdditionalKey)
     */
    Collection<BudgetKeyAdditionalEntity> 
            getBudgetKeyAdditionalsByObjectExpenseAndDependency
            (BudgetKeyAdditionalDto anAdditionalDto);
    
     /*
     * Retorna la suma de los montos de 
     * todos los registros que hagan match con la clave que se envie
     * atravez del dto (anAdditionalDto.budgetKeyAdditional.budgetKeyAdditionalKey)
     */
    double getSumByBudgetKey(BudgetKeyAdditionalDto anAdditionalDto);
    
    /*
     * regresa un presupuesto adicional por id, atraves del dto
     * (anAdditionalDto.budgetKeyAdditional.budgetKeyAdditionalId)
     */
    BudgetKeyAdditionalEntity getBudgetKeyAdditionalByIdentity(
            BudgetKeyAdditionalDto anAdditionalDto);
    
    /*
     * Persiste el elemento mandado se encarga de realizar un update o insert este
     * dependiendo si la propiedad (budgetKeyAdditional.budgetKeyAdditionalId)
     * si esta es mayor a 0 realiza un update asumiendo que el contenido de 
     * budgetKeyAdditional.budgetKeyAdditionalId) es el id del registro, si es
     * igual o menor a 0, realiza un insert.
     */
    BudgetKeyAdditionalEntity persist(BudgetKeyAdditionalEntity anEntity);
    
    /*
     * elimina el registro mandado mandatorio la propiedad
     * budgetKeyAdditional.budgetKeyAdditionalId)
     */
    public void delete(BudgetKeyAdditionalEntity anEntity);
    
    /**
     * Elimina el adicional por medio de la clave presupuestal
     */
    
    void deleteAditional(String aBudgetKey);
    
    BudgetKeyAdditionalEntity getAdditional(String aBudgetKey);
}
