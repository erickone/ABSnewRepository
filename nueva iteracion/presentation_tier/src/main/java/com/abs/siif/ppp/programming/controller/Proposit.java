package com.abs.siif.ppp.programming.controller;

import java.io.Serializable;

/**
 * Clase Temporal
 *
 * @author Israel Ruiz
 */
public class Proposit implements Serializable, Comparable<Proposit> {

    private String description;
    private Long idDelivery;



    public Proposit(Long id, String description) {
        idDelivery = id;
        this.description = description;

    }

    public Proposit(Long idDelivery) {
        this.idDelivery = idDelivery;

    }


    /**
     * Constructor por default
     */
    public Proposit() {

    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return this.idDelivery;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.idDelivery= id;
    }

    @Override
    public int compareTo(Proposit other) {
        return this.idDelivery.compareTo(other.getIdDelivery());
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Proposit){
            return this.idDelivery.equals(((Proposit)obj).getIdDelivery());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 ^ hash + (this.idDelivery != null
                    ? this.idDelivery.hashCode() : 0);
        return hash;
    }
    /**
     * @return the idDelivery
     */
    public Long getIdDelivery() {
        return idDelivery;
    }

    /**
     * @param idDelivery the idDelivery to set
     */
    public void setIdDelivery(Long idDelivery) {
        this.idDelivery = idDelivery;
    }
}
