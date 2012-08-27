/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ViewUserModuleDao
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.security.dto.ViewConstrainsDto;
import com.abs.siif.security.entities.ViewUserModuleEntity;
import java.util.List;

/**
 *
 * @author Israel Ruiz
 */
public interface ViewUserModuleDao extends SIIFBaseDao<ViewUserModuleEntity, Long>{
 
    /**
     * Obtiene una lista de DTO lso cuales traen la información en
     * base el usuario y los constrains que de bera de aplicar a la vista
     * en base al año y elemento de la definicióin de la clave presupuestal
     * @return 
     */ 
    public List<ViewConstrainsDto> getUserConstrains(String itemLevelAttribute);
}
