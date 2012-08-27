/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.catalog.management;

import com.abs.siif.catalog.dao.TypeConfigCatalogDao;
import com.abs.siif.catalog.entities.TypeConfigCatalogEntities;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author absvalenzuela
 */
@Service("typeConfigCatalogManagement")
public class TypeConfigCatalogManagementImpl implements TypeConfigCatalogManagement, Serializable {

    @Resource(name = "typeConfigCatalogDao")
    private transient TypeConfigCatalogDao itsTypeConfigCatalogDao;
    
    @Override
    public List<TypeConfigCatalogEntities> getListTypeConfigCatalog() {
        return itsTypeConfigCatalogDao.getListTypeConfigCatalog();
    }
    
}
