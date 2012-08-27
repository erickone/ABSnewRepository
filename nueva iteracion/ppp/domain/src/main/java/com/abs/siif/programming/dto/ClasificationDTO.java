/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ClasificationDTO
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

import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Israel Ruiz
 */
public class ClasificationDTO  implements Serializable{
    
    private List<DepencenceDto> budgetUnitList;
    private List<DepencenceDto> executeUnitList;
    private List<DepencenceDto> branchList;
    private List<DepencenceDto> sectorList;
    private List<FunctionalClassifierEntity> finallyList;
    private List<FunctionalClassifierEntity> funtionList;
    private List<FunctionalClassifierEntity> subFuntionList;

    /**
     * @return the budgetUnitList
     */
    public List<DepencenceDto> getBudgetUnitList() {
        return budgetUnitList;
    }

    /**
     * @param budgetUnitList the budgetUnitList to set
     */
    public void setBudgetUnitList(List<DepencenceDto> budgetUnitList) {
        this.budgetUnitList = budgetUnitList;
    }

    /**
     * @return the executeUnitList
     */
    public List<DepencenceDto> getExecuteUnitList() {
        return executeUnitList;
    }

    /**
     * @param executeUnitList the executeUnitList to set
     */
    public void setExecuteUnitList(List<DepencenceDto> executeUnitList) {
        this.executeUnitList = executeUnitList;
    }

    /**
     * @return the branchList
     */
    public List<DepencenceDto> getBranchList() {
        return branchList;
    }

    /**
     * @param branchList the branchList to set
     */
    public void setBranchList(List<DepencenceDto> branchList) {
        this.branchList = branchList;
    }

    /**
     * @return the sectorList
     */
    public List<DepencenceDto> getSectorList() {
        return sectorList;
    }

    /**
     * @param sectorList the sectorList to set
     */
    public void setSectorList(List<DepencenceDto> sectorList) {
        this.sectorList = sectorList;
    }

    /**
     * @return the finallyList
     */
    public List<FunctionalClassifierEntity> getFinallyList() {
        return finallyList;
    }

    /**
     * @param finallyList the finallyList to set
     */
    public void setFinallyList(List<FunctionalClassifierEntity> finallyList) {
        this.finallyList = finallyList;
    }

    /**
     * @return the funtionList
     */
    public List<FunctionalClassifierEntity> getFuntionList() {
        return funtionList;
    }

    /**
     * @param funtionList the funtionList to set
     */
    public void setFuntionList(List<FunctionalClassifierEntity> funtionList) {
        this.funtionList = funtionList;
    }

    /**
     * @return the subFuntionList
     */
    public List<FunctionalClassifierEntity> getSubFuntionList() {
        return subFuntionList;
    }

    /**
     * @param subFuntionList the subFuntionList to set
     */
    public void setSubFuntionList(List<FunctionalClassifierEntity> subFuntionList) {
        this.subFuntionList = subFuntionList;
    }
    
    
}
