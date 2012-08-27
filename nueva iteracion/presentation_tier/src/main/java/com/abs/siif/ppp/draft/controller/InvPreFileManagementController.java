/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  InvPreFileManagementController
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.draft.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.planning.dto.DraftProjectDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dto.DraftProjectSearchDto;
import com.abs.siif.programming.dto.DraftProjectStatusSearchDto;
import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import com.abs.siif.programming.management.WorkFlowDraftProjectManagement;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.util.*;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * Clase que se encarga de lo relativo a la vista "Gestionamiento de Fichas"
 *
 * @version 1.0 19-Junio-2012
 * @author Francisco Luna
 */
@Controller("invPreFileManagementController")
@Scope("session")
public class InvPreFileManagementController extends SIIFControllerBase implements Serializable {

    private Long dependenciaSeleccionada;
    private List<DependenceEntity> listDependencias;
    private Date fechaIinicio;
    private Date fechaFin;
    private boolean isFicha;
    private String noFicha;
    private String nombreCorto;
    private List<DraftProjectStatusEntity> listStatus;
    private String statusSeleccionado;
    private Collection<DraftProjectStatusEntity> listAcciones;
    private Long accionSelecionada;
    private Collection<DraftProjectEntity> listProcess;
    private DraftProjectEntity[] selectedProcess;
    @Resource(name = "workFlowDraftProjectManagement")
    private transient WorkFlowDraftProjectManagement workFlowDraftProjectManagement;
    private Collection<DraftProjectBinnacleEntity> listBitacora;
    private boolean all;   

    public boolean getAll() {
        return all;
    }
    
    public void setAll(boolean all) {
        this.all = all;
    }

    public Collection<DraftProjectBinnacleEntity> getListBitacora() {
        return listBitacora;
    }

    public void setListBitacora(Collection<DraftProjectBinnacleEntity> listBitacora) {
        this.listBitacora = listBitacora;
    }

    public DraftProjectEntity[] getSelectedProcess() {
        return selectedProcess;
    }

    public void setSelectedProcess(DraftProjectEntity[] selectedProcess) {
        this.selectedProcess = selectedProcess;
    }

    public Collection<DraftProjectEntity> getListProcess() {
        return listProcess;
    }

    public void setListProcess(Collection<DraftProjectEntity> listProcess) {
        this.listProcess = listProcess;
    }

    public Long getAccionSelecionada() {
        return accionSelecionada;
    }

    public void setAccionSelecionada(Long accionSelecionada) {
        this.accionSelecionada = accionSelecionada;
    }

    public Collection<DraftProjectStatusEntity> getListAcciones() {
        return listAcciones;
    }

    public void setListAcciones(Collection<DraftProjectStatusEntity> listAcciones) {
        this.listAcciones = listAcciones;
    }

    public String getStatusSeleccionado() {
        return statusSeleccionado;
    }

    public void setStatusSeleccionado(String statusSeleccionado) {
        this.statusSeleccionado = statusSeleccionado;
    }

    public List<DraftProjectStatusEntity> getListStatus() {
        return listStatus;
    }

