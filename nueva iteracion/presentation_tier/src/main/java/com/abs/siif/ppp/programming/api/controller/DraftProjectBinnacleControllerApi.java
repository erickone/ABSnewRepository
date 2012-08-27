/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  DraftProjectBinnacleControllerApi
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.api.controller;

import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.util.Collection;

/**
 *
 * @author luis.carreon
 */
public interface DraftProjectBinnacleControllerApi {

    /**
     * @return the TheirDraftProjectNumber
     */
    String getDraftProjectNumber();

    /**
     * @return the theirBinnacleCollection
     */
    Collection<DraftProjectBinnacleEntity> getTheirBinnacleCollection();

    /**
     * @return the theirDraftProjectEntity
     */
    DraftProjectEntity getTheirDraftProjectEntity();

    void initBinnacle();

    /**
     * @param TheirDraftProjectNumber the TheirDraftProjectNumber to set
     */
    void setDraftProjectNumber(String TheirDraftProjectNumber);

    /**
     * @param theirBinnacleCollection the theirBinnacleCollection to set
     */
    void setTheirBinnacleCollection(Collection<DraftProjectBinnacleEntity> theirBinnacleCollection);

    /**
     * @param theirDraftProjectEntity the theirDraftProjectEntity to set
     */
    void setTheirDraftProjectEntity(DraftProjectEntity theirDraftProjectEntity);
    
}
