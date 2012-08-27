/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import com.abs.siif.programming.entities.InputPhysicalAndFinEntity;
import com.abs.siif.programming.entities.PhysicalAndFinancialProgramEntity;
import com.abs.siif.programming.entities.PhysicalProgrammedAdvanceEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ABS15
 */
public class PhysicalAndFinancialProgramDto implements Serializable {
    private PhysicalAndFinancialProgramEntity header;
    private InputPhysicalAndFinEntity  aportations;
    private List<PhysicalProgrammedAdvanceEntity> advProgram;
    private Long idInvestPrepFile;

    public List<PhysicalProgrammedAdvanceEntity> getAdvProgram() {
        return advProgram;
    }

    public InputPhysicalAndFinEntity getAportations() {
        return aportations;
    }

    public PhysicalAndFinancialProgramEntity getHeader() {
        return header;
    }

    public Long getIdInvestPrepFile() {
        return idInvestPrepFile;
    }

    public void setAdvProgram(List<PhysicalProgrammedAdvanceEntity> advProgram) {
        this.advProgram = advProgram;
    }

    public void setAportations(InputPhysicalAndFinEntity aportations) {
        this.aportations = aportations;
    }

    public void setHeader(PhysicalAndFinancialProgramEntity header) {
        this.header = header;
    }

    public void setIdInvestPrepFile(Long idInvestPrepFile) {
        this.idInvestPrepFile = idInvestPrepFile;
    } 
}
