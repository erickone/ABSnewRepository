/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar Implementa los accesos a datos del
 * clasificador funcional
 */
@Repository("functionalClassifierProgrammingDao")
public class FunctionalClassifierProgrammingDaoImpl
        extends SIIFBaseDaoImpl<FunctionalClassifierEntity, Long> implements FunctionalClassifierProgrammingDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<FunctionalClassifierEntity> 
            getFunctionalClassifiersByObjectiveId(Long anObjectiveId) 
    {
        String myQueryString = "select func from FunctionalClassifierEntity func"
                + " left join fetch func.funtionalClassifierObjectives obj"
                + " where (obj.objectiveId= " + anObjectiveId + ")";

        return super.find(myQueryString);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FunctionalClassifierEntity> getHierarchicalFunctionalClassifier(List<Long> anIdentities)
    {
         String myTableName = getMessage("abs.siif.entities.FunctionalClassifierEntity.tablename");
        String myPrimaryKey = getMessage("abs.siif.entities.FunctionalClassifierEntity.primaryKey");
        String myPivotColumn = getMessage("abs.siif.entities.FunctionalClassifierEntity.pivotcolumn");
        String myFields = getMessage("abs.siif.entities.FunctionalClassifierEntity.fields");
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
    public List<FunctionalClassifierEntity> getHierarchicalFunctionalClassifier(Long anIdentity)
    {
        List<Long> myIdentities = new ArrayList<Long>();
        myIdentities.add(anIdentity);
        return getHierarchicalFunctionalClassifier(myIdentities);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<FunctionalClassifierEntity> getHierarchicalFunctionalClassifierByEntities(List<FunctionalClassifierEntity> anIdentity)
    {
        List<Long> myIdentities = new ArrayList<Long>();
        for (FunctionalClassifierEntity myEntity : anIdentity) {
            myIdentities.add(myEntity.getFunctionalClassifierId());
        }
        return getHierarchicalFunctionalClassifier(myIdentities);
    }
}
