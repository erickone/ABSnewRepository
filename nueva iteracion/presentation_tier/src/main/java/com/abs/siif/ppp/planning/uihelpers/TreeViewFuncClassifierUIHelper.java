/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  TreeViewFuncClassifierUIHelper
 *  Purpose:  This is the UI Helper for the FunctionalClassifierController.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.planning.comparators.FuncClassifierEntityComparator;
import com.abs.siif.planning.dto.FunctionalClassifierDto;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import com.abs.siif.planning.management.FunctionalClassifierManagement;
import java.util.*;
import javax.annotation.Resource;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Component;

/**
 *
 * @author FENIX-02
 */
@Component("treeViewFuncClassifierUIHelper")
public class TreeViewFuncClassifierUIHelper {

    @Resource(name = "functionalClassifierManagement")
    private FunctionalClassifierManagement itsFunctionalClassifierManagement;
    
    private TreeNode theirTreeNodeRoot;

    public void restartNodeTreeview() {
        theirTreeNodeRoot = null;
    }

    public TreeNode getNodeTreeview(String myRootName) {
        if (theirTreeNodeRoot == null) {
            createTreeview(myRootName);
        }
        return theirTreeNodeRoot;
    }
    
     private void createTreeview(String myRootName) {
        List<FunctionalClassifierEntity> myFuncClassifiers = 
                itsFunctionalClassifierManagement.getAllFuncClassifiers();

        // Crea el nivel raíz para la estructura jerarquica 
        FunctionalClassifierDto myDto = new FunctionalClassifierDto(myRootName);
        short myInitialLevel = 0;
        myDto.setItsFuncClassifierLevel(myInitialLevel);


        FunctionalClassifierDto myRoot = new FunctionalClassifierDto("Root");
        short myRootLevel = -1;
        myRoot.setItsFuncClassifierLevel(myRootLevel);

        theirTreeNodeRoot = new DefaultTreeNode(myRoot, null);
        TreeNode myRootNode = new DefaultTreeNode(myDto, theirTreeNodeRoot);
        myRootNode.setExpanded(true);
        if (myFuncClassifiers.size() > 0) {
            // Busca los nodos de primer nivel (donde funcClassifier.getFather==null)
            List<FunctionalClassifierEntity> myRootNodesFuncClassifier = getRootNodesFuncClassifier(myFuncClassifiers);
            for (FunctionalClassifierEntity myFuncClassfierFather : myRootNodesFuncClassifier) {

                Long myFatherId = (myFuncClassfierFather.getFather() != null ? myFuncClassfierFather.getFather().getFunctionalClassifierId() : null);
                myFuncClassfierFather = ordenamientoTree(myFuncClassfierFather);
                myDto = new FunctionalClassifierDto(
                        myFuncClassfierFather.getCompositeFuncClassifierKey(), 
                        myFuncClassfierFather.getFunctionalLevelClassifier().getFunctionalLevelClassifierDescription(),
                        myFuncClassfierFather.getFunctionalClassifierId(), 
                        myFuncClassfierFather.getFunctionalLevelClassifier().getFunctionalLevelClassifierId(), 
                        myFuncClassfierFather.getFunctionalClassifierDescription(), 
                        myFuncClassfierFather.getFunctionalClassifierDefinitionCONAC(),
                        myFatherId, 
                        myFuncClassfierFather.getFunctionalLevelClassifier().getFunctionalLevelClassifier(),
                        myFuncClassfierFather.getFunctionalClassifierKey(),
                        myFuncClassfierFather.getFuntionalClassifierObjectives());

                TreeNode myChildNode = new DefaultTreeNode( myDto, myRootNode);
                // llamado al metodo recursivo para la carga de los nodos hijos
                // esto para cada nodo padre
                myChildNode.setExpanded(true);
                addNodeToRoot(myChildNode, myFuncClassfierFather);
            }
        }
    }

