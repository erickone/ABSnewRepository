/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependenceKeyComparator
 *  Purpose:  Dependence Key comparator
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.comparators;

import com.abs.siif.planning.entities.DependenceEntity;
import java.util.Comparator;

/**
 * Esta clase se encarga de proveer el criterio de comparación 
 * para la clave de la depencia, se implementa como un singleton
 * @author Israel Ruiz
 */

public class DependenceKeyComparator implements  Comparator<DependenceEntity> {
 
    public static final DependenceKeyComparator instanceDepCom;
    static{
         instanceDepCom = new DependenceKeyComparator();
    }
    @Override
    public int compare(DependenceEntity o1, DependenceEntity o2) {
        return o1.getDependenceKey().compareTo(o2.getDependenceKey());
    }
    
    public static DependenceKeyComparator getInstance(){
        return instanceDepCom;
    }
    
    private DependenceKeyComparator(){
        
    }
    
}
