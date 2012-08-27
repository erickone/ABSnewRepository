/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  LogbookEntity
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.support.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author luis.carreon
 */
@Entity
@Table(name = "siifabsBitacoraErrores")
public class LogbookEntity implements 
        Serializable, Comparable<LogbookEntity>{
    
    @Id
    @Column(name = "Id", nullable = false)
    private long logbookId;
    
    @Column(name = "FirmaMetodo",nullable=false)
    private String logbookSingClass;
    
    @Column(name = "CausaError",nullable=false)
    private String  logbookErrorCause;
    
    @Column(name = "Usuario", nullable=true)
    private String logbookUser;
    
    @Column(name = "Fecha", nullable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date logbookDate;
    
    public Date getLogbookDate() {
        return logbookDate;
    }

    public void setLogbookDate(Date logbookDate) {
        this.logbookDate = logbookDate;
    }

    public String getLogbookErrorCause() {
        return logbookErrorCause != null ? logbookErrorCause.trim():logbookErrorCause;
    }

    public void setLogbookErrorCause(String logbookErrorCause) {
        this.logbookErrorCause = logbookErrorCause;
    }

    public long getLogbookId() {
        return logbookId;
    }

    public void setLogbookId(long logbookId) {
        this.logbookId = logbookId;
    }

    public String getLogbookSingClass() {
        return logbookSingClass;
    }

    public void setLogbookSingClass(String logbookSingClass) {
        this.logbookSingClass = logbookSingClass;
    }

    public String getLogbookUser() {
        return logbookUser;
    }

    public void setLogbookUser(String logbookUser) {
        this.logbookUser = logbookUser;
    }
    
    @Override
    public int compareTo(LogbookEntity obj) {
        int result = -1;
        if(this.logbookId > obj.getLogbookId())
        {
            result = 1;
        }else if(this.logbookId == obj.getLogbookId()){
            result = 0;
        }
        
        return result;
    }
 
   
    
}
