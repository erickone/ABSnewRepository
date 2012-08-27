/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FuncClassifierEntityComparator
 *  Purpose:  Comparador para el ordenamiento del arbol de catalogo de 
 *            Clasificador Funcional.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.planning.comparators;

import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import java.util.Comparator;

/**
 *
 * @author FENIX-02
 */
public class FuncClassifierEntityComparator implements Comparator<FunctionalClassifierEntity>{

    public static final FuncClassifierEntityComparator instanceObjCom;
    static{
         instanceObjCom = new FuncClassifierEntityComparator();
    }
    
    @Override
    public int compare(FunctionalClassifierEntity o1, FunctionalClassifierEntity o2)
    {
        return o1.getFunctionalClassifierKey().compareTo(o2.getFunctionalClassifierKey());
    }

    public static FuncClassifierEntityComparator getInstance(){
        return instanceObjCom;
    }
    
    public FuncClassifierEntityComparator(){
        
    }
}
