/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DirectCaptureDraftProject
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

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import com.abs.siif.programming.dto.ClasificationDTO;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Establece los metodos de servicios requeridos para la captura directa
 * relacionada a la pestaña de clasificador y Vinculación al PED
 * @author Israel Ruiz
 */
public interface DirectCaptureDraftProject {
    
    /**
     * Obtien los catalogos requeridos para la ventana de Clasificador
     * Administrativo utilizado duirante la captura directa de la Ficha
     * (AnteProyecto)
     * @param resposableUnit
     * @return 
     */
    public ClasificationDTO getCatalogs(Long resposableUnit);
 
  
     /** 
     * Este es el metodo que regresa la lista dummy de las dependencias
     * que son Unidades Ejecutoras del Gasto(UEG), cuando se haga la logica de negocio
     * se cambiaran las firmas que apuntaran a los Dao que se hayan definido para el manejo 
     * de estas operaciones
     * @return 
     */
    public List<DependenceEntity> getDraftProjectDependencesByUEG();
    
    public Map<String, List<?>> getDraftProjectSupportLists();
    
    public Collection<FunctionalClassifierEntity> getMyAncestry(Long aObjectiveId);
    
    public Long GetUEGbyUR(DependenceEntity resposableUnit);
    
}
