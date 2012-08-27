/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.dto.DraftProjectStatusSearchDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar Define los metodos de acceso a datos al
 * estado del proyecto
 */
public interface DraftProjectStatusDao 
extends SIIFBaseDao<DraftProjectStatusEntity, Long> {

    Collection<DraftProjectStatusEntity> getAllDraftProjectStatus();

    @Override
    void saveAll(Collection<DraftProjectStatusEntity> anEntities);

    @Override
    void deleteAll(Collection<DraftProjectStatusEntity> anEntities);

    DraftProjectStatusEntity getStatusByConsecutive(int aConsecutiveOfStatus);
   
    Collection<DraftProjectStatusEntity> getPossibleStatus(DraftProjectStatusSearchDto
            aDraftProjectStatusSearchDto);
    
    DraftProjectStatusEntity getInitialStatus();
    
    DraftProjectStatusEntity getFinalStatus();
    
    
}