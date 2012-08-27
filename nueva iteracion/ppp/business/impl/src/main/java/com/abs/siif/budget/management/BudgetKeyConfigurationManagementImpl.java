/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.management;

import com.abs.siif.budget.dao.BudgetKeyConfigurationDao;
import com.abs.siif.budget.data.BudgetDetailKeyDto;
import com.abs.siif.budget.data.BudgetKeyItemTypes;
import com.abs.siif.budget.entities.BudgetDetailsKeyEntity;
import com.abs.siif.budget.entities.BudgetKeyConfigurationEntity;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar Implementa los metodos de negocio para
 * generar la clave presupuestal
 */
@Service("budgetKeyConfigurationManagement")
public class BudgetKeyConfigurationManagementImpl implements BudgetKeyConfigurationManagement, Serializable {

    @Resource(name = "budgetKeyConfigurationDao")
    private BudgetKeyConfigurationDao itsBudgetKeyConfigurationDao;

    @Override
    public String getBudgetKey(Map<String, Long> anIdentities) {
        Collection<BudgetKeyConfigurationEntity> myBudgetKeyConfigurations = itsBudgetKeyConfigurationDao.getBudgetKeyConfigurations();

        
        Collection<BudgetDetailKeyDto> myDetails = loadBudgetDetailsKey(myBudgetKeyConfigurations, anIdentities);

        return constructBudgetKey(myDetails);
    }

    private Collection<BudgetDetailKeyDto> loadBudgetDetailsKey(Collection<BudgetKeyConfigurationEntity> myBudgetKeyConfigurations, Map<String, Long> anIdentities) {
        Collection<BudgetDetailKeyDto> myDetails = new ArrayList<BudgetDetailKeyDto>();

        for (BudgetKeyConfigurationEntity myConfiguration : myBudgetKeyConfigurations) {
            BudgetKeyItemTypes myType = BudgetKeyItemTypes.valueOf(myConfiguration.getBudgetKeyItem().getBudgetKeyEntity());
            String myQueryString = myType.constructQueryString(anIdentities, myConfiguration.getBudgetKeyConfigurationSQLQuery());

            if (!myQueryString.equals("")) {
                myConfiguration.setBudgetKeyConfigurationSQLQuery(myQueryString);
                BudgetDetailKeyDto myDetailKey = itsBudgetKeyConfigurationDao.getPrefixByBudgetKeyConfiguration(myConfiguration);
                if (myDetailKey != null) {
                    myDetails.add(myDetailKey);
                }
            }
        }
        return myDetails;
    }

    @Override
    public Map<String, List<BudgetDetailsKeyEntity>> getBudgetKeyWithBudgetKeyItems(Map<String, Long> anIdentities) {
        Collection<BudgetKeyConfigurationEntity> myBudgetKeyConfigurations = itsBudgetKeyConfigurationDao.getBudgetKeyConfigurations();
        List<BudgetDetailsKeyEntity> myDetailsItems = new ArrayList<BudgetDetailsKeyEntity>();
        Collection<BudgetDetailKeyDto> myDetails = loadBudgetDetailsKey(myBudgetKeyConfigurations, anIdentities);
        Map<String, List<BudgetDetailsKeyEntity>> myBudgetKeyWithBudgetItems =
                new HashMap<String, List<BudgetDetailsKeyEntity>>();

        String myBudgetKey = constructBudgetKey(myDetails);

        for (BudgetDetailKeyDto myDto : myDetails) {
            BudgetDetailsKeyEntity myDetail = new BudgetDetailsKeyEntity();
            BudgetKeyConfigurationEntity myBudgetKeyConfiguration =
                    new BudgetKeyConfigurationEntity();
            myBudgetKeyConfiguration.setBudgetKeyConfigurationId(
                    myDto.getBudgetKeyConfigurationId());
            myDetail.setBudgetDetailsKeyRealId(myDto.getRealId());
            myDetail.setBudgetKeyConfiguration(myBudgetKeyConfiguration);
            myDetailsItems.add(myDetail);
        }

        myBudgetKeyWithBudgetItems.put(myBudgetKey, myDetailsItems);

        return myBudgetKeyWithBudgetItems;
    }

    private String constructBudgetKey(Collection<BudgetDetailKeyDto> myDetails) {
        StringBuilder myBuilder = new StringBuilder();
        for (BudgetDetailKeyDto myDto : myDetails) {

            myBuilder.append(myDto.getPartialKey());
        }

        return myBuilder.toString();
    }
}
