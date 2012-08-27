/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.ClassifierEntity;

/**
 *
 * @author FENIX-02
 */
public interface ClassifierDao extends SIIFBaseDao<ClassifierEntity, Long>
{
     /**
     * Guarda la informacion de Clasificacion de Anteproyecto.
     * @param invPreFileEntity
     * @return ClassifierEntity
     */
    public ClassifierEntity saveClassifier(ClassifierEntity aClassifierEntity);
    
    /*
     * Trae la informacion guardada de un Anteproyecto.
     *  @param invPreFileEntity
     * @return ClassifierEntity
     */
    public ClassifierEntity getClassifierByDraftProyectId(Long aDraftProyectId);
}
