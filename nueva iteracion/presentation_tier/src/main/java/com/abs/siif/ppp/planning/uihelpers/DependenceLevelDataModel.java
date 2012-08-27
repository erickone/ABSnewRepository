/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependenceLevelDataModel
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.planning.entities.DependenceLevelEntity;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Erick Leija
 */
public class DependenceLevelDataModel
extends ListDataModel<DependenceLevelEntity>
        implements SelectableDataModel<DependenceLevelEntity> {

    public DependenceLevelDataModel() {
    }

    public DependenceLevelDataModel(List<DependenceLevelEntity> aDataModel) {
        super(aDataModel);
    }

    @Override
    public Object getRowKey(DependenceLevelEntity anEntity) {
        return anEntity.getDependenceLevelId();
    }

    @Override
    public DependenceLevelEntity getRowData(String string) {

        Long myLong = Long.parseLong(string);
        
        List<DependenceLevelEntity> myDependenceLevel = (List<DependenceLevelEntity>) getWrappedData();

        for (DependenceLevelEntity myDTO : myDependenceLevel) {
            if (myDTO.getDependenceLevelId().equals(myLong)) {
                return myDTO;
            }
        }
        return null;
    }

}
