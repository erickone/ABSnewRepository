package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
public interface RegionalLevelClassifierDao extends SIIFBaseDao<RegionalLevelClassifierEntity, Long>{

    Collection<RegionalLevelClassifierEntity> getAllRegionalLevelClassifier();

    void saveAll(Collection<RegionalLevelClassifierEntity> anEntities);

    void deleteAll(Collection<RegionalLevelClassifierEntity> anEntities);

    RegionalLevelClassifierEntity getRegionalLevelClassifierById(Long anIdentity);

    List<RegionalLevelClassifierEntity> findUbicationScope();
    
    Long saveOrUpdate(RegionalLevelClassifierEntity anEntity);
    
    int getLastRegionalLevel();
    
    void delete(List<RegionalLevelClassifierEntity> anIdentities);
    
    RegionalLevelClassifierEntity getRegionalClassifierLevelByLevel(int aFCLevel);
    
    boolean isRegClassifKeyValid(String aKey);
    
    RegionalLevelClassifierEntity existRegClassifLevelWithCensusActive(RegionalLevelClassifierEntity anRCLevel);
    
    RegionalLevelClassifierEntity existRegClassifLevelWithGenderActive(RegionalLevelClassifierEntity anRCLevel);
    
    RegionalLevelClassifierEntity existRegClassifLevelWithStateActive(RegionalLevelClassifierEntity anRCLevel);
    
    RegionalLevelClassifierEntity existRegClassifLevelWithMunicipalityActive(RegionalLevelClassifierEntity anRCLevel);
    
    RegionalLevelClassifierEntity existRegClassifLevelWithPostalCodeActive(RegionalLevelClassifierEntity anRCLevel);
}
