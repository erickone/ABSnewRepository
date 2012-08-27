/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dto.DraftProjectSearchDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Esta es la inteface que se encarga de mostrar los metodos que contiene la
 * implementación del management DrafProject
 *
 * @author Erick Leija
 */
public interface DraftProjectManagement {

    /*
     * retorna una mapa con el total de las siguientes listas
     * draftProjectTypeEntity,draftProjectScopeEntity y draftProjectStatusEntity
     *
     */
    
    
    Map<String, List<?>> getSupportLists();

    DraftProjectEntity getDraftProjectById(Long anIdentity);
    
    DraftProjectEntity getDraftProjectByIdWithBudgets(Long anIdentity);

    DraftProjectEntity getDraftProjectByIdEager(Long anIdentity);

    DraftProjectEntity saveDraftProject(DraftProjectEntity aDraftProject, Long anObjectiveId);

    DraftProjectStatusEntity getStatusEntityByConsecutive(int aConsecutive);

    String getTotalProjects(Long anDependenceId);

    int getDraftProjectInDeterminateStatus(DraftProjectEntity aDraftProjectEntity);

    Collection<DraftProjectEntity> getDraftProInWorkFlow(DraftProjectSearchDto
            aDraftProjectSearchDto);
    
    boolean deleteDraftProject(Long aDraftProjectID);
    
}