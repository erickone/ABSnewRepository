package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.programming.dto.RegionalClassifierDTO;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
public interface RegionalClassifierDao extends SIIFBaseDao<RegionalClassifierEntity, Long>{

    Collection<RegionalClassifierEntity> getAllRegionalClassifier();

    @Override
    void saveAll(Collection<RegionalClassifierEntity> anEntities);

    @Override
    void deleteAll(Collection<RegionalClassifierEntity> anEntities);

    RegionalClassifierEntity getRegionalClassifierById(Long anIdentity);

    public List<RegionalClassifierEntity> findUbicationByScope(
            RegionalLevelClassifierEntity regionLevelentity);

    public List<RegionalClassifierDTO> findUbication(
            RegionalClassifierEntity ubication );
    
    public Long findFather(RegionalClassifierEntity region);
    
    public String findFatherDesc(RegionalClassifierEntity region);

    public List<RegionalClassifierEntity> getRegionsByFatherId(RegionalClassifierEntity aRegionFather);
    
    Collection<RegionalClassifierEntity> getRegionalClassifierWithoutFather();
    
    Long saveOrUpdateRegion(RegionalClassifierEntity aRegionalClassifierEntity);
    
    void deleteRegionalClassifier(RegionalClassifierEntity aRegionalClassifierEntity);
}