/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DepToObjLinkManagement
 *  Purpose:   Interface to serve the Link between Dependencies and 
 *              Objectives.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.programming.management;

import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface DepToObjLinkManagement {

     /**
     * Salva los datos del encuadre
     * @param aDependenceEntity
     * @return 
     */
    public boolean saveGeneralDataInvPreFile(DependenceEntity aDependenceEntity);
    
    /**
     * Obtiene la lista de los padres del nivel que se va a encuadrar.
     * @param aNivelDependencia
     * @return List<DependenceEntity>
     */
    public List<DepencenceDto> getFathersList();
    
    /**
     * Obtiene una lista de dependencias basadas en el id del padre.
     * @return List
     */
    public List<DependenceEntity> getChildsList(Long idFather);
    
    /**
     * Este metodo se trae las dependencias relacionadas con algun objetivo.
     * @param idObjective
     * @return 
     */
    public List<DependenceEntity> getChildsRelatedObj(Long idDepFather, Long idObjective);
    
    /**
     * Este medoto guarda un Objetivo y en cascada las dependencias en la tabla
     * de encuadre.
     * @param dependencies
     * @return 
     */
    public boolean saveObjectiveWithDependencies(ObjectiveEntity anObjectiveEntity, Long idpadre);
}
