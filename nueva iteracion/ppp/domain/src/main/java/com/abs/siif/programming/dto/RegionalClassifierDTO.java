/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import java.io.Serializable;

/**
 *
 * @author Israel Ruiz
 */
public class RegionalClassifierDTO implements Serializable {
    private Integer idClasifRegional;
    private String descripcion;
    private Integer numhombres;
    private Integer nummujeres;
    private RegionalClassifierEntity ubication;
    private RegionalClassifierEntity father;
    private boolean selected;
    

    /**
     * @return the idClasifRegional
     */
    public Integer getidclasifregional() {
        return idClasifRegional;
    }

    /**
     * @param idClasifRegional the idClasifRegional to set
     */
    public void setidclasifregional(Integer idClasifRegional) {
        this.idClasifRegional = idClasifRegional;
    }

    /**
     * @return the descripcion
     */
    public String getdescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the numhombres
     */
    public Integer getnumhombres() {
        return numhombres;
    }

    /**
     * @param numhombres the numhombres to set
     */
    public void setnumhombres(Integer numhombres) {
        this.numhombres = numhombres;
    }

    /**
     * @return the nummujeres
     */
    public Integer getnummujeres() {
        return nummujeres;
    }

    /**
     * @param nummujeres the nummujeres to set
     */
    public void setnummujeres(Integer nummujeres) {
        this.nummujeres = nummujeres;
    }

    /**
     * @return the ubication
     */
    public RegionalClassifierEntity getUbication() {
        ubication = new RegionalClassifierEntity();
        ubication.setRegionalClassifierId(new Long(idClasifRegional));
        ubication.setRegionalClassifierDescription(this.descripcion);
        ubication.setRegionalClassifierMenNumber(this.numhombres);
        ubication.setRegionalClassifierWomenNumber(this.nummujeres);
        return ubication;
    }

    /**
     * @param ubication the ubication to set
     */
    public void setUbication(RegionalClassifierEntity ubication) {
        this.ubication = ubication;
    }

    /**
     * @return the father
     */
    public RegionalClassifierEntity getFather() {
        return father;
    }

    /**
     * @param father the father to set
     */
    public void setFather(RegionalClassifierEntity father) {
        this.father = father;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
