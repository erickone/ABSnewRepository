/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.entities.DependenceEntity;
import java.util.Collection;
import java.util.List;

/**
 *Esta es la interfaz, que nos muestra los metodos que podemos consumir del
 * management
 * @author Erick Leija
 */
public interface DependenceProgrammingManagement
{
     List<DependenceEntity> getDependencesByUEG();

    /**
     * Dependencias que estan marcardas como programable
     *
     * @return
     */
    List<DependenceEntity> getDependencesHasProgramming();

    /*
     * Obtienen las dependencias que no tienen padre asociado y por lo tanto
     * son de primer nivel
     */
    Collection<DependenceEntity> getViewDepWithoutFather();

    /*
     * Obtiene las dependencias asociadas a un padre especifico
     */
    Collection<DependenceEntity> getViewDepByFather(DependenceEntity
            aDependencyId);
    
}
