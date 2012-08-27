/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.BenefAndGoalsEntity;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface BenefAndGoalsDao extends SIIFBaseDao<BenefAndGoalsEntity, Long> {
    BenefAndGoalsEntity getBenefAndGoalsByInvPreFile(Long invPreFileId);
}
