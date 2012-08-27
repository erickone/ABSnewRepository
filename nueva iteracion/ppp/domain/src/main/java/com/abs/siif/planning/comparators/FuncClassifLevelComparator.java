/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FuncClassifLevelComparator
 *  Purpose:  Auxiliar comparator for the functional classifier level.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.planning.comparators;

import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import java.util.Comparator;

/**
 *
 * @author FENIX-02
 */
public class FuncClassifLevelComparator implements Comparator<FunctionalLevelClassifier>{

    @Override
    public int compare(FunctionalLevelClassifier o1, FunctionalLevelClassifier o2)
    {
        return Integer.compare(o1.getFunctionalLevelClassifier(), o2.getFunctionalLevelClassifier());
    }

}
