/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.budget.dto.BudgetingKeysDto;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Erick Leija
 */
public class BudgetingKeysDtoDataModel extends ListDataModel<BudgetingKeysDto>
        implements SelectableDataModel<BudgetingKeysDto> {

    public BudgetingKeysDtoDataModel() {
    }

    public BudgetingKeysDtoDataModel(List<BudgetingKeysDto> aDataModel) {
        super(aDataModel);
    }

    @Override
    public Object getRowKey(BudgetingKeysDto anEntity) {
        return anEntity.getItsBudgetingKeyId();
    }

    @Override
    public BudgetingKeysDto getRowData(String string) {
        Long myLong = Long.parseLong(string);
        
        List<BudgetingKeysDto> myObjectExpense = (List<BudgetingKeysDto>) getWrappedData();

        for (BudgetingKeysDto myDTO : myObjectExpense) {
            if (myDTO.getItsBudgetingKeyId().equals(myLong)) {
                return myDTO;
            }
        }
        return null;
    }
}
