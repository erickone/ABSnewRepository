/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dto.DraftProjectSearchByDto;
import com.abs.siif.programming.dto.DraftProjectSearchDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Juan Antonio Zavala Aguilar Defino los metodos de acceso a datos para
 * el ante proyecto
 */
public interface DraftProjectDao extends SIIFBaseDao<DraftProjectEntity, Long> 
{

    Collection<DraftProjectEntity> getDraftProjects();

    @Override
    void deleteAll(Collection<DraftProjectEntity> anEntities);

    DraftProjectEntity getDraftProjectById(Long anIdentity);
    
    long getTotalProjects(Long anDependenceId);
    
    public List<DraftProjectSearchByDto> getFilteredDraftProjectDTO(DraftProjectEntity anEntity);
    
    DraftProjectEntity getDraftProjectEager(Long anIdentity);
    
    Collection<DraftProjectEntity> getDraftProjectsInWorkFlow(DraftProjectSearchDto aaDraftProjectSearchDto);
    
    boolean deleteDraftProject(DraftProjectEntity anEntity);
    
    Collection<DraftProjectEntity> getDraftProjectsByFilter(DraftProjectSearchDto aDraftProjectSearchDto);
    
    DraftProjectEntity getDraftProjectByIdWithBudgets(Long anIdentity);
    
    Long getAvailableCeilingByUEGIds(String identitiesToserach);
    
    Long getAvailableCeilingBasics(String identitiesToserach, Long obj, String Dest);
    
    DraftProjectEntity getFilteredDraftProjectDirectSearch(DraftProjectEntity aDraftProjectEntity, List<DependenceEntity> aDependenceEntity);
    
    DraftProjectEntity getFilteredDraftProjectByDependenceUEG(DraftProjectEntity aDraftProjectEntity, DependenceEntity aDependenceEntity);
    
    Long getBudgetByUEG(DraftProjectEntity aDraftProject);
    
    Long getInvPreFileAvailableCeilingByUEGIds(String identitiesToserach);
}