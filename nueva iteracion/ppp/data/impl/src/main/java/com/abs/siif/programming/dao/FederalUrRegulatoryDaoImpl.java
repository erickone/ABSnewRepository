/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FederalUrRegulatoryDaoImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.FederalURRegulatoryEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FENIX-02
 */
@Service("federalUrRegulatoryDao")
public class FederalUrRegulatoryDaoImpl extends SIIFBaseDaoImpl<FederalURRegulatoryEntity, Long> implements
        FederalUrRegulatoryDao{

    @Autowired
    private SessionFactory theirSessionFactory;
    
    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<FederalURRegulatoryEntity> getAllFederalURRegulatories()
    {
        String myQueryString = "select distinct urn from FederalURRegulatoryEntity urn"
                + " order by urn.federalUrRegulatoryKey"; 
        
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);
        return myQuery.list();
    }
    
    
    @Transactional(readOnly = true)
    @Override
    public FederalURRegulatoryEntity getDefaultFederalURN() {
        String myQueryString = "FROM FederalURRegulatoryEntity as URR"
                + " WHERE URR.federalUrRegulatoryDescription like 'No seleccionado' ";

        Query myQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);
   
        return (FederalURRegulatoryEntity)myQuery.uniqueResult();
    }

}
