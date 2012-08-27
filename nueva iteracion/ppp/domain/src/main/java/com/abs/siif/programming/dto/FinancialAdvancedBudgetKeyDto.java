/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  FinancialAdvancedBudgetKeyDto
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dto;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author ABS15
 */
public class FinancialAdvancedBudgetKeyDto implements Serializable{
    
    private Long idcvepresupuestal;
    private String key;
    private String financingSource;
    private Long idFinancingSource;
    private Long iddefcvepresupuestal;
    private Long idObjectExpensive;
    private double originalAmount;
    private Map<String,Long> mensualBudgetKeyIds;
    private Map<String,String> mensualBudgetKeyValue;
    private double totalCve;
    private double diff;

    /**
     * 
     * @return idFinancingSource
     */
    public Long getIdFinancingSource() {
        return idFinancingSource;
    }

    /**
     * 
     * @param idFinancingSource 
     */
    public void setIdFinancingSource(Long idFinancingSource) {
        this.idFinancingSource = idFinancingSource;
    }

    /**
     * 
     * @return idObjectExpensive
     */
    public Long getIdObjectExpensive() {
        return idObjectExpensive;
    }

    /**
     * 
     * @param idObjectExpensive 
     */
    public void setIdObjectExpensive(Long idObjectExpensive) {
        this.idObjectExpensive = idObjectExpensive;
    }

    /**
     * 
     * @return iddefcvepresupuestal
     */
    public Long getIddefcvepresupuestal() {
        return iddefcvepresupuestal;
    }

    /**
     * 
     * @param iddefcvepresupuestal 
     */
    public void setIddefcvepresupuestal(Long iddefcvepresupuestal) {
        this.iddefcvepresupuestal = iddefcvepresupuestal;
    }
    
    /**
     * 
     * @return mensualBudgetKeyIds
     */
    public Map<String, Long> getMensualBudgetKeyIds() {
        return mensualBudgetKeyIds;
    }

    /**
     * 
     * @param mensualBudgetKeyIds 
     */
    public void setMensualBudgetKeyIds(Map<String, Long> mensualBudgetKeyIds) {
        this.mensualBudgetKeyIds = mensualBudgetKeyIds;
    }

    /**
     * 
     * @return mensualBudgetKeyValue
     */
    public Map<String, String> getMensualBudgetKeyValue() {
        return mensualBudgetKeyValue;
    }

    /**
     * 
     * @param mensualBudgetKeyValue 
     */
    public void setMensualBudgetKeyValue(Map<String, String> mensualBudgetKeyValue) {
        this.mensualBudgetKeyValue = mensualBudgetKeyValue;
    }

    
    /**
     * 
     * @return diff
     */
    public double getDiff() {
        return diff;
    }

    /**
     * 
     * @param diff 
     */
    public void setDiff(double diff) {
        this.diff = diff;
    }
    
    /**
     * 
     * @return totalCve
     */
    public double getTotalCve() {
        return totalCve;
    }

    /**
     * 
     * @param totalCve 
     */
    public void setTotalCve(double totalCve) {
        this.totalCve = totalCve;
    }
    
    /**
     * 
     * @return financingSource
     */
    public String getFinancingSource() {
        return financingSource;
    }

    /**
     * 
     * @param financingSource 
     */
    public void setFinancingSource(String financingSource) {
        this.financingSource = financingSource;
    }

    /**
     * 
     * @return idcvepresupuestal
     */
    public Long getIdcvepresupuestal() {
        return idcvepresupuestal;
    }

    /**
     * 
     * @param idcvepresupuestal 
     */
    public void setIdcvepresupuestal(Long idcvepresupuestal) {
        this.idcvepresupuestal = idcvepresupuestal;
    }

    /**
     * 
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key 
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 
     * @return originalAmount
     */
    public double getOriginalAmount() {
        return originalAmount;
    }

    /**
     * 
     * @param originalAmount 
     */
    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }

}
