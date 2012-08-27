/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AdministrativeClassifierTypeManagement
 *  Purpose:  [Implementa reglas de negocio para el tio de clasificacion
 * de dependencia]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.management;

import com.abs.siif.planning.dao.AdministrativeClassifierTypeDao;
import com.abs.siif.planning.entities.AdministrativeClassifierTypeEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("administrativeClassifierTypeManagement")
public class AdministrativeClassifierTypeManagementImpl
implements AdministrativeClassifierTypeManagement {

    @Resource(name = "administrativeClassifierTypeDao")
    private AdministrativeClassifierTypeDao itsAdministrativeClassifierTypeDao;

    @Override
    public Collection<AdministrativeClassifierTypeEntity> getAdministrativeClassifierTypes() {
        return itsAdministrativeClassifierTypeDao.getAdministrativeClassifiers();
    }
}
