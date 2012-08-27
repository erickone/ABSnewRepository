/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import com.abs.siif.budget.entities.DestinationEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.support.FormatNumber;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author abs71
 */
public class ApplicationBudgetDto implements Serializable {

    private String concept;
    private String techo;
    private BigDecimal asigned;
    private String forAsigned;
    private Long itsSelectedPartida;
    private List<ObjectExpenseEntity> itsItemsPartidaCbo;
    private Long itsSelectedD;
    private List<DestinationEntity> itsItemsDCbo;
    private boolean enableD;
    private boolean enablePartida;
    private List<DependenceEntity> itsItemsUEGCbo;
    private String itsSelectedUEG;
    private Date vencimiento;

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }    

    public List<DependenceEntity> getItsItemsUEGCbo() {
        return itsItemsUEGCbo;
    }

    public void setItsItemsUEGCbo(List<DependenceEntity> itsItemsUEGCbo) {
        this.itsItemsUEGCbo = itsItemsUEGCbo;
    }

    public String getItsSelectedUEG() {
        return itsSelectedUEG;
    }

    public void setItsSelectedUEG(String itsSelectedUEG) {
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
    public Long getItsSelectedD() {
        return itsSelectedD;
    }

    /**
     * @param itsSelectedD the itsSelectedD to set
     */
    public void setItsSelectedD(Long itsSelectedD) {
        this.itsSelectedD = itsSelectedD;
    }

    /**
     * @return the itsItemsDCbo
     */
    public List<DestinationEntity> getItsItemsDCbo() {
        return itsItemsDCbo;
    }

    /**
     * @param itsItemsDCbo the itsItemsDCbo to set
     */
    public void setItsItemsDCbo(List<DestinationEntity> itsItemsDCbo) {
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
    public Long getItsSelectedPartida() {
        return itsSelectedPartida;
    }

    /**
     * @param itsSelectedPartida the itsSelectedPartida to set
     */
    public void setItsSelectedPartida(Long itsSelectedPartida) {
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
    public void setAsigned(BigDecimal asigned) {
        this.asigned = asigned;
    }

    /**
     * @return the asigned
     */
    public BigDecimal getAsigned() {
        return asigned;
    }

    /**
     * @param forAsigned the forAsigned to set
     */
    public void setForAsigned(String forAsigned) {
        this.forAsigned = forAsigned;
    }

    /**
     * @return the forAsigned
     */
    public String getForAsigned() {
        return forAsigned;
    }
}
