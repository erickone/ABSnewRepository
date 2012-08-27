/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dto;

import java.io.Serializable;

/**
 *
 * @author Erick Leija
 */
public class BudgetingSummaryDto implements Serializable
{
    private Long itsBudgetingSummaryId;
    private String itsBudgetingSummaryDescription;
    private int itsBudgetingSummaryChapter;
    private String itsBudgetingSummaryInitial;
    private String itsBudgetingSummaryAditional;
    private String itsBudgetingSummarySum;

    public BudgetingSummaryDto
            (Long aBudgetingSummaryId,String abudgetingSummaryDescription, 
            int abudgetingSummaryChapter, 
            String abudgetingSummaryInitial, 
            String abudgetingSummaryAditional, 
            String abudgetingSummarySum)
    {
        this.itsBudgetingSummaryId = aBudgetingSummaryId;
        this.itsBudgetingSummaryDescription = abudgetingSummaryDescription;
        this.itsBudgetingSummaryChapter = abudgetingSummaryChapter;
        this.itsBudgetingSummaryInitial = abudgetingSummaryInitial;
        this.itsBudgetingSummaryAditional = abudgetingSummaryAditional;
        this.itsBudgetingSummarySum = abudgetingSummarySum;
    }

    public BudgetingSummaryDto()
    {
        
    }
    public Long getItsBudgetingSummaryId()
    {
        return itsBudgetingSummaryId;
    }

    public void setItsBudgetingSummaryId(Long itsBudgetingSummaryId)
    {
        this.itsBudgetingSummaryId = itsBudgetingSummaryId;
    }
    
    

    public String getItsBudgetingSummaryAditional()
    {
        return itsBudgetingSummaryAditional;
    }

    public void setItsBudgetingSummaryAditional(String itsBudgetingSummaryAditional)
    {
        this.itsBudgetingSummaryAditional = itsBudgetingSummaryAditional;
    }

    public int getItsBudgetingSummaryChapter()
    {
        return itsBudgetingSummaryChapter;
    }

    public void setItsBudgetingSummaryChapter(int itsBudgetingSummaryChapter)
    {
        this.itsBudgetingSummaryChapter = itsBudgetingSummaryChapter;
    }

    public String getItsBudgetingSummaryDescription()
    {
        return itsBudgetingSummaryDescription;
    }

    public void setItsBudgetingSummaryDescription(String itsBudgetingSummaryDescription)
    {
        this.itsBudgetingSummaryDescription = itsBudgetingSummaryDescription;
    }

    public String getItsBudgetingSummaryInitial()
    {
        return itsBudgetingSummaryInitial;
    }

    public void setItsBudgetingSummaryInitial(String itsBudgetingSummaryInitial)
    {
        this.itsBudgetingSummaryInitial = itsBudgetingSummaryInitial;
    }

    public String getItsBudgetingSummarySum()
    {
        return itsBudgetingSummarySum;
    }

    public void setItsBudgetingSummarySum(String itsBudgetingSummarySum)
    {
        this.itsBudgetingSummarySum = itsBudgetingSummarySum;
    }

    
    
}
