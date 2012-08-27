/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  InvPrefileSearchControllerApi
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

import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.management.DependenceManagement;
import com.abs.siif.ppp.programming.controller.InvPreFileController;
import com.abs.siif.ppp.programming.uihelpers.InvPreFileSearchUIHelper;
import com.abs.siif.programming.dto.InvPreFileDto;
import com.abs.siif.programming.management.InvPreFileManagement;
import com.abs.siif.security.entities.ProfileEntity;
import java.util.List;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author luis.carreon
 */
public interface InvPrefileSearchControllerApi {

    /**
     * Este método controla cuando se habilita/deshabilita un combo de filtro
     *
     */
    void changeComboStatus();

    void cleanSearch();

    /**
     * Las listas de UR y UEG se obtienen como una lista de DependenceEntity, pero a nivel
     * de este controller solo acepta DependenceDTO. Este método toma la lista de
     * DependenceEntity de tipo UEG y la convierte en una lista de DependenceDTO para ser
     * acrgada la primera vez
     */
    void convertListOfUEGNotHierarchyToDependenceDto();

    /**
     * Este método toma la lista de DependenceEntity de tipo UR y la convierte en una lista
     * de DependenceDTO para ser acrgada la primera vez
     *
     * @param listOfURNotHierarchy
     * @return
     */
    List<DepencenceDto> convertListOfURNotHierarchyToDependenceDto(List<DependenceEntity> listOfURNotHierarchy);

    void deleteInvPreFile();

    void filePriority();

    boolean getBtnSrvSave();

    /**
     * @return the buildingName
     */
    String getBuildingName();

    /**
     * @return the theirCurrentOperationYear
     */
    String getCurrentOperationYear();

    /**
     * @return the dependenceId
     */
    Long getDependenceId();

    /**
     * @return the description
     */
    String getDescription();

    /**
     * @return the destNumber
     */
    String getDestNumber();

    /**
     * @return the theirInvPreFileController
     */
    InvPreFileControllerApi getInvPreFileController();

    /**
     * @return the itsinvPreFileManagement
     */
    InvPreFileManagement getInvPreFileManagement();

    /**
     * @return the InvPrefileList
     */
    List<InvPreFileDto> getInvPrefileList();

    /**
     * @return the itsInvPrefileSearchUIHelper
     */
    InvPreFileSearchUIHelper getInvPrefileSearchUIHelper();

    /**
     * @return the itsDependenceManager
     */
    DependenceManagement getItsDependenceManager();

    /**
     * @return the itsRoleSelected
     */
    ProfileEntity getItsRoleSelected();

    List<DependenceEntity> getListOfBudgetDependences();

    /**
     * Regresa la lsita de dependencias, es la misma lista usada en Programación
     *
     * @return the itsListOfDependences
     */
    List<DependenceEntity> getListOfDependences();

    /**
     * @return the listOfExecutorDependences
     */
    List<DepencenceDto> getListOfExecutorDependences();

    /**
     * @return the listOfResponsibleDependences
     */
    List<DepencenceDto> getListOfResponsibleDependences();

    /**
     * @return the listOfURNotHierarchy
     */
    List<DependenceEntity> getListOfURNotHierarchy();

    /**
     * @return the number
     */
    String getNumber();

    /**
     * @return the partyNumber
     */
    String getPartyNumber();

    /**
     * @return the theirSelectedInvPreFileDTO
     */
    InvPreFileDto getSelectedInvPreFileDTO();

    /**
     * @return the theirYearOfSearch
     */
    String getTheirYearOfSearch();

    /**
     * @return the uniEjecutoraId
     */
    Long getUniEjecutoraId();

    /**
     * @return the uniPresupuestalId
     */
    Long getUniPresupuestalId();

    /**
     * @return the uniResponsableId
     */
    Long getUniResponsableId();

    /**
     * @return the year
     */
    String getYear();

    void init();

    /**
     * @return the isCboResponsible
     */
    boolean isIsCboResponsible();

    /**
     * @return the isCboUEGDisabled
     */
    boolean isIsCboUEGDisabled();

    /**
     * @return the isCboUPDisabled
     */
    boolean isIsCboUPDisabled();

    /**
     * Este método, una vez pasadas todas las validaciones, carga las prefichas encontradas
     * dentro de la lista de Prefichas invPrefileList usada por el dataTable parta mostrar
     * los resultados de la búsqueda.
     */
    void loadInvPreFiles();

    String navigatePreviousPreFicha();

