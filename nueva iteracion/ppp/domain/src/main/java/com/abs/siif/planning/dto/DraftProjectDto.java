/*
 *  Copyright (C) 2012 Advance Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DepencenceDto
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advance Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advance 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Lizeth Pazos.
 * @version 1.0 24/07/2012
 * 
 */
public class DraftProjectDto implements Serializable {

    private int idanteproyecto;
    private int tipoficha;
    private String fundamentolegal;
    private String descripcion;
    private Date fechafin;
    private String clave;
    private String nombre;
    private String proposito;
    private String nombrecorto;
    private boolean activo; 
    private Date fechainicio;
    private int iddependencia; 
    private int idprogramacion;
    private int idambitoanteproyecto; 
    private int idestadoanteproyecto;
    private int idestatusanteproyecto; 
    private int idtipoanteproyecto;
    private String descripciondependencia;
    private String nombreobjetivo;
    private String nombreobjetivopadre;

    public String getNombreobjetivopadre() {
        return nombreobjetivopadre;
    }

    public void setNombreobjetivopadre(String nombreobjetivopadre) {
        this.nombreobjetivopadre = nombreobjetivopadre;
    }
    
    public String getDescripciondependencia() {
        return descripciondependencia;
    }

    public void setDescripciondependencia(String descripciondependencia) {
        this.descripciondependencia = descripciondependencia;
    }

    public String getNombreobjetivo() {
        return nombreobjetivo;
    }

    public void setNombreobjetivo(String nombreobjetivo) {
        this.nombreobjetivo = nombreobjetivo;
    } 

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFundamentolegal() {
        return fundamentolegal;
    }

    public void setFundamentolegal(String fundamentolegal) {
        this.fundamentolegal = fundamentolegal;
    }

    public int getIdambitoanteproyecto() {
        return idambitoanteproyecto;
    }

    public void setIdambitoanteproyecto(int idambitoanteproyecto) {
        this.idambitoanteproyecto = idambitoanteproyecto;
    }

    public int getIdanteproyecto() {
        return idanteproyecto;
    }

    public void setIdanteproyecto(int idanteproyecto) {
        this.idanteproyecto = idanteproyecto;
    }

    public int getIddependencia() {
        return iddependencia;
    }

    public void setIddependencia(int iddependencia) {
        this.iddependencia = iddependencia;
    }

    public int getIdestadoanteproyecto() {
        return idestadoanteproyecto;
    }

    public void setIdestadoanteproyecto(int idestadoanteproyecto) {
        this.idestadoanteproyecto = idestadoanteproyecto;
    }

    public int getIdestatusanteproyecto() {
        return idestatusanteproyecto;
    }

    public void setIdestatusanteproyecto(int idestatusanteproyecto) {
        this.idestatusanteproyecto = idestatusanteproyecto;
    }

    public int getIdprogramacion() {
        return idprogramacion;
    }

    public void setIdprogramacion(int idprogramacion) {
        this.idprogramacion = idprogramacion;
    }

    public int getIdtipoanteproyecto() {
        return idtipoanteproyecto;
    }

    public void setIdtipoanteproyecto(int idtipoanteproyecto) {
        this.idtipoanteproyecto = idtipoanteproyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombrecorto() {
        return nombrecorto;
    }

    public void setNombrecorto(String nombrecorto) {
        this.nombrecorto = nombrecorto;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public int getTipoficha() {
        return tipoficha;
    }

    public void setTipoficha(int tipoficha) {
        this.tipoficha = tipoficha;
    }
    
    
    
}
