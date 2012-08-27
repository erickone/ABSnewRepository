/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.entities.FederalDependenceEntity;
import java.util.Collection;

/**
 *
 * @author jacob.flores
 */
public interface FederalDependenceDao extends SIIFBaseDao<FederalDependenceEntity, Long>
{
  Collection<FederalDependenceEntity> getListOfFederalDependences();
}