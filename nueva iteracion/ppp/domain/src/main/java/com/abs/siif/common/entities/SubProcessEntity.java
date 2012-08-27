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
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 * 
 * MBA 201206191112: Esta entidad se utiliza para almacenar la información a detalle 
 * ligada a los procesos de los módulos del SIIF (Requisiciones, Solicitudes de Pago, 
 * Planeación, Programación, Anteproyecto, etc.) y esta ligada al módulo
 * 
 */
@Entity
@Table(name="siifabsSubProceso")
public class SubProcessEntity  implements 
        Comparable<SubProcessEntity>, Serializable
{
    //Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdSubProceso", nullable = false)
    private Long subprocessId;

    //Este campo contiene la descripcion del subproceso
    @Column(name="Descripcion", length= 100, nullable = false)
    private String subprocessDescription;
    
    // Este campo contiene el Id del Proceso al que esta ligado el subproceso
    @ManyToOne
    @JoinColumn(name = "IdProceso", nullable = false)
    private ProcessEntity processId;

    //Aqui inician los setters de la entidad
    
    public void setSubprocessDescription(String subprocessDescription) {
        this.subprocessDescription = subprocessDescription;
    }

    public void setSubprocessId(Long subprocessId) {
        this.subprocessId = subprocessId;
    }

    public void setProcessId(ProcessEntity processId) {
        this.processId = processId;
    }

    //Aqui inician los getters de la entidad

    public String getSubprocessDescription() {
        return subprocessDescription;
    }

    public Long getSubprocessId() {
        return subprocessId;
    }

    public ProcessEntity getProcessId() {
        return processId;
    }
    
    //Aqui inician los metodos que abstraidos de la base

    @Override
    public int compareTo(SubProcessEntity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }




}
