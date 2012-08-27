/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.ActionGBEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface ActionGBManagement {
 
    Collection<ActionGBEntity> getActionGBs();
    Collection<ActionGBEntity> getActionsByBuildingConcept(Long anIdentity);
}
