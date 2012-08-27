/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.ClassifierEntity;

/**
 *
 * @author FENIX-02
 */
public interface ClassifierManagement
{
    /**
     * Salva los datos de la pestaña de Clasificacion del AnteProyecto
     * @param aClassifierEntity
     * @return 
     */
    public ClassifierEntity saveClassifier(ClassifierEntity 
            aClassifierEntity);
    
    /**
     * Trae los datos de la pestaña de Clasificacion del AnteProyecto
     * @param aClassifierEntity
     * @return 
     */
    public ClassifierEntity getClassifierByDraftProyectId(Long aDraftProyectId);
}
