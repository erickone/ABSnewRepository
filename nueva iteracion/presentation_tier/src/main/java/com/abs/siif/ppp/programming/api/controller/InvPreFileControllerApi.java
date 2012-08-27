/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  InvPreFileControllerApi
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

import com.abs.siif.planning.data.DraftFileType;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.entities.*;
import java.util.List;

/**
 *
 * @author luis.carreon
 */
public interface InvPreFileControllerApi {

    void addInvPreFile();

    void cleanView();

    void disableTabs(boolean aFlag);

    String getTheirInvPreFileBenefitDesc();

    Long getTheirInvPreFileComponent();

    List<ComponentEntity> getTheirInvPreFileComponentList();

    String getTheirInvPreFileDesagLevel();

    String getTheirInvPreFileDescription();

    DraftProjectEntity getTheirInvPreFileDraftEntity();

    DraftFileType getTheirInvPreFileDraftProyect();

    Long getTheirInvPreFileId();

    String getTheirInvPreFileName();

    String getTheirInvPreFileNumber();

    String getTheirInvPreFilePcPy();

    String getTheirInvPreFilePriority();

    Long getTheirInvPreFilePromoter();

    List<PromoterEntity> getTheirInvPreFilePromoterList();

    String getTheirInvPreFileType();

    List<DependenceEntity> getTheirInvPreFileUEGList();

    List<String> getTheirInvPreFileUEGSelected();

    String getTheirInvPreFileURE();

    DependenceEntity getTheirInvPreFileUREEntity();

    Long getTheirInvPreFileURN();

    List<FederalURRegulatoryEntity> getTheirInvPreFileURNList();

    boolean isIsFinStructureTabDisabled();

    boolean isIsGoalsTabDisabled();

    boolean isIsPFProgTabDisabled();

    boolean isIsRequestTabDisabled();

    boolean isIsSaveButtonDisabled();

    boolean isIsSizingTabDisabled();

    boolean isIsUbicationTabDisabled();

    boolean isTheirInvPreFileAction();

    boolean isTheirInvPreFileBuild();

    boolean isTheirInvPreFileEspecifProy();

    boolean isTheirInvPreFileFund();

    boolean isTheirInvPreFileProgram();

    void prepareNewInvPreFile();

    void saveInvPreFile();

    void sendInvPreFileId();

    void setIsFinStructureTabDisabled(boolean isFinStructureTabDisabled);

    void setIsGoalsTabDisabled(boolean isGoalsTabDisabled);

    void setIsPFProgTabDisabled(boolean isPFProgTabDisabled);

    void setIsRequestTabDisabled(boolean isRequestTabDisabled);

    void setIsSaveButtonDisabled(boolean isSaveButtonDisabled);

    void setIsSizingTabDisabled(boolean isSizingTabDisabled);

    void setIsUbicationTabDisabled(boolean isUbicationTabDisabled);

    void setTheirInvPreFileAction(boolean anInvPreFileAction);

    void setTheirInvPreFileBenefitDesc(String anInvPreFileBenefitDesc);

    void setTheirInvPreFileBuild(boolean anInvPreFileBuild);

    void setTheirInvPreFileComponent(Long anInvPreFileComponent);

    void setTheirInvPreFileComponentList(List<ComponentEntity> theirInvPreFileComponentList);

    void setTheirInvPreFileDesagLevel(String anInvPreFileDesagLevel);

    void setTheirInvPreFileDescription(String anInvPreFileDescription);

    void setTheirInvPreFileDraftEntity(InvPreFileEntity invPrefile);

    void setTheirInvPreFileDraftProyect(DraftFileType theirInvPreFileDraftProyect);

    void setTheirInvPreFileEspecifProy(boolean anInvPreFileEspecifProy);

    void setTheirInvPreFileFund(boolean anInvPreFileFund);

    void setTheirInvPreFileId(Long anInvPreFileId);

    void setTheirInvPreFileName(String anInvPreFileName);

    void setTheirInvPreFileNumber(String anInvPreFileNumber);

    void setTheirInvPreFilePcPy(String anInvPreFilePcPy);

    void setTheirInvPreFilePriority(String anInvPreFilePriority);

    void setTheirInvPreFileProgram(boolean anInvPreFileProgram);

    void setTheirInvPreFilePromoter(Long anInvPreFilePromoter);

    void setTheirInvPreFilePromoterList(List<PromoterEntity> theirInvPreFilePromoterList);

    void setTheirInvPreFileType(String anInvPreFileType);

    void setTheirInvPreFileUEGList(List<DependenceEntity> theirInvPreFileUEGList);

    void setTheirInvPreFileUEGSelected(List<String> anInvPreFileUEG);

    void setTheirInvPreFileURE(String anInvPreFileURE);

    void setTheirInvPreFileUREEntity(DependenceEntity anInvPreFileUREEntity);

    void setTheirInvPreFileURN(Long anInvPreFileURN);

    void setTheirInvPreFileURNList(List<FederalURRegulatoryEntity> theirInvPreFileURNList);
    
    public String getImprimirReporte();
    
    public void setImprimirReporte(String imprimirReporte);
    
    public boolean getDisableHRefReport();
    
    public void setDisableHRefReport(boolean enable);
    
    boolean getBtnObservations();

    void setBtnObservations(boolean btnObservations);
        
    boolean isIsPriorityReadOnly();

    void setIsPriorityReadOnly(boolean isPriorityReadOnly);

    String getNewPromoter();

    void setNewPromoter(String newPromoter);

    DependenceEntity getItsInvPreFileUEGFicha();

    void setItsInvPreFileUEGFicha(DependenceEntity itsInvPreFileUEGSelected);
    
    void savePromoter();
    
}
