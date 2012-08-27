/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dto;

import java.io.Serializable;

/**
 *
 * @author absvalenzuela
 */
public class ValidateDeleteCeilingBudgetDto implements Serializable{
    
    private boolean deleteCeilingBudget;

    public boolean isDeleteCeilingBudget() {
        return deleteCeilingBudget;
    }

    public void setDeleteCeilingBudget(boolean deleteCeilingBudget) {
        this.deleteCeilingBudget = deleteCeilingBudget;
    }
}
