/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  TreeNodeComponent
 *  Purpose:  [Elemento de soporte para la creación de estructuras jerarquicas ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.support.component;

import com.abs.siif.common.dto.TreeNodeDto;
import java.lang.reflect.Field;
import java.util.Collection;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Component("treeNodeComponent") 

public class TreeNodeComponentImpl
        implements TreeNodeComponent {

    private TreeNode itsTreeNode;
    private TreeNode theirSelectedNode;

    /**
     *
     * @param aCollection: Debe pasar los elementos de primer nivel aquellos que
     * no tienen padre
     * @param aRootName : Sera tomado como el promer node a mostrar
     */
    @Override
    public void initTreeNode(Collection<?> aCollection,
            String aRootName, String aNamePropertyIdentity,
            Collection<String> aCompositeName, Class aConcreteClass,
            String aNameProperyLevelInMainClass,
            String aNamePropertyLevel, String aNamePropertyIdentityLevel) throws NoSuchFieldException, IllegalArgumentException, IllegalArgumentException, IllegalAccessException {
        //Creación del elemento raiz del arbol elemento hidden

        if (itsTreeNode == null) {

            TreeNodeDto myNodeDto = new TreeNodeDto(-1L,
                    -1L, "Root", Boolean.TRUE, 0, -1L);

            itsTreeNode = new DefaultTreeNode(myNodeDto, null);
            TreeNodeDto myRootNodeDto = new TreeNodeDto(-1L,
                    -1L, aRootName, Boolean.TRUE, 0, -1L);

            TreeNode myRootNode = new DefaultTreeNode(myRootNodeDto, itsTreeNode);
            myRootNode.setExpanded(true);

            createTreeNode(aCollection, myRootNode,
                    aNamePropertyIdentity, aCompositeName, aConcreteClass,
                    aNameProperyLevelInMainClass, aNamePropertyLevel, aNamePropertyIdentityLevel);
        }

    }

    private void createTreeNode(Collection<?> aCollection,
            TreeNode aRootNode, String aNamePropertyIdentity,
            Collection<String> aCompositeName, Class aConcreteClass,
            String aNameProperyLevelInMainClass,
            String aNamePropertyLevel, String aNamePropertyIdentityLevel) throws NoSuchFieldException, IllegalAccessException {
        for (Object myNode : aCollection) {
            Field myFieldIdentity = aConcreteClass.getDeclaredField(aNamePropertyIdentity);
            myFieldIdentity.setAccessible(true);

            Long myIdentity = Long.valueOf(myFieldIdentity.get(myNode).toString());
            String myCompositeName = "";

            Field myFieldLevelInMainClass = aConcreteClass.getDeclaredField(aNameProperyLevelInMainClass);
            myFieldLevelInMainClass.setAccessible(true);

            Object myLevel = myFieldLevelInMainClass.get(myNode);
            Class myLevelClass = myLevel.getClass();

            Field myFieldLevel = myLevelClass.getDeclaredField(aNamePropertyLevel);
            myFieldLevel.setAccessible(true);

            Field myFieldLevelId = myLevelClass.getDeclaredField(aNamePropertyIdentityLevel);
            myFieldLevelId.setAccessible(true);


            int myLevelValue = new Integer(myFieldLevel.get(myLevel).toString());
            Long myLevelId = Long.valueOf(myFieldLevelId.get(myLevel).toString());

            for (String myLabel : aCompositeName) {
                Field myField = aConcreteClass.getDeclaredField(myLabel);
                myField.setAccessible(true);
                myCompositeName = myCompositeName + " " + myField.get(myNode).toString();
            }

            if (aCollection.size() > 0) {
                TreeNodeDto myFather = (TreeNodeDto) aRootNode.getData();
                TreeNodeDto myDto = new TreeNodeDto(myFather.getItsIdentity(),
                        myIdentity, myCompositeName, false, myLevelValue, myLevelId);
                TreeNode myChild = new DefaultTreeNode(myDto, aRootNode);

               
                //Dummy para simular el expand de los nodos
                TreeNodeDto myDummyDto = new TreeNodeDto(
                        myFather.getItsIdentity(), -1L,
                        "", false, myLevelValue, myLevelId);
                TreeNode myDummy = new DefaultTreeNode(myDummyDto, myChild);
            }
        }
    }

    @Override
    public TreeNode getItsTreeNode() {
        return itsTreeNode;
    }

    @Override
    public TreeNode getTheirSelectedNode() {
        return theirSelectedNode;
    }

    @Override
    public void setTheirSelectedNode(TreeNode theirSelectedNode) {
        this.theirSelectedNode = theirSelectedNode;
    }

    @Override
    public void loadChilds(Collection<?> aCollection,
            TreeNode NodeFather, String aNamePropertyIdentity,
            Collection<String> aCompositeName, Class aConcreteClass,
            String aNameProperyLevelInMainClass,
            String aNamePropertyLevel, String aNamePropertyIdentityLevel) throws NoSuchFieldException, IllegalAccessException {


        NodeFather.getChildren().clear();
        createTreeNode(
                aCollection, NodeFather,
                aNamePropertyIdentity, aCompositeName, aConcreteClass,
                aNameProperyLevelInMainClass, aNamePropertyLevel, aNamePropertyIdentityLevel);

    }

    @Override
    public void setItsTreeNode(TreeNode aTreeNode) {
        this.itsTreeNode = aTreeNode;
    }
}
