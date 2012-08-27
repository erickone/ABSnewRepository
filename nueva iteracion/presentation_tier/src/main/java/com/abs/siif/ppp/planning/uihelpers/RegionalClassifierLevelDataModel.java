/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalClassifierLevelDataModel
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

import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author FENIX-02
 */
public class RegionalClassifierLevelDataModel extends ListDataModel<RegionalLevelClassifierEntity>
        implements Serializable, SelectableDataModel<RegionalLevelClassifierEntity>{

    public RegionalClassifierLevelDataModel() {
    }

    public RegionalClassifierLevelDataModel(List<RegionalLevelClassifierEntity> aDataModel) {
        super(aDataModel);
    }
    
    @Override
    public Object getRowKey(RegionalLevelClassifierEntity anEntity)
    {
        return anEntity.getRegionalLevelClassifierId();
    }

    @Override
    public RegionalLevelClassifierEntity getRowData(String rowKey)
    {
        List<RegionalLevelClassifierEntity> myRCLeves = (List<RegionalLevelClassifierEntity>) getWrappedData();
        RegionalLevelClassifierEntity myEntity = new RegionalLevelClassifierEntity();
        myEntity.setRegionalLevelClassifierId(Long.parseLong(rowKey));
        int myIndex = Collections.binarySearch(myRCLeves, myEntity);
        
        return myRCLeves.get(myIndex);
    }

}
