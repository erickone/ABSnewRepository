/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  EncuadreController
 *  Purpose:   Controlar la Relación de Programas con Unidad Ejecutora del 
 *             Gasto (UEG)
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.ppp.programming.api.controller.DepToObjLinkControllerApi;
import com.abs.siif.ppp.programming.uihelpers.DepToObjLinkDataModel;
import com.abs.siif.programming.management.DepToObjLinkManagement;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FENIX-02
 */
@Controller("depToObjLinkController")
@Scope("session")
public class DepToObjLinkController extends SIIFControllerBase 
    implements Serializable, DepToObjLinkControllerApi
{
    
    @Resource(name = "depToObjLinkManagement")
    private DepToObjLinkManagement itsDepToObjLinkManagement;
    
    //Estas variables son para controlar la carga del nivel que se encuadra.
    private String itsEncuadreLevelDesc = "";
    private String itsEncuadreLevel = "";
    private String itsEncuadreName = "";
        
    //Estas variables son para poblar y guardar valores del DataTable.
    private DepToObjLinkDataModel itsFatherDataModel;
    private List<DepencenceDto> itsFatherList;  
    private DepencenceDto selectedFather;
    
    //Esta variable es para poblar el PickList.
    private DualListModel<DependenceEntity> itsChildsDualList;
    List<DependenceEntity> childsSource = new ArrayList<DependenceEntity>();  
    List<DependenceEntity> childsTarget = new ArrayList<DependenceEntity>();
    List<DependenceEntity> allChilds = new ArrayList<DependenceEntity>();
    
    //variable del objetivo editado
    private ObjectiveEntity itsObjectiveEntity;
    
    @Override
    public void onRowSelect(SelectEvent event) {
        childsSource.clear();
        childsTarget.clear();
        childsSource = itsDepToObjLinkManagement.getChildsList(this.selectedFather.getIdDependency());
        
        Long obj = this.itsObjectiveEntity.getObjectiveId();
        childsTarget = itsDepToObjLinkManagement.getChildsRelatedObj(selectedFather.getIdDependency(), obj);
        
        checkLists();
        itsChildsDualList = new DualListModel<DependenceEntity>(childsSource, childsTarget);
        
        allChilds.addAll(childsSource);
        allChilds.addAll(childsTarget);
        
    }  
  
    @Override
    public void onRowUnselect(UnselectEvent event) { 
        itsChildsDualList = null;
    }  
    
    private void checkLists(){
        for (DependenceEntity s : childsTarget) {  
            if (childsSource.contains(s)) {  
                childsSource.remove(s);  
            }  
        }
    }
    
    @Override
    public void init(){
        itsFatherList = itsDepToObjLinkManagement.getFathersList();
        itsFatherDataModel = new DepToObjLinkDataModel(itsFatherList);
        childsSource.clear();
        childsTarget.clear();
        itsChildsDualList = new DualListModel<DependenceEntity>(childsSource, childsTarget);
        selectedFather = null;
        allChilds.clear();
    }
    
    @Override
    public void saveDepObjLink(){
        FacesMessage.Severity myMessageFaces=FacesMessage.SEVERITY_INFO;
        String myMessageUI=this.getMessage("ppp.progr.EncuadreGuardado");
        List<DependenceEntity> targetChild = new ArrayList<DependenceEntity>();
        try {    
            String[] array;
            array = new String[itsChildsDualList.getTarget().size()];
            array = itsChildsDualList.getTarget().toArray(array);
            DependenceEntity  refDep;
               for (String sId : array) {
                refDep = new DependenceEntity();
                Long id = new Long(sId);
                refDep.setDependenceId(id);
                refDep = SearchList.findObjectList(allChilds, refDep);
                targetChild.add(refDep);
               }
               itsObjectiveEntity.setDependences(targetChild);
               
               itsDepToObjLinkManagement.saveObjectiveWithDependencies(itsObjectiveEntity,this.selectedFather.getIdDependency());
               
            } catch (Exception e) {
                myMessageUI=this.getMessage(e.getMessage());
                myMessageFaces=FacesMessage.SEVERITY_ERROR;
            } finally {
                addMessageCurrentInstance(myMessageFaces,
                        myMessageUI,
                        myMessageUI);
            }
    }
    @Override
    public List<DepencenceDto> getFatherList()
    {
        return itsFatherList;
    }

    @Override
    public void setFatherList(List<DepencenceDto> aFatherList)
    {
        this.itsFatherList = aFatherList;
    }

    @Override
    public DepencenceDto getSelectedFather()
    {
        return selectedFather;
    }

    @Override
    public void setSelectedFather(DepencenceDto aSelectedFather)
    {
        this.selectedFather = aSelectedFather;
    }

    @Override
    public DualListModel<DependenceEntity> getItsChildsDualList()
    {
        return itsChildsDualList;
    }

    @Override
    public void setItsChildsDualList(DualListModel<DependenceEntity> aChildsDualList)
    {
        this.itsChildsDualList = aChildsDualList;
    }

    @Override
    public String getItsEncuadreLevelDesc()
    {
        return itsEncuadreLevelDesc;
    }

    @Override
    public void setItsEncuadreLevelDesc(String itsEncuadreLevelDesc)
    {
        this.itsEncuadreLevelDesc = itsEncuadreLevelDesc;
    }

    
    @Override
    public String getItsEncuadreLevel()
    {
        return itsEncuadreLevel;
    }

    @Override
    public void setItsEncuadreLevel(String itsEncuadreLevel)
    {
        this.itsEncuadreLevel = itsEncuadreLevel;
    }

    @Override
    public String getItsEncuadreName()
    {
        return itsEncuadreName;
    }

    @Override
    public void setItsEncuadreName(String itsEncuadreName)
    {
        this.itsEncuadreName = itsEncuadreName;
    }

    @Override
    public DepToObjLinkDataModel getItsFatherDataModel()
    {
        return itsFatherDataModel;
    }

    @Override
    public void setItsFatherDataModel(DepToObjLinkDataModel itsFatherDataModel)
    {
        this.itsFatherDataModel = itsFatherDataModel;
    }

    @Override
    public ObjectiveEntity getItsObjectiveEntity()
    {
        return itsObjectiveEntity;
    }

    @Override
    public void setItsObjectiveEntity(ObjectiveEntity itsObjectiveEntity)
    {
        this.itsObjectiveEntity = itsObjectiveEntity;
    }
    
}
