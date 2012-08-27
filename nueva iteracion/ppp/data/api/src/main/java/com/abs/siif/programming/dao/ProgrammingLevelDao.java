/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.ProgrammingLevelEntity;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
public interface ProgrammingLevelDao extends SIIFBaseDao<ProgrammingLevelEntity, Long> {
    
    ProgrammingLevelEntity getProgrammingLevelPreFichaForId();
}
