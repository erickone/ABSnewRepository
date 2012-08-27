/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.admin.management;

import com.abs.siif.admin.dto.BuildingConceptFrameDto;
import java.util.List;

/**
 *
 * @author cesar.toledano
 */
public interface BuildingConceptFrameManagement {
               
    public List<BuildingConceptFrameDto> getGeneralConcepts();
    
    public List<BuildingConceptFrameDto> getBuildingConceptsUsed(Long id);
    
    public List<BuildingConceptFrameDto> getBuildingConceptsNotUsed(List<BuildingConceptFrameDto> buildingConceptsUsed);
    
    public void generateGeneralConceptBuildingRelation(Long id, Long[] ids);
    
    public boolean deleteGeneralConceptBuildingRelation(Long id, Long[] ids);
    
    public List<BuildingConceptFrameDto> getActionsUsed(Long general, Long building);
    
    public List<BuildingConceptFrameDto> getActionsNotUsed(List<BuildingConceptFrameDto> actionsUsed);
    
    public void generateBuildingConceptActionRelation(Long generalId, Long buildingId, Long[] ids);
    
    public boolean deleteBuildingConceptActionRelation(Long generalId, Long buildingId, Long[] ids);
    
    public List<BuildingConceptFrameDto> getObjectExpensesUsed(Long general, Long building, Long action);
    
    public List<BuildingConceptFrameDto> getObjectExpensesNotUsed(List<BuildingConceptFrameDto> object);
    
    public void generateActionObjectExpenseRelation(Long general, Long building, Long action, Long[] ids);
    
    public boolean deleteActionObjectExpenseRelation(Long general, Long building, Long action, Long[] ids);
    
}
