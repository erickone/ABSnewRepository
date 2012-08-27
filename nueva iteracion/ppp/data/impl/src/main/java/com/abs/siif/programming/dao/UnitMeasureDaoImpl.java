/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.UnitMeasureEntity;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Repository("unitMeasureDaoImpl")
public class UnitMeasureDaoImpl extends SIIFBaseDaoImpl<UnitMeasureEntity, Long>
        implements UnitMeasureDao{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Cacheable("unitMeasureAll")
    @Transactional(readOnly = true)
    @Override
    public Collection<UnitMeasureEntity> getAllUnitMeasure() {
        return super.getAllAndOrderByColumn("unitMeasureDescription");
    }

    @CacheEvict(value="unitMeasureAll",allEntries=true)
    @Transactional(readOnly = false)
    @Override
    public void saveAll(Collection<UnitMeasureEntity> anEntities) {
        super.saveAll(anEntities);
    }

     @CacheEvict(value="unitMeasureAll",allEntries=true)
    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<UnitMeasureEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    @Transactional(readOnly = true)
    @Override
    public UnitMeasureEntity getUnitMeasureById(Long anIdentity) {
        return findById(anIdentity, Boolean.TRUE);
    }

    

}
