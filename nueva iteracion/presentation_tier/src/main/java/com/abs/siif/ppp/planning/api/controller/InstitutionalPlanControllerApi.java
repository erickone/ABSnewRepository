/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  InstitutionalPlanControllerApi
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.api.controller;

import com.abs.siif.common.dto.SampleEntityDto;
import com.abs.siif.ppp.planning.uihelpers.ObjectiveInstitutionalPlanUIHelper;
import java.io.IOException;
import java.util.Date;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author luis.carreon
 */
public interface InstitutionalPlanControllerApi {

    Long SaveOrUpdateAnInstitutionalPlan();

    /**
     * Método para la acción del botón guardar de la pestaña de objetivos
     */
    void addObjetive();

    /**
     * Método para la acción del botón cancelar de la pestaña de objetivos
     */
    void cancelObjetive();

    void deleteAnInstitutionalPlan();

    void deleteObjective();

    boolean getItsInstitutionaBlock();

    Date getItsInstitutionalActualDate();

    Date getItsInstitutionalDateOfExtintion();

    Date getItsInstitutionalDateOfLastModif();

    Long getItsInstitutionalDependence();

    String getItsInstitutionalDiag();

    String getItsInstitutionalFormOfExtintion();

    String getItsInstitutionalGeneralObjective();

    String getItsInstitutionalInternalReg();

    Date getItsInstitutionalInternalRegDate();

    boolean getItsInstitutionalInvesEjec();

    boolean getItsInstitutionalInvesNorm();

    String getItsInstitutionalLegalBasis();

    String getItsInstitutionalMission();

    String getItsInstitutionalNumPerOficial();

    String getItsInstitutionalNumberOfLastModif();

    Date getItsInstitutionalOfficialPubOnDailyDate();

    boolean getItsInstitutionalOrganism();

    String getItsInstitutionalOrganizational();

    Long getItsInstitutionalPlanId();

    Long getItsInstitutionalToDeleteId();

    String getItsInstitutionalVision();

    Date getItsInstitutionalstartDateOfActivities();

    ObjectiveInstitutionalPlanUIHelper getObjInstPlanUIHelper();

    SampleEntityDto getSelectedDeleteSpecificObjective();

    SampleEntityDto getSelectedSpecificObjective();

    void initObjectiveTagElements();

    /**
     * Método para la acción del botón nuevo de la pestaña de objetivos
     */
    void newObjetive();

    void processFileUpload(FileUploadEvent event) throws IOException;

    void saveObjectives();
    
    StreamedContent getFileStreamedContent () throws Exception;

    void setItsInstitutionaBlock(boolean itsInstitutionaBlock);

    void setItsInstitutionalActualDate(Date itsInstitutionalActualDate);

    void setItsInstitutionalDateOfExtintion(Date itsInstitutionalDateOfExtintion);

    void setItsInstitutionalDateOfLastModif(Date itsInstitutionalDateOfLastModif);

    void setItsInstitutionalDependence(Long itsInstitutionalDependence);

    void setItsInstitutionalDiag(String itsInstitutionalDiag);

    void setItsInstitutionalFormOfExtintion(String itsInstitutionalFormOfExtintion);

    void setItsInstitutionalGeneralObjective(String itsInstitutionalGeneralObjective);

    void setItsInstitutionalInternalReg(String itsInstitutionalInternalReg);

    void setItsInstitutionalInternalRegDate(Date itsInstitutionalInternalRegDate);

    void setItsInstitutionalInvesEjec(boolean itsInstitutionalInvesEjec);

    void setItsInstitutionalInvesNorm(boolean itsInstitutionalInvesNorm);

    void setItsInstitutionalLegalBasis(String itsInstitutionalLegalBasis);

    void setItsInstitutionalMission(String itsInstitutionalMission);

    void setItsInstitutionalNumPerOficial(String itsInstitutionalNumPerOficial);

    void setItsInstitutionalNumberOfLastModif(String itsInstitutionalNumberOfLastModif);

    void setItsInstitutionalOfficialPubOnDailyDate(Date itsInstitutionalOfficialPubOnDailyDate);

    void setItsInstitutionalOrganism(boolean itsInstitutionalOrganism);

    void setItsInstitutionalOrganizational(String itsInstitutionalOrganizational);

    void setItsInstitutionalPlanId(Long itsInstitutionalPlanId);

    void setItsInstitutionalToDeleteId(Long itsInstitutionalToDeleteId);

    void setItsInstitutionalVision(String itsInstitutionalVision);

    void setItsInstitutionalstartDateOfActivities(Date itsInstitutionalstartDateOfActivities);

    void setObjInstPlanUIHelper(ObjectiveInstitutionalPlanUIHelper objInstPlanUIHelper);

    void setSelectedDeleteSpecificObjective(SampleEntityDto selectedDeleteSpecificObjective);

    void setSelectedSpecificObjective(SampleEntityDto selectedSpecificObjective);

    void updateObjective();

    boolean validations();
    
}
