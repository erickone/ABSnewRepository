/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.InvPreFileDetailEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abs71
 */

@Repository("invPreFileDetailDao")
public class InvPreFileDetailDaoImpl extends SIIFBaseDaoImpl<InvPreFileDetailEntity, Long> implements InvPreFileDetailDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public void saveInvPreFileDetailDao(InvPreFileDetailEntity entity) {
            super.save(entity);
    }
    
}
