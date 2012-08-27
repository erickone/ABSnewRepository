/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.budget.dto.ValidationNameEnum;
import com.abs.siif.base.common.ResourceBundleMassage;
import com.abs.siif.budget.dao.ObjectExpenseDao;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.comparators.ObjectExpenseKeyComparator;
import com.abs.siif.support.SearchList;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author jacob.flores
 */
@Component("objectExpenseKeyValidator")
@Scope("prototype")
public class ObjectExpenseKeyValidator extends CompCeilingBudget {

    @Resource(name = "objectExpenseDao")
    ObjectExpenseDao objectExpenseDao;

    public ObjectExpenseKeyValidator() {
        setNameValidator(ValidationNameEnum.OBJECT_EXPENCE);
    }

    @Override
    public List<CellCeilingBudgetDto> executeValidation(List<List<CellCeilingBudgetDto>> toValidateSets) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CellCeilingBudgetDto> executeDataValidation(List<CellCeilingBudgetDto> toValidateSets) {

        List<CellCeilingBudgetDto> resultado = new ArrayList<CellCeilingBudgetDto>();
        List<ObjectExpenseEntity> catalog = objectExpenseDao.findAll();
        ObjectExpenseEntity dummyEntity;
        for (CellCeilingBudgetDto ref : toValidateSets) {
            dummyEntity = new ObjectExpenseEntity();
            String sdata = String.valueOf(Double.valueOf(ref.getDataElement()).longValue());
            int myDataElement = Integer.parseInt(sdata);
            dummyEntity.setObjectExpenseKey(myDataElement);

            Object data = SearchList.findObjectListCom(catalog,
                    dummyEntity,
                    ObjectExpenseKeyComparator.getInstance());
            if (data == null) {
                ref.setMesageError(ResourceBundleMassage.getString("ppp.ceiling.validator.errorMessage"));
                resultado.add(ref);
            }

        }
        return resultado;
    }
}
