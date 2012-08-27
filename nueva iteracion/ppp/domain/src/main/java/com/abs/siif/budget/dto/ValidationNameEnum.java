/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ValidationNameEnum
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.dto;

/**
 * Este enum sirve para que 
 * se realice la busqueda de validadores para la carga de techos
 * el primer parametro del validador corresponde al nombre del
 * bean y el segundo parametro al nombre del elemento al cual 
 * refiere la validacion en la tabla de elementos
 * @author Israel Ruiz
 */
public enum ValidationNameEnum {
    VALIDATION_UNIQUE_KEY("budgetKeyValidate","validateData"),//No requiere mapeo
    DEPENDENCY("dependencyValidate",""),//No requiere mapeo
    DEPENDENCY_UEG("dependencyValidateUeg","UEG"),
    DEPENDENCY_UR("dependencyValidateUR","UR"),
    DEPENDENCY_DEP("dependencyValidate","DEPPROGR"),
    DEPENDENCY_UP("dependencyValidateUP","UP"),
    DES_EXPENSE("destinationExpenseValidate","Destino del Gasto"),
    DRAFT_PROJECT("draftProjectValidate", ""),
    FINANCE_SOURCE("financingSourceValidate","FFTO"),
    OBJECT_EXPENCE("objectExpenseKeyValidator","Objeto del Gasto"),
    PROGRAMMING("programmingValidate","PROY"),
    OBJETIVE("objetiveValidator","PROG");
    
    private String beanName;
    private String budgetKeyName;
    
    private ValidationNameEnum(String beanName, String budgetKeyName){
        this.beanName = beanName;
        this.budgetKeyName = budgetKeyName;
    }
    public String getBeanName(){
        return beanName;
    }

    public String getBudgetKeyName() {
        return budgetKeyName;
    }

    public static ValidationNameEnum getValueOfBudgetKeyName(String budgetKeyName){
        ValidationNameEnum[] elements = ValidationNameEnum.values();
        ValidationNameEnum result = null;
        for(ValidationNameEnum elemen : elements){
            if(elemen.getBudgetKeyName().equals(budgetKeyName)){
                result = elemen;
                break;   
            }
        }
        return result;
    }
}
