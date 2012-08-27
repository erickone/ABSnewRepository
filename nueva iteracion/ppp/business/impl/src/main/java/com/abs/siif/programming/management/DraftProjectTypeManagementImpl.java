/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.DraftProjectTypeDao;
import com.abs.siif.programming.entities.DraftProjectTypeEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */

@Service("draftProjectTypeManagement")
public class DraftProjectTypeManagementImpl implements DraftProjectTypeManagement,Serializable
{

    @Resource(name="draftProjectTypeDaoImpl")
    private DraftProjectTypeDao theirDraftProjectTypeDao;
    
    @Override
    public Collection<DraftProjectTypeEntity> getDraftProjectTypes() {
        return theirDraftProjectTypeDao.getAllDraftProjectTypes();
    }
    
    
}
