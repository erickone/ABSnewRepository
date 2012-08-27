/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.entities.ObjectiveEntity;
import java.util.List;

/**
 * Este metodo es una interfaz que se encarga de entregarle a la presentacion
 * las listas de objetivos especificos y los problemas relacionados al Objetivo 
 * Seleccionado
 * @author Erick Leija
 */
public interface ObjectiveProgrammingManagement
{
   List<ObjectiveEntity> getAllObjectivesByDependenceId(Long aDependenceId);
}
