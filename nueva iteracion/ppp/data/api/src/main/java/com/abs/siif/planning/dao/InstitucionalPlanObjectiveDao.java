/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  InstitucionalPlanObjectiveDao
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import com.abs.siif.planning.entities.InstitutionalPlanObjectiveEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Francisco Luna
 */
public interface InstitucionalPlanObjectiveDao extends SIIFBaseDao<InstitutionalPlanObjectiveEntity, Long>{
    
    void saveInstitucionalObjective(List<InstitutionalPlanObjectiveEntity> listToSave);
    List<InstitutionalPlanObjectiveEntity>searchObjectives(InstitutionalPlanEntity input);
    void deleteInstitucionalObjective(List<InstitutionalPlanObjectiveEntity> listToSave);
}
