/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  PedDatailsDTO
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

import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.planning.entities.ObjectiveIndicatorEntity;
import com.abs.siif.planning.entities.ProblemEntity;
import com.abs.siif.planning.entities.SpecificObjectiveEntity;
import java.io.Serializable;
import java.util.List;

/**
 * Se encarga de traer los datos relacionados con la Vinculación al PED
 * que seran utilizados durante la captura directa
 * @author Israel Ruiz
 */
public class PedDatailsDTO implements Serializable {
    
    private ObjectiveEntity estrategyAxisList;
    private List<ProblemEntity> problemList;
    private List<ObjectiveEntity> programList;
    private List<ObjectiveEntity> subProgramList;
    private List<SpecificObjectiveEntity> especificObjList;
    private List<ObjectiveIndicatorEntity> objIndicatorList;

    /**
     * @return the estrategyAxisList
     */
    public ObjectiveEntity getEstrategyAxisList() {
        return estrategyAxisList;
    }

    /**
     * @param estrategyAxisList the estrategyAxisList to set
     */
    public void setEstrategyAxisList(ObjectiveEntity estrategyAxisList) {
        this.estrategyAxisList = estrategyAxisList;
    }

    /**
     * @return the problemList
     */
    public List<ProblemEntity> getProblemList() {
        return problemList;
    }

    /**
     * @param problemList the problemList to set
     */
    public void setProblemList(List<ProblemEntity> problemList) {
        this.problemList = problemList;
    }

    /**
     * @return the programList
     */
    public List<ObjectiveEntity> getProgramList() {
        return programList;
    }

    /**
     * @param programList the programList to set
     */
    public void setProgramList(List<ObjectiveEntity> programList) {
        this.programList = programList;
    }

    /**
     * @return the subProgramList
     */
    public List<ObjectiveEntity> getSubProgramList() {
        return subProgramList;
    }

    /**
     * @param subProgramList the subProgramList to set
     */
    public void setSubProgramList(List<ObjectiveEntity> subProgramList) {
        this.subProgramList = subProgramList;
    }

    /**
     * @return the especificObjList
     */
    public List<SpecificObjectiveEntity> getEspecificObjList() {
        return especificObjList;
    }

    /**
     * @param especificObjList the especificObjList to set
     */
    public void setEspecificObjList(List<SpecificObjectiveEntity> especificObjList) {
        this.especificObjList = especificObjList;
    }

    /**
     * @return the objIndicatorList
     */
    public List<ObjectiveIndicatorEntity> getObjIndicatorList() {
        return objIndicatorList;
    }

    /**
     * @param objIndicatorList the objIndicatorList to set
     */
    public void setObjIndicatorList(List<ObjectiveIndicatorEntity> objIndicatorList) {
        this.objIndicatorList = objIndicatorList;
    }
            
}
