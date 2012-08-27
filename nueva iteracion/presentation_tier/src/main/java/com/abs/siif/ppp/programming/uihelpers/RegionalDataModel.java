/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  regionalDatamodel
 *  Purpose:  [ Este Data Model es el encargado de manejar las listas
 *  del clasificador Regional]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.programming.dto.RegionalClassifierPEDDto;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Erick Leija
 */
public class RegionalDataModel extends ListDataModel<RegionalClassifierPEDDto>
        implements SelectableDataModel<RegionalClassifierPEDDto> {

    public RegionalDataModel() {
    }

    public RegionalDataModel(List<RegionalClassifierPEDDto> aDataModel) {
        super(aDataModel);
    }

    @Override
    public Object getRowKey(RegionalClassifierPEDDto anEntity) {
        return anEntity.getRegionalClassifierId();
    }

    @Override
    public RegionalClassifierPEDDto getRowData(String string) {
        Long myLong = Long.parseLong(string);
        List<RegionalClassifierPEDDto> myObjectExpense = (List<RegionalClassifierPEDDto>) getWrappedData();

        for (RegionalClassifierPEDDto myDTO : myObjectExpense) {
            if (myDTO.getRegionalClassifierId().equals(myLong)) {
                return myDTO;
            }
        }
        return null;
    }
}
