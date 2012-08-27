/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.dao.RegionalClassifierDao;
import com.abs.siif.planning.dao.RegionalLevelClassifierDao;
import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.programming.dao.DraftProjectDao;
import com.abs.siif.programming.dao.DraftProjectRegClassDao;
import com.abs.siif.programming.dao.InvPreFileDao;
import com.abs.siif.programming.dao.InvPreFileRegionalClassifierDao;
import com.abs.siif.programming.dto.RegionalClassifierDTO;
import com.abs.siif.programming.entities.DraftProjRegionalClassifierEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.entities.InvPreFileRegionalClassifierEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provee los servicios requeridos por la funcionalidad de Ubicación
 *
 * @author Israel Ruiz
 */
@Service("ubicationManagement")
public class UbicationManagementImpl implements UbicationManagement {

    @Resource(name = "regionalLevelClassifierDao")
    private RegionalLevelClassifierDao regionalLevelClassifierDao;
    @Resource(name = "RegionalClassifierDao")
    private RegionalClassifierDao regionalClassifierDao;
    @Resource(name = "invPreFileRegionalClassifierDao")
    private InvPreFileRegionalClassifierDao invPreFileRegClassDao;
    @Resource(name = "invPreFileDao")
    private InvPreFileDao invPreFileDao;
    @Resource(name = "draftProjectDaoImpl")
    private DraftProjectDao draftProjectDao;
    @Resource(name="draftProjectRegClassDao")
    DraftProjectRegClassDao draftProjectRegClassDao;

    /**
     * Obtiene la lista de Regiones que se establecieron como ambito
     *
     * @return
     */
    @Transactional
    @Override
    public List<RegionalLevelClassifierEntity> getUbicationScope() {
        return regionalLevelClassifierDao.findUbicationScope();
    }

    @Transactional
    @Override
    public List<RegionalClassifierEntity> getUbicationsByScope(
            RegionalLevelClassifierEntity regionLevelentity) {
        List<RegionalClassifierEntity> ubications =
                regionalClassifierDao.findUbicationByScope(regionLevelentity);
        return ubications;
    }
    
    @Transactional
    @Override
    public List<Long> getUbicationsSelected(
            InvPreFileEntity invPreFile) {
        List<Long> ubications = new ArrayList<Long>();
                
        
         List<InvPreFileRegionalClassifierEntity> ubicationsSelects = 
                    new ArrayList<InvPreFileRegionalClassifierEntity>();

         if(invPreFile != null){
            ubicationsSelects =
                invPreFileRegClassDao.findUBications(invPreFile);
        }
        
         for(InvPreFileRegionalClassifierEntity entityUb :ubicationsSelects){
             ubications.add(entityUb.getInvPreFileRegClasifRegClasif().
                     getRegionalClassifierId());
             
         }
        
        return ubications;
    }

    
    @Override
    public List<RegionalClassifierDTO> findUbication(
            RegionalClassifierEntity ubication,
            InvPreFileEntity invPreFile) {
        
        List<RegionalClassifierDTO> regionalDTOs;
        List<InvPreFileRegionalClassifierEntity> ubications;

        if (invPreFile != null) {
            ubications =
                    invPreFileRegClassDao.findUBications(invPreFile);
        } else {
            ubications = new ArrayList<InvPreFileRegionalClassifierEntity>();
        }

        regionalDTOs = regionalClassifierDao.findUbication(ubication);

        for (InvPreFileRegionalClassifierEntity oneUbi : ubications) {
            RegionalClassifierDTO refToFind = new RegionalClassifierDTO();

            refToFind.setidclasifregional(
                    oneUbi.getInvPreFileRegClasifRegClasif().
                    getRegionalClassifierId().intValue());
        }


        return regionalDTOs;
    }
    
    @Override
    public List<RegionalClassifierDTO> findDraftProjectUbication(
            RegionalClassifierEntity ubication,
            DraftProjectEntity entity) {
        
        List<RegionalClassifierDTO> regionalDTOs;
        List<DraftProjRegionalClassifierEntity> ubications;

        if (entity != null) {
            ubications =
                    this.draftProjectRegClassDao.findUBicationsDraftProject(entity);
        } else {
            ubications = new ArrayList<DraftProjRegionalClassifierEntity>();
        }

        regionalDTOs = regionalClassifierDao.findUbication(ubication);

        for (DraftProjRegionalClassifierEntity oneUbi : ubications) {
            RegionalClassifierDTO refToFind = new RegionalClassifierDTO();

            refToFind.setidclasifregional(
                    oneUbi.getDraftProjectRegionalClassifierRegClassif().
                    getRegionalClassifierId().intValue());
        }


        return regionalDTOs;
    }
    
    
     
