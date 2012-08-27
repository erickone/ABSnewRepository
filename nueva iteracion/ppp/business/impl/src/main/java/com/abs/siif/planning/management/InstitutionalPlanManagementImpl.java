package com.abs.siif.planning.management;

import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.dao.InstitucionalPlanObjectiveDao;
import com.abs.siif.planning.dao.InstitutionalPlanDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import com.abs.siif.planning.entities.InstitutionalPlanObjectiveEntity;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("institutionalPlanManagement")
public class InstitutionalPlanManagementImpl implements InstitutionalPlanManagement {

    @Resource(name = "InstitutionalPlanDao")
    private InstitutionalPlanDao theirInstitutionalPlanDao;
    @Resource(name = "DependenceDao")
    private DependenceDao theirDependenceDao;
    @Resource(name="institucionalPlanObjectiveDao")
    private InstitucionalPlanObjectiveDao instPlanObjectiveDao;

    public void setInstitutionalPlanDao(InstitutionalPlanDao anInstitutionalPlanDao) {
        this.theirInstitutionalPlanDao = anInstitutionalPlanDao;
    }

    @Override
    public List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceId(Long aDependenceId) {
        return theirInstitutionalPlanDao.getInstitutionalPlanByDependenceId(aDependenceId);
    }

    @Override
    public InstitutionalPlanEntity getInstitutionalPlanById(Long anInstitutionalPlanId) {
        return theirInstitutionalPlanDao.getInstitutionalPlanById(anInstitutionalPlanId);
    }

    @Override
    public void deleteInstitutionalPlan(Long anIdInstitutionalPlan) {
        InstitutionalPlanEntity myInstitutionaPlanToDelete;
        myInstitutionaPlanToDelete = theirInstitutionalPlanDao.getInstitutionalPlanById(anIdInstitutionalPlan);
        theirInstitutionalPlanDao.deleteInstitutionalPlan(myInstitutionaPlanToDelete);
    }

    @Override
    public Long updateInstitutionalPlan (InstitutionalPlanEntity anInstitutionalPlan){        
        return saveOrUpdate(anInstitutionalPlan);
    }

    @Override
    public Long addInstitutionalPlan (InstitutionalPlanEntity anInstitutionalPlan){
        return saveOrUpdate(anInstitutionalPlan);
    }
 
    //geting the dependence for this Institutional Plan
    private Long saveOrUpdate(InstitutionalPlanEntity anInstitutionalPlanEntity){
        Long myIdentity = anInstitutionalPlanEntity.getInstitutionalPlanId();
        if (myIdentity == null) {
            myIdentity = theirInstitutionalPlanDao.save(anInstitutionalPlanEntity).getInstitutionalPlanId();
        } else {
            theirInstitutionalPlanDao.merge(anInstitutionalPlanEntity);
        }
        return myIdentity;
    }

    @Override
    public void saveObjectives(List<InstitutionalPlanObjectiveEntity> list) {
        instPlanObjectiveDao.saveInstitucionalObjective(list);
    }

    @Override
    public List<InstitutionalPlanObjectiveEntity> searchInstPlanObjectives(InstitutionalPlanEntity input) {
        List<InstitutionalPlanObjectiveEntity> resultList 
                = instPlanObjectiveDao.searchObjectives(input);
        return resultList;
    }

    @Override
    public void deleteObjectives(List<InstitutionalPlanObjectiveEntity> list) 
    {
        instPlanObjectiveDao.deleteInstitucionalObjective(list);
    }
    
    @Override
    public List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceChildId(Long aDependenceId)
    {
        DependenceEntity myDependence = theirDependenceDao.getDependenceById(aDependenceId);
        //Obtener 
        Long myIdWithIP2 = myDependence.getFather().getDependenceId();
        Long myIdWithIP = myDependence.getDependenceId();
       // return theirInstitutionalPlanDao.getInstitutionalPlanByDependenceId(myIdWithIP);
        return theirInstitutionalPlanDao.getInstitutionalPlanByDependenceId(myIdWithIP2);
         //populateListOfInstitutionalPlan();
    }
    
}
