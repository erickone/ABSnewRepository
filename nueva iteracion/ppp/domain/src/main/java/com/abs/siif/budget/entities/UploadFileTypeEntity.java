/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  UploadFileType
 *  Purpose:  This table contains the different uploaded file types.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author FENIX-02
 */
@Entity
@Table(name = "siifabstipoarchivo")
public class UploadFileTypeEntity implements Comparable<UploadFileTypeEntity>, Serializable{

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idtipoarchivo")
    private Long upLoadFileTypeId;
    @Column(name = "clave", length = 20)
    private String upLoadFileTypeKey;
    @Column(name="descripcion", length = 100)
    private String upLoadFileTypeDesc;

    public String getUpLoadFileTypeDesc()
    {
        return upLoadFileTypeDesc;
    }

    public void setUpLoadFileTypeDesc(String upLoadFileTypeDesc)
    {
        this.upLoadFileTypeDesc = upLoadFileTypeDesc;
    }

    public Long getUpLoadFileTypeId()
    {
        return upLoadFileTypeId;
    }

    public void setUpLoadFileTypeId(Long upLoadFileTypeId)
    {
        this.upLoadFileTypeId = upLoadFileTypeId;
    }

    public String getUpLoadFileTypeKey()
    {
        return upLoadFileTypeKey;
    }

    public void setUpLoadFileTypeKey(String upLoadFileTypeKey)
    {
        this.upLoadFileTypeKey = upLoadFileTypeKey;
    }
    
    @Override
    public int compareTo(UploadFileTypeEntity obj)
    {
        return this.upLoadFileTypeId.compareTo(obj.getUpLoadFileTypeId());
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof UploadFileTypeEntity) {
            this.upLoadFileTypeId.equals(((UploadFileTypeEntity) obj).getUpLoadFileTypeId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.upLoadFileTypeId != null
                ? this.upLoadFileTypeId.hashCode() : 0);
        return hash;
    }
}