    String navigateToInvPrefile();

    String navigateToMainMenu();

    /**
     * Este método se encarga de llamar los métodos para cargar las listas de UEG y UR por
     * primera vez
     */
    void prepareInitialLists();

    void reloadList();

    void searchInvPrefiles();

    void setBtnSrvSave(boolean BtnSrvSave);

    /**
     * @param buildingName the buildingName to set
     */
    void setBuildingName(String buildingName);

    /**
     * @param theirCurrentOperationYear the theirCurrentOperationYear to set
     */
    void setCurrentOperationYear(String theirCurrentOperationYear);

    /**
     * @param dependenceId the dependenceId to set
     */
    void setDependenceId(Long dependenceId);

    /**
     * @param description the description to set
     */
    void setDescription(String description);

    /**
     * @param destNumber the destNumber to set
     */
    void setDestNumber(String destNumber);

    /**
     * @param theirInvPreFileController the theirInvPreFileController to set
     */
    void setInvPreFileController(InvPreFileController theirInvPreFileController);

    /**
     * @param itsinvPreFileManagement the itsinvPreFileManagement to set
     */
    void setInvPreFileManagement(InvPreFileManagement anInvPreFileManagement);

    /**
     * @param InvPrefileList the InvPrefileList to set
     */
    void setInvPrefileList(List<InvPreFileDto> InvPrefileList);

    /**
     * @param itsInvPrefileSearchUIHelper the itsInvPrefileSearchUIHelper to set
     */
    void setInvPrefileSearchUIHelper(InvPreFileSearchUIHelper itsInvPrefileSearchUIHelper);

    /**
     * @param isCboResponsible the isCboResponsible to set
     */
    void setIsCboResponsible(boolean isCboResponsible);

    /**
     * @param isCboUEGDisabled the isCboUEGDisabled to set
     */
    void setIsCboUEGDisabled(boolean isCboUEGDisabled);

    /**
     * @param isCboUPDisabled the isCboUPDisabled to set
     */
    void setIsCboUPDisabled(boolean isCboUPDisabled);

    /**
     * @param itsDependenceManager the itsDependenceManager to set
     */
    void setItsDependenceManager(DependenceManagement itsDependenceManager);

    /**
     * @param itsRoleSelected the itsRoleSelected to set
     */
    void setItsRoleSelected(ProfileEntity itsRoleSelected);

    /**
     * @param listOfBudgetDependences the listOfBudgetDependences to set
     */
    void setListOfBudgetDependences(List<DependenceEntity> listOfBudgetDependences);

    /**
     * @param listOfDependences the listOfDependences to set
     */
    void setListOfDependences(List<DependenceEntity> listOfDependences);

    /**
     * @param listOfExecutorDependences the listOfExecutorDependences to set
     */
    void setListOfExecutorDependences(List<DepencenceDto> listOfExecutorDependences);

    /**
     * @param listOfResponsibleDependences the listOfResponsibleDependences to set
     */
    void setListOfResponsibleDependences(List<DepencenceDto> listOfResponsibleDependences);

    /**
     * @param listOfURNotHierarchy the listOfURNotHierarchy to set
     */
    void setListOfURNotHierarchy(List<DependenceEntity> listOfURNotHierarchy);

    /**
     * @param number the number to set
     */
    void setNumber(String number);

    /**
     * @param partyNumber the partyNumber to set
     */
    void setPartyNumber(String partyNumber);

    /**
     * @param theirSelectedInvPreFileDTO the theirSelectedInvPreFileDTO to set
     */
    void setSelectedInvPreFileDTO(InvPreFileDto theirSelectedInvPreFileDTO);

    /**
     * @param theirYearOfSearch the theirYearOfSearch to set
     */
    void setTheirYearOfSearch(String theirYearOfSearch);

    /**
     * @param uniEjecutoraId the uniEjecutoraId to set
     */
    void setUniEjecutoraId(Long uniEjecutoraId);

    /**
     * @param uniPresupuestalId the uniPresupuestalId to set
     */
    void setUniPresupuestalId(Long uniPresupuestalId);

    /**
     * @param uniResponsableId the uniResponsableId to set
     */
    void setUniResponsableId(Long uniResponsableId);

    /**
     * @param year the year to set
     */
    void setYear(String year);
    
    boolean isDisabledPriorizar();
    
    void setDisabledPriorizar(boolean disabledPriorizar);
    
    void savePriority();
    
    void onRowSelect(SelectEvent event);
    
}
