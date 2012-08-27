/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.catalog.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.catalog.entities.ConfigLevelGenericEntities;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author absvalenzuela
 */
@Repository("configLevelGenericDao")
public class ConfigLevelGenericDaoImpl extends SIIFBaseDaoImpl<ConfigLevelGenericEntities, Long>
        implements ConfigLevelGenericDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ConfigLevelGenericEntities> getListLevelGeneric(Long typeConfigId, Integer year) {
        String myQueryHQL = "select distinct CCE from ConfigLevelGenericEntities CLGE"
                + " inner join fetch CCE.typeConfigEntity"
                + " where CCE.typeConfigEntity.activo = 't'"
                + " and CCE.configLevelGenericId = :configId"
                + " and CCE.year = :yearLevel";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryHQL);


        mySQLQuery.setLong("configId", typeConfigId);
        mySQLQuery.setLong("yearLevel", year);

        return mySQLQuery.list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ConfigLevelGenericEntities> getListLevelGeneric() {

        return super.findAll();
    }
}
