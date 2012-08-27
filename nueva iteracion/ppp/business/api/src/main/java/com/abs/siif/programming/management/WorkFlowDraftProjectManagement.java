/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  WorkFlowDraftProjectManagement
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.dto.DraftProjectDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.dto.DraftProjectSearchDto;
import com.abs.siif.programming.dto.DraftProjectStatusSearchDto;
import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import java.util.Collection;
import java.util.HashMap;
import java.util.List; 

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface WorkFlowDraftProjectManagement {
     
    /*
     * Setea el nuevo status para un anteproyecto,
     * y deja en bitacora el status del anteproyecto actual
     * lanza una exception en el caso de que una cambio de 
     * estatus no pueda ser ejecutado, preparar el controller 
     * para recibir dicha exception
     */
    public void executeChangeStatus(DraftProjectEntity aDraftProject,
            DraftProjectStatusEntity aNextStatus);
    
    /*
     * Setea el nuevo status para cada uno de los anteproyectos,
     * y deja en bitacora el status del anteproyecto actual
     */
    public void executeChangeStatus(Collection<DraftProjectEntity> aDraftProjects,
            DraftProjectStatusEntity aNextStatus);
    
    /*
     * Carga las listas de supporte para la UI. (dependencias, estatus)
     * el key para la lista de dependencias es "dependencies"
     * el key para la lista de estatus es "status"
     */
    HashMap<String, List<?>> getSupportList();

    /*
     * Apartir de un proyecto devuelve los posibles estatus que puede tomar
     * de acuerto al estatus actual del proyecto y el perfil de acceso
     * el perfil es tomado desde el contexto
     */
    Collection<DraftProjectStatusEntity> getPossibleStatus(DraftProjectStatusSearchDto
            aDraftProjectStatusSearchDto);
    
    /*
     * Obtiene el estado inicial de la configuración realizada para el estatus
     */
    DraftProjectStatusEntity getInitialStatus( );
    
    /*
     * 
     */
    DraftProjectStatusEntity getFinalStatus();
    
    Collection<DraftProjectEntity> getDraftProjectsInWorkFlow(DraftProjectSearchDto 
            aDraftProjectSearchDto);
    
    Collection<DraftProjectBinnacleEntity> getBinnaclebyDraftProjectId(Long aDraftProjectID);
    
    Collection<DraftProjectEntity> getDraftProjectsByFilter(DraftProjectSearchDto aDraftProjectSearchDto);
}
