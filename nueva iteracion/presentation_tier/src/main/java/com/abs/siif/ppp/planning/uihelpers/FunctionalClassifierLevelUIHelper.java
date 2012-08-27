/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierLevelUIHelper
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
import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import com.abs.siif.planning.exception.FuncClassifLevelBussinessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author FENIX-02
 */
@Component("functionalClassifierLevelUIHelper")
public class FunctionalClassifierLevelUIHelper extends SIIFControllerBase{

    public static void validateFCLevelData(FunctionalLevelClassifier aCurrentFCLevel)   {
        
        if (!isNumber(aCurrentFCLevel.getFunctionalLevelClassifierYear().toString())) {
            throw new FuncClassifLevelBussinessException("ppp.progr.yearNotAsigned");
        }
        if (aCurrentFCLevel.getFunctionalLevelClassifierKey().trim().toString().equals("")) { 
            throw new FuncClassifLevelBussinessException("ppp.planning.excpKeyEmpty");
        }        
        if (aCurrentFCLevel.getFunctionalLevelClassifierDescription().trim().toString().equals("")) {
            throw new FuncClassifLevelBussinessException("ppp.planning.excpDescEmpty");
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
    
    public static List<FunctionalLevelClassifier> mapperIdentities(FunctionalLevelClassifier[] aSelectedFuncClassifiers) {
        List<FunctionalLevelClassifier> myIdentities = new ArrayList<FunctionalLevelClassifier>();
        myIdentities.addAll(Arrays.asList(aSelectedFuncClassifiers));
        return myIdentities;
    }

}