      /**
     * Busca dentro de una la lista de clasificadores cuales son de primer
     * nivel (donde clasificador.getFather==null)
     *
     * @param aFuncClassifiers lista de clasificadores de donde se desea se obtengan los
     * nodos de primer nivel
     * @return Retorna una nueva lista con clasificadores de primer nivel donde
     * (clasificador.getFather==null)
     */
    private List<FunctionalClassifierEntity> getRootNodesFuncClassifier(List<FunctionalClassifierEntity> aFuncClassifiers) {
        Comparator comKeyObj = FuncClassifierEntityComparator.getInstance();
        List<FunctionalClassifierEntity> myRootNodesFuncClassifier = new ArrayList<FunctionalClassifierEntity>();

        for (FunctionalClassifierEntity myObjective : aFuncClassifiers) {
            if (myObjective.getFather() == null) {
                myRootNodesFuncClassifier.add(myObjective);
            }
        }
        Collections.sort(myRootNodesFuncClassifier, comKeyObj);
        return myRootNodesFuncClassifier;
    }
    
    private FunctionalClassifierEntity ordenamientoTree(FunctionalClassifierEntity elementToSort){
        //Comparator comKeyObj = FuncClassifierEntityComparator.getInstance();
        FunctionalClassifierEntity elementSort = elementToSort;        
        if (elementSort.getFunctionalClassifierChilds().size() > 0){             
            TreeSet<FunctionalClassifierEntity> myChilds = new TreeSet<FunctionalClassifierEntity>(new FuncClassifierEntityComparator());
            for (FunctionalClassifierEntity myObjectiveToSort : elementSort.getFunctionalClassifierChilds()) {
                elementSort = ordenamientoTree(myObjectiveToSort);
                    myChilds.add(myObjectiveToSort);
            }
            if (myChilds.size() > 1){
                elementSort = elementToSort;
                elementSort.getFunctionalClassifierChilds().clear();
                elementSort.setFunctionalClassifierChilds(myChilds);
            }
        }
        return elementSort;
    }
    
    /**
     * Metodo recursivo su responsabilidad es agregar a un nodo determinado,la
     * colección de hijos de un clasificador determinado
     *
     * @param tvNode Nodo al cual se agregara el nodo hijo
     * @param aFuncClassifierData clasificador del cual se desea se agregen 
     * sus hijos a la estructura jerarquica
     */
    private void addNodeToRoot(TreeNode tvNode, FunctionalClassifierEntity aFuncClassifierData) {
        final String TYPE_NODE = "level";
        for (FunctionalClassifierEntity myChild : aFuncClassifierData.getFunctionalClassifierChilds()) {
            //Este IF se introdujo debido a que se decidio con el equipo de analisis que solo 
            //se deben mostrar elementos que correspondan al año con el que se ingrerso al sistema.
            if (myChild.getFunctionalLevelClassifier().getFunctionalLevelClassifierYear() == 
                    Integer.parseInt(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString())){
                String myKeyNode = TYPE_NODE + myChild.getFunctionalLevelClassifier().getFunctionalLevelClassifier();
                Long myFatherId = (myChild.getFather() != null ? myChild.getFather().getFunctionalClassifierId() : null);
                FunctionalClassifierDto myDto = new FunctionalClassifierDto(
                        myChild.getCompositeFuncClassifierKey(), 
                        myChild.getFunctionalLevelClassifier().getFunctionalLevelClassifierDescription(),
                        myChild.getFunctionalClassifierId(), 
                        myChild.getFunctionalLevelClassifier().getFunctionalLevelClassifierId(), 
                        myChild.getFunctionalClassifierDescription(),
                        myChild.getFunctionalClassifierDefinitionCONAC(), 
                        myFatherId, 
                        myChild.getFunctionalLevelClassifier().getFunctionalLevelClassifier(),
                        myChild.getFunctionalClassifierKey(),
                        myChild.getFuntionalClassifierObjectives());
                TreeNode myChildNode = new DefaultTreeNode(myDto, tvNode);
                myChildNode.setExpanded(true);
                addNodeToRoot(myChildNode, myChild);
            }
        }
    }
}
