package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface InstitutionalPlanDao extends SIIFBaseDao<InstitutionalPlanEntity, Long>
{
   
    
    void deleteInstitutionalPlan(InstitutionalPlanEntity anInstitutionalPlan);
    
    List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceId(Long aDependenceId);
    
    List<InstitutionalPlanEntity> getInstitutionalPlansByDependency(DependenceEntity aDependency);
    
    InstitutionalPlanEntity getInstitutionalPlanById(Long anInstitutionalPlanId);
    
    void closeTheCurrentSession();
    
}
