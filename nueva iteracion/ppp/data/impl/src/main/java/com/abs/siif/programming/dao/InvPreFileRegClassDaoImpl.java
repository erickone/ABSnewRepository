/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.entities.InvPreFileRegionalClassifierEntity;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Israel Ruiz
 */
@Repository("invPreFileRegionalClassifierDao")
public class InvPreFileRegClassDaoImpl extends 
        SIIFBaseDaoImpl<InvPreFileRegionalClassifierEntity, Long> implements 
        InvPreFileRegionalClassifierDao{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Override
    @Transactional
    public List<InvPreFileRegionalClassifierEntity> findUBications(
            InvPreFileEntity invPreFile) {
        String hqlUpdate = "from InvPreFileRegionalClassifierEntity inPre "
                + " where inPre.invPreFileEntity.invPreFileId = "
                + invPreFile.getInvPreFileId()+"";
        return this.find(hqlUpdate);
    }

    @Override
    @Transactional
    public void deleteByInvPreFile(InvPreFileEntity invPreFile) {
     String hqlUpdate = "delete InvPreFileRegionalClassifierEntity"
             + " entity where entity.invPreFileEntity.invPreFileId = "
             + ":idIvnPre";

              getTheirSessionFactory().getCurrentSession().
              createQuery( hqlUpdate).
              setLong("idIvnPre", invPreFile.getInvPreFileId() )       
              .executeUpdate();
    }

    
}
