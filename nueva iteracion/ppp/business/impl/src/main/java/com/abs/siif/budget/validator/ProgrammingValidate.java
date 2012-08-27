/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.budget.dto.ValidationNameEnum;
import com.abs.siif.base.common.ResourceBundleMassage;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.planning.comparators.ProgrammingKeyComparator;
import com.abs.siif.programming.dao.ProgrammingDao;
import com.abs.siif.programming.entities.ProgrammingEntity;
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
@Component("programmingValidate")
@Scope("prototype")
public class ProgrammingValidate extends CompCeilingBudget
{
  @Resource(name = "programmingDaoImpl")
  ProgrammingDao programmingDao;

    public ProgrammingValidate() {
        setNameValidator(ValidationNameEnum.PROGRAMMING);       
    }
  
  
  @Override
  public List<CellCeilingBudgetDto> executeValidation(List<List<CellCeilingBudgetDto>> toValidateSets)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<CellCeilingBudgetDto> executeDataValidation(List<CellCeilingBudgetDto> toValidateSets)
  {
    
    List<CellCeilingBudgetDto> resultado = new ArrayList<CellCeilingBudgetDto>();
    List<ProgrammingEntity> catalog = programmingDao.findAll();
    ProgrammingEntity dummyEntity;
    for (CellCeilingBudgetDto ref : toValidateSets)
    {
      dummyEntity = new ProgrammingEntity();
      dummyEntity.setProgrammingKey(ref.getDataElement());

      Object data = SearchList.findObjectListCom(catalog,
              dummyEntity,
              ProgrammingKeyComparator.getInstance());
      if (data == null)
      {
        ref.setMesageError(ResourceBundleMassage.getString("ppp.ceiling.validator.errorMessage"));
        resultado.add(ref);
      }

    }
    return resultado;
  }
  
}