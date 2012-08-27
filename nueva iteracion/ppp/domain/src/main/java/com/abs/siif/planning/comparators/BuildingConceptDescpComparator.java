/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BuildingConceptDescpComparator
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

import com.abs.siif.programming.entities.BuildingConceptEntity;
import java.util.Comparator;

/**
 *
 * @author Israel Ruiz
 */
public class BuildingConceptDescpComparator implements  Comparator<BuildingConceptEntity> {
 
    public static final BuildingConceptDescpComparator instanceDepCom;
    static{
         instanceDepCom = new BuildingConceptDescpComparator();
    }
    @Override
    public int compare(BuildingConceptEntity o1, BuildingConceptEntity o2) {
        return o1.getBuildingConceptDescription().compareTo(
                o2.getBuildingConceptDescription());
    }
    
    public static BuildingConceptDescpComparator getInstance(){
        return instanceDepCom;
    }
    
    private BuildingConceptDescpComparator(){
        
    }
    
}

