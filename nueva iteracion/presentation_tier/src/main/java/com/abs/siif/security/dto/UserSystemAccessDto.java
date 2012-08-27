/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  UserSystemAccessDto
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Francisco Luna
 */
public class UserSystemAccessDto implements Serializable{ 
    
    public UserSystemAccessDto(){
    }
    
    private String descripcion;
    private String clave;
    private String contrasena;
    private String confirmacion;
    private String claveVirtual;
    private String contrasenaVirtual;
    private String confirmacionVirtual;
    private boolean cuentaHabilitada;
    private boolean cambiarContraPrimera;
    private boolean puedeCambiarContra;
    private boolean contraExpira;
    private int intentosPermitidos;
    private Date expDate;

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public boolean getCambiarContraPrimera() {
        return cambiarContraPrimera;
    }

    public void setCambiarContraPrimera(boolean cambiarContraPrimera) {
        this.cambiarContraPrimera = cambiarContraPrimera;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClaveVirtual() {
        return claveVirtual;
    }

    public void setClaveVirtual(String claveVirtual) {
        this.claveVirtual = claveVirtual;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getConfirmacionVirtual() {
        return confirmacionVirtual;
    }

    public void setConfirmacionVirtual(String confirmacionVirtual) {
        this.confirmacionVirtual = confirmacionVirtual;
    }

    public boolean getContraExpira() {
        return contraExpira;
    }

    public void setContraExpira(boolean contraExpira) {
        this.contraExpira = contraExpira;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasenaVirtual() {
        return contrasenaVirtual;
    }

    public void setContrasenaVirtual(String contrasenaVirtual) {
        this.contrasenaVirtual = contrasenaVirtual;
    }

    public boolean getCuentaHabilitada() {
        return cuentaHabilitada;
    }

    public void setCuentaHabilitada(boolean cuantaHabilitada) {
        this.cuentaHabilitada = cuantaHabilitada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIntentosPermitidos() {
        return intentosPermitidos;
    }

    public void setIntentosPermitidos(int intentosPermitidos) {
        this.intentosPermitidos = intentosPermitidos;
    }

    public boolean getPuedeCambiarContra() {
        return puedeCambiarContra;
    }

    public void setPuedeCambiarContra(boolean puedeCambiarContra) {
        this.puedeCambiarContra = puedeCambiarContra;
    }
    
    
}
