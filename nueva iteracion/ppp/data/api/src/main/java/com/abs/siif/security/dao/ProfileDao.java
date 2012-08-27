/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.security.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.security.entities.ProfileEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author luis.carreon
 */
public interface ProfileDao extends SIIFBaseDao<ProfileEntity, Long>{
    
    List<ProfileEntity> getUserProfilesById(Long aUserId);
    
    @Override
    void saveAll(Collection<ProfileEntity> anEntities);

    @Override
    void deleteAll(Collection<ProfileEntity> anEntities);
    
}
