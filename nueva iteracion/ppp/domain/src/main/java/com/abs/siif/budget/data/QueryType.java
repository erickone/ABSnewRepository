/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.data;

/**
 *
 * @author Juan Antonio Zavala Aguilar Identifica los tipos de query que
 * pueden existir (Nativos y HQL).
 */
public enum QueryType {

    NATIVE("NATIVE"),
    HQL("HQL");
    
    private String name;
    private QueryType(String arg){
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
