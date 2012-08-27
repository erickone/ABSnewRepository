package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.planning.comparators.ObjetiveEntityComparator;
import com.abs.siif.planning.dto.ObjectiveTreeviewDto;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.planning.management.ObjectiveManagement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Resource;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Component;

@Component("treeviewObjectiveComponent")
public class TreeviewObjectiveUIHelper {

    @Resource(name = "objectiveManagement")
    private ObjectiveManagement theirObjectiveManagement;
    private TreeNode theirTreeNodeRoot;

    public void restartNodeTreeview() {
        theirTreeNodeRoot = null;
    }

    public TreeNode getNodeTreeview(String myRootName) {
        //if (theirTreeNodeRoot == null) {
        theirTreeNodeRoot = null;
            createTreeview(myRootName);
        //}

        return theirTreeNodeRoot;
    }

    private void createTreeview(String myRootName) {

        // TODO: esta implementación debe cambiar por la administración en curso                   
        List<ObjectiveEntity> myObjectives = theirObjectiveManagement.GetAllObjectives();

        // Crea el nivel raíz para la estructura jerarquica 
//        String myRootName = "Administración 2012";
        ObjectiveTreeviewDto myDto = new ObjectiveTreeviewDto(myRootName);
        short myInitialLevel = 0;
        myDto.setItsObjectiveLevel(myInitialLevel);


        ObjectiveTreeviewDto myRoot = new ObjectiveTreeviewDto("Root");
        short myRootLevel = -1;
        myRoot.setItsObjectiveLevel(myRootLevel);

        theirTreeNodeRoot = new DefaultTreeNode(myRoot, null);
        TreeNode myRootNode = new DefaultTreeNode(myDto, theirTreeNodeRoot);
        myRootNode.setExpanded(true);
        if (myObjectives.size() > 0) {
            // Busca los nodos de primer nivel (donde objective.getFather==null)
            List<ObjectiveEntity> myRootNodesObjectives = getRootNodesObjectives(myObjectives);
            for (ObjectiveEntity myObjectiveFather : myRootNodesObjectives) {

                Long myFatherId = (myObjectiveFather.getFather() != null ? myObjectiveFather.getFather().getObjectiveId() : null);
                myObjectiveFather = ordenamientoTree(myObjectiveFather);
                myDto = new ObjectiveTreeviewDto(myObjectiveFather.getCompositeObjectiveKey(), myObjectiveFather.getObjectiveLevel().getObjectiveLevelDescription(),
                        myObjectiveFather.getObjectiveId(), myObjectiveFather.getObjectiveLevel().getObjectiveLevelId(), myObjectiveFather.getCompositeObjectiveKey(), myObjectiveFather.getObjectiveDefinition(),
                        myObjectiveFather.getObjectivePriority(), myFatherId, myObjectiveFather.getObjectiveLevel().getObjectiveLevel(),myObjectiveFather.getCompositeObjectiveKey());

                TreeNode myChildNode = new DefaultTreeNode(myDto, myRootNode);
                // llamado al metodo recursivo para la carga de los nodos hijos
                // esto para cada nodo padre
                
                myChildNode.setExpanded(true);
                addNodeToRoot(myChildNode, myObjectiveFather);
            }
        }

    }

    /**
     * Busca dentro de una la lista de objetivos cuales objetivos son de primer
     * nivel (donde objective.getFather==null)
     *
     * @param anObjectives lista de objetivos de donde se desea se obtengan los
     * nodos de primer nivel
     * @return Retorna una nueva lista con objetivos de primer nivel donde
     * (objective.getFather==null)
     */
    private List<ObjectiveEntity> getRootNodesObjectives(List<ObjectiveEntity> anObjectives) {
        Comparator comKeyObj = ObjetiveEntityComparator.getInstance();
        List<ObjectiveEntity> myRootNodesObjectives = new ArrayList<ObjectiveEntity>();

        for (ObjectiveEntity myObjective : anObjectives) {
            if (myObjective.getFather() == null) {
                myRootNodesObjectives.add(myObjective);
            }
        }        
        Collections.sort(myRootNodesObjectives, comKeyObj);
        return myRootNodesObjectives;
    }
    
    private ObjectiveEntity ordenamientoTree(ObjectiveEntity elementToSort){
        Comparator comKeyObj = ObjetiveEntityComparator.getInstance();
        ObjectiveEntity elementSort = elementToSort;        
        if (elementSort.getObjectiveChilds().size() > 0){             
            List<ObjectiveEntity> myRootNodesObjectivesSort = new ArrayList<ObjectiveEntity>();
            for (ObjectiveEntity myObjectiveToSort : elementSort.getObjectiveChilds()) {
                elementSort = ordenamientoTree(myObjectiveToSort);
                myRootNodesObjectivesSort.add(myObjectiveToSort);
            }
            if (myRootNodesObjectivesSort.size() > 1){
                elementSort = elementToSort;
                Collections.sort(myRootNodesObjectivesSort, comKeyObj);
                elementSort.setStrategicPlans(myRootNodesObjectivesSort);
            }
        }
        return elementSort;
    }

    /**
     * Metodo recursivo su responsabilidad es agregar a un nodo determinado,la
     * colección de hijos de un objetivo determinado
     *
     * @param tvNode Nodo al cual se agregara el nodo hijo
     * @param objectiveData Objetivo del cual se desea se agregen sus hijos a la
     * estructura jerarquica
     */
    private void addNodeToRoot(TreeNode tvNode, ObjectiveEntity objectiveData) {
        final String TYPE_NODE = "level";
        for (ObjectiveEntity myChild : objectiveData.getObjectiveChilds()) {
            String myKeyNode = TYPE_NODE + myChild.getObjectiveLevel().getObjectiveLevel();
            Long myFatherId = (myChild.getFather() != null ? myChild.getFather().getObjectiveId() : null);
            ObjectiveTreeviewDto myDto = new ObjectiveTreeviewDto(myChild.getCompositeObjectiveKey(), myChild.getObjectiveLevel().getObjectiveLevelDescription(),
                    myChild.getObjectiveId(), myChild.getObjectiveLevel().getObjectiveLevelId(), myChild.getCompositeObjectiveKey(),
                    myChild.getObjectiveDefinition(), myChild.getObjectivePriority(), myFatherId, myChild.getObjectiveLevel().getObjectiveLevel()
                    ,myChild.getCompositeObjectiveKey());
            TreeNode myChildNode = new DefaultTreeNode(myDto, tvNode);
             myChildNode.setExpanded(true);
            addNodeToRoot(myChildNode, myChild);
        }
    }
}
