/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyAdditionalEntity
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.entities;

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.security.entities.UserEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */

@Entity
@Table(name="siifpppcvepptaladicional")
public class BudgetKeyAdditionalEntity implements Serializable,
        Comparable<BudgetKeyAdditionalEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idcvepptaladicional")
    private Long budgetKeyAdditionalId;
    
    @Column(name = "clave", length = 36)
    private String budgetKeyAdditionalKey;
    
    @Column(name = "monto")
    private Double budgetKeyAmount;
    
      
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idobjetogasto")
    private ObjectExpenseEntity budgetKeyAdditionalObjectExpense;
 
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="iddependencia")
    private DependenceEntity bugKeyAdditionalDependency;
    
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idusuario")
    private UserEntity budgetKeyAdditionalUser;
    
    @Column(name = "fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date budgetKeyAdditionalDate;
     
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idcvepresupuestal")
    private BudgetKeyEntity budgetKeyAdditional;
    
    @Column(name="justificacionpptoadic",length=550)
    private String budgetKeyAdditionalJustification;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idanteproyecto")
    private DraftProjectEntity budgetKeyAdditionalDraftProject;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idpreficha")
    private InvPreFileEntity budgetKeyAdditionalPreFile;

    public BudgetKeyEntity getBudgetKeyAdditional() {
        return budgetKeyAdditional;
    }

    public void setBudgetKeyAdditional(BudgetKeyEntity budgetKeyAdditional) {
        this.budgetKeyAdditional = budgetKeyAdditional;
    }

    public ObjectExpenseEntity getBudgetKeyAdditionalObjectExpense() {
        return budgetKeyAdditionalObjectExpense;
    }

    public void setBudgetKeyAdditionalObjectExpense(ObjectExpenseEntity budgetKeyAdditionalObjectExpense) {
        this.budgetKeyAdditionalObjectExpense = budgetKeyAdditionalObjectExpense;
    }

    public UserEntity getBudgetKeyAdditionalUser() {
        return budgetKeyAdditionalUser;
    }

    public void setBudgetKeyAdditionalUser(UserEntity budgetKeyAdditionalUser) {
        this.budgetKeyAdditionalUser = budgetKeyAdditionalUser;
    }

    public DependenceEntity getBugKeyAdditionalDependency() {
        return bugKeyAdditionalDependency;
    }

    public void setBugKeyAdditionalDependency(DependenceEntity bugKeyAdditionalDependency) {
        this.bugKeyAdditionalDependency = bugKeyAdditionalDependency;
    }

    
    

    public Date getBudgetKeyAdditionalDate() {
        return budgetKeyAdditionalDate;
    }

    public void setBudgetKeyAdditionalDate(Date budgetKeyAdditionalDate) {
        this.budgetKeyAdditionalDate = budgetKeyAdditionalDate;
    }

    public Long getBudgetKeyAdditionalId() {
        return budgetKeyAdditionalId;
    }

    public void setBudgetKeyAdditionalId(Long budgetKeyAdditionalId) {
        this.budgetKeyAdditionalId = budgetKeyAdditionalId;
    }

    public String getBudgetKeyAdditionalJustification() {
        return budgetKeyAdditionalJustification;
    }

    public void setBudgetKeyAdditionalJustification(String budgetKeyAdditionalJustification) {
        this.budgetKeyAdditionalJustification = budgetKeyAdditionalJustification;
    }

    public String getBudgetKeyAdditionalKey() {
        return budgetKeyAdditionalKey;
    }

    public void setBudgetKeyAdditionalKey(String budgetKeyAdditionalKey) {
        this.budgetKeyAdditionalKey = budgetKeyAdditionalKey;
    }

    

    public Double getBudgetKeyAmount() {
        return budgetKeyAmount;
    }

    public void setBudgetKeyAmount(Double budgetKeyAmount) {
        this.budgetKeyAmount = budgetKeyAmount;
    }

    public DraftProjectEntity getBudgetKeyAdditionalDraftProject()
    {
        return budgetKeyAdditionalDraftProject;
    }

    public void setBudgetKeyAdditionalDraftProject(DraftProjectEntity budgetKeyAdditionalDraftProject)
    {
        this.budgetKeyAdditionalDraftProject = budgetKeyAdditionalDraftProject;
    }

    public InvPreFileEntity getBudgetKeyAdditionalPreFile()
    {
        return budgetKeyAdditionalPreFile;
    }

    public void setBudgetKeyAdditionalPreFile(InvPreFileEntity budgetKeyAdditionalPreFile)
    {
        this.budgetKeyAdditionalPreFile = budgetKeyAdditionalPreFile;
    }

    

    @Override
    public int compareTo(BudgetKeyAdditionalEntity anOtherEntity) {
        return this.budgetKeyAdditionalId.compareTo(
                anOtherEntity.budgetKeyAdditionalId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BudgetKeyAndDraftProjectFramingEntity) {
            return this.budgetKeyAdditionalId.equals(
                    ((BudgetKeyAdditionalEntity) obj).budgetKeyAdditionalId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.budgetKeyAdditionalId != null
                ? this.budgetKeyAdditionalId.hashCode() : 0);
        return hash;
    }
}
