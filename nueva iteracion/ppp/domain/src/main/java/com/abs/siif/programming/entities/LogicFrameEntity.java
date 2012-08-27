/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  LogicFrameEntity
 *  Purpose:  [ Esta entidad es la encargada de guardar la información 
 *  relacionada con el proceso del marco logico, que se encuentra 
 *  como un proceso del anteproyecto, en este proceso se guardaran archivos
 *  que el usuario, integrará para complementar la información de un 
 *  anteproyecto]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.entities;

import com.abs.siif.common.entities.DocumentTypeEntity;
import com.abs.siif.security.entities.UserEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Esta entidad se encargara de manejar los archivos que son subidos al 
 * anteproyecto para complemetar su información
 * @author Erick Leija
 */
@Entity
@Table(name="siifpppmarcologico")
public class LogicFrameEntity implements 
        Comparable<LogicFrameEntity>, Serializable
{
     // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "Idmarcologico", nullable = false)
    private Long logicFrameId;
    
    @Column(name="TipodeArbol")
    private int logicFrameTreeType;
    
    @Column(name="NombreDocumento", length = 50)
    private String logicFramedocumentName;
    
    @Column(name="DetalleDocumento", length = 100)
    private String logicFrameDocumentDetail;
    
    @Column(name="RutadelDocumento", length = 1000)
    private String logicFrameDocumentPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdAnteProyecto", nullable = false)
    private DraftProjectEntity logicFrameDraftProject;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTipoDocumento", nullable = true)
    private DocumentTypeEntity logicFrameDocumentType;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdUsuario", nullable = false)
    private UserEntity logicFrameUserId;
    
    //Aqui empiezan los Getters
    public String getLogicFrameDocumentDetail() 
    {
        return logicFrameDocumentDetail != null ? logicFrameDocumentDetail.trim() : logicFrameDocumentDetail;
    }

    public int getLogicFrameTreeType() 
    {
        return logicFrameTreeType;
    }

    
    public String getLogicFramedocumentName() 
    {
        return logicFramedocumentName != null ? logicFramedocumentName.trim() : logicFramedocumentName;
    }

    public Long getLogicFrameId() 
    {
        return logicFrameId;
    }

    public DocumentTypeEntity getLogicFrameDocumentType() 
    {
        return logicFrameDocumentType;
    }

    public DraftProjectEntity getlogicFrameDraftProject() 
    {
        return logicFrameDraftProject;
    }

    public UserEntity getLogicFrameUserId() 
    {
        return logicFrameUserId;
    }

    public String getLogicFrameDocumentPath() {
        return logicFrameDocumentPath != null ? logicFrameDocumentPath.trim() : logicFrameDocumentPath;
    }
    

    //Aqui empiezan los Setters
    public void setLogicFrameTreeType(int logicFrameTreeType) 
    {
        this.logicFrameTreeType = logicFrameTreeType;
    }

    public void setLogicFramedocumentName(String logicFramedocumentName) 
    {
        this.logicFramedocumentName = logicFramedocumentName;
    }

    public void setLogicFrameDocumentDetail(String logicFrameDocumentDetail) 
    {
        this.logicFrameDocumentDetail = logicFrameDocumentDetail;
    }

    public void setLogicFrameId(Long logicFrameId) 
    {
        this.logicFrameId = logicFrameId;
    }

    public void setLogicFrameDocumentType(DocumentTypeEntity logicFrameDocumentType) 
    {
        this.logicFrameDocumentType = logicFrameDocumentType;
    }

    public void setlogicFrameDraftProject(DraftProjectEntity alogicFrameDraftProject) 
    {
        this.logicFrameDraftProject = alogicFrameDraftProject;
    }

    public void setLogicFrameUserId(UserEntity logicFrameUserId)
    {
        this.logicFrameUserId = logicFrameUserId;
    }

    public void setLogicFrameDocumentPath(String logicFrameDocumentPath) {
        this.logicFrameDocumentPath = logicFrameDocumentPath;
    }
    
    
    //Estos son los metodos que fueron abstraidos de clase Base
    @Override
    public int compareTo(LogicFrameEntity obj) 
    {
        int result = -1;
        if (this.logicFrameId != null && obj.logicFrameId != null) 
        {
            result = this.logicFrameId.compareTo(obj.logicFrameId);
        }
        return result;
    }

    
    
    @Override
    public boolean equals(Object obj) 
    {
        boolean result = false;
        if (obj instanceof LogicFrameEntity
                && this.logicFrameId != null
                && ((LogicFrameEntity) obj).logicFrameId != null) {

            result = this.logicFrameId.equals(
                    ((LogicFrameEntity) obj).logicFrameId);

        }

        return result;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 41 * hash + (this.logicFrameId != null
                ? this.logicFrameId.hashCode() : 0);
        return hash;
    }
}
