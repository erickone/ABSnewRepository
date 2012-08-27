/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dto;

import com.abs.siif.budget.entities.CeillingBudgetEntity;
import java.io.Serializable;

/**
 *
 * @author absvalenzuela
 */
public class CellCeilingBudgetDto implements Serializable {

    private int registerId;

    private Long elementType;

    private String dataElement;

    private String mesageError;

    private ValidationNameEnum validatorName;

    private CeillingBudgetEntity objentity;

    public String getDataElement() {
        return dataElement;
    }

    public void setDataElement(String dataElement) {
        this.dataElement = dataElement;
    }

    public Long getElementType() {
        return elementType;
    }

    public void setElementType(Long elementType) {
        this.elementType = elementType;
    }

    public String getMesageError() {
        return mesageError;
    }

    public void setMesageError(String mesageError) {
        this.mesageError = mesageError;
    }

    public int getRegisterId() {
        return registerId;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    /**
     * @return the validatorName
     */
    public String getValidatorName() {
        return validatorName.getBeanName();
    }

    /**
     * @param validatorName the validatorName to set
     */
    public void setValidatorName(ValidationNameEnum validatorName) {
        this.validatorName = validatorName;
    }

    public String getElementForIndex(int iIndexRow) {
        String data = "";
        try {


            if (iIndexRow == 0) {
                data = String.valueOf(registerId);
            } else {
                data = this.getDataElement();
            }
        } catch (Exception ex) {
            data = mesageError != null ? "No" : "Si";
        }
        return data;
    }

    public CeillingBudgetEntity getObjentity() {
        return objentity;
    }

    public void setObjentity(CeillingBudgetEntity objentity) {
        this.objentity = objentity;
    }
    
    
}
