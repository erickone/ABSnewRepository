/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dto;

import com.abs.siif.support.FormatNumber;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Erick Leija
 */
public class BudgetingKeysDto implements Serializable, Comparable<BudgetingKeysDto>
{
    private Long itsBudgetingKeyId;
    private String itsProgrammaticKey;
    private String itsBudgetingKeyObjectExpense;
    private String itsBudgetingKeyDestination;
    private String itsBudgetingKeyAmount="0";
    private String itsAditionalBudgetingKeyAmount = "0";
    private boolean itsModifiedBudget;
    public NumberFormat numberValidator = NumberFormat.getInstance(Locale.US);

    public BudgetingKeysDto(){
        
    }
    
    public BudgetingKeysDto(Long itsBudgetingKeyId, String itsProgrammaticKey, String itsBudgetingKeyObjectExpense, 
            String itsBudgetingKeyAmount, boolean itsModifiedBudget, String itsBudgetingDestination)
    {
        this.itsBudgetingKeyId = itsBudgetingKeyId;
        this.itsProgrammaticKey = itsProgrammaticKey;
        this.itsBudgetingKeyObjectExpense = itsBudgetingKeyObjectExpense;
        this.itsBudgetingKeyAmount = itsBudgetingKeyAmount;
        this.itsModifiedBudget = itsModifiedBudget;
        this.itsBudgetingKeyDestination = itsBudgetingDestination;
    }
    
    
    

    public String getItsBudgetingKeyAmount()
    {
        return itsBudgetingKeyAmount;
    }

    public void setItsBudgetingKeyAmount(String itsBudgetingKeyAmount)
    {
        this.itsBudgetingKeyAmount = itsBudgetingKeyAmount;
    }

    public Long getItsBudgetingKeyId()
    {
        return itsBudgetingKeyId;
    }

    public void setItsBudgetingKeyId(Long itsBudgetingKeyId)
    {
        this.itsBudgetingKeyId = itsBudgetingKeyId;
    }

    public String getItsBudgetingKeyObjectExpense()
    {
        return itsBudgetingKeyObjectExpense;
    }

    public void setItsBudgetingKeyObjectExpense(String itsBudgetingKeyObjectExpense)
    {
        this.itsBudgetingKeyObjectExpense = itsBudgetingKeyObjectExpense;
    }

    public boolean getItsModifiedBudget()
    {
        return itsModifiedBudget;
    }

    public void setItsModifiedBudget(boolean itsModifiedBudget)
    {
        this.itsModifiedBudget = itsModifiedBudget;
    }

    public String getItsProgrammaticKey()
    {
        return itsProgrammaticKey;
    }

    public void setItsProgrammaticKey(String itsProgrammaticKey)
    {
        this.itsProgrammaticKey = itsProgrammaticKey;
    }

    public String getItsBudgetingKeyDestination()
    {
        return itsBudgetingKeyDestination;
    }

    public void setItsBudgetingKeyDestination(String itsBudgetingKeyDestination)
    {
        this.itsBudgetingKeyDestination = itsBudgetingKeyDestination;
    }

    /**
     * @return the itsAditionalBudgetingKeyAmount
     */
    public String getItsAditionalBudgetingKeyAmount() {
        return itsAditionalBudgetingKeyAmount;
    }

    /**
     * @param itsAditionalBudgetingKeyAmount the itsAditionalBudgetingKeyAmount to set
     */
    public void setItsAditionalBudgetingKeyAmount(String itsAditionalBudgetingKeyAmount) {
        this.itsAditionalBudgetingKeyAmount = itsAditionalBudgetingKeyAmount;
    }

    /**
     * @return the itsTotalBudgetingKeyAmount
     */
    public String getItsTotalBudgetingKeyAmount() { 
        long additional =Long.parseLong(FormatNumber.removeFormat(itsAditionalBudgetingKeyAmount));
        long mount = Long.parseLong(FormatNumber.removeFormat(itsBudgetingKeyAmount));
        return numberValidator.format(additional+mount); 
    }

    @Override
    public int compareTo(BudgetingKeysDto o) {
        if(itsProgrammaticKey != null && o.getItsProgrammaticKey()!= null){
            return itsProgrammaticKey.compareTo( o.getItsProgrammaticKey());
        }
        return -1;
    }
    
    
}
