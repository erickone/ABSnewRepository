/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetDraftProjectEnum
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.support;

/**
 *
 * @author Israel Ruiz
 */
public enum BudgetDraftProjectEnum {
    
    UEG("DependenceEntity","DependenceDao","getDependciesIsExecUnitByDependIdRelated"),
    UR("DependenceEntity","DependenceDao","getDependciesIsRespUnitByDependIdRelated"),
    UP("DependenceEntity","DependenceDao","getDependciesIsBudgetByDependIdRelated"),
    OBJG("ObjectExpenseEntity","objectExpenseDao","getObjectExpenseBySpecificParByObjectIdRelated"),
    DESTINOOBJG("DestinationEntity","destinationDao","getDestinyById"),
    FFTO("FinancingSourceEntity","financingSourceDao","getFinancingSourceById");
    
    private String beanName;
    private String serviceCall;
    private String type;
    
    public String getBeanName() {
        return beanName;
    }
    

    public String getServiceCall() {
        return serviceCall;
    }
    
    public String getType() {
        return type;
    }
    private BudgetDraftProjectEnum(String type,String beanName, String serviceCall){
        this.beanName = beanName;
        this.serviceCall = serviceCall;
        this.type = type;
        
    }
    
}
