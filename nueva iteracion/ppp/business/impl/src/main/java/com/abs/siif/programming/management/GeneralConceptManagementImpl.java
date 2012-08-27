/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.GeneralConceptDao;
import com.abs.siif.programming.entities.GeneralConceptEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("generalConceptManagement")
public class GeneralConceptManagementImpl implements GeneralConceptManagement {

    @Resource(name = "generalConceptDao")
    private GeneralConceptDao itsGeneralConceptDao;

    @Override
    public Collection<GeneralConceptEntity> getGeneralConcepts() {
        return itsGeneralConceptDao.getGeneralConcepts();
    }
}
