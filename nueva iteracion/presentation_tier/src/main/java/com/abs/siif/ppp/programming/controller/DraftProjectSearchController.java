/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.management.DependenceManagement;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.api.controller.ProgMainCtrlApi;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.ppp.programming.uihelpers.DraftProjectSearchUIHelper;
import com.abs.siif.programming.dto.DraftProjectSearchByDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.management.DraftProjectSearchManagement;
import com.abs.siif.security.entities.ProfileEntity;
import com.abs.siif.support.UtilsDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jacob.flores Esta clase administra la búsqueda de Fichas de Anteproyecto
 */
@Scope("session")
@Controller("draftProjectSearchController")
public class DraftProjectSearchController extends SIIFControllerBase implements Serializable
{

  /**
   * Esta inyección se encarga de las operaciones del combo de Dependencias
   */
  @Resource(name = "dependenceManagement")
  private transient DependenceManagement theirDependenceManagement;
  @Resource(name = "draftProjectSearchManagement")
  private transient DraftProjectSearchManagement itsDraftProjectManagement;
  @Resource(name = "draftProjectHeaderController")
  private transient DraftProjectHeaderControllerApi theirDraftProjectHeaderController;
  @Resource(name = "programminMainController")
  private transient ProgMainCtrlApi theirProgrammingMainController;
  private List<DependenceEntity> itsListOfDependences;
  private Long theirDependenceId;
  private String theirYear;
  private String theirNumber;
  private String theirShortName;
  private String theirCurrentOperationYear;
  private DraftProjectSearchUIHelper itsDPSearchUIHelper;
  private List<DraftProjectEntity> theirDraftProjectList;
  private List<DraftProjectSearchByDto> theirDraftProjectListDTO;
  private String theirSubProgram;
  private DraftProjectSearchByDto theirSelectedDraftProjectDTO;
  private ProfileEntity itsRoleSelected;
  private boolean theirDirectSearchSelected;
  private boolean isCboDependencesDisabled;
  private boolean isNumberDisabled;
  private boolean isShortNameDisabled;

  public DraftProjectSearchController()
  {
  }

  /**
   * Inicializa los campso de búsqueda, setea las listas en su valosi nicial.
   */
  public void init()
  {
    this.setDraftProjectListDTO(null);
    this.setCurrentOperationYear(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString());
    this.setYear(this.getCurrentOperationYear().toString());
    itsListOfDependences = new ArrayList<DependenceEntity>();
    itsListOfDependences = getListOfDependences();
    this.setDependenceId(new Long(0));
    this.setNumber("");
    this.setShortName("");

    this.isCboDependencesDisabled = Boolean.FALSE;
    this.isShortNameDisabled = Boolean.FALSE;
    this.isNumberDisabled = Boolean.FALSE;
    this.theirDirectSearchSelected = Boolean.FALSE;
  }

  public void changeAnio()
  {

    SIIFContextBase.setParamLocalThread(
            KeyContextEnum.YEAR_SEARCH, theirYear);
    itsListOfDependences = new ArrayList<DependenceEntity>();
    itsListOfDependences = getListOfDependences();

  }

  public String navigateToMainMenu()
  {
    return "options";
  }

  //Carga los proyectos  encontrados de acuerdo a los filtros establecidos enla entity
  public void loadDraftProjects()
  {
    //Se declara una entity que se pasará al método de búsqeuda
    DraftProjectEntity myEntityToSearch = prepareEntityToSearch();
    this.setDraftProjectListDTO(itsDraftProjectManagement.getFilteredDraftProjectDTO(myEntityToSearch));
  }

  public String navigateToDraftProject()
  {
    return "newDraftProject";
  }

  /**
   * Este método se encarga de setear los datos necesarios del anteproyecto y de la
   * programación para poder ir al anteproyecto como Edición (cargue todoslos datos del
   * anteproyecto)
   */
  public void preparateEditProgramming()
  {
    Long myDraftProjectId = Long.parseLong(this.theirSelectedDraftProjectDTO.getIdanteproyecto());
    Long myProgrammingId = Long.valueOf(this.theirSelectedDraftProjectDTO.getIdprogramacion());
    Long myDependenceId = Long.valueOf(this.theirSelectedDraftProjectDTO.getIdprogramacion());
    Long myObjectiveId = Long.parseLong(this.theirSelectedDraftProjectDTO.getIdobjetivo());
    theirDraftProjectHeaderController.setTheirCurrentDraftProjectId(myDraftProjectId);
    theirDraftProjectHeaderController.setTheirProgrammingId(myProgrammingId);
    theirDraftProjectHeaderController.setIdDependency(myDependenceId);
    theirProgrammingMainController.setAObjectiveOfPI(myObjectiveId);
    theirProgrammingMainController.setAnObjectiveId(myObjectiveId);
    theirDraftProjectHeaderController.setItsDisabledTab(Boolean.FALSE);
    
  }

