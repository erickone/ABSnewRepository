/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ReglClassifLevelComparator
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

import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import java.util.Comparator;

/**
 *
 * @author FENIX-02
 */
public class ReglClassifLevelComparator implements Comparator<RegionalLevelClassifierEntity>{

    @Override
    public int compare(RegionalLevelClassifierEntity o1, RegionalLevelClassifierEntity o2)
    {
        return Integer.compare(o1.getRegionalLevelClassifierLevel(), o2.getRegionalLevelClassifierLevel());
    }

}
