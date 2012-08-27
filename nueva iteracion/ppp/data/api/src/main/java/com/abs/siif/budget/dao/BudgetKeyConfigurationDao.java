/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.budget.data.BudgetDetailKeyDto;
import com.abs.siif.budget.entities.BudgetKeyConfigurationEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 * Define los metodos para acceso a datos, que se relacionan a 
 * la tabla de la configuracion de la clave presupuestal,esta tabla
 * guarda como se debe crear la clave presupuestal, indica que elementos
 * deben utilizarse cuantas posiciones deben emplearse etc
 */
public interface BudgetKeyConfigurationDao {
    
    /*
     * Obtiene todos los registros de la configuración
     */
    Collection<BudgetKeyConfigurationEntity> getBudgetKeyConfigurations();
    
    /*
     * genera un key apartir de la configuración establecida para cada elemento
     * de la configuración 
     */
   
    public BudgetDetailKeyDto getPrefixByBudgetKeyConfiguration(BudgetKeyConfigurationEntity myConfiguration);


    
}
