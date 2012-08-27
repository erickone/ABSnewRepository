/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.data;

/**
 *
 * @author Juan Antonio Zavala Aguilar Identifica los tipos de anteproyecto que
 * pueden existir (Proceso, Proyecto)
 */
public enum DraftFileType {

    PROCESS("name.process"),
    PROJECT("name.project");
    
    private String name;
    private DraftFileType(String arg){
        this.name = arg;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
