/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  PromoterManagementImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.programming.management;

import com.abs.siif.base.management.ManagementBase;
import com.abs.siif.programming.dao.PromoterDao;
import com.abs.siif.programming.entities.PromoterEntity;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("promoterManagement")
public class PromoterManagementImpl extends ManagementBase
implements PromoterManagement{

    @Resource(name = "promoterDao")
    PromoterDao promoterDao;
    
    @Override
    public Long savePromoter(PromoterEntity anEntity)
    {
        return promoterDao.save(anEntity).getIdPromoter();
    }

}