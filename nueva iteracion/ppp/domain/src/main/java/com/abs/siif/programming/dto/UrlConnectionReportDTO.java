/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author absvalenzuela
 */
public class UrlConnectionReportDTO implements Serializable {
    
    private String urlServer;
    private String tipoFicha;
    private String todo;
    private String tipoDocumento;
    private String idObjecto;
    private String iddependencia;

    public String getUrlServer() {
        return urlServer;
    }

    public void setUrlServer(String urlServer) {
        this.urlServer = urlServer;
    }

    public String getTipoFicha() {
        return tipoFicha;
    }

    public void setTipoFicha(String tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipodocumento) {
        this.tipoDocumento = tipodocumento;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todos) {
        this.todo = todos;
    }
    
    public String getUrlDocumento() {
        String url = "";
        URL street = null;
        String page = "/reportesapp/reportes?id_t=" + this.getIdObjecto() + "&id_rep=" + this.getTipoFicha() +
              "&ls_todo=" + this.getTodo() + "&id_export=" + this.getTipoDocumento();
        try {
            street = new URL("https", this.getUrlServer(), page);
            url = street.toString();
            url = url.replace('[', ' ').replace(']', ' ').replaceAll(" ", "");
        } catch (MalformedURLException ex) {}
        return url;
        
    }
    
    public String getUrlDocumentoWithDependencie() {
        String url = "";
        URL street = null;
        String page = "/reportesapp/reportes?id_t=" + this.getIdObjecto() + "&id_rep=" + this.getTipoFicha() +
              "&ls_todo=" + this.getTodo() + "&id_export=" + this.getTipoDocumento()
                + "&iddependencia="+this.getIddependencia();
        try {
            street = new URL("https", this.getUrlServer(), page);
            url = street.toString();
            url = url.replace('[', ' ').replace(']', ' ').replaceAll(" ", "");
        } catch (MalformedURLException ex) {}
        return url;
        
    }

    public String getIdObjecto() {
        return idObjecto;
    }

    public void setIdObjecto(String idObjecto) {
        this.idObjecto = idObjecto;
    }

    public String getIddependencia()
    {
        return iddependencia;
    }

    public void setIddependencia(String iddependencia)
    {
        this.iddependencia = iddependencia;
    }
    
}
