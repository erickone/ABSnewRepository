/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.budget.dao.DestinationDao;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.dto.ValidationNameEnum;
import com.abs.siif.budget.entities.DestinationEntity;
import com.abs.siif.planning.comparators.DestinationExpenseComparator;
import com.abs.siif.support.SearchList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author jacob.flores
 */
@Component("destinationExpenseValidate")
@Scope("prototype")
public class DestinationExpenseValidate extends CompCeilingBudget {

    @Resource(name = "destinationDao")
    DestinationDao destinationDao;

    public DestinationExpenseValidate() {
        setNameValidator(ValidationNameEnum.DES_EXPENSE);
    }

    @Override
    public List<CellCeilingBudgetDto> executeValidation(List<List<CellCeilingBudgetDto>> toValidateSets) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CellCeilingBudgetDto> executeDataValidation(List<CellCeilingBudgetDto> toValidateSets) {
        
        List<CellCeilingBudgetDto> resultado = new ArrayList<CellCeilingBudgetDto>();
        List<DestinationEntity> catalog = destinationDao.findAll();
        DestinationEntity dummyEntity;
        for (CellCeilingBudgetDto ref : toValidateSets) {
            dummyEntity = new DestinationEntity();
            String sdata = String.valueOf(Double.valueOf(ref.getDataElement()).longValue());
            
            if (sdata.length() == 1){
                sdata = getMessage("ppp.techo.validationdestinationexpense.paddingcharacter") + sdata;
            }
            ref.setDataElement(sdata);
            dummyEntity.setDestinationKey(sdata);

            Object data = SearchList.findObjectListCom(catalog,
                    dummyEntity,
                    DestinationExpenseComparator.getInstance());
            if (data == null) {
                ref.setMesageError(getMessage("ppp.ceiling.validator.errorMessage"));
                resultado.add(ref);
            }

        }
        return resultado;
    }
}
