/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  DepToObjLinkControllerApi
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

import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.ppp.programming.uihelpers.DepToObjLinkDataModel;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author luis.carreon
 */
public interface DepToObjLinkControllerApi {

    List<DepencenceDto> getFatherList();

    DualListModel<DependenceEntity> getItsChildsDualList();

    String getItsEncuadreLevel();

    String getItsEncuadreLevelDesc();

    String getItsEncuadreName();

    DepToObjLinkDataModel getItsFatherDataModel();

    ObjectiveEntity getItsObjectiveEntity();

    DepencenceDto getSelectedFather();

    void init();

    void onRowSelect(SelectEvent event);

    void onRowUnselect(UnselectEvent event);

    void saveDepObjLink();

    void setFatherList(List<DepencenceDto> aFatherList);

    void setItsChildsDualList(DualListModel<DependenceEntity> aChildsDualList);

    void setItsEncuadreLevel(String itsEncuadreLevel);

    void setItsEncuadreLevelDesc(String itsEncuadreLevelDesc);

    void setItsEncuadreName(String itsEncuadreName);

    void setItsFatherDataModel(DepToObjLinkDataModel itsFatherDataModel);

    void setItsObjectiveEntity(ObjectiveEntity itsObjectiveEntity);

    void setSelectedFather(DepencenceDto aSelectedFather);
    
}
