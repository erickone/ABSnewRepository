/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  DraftProjectSearchControllerApi
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
 * @author luis.carreon
 */
public interface DraftProjectSearchControllerApi {

    void addDraftProject();

    /**
     * Este método obtiene el ID del estatus indicado en la enumeración, en base
     * al consecutivo asignado a al estatus
     */
    void assignStatusFromConsecutive();

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

    /**
     * @return the theirDraftStatusId
     */
    Long getDraftStatusId();

    int getDraftType();

    Long getDraftTypeId();

    /**
     * @return the idDependency
     */
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

    boolean isIsNewButtonDisabled();

    boolean isItsDisabledTab();

    boolean isItsDisabledValidacionSeplanTab();

    String navigateInvPre();

    void saveDraftProject();

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

    void setIsNewButtonDisabled(boolean isNewButtonDisabled);

    void setItsAxisSelected(ObjectiveJoinLevelTreeviewDto itsAxisSelected);

    void setItsCmbTypeList(List<DraftProjectTypeEntity> itsCmbTypeList);

    void setItsDisabledTab(boolean itsDisabledTab);

    void setItsDisabledValidacionSeplanTab(boolean itsDisabledValidacionSeplanTab);

    /**
     * @param itsDraftProjectEntity the itsDraftProjectEntity to set
     */
    void setItsDraftProjectEntity(DraftProjectEntity itsDraftProjectEntity);

    void setItsFileTypeList(List<DraftProjectTypeEntity> itsFileTypeList);

    void setItsProgramSelected(ObjectiveJoinLevelTreeviewDto itsProgramSelected);

    void setItsSelectedUEG(Long itsSelectedUEG);

    void setItsSubProgramSelected(ObjectiveJoinLevelTreeviewDto itsSubProgramSelected);

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
    
    int getItsTabActiveIndex();

    void setItsTabActiveIndex(int itsTabActiveIndex);
    
    
}
