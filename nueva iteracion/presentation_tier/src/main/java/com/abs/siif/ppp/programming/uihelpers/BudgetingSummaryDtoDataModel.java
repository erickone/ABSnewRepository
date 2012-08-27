/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.budget.dto.BudgetingSummaryDto;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Erick Leija este data model nos auxilia en las operaciones del data
 * table
 */
public class BudgetingSummaryDtoDataModel extends ListDataModel<BudgetingSummaryDto>
        implements SelectableDataModel<BudgetingSummaryDto> {

    public BudgetingSummaryDtoDataModel() {
    }

    public BudgetingSummaryDtoDataModel(List<BudgetingSummaryDto> aDataModel) {
        super(aDataModel);
    }

    @Override
    public Object getRowKey(BudgetingSummaryDto anEntity) {
        return anEntity.getItsBudgetingSummaryId();
    }

    @Override
    public BudgetingSummaryDto getRowData(String string) {
        BudgetingSummaryDto myDTO = null;
        if (string != null && !string.isEmpty()) {
            Long myLong = Long.parseLong(string);

            List<BudgetingSummaryDto> myObjectExpense = (List<BudgetingSummaryDto>) getWrappedData();

            for (BudgetingSummaryDto myDTOL : myObjectExpense) {
                if (myDTOL.getItsBudgetingSummaryId().equals(myLong)) {
                    myDTO =  myDTOL;
                }
            }
        }
        return myDTO;
    }
}
