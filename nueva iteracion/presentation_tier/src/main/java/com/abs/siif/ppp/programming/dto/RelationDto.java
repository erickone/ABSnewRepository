/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.dto;

import com.abs.siif.budget.entities.FinancingSourceEntity;
import com.abs.siif.budget.entities.BudgetDetailsKeyEntity;
import com.abs.siif.support.FormatNumber;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author abs71
 */
public class RelationDto implements Serializable {

    private String clavePresupuestal;
    private String fuenteFinanciamiento;
    private String vencimiento;
    private String techoCve;
    private String asignadoEstatal;
    private String asignadoAdicional;
    private boolean disableAditional;
    private boolean disableEstatal;
    private boolean disableAll;
    private String itsSelectedFuente;
    private List<FinancingSourceEntity> itsItemsFuenteCbo;    
    private List<BudgetDetailsKeyEntity> budgetDetailsKeyList;
    private Long objectId;
    private Long cveid;
    private boolean disableEdit;
    private boolean disableDelete;

    public boolean isDisableDelete() {
        return disableDelete;
    }

    public void setDisableDelete(boolean disableDelete) {
        this.disableDelete = disableDelete;
    }

    public boolean isDisableEdit() {
        return disableEdit;
    }

    public void setDisableEdit(boolean disableEdit) {
        this.disableEdit = disableEdit;
    }    

    public Long getCveid() {
        return cveid;
    }

    public void setCveid(Long cveid) {
        this.cveid = cveid;
    }
    
    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }    
    
    public List<BudgetDetailsKeyEntity> getBudgetDetailsKeyList(){
        return budgetDetailsKeyList;
    }
    
    public void setBudgetDetailsKeyList(List<BudgetDetailsKeyEntity> budgetDetailsKeyList){
        this.budgetDetailsKeyList = budgetDetailsKeyList;
    }

    public boolean isDisableAll() {
        return disableAll;
    }

    public void setDisableAll(boolean disableAll) {
        this.disableAll = disableAll;
    }

    public boolean isDisableEstatal() {
        return disableEstatal;
    }

    public void setDisableEstatal(boolean disableEstatal) {
        this.disableEstatal = disableEstatal;
    }

    public boolean isDisableAditional() {
        return disableAditional;
    }

    public void setDisableAditional(boolean disableAditional) {
        this.disableAditional = disableAditional;
    }

    public String getAsignadoAdicional() {
        return asignadoAdicional;
    }

    public void setAsignadoAdicional(String asignadoAdicional) {
        if(asignadoAdicional!=null & !asignadoAdicional.isEmpty())
            asignadoAdicional = FormatNumber.formatNumber(asignadoAdicional);
        this.asignadoAdicional = asignadoAdicional;
    }

    public String getAsignadoEstatal() {
        return asignadoEstatal;
    }

    public void setAsignadoEstatal(String asignadoEstatal) {
        if(asignadoEstatal!=null & !asignadoEstatal.isEmpty())
            asignadoEstatal = FormatNumber.formatNumber(asignadoEstatal);
        this.asignadoEstatal = asignadoEstatal;
    }

    /**
     * @return the itsSelectedFuente
     */
    public String getItsSelectedFuente() {
        return itsSelectedFuente;
    }

    /**
     * @param itsSelectedFuente the itsSelectedFuente to set
     */
    public void setItsSelectedFuente(String itsSelectedFuente) {
        this.itsSelectedFuente = itsSelectedFuente;
    }

    /**
     * @return the itsItemsFuenteCbo
     */
    public List<FinancingSourceEntity> getItsItemsFuenteCbo() {
        return itsItemsFuenteCbo;
    }

    /**
     * @param itsItemsDCbo the itsItemsDCbo to set
     */
    public void setItsItemsFuenteCbo(List<FinancingSourceEntity> itsItemsFuenteCbo) {
        this.itsItemsFuenteCbo = itsItemsFuenteCbo;
    }

    /**
     * @return the clavePresupuestal
     */
    public String getClavePresupuestal() {
        return clavePresupuestal;
    }

    /**
     * @param clavePresupuestal the clavePresupuestal to set
     */
    public void setClavePresupuestal(String clavePresupuestal) {
        this.clavePresupuestal = clavePresupuestal;
    }

    /**
     * @return the fuenteFinanciamiento
     */
    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    /**
     * @param fuenteFinanciamiento the fuenteFinanciamiento to set
     */
    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    /**
     * @return the vencimiento
     */
    public String getVencimiento() {
        return vencimiento;
    }

    /**
     * @param vencimiento the vencimiento to set
     */
    public void setVencimiento(Date vencimiento) {
        String ven="";
        if(vencimiento!=null){            
            SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
            ven = format.format(vencimiento);
        }
        this.vencimiento = ven;
    }

    /**
     * @return the techoCve
     */
    public String getTechoCve() {
        return techoCve;
    }

    /**
     * @param techoCve the techoCve to set
     */
    public void setTechoCve(String techoCve) {        
        if(techoCve!=null && !techoCve.equals(""))  
            techoCve = FormatNumber.formatNumber(techoCve);
        this.techoCve = techoCve;
    }
}
