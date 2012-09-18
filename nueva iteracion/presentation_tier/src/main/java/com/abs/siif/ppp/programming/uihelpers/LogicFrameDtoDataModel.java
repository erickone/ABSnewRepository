/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  LogicFrameDtoDataModel
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.ppp.programming.dto.LogicFrameDto;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Erick Leija
 */
public class LogicFrameDtoDataModel extends ListDataModel<LogicFrameDto>
        implements SelectableDataModel<LogicFrameDto> {

    public LogicFrameDtoDataModel() {
    }

    public LogicFrameDtoDataModel(List<LogicFrameDto> aDataModel) {
        super(aDataModel);
    }

    @Override
    public Object getRowKey(LogicFrameDto anEntity) {
        return anEntity.getItsLogicFrameId();
    }

    @Override
    public LogicFrameDto getRowData(String string) {

        Long myLong = Long.parseLong(string);
        
        List<LogicFrameDto> myObjectExpense = (List<LogicFrameDto>) getWrappedData();

        for (LogicFrameDto myDTO : myObjectExpense) {
            if (myDTO.getItsLogicFrameId().equals(myLong)) {
                return myDTO;
            }
        }
        return null;
    }
}