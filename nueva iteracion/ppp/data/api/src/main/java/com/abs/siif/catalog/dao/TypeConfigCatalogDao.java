/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.catalog.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.catalog.entities.TypeConfigCatalogEntities;
import java.util.List;

/**
 *
 * @author absvalenzuela
 */
public interface TypeConfigCatalogDao extends SIIFBaseDao<TypeConfigCatalogEntities, Long>{
    
    public List<TypeConfigCatalogEntities> getListTypeConfigCatalog();
    
    
}
