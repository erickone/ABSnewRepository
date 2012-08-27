/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AdministrativeClassifierTypeDaoImpl
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

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.entities.AdministrativeClassifierTypeEntity;
import java.io.Serializable;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("administrativeClassifierTypeDao")
public class AdministrativeClassifierTypeDaoImpl
extends SIIFBaseDaoImpl<AdministrativeClassifierTypeEntity, Serializable>
 implements  AdministrativeClassifierTypeDao {

    @Autowired
    private SessionFactory itsSessionFactory;
    
    @Transactional(readOnly=true)
    @Override
    public Collection<AdministrativeClassifierTypeEntity> getAdministrativeClassifiers() {
        return super.getAllAndOrderByColumn("adminClassTypeDescription");
    }

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }
    
}
