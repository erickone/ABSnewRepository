/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.base.common.ResourceBundleMassage;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.dto.ValidationNameEnum;
import com.abs.siif.planning.comparators.ObjetiveEntityComparator;
import com.abs.siif.planning.dao.ObjectiveDao;
import com.abs.siif.planning.entities.ObjectiveEntity;
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
@Component("objetiveValidator")
@Scope("prototype")
public class ObjetiveValidator extends CompCeilingBudget {

    @Resource(name = "ObjectiveDao")
    ObjectiveDao objectiveDao;

    public ObjetiveValidator() {
        setNameValidator(ValidationNameEnum.OBJETIVE);
    }

    @Override
    public List<CellCeilingBudgetDto> executeValidation(List<List<CellCeilingBudgetDto>> toValidateSets) {

        return null;
    }

    @Override
    public List<CellCeilingBudgetDto> executeDataValidation(List<CellCeilingBudgetDto> toValidateSets) {
        List<CellCeilingBudgetDto> resultado = new ArrayList<CellCeilingBudgetDto>();
        List<ObjectiveEntity> catalog = objectiveDao.findAll();
        ObjectiveEntity dummyEntity;
        for (CellCeilingBudgetDto ref : toValidateSets) {
            dummyEntity = new ObjectiveEntity();
            dummyEntity.setObjectiveKey(ref.getDataElement());

            Object data = SearchList.findObjectListCom(catalog,
                    dummyEntity,
                    ObjetiveEntityComparator.getInstance());
            if (data == null) {
                ref.setMesageError(ResourceBundleMassage.getString("ppp.ceiling.validator.errorMessage"));
                resultado.add(ref);
            }

        }
        return resultado;
    }
}
