/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RequestUploadFilesManagement
 *  Purpose:  This interface contains the methods for the Uploaded files.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.management;

import com.abs.siif.budget.entities.RequestUploadFilesEntity;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface RequestUploadFilesManagement
{
    public List<RequestUploadFilesEntity> getUploadedFilesByReqId(Long id);
    
    public RequestUploadFilesEntity saveUploadedFile(RequestUploadFilesEntity anEntity);
    
    public RequestUploadFilesEntity getUploadedFileByPath(String aPath);
}
