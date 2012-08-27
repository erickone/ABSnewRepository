/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.base.entities;

import java.io.Serializable;

/**
 * Clase base para las entidades
 * @author Israel Ruiz
 */
public class EntityBase implements Serializable{
    // Identificador utilizado para las busquedas en listados 
    // Este id no necesarimente es parte de id que conforma la entidad
    private transient int idSearch;

    /**
     * @return the idSearch
     */
    public int getIdSearch() {
        return idSearch;
    }

    /**
     * @param idSearch the idSearch to set
     */
    public void setIdSearch(int idSearch) {
        this.idSearch = idSearch;
    }
}
