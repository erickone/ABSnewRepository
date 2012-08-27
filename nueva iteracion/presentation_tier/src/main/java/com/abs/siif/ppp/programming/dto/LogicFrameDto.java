/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  LogicFrameDto
 *  Purpose:  [ esta clase es un Dto, lo que significa es: que es una vista
 *  de la entidad de logicFrameEntity, en la cual tenemos unos joins para
 *  mostrar el nombre del usuario y la descripcion del tipo de usuario, ya 
 *  que en pantalla no podemos mostrar solo las claves, que son las que se
 *  guardan en la base de datos]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.dto;

import java.io.Serializable;

/**
 *
 * @author Erick Leija
 */
public class LogicFrameDto implements Serializable
{
    private Long itsLogicFrameId;
    private String itsTreeType;
    private String itsDocumentName;
    private String itsDocumentDetail;
    private String itsDocumentPath;
    private Long itsTypeDocumentId;
    private Long itsUserId;
    
    private String itsTreeTypeDescription;
    private String itsTypeDocumentDescription;
    private String itsUserName;
   
    
    //Este es el Constructor

    public LogicFrameDto(Long aLogicFrameId, String aTreeType, 
            String aDocumentName, String aDocumentDetail, 
            String aDocumentPath, Long aTypeDocumentId, Long aUserId, 
            String aTreeTypeDescription, String aTypeDocumentDescription, 
            String aUserName) 
    {
        this.itsLogicFrameId = aLogicFrameId;
        this.itsTreeType = aTreeType;
        this.itsDocumentName = aDocumentName;
        this.itsDocumentDetail = aDocumentDetail;
        this.itsDocumentPath = aDocumentPath;
        this.itsTypeDocumentId = aTypeDocumentId;
        this.itsUserId = aUserId;
        this.itsTreeTypeDescription = aTreeTypeDescription;
        this.itsTypeDocumentDescription = aTypeDocumentDescription;
        this.itsUserName = aUserName;
    }

    
        
    
    //Aqui se inician todos los getters
    public String getItsDocumentDetail() {
        return itsDocumentDetail;
    }

    public String getItsDocumentName() {
        return itsDocumentName;
    }

    public String getItsTreeTypeDescription() {
        return itsTreeTypeDescription;
    }

    public String getItsTypeDocumentDescription() {
        return itsTypeDocumentDescription;
    }

    public String getItsUserName() {
        return itsUserName;
    }

    public Long getItsLogicFrameId() {
        return itsLogicFrameId;
    }

    public String getItsDocumentPath() {
        return itsDocumentPath;
    }

    public String getItsTreeType() {
        return itsTreeType;
    }

    public Long getItsTypeDocumentId() {
        return itsTypeDocumentId;
    }

    public Long getItsUserId() {
        return itsUserId;
    }
    
    
    
    //Aqui se inician todos los setters

    public void setItsDocumentDetail(String itsDocumentDetail) {
        this.itsDocumentDetail = itsDocumentDetail;
    }

    public void setItsDocumentName(String itsDocumentName) {
        this.itsDocumentName = itsDocumentName;
    }

    public void setItsTreeTypeDescription(String itsTreeTypeDescription) {
        this.itsTreeTypeDescription = itsTreeTypeDescription;
    }

    public void setItsTypeDocumentDescription(String itsTypeDocumentDescription) {
        this.itsTypeDocumentDescription = itsTypeDocumentDescription;
    }

    public void setItsUserName(String itsUserName) {
        this.itsUserName = itsUserName;
    }

    public void setItsLogicFrameId(Long itsLogicFrameId) {
        this.itsLogicFrameId = itsLogicFrameId;
    }

    public void setItsDocumentPath(String itsDocumentPath) {
        this.itsDocumentPath = itsDocumentPath;
    }

    public void setItsTreeType(String itsTreeType) {
        this.itsTreeType = itsTreeType;
    }

    public void setItsTypeDocumentId(Long itsTypeDocumentId) {
        this.itsTypeDocumentId = itsTypeDocumentId;
    }

    public void setItsUserId(Long itsUserId) {
        this.itsUserId = itsUserId;
    }
    
    
}
