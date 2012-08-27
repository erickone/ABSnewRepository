/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.InvestmentLineEntity;
import com.abs.siif.programming.entities.MacroBuildEntity;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("macroBuildDao")
public class MacroBuildDaoImpl extends SIIFBaseDaoImpl<MacroBuildEntity, String>
implements MacroBuildDao{

    @Autowired 
    private SessionFactory itsSessionFactory;
    
    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Cacheable("macroBuildsAll")
    @Transactional(readOnly=true)
    @Override
    public Collection<MacroBuildEntity> getMacroBuilds() {
        return super.getAllAndOrderByColumn("macroBuildDescription");
    }
    
    @Transactional(readOnly=true)
    @Override
    public MacroBuildEntity getDefaultMacroBuild(){
        String myQueryString = "FROM MacroBuildEntity as macroBuild"
                + " WHERE macroBuild.macroBuildDescription like 'No seleccionado' ";

        Query myQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);
   
        return (MacroBuildEntity)myQuery.uniqueResult();
    }
    
}
