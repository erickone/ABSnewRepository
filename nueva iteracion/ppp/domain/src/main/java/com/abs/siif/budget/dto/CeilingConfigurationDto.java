/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingBudgetKeyDto
 *  Purpose:  Contains the information obtained from the CeilingConfigutarionEnt
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.dto;

import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public class CeilingConfigurationDto implements Serializable{

    private Long ceilingConfigId;
    private String ceilingConfigName;
    private String ceilingConfigDesc;
    private boolean isCeilingConfigActive;
    private BudgetKeyDefinitionEntity budgetKeyDefinition;
    private List<BudgetKeyItemEntity> budgetKeyItems;

    public List<BudgetKeyItemEntity> getBudgetKeyItems()
    {
        return budgetKeyItems;
    }

    public void setBudgetKeyItems(List<BudgetKeyItemEntity> budgetKeyItems)
    {
        this.budgetKeyItems = budgetKeyItems;
    }

    public String getCeilingConfigDesc()
    {
        return ceilingConfigDesc;
    }

    public void setCeilingConfigDesc(String ceilingConfigDesc)
    {
        this.ceilingConfigDesc = ceilingConfigDesc;
    }

    public Long getCeilingConfigId()
    {
        return ceilingConfigId;
    }

    public void setCeilingConfigId(Long ceilingConfigId)
    {
        this.ceilingConfigId = ceilingConfigId;
    }

    public String getCeilingConfigName()
    {
        return ceilingConfigName;
    }

    public void setCeilingConfigName(String ceilingConfigName)
    {
        this.ceilingConfigName = ceilingConfigName;
    }

    public boolean isIsCeilingConfigActive()
    {
        return isCeilingConfigActive;
    }

    public void setIsCeilingConfigActive(boolean isCeilingConfigActive)
    {
        this.isCeilingConfigActive = isCeilingConfigActive;
    }

    public BudgetKeyDefinitionEntity getBudgetKeyDefinition()
    {
        return budgetKeyDefinition;
    }

    public void setBudgetKeyDefinition(BudgetKeyDefinitionEntity budgetKeyDefinition)
    {
        this.budgetKeyDefinition = budgetKeyDefinition;
    }
    
}
