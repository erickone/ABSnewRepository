/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.BuildingConceptEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zaval aguilar
 */
public interface BuildingConceptManagement {
    
    Collection<BuildingConceptEntity> getBuildingConceptsByConceptGeneral(Long anIdentity);
}
