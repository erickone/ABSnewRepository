/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.budget.dto.ValidationNameEnum;
import com.abs.siif.base.common.ResourceBundleMassage;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.planning.comparators.DraftProjectShortNameComparator;
import com.abs.siif.programming.dao.DraftProjectDao;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.support.SearchList;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Valida los datos del DraftProject sobre el shortname
 *
 * @author jacob.flores
 */
@Component("draftProjectValidate")
@Scope("prototype")
public class DraftProjectValidate extends CompCeilingBudget {

    @Resource(name = "draftProjectDaoImpl")
    DraftProjectDao draftProjectDao;

    public DraftProjectValidate() {
        setNameValidator(ValidationNameEnum.DRAFT_PROJECT);
    }

    @Override
    public List<CellCeilingBudgetDto> executeValidation(List<List<CellCeilingBudgetDto>> toValidateSets) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CellCeilingBudgetDto> executeDataValidation(List<CellCeilingBudgetDto> toValidateSets) {

        List<CellCeilingBudgetDto> resultado = new ArrayList<CellCeilingBudgetDto>();
        List<DraftProjectEntity> draftProjectCatalog = draftProjectDao.findAll();
        DraftProjectEntity dummyEntity;
        for (CellCeilingBudgetDto ref : toValidateSets) {
            dummyEntity = new DraftProjectEntity();
            dummyEntity.setDraftProjectShortName(ref.getDataElement());

            Object data = SearchList.findObjectListCom(draftProjectCatalog,
                    dummyEntity,
                    DraftProjectShortNameComparator.getInstance());
            if (data == null) {
                ref.setMesageError(ResourceBundleMassage.getString("ppp.ceiling.validator.errorMessage"));
                resultado.add(ref);
            }

        }
        return resultado;
    }
}