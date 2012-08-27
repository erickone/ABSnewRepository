/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.dto;

import com.abs.siif.budget.entities.DestinationEntity;
import com.abs.siif.budget.entities.DestinyObjectExpenseRUBUEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.support.FormatNumber;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author abs71
 */
public class ApplicationBudgetDto implements Serializable {

    private String concept;
    private String techo;
    private String asigned;
    private String forAsigned;
    private String itsSelectedPartida;
    private List<ObjectExpenseEntity> itsItemsPartidaCbo;
    private String itsSelectedD;
    private List<DestinyObjectExpenseRUBUEntity> itsItemsDCbo;
    private boolean enableD;
    private boolean enablePartida;
    private List<DependenceEntity> itsItemsUEGCbo;
    private Long itsSelectedUEG;
    private boolean aditionalCheck;
    private boolean disableAllCbo;   
    
    public boolean isDisableAllCbo() {
        return disableAllCbo;
    }

    public void setDisableAllCbo(boolean disableAllCbo) {
        this.disableAllCbo = disableAllCbo;
    }    
    
    public boolean isAditionalCheck() {
        return aditionalCheck;
    }

    public void setAditionalCheck(boolean aditionalCheck) {
        this.aditionalCheck = aditionalCheck;
    }

    public List<DependenceEntity> getItsItemsUEGCbo() {
        return itsItemsUEGCbo;
    }

    public void setItsItemsUEGCbo(List<DependenceEntity> itsItemsUEGCbo) {
        this.itsItemsUEGCbo = itsItemsUEGCbo;
    }

    public Long getItsSelectedUEG() {
        return itsSelectedUEG;
    }

    public void setItsSelectedUEG(Long itsSelectedUEG) {
        this.itsSelectedUEG = itsSelectedUEG;
    }
    
    /**
     * @return the enablePartida
     */
    public boolean isEnablePartida() {
        return enablePartida;
    }

    /**
     * @param enablePartida the enablePartida to set
     */
    public void setEnablePartida(boolean enablePartida) {
        this.enablePartida = enablePartida;
    }

    /**
     * @return the enableD
     */
    public boolean isEnableD() {
        return enableD;
    }

    /**
     * @param enableD the enableD to set
     */
    public void setEnableD(boolean enableD) {
        this.enableD = enableD;
    }

    /**
     * @return the itsSelectedD
     */
    public String getItsSelectedD() {
        return itsSelectedD;
    }

    /**
     * @param itsSelectedD the itsSelectedD to set
     */
    public void setItsSelectedD(String itsSelectedD) {
        this.itsSelectedD = itsSelectedD;
    }

    /**
     * @return the itsItemsDCbo
     */
    public List<DestinyObjectExpenseRUBUEntity> getItsItemsDCbo() {
        return itsItemsDCbo;
    }

    /**
     * @param itsItemsDCbo the itsItemsDCbo to set
     */
    public void setItsItemsDCbo(List<DestinyObjectExpenseRUBUEntity> itsItemsDCbo) {
        this.itsItemsDCbo = itsItemsDCbo;
    }

    /**
     * @return the itsItemsAmbitoCbo
     */
    public List<ObjectExpenseEntity> getItsItemsPartidaCbo() {
        return itsItemsPartidaCbo;
    }

    /**
     * @param itsItemsAmbitoCbo the itsItemsAmbitoCbo to set
     */
    public void setItsItemsPartidaCbo(List<ObjectExpenseEntity> itsItemsPartidaCbo) {
        this.itsItemsPartidaCbo = itsItemsPartidaCbo;
    }

    /**
     * @return the itsSelectedPartida
     */
    public String getItsSelectedPartida() {
        return itsSelectedPartida;
    }

    /**
     * @param itsSelectedPartida the itsSelectedPartida to set
     */
    public void setItsSelectedPartida(String itsSelectedPartida) {
        this.itsSelectedPartida = itsSelectedPartida;
    }

    /**
     * @return the concept
     */
    public String getConcept() {
        return concept;
    }

    /**
     * @param concept the concept to set
     */
    public void setConcept(String concept) {
        this.concept = concept;
    }

    /**
     * @return the techo
     */
    public String getTecho() {
        return techo;
    }

    /**
     * @param techo the techo to set
     */
    public void setTecho(String techo) {
        if(techo!=null && !techo.equals(""))        
            techo = FormatNumber.formatNumber(techo);
        this.techo = techo;
    }

    /**
     * @param asigned the asigned to set
     */
    public void setAsigned(String asigned) {
        if(asigned!=null && !asigned.isEmpty())        
            asigned = FormatNumber.formatNumber(asigned);
        this.asigned = asigned;
    }

    /**
     * @return the asigned
     */
    public String getAsigned() {    
        return asigned;
    }

    /**
     * @param forAsigned the forAsigned to set
     */
    public void setForAsigned(String forAsigned) {
        if(forAsigned!=null && !forAsigned.isEmpty())        
            forAsigned = FormatNumber.formatNumber(forAsigned);
        this.forAsigned = forAsigned;
    }

    /**
     * @return the forAsigned
     */
    public String getForAsigned() {
        return forAsigned;
    }
}
