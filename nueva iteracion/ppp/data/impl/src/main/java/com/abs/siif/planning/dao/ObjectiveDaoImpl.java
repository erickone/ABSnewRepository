package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("ObjectiveDao")
public class ObjectiveDaoImpl extends SIIFBaseDaoImpl<ObjectiveEntity, Long>
            implements ObjectiveDao {

    @Autowired
    private SessionFactory theirSessionFactory;
    
    Logger objLogger = Logger.getLogger(this.getClass());

    @Transactional(readOnly = true)
    @Override
    public List<ObjectiveEntity> GetAllObjectives() {
        objLogger.info("ObjectiveDaoImpl - GetAllObjectives()");
        
        String myQueryHQL = "select distinct obj from ObjectiveEntity obj left join fetch obj.objectiveFather"
                + " left join fetch obj.objectiveChilds"
                + " left join fetch obj.objectiveLevel"
                + " order by obj.objectiveKey  asc ";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);

        return myQuery.list();

    }

    @Transactional(readOnly = false)
    @Override
    public Long save(ObjectiveEntity anObjective, SaveType aSaveType) {
        objLogger.info("ObjectiveDaoImpl - save(ObjectiveEntity anObjective, SaveType aSaveType)");
        if (aSaveType == SaveType.UPDATE) {
            
            theirSessionFactory.getCurrentSession().merge(anObjective);
        } else {
            theirSessionFactory.getCurrentSession().save(anObjective);
        }

        return anObjective.getObjectiveId();
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(ObjectiveEntity anObjective) {
        objLogger.info("ObjectiveDaoImpl - delete(ObjectiveEntity anObjective)");
        theirSessionFactory.getCurrentSession().delete(anObjective);
    }

    @Transactional(readOnly = true)
    @Override
    public ObjectiveEntity getObjectiveByIdentity(Long anObjectiveId) {
        objLogger.info("ObjectiveDaoImpl - getObjectiveByIdentity()");
        return (ObjectiveEntity) theirSessionFactory.getCurrentSession().createCriteria(ObjectiveEntity.class).add(Restrictions.eq("objectiveId", anObjectiveId)).uniqueResult();
    }

    @Transactional(readOnly = false)
    @Override
    public void save(List<ObjectiveEntity> anEntities) {
        objLogger.info("ObjectiveDaoImpl - save(List<ObjectiveEntity> anEntities)");
        for (ObjectiveEntity myObjectiveEntity : anEntities) {
            theirSessionFactory.getCurrentSession().save(myObjectiveEntity);
        }

    }

    @Transactional(readOnly = false)
    @Override
    public void delete(List<ObjectiveEntity> anEntities) {
        objLogger.info("ObjectiveDaoImpl - delete(List<ObjectiveEntity> anEntities)");
        for (ObjectiveEntity myObjective : anEntities) {
            theirSessionFactory.getCurrentSession().delete(myObjective);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ObjectiveEntity getObjectiveEagerByIdentity(Long anObjectiveId) {
        objLogger.info("ObjectiveDaoImpl - getObjectiveEagerByIdentity()");
        String myQueryHQL = "select distinct  obj from ObjectiveEntity obj "
                + " left join fetch obj.problems p"
                + " left join fetch obj.objectiveChilds"
                + " left join fetch obj.specificObjectives so"
                + " left join fetch so.strategies strategies"
                + " left join fetch obj.objectiveLevel level"
                + " left join fetch obj.objectiveFather father"
                + " where (obj.objectiveId=:anObjectiveId)";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);

        myQuery.setLong("anObjectiveId", anObjectiveId);
        
        return (ObjectiveEntity) myQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public ObjectiveEntity getObjectiveSpecificObjAndIndicatorByIdentity(Long anObjectiveId)
    {
         objLogger.info("ObjectiveDaoImpl - getObjectiveSpecificObjAndIndicatorByIdentity()");
         String myQueryHQL = "select distinct  obj from ObjectiveEntity obj "
                + " left join fetch obj.objetiveIndicators indi"
                + " left join fetch obj.specificObjectives so"
                + " left join fetch so.strategies strategies"
                + " where (obj.objectiveId=:anObjectiveId)";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);

        myQuery.setLong("anObjectiveId", anObjectiveId);
        
        return (ObjectiveEntity) myQuery.uniqueResult();
    }
    
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    /**
     * Este metodo se trae las dependencias relacionadas a un objetivo.
     * @param anObjectiveId
     * @return 
     */
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getChildsRelatedObjList(Long anObjectiveId)
    {
        objLogger.info("ObjectiveDaoImpl - getChildsRelatedObjList()");
        String myQueryString = "select distinct dep from DependenceEntity dep"
                + " left join fetch dep.objectives ob"
                + " where ob.objectiveId = :anObjectiveId";
        
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);
        
        myQuery.setLong("anObjectiveId", anObjectiveId);
        
        return myQuery.list();
    }
    
}
