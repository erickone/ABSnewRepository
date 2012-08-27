/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.DraftProjectStateEntity;
import java.io.Serializable;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala
 */
@Repository("draftProjectStateDaoImpl")
public class DraftProjectStateDaoImpl extends SIIFBaseDaoImpl<DraftProjectStateEntity, Long>
           implements DraftProjectStateDao
{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DraftProjectStateEntity> getAllDraftProjectState() 
    {
        return super.findAll();
    }

    @Transactional(readOnly = false)
    @Override
    public void saveAll(Collection<DraftProjectStateEntity> myEntities)
    {
        super.saveAll(myEntities);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<DraftProjectStateEntity> myEntities) 
    {
        super.deleteAll(myEntities);
    }
  
}