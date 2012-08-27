/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependenceDtoComparator
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.comparators;

import com.abs.siif.planning.dto.DepencenceDto;
import java.util.Comparator;

/**
 *
 * @author Israel Ruiz
 */
public class DependenceDtoComparator implements Comparator<DepencenceDto> {
 private static final DependenceDtoComparator instanceComp;
 static{
     instanceComp = new DependenceDtoComparator();
 }
    @Override
    public int compare(DepencenceDto o1, DepencenceDto o2) {
        return o1.getIdDependency().compareTo(o2.getIdDependency());
    }
    
    public static DependenceDtoComparator getInstance(){
        return instanceComp;
        
    }
   
    private DependenceDtoComparator(){}
}
