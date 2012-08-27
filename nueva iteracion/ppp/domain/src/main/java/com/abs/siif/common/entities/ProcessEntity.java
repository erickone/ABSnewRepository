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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 * 
 * MBA 201206191112: Esta entidad se utiliza para almacenar la información general 
 * de los procesos de los módulos del SIIF (Adquisiciones, Ejercicio Pptal, 
 * Planeación, Programación y Presupuestación, etc.) y esta ligada al módulo
 * 
 */
@Entity
@Table(name="siifabsProceso")
public class ProcessEntity  implements 
        Comparable<ProcessEntity>, Serializable
{
    //Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdProceso", nullable = false)
    private Long processId;

    //Este campo contiene la clave del proceso
    @Column(name="Clave", length= 20, nullable = false)
    private String processKey;
    
    //Este campo contiene la descripcion del proceso
    @Column(name="Descripcion", length= 100, nullable = false)
    private String processDescription;
    
    // Este campo contiene el Id del Módulo al que esta ligado el proceso
    // TODO : Generar mapeo cuando se genere la entidad de módulos del Sistema SIIF
    @Column(name="IdModulo", nullable = false)
    private Long processModuleId;
    
    //Aqui inician los setters de la entidad
    
    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }
    
    public void setProcessDescription(String processDescription) {
        this.processDescription = processDescription;
    }

    public void setProcessModuleId(Long processModuleId) {
        this.processModuleId = processModuleId;
    }
    
    //Aqui inician los getters de la entidad

    public Long getProcessId() {
        return processId;
    }

    public String getProcessKey() {
        return processKey;
    }

    public String getProcessDescription() {
        return processDescription;
    }

    public Long getProcessModuleId() {
        return processModuleId;
    }
    
    //Aqui inician los metodos que abstraidos de la base

    @Override
    public int compareTo(ProcessEntity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
