/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.programming.dto.RegionalClassifierDTO;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.entities.InvPreFileRegionalClassifierEntity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Israel Ruiz
 */
public interface UbicationManagement {

    /**
     * Obtiene la lista de Regiones que se establecieron como ambito
     *
     * @return
     */
    public List<RegionalLevelClassifierEntity> getUbicationScope();

    public List<RegionalClassifierEntity> getUbicationsByScope(RegionalLevelClassifierEntity regionLevelentity);

    public RegionalClassifierEntity getRegionalClassiById(RegionalClassifierEntity entityToFind);

    /**
     * Lista de Ubicaciones en relacion al entidad entregada
     *
     * @param ubication
     * @return
     */
    public List<RegionalClassifierDTO> findUbication(
            RegionalClassifierEntity ubication);

    public Map<Long, List<RegionalClassifierDTO>> saveInvPreFileReg(
            List<String> ubicationSelected, InvPreFileEntity invPreFile);

    
     public List<Long> getUbicationsSelected(
            InvPreFileEntity invPreFile);
     
     public List<RegionalClassifierDTO> findUbication(
            RegionalClassifierEntity ubication,
            InvPreFileEntity invPreFile);
     
     public List<Long> getDraftProjectUbications(
                DraftProjectEntity draftProEntity);

    public Map<Long, List<RegionalClassifierDTO>> saveDraftProjectUbication(
            List<String> itsSelectedUbications, Long currentId);
    
    /**
     * Obtiene todos las ubicaciones que se asocian al Anteproyecto
     * @param ubication
     * @param entity
     * @return 
     */
    public List<RegionalClassifierDTO> findDraftProjectUbication(
            RegionalClassifierEntity ubication,
            DraftProjectEntity entity);
    /**
     * Obtiene el id del regional level de una región
     * @param idRegion
     * @return 
     */
    public Long findFather(Long idRegion);
    
    /**
     * Obtiene la descripción del regional level de una región
     * @param idRegion
     * @return 
     */
    public String findFatherDesc(Long idRegion);
}
