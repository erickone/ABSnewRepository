/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.ActionGBEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface ActionGBDao {
    
    Collection<ActionGBEntity> getActionGBs();

    public Collection<ActionGBEntity> getActionsByBuildingConcept(Long anIdentity);

    public List<ActionGBEntity> getActionsNotWithIds(Long[] ids);
    
}
