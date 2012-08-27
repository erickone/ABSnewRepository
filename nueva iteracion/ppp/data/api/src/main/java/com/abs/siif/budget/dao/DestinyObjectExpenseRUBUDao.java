/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DestinyObjectExpenseRUBUDao
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.dto.BudgetingSummaryDto;
import com.abs.siif.budget.entities.DestinyObjectExpenseRUBUEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.dto.DepencenceDto;
import java.util.Collection;

/**
 *
 * @author Erick Leija
 */
public interface DestinyObjectExpenseRUBUDao extends SIIFBaseDao<DestinyObjectExpenseRUBUEntity, Long>
{
    Collection<DestinyObjectExpenseRUBUEntity> getFrammingByRUkey(DepencenceDto aDependence,
            BudgetingSummaryDto aBudgetingDto, boolean investFlag);
    Collection<DestinyObjectExpenseRUBUEntity> getDestinationByObject(DepencenceDto aDependence,
            ObjectExpenseEntity aBudgetingDto);
    Collection<DestinyObjectExpenseRUBUEntity> getDestinationByObjectInv(DepencenceDto aDependence,
    ObjectExpenseEntity aBudgetingDto);
}