    @Transactional
    @Override
    public RegionalClassifierEntity getRegionalClassiById(
            RegionalClassifierEntity entityToFind) {
        return regionalClassifierDao.findById(entityToFind.getRegionalClassifierId(), true);
    }
    /**
     * Realiza la busqueda de las ubicaciones (municipios) dado una region
     * la cual se realciona a una preficha de Inversion
     * @param ubication
     * @param invPreFile
     * @return 
     */
    @Transactional
    @Override
    public List<RegionalClassifierDTO> findUbication(
            RegionalClassifierEntity ubication) {
        
        List<RegionalClassifierDTO> regionalDTOs;
        regionalDTOs = regionalClassifierDao.findUbication(ubication);
        
        return regionalDTOs;
    }

    @Override
    public Map<Long, List<RegionalClassifierDTO>>
             saveInvPreFileReg(
                List<String> ubicationSelected, InvPreFileEntity invPreFile) {
        
        
        Map<Long, List<RegionalClassifierDTO>> result=
                new HashMap<Long, 
                        List<RegionalClassifierDTO>>();
                
        InvPreFileRegionalClassifierEntity  entity;
        RegionalClassifierEntity ubicationFather;
        RegionalClassifierDTO dtoKey;
        if(ubicationSelected != null ){
            invPreFileRegClassDao.deleteByInvPreFile(invPreFile);
        }
        for(String idRegion:ubicationSelected){
           entity = new InvPreFileRegionalClassifierEntity();
           ubicationFather = regionalClassifierDao.findById(Long.parseLong(idRegion), true);
           invPreFile = invPreFileDao.findById(
                invPreFile.getInvPreFileId(), true);
           entity.setInvPreFileEntity(invPreFile);
           entity.setInvPreFileRegClasifRegClasif(ubicationFather);
           invPreFileRegClassDao.save(entity);
           dtoKey = new RegionalClassifierDTO();
           dtoKey.setFather(ubicationFather);
           result.put(Long.parseLong(idRegion),findUbication(
                   ubicationFather,
                   invPreFile));
        }
       return result;
    }
    
    @Override
    public Long findFather(Long idRegion) {
        RegionalClassifierEntity ubication = new RegionalClassifierEntity();
        ubication.setRegionalClassifierId(idRegion);
        Long ubicationFatherId = regionalClassifierDao.findFather(ubication);
        return ubicationFatherId;
    }
    
    @Override
    public String findFatherDesc(Long idRegion) {
        RegionalClassifierEntity ubication = new RegionalClassifierEntity();
        ubication.setRegionalClassifierId(idRegion);
        String ubicationFatherId = regionalClassifierDao.findFatherDesc(ubication);
        return ubicationFatherId;
    }

    @Override
    public List<Long> getDraftProjectUbications(DraftProjectEntity draftProEntity) {
        List<Long> ubications = new ArrayList<Long>();
                
        
         List<DraftProjRegionalClassifierEntity> ubicationsSelects = 
                    new ArrayList<DraftProjRegionalClassifierEntity>();

         if(draftProEntity != null){
            ubicationsSelects =
                draftProjectRegClassDao.findUBicationsDraftProject(draftProEntity);
        }
        
         for(DraftProjRegionalClassifierEntity entityUb :ubicationsSelects){
             ubications.add(entityUb.getDraftProjectRegionalClassifierRegClassif().
                     getRegionalClassifierId());
             
         }
        
        return ubications;

    }

    @Override
    public Map<Long, List<RegionalClassifierDTO>> 
            saveDraftProjectUbication(List<String> ubicationSelected, 
            Long draftProjectId) {
        
        Map<Long, List<RegionalClassifierDTO>> result=
                new HashMap<Long, 
                        List<RegionalClassifierDTO>>();
        List<String> current;
        DraftProjRegionalClassifierEntity  entity;
        RegionalClassifierEntity ubicationFather;
        RegionalClassifierDTO dtoKey;
        DraftProjectEntity draftProjectEntity;
        
        draftProjectEntity = draftProjectDao.findById(draftProjectId, true);
        
        if(ubicationSelected != null ){
            draftProjectRegClassDao.deleteUbicationDraftProj(draftProjectId);
        }
        for(String idRegion:ubicationSelected){
           entity = new DraftProjRegionalClassifierEntity();
           ubicationFather = regionalClassifierDao.findById(Long.parseLong(idRegion), true);
           entity.setDraftProjRegClassifDraftProject(draftProjectEntity);
           entity.setDraftProjectRegionalClassifierRegClassif(ubicationFather);
           draftProjectRegClassDao.save(entity);
           dtoKey = new RegionalClassifierDTO();
           dtoKey.setFather(ubicationFather);
           result.put(Long.parseLong(idRegion),this.findDraftProjectUbication(
                   ubicationFather,
                   draftProjectEntity));
        }
       return result;
        
    }    
}
