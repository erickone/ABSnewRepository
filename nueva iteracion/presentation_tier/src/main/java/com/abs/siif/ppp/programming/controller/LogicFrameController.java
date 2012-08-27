/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  LogicFrameController
 *  Purpose:  [ Esta Clase se encargara de la interacción entre los datos
 *  primitivos de pantalla y el management que se encargara de las operaciones
 *  que esta tendra en la base de datos, asi mismo se encargara del manejo de
 *  la subida de los archivos para complementar la informacion del Anteproyecto]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.ppp.programming.api.controller.LogicFrameControllerApi;
import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.common.entities.DocumentTypeEntity;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.dto.LogicFrameDto;
import com.abs.siif.ppp.programming.uihelpers.LogicFrameDtoDataModel;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.LogicFrameEntity;
import com.abs.siif.programming.management.DraftProjectManagement;
import com.abs.siif.programming.management.LogicFrameManagement;
import com.abs.siif.security.entities.UserEntity;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Erick Leija
 */
@Controller("logicFrameController")
@Scope("session")
public class LogicFrameController
        extends SIIFControllerBase
        implements Serializable, LogicFrameControllerApi {
    //A continución se agregara una inyección para de un management para cargar
    //el DraftProjectEntity

    @Resource(name = "draftProjectManagement")
    private transient DraftProjectManagement theirDraftProjectManagement;
    //Acontinuación se presentan las inyecciónes de el management que se 
    //encargara de cargar los datos del init
    @Resource(name = "logicFrameManagement")
    private LogicFrameManagement theirLogicFrameManagement;
    //Esta es la inyección para obtener el Id del antreproyecto
    @Resource(name = "draftProjectHeaderController")
    private DraftProjectHeaderControllerApi theirDraftProjectHeaderController;
    //Esta Variable controla el id del anteproyecto tiene su respectivo
    //setter and getter
    private Long itsDraftProjectId;
    //estas son las variables que se encargaran de guardar los datos detalle
    //del archivo mostrado en pantalla
    private Long itsLogicFrameId;
    private String itsTreekey;
    private String itsDocumentName;
    private Long itsDocumentType;
    private String itsDocumentPath;
    private String itsDocumentDetails;
    //Esta variable es la que se encarga de guardar el un archivo reemplazado
    private String itsDocumentToReplace;
    //Estas es la lista de archivos que se utlizara para mostrar los datos
    //en los grid
    private List<LogicFrameEntity> itsListOfFrameLogicFilesById;
    //Esta es la lista donde se vaciaran la información que sera vista en 
    //pantalla
    private List<LogicFrameDto> itsListOfLogicFrameDto;
    //Esta es la lista de los tipos de documentos Existentes
    private List<DocumentTypeEntity> itsListOfDocumentTypes;
    //Esta es el Data model de la Data Table
    private LogicFrameDtoDataModel itsLogicFrameDataModel;
    //Esta es la variable para el reglon Seleccionado
    private LogicFrameDto itsSelectedRow;
    //Esta es la variable que controla la habilitación del boton Descargar
    private boolean itsDownloaDisabled;
    private boolean btnSrvSave = false;

    //Este es el constructor del Controller
    public LogicFrameController() {
        this.itsListOfFrameLogicFilesById = new ArrayList<LogicFrameEntity>();
        this.itsListOfLogicFrameDto = new ArrayList<LogicFrameDto>();
        this.itsTreekey = "";

    }

    //este es el metodo inicializador de la pagina logicframe.xhtml
    @Override
    public void init() {
        btnSrvSave = false;
        this.itsDraftProjectId = theirDraftProjectHeaderController.getTheirCurrentDraftProjectId();
        this.itsListOfLogicFrameDto = new ArrayList<LogicFrameDto>();
        cleanFields();
        this.itsListOfDocumentTypes = new ArrayList<DocumentTypeEntity>(theirLogicFrameManagement.getAllDocumentTypes());
        for (DocumentTypeEntity myDocumen : itsListOfDocumentTypes) {
            if (myDocumen.getDocumentTypeDescription().equals(this.getMessage("logicFrame.TypeDocument.Search"))) {
                this.setItsDocumentType(myDocumen.getDocumentTypeId());
                break;
            }
        }
        this.itsListOfFrameLogicFilesById = new ArrayList<LogicFrameEntity>(theirLogicFrameManagement.getLogicFrameByDraftProjectID(itsDraftProjectId));
        loadDtoList();
    }

    //Con este metodo se limpian los campos que se utlizan para manejar las entities
    @Override
    public void cleanFields() {
        itsDownloaDisabled = true;
        this.itsLogicFrameId = new Long(0);
        this.itsTreekey = "";
        this.itsDocumentName = "";
        this.itsDocumentType = new Long(0);
        this.itsDocumentPath = "";
        this.itsDocumentDetails = "";
    }
    //Este metodo se usa para cargar la Lista de Dto's que serán mostrados en 
    //pantalla

    @Override
    public void loadDtoList() {
        itsListOfLogicFrameDto.clear();
        for (LogicFrameEntity myEntity : itsListOfFrameLogicFilesById) {
            String myTreeName = "";
            if (myEntity.getLogicFrameTreeType() == 0) {
                myTreeName = "Árbol de Problemas";
            } else {
                if (myEntity.getLogicFrameTreeType() == 1) {
                    myTreeName = "Árbol de Objetivos";
                }
            }

            LogicFrameDto myDto = new LogicFrameDto(myEntity.getLogicFrameId(),
                    String.valueOf(myEntity.getLogicFrameTreeType()),
                    myEntity.getLogicFramedocumentName(),
                    myEntity.getLogicFrameDocumentDetail(),
                    myEntity.getLogicFrameDocumentPath(),
                    null,
                    myEntity.getLogicFrameUserId().getUserId(),
                    myTreeName,
                    null,
                    myEntity.getLogicFrameUserId().getUserName().toString());
            itsListOfLogicFrameDto.add(myDto);
        }
        itsLogicFrameDataModel = new LogicFrameDtoDataModel(itsListOfLogicFrameDto);
    }
    //Este es el metodo que se encarga de guardar el archivo en la base de datos

    @Override
    public void processFileUpload(FileUploadEvent event) throws IOException {

        if (!(this.itsDocumentPath == null || this.itsDocumentPath.isEmpty())) {
            itsDocumentToReplace = itsDocumentPath;
        } else {
            itsDocumentToReplace = "";
        }
        try {

            UploadedFile arq = event.getFile();

            InputStream in = new BufferedInputStream(arq.getInputstream());

            File file = new File(SIIFContextBase.getParamContext(KeyContextEnum.PATH) + arq.getFileName());

            this.itsDocumentPath = file.getAbsolutePath().toString();
            this.itsDocumentDetails = file.getName();
            FileOutputStream fout = new FileOutputStream(file);

            while (in.available() != 0) {

                fout.write(in.read());

            }

            fout.close();


            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    getMessage("ppp.planning.successAdd", file.getName()),
                    getMessage("ppp.planning.successAdd", file.getName()));

        } catch (Exception ex) {
        }
    }

    //Este es el metodo para descargar el archivo desde el servidor
    @Override
    public StreamedContent getFileStreamedContent() throws Exception {

        StreamedContent file = null;

        try {
            InputStream is = new BufferedInputStream(
                    new FileInputStream(this.itsDocumentPath));
            String type = getType("file:" + this.itsDocumentPath);
            file = new DefaultStreamedContent(is, type, itsDocumentDetails);

        } catch (Exception exc) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));

        }

        return file;
    }

    public static String getType(String fileUrl) throws Exception {
        URL u = new URL(fileUrl);
        URLConnection uc = u.openConnection();
        String type = uc.getContentType();

        return type;
    }

    @Override
    public void saveDocument() {
        if (validationsAfterSave()) {
            try {
                LogicFrameEntity myDocumentEntity = new LogicFrameEntity();
                if (!(itsLogicFrameId == null)) {
                    myDocumentEntity.setLogicFrameId(itsLogicFrameId);
                }
                myDocumentEntity.setLogicFrameDocumentPath(itsDocumentPath);
                if (itsTreekey.equals("") || itsTreekey == null) {
                    itsTreekey = "0";
                }
                myDocumentEntity.setLogicFrameTreeType(Integer.parseInt(itsTreekey));
                myDocumentEntity.setLogicFramedocumentName(itsDocumentName);
                Calendar calendar = new GregorianCalendar();
                String Fecha = Integer.toString(calendar.get(Calendar.DATE)) + "/"
                        + Integer.toString(calendar.get(Calendar.MONTH)) + "/"
                        + Integer.toString(calendar.get(Calendar.YEAR)) + " ";
                myDocumentEntity.setLogicFrameDocumentDetail(Fecha + itsDocumentDetails);
                DraftProjectEntity myProject = theirDraftProjectManagement.getDraftProjectById(itsDraftProjectId);
                myDocumentEntity.setlogicFrameDraftProject(myProject);
                UserEntity myUser = getUser();
                myDocumentEntity.setLogicFrameUserId(myUser);
                itsLogicFrameId = theirLogicFrameManagement.saveLogicFrameFileData(myDocumentEntity);
                init();
                addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                        this.getMessage("ppp.progr.LogicFrame.successSave"),
                        this.getMessage("ppp.progr.LogicFrame.successSave"));
                if (!(itsDocumentToReplace == null || itsDocumentToReplace.isEmpty())) {

                    File fichero = new File(itsDocumentToReplace);
                    if (!fichero.delete()) {
                        addMessageCurrentInstance(FacesMessage.SEVERITY_FATAL,
                                this.getMessage("ppp.planning.fileDeleteError"),
                                this.getMessage("ppp.planning.fileDeleteError"));
                    }
                    itsDocumentToReplace = "";
                }
            } catch (Exception exc) {
                String myError;
                if (itsDraftProjectId == null) {
                    myError = this.getMessage("ppp.progr.LogicFrame.DraftProyectNotSaved");
                } else {
                    myError = this.getMessage(exc.getMessage());
                }

                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        myError, myError);
            }
        }

    }

    //Este metodo sirve para borrar el registro Seleccionado
    @Override
    public void deleteDocument(ActionEvent actionEvent) {
        try {
            LogicFrameEntity myEntityToDelete = new LogicFrameEntity();
            myEntityToDelete.setLogicFrameId(itsSelectedRow.getItsLogicFrameId());
            theirLogicFrameManagement.deleteLogicFrameFileData(myEntityToDelete);
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    this.getMessage("ppp.progr.LogicFrame.successDelete"),
                    this.getMessage("ppp.progr.LogicFrame.successDelete"));
            File fichero = new File(itsSelectedRow.getItsDocumentPath());
            if (!fichero.delete()) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_FATAL,
                        this.getMessage("ppp.progr.LogicFrame.errorDeleteFile"),
                        this.getMessage("ppp.progr.LogicFrame.errorDeleteFile"));
            }
            init();
        } catch (Exception exc) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }

    }

    @Override
    public boolean validationsAfterSave() {
        boolean result = true;
        if (itsDocumentName == null || itsDocumentName.equals("")) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.LogicFrame.notName"),
                    this.getMessage("ppp.progr.LogicFrame.notName"));
            result = false;
        } else {
            if (itsDocumentPath == null || itsDocumentPath.equals("")) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.LogicFrame.notFileToLoad"),
                        this.getMessage("ppp.progr.LogicFrame.notFileToLoad"));
                result = false;
            } else {
                if (itsTreekey == null || itsTreekey.isEmpty()) {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                            this.getMessage("ppp.progr.LogicFrame.notTree"),
                            this.getMessage("ppp.progr.LogicFrame.notTree"));
                    result = false;
                }
            }
        }

        return result;
    }

    //Este metodo sirve para cargar los datos del registro seleccionado en pantalla
    @Override
    public void onRowSelect(SelectEvent event) {
        this.itsDownloaDisabled = false;
        this.itsTreekey = itsSelectedRow.getItsTreeType();
        this.itsDocumentName = itsSelectedRow.getItsDocumentName();
        this.itsDocumentType = itsSelectedRow.getItsTypeDocumentId();
        this.itsDocumentPath = itsSelectedRow.getItsDocumentPath();
        this.itsDocumentDetails = itsSelectedRow.getItsDocumentDetail();
        this.itsLogicFrameId = itsSelectedRow.getItsLogicFrameId();

    }
    //Aqui inician los Getters de las variables del controller

    @Override
    public String getItsDocumentName() {
        return itsDocumentName;
    }

    @Override
    public Long getItsDocumentType() {
        return itsDocumentType;
    }

    @Override
    public List<LogicFrameEntity> getItsListOfFrameLogicFilesById() {
        return itsListOfFrameLogicFilesById;
    }

    @Override
    public String getItsTreekey() {
        return itsTreekey;
    }

    @Override
    public Long getItsDraftProjectId() {
        return itsDraftProjectId;
    }

    @Override
    public List<LogicFrameDto> getItsListOfLogicFrameDto() {
        return itsListOfLogicFrameDto;
    }

    @Override
    public List<DocumentTypeEntity> getItsListOfDocumentTypes() {
        return itsListOfDocumentTypes;
    }

    @Override
    public LogicFrameManagement getTheirLogicFrameManagement() {
        return theirLogicFrameManagement;
    }

    @Override
    public String getItsDocumentPath() {
        return itsDocumentPath;
    }

    @Override
    public DraftProjectHeaderControllerApi getTheirDraftProjectHeaderController() {
        return theirDraftProjectHeaderController;
    }

    @Override
    public LogicFrameDtoDataModel getItsLogicFrameDataModel() {
        return itsLogicFrameDataModel;
    }

    @Override
    public LogicFrameDto getItsSelectedRow() {
        return itsSelectedRow;
    }

    @Override
    public Long getItsLogicFrameId() {
        return itsLogicFrameId;
    }

    @Override
    public String getItsDocumentDetails() {
        return itsDocumentDetails;
    }

    @Override
    public String getItsDocumentToReplace() {
        return itsDocumentToReplace;
    }

    @Override
    public boolean isItsDownloadEnabled() {
        return itsDownloaDisabled;
    }

    //Aqui inician los Setters de las variables del controller
    @Override
    public void setItsDocumentName(String aDocumentName) {
        this.itsDocumentName = aDocumentName;
    }

    @Override
    public void setItsDocumentType(Long aDocumentType) {
        this.itsDocumentType = aDocumentType;
    }

    @Override
    public void setItsTreekey(String aTreekey) {
        this.itsTreekey = aTreekey;
    }

    @Override
    public void setItsDraftProjectId(Long aDraftProjectId) {
        this.itsDraftProjectId = aDraftProjectId;
    }

    @Override
    public void setItsListOfLogicFrameDto(List<LogicFrameDto> aListOfLogicFrameDto) {
        this.itsListOfLogicFrameDto = aListOfLogicFrameDto;
    }

    @Override
    public void setItsListOfFrameLogicFilesById(List<LogicFrameEntity> aListOfFrameLogicFilesById) {
        this.itsListOfFrameLogicFilesById = aListOfFrameLogicFilesById;
    }

    @Override
    public void setItsListOfDocumentTypes(List<DocumentTypeEntity> itsListOfDocumentTypes) {
        this.itsListOfDocumentTypes = itsListOfDocumentTypes;
    }

    @Override
    public void setTheirLogicFrameManagement(LogicFrameManagement theirLogicFrameManagement) {
        this.theirLogicFrameManagement = theirLogicFrameManagement;
    }

    @Override
    public void setItsDocumentPath(String itsDocumentPath) {
        this.itsDocumentPath = itsDocumentPath;
    }

    @Override
    public void setTheirDraftProjectHeaderController(DraftProjectHeaderControllerApi theirDraftProjectHeaderController) {
        this.theirDraftProjectHeaderController = theirDraftProjectHeaderController;
    }

    @Override
    public void setItsLogicFrameDataModel(LogicFrameDtoDataModel itsLogicFrameDataModel) {
        this.itsLogicFrameDataModel = itsLogicFrameDataModel;
    }

    @Override
    public void setItsSelectedRow(LogicFrameDto itsSelectedRow) {
        this.itsSelectedRow = itsSelectedRow;
    }

    @Override
    public void setItsLogicFrameId(Long itsLogicFrameId) {
        this.itsLogicFrameId = itsLogicFrameId;
    }

    @Override
    public void setItsDocumentDetails(String itsDocumentDetails) {
        this.itsDocumentDetails = itsDocumentDetails;
    }

    @Override
    public void setItsDocumentToReplace(String itsDocumentToReplace) {
        this.itsDocumentToReplace = itsDocumentToReplace;
    }

    @Override
    public void setItsDownloadEnabled(boolean aDownloaDisabled) {
        this.itsDownloaDisabled = aDownloaDisabled;
    }

    @Override
    public boolean getBtnSrvSave() {
        return btnSrvSave;
    }

    @Override
    public void setBtnSrvSave(boolean itsBtnSrvSave) {
        this.btnSrvSave = itsBtnSrvSave;
    }
}
