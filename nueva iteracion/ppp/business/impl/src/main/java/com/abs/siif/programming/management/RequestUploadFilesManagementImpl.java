/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RequestUploadFilesManagementImpl
 *  Purpose:  This class controls the operations for Uploaded Files.
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
import com.abs.siif.budget.entities.UploadFileTypeEntity;
import com.abs.siif.programming.dao.RequestUploadFilesDao;
import com.abs.siif.programming.dao.UploadFileTypeDao;
import com.abs.siif.support.UploadFileType;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("requestUploadFilesManagement")
@Scope("session")
public class RequestUploadFilesManagementImpl implements RequestUploadFilesManagement, Serializable{

    @Resource(name = "requestUploadFilesDao")
    RequestUploadFilesDao requestUploadFilesDao;
    @Resource(name = "uploadFileTypeDao")
    UploadFileTypeDao uploadFileTypeDao;
    
    @Override
    public List<RequestUploadFilesEntity> getUploadedFilesByReqId(Long id)
    {
        return requestUploadFilesDao.getUploadedFilesByReqId(id);
    }

    @Override
    public RequestUploadFilesEntity saveUploadedFile(RequestUploadFilesEntity anEntity)
    {
        RequestUploadFilesEntity myRUF = getUploadedFileByPath(anEntity.getRequestUpLoadFilePath());
        if (myRUF != null)
            return myRUF;
        else{
            getUploadedFileInfo(anEntity);
        }
        return requestUploadFilesDao.save(anEntity);
    }
    
    private void getUploadedFileInfo(RequestUploadFilesEntity anEntity){
        
        int index = anEntity.getRequestUpLoadFilePath().lastIndexOf(".");
        String extension = anEntity.getRequestUpLoadFilePath().substring(++index);
        UploadFileType myType;
        UploadFileTypeEntity myUFT = null;
        try{
            myType = UploadFileType.valueOf(extension.toUpperCase());
            myUFT = uploadFileTypeDao.getUploadFileTypeByKey(myType.getKey());
        }
        catch(Exception e){
            myUFT = uploadFileTypeDao.getUploadFileTypeByKey("02");
        }
        index = anEntity.getRequestUpLoadFilePath().lastIndexOf("\\");
        String name = anEntity.getRequestUpLoadFilePath().substring(++index);
        anEntity.setUpLoadfileType(myUFT);
        anEntity.setRequestUpLoadFileDesc(myUFT.getUpLoadFileTypeDesc());
        anEntity.setReqPreInversionKey(myUFT.getUpLoadFileTypeKey());
        anEntity.setName(name);
        
    }

    /**
     * Este metodo verifica primero si ya existe en BD una direccion de archivo
     * igual a la que se quiere dar de alta.
     * @param aPath
     * @return 
     */
    @Override
    public RequestUploadFilesEntity getUploadedFileByPath(String aPath)
    {
        return requestUploadFilesDao.getUploadedFileByPath(aPath);
    }

}
