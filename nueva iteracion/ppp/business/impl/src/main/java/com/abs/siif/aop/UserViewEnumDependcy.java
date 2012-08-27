/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  UserViewEnumDependcy
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.aop;

/**
 *
 * @author Israel Ruiz
 */
public enum UserViewEnumDependcy {

    IS_BUDGET_LEVEL("dependencyLevelIsBudgetUnit"),
    HAS_BUDGET_LEVEL("dependencyLevelHasBudget"),
    IS_EXECUTION_LEVEL("dependencyLevelIsExecutionUnit"),
    IS_RESP_UNIT_LEVEL("dependencyLevelIsResponsibleUnit"),
    HAS_PROGRAMMING_LEVEL("dependencyLevelHasProgramming"),
    HAS_INSTITUTIONAL_PLAN("dependencyLevelHasInstitutionalPlan"),
    IS_BRANCH("dependencyLevelIsBranch"),
    IS_SECTOR("dependencyLevelIsSector"),
    HAS_OBJECTIVE_FRAME("dependencyLevelHasObjectiveFraming");
    
    private String level;

    private UserViewEnumDependcy(String level) {
        this.level = level;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }
}
