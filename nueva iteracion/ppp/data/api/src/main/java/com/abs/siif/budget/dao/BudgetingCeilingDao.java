/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.entities.BudgetingCeilingEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Erick Leija
 * esta interfaz se encarga de 
 */
public interface BudgetingCeilingDao extends SIIFBaseDao<BudgetingCeilingEntity, Long>
{
    List<ObjectExpenseEntity> getObjectExpenseRoots();
    List<Long> getHierarchicalDependencies(Long anIdentity,Long aDependence);
    List<Long> getHierarchicalDependencies(List<Long> anIdentities,Long aDependence);
    Long getLevelOfSpecificParId();
}