    public void setListStatus(List<DraftProjectStatusEntity> listStatus) {
        this.listStatus = listStatus;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getNoFicha() {
        return noFicha;
    }

    public void setNoFicha(String noFicha) {
        this.noFicha = noFicha;
    }

    public boolean getIsFicha() {
        return this.isFicha;
    }

    public void setIsFicha(boolean isFicha) {
        this.isFicha = isFicha;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaIinicio() {
        return fechaIinicio;
    }

    public void setFechaIinicio(Date fechaIinicio) {
        this.fechaIinicio = fechaIinicio;
    }

    public List<DependenceEntity> getListDependencias() {
        return listDependencias;
    }

    public void setListDependencias(List<DependenceEntity> listDependencias) {
        this.listDependencias = listDependencias;
    }

    public Long getDependenciaSeleccionada() {
        return dependenciaSeleccionada;
    }

    public void setDependenciaSeleccionada(Long itsSelectedDependencia) {
        this.dependenciaSeleccionada = itsSelectedDependencia;
    }

    /**
     * Método que inicializa los elementos de la vista invPreFileManagement
     */
    public void initInvPreFileManageView() {
        listBitacora = new ArrayList<DraftProjectBinnacleEntity>();
        listAcciones = new ArrayList<DraftProjectStatusEntity>();
        listProcess = new ArrayList<DraftProjectEntity>();
        initDates();

        loadFilterData();

    }

    /**
     * Método que se encarga de realizar el cambio de estatus
     */
    public void gestionar() {
        List<DraftProjectEntity> listProjects = new ArrayList<DraftProjectEntity>();
        DraftProjectStatusEntity status = new DraftProjectStatusEntity();
        DraftProjectEntity[] currentProcess = getSelectedProcess();
        ProfileEntity profile = getRole();
        Long nextStatus = getAccionSelecionada();
        status.setDraftProjectStatusId(nextStatus);
        status = SearchList.findObjectList(getListAcciones(), status);
        if(getAccionSelecionada() != null){
            if(currentProcess != null && currentProcess.length > 0){
                try{
                    if(currentProcess.length == 1){            
                        DraftProjectEntity current = currentProcess[0];                     
                        workFlowDraftProjectManagement.executeChangeStatus(current, status);
                        getNextStatus(current, profile);
                        exectBitacora(current.getDraftProjectId());
                        search();
                        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                                this.getMessage("ppp.planning.draftPreFile.message.gestionOk"),
                                this.getMessage("ppp.planning.draftPreFile.message.gestionOk"));
                    } else {
                        if(getStatusSeleccionado() == null || 
                            getStatusSeleccionado().trim().equals("")){
                            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                                    this.getMessage("ppp.planning.draftPreFile.message.multipleSelect"),
                                    this.getMessage("ppp.planning.draftPreFile.message.multipleSelect"));
                        }else{
                            listProjects = Arrays.asList(currentProcess);                            
                            workFlowDraftProjectManagement.executeChangeStatus(listProjects, status);
                            getListAcciones().clear();
                            search();
                            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                                    this.getMessage("ppp.planning.draftPreFile.message.gestionOk"),
                                    this.getMessage("ppp.planning.draftPreFile.message.gestionOk"));
                        }                       
                    }
                } catch (BaseBusinessException ex) {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                            this.getMessage(ex.getMessage()),
                            this.getMessage(ex.getMessage()));
                } catch (Exception e) {
                    //TODO: loggear la excepción
                }
            } else {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.planning.draftPreFile.message.processNull"),
                        this.getMessage("ppp.planning.draftPreFile.message.processNull"));
            }
        } else {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.planning.draftPreFile.message.selectAction"),
                    this.getMessage("ppp.planning.draftPreFile.message.selectAction"));
        }
    }

    public void cerrar() {
    }

    /**
     * Método que realiza la busuqeda de las fichas de acuerdo a la dependencia
     * seleccionada
     */
    public void search() {
        DraftProjectSearchDto draftProDto = new DraftProjectSearchDto();
        if(getAll()){
            draftProDto.setDependencyId(0L);
            draftProDto.setIdAnteProyecto("");
            draftProDto.setIdPreFicha("");
            draftProDto.setNombreCorto("");
            draftProDto.setStatus(0L);
            setListProcess(workFlowDraftProjectManagement.getDraftProjectsByFilter(draftProDto));
                        getListBitacora().clear();
                        getListAcciones().clear();
        }else{
            boolean isvalid = validateInputs();
            if (isvalid) {                        
                        draftProDto.setDependencyId(getDependenciaSeleccionada());
                        java.sql.Date sqlDateFin = new java.sql.Date(getFechaFin().getTime());
                        java.sql.Date sqlDateIni = new java.sql.Date(getFechaIinicio().getTime());
                        draftProDto.setFechaFin(sqlDateFin);
                        draftProDto.setFechaInicio(sqlDateIni);
                        if(getIsFicha()){
                            draftProDto.setIdAnteProyecto(getNoFicha());
                            draftProDto.setIdPreFicha("");
                        }else{
                            draftProDto.setIdPreFicha(getNoFicha());
                            draftProDto.setIdAnteProyecto("");
                        }
                        draftProDto.setNombreCorto(getNombreCorto());
                        draftProDto.setStatus(Long.parseLong(getStatusSeleccionado()));

                        setListProcess(workFlowDraftProjectManagement.getDraftProjectsByFilter(draftProDto));
                        getListBitacora().clear();
                        getListAcciones().clear();
            }           
        }        
    }

    /**
     * Método que valida que la fecha de inicio sea menos que la fecha final, de
     * los filtros de búsqueda
     *
     * @return isValidDate //true si es valida
     */
    public boolean validateInputs() {
        boolean isValidDate = true;
        
        //TODO: Eliminar los else y descomentar el contenido del If, además de agregar la lógica de
        //validaciones una vez que definan el user story
        
        if(getDependenciaSeleccionada() == null){
            //isValidDate = false;
        }
        
        if(getNoFicha() == null || getNoFicha().trim().equals("")){
            //isValidDate = false;
            setNoFicha("");
        }
        
        if(getNombreCorto() == null || getNombreCorto().trim().equals("")){
            //isValidDate = false;
            setNombreCorto("");
        }
        
        if(getStatusSeleccionado() == null || 
                getStatusSeleccionado().trim().equals("")){
            //isValidDate = false;
            setStatusSeleccionado("0");
        }
        
        Date initDate = getFechaIinicio();
        Date endDate = getFechaFin();
        if (initDate != null && endDate != null) {
            int comp = initDate.compareTo(endDate);
            if (comp > 0) {
                isValidDate = false;
            }
        }
        return isValidDate;
    }

    /**
     * Método que se encarga de hacer la carga de los drop-dwon de dependencias
     * y estatus de los filtros de búsqueda
     */
    public void loadFilterData() {
        HashMap<String, List<?>> supportList =
                workFlowDraftProjectManagement.getSupportList();
        try {
            setListDependencias((List<DependenceEntity>) supportList.get("dependencies"));
            setListStatus((List<DraftProjectStatusEntity>) supportList.get("status"));
            this.setDependenciaSeleccionada(0L);
        } catch (Exception e) {
            //TODO: Manajar la excepción
        }
    }

    /**
     * Método que inicializa las fechas de los filtros de búsqueda
     */
    public void initDates() {
        Calendar calen = Calendar.getInstance();
        setFechaIinicio(calen.getTime());
        calen.add(Calendar.MONTH, 1);
        setFechaFin(calen.getTime());
    }

    /**
     * Método que maneja el evento de selección de columna de la tabla, realiza
     * la carga de combo con los posibles siguientes estatus que pueda tener la
     * ficha seleccionada
     *
     * @param event
     */
    public void onRowSelect(SelectEvent event) {
        DraftProjectEntity current = (DraftProjectEntity)event.getObject();        
        ProfileEntity profile = getRole();
        getNextStatus(current, profile);
        if(getSelectedProcess().length < 2){            
            exectBitacora(current.getDraftProjectId());
        }else{
            getListBitacora().clear();
        }
    }

    public void exectBitacora(Long id) {
        setListBitacora(workFlowDraftProjectManagement.getBinnaclebyDraftProjectId(id));
    }

    public void onRowUnselect(UnselectEvent event) {
        getListBitacora().clear();
        getListAcciones().clear();
    }

    public void getNextStatus(DraftProjectEntity current, ProfileEntity profile) {
        DraftProjectStatusSearchDto dtoToSearch = new DraftProjectStatusSearchDto();
        dtoToSearch.setDraftProject(current);
        dtoToSearch.setProfile(profile);
        setListAcciones(workFlowDraftProjectManagement.getPossibleStatus(dtoToSearch));
    }    
    
}
