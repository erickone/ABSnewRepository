/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ViewEntity
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.entities;

import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 * 
 * Esta entidad define los atributos para una vista dentro del SIIF, esto es solo a manera 
 * de encabezado, tienen un ámbito regido por la definición de la clave presupuestal
 * 
 */

@Entity
@Table(name = "siifsegvista")
public class ViewEntity implements 
        Serializable, Comparable<ViewEntity>{
    
    

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdVista", nullable = false)
    private Long viewId;

    //Descripción de la Vista
    @Column(name = "Descripcion", length = 50)
    private String viewDescription;
    
    // Identificador de la Definición de la Clave Presupuestal
    @ManyToOne
    @JoinColumn(name="IdDefCvePresupuestal", nullable=true)
    private BudgetKeyDefinitionEntity budgetKeyDefinitionBudgetKey;
     
    
    @OneToMany(mappedBy = "viewUserModuleView",fetch= FetchType.LAZY)
    private List<ViewUserModuleEntity> viewUserModuleEntitys;

    @OneToMany(mappedBy = "viewItemView",fetch= FetchType.LAZY)
    private List<ViewItemEntity> viewItems;
    
    public List<ViewUserModuleEntity> getViewUserModuleEntitys() {
        return viewUserModuleEntitys;
    }

    public List<ViewItemEntity> getViewItems() {
        return viewItems;
    }
    
    
    

    public BudgetKeyDefinitionEntity getBudgetKeyDefinitionBudgetKey() {
        return budgetKeyDefinitionBudgetKey;
    }

    public void setBudgetKeyDefinitionBudgetKey(BudgetKeyDefinitionEntity budgetKeyDefinitionBudgetKey) {
        this.budgetKeyDefinitionBudgetKey = budgetKeyDefinitionBudgetKey;
    }

    public String getViewDescription() {
        return viewDescription != null ? viewDescription.trim():viewDescription;
    }

    public void setViewDescription(String viewDescription) {
        this.viewDescription = viewDescription;
    }

    public Long getViewId() {
        return viewId;
    }

    public void setViewId(Long viewId) {
        this.viewId = viewId;
    }

    @Override
    public int compareTo(ViewEntity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    
}
