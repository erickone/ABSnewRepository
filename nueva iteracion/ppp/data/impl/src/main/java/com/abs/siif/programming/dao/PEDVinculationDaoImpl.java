/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  PEDVinculationDaoImpl
 *  Purpose:  [ esta clase es la encargada de las llamadas a la base de datos  ]
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
import com.abs.siif.programming.entities.PEDVinculationEntity;
import java.io.Serializable;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
@Repository("pedVinculationDao")
public class PEDVinculationDaoImpl
extends SIIFBaseDaoImpl<PEDVinculationEntity, Serializable> 
implements PEDVinculationDao
{

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
    public PEDVinculationEntity getPEDVinculationById(Long aDraftProjectId) 
    {
      String myQueryString = "select distinct PED from PEDVinculationEntity PED"
             + " where PED.draftProjectId = " + aDraftProjectId + "";
      PEDVinculationEntity myEntity = null;
      if (super.find(myQueryString).size()>0)
      {
          myEntity = (PEDVinculationEntity) super.find(myQueryString).get(0);
      }
     
     return myEntity;
    }

    @Transactional(readOnly = false)
    @Override
    public Long savePEDVinculation(PEDVinculationEntity aPEDVinculation) 
    {
        Long myPEDId;
        if (aPEDVinculation.getVinculationId()==null)
        {
            myPEDId = super.save(aPEDVinculation).getVinculationId();
        }    
        else
        {
            myPEDId = super.merge(aPEDVinculation).getVinculationId();
        }
       return myPEDId;
    }
    
}
