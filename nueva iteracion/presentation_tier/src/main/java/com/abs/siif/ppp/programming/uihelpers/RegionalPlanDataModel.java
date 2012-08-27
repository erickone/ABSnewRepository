/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalPlanDataModel
 *  Purpose:  [ este es un data model, para manejar los registros de plan 
 *  Regional Asociados a un Anteproyecto]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.programming.dto.RegionalPlanDto;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Erick Leija
 */
public class RegionalPlanDataModel extends ListDataModel<RegionalPlanDto>
        implements SelectableDataModel<RegionalPlanDto> {

    public RegionalPlanDataModel() {
    }

    public RegionalPlanDataModel(List<RegionalPlanDto> aDataModel) {
        super(aDataModel);
    }

    @Override
    public Object getRowKey(RegionalPlanDto anEntity) {
        return anEntity.getRegionalPlanId();
    }

    @Override
    public RegionalPlanDto getRowData(String string) {
        Long myLong = Long.parseLong(string);
        List<RegionalPlanDto> myObjectExpense = (List<RegionalPlanDto>) getWrappedData();

        for (RegionalPlanDto myDTO : myObjectExpense) {
            if (myDTO.getRegionalPlanId().equals(myLong)) {
                return myDTO;
            }
        }
        return null;
    }
}
