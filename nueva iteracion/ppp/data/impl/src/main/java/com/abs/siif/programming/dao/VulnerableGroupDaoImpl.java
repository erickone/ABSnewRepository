/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.VulnerableGroupEntity;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("vulnerableGroupDao")
public class VulnerableGroupDaoImpl extends SIIFBaseDaoImpl<VulnerableGroupEntity, Long>
        implements VulnerableGroupDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Cacheable("vulnerableGroupsAll")
    @Transactional(readOnly = true)
    @Override
    public Collection<VulnerableGroupEntity> getVulnerableGroups() {
        return super.getAllAndOrderByColumn("vulnerableGroupDescription");
    }
}
