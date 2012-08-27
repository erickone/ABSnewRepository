/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DraftProjectHeaderControllerApi
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.api.controller;

import com.abs.siif.common.dto.SampleEntityDto;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.DraftProjectTypeEntity;
import com.abs.siif.programming.entities.VulnerableGroupEntity;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.primefaces.event.DateSelectEvent;

/**
 *
 * @author Israel Ruiz
 */
public interface DraftProjectHeaderControllerApi {

    void addDraftProject();

    /**
     * Este método obtiene el ID del estatus indicado en la enumeración, en base al
     * consecutivo asignado a al estatus
     */
    void assignStatusFromConsecutive();

    /**
     * @return the btnSrvSave
     */
    Boolean getBtnSrvSave();

    String getDraftDescription();

    int getDraftDurationInMonths();

    Date getDraftEndDate();

    String getDraftLegalBase();

    String getDraftNumber();

    String getDraftProjectName();

    /**
     * @return the theirDraftPurpose
     */
    String getDraftPurpose();

    Long getDraftScopeId();

    String getDraftShortName();

    Date getDraftStartDate();

    Long getDraftStateId();

    /**
     * @return the theirDraftStatusDescription
     */
    String getDraftStatusDescription();

    Long getDraftStatusId();

    int getDraftType();

    Long getDraftTypeId();

    Long getIdDependency();

    ObjectiveJoinLevelTreeviewDto getItsAxisSelected();

    List<DraftProjectTypeEntity> getItsCmbTypeList();

    /**
     * @return the itsDraftProjectEntity
     */
    DraftProjectEntity getItsDraftProjectEntity();

    List<DraftProjectTypeEntity> getItsFileTypeList();

    ObjectiveJoinLevelTreeviewDto getItsProgramSelected();

    Long getItsSelectedUEG();

    ObjectiveJoinLevelTreeviewDto getItsSubProgramSelected();

    int getItsTabActiveIndex();

    String getMinDate();

    Map<String, List<?>> getMySupportList();

    SampleEntityDto getSelectedDraftType();

    /**
     * @return the selectedOptions
     */
    List<String> getSelectedVulGrops();

    /**
     * @return the theirStatusConsecutive
     */
    int getStatusConsecutive();

    Long getTheirCurrentDraftProjectId();

    Long getTheirObjectiveId();

    Long getTheirProgrammingId();

    SampleEntityDto getTheirSelectedDraftProjectScope();

    SampleEntityDto getTheirSelectedDraftProjectState();

    SampleEntityDto getTheirSelectedDraftType();

    /**
     * @return the vulnerableGroup
     */
    List<VulnerableGroupEntity> getVulnerableGroup();

    void handleDateSelectEndDate(DateSelectEvent event);

    void handleDateSelectStartDate(DateSelectEvent event);

    void initSupporList();

    /**
     * @return the isDisabledTxtShortName
     */
    boolean isIsDisabledTxtShortName();

    boolean isIsNewButtonDisabled();

    boolean isItsDisabledTab();

    /**
     * @return the itsDisabledTabButton
     */
    boolean isItsDisabledTabButton();

    /**
     * @return the itsDisabledTabClassif
     */
    boolean isItsDisabledTabClassif();

    /**
     * @return the itsDisabledTabDelivery
     */
    boolean isItsDisabledTabDelivery();

    boolean isItsDisabledValidacionSeplanTab();

    String navigateInvPre();

    void saveDraftProject();

    /**
     * @param btnSrvSave the btnSrvSave to set
     */
    void setBtnSrvSave(Boolean btnSrvSave);

    void setDraftDescription(String aDraftDescription);

    void setDraftDurationInMonths(int aDurationInMonths);

    void setDraftEndDate(Date aDraftEndDate);

    void setDraftLegalBase(String aDraftLegalBase);

    void setDraftNumber(String aDraftNumber);

    void setDraftProjectName(String aDraftName);

    /**
     * @param theirDraftPurpose the theirDraftPurpose to set
     */
    void setDraftPurpose(String theirDraftPurpose);

    void setDraftScopeId(Long theirDraftScopeId);

    void setDraftShortName(String aDraftShortName);

    void setDraftStartDate(Date aDraftStartDate);

    void setDraftStateId(Long aDraftStateId);

    /**
     * @param theirDraftStatusDescription the theirDraftStatusDescription to set
     */
    void setDraftStatusDescription(String theirDraftStatusDescription);

    /**
     * @param theirDraftStatusId the theirDraftStatusId to set
     */
    void setDraftStatusId(Long theirDraftStatusId);

    void setDraftType(int aDraftScope);

    void setDraftTypeId(Long aDraftTypeId);

    /**
     * @param idDependency the idDependency to set
     */
    void setIdDependency(Long idDependency);

    /**
     * @param isDisabledTxtShortName the isDisabledTxtShortName to set
     */
    void setIsDisabledTxtShortName(boolean isDisabledTxtShortName);

    void setIsNewButtonDisabled(boolean isNewButtonDisabled);

    void setItsAxisSelected(ObjectiveJoinLevelTreeviewDto itsAxisSelected);

    void setItsCmbTypeList(List<DraftProjectTypeEntity> itsCmbTypeList);

    void setItsDisabledTab(boolean itsDisabledTab);

    /**
     * @param itsDisabledTabButton the itsDisabledTabButton to set
     */
    void setItsDisabledTabButton(boolean itsDisabledTabButton);

    /**
     * @param itsDisabledTabClassif the itsDisabledTabClassif to set
     */
    void setItsDisabledTabClassif(boolean itsDisabledTabClassif);

    /**
     * @param itsDisabledTabDelivery the itsDisabledTabDelivery to set
     */
    void setItsDisabledTabDelivery(boolean itsDisabledTabDelivery);

    void setItsDisabledValidacionSeplanTab(boolean itsDisabledValidacionSeplanTab);

    /**
     * @param itsDraftProjectEntity the itsDraftProjectEntity to set
     */
    void setItsDraftProjectEntity(DraftProjectEntity itsDraftProjectEntity);

    void setItsFileTypeList(List<DraftProjectTypeEntity> itsFileTypeList);

    void setItsProgramSelected(ObjectiveJoinLevelTreeviewDto itsProgramSelected);

    void setItsSelectedUEG(Long itsSelectedUEG);

    void setItsSubProgramSelected(ObjectiveJoinLevelTreeviewDto itsSubProgramSelected);

    void setItsTabActiveIndex(int itsTabActiveIndex);

    void setMySupportList(Map<String, List<?>> aSupportList);

    void setSelectedDraftType(SampleEntityDto aSelectedDraftType);

    /**
     * @param selectedOptions the selectedOptions to set
     */
    void setSelectedVulGrops(List<String> selectedOptions);

    /**
     * @param theirStatusConsecutive the theirStatusConsecutive to set
     */
    void setStatusConsecutive(int theirStatusConsecutive);

    void setTheirCurrentDraftProjectId(Long theirCurrentDraftProjectId);

    void setTheirObjectiveId(Long theirObjectiveId);

    void setTheirProgrammingId(Long theirProgrammingId);

    void setTheirSelectedDraftProjectScope(SampleEntityDto theirSelectedDraftProjectScope);

    void setTheirSelectedDraftProjectState(SampleEntityDto theirSelectedDraftProjectState);

    void setTheirSelectedDraftType(SampleEntityDto theirSelectedDraftType);

    /**
     * @param vulnerableGroup the vulnerableGroup to set
     */
    void setVulnerableGroup(List<VulnerableGroupEntity> vulnerableGroup);

    void tipoFicha();
    
}
