/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.BudgetingCeilingEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.budget.entities.ObjectExpenseLevelEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * este metodo nos sirve para realizar las operaciones sobre los
 * techos presupuestales
 * @author Erick Leija
 */
@Repository("budgetingCeilingDao")
public class BudgetingCeilingDaoImpl
extends SIIFBaseDaoImpl<BudgetingCeilingEntity, Long> 
implements BudgetingCeilingDao
{
    
    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ObjectExpenseEntity> getObjectExpenseRoots()
    {
         String myQueryString="select distinct obj from ObjectExpenseEntity obj"
                + "  where obj.ObjectExpenseFather is null and "
                + "     obj.objectExpenseLevelEnt.objectExpenseChapter = 't'"
                + " order by obj.objectExpenseKey asc";
         Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);       
        return myQuery.list(); 
    }
    @Transactional(readOnly=true)
    @Override
    public List<Long> getHierarchicalDependencies(List<Long> anIdentities,Long aDependence )
    {
        String myTableName = getMessage("abs.siif.entities.BudgetingSummary.tablename");
        myTableName = myTableName + "" + aDependence + ")  as b on a.idobjetogasto=b.idobjetogasto)"; 
        String myPrimaryKey = getMessage("abs.siif.entities.BudgetingSummary.primaryKey");
        String myPivotColumn = getMessage("abs.siif.entities.BudgetingSummary.pivotcolumn");
        String myFields = getMessage("abs.siif.entities.BudgetingSummary.fields");
        StringBuffer myStringBuffer = new StringBuffer();
        String myPrefixContitional = "";
        for (Long myIdentity : anIdentities) {

            if (myStringBuffer.length() > 0) {
                myStringBuffer.append("or");
            }
            myStringBuffer.append("(");
            myStringBuffer.append(myPrefixContitional);
            myStringBuffer.append(myPrimaryKey);
            myStringBuffer.append("=");
            myStringBuffer.append(myIdentity);
            myStringBuffer.append(")");
        }
        
        
         String myQueryString = getMessage("siif.dao.nativequeries.SQLHierarchiesforBudgetingSummary",
               myFields, myTableName,  myStringBuffer.toString(), myPrimaryKey, myPivotColumn);

        return super.getListToNativeQuery(myQueryString);
        

    }

    @Transactional(readOnly=true)
    @Override
    public List<Long> getHierarchicalDependencies(Long anIdentity,Long aDependence)
    {
        
        List<Long> myIdentities = new ArrayList<Long>();
        myIdentities.add(anIdentity);
        return getHierarchicalDependencies(myIdentities,aDependence);
    }

    @Transactional(readOnly = true)
    @Override
    public Long getLevelOfSpecificParId()
    {
        String myQueryString = "select distinct obj from ObjectExpenseLevelEntity obj"
                + "  where obj.objectExpenseSpecificPar = 't'";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);
        ObjectExpenseLevelEntity myParGenericLevel = (ObjectExpenseLevelEntity)  myQuery.uniqueResult();
        return myParGenericLevel.getObjectExpenseLevelId();
    }
    
}
