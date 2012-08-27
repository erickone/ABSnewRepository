/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DepToObjLinkDao
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface DepToObjLinkDao extends SIIFBaseDao<DependenceEntity, Long>
{
    public List<DependenceEntity> getFathersList();
    
    public DependenceLevelEntity getFatherLevel();
    
    public List<DependenceEntity> getChildsList(Long aFatherId);
    
    public List<DependenceEntity> getChildsRelatedObjList(Long aDepFatherId, Long anObjectiveId);
    
    public boolean saveDepToObjRelation(ObjectiveEntity anObjectiveEntity, Long idpadre);
}
