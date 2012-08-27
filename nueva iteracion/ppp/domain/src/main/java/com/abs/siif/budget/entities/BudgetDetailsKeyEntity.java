/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetDetailsKeyEntity
 *  Purpose:  [Esta entidad lleva el guarda los id's reales de los elementos
 * con los que se conformo la clave presupuestal, debe existir una serie de item
 * por cada registro guardado de BudgetKeyEntity, la serie de items dependen de
 * la configuracion en BudgetKeyConfigurationEntity]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.entities;
 
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name="siifpppdetallecvepptal")
public class BudgetDetailsKeyEntity  
implements  Serializable, Comparable<BudgetDetailsKeyEntity> {
    
    @Id
     @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "iddetallecvepptal")
    private Long budgetDetailsKeyId;
    
    @Column(name="idreal")
    private Long budgetDetailsKeyRealId;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idcvepresupuestal")
    private BudgetKeyEntity budgetKey;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idcvecfg")
    private BudgetKeyConfigurationEntity BudgetKeyConfiguration;

    public BudgetKeyConfigurationEntity getBudgetKeyConfiguration() {
        return BudgetKeyConfiguration;
    }

    public void setBudgetKeyConfiguration(BudgetKeyConfigurationEntity BudgetKeyConfiguration) {
        this.BudgetKeyConfiguration = BudgetKeyConfiguration;
    }

    public Long getBudgetDetailsKeyId() {
        return budgetDetailsKeyId;
    }

    public void setBudgetDetailsKeyId(Long budgetDetailsKeyId) {
        this.budgetDetailsKeyId = budgetDetailsKeyId;
    }

    public Long getBudgetDetailsKeyRealId() {
        return budgetDetailsKeyRealId;
    }

    public void setBudgetDetailsKeyRealId(Long budgetDetailsKeyRealId) {
        this.budgetDetailsKeyRealId = budgetDetailsKeyRealId;
    }

    public BudgetKeyEntity getBudgetKey() {
        return budgetKey;
    }

    public void setBudgetKey(BudgetKeyEntity budgetKey) {
        this.budgetKey = budgetKey;
    }

    
    
    @Override
    public int compareTo(BudgetDetailsKeyEntity anOtherEntity) {
        return this.budgetDetailsKeyId.compareTo(
                anOtherEntity.budgetDetailsKeyId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BudgetDetailsKeyEntity) {
            return this.budgetDetailsKeyId.equals(
                    ((BudgetDetailsKeyEntity) obj).budgetDetailsKeyId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.budgetDetailsKeyId != null
                ? this.budgetDetailsKeyId.hashCode() : 0);
        return hash;
    }
     
    
}
