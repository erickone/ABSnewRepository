/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ProgMainCtrlApi
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

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import com.abs.siif.planning.entities.InstitutionalPlanObjectiveEntity;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import java.util.List;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Israel Ruiz
 */
public interface ProgMainCtrlApi {

    /**
     * este metodo se encarga de llenar las listas necesarias para la utlizacion
     * de la pantalla(el combo de Objetivos Especificos, el Arbol de Solo
     * Programas y el arbol de las fichas)
     */
    void ListOfInstitutionalPlansAndObjectiveEntity();

    void deleteDraftProject();

    /**
     * formatea llave de la dependencua a 5 digitos
     *
     * @param key llave dependencia
     * @return llave formateada a 5 digitos
     */
    String formatDependenceKey(String key);

    /**
     * Este metodo nos sirve para obtener el Id de la dependencia que estamos
     * trabajando
     *
     * @return Un String que contiene el Id de la dependencia Seleccionada
     */
    Long getADependenceId();

    /**
     * Este metodo nos sirve para obtener el Id del objetivo General
     * Seleccionado en el combo
     *
     * @return un String con el Id del Plan Institucional
     */
    Long getAObjectiveOfPI();

    Long getAnObjectiveId();

    void getDataForDraftProject();

    /**
     *
     * @return
     */
    Boolean getDisableTreeOptionsEdit();

    /**
     *
     * @return
     */
    Boolean getDisableTreeOptionsNew();

    /**
     * @return the itsFatherDescription
     */
    String getFatherDescription();

    ObjectiveJoinLevelTreeviewDto getItsAxisSelected();

    Boolean getItsDisable();

    /**
     * Se define la lista de dependencias que estaran poblando el combo de las
     * dependencias
     *
     * @return regresa una lista de propiedades que seran utilizadas en la
     * presentacion
     */
    List<DependenceEntity> getItsListOfDependences();

    /**
     * En este metodo definimos la lista de los planes institucionales que seran
     * mostrados
     *
     * @return regresa la lista de planes institucionales que sea definida en la
     * clase
     */
    List<InstitutionalPlanEntity> getItsListOfInstitutionalPlans();

    List<InstitutionalPlanObjectiveEntity> getItsListOfObjectivesofPI();

    ObjectiveJoinLevelTreeviewDto getItsProgramSelected();

    /**
     * con este metodo se obtiene el nodo seleccionado del arbol de objetivos
     *
     * @return
     */
    TreeNode getItsSelectedNode();

    /**
     * nos sirve para obtener los datos del nodo seleccionado en el arbol de
     * fichas
     *
     * @return regresa un objeto (TreeNode) que se puede castear como
     * (DraftProjectTreeviewDto)
     */
    TreeNode getItsSelectedNodeToken();

    String getItsShortName();

    ObjectiveJoinLevelTreeviewDto getItsSubProgramSelected();

    /**
     * Este metodo obtiene el arbol de fichas que seran mostradas en la
     * presentación
     *
     * @return
     */
    TreeNode getItsTokenRoot();

    /**
     * este metodo obtiene el arbol de objetivos que sera mostrado en la
     * presentación
     *
     * @return
     */
    TreeNode getRoot();

    void init();

    String navigateDraftProject();

    /**
     * Este metodo se ejecuta cuando seleccionamos un nodo del objetivos y a su
     * vez nos muestra el arbol de fichas, el cual se obtiene por el encuadre de
     * plan estrategico y la programación
     *
     * @param event
     */
    void onNodeSelect(NodeSelectEvent event);

    /**
     *
     * @param event
     */
    void onNodeSelectTree(NodeSelectEvent event);

    /**
     *
     * @param event
     */
    void onNodeUnselect(NodeUnselectEvent event);

    void preparateEditProgramming();

    void restartTreeview();

    /**
     * Este metodo nos sirve para definir el Id de la dependencia seleccionada
     *
     * @param aDependenceId
     */
    void setADependenceId(Long aDependenceId);

    /**
     * este metodo nos sirve para definir el Id del plan institucional
     * seleccionado
     *
     * @param aObjectiveOfPI
     */
    void setAObjectiveOfPI(Long aObjectiveOfPI);

    void setAnObjectiveId(Long anObjectiveId);

    /**
     *
     * @param disableTreeOptionsEdit
     */
    void setDisableTreeOptionsEdit(Boolean disableTreeOptionsEdit);

    /**
     *
     * @param disableTreeOptionsNew
     */
    void setDisableTreeOptionsNew(Boolean disableTreeOptionsNew);

    /**
     * @param itsFatherDescription the itsFatherDescription to set
     */
    void setFatherDescription(String itsFatherDescription);

    void setItsAxisSelected(ObjectiveJoinLevelTreeviewDto itsAxisSelected);

    void setItsDisable(Boolean itsDisable);

    /**
     * aqui se define la lista de las dependencias que seran utlizadas
     *
     * @param itsListOfDependences
     */
    void setItsListOfDependences(List<DependenceEntity> itsListOfDependences);

    /**
     * aqui se define la lista de los planes institucionales a mostrar
     *
     * @param itsListOfInstitutionalPlans
     */
    void setItsListOfInstitutionalPlans(List<InstitutionalPlanEntity> itsListOfInstitutionalPlans);

    void setItsListOfObjectivesofPI(List<InstitutionalPlanObjectiveEntity> itsListOfObjectivesofPI);

    void setItsProgramSelected(ObjectiveJoinLevelTreeviewDto itsProgramSelected);

    /**
     * este metodo sirve para definir los datos del nodo seleccionado en la
     * presentacion
     *
     * @param itsSelectedNode
     */
    void setItsSelectedNode(TreeNode itsSelectedNode);

    /**
     * sirve para definir cual nodo ha sido seleccionado
     *
     * @param itsSelectedNodeToken
     */
    void setItsSelectedNodeToken(TreeNode itsSelectedNodeToken);

    void setItsShortName(String itsShortName);

    void setItsSubProgramSelected(ObjectiveJoinLevelTreeviewDto itsSubProgramSelected);

    Boolean getBtnSrvDelete();

    void setBtnSrvDelete(Boolean btnDelete);
    
}
