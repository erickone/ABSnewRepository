/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.GeneralConceptEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface GeneralConceptDao {
    
    Collection<GeneralConceptEntity> getGeneralConcepts();
    
}
