package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("ObjectiveProgrammingDao")
public class ObjectiveProgrammingDaoImpl extends SIIFBaseDaoImpl<ObjectiveEntity, Long> 
            implements ObjectiveProgrammingDao {
    @Autowired
    private SessionFactory theirSessionFactory;
    
    Logger objLogger = Logger.getLogger(this.getClass());

    @Transactional(readOnly = true)
    @Override
    public List<ObjectiveEntity> getObjectivesByDependencyId(Long anDependenceId) {
       objLogger.info("ObjectiveProgrammingDaoImpl - getObjectivesByDependencyId()");
        
        String myQueryString = "select distinct obj "
                + " from ObjectiveEntity obj "
                + " left join fetch obj.dependences Dep"
                + " where Dep.dependenceId = " + anDependenceId.toString()
                + " order by obj.objectiveName  asc ";
        return super.find(myQueryString);

    }

    @Transactional(readOnly = true)
    @Override
    public List<ObjectiveEntity> getObjectivesByDependency(DependenceEntity anDependence) {
        objLogger.info("ObjectiveProgrammingDaoImpl - getObjectivesByDependency()");        
        
        String myQueryString = "select distinct obj "
                + " from ObjectiveEntity obj "
                + " left join fetch obj.dependences Dep"
                + " where Dep.dependenceId = "
                + anDependence.getDependenceId().toString()
                + " order by obj.objectiveName  asc ";
        return super.find(myQueryString);

    }

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ObjectiveEntity> getHierarchicalObjectives(List<Long> anIdentities) {
        objLogger.info("ObjectiveProgrammingDaoImpl - getHierarchicalObjectives()");
        
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
    public List<ObjectiveEntity> getHierarchicalObjectives(Long anIdentity) {
        objLogger.info("ObjectiveProgrammingDaoImpl - getHierarchicalObjectives()");
        List<Long> myIdentities = new ArrayList<Long>();
        myIdentities.add(anIdentity);
        return getHierarchicalObjectives(myIdentities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ObjectiveEntity> getHierarchicalObjectivesByEntities(List<ObjectiveEntity> anEntities) {
        objLogger.info("ObjectiveProgrammingDaoImpl - getHierarchicalObjectivesByEntities()");
        List<Long> myIdentities = new ArrayList<Long>();
        for (ObjectiveEntity myEntity : anEntities) {
            myIdentities.add(myEntity.getObjectiveId());
        }
        return getHierarchicalObjectives(myIdentities);
    }
}
