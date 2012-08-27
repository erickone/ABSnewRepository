/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  PreInvRequestControllerApi
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

import com.abs.siif.budget.entities.FederalDependenceEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.io.IOException;
import java.util.Collection;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author luis.carreon
 */
public interface PreInvRequestControllerApi {

    void activateRadioButtons();

    /**
     * @return the theirActualPreInvRquestId
     */
    Long getActualPreInvRequestId();

    /**
     * @return the theirAuhtNumber
     */
    String getAuhtNumber();

    /**
     * @return the theirFederalDependence
     */
    Collection<FederalDependenceEntity> getFederalDependence();

    /**
     * @return the theirFederalDependenceSelectedId
     */
    Long getFederalDependenceSelectedId();

    /**
     * @return the theirFileName
     */
    String getFileName();

    /**
     * @return the isAcceptanceAct
     */
    int getIsAcceptanceAct();

    /**
     * @return the isAccreditationOfProperty
     */
    int getIsAccreditationOfProperty();

    /**
     * @return the isAdvanceLevel
     */
    int getIsAdvanceLevel();

    /**
     * @return the isAuthOfUse
     */
    int getIsAuthOfUse();

    /**
     * @return the isCostBenefitStudy
     */
    int getIsCostBenefitStudy();

    /**
     * @return the isEnvImpactDictum
     */
    int getIsEnvImpactDictum();

    /**
     * @return the isExecutiveProject
     */
    int getIsExecutiveProject();

    /**
     * @return the isFeasibilityDictum
     */
    int getIsFeasibilityDictum();

    /**
     * @return the isIntegrationAct
     */
    int getIsIntegrationAct();

    /**
     * @return the activateRdButtons
     */
    boolean getIsRdButtonActive();

    /**
     * @return the isSoilMechanicsStudy
     */
    int getIsSoilMechanicsStudy();

    /**
     * @return the theirOtherStudiesDescription
     */
    String getOtherStudiesDescription();

    /**
     * @return the theirPreFileId
     */
    Long getPreFileId();

    /**
     * @return the theirInvPreFileEntity
     */
    InvPreFileEntity getTheirInvPreFileEntity();

    void init();

    void loadFile(FileUploadEvent event) throws IOException;
    
    StreamedContent getFileStreamedContent () throws Exception;

    void savePreInvRequest();
    
    public void saveUploadedFile();

    /**
     * @param theirActualPreInvRquestId the theirActualPreInvRquestId to set
     */
    void setActualPreInvRequestId(Long anActualPreInvRquestId);

    /**
     * @param theirAuhtNumber the theirAuhtNumber to set
     */
    void setAuhtNumber(String anAuhtNumber);

    /**
     * @param theirFederalDependence the theirFederalDependence to set
     */
    void setFederalDependence(Collection<FederalDependenceEntity> aFederalDependence);

    /**
     * @param theirFederalDependenceSelectedId the theirFederalDependenceSelectedId to set
     */
    void setFederalDependenceSelectedId(Long aFederalDependenceSelectedId);

    /**
     * @param theirFileName the theirFileName to set
     */
    void setFileName(String aFileName);

    /**
     * @param isAcceptanceAct the isAcceptanceAct to set
     */
    void setIsAcceptanceAct(int isAcceptanceAct);

    /**
     * @param isAccreditationOfProperty the isAccreditationOfProperty to set
     */
    void setIsAccreditationOfProperty(int isAccreditationOfProperty);

    /**
     * @param isAdvanceLevel the isAdvanceLevel to set
     */
    void setIsAdvanceLevel(int isAdvanceLevel);

    /**
     * @param isAuthOfUse the isAuthOfUse to set
     */
    void setIsAuthOfUse(int isAuthOfUse);

    /**
     * @param isCostBenefitStudy the isCostBenefitStudy to set
     */
    void setIsCostBenefitStudy(int isCostBenefitStudy);

    /**
     * @param isEnvImpactDictum the isEnvImpactDictum to set
     */
    void setIsEnvImpactDictum(int isEnvImpactDictum);

    /**
     * @param isExecutiveProject the isExecutiveProject to set
     */
    void setIsExecutiveProject(int isExecutiveProject);

    /**
     * @param isFeasibilityDictum the isFeasibilityDictum to set
     */
    void setIsFeasibilityDictum(int isFeasibilityDictum);

    /**
     * @param isIntegrationAct the isIntegrationAct to set
     */
    void setIsIntegrationAct(int isIntegrationAct);

    /**
     * @param activateRdButtons the activateRdButtons to set
     */
    void setIsRdButtonActive(boolean anActivateValue);

    /**
     * @param isSoilMechanicsStudy the isSoilMechanicsStudy to set
     */
    void setIsSoilMechanicsStudy(int isSoilMechanicsStudy);

    /**
     * @param theirOtherStudiesDescription the theirOtherStudiesDescription to set
     */
    void setOtherStudiesDescription(String anOtherStudiesDescription);

    /**
     * @param theirPreFileId the theirPreFileId to set
     */
    void setPreFileId(Long aPreFileId);

    /**
     * @param theirInvPreFileEntity the theirInvPreFileEntity to set
     */
    void setTheirInvPreFileEntity(InvPreFileEntity theirInvPreFileEntity);
    
}
