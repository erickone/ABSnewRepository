package com.abs.siif.planning.management;

import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import com.abs.siif.planning.entities.InstitutionalPlanObjectiveEntity;
import java.util.List;

/**
 *
 * @author Erick Leija
 * 
 */
public interface InstitutionalPlanManagement {
    
    List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceId(Long aDependenceId) ;

    InstitutionalPlanEntity getInstitutionalPlanById(Long anInstitutionalPlanId);

    void deleteInstitutionalPlan(Long anIdInstitutionalPlan);

    Long updateInstitutionalPlan(InstitutionalPlanEntity anInstitutionalPlanEntity);
                            
    Long addInstitutionalPlan(InstitutionalPlanEntity anInstitutionalPlanEntity);
    
    void saveObjectives(List<InstitutionalPlanObjectiveEntity> list);
    
    List<InstitutionalPlanObjectiveEntity>searchInstPlanObjectives(InstitutionalPlanEntity input);
    
   void deleteObjectives(List<InstitutionalPlanObjectiveEntity> list);
   
    List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceChildId(Long aDependenceId);
}
