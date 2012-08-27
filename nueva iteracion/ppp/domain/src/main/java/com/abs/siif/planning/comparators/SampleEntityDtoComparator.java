/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  SampleEntityDtoComparator
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

import com.abs.siif.common.dto.SampleEntityDto;
import java.util.Comparator;

/**
 * Esta clase se encarga de proveer el criterio de comparación 
 * para el entity comparable
 * 
 * @author Francisco Luna
 */
public class SampleEntityDtoComparator implements  Comparator<SampleEntityDto>{
    
    public static final SampleEntityDtoComparator instanceComp;
    static{
         instanceComp = new SampleEntityDtoComparator();
    }

    @Override
    public int compare(SampleEntityDto o1, SampleEntityDto o2) {
        return o1.getItsGenericItemId().compareTo(o2.getItsGenericItemId());
    }
    
    public static SampleEntityDtoComparator getInstance(){
        return instanceComp;
    }
    
    public SampleEntityDtoComparator(){        
    }  
    
}
