/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 * define los metodos de acceso a datos, necesarios para utilizar
 * los elementos de la clave presupuestal.
 */
public interface BudgetKeyItemDao {
    
    Collection<BudgetKeyItemEntity> getBudgetKeyItems(); 
    
    List<BudgetKeyItemEntity> getBudgetItemsRelatedCeilingConf(Long aCeilingConfId);
}
