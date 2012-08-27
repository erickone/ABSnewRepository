/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import com.abs.siif.programming.entities.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clases para obtener los catalogos de 
 * Metas y beneficiarios
 * @author Israel Ruiz
 */
public class BenefGoalsCatDTO implements Serializable {
    private List<UnitMeasureEntity> uMeasures;
    private List<VulnerableGroupEntity> vunerableGroupEntity;
    private List<GeneralConceptEntity> generalConcepts;
    private List<InvestmentLineEntity> investmentLines;
    private List<MacroBuildEntity> macroBuild;

    /**
     * @return the uMeasures
     */
    public List<UnitMeasureEntity> getuMeasures() {
        return uMeasures;
    }

    /**
     * @param uMeasures the uMeasures to set
     */
    public void setuMeasures(List<UnitMeasureEntity> uMeasures) {
        this.uMeasures = uMeasures;
    }

    /**
     * @return the vunerableGroupEntity
     */
    public List<VulnerableGroupEntity> getVunerableGroupEntity() {
        return vunerableGroupEntity;
    }

    /**
     * @param vunerableGroupEntity the vunerableGroupEntity to set
     */
    public void setVunerableGroupEntity(List<VulnerableGroupEntity> vunerableGroupEntity) {
        this.vunerableGroupEntity = vunerableGroupEntity;
    }

    /**
     * @return the generalConcepts
     */
    public List<GeneralConceptEntity> getGeneralConcepts() {
        return generalConcepts;
    }

    /**
     * @param generalConcepts the generalConcepts to set
     */
    public void setGeneralConcepts(List<GeneralConceptEntity> generalConcepts) {
        this.generalConcepts = generalConcepts;
    }

    /**
     * @return the investmentLines
     */
    public List<InvestmentLineEntity> getInvestmentLines() {
        return investmentLines;
    }

    /**
     * @param investmentLines the investmentLines to set
     */
    public void setInvestmentLines(List<InvestmentLineEntity> investmentLines) {
        this.investmentLines = investmentLines;
    }

    /**
     * @return the macroBuild
     */
    public List<MacroBuildEntity> getMacroBuild() {
        return macroBuild;
    }

    /**
     * @param macroBuild the macroBuild to set
     */
    public void setMacroBuild(List<MacroBuildEntity> macroBuild) {
        this.macroBuild = macroBuild;
    }
    
}
