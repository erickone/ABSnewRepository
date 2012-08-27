/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.dto.DestinationDto;
import com.abs.siif.budget.entities.DestinationEntity;
import java.util.Collection;

/**
 *
 * @author Erick Leija
 */
public interface DestinationDao extends SIIFBaseDao<DestinationEntity, Long>
{
    Collection<DestinationEntity> getDestinyByObjectExpense(Long anObjectExpenseId);
    DestinationEntity getDestinyById(Long aDestinationId);
    DestinationEntity getDestinationByKey(String aKey);
    DestinationDto getEncDepObjGasDest(Long anObjectExpenseId, Long aDependenceId, String aDestinyKey);
}
