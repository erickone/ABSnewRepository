/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.support.FormatNumber;
import java.io.Serializable;

/**
 *
 * @author Israel Ruiz
 */
public class UbicationPeopleHelper implements
        Comparable<UbicationPeopleHelper>, Serializable {

    private RegionalClassifierEntity ubication;
    private String distribution;
    private String totalPeople;
    private String inegiDistribution;
    private InvPreFileEntity preFile;
    private String womens;
    private String mens;
    
    public void setWomens(String womens){
        womens = FormatNumber.formatNumber(womens);
        this.womens = womens;
    }
    
    public String getWomens(){
        return womens;
    }
    
    public void setMens(String mens){
        mens = FormatNumber.formatNumber(mens);
        this.mens = mens;
    }
    
    public String getMens(){
        return mens;
    }

    public InvPreFileEntity getPreFile() {
        return preFile;
    }

    public void setPreFile(InvPreFileEntity preFile) {
        this.preFile = preFile;
    }


    /**
     * @return the ubication
     */
    public RegionalClassifierEntity getUbication() {
        return ubication;
    }

    /**
     * @param ubication the ubication to set
     */
    public void setUbication(RegionalClassifierEntity ubication) {
        setWomens(String.valueOf(ubication.getRegionalClassifierWomenNumber()));
        setMens(String.valueOf(ubication.getRegionalClassifierMenNumber()));
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
        totalPeople = FormatNumber.formatNumber(totalPeople);
        this.totalPeople = totalPeople;
    }

    @Override
    public int compareTo(UbicationPeopleHelper obj) {
        return this.ubication.getRegionalClassifierId().
                compareTo(obj.getUbication().getRegionalClassifierId());
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

}
