/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Declaraci�n de m�todos para la bit�cora de Anteproyecto
 */
public interface DraftProjectBinnacleDao extends SIIFBaseDao<DraftProjectBinnacleEntity, Long> {

    void saveAll(Collection<DraftProjectBinnacleEntity> anEntities);

    void deleteAll(Collection<DraftProjectBinnacleEntity> anEntities);

    DraftProjectBinnacleEntity getDraftProjectBinnacleById(Long anIdentity);
    
    Collection<DraftProjectBinnacleEntity> getDraftProjectBinnacleByDraftProjectId(Long anIdentity);
    
    Date getDateOfLastStatus(Long aDraftProjectId, Long aStatusId);
    void Save(DraftProjectBinnacleEntity aDraftProjectBinnacleEntity);
    
    void updateDraftProjectStatus(DraftProjectBinnacleEntity aDraftBinnacle);
     int getDraftProjectInDeterminateStatus(DraftProjectEntity aDraftProjectEntity);
}
