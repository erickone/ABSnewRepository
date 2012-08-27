/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.PhysicalProgrammedAdvanceEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface PhysicalProgrammedAdvanceDao extends SIIFBaseDao<PhysicalProgrammedAdvanceEntity, Long> {

    Collection<PhysicalProgrammedAdvanceEntity> getPhysicalProgrammedByInvPreFile(Long anIdentity);

    
}
