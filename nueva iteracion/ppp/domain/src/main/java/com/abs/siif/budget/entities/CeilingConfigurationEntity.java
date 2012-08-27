/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingFilterEntity
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author FENIX-02
 */
@Entity
@Table(name = "SIIFPPPConfTechoPptal")
public class CeilingConfigurationEntity implements Comparable<CeilingConfigurationEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idconftechopptal")
    private Long ceilingConfigId;
    @Column(name = "Nombre", length = 150)
    private String ceilingConfigName;
    @Column(name = "Descripcion", length = 150)
    private String ceilingConfigDescription;
    @Column(name = "activo")
    private boolean ceilingConfigActive;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "siifpppdetconftechopptal", joinColumns = {
        @JoinColumn(name = "idconftechopptal", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "idelemento", nullable = false, updatable = false)})
    private List<BudgetKeyItemEntity> budgetKeyItems = new ArrayList<BudgetKeyItemEntity>();
    
  
    @ManyToOne
    @JoinColumn(name = "iddefcvepresupuestal", nullable = false)
    private BudgetKeyDefinitionEntity ceilingConfigBudgetKey;

    public boolean isCeilingConfigActive() {
        return ceilingConfigActive;
    }

    public void setCeilingConfigActive(boolean ceilingConfigActive) {
        this.ceilingConfigActive = ceilingConfigActive;
    }

    public BudgetKeyDefinitionEntity getCeilingConfigBudgetKey() {
        return ceilingConfigBudgetKey;
    }

    public void setCeilingConfigBudgetKey(BudgetKeyDefinitionEntity ceilingFilterConfiguration) {
        this.ceilingConfigBudgetKey = ceilingFilterConfiguration;
    }

    public String getCeilingConfigDescription() {
        return ceilingConfigDescription != null ? ceilingConfigDescription.trim() :
               ceilingConfigDescription;
    }

    public void setCeilingConfigDescription(String ceilingConfigDescription) {
        this.ceilingConfigDescription = ceilingConfigDescription;
    }

    public Long getCeilingConfigId() {
        return ceilingConfigId;
    }

    public void setCeilingConfigId(Long ceilingConfigId) {
        this.ceilingConfigId = ceilingConfigId;
    }

    public String getCeilingConfigName() {
        return ceilingConfigName != null ? ceilingConfigName.trim() :
               ceilingConfigName;
    }

    public void setCeilingConfigName(String ceilingConfigName) {
        this.ceilingConfigName = ceilingConfigName;
    }

    public List<BudgetKeyItemEntity> getBudgetKeyItems() {
        return budgetKeyItems;
    }

    public void setBudgetKeyItems(List<BudgetKeyItemEntity> budgetKeyItems) {
        this.budgetKeyItems = budgetKeyItems;
    }

    @Override
    public int compareTo(CeilingConfigurationEntity obj) {
        int result = -1;
        if (this.ceilingConfigId != null
                && obj.getCeilingConfigId() != null) {
            result = this.ceilingConfigId.compareTo(obj.getCeilingConfigId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof CeilingConfigurationEntity) {
            this.ceilingConfigId.equals(((CeilingConfigurationEntity) obj).getCeilingConfigId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.ceilingConfigId != null
                ? this.ceilingConfigId.hashCode() : 0);
        return hash;
    }
}
