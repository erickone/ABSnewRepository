/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  TreeNodeComponent
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.support.component;

import java.io.Serializable;
import java.util.Collection;
import org.primefaces.model.TreeNode;

/**
 *
 * @author abs70
 */
public interface TreeNodeComponent {

    TreeNode getItsTreeNode();
    
    public void setItsTreeNode(TreeNode aTreeNode);

    TreeNode getTheirSelectedNode();

    /**
     *
     * @param aCollection: Debe pasar los elementos de primer nivel aquellos que
     * no tienen padre
     * @param aRootName : Sera tomado como el promer node a mostrar
     */
    void initTreeNode(Collection<?> aCollection, String aRootName, 
            String aNamePropertyIdentity, Collection<String> aCompositeName,
            Class aConcreteClass,  String aNameProperyLevelInMainClass,
            String aNamePropertyLevel,String aNamePropertyIdentityLevel) throws NoSuchFieldException, 
            IllegalArgumentException, IllegalArgumentException, IllegalAccessException;

    void loadChilds(Collection<?> aCollection, TreeNode NodeFather, 
            String aNamePropertyIdentity, Collection<String> aCompositeName,
            Class aConcreteClass,String aNameProperyLevelInMainClass,
            String aNamePropertyLevel,String aNamePropertyIdentityLevel) throws NoSuchFieldException, 
            IllegalAccessException;

    void setTheirSelectedNode(TreeNode theirSelectedNode);
    
}
