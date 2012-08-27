/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.BuildingConceptEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface BuildingConceptDao {
    
    Collection<BuildingConceptEntity> getBuildingConceptsByConceptGeneral(Long anIdentity);   

    public List<BuildingConceptEntity> getBuildingConceptsNotWithIds(Long[] ids);
}
