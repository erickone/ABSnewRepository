/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.UnitMeasureBenefAndGoalEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 * Define los metodos de negocio para las unidades de medica
 * Preficha->Meta Beneficiarios
 */
public interface UnitMeasureBenefAndGoalManagement {
    
    Collection<UnitMeasureBenefAndGoalEntity> getUnitMeasureBenefAndGoals();
}