/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  LogicFrameControllerApi
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

import com.abs.siif.common.entities.DocumentTypeEntity;
import com.abs.siif.ppp.programming.dto.LogicFrameDto;
import com.abs.siif.ppp.programming.uihelpers.LogicFrameDtoDataModel;
import com.abs.siif.programming.entities.LogicFrameEntity;
import com.abs.siif.programming.management.LogicFrameManagement;
import java.io.IOException;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author luis.carreon
 */
public interface LogicFrameControllerApi {

    void cleanFields();

    void deleteDocument(ActionEvent actionEvent);

    StreamedContent getFileStreamedContent() throws Exception;

    String getItsDocumentDetails();

    String getItsDocumentName();

    String getItsDocumentPath();

    String getItsDocumentToReplace();

    Long getItsDocumentType();

    Long getItsDraftProjectId();

    List<DocumentTypeEntity> getItsListOfDocumentTypes();

    List<LogicFrameEntity> getItsListOfFrameLogicFilesById();

    List<LogicFrameDto> getItsListOfLogicFrameDto();

    LogicFrameDtoDataModel getItsLogicFrameDataModel();

    Long getItsLogicFrameId();

    LogicFrameDto getItsSelectedRow();

    String getItsTreekey();

    DraftProjectHeaderControllerApi getTheirDraftProjectHeaderController();

    LogicFrameManagement getTheirLogicFrameManagement();

    void init();

    boolean getBtnSrvSave();

    boolean isItsDownloadEnabled();

    void loadDtoList();

    void onRowSelect(SelectEvent event);

    void processFileUpload(FileUploadEvent event) throws IOException;

    void saveDocument();

    void setBtnSrvSave(boolean itsBtnSrvSave);

    void setItsDocumentDetails(String itsDocumentDetails);

    void setItsDocumentName(String aDocumentName);

    void setItsDocumentPath(String itsDocumentPath);

    void setItsDocumentToReplace(String itsDocumentToReplace);

    void setItsDocumentType(Long aDocumentType);

    void setItsDownloadEnabled(boolean aDownloaDisabled);

    void setItsDraftProjectId(Long aDraftProjectId);

    void setItsListOfDocumentTypes(List<DocumentTypeEntity> itsListOfDocumentTypes);

    void setItsListOfFrameLogicFilesById(List<LogicFrameEntity> aListOfFrameLogicFilesById);

    void setItsListOfLogicFrameDto(List<LogicFrameDto> aListOfLogicFrameDto);

    void setItsLogicFrameDataModel(LogicFrameDtoDataModel itsLogicFrameDataModel);

    void setItsLogicFrameId(Long itsLogicFrameId);

    void setItsSelectedRow(LogicFrameDto itsSelectedRow);

    void setItsTreekey(String aTreekey);

    void setTheirDraftProjectHeaderController(DraftProjectHeaderControllerApi theirDraftProjectHeaderController);

    void setTheirLogicFrameManagement(LogicFrameManagement theirLogicFrameManagement);

    boolean validationsAfterSave();
    
}
