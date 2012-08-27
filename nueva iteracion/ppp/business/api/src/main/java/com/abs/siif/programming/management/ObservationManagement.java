/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ObservationManagement
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

import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.programming.dto.ObservationDto;
import com.abs.siif.programming.entities.ObservationEntity;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface ObservationManagement {
    
    public List<ObservationDto> getObservationsByInvPreFileId(Long Id);
    
    public DepencenceDto getDependenceByUserId(Long Id);
    
    public ObservationEntity saveObservation(ObservationEntity myEntity);
    
    public void deleteObservation(ObservationEntity myEntity);
}
