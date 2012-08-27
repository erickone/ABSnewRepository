/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.data.BudgetDetailKeyDto;
import com.abs.siif.budget.data.QueryType;
import com.abs.siif.budget.entities.BudgetKeyConfigurationEntity;
import java.util.Collection;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("budgetKeyConfigurationDao")
public class BudgetKeyConfigurationDaoImpl
        extends SIIFBaseDaoImpl<BudgetKeyConfigurationEntity, Long>
        implements BudgetKeyConfigurationDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<BudgetKeyConfigurationEntity> getBudgetKeyConfigurations() {
        return super.getAllAndOrderByColumn("budgetKeyConfigurationHierarchy");
    }

    @Transactional(readOnly = true)
    @Override
    public BudgetDetailKeyDto getPrefixByBudgetKeyConfiguration(BudgetKeyConfigurationEntity myConfiguration) {
        String myPrefix = "";
        String myQueryString = myConfiguration.getBudgetKeyConfigurationSQLQuery();
        QueryType myQueryType = myConfiguration.getBudgetKeyConfigurationQueryType();
        BudgetDetailKeyDto myDto = null;
        List myResults = (myQueryType != QueryType.HQL
                ? super.getListToNativeQuery(myQueryString) : super.find(myQueryString));
        Object[] myObjectResults = null;

        if (myResults.size() > 0) {
            myObjectResults = (Object[]) myResults.get(0);
            Long myRealId = Long.valueOf(myObjectResults[0].toString());
            String myRealKey = myObjectResults[1].toString();
            int myPositions = myConfiguration.getBudgetKeyConfigurationPositions();
            if (myRealKey.length() < myPositions) {
                myPositions = myRealKey.length();
            }

            myPrefix = myRealKey.substring(0, myPositions);
            myDto = new BudgetDetailKeyDto();
            myDto.setPartialKey(myPrefix);
            myDto.setRealId(myRealId);
            myDto.setBudgetKeyConfigurationId
                    (myConfiguration.getBudgetKeyConfigurationId());
        }

        return myDto;
    }
}
