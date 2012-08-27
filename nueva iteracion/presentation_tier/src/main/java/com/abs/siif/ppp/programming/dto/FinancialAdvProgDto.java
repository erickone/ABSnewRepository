/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ABS15
 */
public class FinancialAdvProgDto {
    
    private String clavePresupuestal;
    private String fteFto;
    private String anualAmount;
    private Map<String, String> avanceFinancieroProg;

    /*public FinancialAdvProgDto(String clavePresupuestal, String fteFto) {
        this.clavePresupuestal = clavePresupuestal;
        this.fteFto = fteFto;
        Map<String,String> mp = new HashMap<String, String>();
        mp.put("ENE", "");
        mp.put("DIC", "");
        this.avanceFinancieroProg = mp;
     
    }*/

    public String getAnualAmount() {
        return anualAmount;
    }

    public void setAnualAmount(String anualAmount) {
        this.anualAmount = anualAmount;
    }
     
    
    public void setAvanceFinancieroProg(Map<String, String> avanceFinancieroProg) {
        this.avanceFinancieroProg = avanceFinancieroProg;
    }

    public void setClavePresupuestal(String clavePresupuestal) {
        this.clavePresupuestal = clavePresupuestal;
    }

    public void setFteFto(String fteFto) {
        this.fteFto = fteFto;
    }

    public Map<String, String> getAvanceFinancieroProg() {
        return avanceFinancieroProg;
    }

    public String getClavePresupuestal() {
        return clavePresupuestal;
    }

    public String getFteFto() {
        return fteFto;
    }
    
}
