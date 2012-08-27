/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.DraftProjectStateDao;
import com.abs.siif.programming.entities.DraftProjectStateEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("draftProjectStateManagement")
public class DraftProjectStateManagementImpl implements DraftProjectStateManagement,Serializable 
{

    @Resource(name = "draftProjectStateDaoImpl")
    private DraftProjectStateDao theirDraftProjectStateDao;

    @Override
    public Collection<DraftProjectStateEntity> getDraftProjectState() 
    {
        return theirDraftProjectStateDao.getAllDraftProjectState();
    }
}