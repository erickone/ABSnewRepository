/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.DraftProjectTypeEntity;
import java.io.Serializable;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala
 */
@Repository("draftProjectTypeDaoImpl")
public class DraftProjectTypeDaoImpl extends SIIFBaseDaoImpl<DraftProjectTypeEntity, Long>
        implements DraftProjectTypeDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Cacheable("projectTypesAll")
    @Transactional(readOnly = true)
    @Override
    public Collection<DraftProjectTypeEntity> getAllDraftProjectTypes() {
        return super.findAll();
    }

    @CacheEvict(value = "projectTypesAll", allEntries = true)
    @Transactional(readOnly = false)
    @Override
    public void saveAll(Collection<DraftProjectTypeEntity> anEntities) {
        super.saveAll(anEntities);
    }

    @CacheEvict(value = "projectTypesAll", allEntries = true)
    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<DraftProjectTypeEntity> anEntities) {
        super.deleteAll(anEntities);
    }
}
