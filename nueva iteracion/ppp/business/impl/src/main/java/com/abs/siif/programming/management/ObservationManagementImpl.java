/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ObservationManagementImpl
 *  Purpose:  Service for the Observations catalog related to InvPreFile.
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
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.programming.dao.ObservationDao;
import com.abs.siif.programming.dto.ObservationDto;
import com.abs.siif.programming.entities.ObservationEntity;
import com.abs.siif.security.dao.UserDao;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("observationManagement")
public class ObservationManagementImpl extends ManagementBase 
    implements ObservationManagement{

    @Resource(name = "observationDao")
    ObservationDao observationDao;
    
    @Resource(name = "userDao")
    UserDao userDao;
    
    @Override
    public List<ObservationDto> getObservationsByInvPreFileId(Long Id) {
        return observationDao.getObservationsByInvPreFileId(Id);
    }

    @Override
    public DepencenceDto getDependenceByUserId(Long Id) {
        return userDao.getDependenceByUserId(Id);
    }

    @Override
    public ObservationEntity saveObservation(ObservationEntity myEntity) {
        return observationDao.save(myEntity);
    }

    @Override
    public void deleteObservation(ObservationEntity myEntity) {
        observationDao.delete(myEntity);
    }
    
}
