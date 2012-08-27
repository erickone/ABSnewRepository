/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.admin.dao;

import com.abs.siif.admin.entities.BuildingConceptFrameEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ABS
 */
public interface BuildingConceptFrameDao {
    
    
    public Collection<BuildingConceptFrameEntity> getBuildingsByGeneral(Long generalConceptId);   
    
    public Collection<BuildingConceptFrameEntity> getActionsByGeneralAndBuilding(Long general, Long building);
    
    public Collection<BuildingConceptFrameEntity> getObjectExpensesByGeneralBuildingAndAction(
            Long general, Long building, Long actionId);

    public void saveAll(List<BuildingConceptFrameEntity> entities);
    
    public void deleteAll(List<BuildingConceptFrameEntity> entities);
    
}
