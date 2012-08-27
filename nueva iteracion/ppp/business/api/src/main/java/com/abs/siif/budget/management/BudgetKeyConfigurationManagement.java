package com.abs.siif.budget.management;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Map;
import com.abs.siif.budget.entities.BudgetDetailsKeyEntity;
import java.util.Collection;
import java.util.List;
/**
 *
 * @author Juan Antonio Zavala Aguilar
 * Define los metodos de negocio para la generación de una nueva clave presupuestal
 */
public interface BudgetKeyConfigurationManagement {
    
    /**
     * Regresa una cadena de caracteres que conforman la clave presupuestal,
     * los caracteres son tomados apartir del un sql nativo o hql, configurados 
     * en la tabla siifabspppcvepptalcfg para cada elemento.
     * @param anIdentities debe enviarse un map de la siguiente forma
     *  item "iddependencia",'UUID (dependenceEntity.dependenceId)'
     *  item "idproyecto",'UUID (draftProject.draftprojectId)'
     *  item "idobjetogasto",'UUID (objectExpenseEntity.ObjectExpenseId)'
     *  item "iddestino",'UUID (DestinationEntity.destinationId)'
     * @return BudgetKey
     */
    public String getBudgetKey(Map<String,Long> anIdentities);
    
    
    public Map<String,List<BudgetDetailsKeyEntity>> getBudgetKeyWithBudgetKeyItems(Map<String,Long> anIdentities);
}
