/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.ActionGBDao;
import com.abs.siif.programming.entities.ActionGBEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("actionGBManagement")
public class ActionGBManagementImpl implements ActionGBManagement {

    @Resource(name = "actionGBDao")
    private ActionGBDao itsActionGBDao;

    @Override
    public Collection<ActionGBEntity> getActionGBs() {
        return itsActionGBDao.getActionGBs();
    }

    @Override
    public Collection<ActionGBEntity> getActionsByBuildingConcept(Long anIdentity) {
        return itsActionGBDao.getActionsByBuildingConcept(anIdentity);
    }
}
