/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.catalog.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.catalog.entities.TypeConfigCatalogEntities;
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
@Repository("typeConfigCatalogDao")
public class TypeConfigCatalogDaoImpl extends SIIFBaseDaoImpl<TypeConfigCatalogEntities, Long> 
implements TypeConfigCatalogDao{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Override
    @Transactional(readOnly = false)
    public List<TypeConfigCatalogEntities> getListTypeConfigCatalog() {
        String myQueryHQL = "select TCCE from TypeConfigCatalogEntities TCCE"
                + " where TCCE.activo = 't' ";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryHQL);


        return mySQLQuery.list();
    }
    
}
