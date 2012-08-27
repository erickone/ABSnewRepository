/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingBudgetKeyDataModel
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.uihelpers;

import com.abs.siif.budget.dto.CeilingConfigurationDto;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author FENIX-02
 */
public class CeilingBudgetKeyDataModel extends ListDataModel<CeilingConfigurationDto>
        implements SelectableDataModel<CeilingConfigurationDto>{

    public CeilingBudgetKeyDataModel()
    {
    }
    
    public CeilingBudgetKeyDataModel(List<CeilingConfigurationDto> aDataModel) {
        super(aDataModel);
    }
    
    @Override
    public Object getRowKey(CeilingConfigurationDto anEntity)
    {
        return anEntity.getCeilingConfigId();
    }

    @Override
    public CeilingConfigurationDto getRowData(String aString)
    {
        Long myLong = Long.parseLong(aString);
        List<CeilingConfigurationDto> myObjectExpense = (List<CeilingConfigurationDto>) getWrappedData();
        
        for(CeilingConfigurationDto myDTO : myObjectExpense) {  
            if(myDTO.getCeilingConfigId().equals(myLong))  
                return myDTO;  
        }  
        return null;
    }

}
