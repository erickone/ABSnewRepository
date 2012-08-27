/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.MacroBuildDao;
import com.abs.siif.programming.entities.MacroBuildEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("macroBuildManagement")
public class MacroBuildManagementImpl implements MacroBuildManagement{

    @Resource(name="macroBuildDao")
    private MacroBuildDao itsMacroBuildDao;
    
    @Override
    public Collection<MacroBuildEntity> getMacroBuilds() {
       return itsMacroBuildDao.getMacroBuilds();
    }
    
    @Override
    public MacroBuildEntity getDefaultMacroBuild() {
       return itsMacroBuildDao.getDefaultMacroBuild();
    }
    
}
