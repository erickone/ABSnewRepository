/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.VulnerableGroupDao;
import com.abs.siif.programming.entities.VulnerableGroupEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("vulnerableGroupManagement")
public class VulnerableGroupManagementImpl implements VulnerableGroupManagement{

    @Resource(name="vulnerableGroupDao")
    private VulnerableGroupDao itsVulnerableGroupDao;
    
    @Override
    public Collection<VulnerableGroupEntity> getVulnerableGroups() {
        return itsVulnerableGroupDao.getVulnerableGroups();
    }
    
}
