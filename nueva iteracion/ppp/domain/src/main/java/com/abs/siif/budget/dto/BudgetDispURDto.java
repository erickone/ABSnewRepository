/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dto;

import com.abs.siif.budget.entities.BudgetKeyAndDraftProjectFramingEntity;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import com.abs.siif.budget.entities.FinancingSourceEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import java.io.Serializable;

/**
 *
 * @author Israel Ruiz
 */
public class BudgetDispURDto implements Serializable {

    private DependenceEntity dependenceEntity;

    private ObjectExpenseEntity objectExpenseEntity;

    private String desting;

    private FinancingSourceEntity financingSourceEntity;

    private DepencenceDto itsUrDependence;

    private Long ceilingBudgetObjectExpense;
    
    private Long ceilingBudgetObjectExpenseUsed;
    
    private boolean isAdditionalAmount;
    
    private Long idDraftProject = 0L;
    
    private BudgetKeyEntity myBudget;  
    
    private BudgetKeyAndDraftProjectFramingEntity myEntityToFraming;

    public Long getCeilingBudgetObjectExpenseUsed() {
        return ceilingBudgetObjectExpenseUsed;
    }

    public void setCeilingBudgetObjectExpenseUsed(Long ceilingBudgetObjectExpenseUsed) {
        this.ceilingBudgetObjectExpenseUsed = ceilingBudgetObjectExpenseUsed;
    }
    

    /**
     * @return the dependenceEntity
     */
    public DependenceEntity getDependenceEntity() {
        return dependenceEntity;
    }

    /**
     * @param dependenceEntity the dependenceEntity to set
     */
    public void setDependenceEntity(DependenceEntity dependenceEntity) {
        this.dependenceEntity = dependenceEntity;
    }

    /**
     * @return the objectExpenseEntity
     */
    public ObjectExpenseEntity getObjectExpenseEntity() {
        return objectExpenseEntity;
    }

    /**
     * @param objectExpenseEntity the objectExpenseEntity to set
     */
    public void setObjectExpenseEntity(ObjectExpenseEntity objectExpenseEntity) {
        this.objectExpenseEntity = objectExpenseEntity;
    }

    /**
     * @return the desting
     */
    public String getDesting() {
        return desting;
    }

    /**
     * @param desting the desting to set
     */
    public void setDesting(String desting) {
        this.desting = desting;
    }

    /**
     * @return the financingSourceEntity
     */
    public FinancingSourceEntity getFinancingSourceEntity() {
        return financingSourceEntity;
    }

    /**
     * @param financingSourceEntity the financingSourceEntity to set
     */
    public void setFinancingSourceEntity(FinancingSourceEntity financingSourceEntity) {
        this.financingSourceEntity = financingSourceEntity;
    }

    public DepencenceDto getItsUrDependence() {
        return itsUrDependence;
    }

    public void setItsUrDependence(DepencenceDto itsUrDependence) {
        this.itsUrDependence = itsUrDependence;
    }

    public Long getCeilingBudgetObjectExpense() {
        return ceilingBudgetObjectExpense;
    }

    public void setCeilingBudgetObjectExpense(Long ceilingBudgetObjectExpense) {
        this.ceilingBudgetObjectExpense = ceilingBudgetObjectExpense;
    }

    /**
     * @return the isAditionalBudgeting
     */
    public boolean isAdditionalAmount() {
        return isAdditionalAmount;
    }

    /**
     * @param isAditionalBudgeting the isAditionalBudgeting to set
     */
    public void setIsAdditionalAmount(boolean isAditionalBudgeting) {
        this.isAdditionalAmount = isAditionalBudgeting;
    }

    /**
     * @return the idDraftProject
     */
    public Long getIdDraftProject() {
        return idDraftProject;
    }

    /**
     * @param idDraftProject the idDraftProject to set
     */
    public void setIdDraftProject(Long idDraftProject) {
        this.idDraftProject = idDraftProject;
    }

    /**
     * @return the myBudget
     */
    public BudgetKeyEntity getMyBudget() {
        return myBudget;
    }

    /**
     * @param myBudget the myBudget to set
     */
    public void setMyBudget(BudgetKeyEntity myBudget) {
        this.myBudget = myBudget;
    }

    /**
     * @return the myEntityToFraming
     */
    public BudgetKeyAndDraftProjectFramingEntity getMyEntityToFraming() {
        return myEntityToFraming;
    }

    /**
     * @param myEntityToFraming the myEntityToFraming to set
     */
    public void setMyEntityToFraming(BudgetKeyAndDraftProjectFramingEntity myEntityToFraming) {
        this.myEntityToFraming = myEntityToFraming;
    }
    
    
}
