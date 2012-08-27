/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  StatusColectiveManagement
 *  Purpose:  [ Implementa las reglas de negocio para el estatus colectiva ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common;

import com.abs.siif.common.dao.StatusColectiveDao;
import com.abs.siif.common.entities.StatusColectiveEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("statusColectiveManagement")
public class StatusColectiveManagementImpl implements StatusColectiveManagement {

    @Resource(name = "statusColectiveDao")
    private StatusColectiveDao itsStatusColectiveDao;

    @Override
    public Collection<StatusColectiveEntity> getStatusColectives() {
        return itsStatusColectiveDao.getStatusColectives();
        
    }
}
