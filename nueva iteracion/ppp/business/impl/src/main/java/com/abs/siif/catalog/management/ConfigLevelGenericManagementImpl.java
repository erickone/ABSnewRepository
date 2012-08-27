/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.catalog.management;

import com.abs.siif.catalog.dao.ConfigLevelGenericDao;
import com.abs.siif.catalog.entities.ConfigLevelGenericEntities;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author absvalenzuela
 */
@Service("configLevelGenericManagement")
public class ConfigLevelGenericManagementImpl implements ConfigLevelGenericManagement, Serializable{
 
    @Resource(name = "configLevelGenericDao")
    private transient ConfigLevelGenericDao itsConfigLevelGenericDao;
    
    @Override
    public List<ConfigLevelGenericEntities> getListLevelGeneric(Long typeConfigId, Integer year){
        return this.itsConfigLevelGenericDao.getListLevelGeneric(typeConfigId, year);
    }
    
    @Override
    public List<ConfigLevelGenericEntities> getListLevelGeneric(){
        return this.itsConfigLevelGenericDao.getListLevelGeneric();
    }
}
