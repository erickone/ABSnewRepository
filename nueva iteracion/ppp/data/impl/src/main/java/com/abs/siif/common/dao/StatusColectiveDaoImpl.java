/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  StatusColectiveDaoImpl
 *  Purpose:  [Mapea los accesos a dato para el status de la colectiva ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.common.entities.StatusColectiveEntity;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("statusColectiveDao")
public class StatusColectiveDaoImpl
        extends SIIFBaseDaoImpl<StatusColectiveEntity, String>
        implements StatusColectiveDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<StatusColectiveEntity> getStatusColectives() {
        return super.getAllAndOrderByColumn("statusColectiveDescription");
    }
}
