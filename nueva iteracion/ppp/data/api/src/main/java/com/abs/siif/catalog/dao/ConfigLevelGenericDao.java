/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.catalog.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.catalog.entities.ConfigLevelGenericEntities;
import java.util.List;

/**
 *
 * @author absvalenzuela
 */
public interface ConfigLevelGenericDao  extends SIIFBaseDao<ConfigLevelGenericEntities, Long>{
    
    public List<ConfigLevelGenericEntities> getListLevelGeneric(Long typeConfigId, Integer year);
    
    public List<ConfigLevelGenericEntities> getListLevelGeneric();
}
