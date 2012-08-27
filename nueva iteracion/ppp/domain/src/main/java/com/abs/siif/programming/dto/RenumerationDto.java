/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  RenumerationDto
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

/**
 *
 * @author luis.carreon
 */
public class RenumerationDto {

    private Long draftProjectId;
    private String draftprojectNumber;
    private String newDraftprojectNumber;
    private String draftprojectName;
    private String draftprojectShortName;
    private boolean isCorrect = true;
    private boolean isEnabledEdit = true;
    private boolean isValueChanged = false;

    public RenumerationDto(Long draftProyectId, String draftproyectNumber, String newDraftproyectNumber, String draftproyectName, String draftproyectShortName) {
        this.draftProjectId = draftProyectId;
        this.draftprojectNumber = draftproyectNumber;
        this.newDraftprojectNumber = newDraftproyectNumber;
        this.draftprojectName = draftproyectName;
        this.draftprojectShortName = draftproyectShortName;
    }

    public Long getDraftProjectId() {
        return draftProjectId;
    }

    public void setDraftProjectId(Long draftProyectId) {
        this.draftProjectId = draftProyectId;
    }

    public String getDraftprojectName() {
        return draftprojectName;
    }

    public void setDraftprojectName(String draftprojectName) {
        this.draftprojectName = draftprojectName;
    }

    public String getDraftprojectNumber() {
        return draftprojectNumber;
    }

    public void setDraftprojectNumber(String draftprojectNumber) {
        this.draftprojectNumber = draftprojectNumber;
    }

    public String getDraftprojectShortName() {
        return draftprojectShortName;
    }

    public void setDraftprojectShortName(String draftprojectShortName) {
        this.draftprojectShortName = draftprojectShortName;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean getIsEnabledEdit() {
        return isEnabledEdit;
    }

    public void setIsEnabledEdit(boolean isEnabledEdit) {
        this.isEnabledEdit = isEnabledEdit;
    }

    public String getNewDraftprojectNumber() {
        return newDraftprojectNumber;
    }

    public void setNewDraftprojectNumber(String newDraftprojectNumber) {
        if (newDraftprojectNumber.trim().equals("")) {
            this.setIsValueChanged(false);
        } else {
            this.setIsValueChanged(true);
        }
        this.newDraftprojectNumber = newDraftprojectNumber;
    }

    public boolean getIsValueChanged() {
        return isValueChanged;
    }

    public void setIsValueChanged(boolean isValueChanged) {
        this.isValueChanged = isValueChanged;
    }
}
