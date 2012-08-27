package com.abs.siif.planning.management;

import com.abs.siif.planning.dao.ObjectiveDao;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.programming.dao.FunctionalClassifierProgrammingDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("objectiveManagement")
public class ObjectiveManagementImpl implements ObjectiveManagement {

    @Resource(name = "ObjectiveDao")
    private ObjectiveDao theirObjectiveDao;
    @Resource(name = "functionalClassifierProgrammingDao")
    private FunctionalClassifierProgrammingDao itsFuncClassProgrammingDao;

    public void setObjectiveDao(ObjectiveDao anObjectiveDao) {
        this.theirObjectiveDao = anObjectiveDao;
    }

    @Override
    public List<ObjectiveEntity> GetAllObjectives() {
        return theirObjectiveDao.GetAllObjectives();
    }

    @Override
    public void deleteObjective(Long anIdObjective) {
        ObjectiveEntity myObjectiveToDelete = null;
        myObjectiveToDelete = getObjectiveById(anIdObjective);
        theirObjectiveDao.delete(myObjectiveToDelete);

    }

    @Override
    public ObjectiveEntity getObjectiveByIdentity(Long anIdObjective) {
        return getObjectiveById(anIdObjective);
    }

    @Override
    public ObjectiveEntity getObjectiveById(Long anIdObjective) {
        return theirObjectiveDao.getObjectiveByIdentity(anIdObjective);
    }

    @Override
    public void updateObjective(Long anObjectiveId, String anObjectiveName, String anObjectiveDefinition,
            Short anObjectivePriority, Long anObjectiveLevelId, Long aFatherId) {
    }

    @Override
    public ObjectiveEntity getObjectiveEagerByIdentity(Long anObjectiveId) {
        return theirObjectiveDao.getObjectiveEagerByIdentity(anObjectiveId);
    }

    @Override
    public Long Save(ObjectiveEntity myObjectiveData, SaveType aSaveType) {
        return theirObjectiveDao.save(myObjectiveData, aSaveType);
    }

    @Override
    public List<DependenceEntity> getChildsRelatedObjList(Long anObjectiveId) {
        return theirObjectiveDao.getChildsRelatedObjList(anObjectiveId);
    }

    @Override
    public Set<FunctionalClassifierEntity> getFunctionalClassifiersByObjectiveId(Long anObjectiveId) {
        Set<FunctionalClassifierEntity> myFunClassEntities = null;
        Collection<FunctionalClassifierEntity> c = itsFuncClassProgrammingDao.getFunctionalClassifiersByObjectiveId(anObjectiveId);
        myFunClassEntities = new HashSet<FunctionalClassifierEntity>(c);
        return myFunClassEntities;
    }

    @Override
    public ObjectiveEntity getObjectiveSpecificObjAndIndicatorByIdentity(Long anObjectiveId) {
        return theirObjectiveDao.getObjectiveSpecificObjAndIndicatorByIdentity(anObjectiveId);
    }
    
    @Override
    public List<ObjectiveEntity> getStateAlignmet(ObjectiveEntity subProgram){
        List<ObjectiveEntity> result = new ArrayList<ObjectiveEntity>();
        subProgram = theirObjectiveDao.getObjectiveEagerByIdentity(subProgram.getObjectiveId());
        ObjectiveEntity parent2 = null;
        ObjectiveEntity parent1 = null;
        if(subProgram != null && subProgram.getFather()!= null){
            parent2 = theirObjectiveDao.getObjectiveEagerByIdentity(subProgram.getFather()
                    .getObjectiveId());
        }
       if(parent2 != null && parent2.getFather()!= null){
            parent1 = theirObjectiveDao.getObjectiveEagerByIdentity(parent2.getFather()
                    .getObjectiveId());
            result.add(parent1);
            result.add(parent2);
            result.add(subProgram);
        }
       return result;
    }
    
}
