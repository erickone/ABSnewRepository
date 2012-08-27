/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  EncuadreDataModel
 *  Purpose:   Helper class for data table in EncuadreController.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.planning.dto.DepencenceDto;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author FENIX-02
 */
public class DepToObjLinkDataModel extends ListDataModel<DepencenceDto>
        implements SelectableDataModel<DepencenceDto>{

    public DepToObjLinkDataModel()
    {
    }

    public DepToObjLinkDataModel(List<DepencenceDto> aDataModel) {
        super(aDataModel);
    }
    
    @Override
    public Object getRowKey(DepencenceDto anEntity)
    {
        return anEntity.getIdDependency();
    }

    @Override
    public DepencenceDto getRowData(String aString)
    {
        Long myLong = Long.parseLong(aString);
        List<DepencenceDto> myObjectExpense = (List<DepencenceDto>) getWrappedData();
        
        for(DepencenceDto myDTO : myObjectExpense) {  
            if(myDTO.getIdDependency().equals(myLong))  
                return myDTO;  
        }  
        return null;
    }

}
