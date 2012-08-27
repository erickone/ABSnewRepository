/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DraftProjectSearchDto
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class DraftProjectSearchDto  implements Serializable {
 
    private Long dependencyId;
    private String idAnteProyecto;
    private String idPreFicha;
    private Long status;
    private String nombreCorto;
    private Date fechaInicio;
    private Date fechaFin;

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getIdAnteProyecto() {
        return idAnteProyecto;
    }

    public void setIdAnteProyecto(String idAnteProyecto) {
        this.idAnteProyecto = idAnteProyecto;
    }

    public String getIdPreFicha() {
        return idPreFicha;
    }

    public void setIdPreFicha(String idPreFicha) {
        this.idPreFicha = idPreFicha;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }   
    

    public Long getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(Long dependencyId) {
        this.dependencyId = dependencyId;
    }
    
}