  /**
   * Este método genera una entitiy de DraftProject y le pasa los parámetros de búsqueda
   * del usuario, para hacer la búsqueda
   *
   * @return
   */
  private DraftProjectEntity prepareEntityToSearch()
  {
    DraftProjectEntity myPreparedEntityToSearch = new DraftProjectEntity();
    //Añadir la fecha a la entity, solo nos interesa el año recopilado del Controller
    String myBuildedDate = "01-01-" + this.getYear().toString();
    Date myDate = new Date();
    myDate = UtilsDate.convertToDate(myBuildedDate, UtilsDate.DD_MM_YYYY);
    myPreparedEntityToSearch.setDraftProjectStartDate(myDate);
    DependenceEntity myDependence = new DependenceEntity();
    DependenceEntity myFatherDep = new DependenceEntity();
    myFatherDep.setDependenceId(this.getDependenceId());
    myDependence.setFather(myFatherDep);
    myPreparedEntityToSearch.setDraftProjectDependency(myDependence);
    myPreparedEntityToSearch.setDraftProjectKey(this.getNumber());
    myPreparedEntityToSearch.setDraftProjectShortName(this.getShortName());
    
    if(this.theirDirectSearchSelected){
                
        myDependence.setDependenceId(Long.valueOf(0));
        myDependence.setFather(myDependence);
        myPreparedEntityToSearch.setDraftProjectDependency(myDependence);
        myPreparedEntityToSearch.setDraftProjectKey("");
        myPreparedEntityToSearch.setDraftProjectShortName("");
    }

    return myPreparedEntityToSearch;
  }

  /**
   * Regresa la lsita de dependencias, es la misma lista usada en Programación
   *
   * @return the itsListOfDependences
   */
  public List<DependenceEntity> getListOfDependences()
  {
    itsListOfDependences = theirDependenceManagement.getViewDependenciesTypeUR();
    return itsListOfDependences;
  }

  /**
   * @param itsListOfDependences the itsListOfDependences to set
   */
  public void setListOfDependences(List<DependenceEntity> aListOfDependences)
  {
    this.itsListOfDependences = aListOfDependences;
  }

  /**
   * @return the theirDependenceId
   */
  public Long getDependenceId()
  {
    return theirDependenceId;
  }

  /**
   * @param theirDependenceId the theirDependenceId to set
   */
  public void setDependenceId(Long theirDependenceId)
  {
    this.theirDependenceId = theirDependenceId;
  }

  /**
   * @return the theirYear
   */
  public String getYear()
  {
    return theirYear;
  }

  /**
   * @param theirYear the theirYear to set
   */
  public void setYear(String theirYear)
  {
    this.theirYear = theirYear;
  }

  /**
   * @return the theirCurrentOperationYear
   */
  public String getCurrentOperationYear()
  {
    return theirCurrentOperationYear;
  }

  /**
   * @param theirCurrentOperationYear the theirCurrentOperationYear to set
   */
  public void setCurrentOperationYear(String theirCurrentOperationYear)
  {
    this.theirCurrentOperationYear = theirCurrentOperationYear;
  }

  /**
   * @return the itsDraftProjectManagement
   */
  public DraftProjectSearchManagement getDraftProjectManagement()
  {
    return itsDraftProjectManagement;
  }

  /**
   * @param itsDraftProjectManagement the itsDraftProjectManagement to set
   */
  public void setDraftProjectManagement(DraftProjectSearchManagement itsDraftProjectManagement)
  {
    this.itsDraftProjectManagement = itsDraftProjectManagement;
  }

  /**
   * @return the theirDraftProjectList
   */
  public List<DraftProjectSearchByDto> getDraftProjectListDTO()
  {
    return theirDraftProjectListDTO;
  }
  
  /**
   * @param theirDraftProjectList the theirDraftProjectListDTO to set
   */
  public void setDraftProjectListDTO(List<DraftProjectSearchByDto> theirDraftProjectList)
  {
    this.theirDraftProjectListDTO = theirDraftProjectList;
  }

  /**
   * @return the theirNumber
   */
  public String getNumber()
  {
    return theirNumber;
  }

  /**
   * @param theirNumber the theirNumber to set
   */
  public void setNumber(String aNumber)
  {
    this.theirNumber = aNumber;
  }

