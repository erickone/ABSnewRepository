/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalPlanWithDraftProjectDao
 *  Purpose:  [ este dao se encarga de guardar todas las selecciones hechas
 *  en la pantalla de Vinculación del PED]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.RegionalPlansOfPEDEntity;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface RegionalPlanWithDraftProjectDao 
{
     Long saveRegionalPlanAndDraftProject(RegionalPlansOfPEDEntity anEntity);
     
     Long persistsaveRegionalPlanAndDraftProject(RegionalPlansOfPEDEntity anEntity);
    
    List<RegionalPlansOfPEDEntity> getRegionalPlanByDraftProjectId(Long aDraftProjectId);
    
    String deleteAllRegionalPlansByDraftProjectIDAndRegionClassifierId
            (Long aDraftProjectId,Long aRegionalClassifier);
    
    
}
