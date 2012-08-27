/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.security.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.security.entities.ProfileEntity;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis.carreon
 */
@Repository("profileDao")
public class ProfileDaoImpl extends SIIFBaseDaoImpl<ProfileEntity, Long> 
        implements ProfileDao, Serializable{
    
    @Autowired
    private SessionFactory itsSessionFactory;
    
    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ProfileEntity> getUserProfilesById(Long aUserId)
    {

        String myQueryString = "SELECT DISTINCT prof FROM ProfileEntity prof"
                + " left join fetch prof.profileUser PU "
                + " WHERE PU.userId = " + aUserId ;

        return this.find(myQueryString);       
    }

    @Transactional(readOnly = false)
    @Override
    public ProfileEntity save(ProfileEntity anEntity) {
        return super.save(anEntity);
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(ProfileEntity anEntity) {
        super.delete(anEntity);
    }

    
    
}
