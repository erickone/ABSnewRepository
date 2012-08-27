/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  PEDVinculationManagement
 *  Purpose:  [ esta interfaz se encargara de enumerar las acciones que se 
 *  pueden realizar sobre la entidad de PEDVInculation, y es la encargara
 *  de la logica de negocio de estas acciones]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.PEDVinculationEntity;
import com.abs.siif.programming.entities.RegionalPlansOfPEDEntity;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface PedVinculationManagement 
{
    /**
     * este metodo nos regresa una entidad que se busca por medio del id del
     * anteproyecto que tiene asignado
     * @param anDraftProjectId
     * @return 
     */
    PEDVinculationEntity getPEDVinculationById(Long anDraftProjectId);
    
    /**
     * este metodo sirve para guardar la seleccion del usuario en pantalla
     * @param aPEDVinculation
     * @return 
     */
    Long savePEDVinculation(PEDVinculationEntity aPEDVinculation);
    
    /**
     * Este metodo sirve para salvar un registro de los REgional plans 
     * Seleccionados
     * @param anEntity
     * @return 
     */
    Long saveRegionalPlanAndDraftProject(RegionalPlansOfPEDEntity anEntity);
    
    /**
     * Este metodo sirve para traer la entidad que contiene toda la información
     * relacionada con los planes regionales de la ventana vinculación a PED
     * @param aDraftProjectId
     * @return 
     */
    List<RegionalPlansOfPEDEntity> getRegionalPlanByDraftProjectId(Long aDraftProjectId);
    
    String deleteAllRegionalPlansByDraftProjectIDAndRegionClassifierId(Long aDraftProjectId,Long aRegionalClassifier);
}
