/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FinancingSourceValidate
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.budget.dto.ValidationNameEnum;
import com.abs.siif.base.common.ResourceBundleMassage;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.dao.FinancingSourceDao;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.entities.FinancingSourceEntity;
import com.abs.siif.planning.comparators.FinancingSourceKeyComparator;
import com.abs.siif.support.SearchList;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Israel Ruiz
 */
@Component("financingSourceValidate")
@Scope("prototype")
/**
 * Calse que se encarga de Validar los datos que se envia contra el catalogo de
 * Fuente de financiamiento, la validación se realiza sobre su clave
 */
public class FinancingSourceValidate extends CompCeilingBudget {

    @Resource(name = "financingSourceDao")
    FinancingSourceDao financingSourceDao;

    public FinancingSourceValidate() {
        setNameValidator(ValidationNameEnum.FINANCE_SOURCE);
    }

    @Override
    public List<CellCeilingBudgetDto> executeValidation(List<List<CellCeilingBudgetDto>> toValidateSets) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CellCeilingBudgetDto> executeDataValidation(List<CellCeilingBudgetDto> toValidateSets) {

        /*
         * verificar por cada dato en el Set las siguientes validaciones Que
         * exista el dato en su catalogo correspondiente En caso de Error se
         * debera de colocar en el DTO el mensaje correspondiente y colocarlo en
         * una lista para su regreso, en caso de exito no se colocara en la
         * lista
         */

        List<CellCeilingBudgetDto> resultado = new ArrayList<CellCeilingBudgetDto>();
        List<FinancingSourceEntity> catalog = financingSourceDao.findAll();
        FinancingSourceEntity dummyEntity;
        for (CellCeilingBudgetDto ref : toValidateSets) {
            dummyEntity = new FinancingSourceEntity();
            dummyEntity.setFinancingSourceKey(ref.getDataElement());

            Object data = SearchList.findObjectListCom(catalog,
                    dummyEntity,
                    FinancingSourceKeyComparator.getInstance());
            if (data == null) {
                ref.setMesageError(ResourceBundleMassage.getString("ppp.ceiling.validator.errorMessage"));
                resultado.add(ref);
            }

        }
        return resultado;

    }
}
