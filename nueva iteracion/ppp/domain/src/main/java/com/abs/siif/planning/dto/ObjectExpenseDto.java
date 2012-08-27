/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.dto;

import java.io.Serializable;

/**
 *
 * @author abs71
 */
public class ObjectExpenseDto implements Serializable {
    private int clave;
    private String descripcion;
    private int iddependency;

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getIddependency() {
        return iddependency;
    }

    public void setIddependency(int iddependency) {
        this.iddependency = iddependency;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    
  
    
}
