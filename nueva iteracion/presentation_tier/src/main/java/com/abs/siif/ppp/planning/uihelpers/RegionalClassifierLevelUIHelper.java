/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalClassifierLevelUIHelper
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.planning.exception.RegionalClassifierLevelBusinessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public class RegionalClassifierLevelUIHelper extends SIIFControllerBase{

    public static void validateRCLevelData(RegionalLevelClassifierEntity aCurrentRCLevel)   {
        
        if (!isNumber(aCurrentRCLevel.getRegionalLevelClassifierYear().toString())) {
            throw new RegionalClassifierLevelBusinessException("ppp.progr.yearNotAsigned");
        }
        if (aCurrentRCLevel.getRegionalLevelClassifierKey().trim().toString().equals("")) { 
            throw new RegionalClassifierLevelBusinessException("ppp.planning.excpKeyEmpty");
        }        
        if (aCurrentRCLevel.getRegionalLevelClassifierDescription().trim().toString().equals("")) {
            throw new RegionalClassifierLevelBusinessException("ppp.planning.excpDescEmpty");
        }
    }
    
    private static boolean isNumber(String cad) {
        //Checa que cada caracter sea un numero
        for(int i = 0; i<cad.length(); i++)
            if( !Character.isDigit(cad.charAt(i)))
                return false;
        //Checa que el año tenga 4 digitos
        if(cad.length() != 4)
            return false;
              
        return true;
    }
    
    public static List<RegionalLevelClassifierEntity> mapperIdentities(RegionalLevelClassifierEntity[] aSelectedFuncClassifiers) {
        List<RegionalLevelClassifierEntity> myIdentities = new ArrayList<RegionalLevelClassifierEntity>();
        myIdentities.addAll(Arrays.asList(aSelectedFuncClassifiers));
        return myIdentities;
    }
}
