/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AdministrativeClassifierTypeManagement
 *  Purpose:[ Maneja las reglas de negocio del tipo de clasificacion dependencia]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.management;

import com.abs.siif.planning.entities.AdministrativeClassifierTypeEntity;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public interface AdministrativeClassifierTypeManagement {

    Collection<AdministrativeClassifierTypeEntity> getAdministrativeClassifierTypes();
    
}
