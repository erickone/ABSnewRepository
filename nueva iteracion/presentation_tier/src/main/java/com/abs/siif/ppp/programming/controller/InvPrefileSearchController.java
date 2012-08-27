/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  InvPrefileSearchController
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.management.DependenceManagement;
import com.abs.siif.ppp.programming.api.controller.InvPreFileControllerApi;
import com.abs.siif.ppp.programming.api.controller.InvPrefileSearchControllerApi;
import com.abs.siif.ppp.programming.uihelpers.InvPreFileSearchUIHelper;
import com.abs.siif.programming.dto.InvPreFileDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.management.DependenceProgrammingManagement;
import com.abs.siif.programming.management.InvPreFileManagement;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.support.UtilsDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Israel Ruiz
 */
@Scope("session")
@Controller("invPrefileSearchController")
public class InvPrefileSearchController extends SIIFControllerBase
        implements Serializable, InvPrefileSearchControllerApi
{

    @Resource(name = "dependenceProgrammingManagement")
    private transient DependenceProgrammingManagement theirDependenceManagement;
    @Resource(name = "invPreFileManagement")
    private transient InvPreFileManagement itsinvPreFileManagement;
    @Resource(name = "invPreFileController")
    private transient InvPreFileControllerApi theirInvPreFileController;
    @Resource(name = "dependenceManagement")
    private transient DependenceManagement itsDependenceManagement;
    private String year;
    private Long uniPresupuestalId;
    private Long uniResponsableId;
    private Long uniEjecutoraId;
    private Long dependenceId;
    private List<DependenceEntity> listOfDependences;
    private List<DependenceEntity> listOfBudgetDependences;
    private List<DepencenceDto> listOfResponsibleDependences;
    private List<DepencenceDto> listOfExecutorDependences;
    private List<InvPreFileDto> invPrefileList;
    private List<DependenceEntity> listOfURNotHierarchy;
    private String number;
    private String buildingName;
    private String description;
    private String partyNumber;
    private String destNumber;
    private String theirCurrentOperationYear;
    private InvPreFileSearchUIHelper itsInvPrefileSearchUIHelper;
    private ProfileEntity itsRoleSelected;
    private InvPreFileDto theirSelectedInvPreFileDTO;
    private boolean isCboResponsibleDisabled;
    private boolean isCboUEGDisabled;
    private boolean isCboUPDisabled;
    private boolean BtnSrvSave;
    private boolean disabledPriorizar;
    private String theirYearOfSearch;

    public InvPrefileSearchController()
    {
    }

    @Override
    public void init()
    {
        this.setCurrentOperationYear(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString());
        this.setYear(this.getCurrentOperationYear().toString());
        this.setListOfBudgetDependences(new ArrayList<DependenceEntity>());
        this.setListOfResponsibleDependences(new ArrayList<DepencenceDto>());
        this.setListOfExecutorDependences(new ArrayList<DepencenceDto>());
        this.setUniPresupuestalId(Long.valueOf(0));
        this.setUniResponsableId(Long.valueOf(0));
        this.setUniEjecutoraId(Long.valueOf(0));

        this.setNumber("");
        this.setBuildingName("");
        this.setDescription("");
        this.setPartyNumber("");
        this.setDestNumber("");
        this.setInvPrefileList(null);
        prepareInitialLists();
        this.isCboResponsibleDisabled = Boolean.FALSE;
        this.isCboUEGDisabled = Boolean.FALSE;
        this.isCboUPDisabled = Boolean.FALSE;
        this.disabledPriorizar = Boolean.TRUE;
    }

    @Override
    public void reloadList()
    {
        SIIFContextBase.setParamLocalThread(KeyContextEnum.YEAR_SEARCH, this.getYear());
        prepareInitialLists();
    }

    /**
     * Regresa la lsita de dependencias, es la misma lista usada en Programación
     *
     * @return the itsListOfDependences
     */
    @Override
    public List<DependenceEntity> getListOfDependences()
    {
        this.listOfDependences = theirDependenceManagement.getDependencesByUEG();
        return this.listOfDependences;
    }

    @Override
    public List<DependenceEntity> getListOfBudgetDependences()
    {
        this.setListOfBudgetDependences(this.itsDependenceManagement.getViewDepIsBudgetUnit());
        return this.listOfBudgetDependences;
    }

    /**
     * @return the listOfResponsibleDependences
     */
    @Override
    public List<DepencenceDto> getListOfResponsibleDependences()
    {
        if (this.getUniPresupuestalId() > 0)
        {

            this.setListOfResponsibleDependences(
                    this.itsDependenceManagement.getDependciesIsRespUnitByDependIdRelated(
                    this.getUniPresupuestalId()));
        }
        return listOfResponsibleDependences;
    }

    /**
     * @return the listOfExecutorDependences
     */
    @Override
    public List<DepencenceDto> getListOfExecutorDependences()
    {
        if (this.getUniResponsableId() > 0)
        {

            this.setListOfExecutorDependences(this.itsDependenceManagement.getDependciesIsExecUnitByDependIdRelated(this.getUniResponsableId()));

        }
        return listOfExecutorDependences;
    }

    @Override
    public void searchInvPrefiles()
    {
        boolean myRequiredFileds = Boolean.FALSE;
        boolean myValidateYear = Boolean.FALSE;
        this.itsInvPrefileSearchUIHelper = new InvPreFileSearchUIHelper();
        myRequiredFileds = this.itsInvPrefileSearchUIHelper.validateRequiredFields(this);
        myValidateYear = this.itsInvPrefileSearchUIHelper.validateYearOfSearch(this);

        if (myRequiredFileds && myValidateYear)
        {
            loadInvPreFiles();
        }
    }

    /**
     * Este método, una vez pasadas todas las validaciones, carga las prefichas
     * encontradas dentro de la lista de Prefichas invPrefileList usada por el
     * dataTable parta mostrar los resultados de la búsqueda.
     */
    @Override
    public void loadInvPreFiles()
    {
        InvPreFileEntity myEntityToSearch = prepareEntityToSearch();
        this.setInvPrefileList(this.itsinvPreFileManagement.getFilteredInvPrefileDTO(myEntityToSearch));
    }

    /**
     * Esta función prepara la entidad de invPrefile a ser buscada, para pasar
     * los parámetros de búsqueda dentro de una entidad de tipo InvPreFileEntity
     *
     * @return
     */
    private InvPreFileEntity prepareEntityToSearch()
    {
        InvPreFileEntity myEntityToSearch = new InvPreFileEntity();

        //Añadiendo el año de búsqueda de preficha
        String myBuildedDate = "01-01-" + this.getYear().toString();
        Date myDate = new Date();
        myDate = UtilsDate.convertToDate(myBuildedDate, UtilsDate.DD_MM_YYYY);

        //Añadiendo el año de búsqueda de preficha
        String myBuildedEndDate = "31-12-" + this.getYear().toString();
        Date myEndDate = new Date();
        myEndDate = UtilsDate.convertToDate(myBuildedEndDate, UtilsDate.DD_MM_YYYY);

        //Añadir el año al draft project de invprefile
        DraftProjectEntity myDraftProject = new DraftProjectEntity();
        myDraftProject.setDraftProjectStartDate(myDate);
        myDraftProject.setDraftProjectEndDate(myEndDate);
        myEntityToSearch.setInvPreFileDraftProject(myDraftProject);

        //Añadir Dependence UR
        DependenceEntity myDependence = new DependenceEntity();
        myDependence.setDependenceId(this.getUniResponsableId());
        //Si hay seleccionada un UP, asignarla
        if (!(this.getUniPresupuestalId() == Long.valueOf(0)))
        {
            DependenceEntity myDependenceFather = new DependenceEntity();
            myDependenceFather.setDependenceId(this.getUniPresupuestalId());
            myDependence.setFather(myDependenceFather);
        }
        myEntityToSearch.setInvPreFileUExecuting(myDependence);

        //Añadir Dependence UEG
        DependenceEntity myUEGDependence = new DependenceEntity();
        myUEGDependence.setDependenceId(this.getUniEjecutoraId());
        List<DependenceEntity> myDepList = new ArrayList<DependenceEntity>();
        myDepList.add(myUEGDependence);
        myEntityToSearch.setUnitExecSpending(myDepList);

        myEntityToSearch.setFolio(this.getNumber());
        myEntityToSearch.setName(this.getBuildingName());
        myEntityToSearch.setDescription(this.getDescription());
        return myEntityToSearch;

    }

    @Override
    public void cleanSearch()
    {
        this.init();
    }

    @Override
    public void filePriority()
    {
        this.disabledPriorizar = Boolean.FALSE;
    }

    @Override
    public void savePriority()
    {
        FacesMessage.Severity myMessageFaces = FacesMessage.SEVERITY_INFO;
        String myMessageUI = this.getMessage("ppp.progr.DraftProjectSearch.PrioritySaved");
        String priority = theirSelectedInvPreFileDTO.getInvprefilepriority();
        if (priority != null && !"".equals(priority))
        {
            try
            {
                InvPreFileEntity invPreFile = new InvPreFileEntity();
                Long myInvPrefileId = Long.parseLong(theirSelectedInvPreFileDTO.getInvprefileid());
                invPreFile = itsinvPreFileManagement.getInvPreFileById(myInvPrefileId);
                invPreFile.setPriority(theirSelectedInvPreFileDTO.getInvprefilepriority());
                itsinvPreFileManagement.savePriority(invPreFile);
            } catch (Exception e)
            {
                myMessageUI = this.getMessage(e.getMessage());
                myMessageFaces = FacesMessage.SEVERITY_ERROR;
            } finally
            {
                addMessageCurrentInstance(myMessageFaces,
                        myMessageUI,
                        myMessageUI);
            }
        }
    }

    @Override
    public String navigateToMainMenu()
    {
        return "options";
    }

    @Override
    public String navigateToInvPrefile()
    {
        return "newInPreFile";
    }

    /**
     * @return the year
     */
    @Override
    public String getYear()
    {
        return year;
    }

    /**
     * @param year the year to set
     */
    @Override
    public void setYear(String year)
    {
        this.year = year;
    }

    /**
     * @return the dependenceId
     */
    @Override
    public Long getDependenceId()
    {
        return dependenceId;
    }

    /**
     * @param dependenceId the dependenceId to set
     */
    @Override
    public void setDependenceId(Long dependenceId)
    {
        this.dependenceId = dependenceId;
    }

    /**
     * @param listOfDependences the listOfDependences to set
     */
    @Override
    public void setListOfDependences(List<DependenceEntity> listOfDependences)
    {
        this.listOfDependences = listOfDependences;
    }

    /**
     * @return the InvPrefileList
     */
    @Override
    public List<InvPreFileDto> getInvPrefileList()
    {
        return invPrefileList;
    }

    /**
     * @param InvPrefileList the InvPrefileList to set
     */
    @Override
    public void setInvPrefileList(List<InvPreFileDto> InvPrefileList)
    {
        this.invPrefileList = InvPrefileList;
    }

    /**
     * @return the number
     */
    @Override
    public String getNumber()
    {
        return number;
    }

    /**
     * @param number the number to set
     */
    @Override
    public void setNumber(String number)
    {
        this.number = number;
    }

    /**
     * @return the buildingName
     */
    @Override
    public String getBuildingName()
    {
        return buildingName;
    }

    /**
     * @param buildingName the buildingName to set
     */
    @Override
    public void setBuildingName(String buildingName)
    {
        this.buildingName = buildingName;
    }

    /**
     * @return the description
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the partyNumber
     */
    @Override
    public String getPartyNumber()
    {
        return partyNumber;
    }

    /**
     * @param partyNumber the partyNumber to set
     */
    @Override
    public void setPartyNumber(String partyNumber)
    {
        this.partyNumber = partyNumber;
    }

    /**
     * @return the destNumber
     */
    @Override
    public String getDestNumber()
    {
        return destNumber;
    }

    /**
     * @param destNumber the destNumber to set
     */
    @Override
    public void setDestNumber(String destNumber)
    {
        this.destNumber = destNumber;
    }

    /**
     * @return the theirCurrentOperationYear
     */
    @Override
    public String getCurrentOperationYear()
    {
        return theirCurrentOperationYear;
    }

    /**
     * @param theirCurrentOperationYear the theirCurrentOperationYear to set
     */
    @Override
    public void setCurrentOperationYear(String theirCurrentOperationYear)
    {
        this.theirCurrentOperationYear = theirCurrentOperationYear;
    }

    /**
     * @return the itsInvPrefileSearchUIHelper
     */
    @Override
    public InvPreFileSearchUIHelper getInvPrefileSearchUIHelper()
    {
        return itsInvPrefileSearchUIHelper;
    }

    /**
     * @param itsInvPrefileSearchUIHelper the itsInvPrefileSearchUIHelper to set
     */
    @Override
    public void setInvPrefileSearchUIHelper(InvPreFileSearchUIHelper itsInvPrefileSearchUIHelper)
    {
        this.itsInvPrefileSearchUIHelper = itsInvPrefileSearchUIHelper;
    }

    /**
     * @return the itsinvPreFileManagement
     */
    @Override
    public InvPreFileManagement getInvPreFileManagement()
    {
        return itsinvPreFileManagement;
    }

    /**
     * @param itsinvPreFileManagement the itsinvPreFileManagement to set
     */
    @Override
    public void setInvPreFileManagement(InvPreFileManagement anInvPreFileManagement)
    {
        this.itsinvPreFileManagement = anInvPreFileManagement;
    }

    /**
     * @return the itsRoleSelected
     */
    @Override
    public ProfileEntity getItsRoleSelected()
    {
        return itsRoleSelected;
    }

    /**
     * @param itsRoleSelected the itsRoleSelected to set
     */
    @Override
    public void setItsRoleSelected(ProfileEntity itsRoleSelected)
    {
        this.itsRoleSelected = itsRoleSelected;
    }

    /**
     * @return the theirSelectedInvPreFileDTO
     */
    @Override
    public InvPreFileDto getSelectedInvPreFileDTO()
    {
        return theirSelectedInvPreFileDTO;
    }

    /**
     * @param theirSelectedInvPreFileDTO the theirSelectedInvPreFileDTO to set
     */
    @Override
    public void setSelectedInvPreFileDTO(InvPreFileDto theirSelectedInvPreFileDTO)
    {
        this.theirSelectedInvPreFileDTO = theirSelectedInvPreFileDTO;
        this.preparateEditInvPreFile();
    }

    private void preparateEditInvPreFile()
    {
        InvPreFileEntity myInvPrefile = new InvPreFileEntity();
        //Inicializar valores de la pre-Ficha
        if (this.getSelectedInvPreFileDTO() != null)
        {

            Long myInvPrefileId = Long.parseLong(theirSelectedInvPreFileDTO.getInvprefileid());
            myInvPrefile = this.itsinvPreFileManagement.getInvPreFileById(myInvPrefileId);
        }

        this.theirInvPreFileController.setTheirInvPreFileDraftEntity(myInvPrefile);
        this.navigateToInvPrefile();
    }

    /**
     * @return the theirInvPreFileController
     */
    @Override
    public InvPreFileControllerApi getInvPreFileController()
    {
        return theirInvPreFileController;
    }

    /**
     * @param theirInvPreFileController the theirInvPreFileController to set
     */
    @Override
    public void setInvPreFileController(InvPreFileController theirInvPreFileController)
    {
        this.theirInvPreFileController = theirInvPreFileController;
    }

    /**
     * @return the uniPresupuestalId
     */
    @Override
    public Long getUniPresupuestalId()
    {
        return uniPresupuestalId;
    }

    /**
     * @param uniPresupuestalId the uniPresupuestalId to set
     */
    @Override
    public void setUniPresupuestalId(Long uniPresupuestalId)
    {
        this.uniPresupuestalId = uniPresupuestalId;
    }

    /**
     * @param listOfBudgetDependences the listOfBudgetDependences to set
     */
    @Override
    public void setListOfBudgetDependences(List<DependenceEntity> listOfBudgetDependences)
    {
        this.listOfBudgetDependences = listOfBudgetDependences;
    }

    /**
     * @return the uniResponsableId
     */
    @Override
    public Long getUniResponsableId()
    {
        return uniResponsableId;
    }

    /**
     * @param uniResponsableId the uniResponsableId to set
     */
    @Override
    public void setUniResponsableId(Long uniResponsableId)
    {
        this.uniResponsableId = uniResponsableId;
    }

    /**
     * @param listOfResponsibleDependences the listOfResponsibleDependences to
     * set
     */
    @Override
    public void setListOfResponsibleDependences(List<DepencenceDto> listOfResponsibleDependences)
    {
        this.listOfResponsibleDependences = listOfResponsibleDependences;
    }

    /**
     * @return the uniEjecutoraId
     */
    @Override
    public Long getUniEjecutoraId()
    {
        return uniEjecutoraId;
    }

    /**
     * @param uniEjecutoraId the uniEjecutoraId to set
     */
    @Override
    public void setUniEjecutoraId(Long uniEjecutoraId)
    {
        this.uniEjecutoraId = uniEjecutoraId;
    }

    /**
     * @param listOfExecutorDependences the listOfExecutorDependences to set
     */
    @Override
    public void setListOfExecutorDependences(List<DepencenceDto> listOfExecutorDependences)
    {
        this.listOfExecutorDependences = listOfExecutorDependences;
    }

    @Override
    public String navigatePreviousPreFicha()
    {
        return "findPreFicha";
    }

    /**
     * @return the itsDependenceManager
     */
    @Override
    public DependenceManagement getItsDependenceManager()
    {
        return itsDependenceManagement;
    }

    /**
     * @param itsDependenceManager the itsDependenceManager to set
     */
    @Override
    public void setItsDependenceManager(DependenceManagement itsDependenceManager)
    {
        this.itsDependenceManagement = itsDependenceManager;
    }

    /**
     * @return the listOfURNotHierarchy
     */
    @Override
    public List<DependenceEntity> getListOfURNotHierarchy()
    {
        this.listOfURNotHierarchy = this.itsDependenceManagement.getViewDependenciesTypeUR();
        return listOfURNotHierarchy;
    }

    /**
     * @param listOfURNotHierarchy the listOfURNotHierarchy to set
     */
    @Override
    public void setListOfURNotHierarchy(List<DependenceEntity> listOfURNotHierarchy)
    {
        this.listOfURNotHierarchy = listOfURNotHierarchy;
    }

    /**
     * Las listas de UR y UEG se obtienen como una lista de DependenceEntity,
     * pero a nivel de este controller solo acepta DependenceDTO. Este método
     * toma la lista de DependenceEntity de tipo UEG y la convierte en una lista
     * de DependenceDTO para ser acrgada la primera vez
     */
    @Override
    public void convertListOfUEGNotHierarchyToDependenceDto()
    {
        List<DepencenceDto> myListOfDTO = new ArrayList<DepencenceDto>();
        List<DependenceEntity> myListOfUegNotHierarchy = this.itsDependenceManagement.getViewDepIsExecutionUnit();
        for (DependenceEntity myDependence : myListOfUegNotHierarchy)
        {
            DepencenceDto myDependenceDTO = new DepencenceDto();
            myDependenceDTO.setIdDependency(myDependence.getDependenceId());
            myDependenceDTO.setNameDepend(myDependence.getDependenceDescription());
            myDependenceDTO.setClave(myDependence.getDependenceKey());
            myListOfDTO.add(myDependenceDTO);
            myDependenceDTO = null;
        }
        this.setListOfExecutorDependences(myListOfDTO);
    }

    /**
     * Este método toma la lista de DependenceEntity de tipo UR y la convierte
     * en una lista de DependenceDTO para ser acrgada la primera vez
     *
     * @param listOfURNotHierarchy
     * @return
     */
    @Override
    public List<DepencenceDto> convertListOfURNotHierarchyToDependenceDto(List<DependenceEntity> listOfURNotHierarchy)
    {
        List<DepencenceDto> myListOfDTO = new ArrayList<DepencenceDto>();

        for (DependenceEntity myDependence : listOfURNotHierarchy)
        {
            DepencenceDto myDependenceDTO = new DepencenceDto();
            myDependenceDTO.setIdDependency(myDependence.getDependenceId());
            myDependenceDTO.setNameDepend(myDependence.getDependenceDescription());
            myDependenceDTO.setClave(myDependence.getDependenceKey());
            myListOfDTO.add(myDependenceDTO);
            myDependenceDTO = null;
        }
        return myListOfDTO;
    }

    /**
     * Este método se encarga de llamar los métodos para cargar las listas de
     * UEG y UR por primera vez
     */
    @Override
    public void prepareInitialLists()
    {
        this.getListOfURNotHierarchy();
        this.setListOfResponsibleDependences(
                this.convertListOfURNotHierarchyToDependenceDto(
                this.getListOfURNotHierarchy()));
        convertListOfUEGNotHierarchyToDependenceDto();
    }

    /**
     * @return the isCboResponsible
     */
    @Override
    public boolean isIsCboResponsible()
    {
        return isCboResponsibleDisabled;
    }

    /**
     * @param isCboResponsible the isCboResponsible to set
     */
    @Override
    public void setIsCboResponsible(boolean isCboResponsible)
    {
        this.isCboResponsibleDisabled = isCboResponsible;
    }

    /**
     * Este método controla cuando se habilita/deshabilita un combo de filtro
     *
     */
    @Override
    public void changeComboStatus()
    {
        if (!(this.uniPresupuestalId == 0L))
        {
            this.isCboResponsibleDisabled = Boolean.TRUE;
            this.isCboUEGDisabled = Boolean.TRUE;
            this.uniResponsableId = 0L;
            this.uniEjecutoraId = 0L;
        } else if (!(this.uniResponsableId == 0))
        {
            this.isCboUEGDisabled = Boolean.TRUE;
            this.isCboUPDisabled = Boolean.TRUE;
            this.uniEjecutoraId = 0L;
            this.uniPresupuestalId = 0L;
        } else if (!(this.uniEjecutoraId == 0))
        {
            this.isCboResponsibleDisabled = Boolean.TRUE;
            this.isCboUPDisabled = Boolean.TRUE;
            this.uniPresupuestalId = 0L;
            this.uniResponsableId = 0L;
        }
    }

    /**
     * @return the isCboUEGDisabled
     */
    @Override
    public boolean isIsCboUEGDisabled()
    {
        return isCboUEGDisabled;
    }

    /**
     * @param isCboUEGDisabled the isCboUEGDisabled to set
     */
    @Override
    public void setIsCboUEGDisabled(boolean isCboUEGDisabled)
    {
        this.isCboUEGDisabled = isCboUEGDisabled;
    }

    /**
     * @return the isCboUPDisabled
     */
    @Override
    public boolean isIsCboUPDisabled()
    {
        return isCboUPDisabled;
    }

    /**
     * @param isCboUPDisabled the isCboUPDisabled to set
     */
    @Override
    public void setIsCboUPDisabled(boolean isCboUPDisabled)
    {
        this.isCboUPDisabled = isCboUPDisabled;
    }

    /**
     * @return the theirYearOfSearch
     */
    @Override
    public String getTheirYearOfSearch()
    {
        return theirYearOfSearch;
    }

    /**
     * @param theirYearOfSearch the theirYearOfSearch to set
     */
    @Override
    public void setTheirYearOfSearch(String theirYearOfSearch)
    {
        this.theirYearOfSearch = theirYearOfSearch;
    }

    @Override
    public boolean getBtnSrvSave()
    {
        return BtnSrvSave;
    }

    @Override
    public void setBtnSrvSave(boolean BtnSrvSave)
    {
        this.BtnSrvSave = BtnSrvSave;
    }

    @Override
    public boolean isDisabledPriorizar()
    {
        return disabledPriorizar;
    }

    @Override
    public void setDisabledPriorizar(boolean disabledPriorizar)
    {
        this.disabledPriorizar = disabledPriorizar;
    }

    @Override
    public void deleteInvPreFile()
    {
        try
        {
            InvPreFileEntity invPreFile = new InvPreFileEntity();
            String priority = theirSelectedInvPreFileDTO.getInvprefilepriority();
            if (priority != null && !"".equals(priority))
            {
                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.progr.DraftProjectSearch.PriorizarMsg"),
                        this.getMessage("ppp.progr.DraftProjectSearch.PriorizarMsg"));
            }
            Long myInvPrefileId = Long.parseLong(theirSelectedInvPreFileDTO.getInvprefileid());
            invPreFile.setInvPreFileId(myInvPrefileId);
            itsinvPreFileManagement.deleteInvPreFile(invPreFile);
            searchInvPrefiles();
        } catch (Exception e)
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                    this.getMessage(e.getMessage()),
                    this.getMessage(e.getMessage()));
        }
    }

    @Override
    public void onRowSelect(SelectEvent event)
    {
        InvPreFileDto object = (InvPreFileDto) event.getObject();
        object.getInvprefileid();
    }
}