/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  BudgetKeyValidate
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.base.common.ResourceBundleMassage;
import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.budget.dto.ValidationNameEnum;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.entities.CeillingBudgetEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Israel Ruiz
 */
@Component("budgetKeyValidate")
@Scope("prototype")
public class BudgetKeyValidate extends CompCeilingBudget {

    public BudgetKeyValidate() {
        setNameValidator(ValidationNameEnum.VALIDATION_UNIQUE_KEY);
    }

    @Override
    public List<CellCeilingBudgetDto> executeValidation(List<List<CellCeilingBudgetDto>> toValidateSets) {
        setNameValidator(ValidationNameEnum.VALIDATION_UNIQUE_KEY);
        List<CellCeilingBudgetDto> resultado = new ArrayList<CellCeilingBudgetDto>();
        Set<String> generatedBudgetKeys = new HashSet<String>();
        int myRowSize = toValidateSets.get(0).size() - 1;

        CellCeilingBudgetDto result = new CellCeilingBudgetDto();
                
        List<CellCeilingBudgetDto> myListOfCeillingBudgetToReturn = 
                    new ArrayList<CellCeilingBudgetDto>();
        finalizePrincFor:
        for (int myCounter = 0; myCounter <= myRowSize; myCounter++) {
            String myGeneratedKey = "";
            CellCeilingBudgetDto myCeilBudgetDto = null;
            result = new CellCeilingBudgetDto();
            for (List<CellCeilingBudgetDto> myListCeilBudgetDto : toValidateSets) {
                myCeilBudgetDto = myListCeilBudgetDto.get(myCounter);
                myGeneratedKey += myCeilBudgetDto.getDataElement().toString() + "|";

            }
            if (myCeilBudgetDto != null && !generatedBudgetKeys.add(myGeneratedKey)) {
                myCeilBudgetDto.setMesageError(ResourceBundleMassage.getString("ppp.ceiling.validator.errorMessage"));
                resultado.add(myCeilBudgetDto);
                break finalizePrincFor;
            } else if(myCeilBudgetDto != null){                
                
                CeillingBudgetEntity myCeillingBudget = new CeillingBudgetEntity();
                ArrayList<String> myValueToSet = getClaveCarga(myGeneratedKey);
                myCeillingBudget.setClaveCarga(myValueToSet.get(0));
                myCeillingBudget.setMonto(Long.parseLong(myValueToSet.get(1)));
                
                result.setRegisterId(myCeilBudgetDto.getRegisterId());
                result.setObjentity(myCeillingBudget);
                
                myListOfCeillingBudgetToReturn.add(result);
            }

        }
        if(myListOfCeillingBudgetToReturn != null &&
                !myListOfCeillingBudgetToReturn.isEmpty()){
            SIIFContextBase.setParameterSession(
                    SessionKeyEnum.LIST_CEILLING_BUDGET, 
                    myListOfCeillingBudgetToReturn);
        }
        return resultado;
    }

    private ArrayList<String> getClaveCarga(String aGeneratedKey) {
        ArrayList<String> stringToReturn = new ArrayList<String>();
        String myResult[];
        String myLoadKey = "";
        String myMount = "";
        myResult = aGeneratedKey.split("\\|");
        int mySize = myResult.length - 1;

        for (int myCount = 0; myCount < mySize; myCount++) {
            myLoadKey += myResult[myCount].replaceAll(" ", "") + " ";
        }

        //Se añade la llave
        stringToReturn.add(myLoadKey.trim());
        //se añade el monto
        myMount = myResult[mySize];
        Double d = Double.parseDouble(myMount);
        stringToReturn.add(""+d.longValue());
        return stringToReturn;
    }

    @Override
    public List<CellCeilingBudgetDto> executeDataValidation(List<CellCeilingBudgetDto> toValidateSets) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
