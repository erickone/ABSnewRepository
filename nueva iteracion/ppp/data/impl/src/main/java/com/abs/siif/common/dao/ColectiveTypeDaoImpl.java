/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ColectiveTypeDaoImpl
 *  Purpose:  [ Implementacion de los metodos del tipo de colectiva]
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
import com.abs.siif.common.entities.ColectiveTypeEntity;
import java.io.Serializable;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abs70
 */
@Repository("colectiveTypeDao")
public class ColectiveTypeDaoImpl
        extends SIIFBaseDaoImpl<ColectiveTypeEntity, String>
        implements ColectiveTypeDao {

    
    @Autowired
    private SessionFactory itsSessionFactory;
    
    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Transactional(readOnly=true)
    @Override
    public Collection<ColectiveTypeEntity> getColectiveTypes() {
        return super.getAllAndOrderByColumn("colectiveTypeDescription");
    }
}
