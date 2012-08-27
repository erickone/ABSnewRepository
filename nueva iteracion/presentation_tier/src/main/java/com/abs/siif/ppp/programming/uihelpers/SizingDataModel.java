/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.programming.dto.SizingDto;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Erick Leija
 */
public class SizingDataModel extends ListDataModel<SizingDto>
        implements SelectableDataModel<SizingDto> {

    public SizingDataModel() {
    }

    public SizingDataModel(List<SizingDto> aDataModel) {
        super(aDataModel);
    }

    @Override
    public Object getRowKey(SizingDto anEntity) {
        return anEntity.getItsSizingId();
    }

    @Override
    public SizingDto getRowData(String string) {

        Long myLong = Long.parseLong(string);
        List<SizingDto> myObjectExpense = (List<SizingDto>) getWrappedData();

        for (SizingDto myDTO : myObjectExpense) {
            if (myDTO.getItsSizingId().equals(myLong)) {
                return myDTO;
            }
        }
        return null;
    }
}
