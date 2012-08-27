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

import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 * 
 * Esta entidad define los atributos de los elementos de las vistas en el SIIF
 * 
 */

@Entity
@Table(name = "siifsegvistaelemento")
public class ViewItemEntity implements 
        Serializable, Comparable<ViewItemEntity>{

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdVistaElemento", nullable = false)
    private Long viewItemId;

    
    // Identificador de la Vista
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="IdVista", nullable=false)
    private ViewEntity viewItemView;

    // Identificador de los Elementos del Presupuesto
    @ManyToOne
    @JoinColumn(name="IdElemento", nullable=false)
    private BudgetKeyItemEntity viewItemBudgetKeyItem;
    
    // Identificador del Elemento
    @Column(name = "Criterio", length = 50)
    private String viewItemCriteria;

    public BudgetKeyItemEntity getViewItemBudgetKeyItem() {
        return viewItemBudgetKeyItem;
    }

    public void setViewItemBudgetKeyItem(BudgetKeyItemEntity viewItemBudgetKeyItem) {
        this.viewItemBudgetKeyItem = viewItemBudgetKeyItem;
    }

    public String getViewItemCriteria() {
        return viewItemCriteria != null ?
                viewItemCriteria.trim(): viewItemCriteria;
    }

    public void setViewItemCriteria(String viewItemCriteria) {
        this.viewItemCriteria = viewItemCriteria;
    }

    public Long getViewItemId() {
        return viewItemId;
    }

    public void setViewItemId(Long viewItemId) {
        this.viewItemId = viewItemId;
    }

    public ViewEntity getViewItemView() {
        return viewItemView;
    }

    public void setViewItemView(ViewEntity viewItemView) {
        this.viewItemView = viewItemView;
    }

    @Override
    public int compareTo(ViewItemEntity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
