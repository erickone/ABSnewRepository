/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierLevelDataModel
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

import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author FENIX-02
 */
public class FunctionalClassifierLevelDataModel extends ListDataModel<FunctionalLevelClassifier>
        implements Serializable, SelectableDataModel<FunctionalLevelClassifier>{

    public FunctionalClassifierLevelDataModel() {
    }

    public FunctionalClassifierLevelDataModel(List<FunctionalLevelClassifier> aDataModel) {
        super(aDataModel);
    }
    
    @Override
    public Object getRowKey(FunctionalLevelClassifier anEntity)
    {
        return anEntity.getFunctionalLevelClassifierId();
    }

    @Override
    public FunctionalLevelClassifier getRowData(String rowKey)
    {
        List<FunctionalLevelClassifier> myFCLeves = (List<FunctionalLevelClassifier>) getWrappedData();
        FunctionalLevelClassifier myEntity = new FunctionalLevelClassifier();
        myEntity.setFunctionalLevelClassifierId(Long.parseLong(rowKey));
        int myIndex = Collections.binarySearch(myFCLeves, myEntity);
        
        return myFCLeves.get(myIndex);
    }

}
