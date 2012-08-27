/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.catalog.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author absvalenzuela
 */
@Entity
@Table(name = "siifabstipoconfig")
public class TypeConfigCatalogEntities implements Serializable{
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idtipoconfig")
    private Long typeConfigId;
    
    @Column(name = "clave")
    private String clave;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "activo")
    private boolean activo;

    public Long getTypeConfigId() {
        return typeConfigId;
    }

    public void setTypeConfigId(Long typeConfigId) {
        this.typeConfigId = typeConfigId;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }    
    
}
