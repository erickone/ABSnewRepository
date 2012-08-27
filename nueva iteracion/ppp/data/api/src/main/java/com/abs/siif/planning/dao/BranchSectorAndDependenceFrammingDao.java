/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.BranchAndSectorFrammingEntity;
import com.abs.siif.planning.entities.BranchSectorAndDependenceFrammingEntity;
import com.abs.siif.planning.entities.DependenceEntity;

/**
 *
 * @author Erick Leija
 */
public interface BranchSectorAndDependenceFrammingDao extends SIIFBaseDao<BranchSectorAndDependenceFrammingEntity, Long>
{
    BranchSectorAndDependenceFrammingEntity getFrammingByDependence(DependenceEntity aDependenceId);
    
}
