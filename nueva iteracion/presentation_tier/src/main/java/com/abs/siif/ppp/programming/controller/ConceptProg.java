package com.abs.siif.ppp.programming.controller;

/**
 * Clase de apoyo para el Mapa del Mensual Programado
 * @author Israel Ruiz
 */
public class ConceptProg {
    private String valueProg;
    private Long mensualComponentId;

    public ConceptProg(){

    }
    public ConceptProg(Long mensualComponentId, String programmedGoal) {
        this.mensualComponentId =mensualComponentId;
        this.valueProg = programmedGoal;
    }


    /**
     * @return the mensualComponentId
     */
    public Long getMensualComponentId() {
        return mensualComponentId;
    }

    /**
     * @param mensualComponentId the mensualComponentId to set
     */
    public void setMensualComponentId(Long mensualComponentId) {
        this.mensualComponentId = mensualComponentId;
    }

    /**
     * @return the valueProg
     */
    public String getValueProg() {
        return valueProg;
    }

    /**
     * @param valueProg the valueProg to set
     */
    public void setValueProg(String valueProg) {
        this.valueProg = valueProg;
    }
}
