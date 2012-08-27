/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ObservationDto
 *  Purpose:  All the information about InvPreFile Observations.
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author FENIX-02
 */
public class ObservationDto implements Serializable{
    
    private Integer idprefichaobservacion;
    private Integer idpreficha;
    private String comentario;
    private Integer idperfil;
    private Integer idusuario;
    private String userdesc;
    private String usuario;
    private String contrasenia;
    private String nombre;
    private String apepaterno;
    private String apematerno;
    private Integer idcolectiva;
    private Integer idempleado;
    private Integer iddependencia;
    private String clave;
    private String descripcion;
    private Date fechacomentario;
    private String dateWithFormat;
    
    SimpleDateFormat ft = 
       new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
    
    public ObservationDto(){
        
    }
    
    public ObservationDto(int anObservationId, int anInvPreFileId, String aComment, 
            int aUserId, int aProfileId, String aUser, String aUserDesc, 
            String aUserPwd, String aUserName, String aUserFirsLastName, 
            String aUserSecondLastName, int aUserColectiveId, int aEmployeeId, 
            int aDependenceId, String aDependenceKey, String aDependenceDesc,
            Date aDate){
        idprefichaobservacion = anObservationId;
        idpreficha = anInvPreFileId;
        comentario = aComment;
        idperfil = aProfileId;
        idusuario = aUserId;
        //itsUserDesc = aUserDesc;
        userdesc = aUserDesc;
        usuario = aUser;
        contrasenia = aUserPwd;
        nombre = aUserName;
        apepaterno = aUserFirsLastName;
        apematerno = aUserSecondLastName;
        //itsUserKey = aUserKey;
        idcolectiva = aUserColectiveId;
        idempleado = aEmployeeId;
        iddependencia = aDependenceId;
        clave = aDependenceKey;
        descripcion = aDependenceDesc;
        fechacomentario = aDate;
    }

    /**
     * Returns the Dependence´s name composed with the Key.
     * @return 
     */
    public String getComposedDependenceName() {
    return(this.getClave() + " " + this.getDescripcion());
    }
    
    /**
     * Returns the User´s complete name.
     * @return 
     */
    public String getComposedUserName(){
        return(this.getNombre() + " " + this.getApepaterno() +
                " " + this.getApematerno());
    }

    public String getDateWithFormat(){
        return ft.format(this.fechacomentario);
    }
    public Integer getIdprefichaobservacion() {
        return idprefichaobservacion;
    }

    public void setIdprefichaobservacion(int idprefichaobservacion) {
        this.idprefichaobservacion = idprefichaobservacion;
    }

    public Integer getIdpreficha() {
        return idpreficha;
    }

    public void setIdpreficha(int idpreficha) {
        this.idpreficha = idpreficha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(int idperfil) {
        this.idperfil = idperfil;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUserdesc() {
        return userdesc;
    }

    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApepaterno() {
        return apepaterno;
    }

    public void setApepaterno(String apepaterno) {
        this.apepaterno = apepaterno;
    }

    public String getApematerno() {
        return apematerno;
    }

    public void setApematerno(String apematerno) {
        this.apematerno = apematerno;
    }

    public Integer getIdcolectiva() {
        return idcolectiva;
    }

    public void setIdcolectiva(int idcolectiva) {
        this.idcolectiva = idcolectiva;
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public Integer getIddependencia() {
        return iddependencia;
    }

    public void setIddependencia(int iddependencia) {
        this.iddependencia = iddependencia;
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

    public Date getFechacomentario() {
        return fechacomentario;
    }

    public void setFechacomentario(Date fechacomentario) {
        this.fechacomentario = fechacomentario;
    }

}
