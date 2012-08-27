/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.ProgrammingEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Repository("programmingDaoImpl")
public class ProgrammingDaoImpl extends SIIFBaseDaoImpl<ProgrammingEntity, Long>
        implements ProgrammingDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<ProgrammingEntity> getProgrammingByObjectiveId(Long anIdentity) {

        return theirSessionFactory.getCurrentSession().createCriteria(ProgrammingEntity.class).add(Restrictions.eq("programmingObjective.objectiveId", anIdentity)).addOrder(Order.desc("programmingKey")).list();
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<ProgrammingEntity> getProgramminByDependenceAndObjective(Long aDependencyId, Long aObjectiveId) {
        String myQueryString = "select   prog "
                + " from ProgrammingEntity prog "
                + " left join fetch prog.programmingDependency DP"
                + " left join fetch prog.programmingObjective PO "
                + " where ((DP.dependenceId = :dependenceId ) and"
                + " ( PO.objectiveId=:objectiveId ) "
                + "and (prog.draftProject.isDraftProjectActive = 't')) "//see comment
                + " order by prog.father.programmingId";
        //la linea que checa si esta el draft esta activo es para la eliminacion 
        //logica del anteproyecto.

        Query myQuery = theirSessionFactory.
                getCurrentSession().createQuery(myQueryString).
                setLong("dependenceId", aDependencyId).
                setLong("objectiveId", aObjectiveId);
        
        return myQuery.list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProgrammingEntity> getHierarchicalPrograms(List<Long> anIdentities)
    {
        String myTableName = getMessage("abs.siif.entities.programmingentity.tablename");
        String myPrimaryKey = getMessage("abs.siif.entities.programmingentity.primaryKey");
        String myPivotColumn = getMessage("abs.siif.entities.programmingentity.pivotcolumn");
        String myFields = getMessage("abs.siif.entities.programmingentity.fields");
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
    public List<ProgrammingEntity> getHierarchicalPrograms(Long anIdentity)
    {
        List<Long> myIdentities = new ArrayList<Long>();
        myIdentities.add(anIdentity);
        return getHierarchicalPrograms(myIdentities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProgrammingEntity> getHierarchicalProgramsByEntities(List<ProgrammingEntity> anEntities) {
        List<Long> myIdentities = new ArrayList<Long>();
        for (ProgrammingEntity myEntity : anEntities) {
            myIdentities.add(myEntity.getProgrammingId());
        }
        return getHierarchicalPrograms(myIdentities);
    }
}
