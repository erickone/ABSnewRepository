/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import com.abs.siif.programming.management.AdministrativeClassifierManagement;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Erick Leija
 */
@Component("treeViewAdministrativeClassifier")
public class TreeViewAdministrativeClassifierUIHelper implements Serializable
{
     @Resource(name ="administrativeClassifierManagement")
    private AdministrativeClassifierManagement theirAdministrativeClassifierManagement;
    
   
   
    private TreeNode theirTreeNodeRoot;
    List<ObjectiveJoinLevelTreeviewDto> myDTOs= new ArrayList<ObjectiveJoinLevelTreeviewDto>();

    public void restartNodeTreeview() {
        theirTreeNodeRoot = null;
    }

    public TreeNode getNodeTreeview(Long aDependenceId) {
        if (theirTreeNodeRoot == null) {
            createTreeview(aDependenceId);
        }

        return theirTreeNodeRoot;
    }

    private void createTreeview(Long aDependenceId) 
    {
        
         // Crea el nivel raíz para la estructura jerarquica 
        String myRootName = "CLASIFICACION ADMINISTRATIVA";
        ObjectiveJoinLevelTreeviewDto myDto = new ObjectiveJoinLevelTreeviewDto(myRootName, null, null, null, null, null, (short)0, null, (short)0,null);


        ObjectiveJoinLevelTreeviewDto myRoot = new ObjectiveJoinLevelTreeviewDto("Root", null, null, null, null, null, (short)-1, null, (short)-1,null);
        theirTreeNodeRoot = new DefaultTreeNode(myRoot, null);
        TreeNode myRootNode = new DefaultTreeNode(myDto, theirTreeNodeRoot);

        // TODO: aqui se hace la llamada al metodo que nos devolvera la lista que contiene todos los objetivos de ultimo nivel
        // que estan relacionados con la dependencia que estamos manejando, además vendra cargada con toda la herencia ascendente de los
        // objetivos relacionados, esta lista nos sirve para construir nuestro arbol.
        List<DependenceEntity> myObjectivesToCast = (List<DependenceEntity>)theirAdministrativeClassifierManagement.getMyAncestryClassiFier(aDependenceId);
        myDTOs= new ArrayList<ObjectiveJoinLevelTreeviewDto>();
        ObjectiveJoinLevelTreeviewDto myWorkingDto;
         
        for(Object myObjectiveCast: myObjectivesToCast)
        {
            Object[] myObjectFields = (Object[]) myObjectiveCast;   
            myWorkingDto= new ObjectiveJoinLevelTreeviewDto
                    (((String)myObjectFields[1]), ((String)myObjectFields[2]), (Long)myObjectFields[0],
                    ((Long)myObjectFields[3]), ((String)myObjectFields[1]), ((String)myObjectFields[2]),
                    ((short) 1), ((Long)myObjectFields[5]),((short)1),((String)myObjectFields[4]));
            myDTOs.add(myWorkingDto);
        }
        if (myDTOs.size() > 0) {
            // Busca los nodos de primer nivel (donde objective.getFather==null)
            List<ObjectiveJoinLevelTreeviewDto> myRootNodesObjectives = getRootNodesObjectives(myDTOs);
            for (ObjectiveJoinLevelTreeviewDto myObjectiveFather : myRootNodesObjectives) {

               
                TreeNode myChildNode = new DefaultTreeNode(myObjectiveFather, myRootNode);
                // llamado al metodo recursivo para la carga de los nodos hijos
                // esto para cada nodo padre
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
    private List<ObjectiveJoinLevelTreeviewDto> getRootNodesObjectives(List<ObjectiveJoinLevelTreeviewDto> anObjectives) {
        List<ObjectiveJoinLevelTreeviewDto> myRootNodesObjectives = new ArrayList<ObjectiveJoinLevelTreeviewDto>();

        for (ObjectiveJoinLevelTreeviewDto myObjective : anObjectives) {
            if (myObjective.getItsFatherId() == null) {
                myRootNodesObjectives.add(myObjective);
            }
        }
        return myRootNodesObjectives;
    }

    /**
     * Metodo recursivo su responsabilidad es agregar a un nodo determinado,la
     * colección de hijos de un objetivo determinado
     *
     * @param tvNode Nodo al cual se agregara el nodo hijo
     * @param objectiveData Objetivo del cual se desea se agregen sus hijos a la
     * estructura jerarquica
     */
    private void addNodeToRoot(TreeNode tvNode, ObjectiveJoinLevelTreeviewDto objectiveData) {
        
        ComparatorFatherAndChild myComparator = new ComparatorFatherAndChild();
        List<ObjectiveJoinLevelTreeviewDto> myChildList = (List<ObjectiveJoinLevelTreeviewDto>) SearchList.findAllinList(myDTOs, objectiveData, myComparator);
        for (ObjectiveJoinLevelTreeviewDto myChild : myChildList) {
           
            TreeNode myChildNode = new DefaultTreeNode(myChild, tvNode);
            addNodeToRoot(myChildNode, myChild);
        }
    }    
    
}
