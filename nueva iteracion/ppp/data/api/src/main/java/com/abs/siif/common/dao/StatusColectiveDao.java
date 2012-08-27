/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  StatusColectiveDao
 *  Purpose:  [Mapea los metodos de acceso para el estatus de la colectiva]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.dao;

import com.abs.siif.common.entities.StatusColectiveEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface StatusColectiveDao {
    
    Collection<StatusColectiveEntity> getStatusColectives();
}
