/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.PromoterEntity;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Israel Ruiz
 */
@Repository("promoterDao")
public class PromoterDaoImpl extends SIIFBaseDaoImpl<PromoterEntity, Long> 
    implements PromoterDao{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;

    }

    @Cacheable("SponsorsAll")
    @Override
    public List<PromoterEntity> findAll() {
        return super.findAll();
    }
}
