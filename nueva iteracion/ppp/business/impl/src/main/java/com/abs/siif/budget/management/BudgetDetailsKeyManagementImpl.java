/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.management;

import com.abs.siif.budget.dao.BudgetDetailsKeyDao;
import com.abs.siif.budget.entities.BudgetDetailsKeyEntity;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("budgetDetailsKeyManagement")
public class BudgetDetailsKeyManagementImpl implements
        BudgetDetailsKeyManagement {

    @Resource(name = "budgetDetailsKeyDao")
    private BudgetDetailsKeyDao itsBudgetDetailsKeyDao;

    @Transactional(readOnly = false)
    @Override
    public void persistBudgetDetailsKey(Collection<BudgetDetailsKeyEntity> anEntities) {
        itsBudgetDetailsKeyDao.saveAll(anEntities);
    }

    @Override
    public BudgetDetailsKeyEntity persistBudgetDetailsKey(BudgetDetailsKeyEntity anEntity) {
        BudgetDetailsKeyEntity myBudgetDetailsKey = new BudgetDetailsKeyEntity();
        if (anEntity.getBudgetDetailsKeyId() != null) {
            myBudgetDetailsKey = itsBudgetDetailsKeyDao.save(anEntity);
        } else {
            myBudgetDetailsKey = itsBudgetDetailsKeyDao.merge(anEntity);
        }
        return myBudgetDetailsKey;
    }

    @Override
    public Collection<BudgetDetailsKeyEntity> getBudgetDetailsKeyByBudgetKey(BudgetKeyEntity anEntity) {
        return itsBudgetDetailsKeyDao.getBudgetDetailsKeyByBudgetKey(anEntity);
    }

    @Override
    public void deleteBudgetDetailsKey(BudgetKeyEntity anIdentity) {
        itsBudgetDetailsKeyDao.deleteBudgetDetailsKey(anIdentity);
    }
    
}
