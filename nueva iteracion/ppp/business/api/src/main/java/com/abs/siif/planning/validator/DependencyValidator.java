/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependencyValidator
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.validator;

import com.abs.siif.planning.entities.DependenceEntity;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface DependencyValidator {
    
    public boolean notEmptyAnyAttributes(DependenceEntity anEntity);
    
    /*
     * al entidad debe contener la definicion de la clave presupuestal
     * (getBudgetKeyDefinition) y el nivel al que posiblemente va a pertenecer 
     * la entidad (getDependenceLevel)
     */
    public boolean notExceedLastLevel(DependenceEntity anEntity);
}
