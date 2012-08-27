/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.dto.BudgetSummaryDto;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
@Repository("objectExpenseDao")
public class ObjectExpenseDaoImpl extends SIIFBaseDaoImpl<ObjectExpenseEntity, Long>
        implements ObjectExpenseDao {

    private Logger objLogger = Logger.getLogger(this.getClass());
    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Override
    public List<ObjectExpenseEntity> getHierarchicalObjectives(List<Long> anIdentities) {
        objLogger.info("ObjectExpenseDaoImpl.getHierarchicalObjectives");
        String myTableName = getMessage("abs.siif.entities.objectiveentity.tablename");
        String myPrimaryKey = getMessage("abs.siif.entities.objectiveentity.primarykey");
        String myPivotColumn = getMessage("abs.siif.entities.objectiveentity.pivotcolumn");
        String myFields = getMessage("abs.siif.entities.objectiveentity.fields");
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
        return super.getHierarchicalStructures(myStringBuffer.toString(), myFields, myTableName, myPrimaryKey, myPivotColumn);

    }

    @Transactional(readOnly = true)
    @Override
    public BudgetSummaryDto getAllAmountsOfChapters(String programmaticKey, String aditionalFlag) {
        objLogger.info("ObjectExpenseDaoImpl.getAllAmountsOfChapters");
        BudgetSummaryDto myDto = new BudgetSummaryDto();
        String myQueryString = "select sum(a.originalAmount) as originalAmount"
                + ", sum(a.additionalAmount) as additionalAmount,"
                + "  (sum(a.originalAmount) + sum(a.additionalAmount)) as Sum"
                + " from BudgetKeyEntity as a "
                + " where (a.budgetKeyBProgramaticKey like '" + programmaticKey + "%')";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        if (mySQLQuery.uniqueResult() != null) {
            Object[] myObjectFields = (Object[]) mySQLQuery.uniqueResult();
            if (myObjectFields[0] != null) {
                myDto.setOriginalAmount((Double) myObjectFields[0]);
                myDto.setAdditionalAmount((Double) myObjectFields[1]);
                myDto.setSum((Double) myObjectFields[2]);
            }
        }

        return myDto;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<ObjectExpenseEntity> getLastLevelObjects(Long anObjectExpenseid) {
        objLogger.info("ObjectExpenseDaoImpl.getLastLevelObjects");
        String myQueryString = "select distinct o.idobjetogasto,o.descripcion,o.clave,"
                + "o.idpadre,o.idnivelobjgasto from (siifpppobjetogasto) as o "
                + "Start with (o.idobjetogasto= :objectExpensiveId)"
                + " CONNECT BY Prior o.idobjetogasto=o.idpadre order by o.clave";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);

        mySQLQuery.setLong("objectExpensiveId", anObjectExpenseid);

        return mySQLQuery.list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ObjectExpenseEntity> getObjectExpenseBySpecificParByObjectIdRelated(
            Long idObjectExpense) {
        objLogger.info("ObjectExpenseDaoImpl.getObjectExpenseBySpecificParByObjectIdRelated");
        return getObjectExpenseBySpecificParByObjectIdRelatedFilter(
                idObjectExpense, null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ObjectExpenseEntity> getObjectExpenseBySpecificParByObjectIdRelatedFilter(
            Long idObjectExpense, String filter) {
        objLogger.info("ObjectExpenseDaoImpl.getObjectExpenseBySpecificParByObjectIdRelatedFilter");
        String level = " niv.parespecifica='t'";
        return getObjectsByIdRelated(idObjectExpense, level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ObjectExpenseEntity> getObjectExpenseByGenericParByObjectIdRelated(
            Long idObjectExpense) {
        objLogger.info("ObjectExpenseDaoImpl.getObjectExpenseByGenericParByObjectIdRelated");
        return getObjectExpenseByGenericParByObjectIdRelatedFilter(
                idObjectExpense, null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ObjectExpenseEntity> getObjectExpenseByGenericParByObjectIdRelatedFilter(
            Long idObjectExpense, String filter) {
        objLogger.info("ObjectExpenseDaoImpl.getObjectExpenseByGenericParByObjectIdRelatedFilter");
        String level = " niv.pargenerica='t'";
        return getObjectsByIdRelated(idObjectExpense, level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ObjectExpenseEntity> getObjectExpenseByConceptByObjectIdRelated(
            Long idObjectExpense) {
        objLogger.info("ObjectExpenseDaoImpl.getObjectExpenseByConceptByObjectIdRelated");
        return getObjectExpenseByConceptByObjectIdRelatedFilter(
                idObjectExpense, null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ObjectExpenseEntity> getObjectExpenseByConceptByObjectIdRelatedFilter(
            Long idObjectExpense, String filter) {
        objLogger.info("ObjectExpenseDaoImpl.getObjectExpenseByConceptByObjectIdRelatedFilter");
        String level = " niv.concepto='t'";
        return getObjectsByIdRelated(idObjectExpense, level, filter);
    }

    @Transactional(readOnly = true)
    private List<ObjectExpenseEntity> getObjectsByIdRelated(
            Long idObjectExpense, String level, String filter) {
        objLogger.info("ObjectExpenseDaoImpl.getObjectsByIdRelated");

        String query = this.getMessage("siif.dao.nativequeries.SQLObjectExpense");
        query += level;

        if (filter != null) {
            query += filter;
        }

        SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ObjectExpenseEntity.class);

        mySQLQuery.setLong("objectId", idObjectExpense);
        mySQLQuery.setLong("objectId1", idObjectExpense);



        //return getListFromSQLquery(mySQLQuery , ObjectExpenseEntity.class);
        return (List<ObjectExpenseEntity>) mySQLQuery.list();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ObjectExpenseEntity> getObjectExpensesNotWithIds(Long[] ids) {
        String myQueryString = "select distinct bc from ObjectExpenseEntity bc"
                + " left join fetch bc.objectExpenseLevelEnt oe"
                + " where bc.ObjectExpenseId not in (:ids)";
        Query myQuery = getTheirSessionFactory().getCurrentSession().createQuery(myQueryString).
                setParameterList("ids", ids);                
        
        return myQuery.list();
    }
}
