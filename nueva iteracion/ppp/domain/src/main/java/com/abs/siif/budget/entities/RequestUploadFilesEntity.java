/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RequestUploadFiles
 *  Purpose:  This table contains the properties of the uploaded files.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.entities;

import com.abs.siif.planning.entities.DependenceEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author FENIX-02
 */
@Entity
@Table(name = "SIIFabsarchivo")
public class RequestUploadFilesEntity implements Comparable<RequestUploadFilesEntity>, Serializable{

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idarchivo")
    private Long requestUpLoadFileId;
    @ManyToOne
    @JoinColumn(name = "idtipoarchivo", nullable = false)
    private UploadFileTypeEntity upLoadfileType;
    @Column(name="clave", length = 20)
    private String reqPreInversionKey;
    @Column(name="descripcion", length = 100)
    private String requestUpLoadFileDesc;
    @Column(name="nombre", length = 100)
    private String name;
    @Column(name="ruta")
    private String requestUpLoadFilePath;
    
    /**
   * Archivos cargados por Requerimiento;
   */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "siifppparchivoreqpreinv", joinColumns =
    {
        @JoinColumn(name = "idarchivo", nullable = false, updatable = true)
    },
    inverseJoinColumns =
    {
        @JoinColumn(name = "IdReqPreinversion",
        nullable = false, updatable = false)
    })
    private List<PreInvRequestEntity> requirements;

    public String getReqPreInversionKey()
    {
        return reqPreInversionKey;
    }

    public void setReqPreInversionKey(String reqPreInversionKey)
    {
        this.reqPreInversionKey = reqPreInversionKey;
    }

    public String getRequestUpLoadFileDesc()
    {
        return requestUpLoadFileDesc;
    }

    public void setRequestUpLoadFileDesc(String requestUpLoadFileDesc)
    {
        this.requestUpLoadFileDesc = requestUpLoadFileDesc;
    }

    public Long getRequestUpLoadFileId()
    {
        return requestUpLoadFileId;
    }

    public void setRequestUpLoadFileId(Long requestUpLoadFileId)
    {
        this.requestUpLoadFileId = requestUpLoadFileId;
    }

    public String getRequestUpLoadFilePath()
    {
        return requestUpLoadFilePath;
    }

    public void setRequestUpLoadFilePath(String requestUpLoadFilePath)
    {
        this.requestUpLoadFilePath = requestUpLoadFilePath;
    }

    public UploadFileTypeEntity getUpLoadfileType()
    {
        return upLoadfileType;
    }

    public void setUpLoadfileType(UploadFileTypeEntity upLoadfileType)
    {
        this.upLoadfileType = upLoadfileType;
    }

    public List<PreInvRequestEntity> getRequirements()
    {
        return requirements;
    }

    public void setRequirements(List<PreInvRequestEntity> requirements)
    {
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int compareTo(RequestUploadFilesEntity obj)
    {
        return this.requestUpLoadFileId.compareTo(obj.getRequestUpLoadFileId());
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof RequestUploadFilesEntity) {
            this.requestUpLoadFileId.equals(((RequestUploadFilesEntity) obj).getRequestUpLoadFileId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.requestUpLoadFileId != null
                ? this.requestUpLoadFileId.hashCode() : 0);
        return hash;
    }

}