  /**
   * @return the theirShortName
   */
  public String getShortName()
  {
    return theirShortName;
  }

  /**
   * @param theirShortName the theirShortName to set
   */
  public void setShortName(String aShortName)
  {
    this.theirShortName = aShortName;
  }

  /**
   * @return the theirSubProgram
   */
  public String getSubProgram()
  {
    return theirSubProgram;
  }

  /**
   * @param theirSubProgram the theirSubProgram to set
   */
  public void setSubProgram(String theirSubProgram)
  {
    this.theirSubProgram = theirSubProgram;
  }

  /**
   * @return the theirDraftProjectHeaderController
   */
  public DraftProjectHeaderControllerApi getDraftProjectHeaderController()
  {
    return theirDraftProjectHeaderController;
  }

  /**
   * @param theirDraftProjectHeaderController the theirDraftProjectHeaderController to set
   */
  public void setDraftProjectHeaderController(DraftProjectHeaderControllerApi theirDraftProjectHeaderController)
  {
    this.theirDraftProjectHeaderController = theirDraftProjectHeaderController;
  }

  /**
   * @return the theirSelectedDraftProject
   */
  public DraftProjectSearchByDto getSelectedDraftProject()
  {

    return theirSelectedDraftProjectDTO;
  }

  /**
   * @param setSelectedDraftProject the theirSelectedDraftProject to set
   */
  public void setSelectedDraftProject(DraftProjectSearchByDto aSelectedDraftProject)
  {
    ObjectiveJoinLevelTreeviewDto myJoinLevel = new ObjectiveJoinLevelTreeviewDto();
    // myJoinLevel.set
    this.theirSelectedDraftProjectDTO = aSelectedDraftProject;
    this.preparateEditProgramming();
    navigateToDraftProject();
  }

  /**
   * @return the theirProgrammingMainController
   */
  public ProgMainCtrlApi getProgrammingMainController()
  {
    return theirProgrammingMainController;
  }

  /**
   * @param theirProgrammingMainController the theirProgrammingMainController to set
   */
  public void setProgrammingMainController(ProgrammingMainController theirProgrammingMainController)
  {
    this.theirProgrammingMainController = theirProgrammingMainController;
  }

  /**
   * @return the itsRoleSelected
   */
  public ProfileEntity getItsRoleSelected()
  {
    return itsRoleSelected;
  }

  /**
   * @param itsRoleSelected the itsRoleSelected to set
   */
  public void setItsRoleSelected(ProfileEntity itsRoleSelected)
  {
    this.itsRoleSelected = itsRoleSelected;
  }

  /**
   * @return the theirDirectSearchSelected
   */
  public boolean isTheirDirectSearchSelected()
  {
    return theirDirectSearchSelected;
  }

  /**
   * @param theirDirectSearchSelected the theirDirectSearchSelected to set
   */
  public void setTheirDirectSearchSelected(boolean theirDirectSearchSelected)
  {
    this.theirDirectSearchSelected = theirDirectSearchSelected;
  }

  public void changeControlStatus()
  {
    if (this.theirDirectSearchSelected)
    {
      this.isCboDependencesDisabled = Boolean.TRUE;
      this.isShortNameDisabled = Boolean.TRUE;
      this.isNumberDisabled = Boolean.TRUE;
    }
    else
    {
      this.isCboDependencesDisabled = Boolean.FALSE;
      this.isShortNameDisabled = Boolean.FALSE;
      this.isNumberDisabled = Boolean.FALSE;
    }
  }

  /**
   * @return the isCboDependencesDisabled
   */
  public boolean isIsCboDependencesDisabled()
  {
    return isCboDependencesDisabled;
  }

  /**
   * @param isCboDependencesDisabled the isCboDependencesDisabled to set
   */
  public void setIsCboDependencesDisabled(boolean isCboDependencesDisabled)
  {
    this.isCboDependencesDisabled = isCboDependencesDisabled;
  }

  /**
   * @return the isNumberDisabled
   */
  public boolean isIsNumberDisabled()
  {
    return isNumberDisabled;
  }

  /**
   * @param isNumberDisabled the isNumberDisabled to set
   */
  public void setIsNumberDisabled(boolean isNumberDisabled)
  {
    this.isNumberDisabled = isNumberDisabled;
  }

  /**
   * @return the isShortNameDisabled
   */
  public boolean isIsShortNameDisabled()
  {
    return isShortNameDisabled;
  }

  /**
   * @param isShortNameDisabled the isShortNameDisabled to set
   */
  public void setIsShortNameDisabled(boolean isShortNameDisabled)
  {
    this.isShortNameDisabled = isShortNameDisabled;
  }
}