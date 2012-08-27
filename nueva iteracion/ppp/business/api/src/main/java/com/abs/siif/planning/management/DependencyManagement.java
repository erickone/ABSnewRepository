/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependencyManagement
 *  Purpose:  [Implementa las reglas de negocio para el manejo del clasificador
 * de dependencias]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.management;

import com.abs.siif.planning.entities.DependenceEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface DependencyManagement   {
    
    /**
     * Retorna las listas de soporte para el manejo del clasificador 
     * administrativo,regresa los estatus de colectiva, tipos de colectiva y
     * tipos de clasificacion de dependencia para accesar a las distintas lista
     * siga la siguiente guia
     * 
     * @return 
     * "StatusColectives" retorna los estatus de la colectiva
     * "AdminClassTypes" retorna los tipos de clasificacion dependencia
     * "ColectiveTypes" retorna los tipos de colectiva.
     * "Responsibles" retorna los empleados registrados.
     */
    Map<String,List<?>> getSupportList();
   
    DependenceEntity getDependecyById(DependenceEntity anEntity);
    
    DependenceEntity persistDependency(DependenceEntity anEntity);
    
    void deleteDependency(DependenceEntity anEntity);
}
