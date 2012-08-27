/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyAndDraftProjectFraming
 *  Purpose:  [ Esta entidad es la encargada de realizar el encuadre entre
 *  el anteproyecto y las claves presupuestales, ademas de guardar
 *  elgunos campos que puedan agregarse en el futuro]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.entities;

import com.abs.siif.programming.entities.DraftProjectEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 */
@Entity
@Table(name = "siifpppdetcveanteproy")
public class BudgetKeyAndDraftProjectFramingEntity 
implements Serializable, Comparable<BudgetKeyAndDraftProjectFramingEntity>
{
     // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "iddetcveanteproy")
    private Long budgetKeyAndDraftProId;


    // Identificador del Anteproyecto
    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "IdAnteproyecto", nullable = false)
    private DraftProjectEntity draftProjectIdBudgetKeyAndDraftPro;
    
    // Identificador de las claves presupuestales
    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "IdCvePresupuestal", nullable = false)
    private BudgetKeyEntity budgetKeyIdBudgetKeyAndDraftPro;

    public BudgetKeyEntity getBudgetKeyIdBudgetKeyAndDraftPro() {
        return budgetKeyIdBudgetKeyAndDraftPro;
    }

    public void setBudgetKeyIdBudgetKeyAndDraftPro(BudgetKeyEntity BudgetKeyIdBudgetKeyAndDraftPro) {
        this.budgetKeyIdBudgetKeyAndDraftPro = BudgetKeyIdBudgetKeyAndDraftPro;
    }

    public Long getBudgetKeyAndDraftProId() {
        return budgetKeyAndDraftProId;
    }

    public void setBudgetKeyAndDraftProId(Long budgetKeyAndDraftProId) {
        this.budgetKeyAndDraftProId = budgetKeyAndDraftProId;
    }

    public DraftProjectEntity getDraftProjectIdBudgetKeyAndDraftPro() {
        return draftProjectIdBudgetKeyAndDraftPro;
    }

    public void setDraftProjectIdBudgetKeyAndDraftPro(DraftProjectEntity draftProjectIdBudgetKeyAndDraftPro) {
        this.draftProjectIdBudgetKeyAndDraftPro = draftProjectIdBudgetKeyAndDraftPro;
    }
    
    
    @Override
    public int compareTo(BudgetKeyAndDraftProjectFramingEntity anOtherEntity) {
        return this.budgetKeyAndDraftProId.compareTo(
                anOtherEntity.budgetKeyAndDraftProId);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BudgetKeyAndDraftProjectFramingEntity) {
            return this.budgetKeyAndDraftProId.equals(
                    ((BudgetKeyAndDraftProjectFramingEntity) obj).budgetKeyAndDraftProId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.budgetKeyAndDraftProId != null
                ? this.budgetKeyAndDraftProId.hashCode() : 0);
        return hash;
    }

    
    
}
