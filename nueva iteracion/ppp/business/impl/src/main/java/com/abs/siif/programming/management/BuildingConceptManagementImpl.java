/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.BuildingConceptDao;
import com.abs.siif.programming.entities.BuildingConceptEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("buildingConceptManagement")
public class BuildingConceptManagementImpl implements BuildingConceptManagement {

    @Resource(name = "buildingConceptDao")
    private BuildingConceptDao itsBuildingConceptDao;

    @Override
    public Collection<BuildingConceptEntity> getBuildingConceptsByConceptGeneral(Long anIdentity) {
        return itsBuildingConceptDao.getBuildingConceptsByConceptGeneral(anIdentity);
    }
}
