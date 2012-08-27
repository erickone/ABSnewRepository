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
public class FinancingSourceDto  implements Serializable
{
    private Long financingSourceId;
    private String financingSourceDesc;
    private String financingSourceKey;

    public FinancingSourceDto(Long aFinancingSourceId, String aFinancingSourceDesc, String aFinancingSourceKey)
    {
        this.financingSourceId = aFinancingSourceId;
        this.financingSourceDesc = aFinancingSourceDesc;
        this.financingSourceKey = aFinancingSourceKey;
    }

    public String getFinancingSourceDesc()
    {
        return financingSourceDesc;
    }

    public void setFinancingSourceDesc(String financingSourceDesc)
    {
        this.financingSourceDesc = financingSourceDesc;
    }

    public Long getFinancingSourceId()
    {
        return financingSourceId;
    }

    public void setFinancingSourceId(Long financingSourceId)
    {
        this.financingSourceId = financingSourceId;
    }

    public String getFinancingSourceKey()
    {
        return financingSourceKey;
    }

    public void setFinancingSourceKey(String financingSourceKey)
    {
        this.financingSourceKey = financingSourceKey;
    }
}
