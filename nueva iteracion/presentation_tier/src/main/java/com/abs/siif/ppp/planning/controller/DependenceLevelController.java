/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependenceLevelController
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.controller;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.budget.management.BudgetKeyDefinitionManagement;
import com.abs.siif.common.entities.ColectiveTypeEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import com.abs.siif.planning.management.DependenceLevelManagement;
import com.abs.siif.ppp.planning.api.controller.DependenceLevelControllerApi;
import com.abs.siif.ppp.planning.uihelpers.DependenceLevelDataModel;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Erick Leija
 */
@Controller("dependenceLevelController")
@Scope("session")
public class DependenceLevelController
extends SIIFControllerBase
        implements Serializable, DependenceLevelControllerApi
{
    @Resource(name = "dependenceLevelManagement")
    private transient DependenceLevelManagement theirDependenceLevelManagement;
    
    @Resource(name = "budgetKeyDefinitionManagement")
    private transient BudgetKeyDefinitionManagement theirBudgetKeyDefinitionManagement;

    //Esta es la lista que contiene los niveles de las Dependencias
    public List<DependenceLevelEntity> itsListOfDependenceLevels= new ArrayList<DependenceLevelEntity>();
    //Esta es la Lista que contiene los Tipos de Colectivas
    public List<ColectiveTypeEntity> itsListOfColectiveType = new ArrayList<ColectiveTypeEntity>();
    private DependenceLevelDataModel itsMyDependenceLevelDataModel;

    //esta variable contiene el elemento Seleccionado
    private DependenceLevelEntity itsSelectedRow;
    
    //Aqui empiezan las variables para manejar los registros
    private Long itsLevelId;
    private int itsYearOfEjecution;
    private int itsLevel;
    private String itsLevelKey;
    private String itsLevelDescription;
    private Long itsSelectedColectiveType;
    
    //Aqui comienza todas las banderas del nivel
    private boolean itsLevelHasInstitutionalPlan;
    private boolean itsLevelHasPaymentRequest;
    private boolean itsLevelHasRequisition;
    private boolean itsLevelHasEmployee;
    private boolean itsLevelHasBranch;
    private boolean itsLevelHasCeilingBudget;
    private boolean itsLevelHasHistorical;
    private boolean itsLevelHasFramingAdminClassif;
    private boolean itsLevelHasSector;
    private boolean itsLevelHasProyDependence;
    private boolean itsLevelHasInventory;
    private boolean itsLevelHasResponsibleUnit;
    private boolean itsLevelHasUEG;
    private boolean itsLevelHasClassifierUnit;
    
    
    /**
     * Este metodo se encarga de inicializar la pantalla trayendo la lista de 
     * niveles de dependencia, así como la lista de los tipos de colectiva que 
     * existen en la base de datos
     */
    @Override
    public void init()
    {
        try
       {
          //Se obtiene un mapa el cual contiene 2 listas, una de niveles y otra de Colectivas
          Map<String, List<?>> myMapToLoad = theirDependenceLevelManagement.
                  getAllDependenceLevelsAndSupportListByYear
                  (Integer.parseInt(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()));
       itsListOfDependenceLevels = (List<DependenceLevelEntity>) myMapToLoad.get("Levels");
       itsListOfColectiveType = (List<ColectiveTypeEntity>) myMapToLoad.get("ColectiveTypes");
       cleanScreen();
       //Aqui se define el DataModel asignandole La Lista de los niveles de Dependencias
       itsMyDependenceLevelDataModel = new DependenceLevelDataModel(itsListOfDependenceLevels);
       
       } catch (Exception exc) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));

        }
       
    }

    /**
     * Este metodo es el encargado de limpiar las cajas de texto y las banderas 
     * de que se encuentran en la pantalla
     */
    @Override
    public void cleanScreen()
    {
        itsLevelId = null;
        itsLevel = 0;
        itsLevelKey = "";
        itsLevelDescription = "";
        itsSelectedColectiveType = (long) 0;
        itsLevelHasInstitutionalPlan = false;
        itsLevelHasPaymentRequest = false;
        itsLevelHasRequisition = false;
        itsLevelHasEmployee = false;
        itsLevelHasBranch = false;
        itsLevelHasCeilingBudget = false;
        itsLevelHasHistorical = false;
        itsLevelHasFramingAdminClassif = false;
        itsLevelHasSector = false;
        itsLevelHasProyDependence = false;
        itsLevelHasInventory = false;
        itsLevelHasResponsibleUnit = false;
        itsLevelHasUEG = false;
        itsLevelHasClassifierUnit = false;
        itsSelectedRow = null;
    }
    
    /**
     * Este Metodo es el encargado de guardar el nivel, del que se han guardado
     * los datos
     */
    @Override
    public void saveLevelOfDependence()
    {
        try
        {
        //Se crea una entidad de Definicion de la Clave presupuestal
        //Se manda como parametro El año que traemos en el contexto
        BudgetKeyDefinitionEntity myConfToSave = theirBudgetKeyDefinitionManagement.
               getBudgetDefinitionByYear
               (Integer.parseInt(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()));
        DependenceLevelEntity myEntityToSave= new DependenceLevelEntity();    
        myEntityToSave.setBudgetKeyDefinitionLevels(myConfToSave);
        myEntityToSave.setDependenceLevelId(itsLevelId);
        myEntityToSave.setDependenceLevel((short)itsLevel);
        myEntityToSave.setDependenceLevelkey(itsLevelKey);
        myEntityToSave.setDependenceLevelDescription(itsLevelDescription); 
        //Aqui se crea la entidad de Colectiva que se va a guardar
        ColectiveTypeEntity myColectiveToSave = new ColectiveTypeEntity();
        myColectiveToSave.setColectiveTypeId(itsSelectedColectiveType);
        myEntityToSave.setColectiveType(myColectiveToSave); 
        myEntityToSave.setDependencyLevelHasInstitutionalPlan(itsLevelHasInstitutionalPlan);
        myEntityToSave.setDependencyLevelHasPaymetRequest(itsLevelHasPaymentRequest);
        myEntityToSave.setDependencyLevelHasRequisition(itsLevelHasRequisition);
        myEntityToSave.setDependencyLevelHasEmployee(itsLevelHasEmployee);
        myEntityToSave.setDependencyLevelIsBranch(itsLevelHasBranch);
        myEntityToSave.setDependencyLevelHasBudgetCeiling(itsLevelHasCeilingBudget);
        myEntityToSave.setDependencyLevelIsHistorical(itsLevelHasHistorical);
        myEntityToSave.setDependencyLevelHasClassifFraming(itsLevelHasFramingAdminClassif);
        myEntityToSave.setDependencyLevelIsSector(itsLevelHasSector);
        myEntityToSave.setDependencyLevelHasProyDependence(itsLevelHasProyDependence);
        myEntityToSave.setDependencyLevelHasInventory(itsLevelHasInventory);
        myEntityToSave.setDependencyLevelIsResponsibleUnit(itsLevelHasResponsibleUnit);
        myEntityToSave.setDependencyLevelIsExecutionUnit(itsLevelHasUEG);
        myEntityToSave.setDependencyLevelHasClassifierUnit(itsLevelHasClassifierUnit);
        theirDependenceLevelManagement.saveDependenceLevel(myEntityToSave);
        init();
        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                            this.getMessage("ppp.progr.succesSave"),
                            this.getMessage("ppp.progr.succesSave"));
        }
        catch (Exception exc) 
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));

        }
    }
    
    /**
     * Borra el registro Seleccionado en pantalla
     */
    @Override
    public void deleteLevelOfDependence()
    {
        try
        {
            if (itsSelectedRow.getDependenceLevel() == ((short) itsListOfDependenceLevels.size()))
            {
                theirDependenceLevelManagement.deleteDependenceLevel(itsSelectedRow);
                init();
                addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                        this.getMessage("ppp.progr.succesDelete"),
                        this.getMessage("ppp.progr.succesDelete"));
            }
            else
            {
                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.LevelsOfAdministrativeClassifier.Error.LevelSecuenceDelete"),
                        this.getMessage("ppp.LevelsOfAdministrativeClassifier.Error.LevelSecuenceDelete"));
            }
        }
       catch (Exception exc) 
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));

        }
    }
    
    /**
     * Este metodo es el que se encarga de preparar la pantalla para dar de alta
     * un nuevo registro
     */
    @Override
    public void prepareNewLevelOfDependence()
    {
        cleanScreen();
        itsLevel = getTheLastLevel();
    }
    
    /**
     * Este Metodo es el encargado de obtener el Siguiente nivel que sera Dado
     * de Alta tomando en cuenta el numero de niveles capturados hasta el momento
     * @return 
     */
    @Override
    public int getTheLastLevel()
    {
        return itsListOfDependenceLevels.size()+1;
    }
    
    /**
     * Este metodo es el Encargado de llenar los campos de la pantalla cuando se
     * ha seleccionado un registro del Grid en pantalla
     * @param event 
     */
    @Override
    public void onRowSelect(SelectEvent event) 
    {
        itsLevelId = itsSelectedRow.getDependenceLevelId();
        itsYearOfEjecution = itsSelectedRow.getBudgetKeyDefinitionLevels().getPresupuestalKeyDefinitionYear();
        itsLevel = itsSelectedRow.getDependenceLevel();
        itsLevelKey = itsSelectedRow.getDependenceLevelkey();
        itsLevelDescription = itsSelectedRow.getDependenceLevelDescription();
        itsSelectedColectiveType = itsSelectedRow.getColectiveType().getColectiveTypeId();
        itsLevelHasInstitutionalPlan = itsSelectedRow.isDependencyLevelHasInstitutionalPlan();
        itsLevelHasPaymentRequest = itsSelectedRow.isDependencyLevelHasPaymetRequest();
        itsLevelHasRequisition = itsSelectedRow.isDependencyLevelHasRequisition();
        itsLevelHasEmployee = itsSelectedRow.isDependencyLevelHasEmployee();
        itsLevelHasBranch = itsSelectedRow.isDependencyLevelIsBranch();
        itsLevelHasCeilingBudget = itsSelectedRow.isDependencyLevelHasBudgetCeiling();
        itsLevelHasHistorical = itsSelectedRow.isDependencyLevelIsHistorical();
        itsLevelHasFramingAdminClassif = itsSelectedRow.isDependencyLevelHasClassifFraming();
        itsLevelHasSector = itsSelectedRow.isDependencyLevelIsSector();
        itsLevelHasProyDependence = itsSelectedRow.isDependencyLevelHasProyDependence();
        itsLevelHasInventory = itsSelectedRow.isDependencyLevelHasInventory();
        itsLevelHasResponsibleUnit = itsSelectedRow.isDependencyLevelIsResponsibleUnit();
        itsLevelHasUEG = itsSelectedRow.isDependencyLevelIsExecutionUnit();
        itsLevelHasClassifierUnit = itsSelectedRow.isDependencyLevelHasClassifierUnit();
    }
    //***********************************************************************************************
    
    
    /**
     * Este Metodo es el encargado de realizar la busqueda en la lista de las banderas que se
     * se manden como parametro de la UI, 
     * @param flagToCheck Es la Bandera de la Base de datos que se checara
     * @param myFlagNameToUnable Es el control que se inhabilitara en caso de Ser necesario
     */
    public void searchInList(String flagToCheck,String myFlagNameToUnable)
    {
       try
        {
            
        //Aqui se usa Reflection para obtener los la variable que se meodificara
        Field myFieldDescription = DependenceLevelController.class.getDeclaredField(myFlagNameToUnable);
        myFieldDescription.setAccessible(true);
        //Aqui se usa Reflection para el campo que se checara en la Entity
        Field myFieldIdentity = DependenceLevelEntity.class.getDeclaredField(flagToCheck);
         myFieldIdentity.setAccessible(true);
         if(Boolean.valueOf(myFieldDescription.get(this).toString()))
         {
            for (DependenceLevelEntity myDependenceToCheck:itsListOfDependenceLevels)
            {
                if (Boolean.valueOf(myFieldIdentity.get(myDependenceToCheck).toString()))
                {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.LevelsOfAdministrativeClassifier.Flags.AlreadyUsed"),
                        this.getMessage("ppp.LevelsOfAdministrativeClassifier.Flags.AlreadyUsed"));
                    myFieldDescription.set(this, false);
                    return;
                }
            }
         }
        }
        
        catch (Exception exc) 
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));

        }
    }
    //***********************************************************************************************
    //Aqui comienza todos los Getters****************************************************************
    @Override
    public List<DependenceLevelEntity> getItsListOfDependenceLevels()
    {
        return itsListOfDependenceLevels;
    }

    @Override
    public int getItsLevel()
    {
        return itsLevel;
    }

    @Override
    public String getItsLevelDescription()
    {
        return itsLevelDescription;
    }

    @Override
    public boolean isItsLevelHasBranch()
    {
        return itsLevelHasBranch;
    }

    @Override
    public boolean isItsLevelHasCeilingBudget()
    {
        return itsLevelHasCeilingBudget;
    }

    @Override
    public boolean isItsLevelHasClassifierUnit()
    {
        return itsLevelHasClassifierUnit;
    }

    @Override
    public boolean isItsLevelHasEmployee()
    {
        return itsLevelHasEmployee;
    }

    @Override
    public boolean isItsLevelHasFramingAdminClassif()
    {
        return itsLevelHasFramingAdminClassif;
    }

    @Override
    public boolean isItsLevelHasHistorical()
    {
        return itsLevelHasHistorical;
    }

    @Override
    public boolean isItsLevelHasInstitutionalPlan()
    {
        return itsLevelHasInstitutionalPlan;
    }

    @Override
    public boolean isItsLevelHasInventory()
    {
        return itsLevelHasInventory;
    }

    @Override
    public boolean isItsLevelHasPaymentRequest()
    {
        return itsLevelHasPaymentRequest;
    }

    @Override
    public boolean isItsLevelHasProyDependence()
    {
        return itsLevelHasProyDependence;
    }

    @Override
    public boolean isItsLevelHasRequisition()
    {
        return itsLevelHasRequisition;
    }

    @Override
    public boolean isItsLevelHasResponsibleUnit()
    {
        return itsLevelHasResponsibleUnit;
    }

    @Override
    public boolean isItsLevelHasSector()
    {
        return itsLevelHasSector;
    }

    @Override
    public boolean isItsLevelHasUEG()
    {
        return itsLevelHasUEG;
    }

    @Override
    public String getItsLevelKey()
    {
        return itsLevelKey;
    }

    @Override
    public List<ColectiveTypeEntity> getItsListOfColectiveType()
    {
        return itsListOfColectiveType;
    }

    @Override
    public Long getItsSelectedColectiveType()
    {
        return itsSelectedColectiveType;
    }

    @Override
    public int getItsYearOfEjecution()
    {
        return itsYearOfEjecution;
    }

    public DependenceLevelManagement getTheirDependenceLevelManagement()
    {
        return theirDependenceLevelManagement;
    }

    @Override
    public DependenceLevelDataModel getItsMyDependenceLevelDataModel()
    {
        return itsMyDependenceLevelDataModel;
    }

    @Override
    public DependenceLevelEntity getItsSelectedRow()
    {
        return itsSelectedRow;
    }
    //***********************************************************************************************

    //Aqui inician Todos loas Setters****************************************************************
    @Override
    public void setItsListOfDependenceLevels(List<DependenceLevelEntity> itsListOfDependenceLevels)
    {
        this.itsListOfDependenceLevels = itsListOfDependenceLevels;
    }

    @Override
    public void setItsLevel(int itsLevel)
    {
        this.itsLevel = itsLevel;
    }

    @Override
    public void setItsLevelDescription(String itsLevelDescription)
    {
        this.itsLevelDescription = itsLevelDescription;
    }

    @Override
    public void setItsLevelHasBranch(boolean itsLevelHasBranch)
    {
        this.itsLevelHasBranch = itsLevelHasBranch;
    }

    @Override
    public void setItsLevelHasCeilingBudget(boolean itsLevelHasCeilingBudget)
    {
        this.itsLevelHasCeilingBudget = itsLevelHasCeilingBudget;
    }

    @Override
    public void setItsLevelHasClassifierUnit(boolean itsLevelHasClassifierUnit)
    {
        this.itsLevelHasClassifierUnit = itsLevelHasClassifierUnit;
    }

    @Override
    public void setItsLevelHasEmployee(boolean itsLevelHasEmployee)
    {
        this.itsLevelHasEmployee = itsLevelHasEmployee;
    }

    @Override
    public void setItsLevelHasFramingAdminClassif(boolean itsLevelHasFramingAdminClassif)
    {
        this.itsLevelHasFramingAdminClassif = itsLevelHasFramingAdminClassif;
    }

    @Override
    public void setItsLevelHasHistorical(boolean itsLevelHasHistorical)
    {
        this.itsLevelHasHistorical = itsLevelHasHistorical;
    }

    @Override
    public void setItsLevelHasInstitutionalPlan(boolean itsLevelHasInstitutionalPlan)
    {
        this.itsLevelHasInstitutionalPlan = itsLevelHasInstitutionalPlan;
    }

    @Override
    public void setItsLevelHasInventory(boolean itsLevelHasInventory)
    {
        this.itsLevelHasInventory = itsLevelHasInventory;
    }

    @Override
    public void setItsLevelHasPaymentRequest(boolean itsLevelHasPaymentRequest)
    {
        this.itsLevelHasPaymentRequest = itsLevelHasPaymentRequest;
    }

    @Override
    public void setItsLevelHasProyDependence(boolean itsLevelHasProyDependence)
    {
        this.itsLevelHasProyDependence = itsLevelHasProyDependence;
    }

    @Override
    public void setItsLevelHasRequisition(boolean itsLevelHasRequisition)
    {
        this.itsLevelHasRequisition = itsLevelHasRequisition;
    }

    @Override
    public void setItsLevelHasResponsibleUnit(boolean itsLevelHasResponsibleUnit)
    {
        this.itsLevelHasResponsibleUnit = itsLevelHasResponsibleUnit;
    }

    @Override
    public void setItsLevelHasSector(boolean itsLevelHasSector)
    {
        this.itsLevelHasSector = itsLevelHasSector;
    }

    @Override
    public void setItsLevelHasUEG(boolean itsLevelHasUEG)
    {
        this.itsLevelHasUEG = itsLevelHasUEG;
    }

    @Override
    public void setItsLevelKey(String itsLevelKey)
    {
        this.itsLevelKey = itsLevelKey;
    }

    @Override
    public void setItsListOfColectiveType(List<ColectiveTypeEntity> itsListOfColectiveType)
    {
        this.itsListOfColectiveType = itsListOfColectiveType;
    }

    @Override
    public void setItsSelectedColectiveType(Long itsSelectedColectiveType)
    {
        this.itsSelectedColectiveType = itsSelectedColectiveType;
    }

    @Override
    public void setItsYearOfEjecution(int itsYearOfEjecution)
    {
        this.itsYearOfEjecution = itsYearOfEjecution;
    }

    public void setTheirDependenceLevelManagement(DependenceLevelManagement theirDependenceLevelManagement)
    {
        this.theirDependenceLevelManagement = theirDependenceLevelManagement;
    }

    @Override
    public void setItsMyDependenceLevelDataModel(DependenceLevelDataModel itsMyDependenceLevelDataModel)
    {
        this.itsMyDependenceLevelDataModel = itsMyDependenceLevelDataModel;
    }

    @Override
    public void setItsSelectedRow(DependenceLevelEntity itsSelectedRow)
    {
        this.itsSelectedRow = itsSelectedRow;
    }
    
    //***********************************************************************************************
}
