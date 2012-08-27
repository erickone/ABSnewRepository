/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.budget.dto.ValidationNameEnum;
import com.abs.siif.base.common.ResourceBundleMassage;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.planning.comparators.DependencyComparator;
import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.entities.DependenceEntity;
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
@Component("dependencyValidate")
@Scope("prototype")
public class DependencyValidate extends CompCeilingBudget {

    @Resource(name = "DependenceDao")
    DependenceDao dependenceDao;

    public DependencyValidate() {
        setNameValidator(ValidationNameEnum.DEPENDENCY);
    }

    @Override
    public List<CellCeilingBudgetDto> executeValidation(List<List<CellCeilingBudgetDto>> toValidateSets) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CellCeilingBudgetDto> executeDataValidation(List<CellCeilingBudgetDto> toValidateSets) {
        setNameValidator(ValidationNameEnum.DEPENDENCY);
        List<CellCeilingBudgetDto> resultado = new ArrayList<CellCeilingBudgetDto>();
        List<DependenceEntity> draftProjectCatalog = dependenceDao.findAll();
        DependenceEntity dummyEntity;
        for (CellCeilingBudgetDto ref : toValidateSets) {
            dummyEntity = new DependenceEntity();
            dummyEntity.setDependenceKey(ref.getDataElement());

            Object data = SearchList.findObjectListCom(draftProjectCatalog,
                    dummyEntity,
                    DependencyComparator.getInstance());
            if (data == null) {
                ref.setMesageError(ResourceBundleMassage.getString("ppp.ceiling.validator.errorMessage"));
                resultado.add(ref);
            }

        }
        return resultado;
    }
}
