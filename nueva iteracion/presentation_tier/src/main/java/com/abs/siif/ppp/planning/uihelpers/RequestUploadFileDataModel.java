/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RequestUploadFileDataModel
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

import com.abs.siif.budget.entities.RequestUploadFilesEntity;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author FENIX-02
 */
public class RequestUploadFileDataModel extends ListDataModel<RequestUploadFilesEntity>
        implements Serializable, SelectableDataModel<RequestUploadFilesEntity>{

    public RequestUploadFileDataModel() {
    }

    public RequestUploadFileDataModel(List<RequestUploadFilesEntity> aDataModel) {
        super(aDataModel);
    }
    
    @Override
    public Object getRowKey(RequestUploadFilesEntity anEntity)
    {
        return anEntity.getRequestUpLoadFileId();
    }

    @Override
    public RequestUploadFilesEntity getRowData(String rowKey)
    {
        List<RequestUploadFilesEntity> myRUFiles = (List<RequestUploadFilesEntity>) getWrappedData();
        RequestUploadFilesEntity myEntity = new RequestUploadFilesEntity();
        myEntity.setRequestUpLoadFileId(Long.parseLong(rowKey));
        int myIndex = Collections.binarySearch(myRUFiles, myEntity);
        
        return myRUFiles.get(myIndex);
    }

}
