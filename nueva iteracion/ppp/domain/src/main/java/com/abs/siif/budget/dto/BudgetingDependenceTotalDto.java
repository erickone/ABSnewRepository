/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dto;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Erick Leija
 */
public class BudgetingDependenceTotalDto implements Serializable, Comparable<BudgetingDependenceTotalDto>
{
    public NumberFormat numberValidator = NumberFormat.getInstance(Locale.US);
    
    private Long    itsDependenceId;
    private String  itsDependenceKey;
    private String  itsDependenceName;
    private String    itsDependenceInitialTotal;
    private String    itsDependenceAdditionalTotal;
    private String    itsDependenceSummatoryTotal;

    public BudgetingDependenceTotalDto(Long itsDependenceId, String itsDependenceKey, 
            String itsDependenceName, Long itsDependenceInitialTotal, Long itsDependenceAdditionalTotal,
            Long itsDependenceSummatoryTotal)
    {
        this.itsDependenceId = itsDependenceId;
        this.itsDependenceKey = itsDependenceKey;
        this.itsDependenceName = itsDependenceName;
        this.itsDependenceInitialTotal = numberValidator.format(itsDependenceInitialTotal);
        this.itsDependenceAdditionalTotal = numberValidator.format(itsDependenceAdditionalTotal);
        this.itsDependenceSummatoryTotal = numberValidator.format(itsDependenceSummatoryTotal);
    }

    public Long getItsDependenceId()
    {
        return itsDependenceId;
    }

    public void setItsDependenceId(Long itsDependenceId)
    {
        this.itsDependenceId = itsDependenceId;
    }

    public String getItsDependenceKey()
    {
        return itsDependenceKey;
    }

    public void setItsDependenceKey(String itsDependenceKey)
    {
        this.itsDependenceKey = itsDependenceKey;
    }

    public String getItsDependenceName()
    {
        return itsDependenceName;
    }

    public void setItsDependenceName(String itsDependenceName)
    {
        this.itsDependenceName = itsDependenceName;
    }

    public String getItsDependenceInitialTotal()
    {
        return itsDependenceInitialTotal;
    }


    public String getItsDependenceAdditionalTotal()
    {
        return itsDependenceAdditionalTotal;
    }



    public String getItsDependenceSummatoryTotal()
    {
        return itsDependenceSummatoryTotal;
    }

    
    
    
    
    

    @Override
    public int compareTo(BudgetingDependenceTotalDto o)
    {
        if(itsDependenceId != null && o.getItsDependenceId()!= null){
            return itsDependenceId.compareTo( o.getItsDependenceId());
        }
        return -1;
    }
    
}
