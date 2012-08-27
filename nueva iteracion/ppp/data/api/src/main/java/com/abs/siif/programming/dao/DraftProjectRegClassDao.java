/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.DraftProjRegionalClassifierEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.util.List;

/**
 *
 * @author Israel Ruiz
 */
public interface DraftProjectRegClassDao extends 
        SIIFBaseDao<DraftProjRegionalClassifierEntity, Long> {
    /**
     * Obtiene las ubicaciones que estan asociadas al anteproyecto
     * @param draftProEntity anteproyecto del cuaol se requiere buscar 
     *        las ubiciones
     * @return  Lista de las Ubicaciones asociadas
     */
    public List<DraftProjRegionalClassifierEntity> 
            findUBicationsDraftProject(DraftProjectEntity draftProjEntity);
    /**
     * Elimina todas las ubicaciones que se tengan registradas con el 
     * Project Id que se manda
     * @param draftProjectId 
     */
    public void deleteUbicationDraftProj(Long draftProjectId);
    
}
