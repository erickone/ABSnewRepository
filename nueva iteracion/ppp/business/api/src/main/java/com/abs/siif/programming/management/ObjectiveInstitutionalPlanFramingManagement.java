/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.entities.ObjectiveEntity;
import java.util.List;

/**
 *esta es la interfaz del management que se encargara de la busqueda en la entidad
 * ObjectiveInstitutionalPlanFraming de los objetivos que tengan asociada la dependencia
 * se supone que solo los obejtivos de ultimo nivel son los que pueden tener ficha
 * entonces para formar el arbolito se necesitan los padres de los objetivos obtenidos en 
 * esta cosulta, para poder crear el arbolito, este metodo tiene como meta
 * regresar una lista de los objetivos de ultimo nivel y toda su herencia ascendente
 * 
 * 
 * @author Erick Leija
 */
public interface ObjectiveInstitutionalPlanFramingManagement
{
    
    public List<ObjectiveEntity> getAllObjByDependId(Long aDependenceId);
}
