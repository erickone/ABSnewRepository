/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DocumentTypeEntity
 *  Purpose:  [ esta entidad se encarga de guardar los diferentes tipos de
 *  documentos y sus descripciones permitidas]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 */
@Entity
@Table(name="siifabsTipoDocumento")
public class DocumentTypeEntity  implements 
        Comparable<DocumentTypeEntity>, Serializable
{
    //Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdTipoDocumento", nullable = false)
    private Long documentTypeId;
    
    //Este campo contiene la descripcion de el tipo de documento
    @Column(name="Descripcion", length= 50)
    private String documentTypeDescription;
    
    
    //Aqui inician los setters de la entidad
    
     public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }
    
    //Aqui inician los getters de la entidad

    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }

    public Long getDocumentTypeId() {
        return documentTypeId;
    }
    
    
    //Aqui inician los metodos que abstraidos de la base
   @Override
    public int compareTo(DocumentTypeEntity obj) 
    {
        int result = -1;
        if (this.documentTypeId != null && obj.documentTypeId != null) {
            result = this.documentTypeId.compareTo(obj.documentTypeId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof DocumentTypeEntity
                && this.documentTypeId != null
                && ((DocumentTypeEntity) obj).documentTypeId != null) {

            result = this.documentTypeId.equals(
                    ((DocumentTypeEntity) obj).documentTypeId);

        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.documentTypeId != null
                ? this.documentTypeId.hashCode() : 0);
        return hash;
    }
}
