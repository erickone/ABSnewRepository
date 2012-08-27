/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.GeneralConceptEntity;
import java.util.Collection;

/**
 *
 * @author Juan Zavala Aguilar
 */
public interface GeneralConceptManagement {
    
    Collection<GeneralConceptEntity> getGeneralConcepts();
}
