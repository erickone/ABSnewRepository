/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ObservationDataModel
 *  Purpose:  DataTable helper for the Observations catalog related to 
 *              InvPreFile.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.programming.dto.ObservationDto;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author FENIX-02
 */
public class ObservationDataModel extends ListDataModel<ObservationDto>
        implements Serializable, SelectableDataModel<ObservationDto>{

    public ObservationDataModel() {
    }

    public ObservationDataModel(List<ObservationDto> aDataModel) {
        super(aDataModel);
    }
    
    @Override
    public Object getRowKey(ObservationDto aDto)
    {
        return aDto.getIdprefichaobservacion();
    }

    @Override
    public ObservationDto getRowData(String rowKey)
    {
        Integer myInteger = Integer.parseInt(rowKey);
        
        List<ObservationDto> myObjectExpense = (List<ObservationDto>) getWrappedData();

        for (ObservationDto myDTO : myObjectExpense) {
            if (myInteger.equals(myDTO.getIdprefichaobservacion())) {
                return myDTO;
            }
        }
        return null;
    }
    
}
