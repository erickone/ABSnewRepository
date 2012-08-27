/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.UnitMeasureGoalEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 * Mapea los metodos de acceso a datos que pertenecen a las unidades de medida
 * usadas en "Preficha->Meta"
 */
public interface UnitMeasureGoalDao {
    
    Collection<UnitMeasureGoalEntity> getUnitMeasureGoals();
    
}
