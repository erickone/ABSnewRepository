/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  UploadFileType
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.support;

/**
 *
 * @author FENIX-02
 */
public enum UploadFileType
{
    //Imagen
    BMP("01"),
    GIF("01"),
    JPEG("01"),
    JPG("01"),
    JPG2("01"),
    JP2("01"),
    PNG("01"),
    //Texto
    DOC("02"),
    DOCX("02"),
    DOCXML("02"),
    LOG("02"),
    MSG("02"),
    ODT("02"),
    README("02"),
    RTF("02"),
    RTX("02"),
    TEXT("02"),
    TXT("02"),
    WTX("02"),
    //PDF
    PDF("03");
    
    private String key = "02";

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
    
    private UploadFileType(String key){
        this.key = key;
    }
}
