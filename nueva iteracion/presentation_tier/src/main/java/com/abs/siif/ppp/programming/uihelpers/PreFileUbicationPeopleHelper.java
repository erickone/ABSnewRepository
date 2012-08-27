/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.entities.InvPreFileRegionalClassifierEntity;

/**
 *
 * @author Francisco Luna
 */
public class PreFileUbicationPeopleHelper implements
        Comparable<InvPreFileRegionalClassifierEntity> {
    
    private InvPreFileRegionalClassifierEntity ubication;
    private String distribution;
    private String totalPeople;
    private String inegiDistribution;
    private InvPreFileEntity preFile;

    public InvPreFileEntity getPreFile() {
        return preFile;
    }

    public void setPreFile(InvPreFileEntity preFile) {
        this.preFile = preFile;
    }


    /**
     * @return the ubication
     */
    public InvPreFileRegionalClassifierEntity getUbication() {
        return ubication;
    }

    /**
     * @param ubication the ubication to set
     */
    public void setUbication(InvPreFileRegionalClassifierEntity ubication) {
        this.ubication = ubication;
    }

    /**
     * @return the distribution
     */
    public String getDistribution() {
        return distribution;
    }

    /**
     * @param distribution the distribution to set
     */
    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    /**
     * @return the totalPeople
     */
    public String getTotalPeople() {
        return totalPeople;
    }

    /**
     * @param totalPeople the totalPeople to set
     */
    public void setTotalPeople(String totalPeople) {
        this.totalPeople = totalPeople;
    }   
    
    /**
     * @return the inegi distribution
     */
    public String getInegiDistribution() {
        return inegiDistribution;
    }

    /**
     * @param InegiDistribution the distribution to set
     */
    public void setInegiDistribution(String distribution) {
        this.inegiDistribution = distribution;
    }

    @Override
    public int compareTo(InvPreFileRegionalClassifierEntity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
