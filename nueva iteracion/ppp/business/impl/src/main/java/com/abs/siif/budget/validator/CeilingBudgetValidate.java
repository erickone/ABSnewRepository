/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.validator;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.management.ManagementBase;
import com.abs.siif.budget.dao.BudgetKeyItemDao;
import com.abs.siif.budget.dao.CeilingConfigurationDao;
import com.abs.siif.budget.dto.BudgetKeyBreakDownDto;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author absvalenzuela
 */
//@Component("validateURCeilingBudget")
@Service("ceilingBudgetValidate")
public class CeilingBudgetValidate extends ManagementBase
        implements Serializable {

    @Resource(name = "ceilingConfigurationDao")
    private transient CeilingConfigurationDao theirceilingConfigurationDao;

    @Resource(name = "budgetKeyItemDao")
    private transient BudgetKeyItemDao itsBudgetKeyItemDao;

    public Long getConfigBudgetId() {
        Long configCeilingId = 0L;
        Boolean existAllElement = false;
        int iOrder = 0;
        List<BudgetKeyItemEntity> itemsElement = elementCeilingDefault();
        List<CeilingConfigurationEntity> configurations =
                theirceilingConfigurationDao.getAllCeilingConfigurationByYear(Integer.parseInt(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()));

        for (CeilingConfigurationEntity itemCeilingBudget : configurations) {
            if (itemCeilingBudget.getBudgetKeyItems().size() == itemsElement.size()) {
                iOrder = 0;
                for (BudgetKeyItemEntity item : itemsElement) {
                    int iIndexElement = SearchList.indexList(itemCeilingBudget.getBudgetKeyItems(), item);
                    if (iIndexElement < 0) {
                        existAllElement = false;
                        break;
                    } else {
                        if (iOrder == iIndexElement) {
                            iOrder++;
                            existAllElement = true;
                        }
                    }
                }
            }
            if (existAllElement) {
                configCeilingId = itemCeilingBudget.getCeilingConfigId();
                break;
            }
        }
        return configCeilingId;
    }

    private List<BudgetKeyItemEntity> elementCeilingDefault() {
        List<BudgetKeyItemEntity> itemsElement = new ArrayList<BudgetKeyItemEntity>();

        List<String> lstDescriptionCeiling = new ArrayList<String>();

        lstDescriptionCeiling.add("UR");
        lstDescriptionCeiling.add("Objeto del Gasto");
        lstDescriptionCeiling.add("Destino del Gasto");

        List<BudgetKeyItemEntity> lstElements = (List<BudgetKeyItemEntity>) itsBudgetKeyItemDao.getBudgetKeyItems();

        for (BudgetKeyItemEntity objectElement : lstElements) {
            for (String description : lstDescriptionCeiling) {
                if (description.equals(objectElement.getBudgetKeyName())) {
                    itemsElement.add(objectElement);
                }
            }
        }
        return itemsElement;
    }
    
    public boolean modifyCeilingBudget(BudgetKeyBreakDownDto objectCeilingUpdate){
        boolean updateCeilingBudget = false;
        
        
        return updateCeilingBudget;        
    }
}
