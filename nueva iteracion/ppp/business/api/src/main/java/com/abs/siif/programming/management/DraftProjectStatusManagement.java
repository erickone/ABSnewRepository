/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dto.DraftProjectStatusSearchDto;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface DraftProjectStatusManagement {

    Collection<DraftProjectStatusEntity> getDraftProjectStatus();
    
    DraftProjectStatusEntity getStatusByConsecutive(int aConsecutive);
    
    /*
     * Resive un dto que al momento contiene el anteproyecto y el perfil
     * del usuario
     */
    Collection<DraftProjectStatusEntity> getStatus(DraftProjectStatusSearchDto
            aDraftProjectStatusSearchDto);
    
    DraftProjectStatusEntity getIniStatus();
    
    DraftProjectStatusEntity getEndStatus();
}
