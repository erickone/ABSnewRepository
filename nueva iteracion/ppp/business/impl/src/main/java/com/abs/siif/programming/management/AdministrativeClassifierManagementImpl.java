/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.dao.BranchSectorAndDependenceFrammingDao;
import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.BranchSectorAndDependenceFrammingEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Erick Leija
 */ 
@Service("administrativeClassifierManagement")
public class AdministrativeClassifierManagementImpl implements AdministrativeClassifierManagement,Serializable
{

    @Resource(name="DependenceDao")
    private transient DependenceDao theirDependenceDao;
    
    @Resource(name="branchSectorAndDependenceFrammingDao")
    private transient BranchSectorAndDependenceFrammingDao theirBranchSectorAndDependenceFrammingDao;
    
    @Override
    public Collection<DependenceEntity> getMyAncestryClassiFier(Long aDependenceId) {
        return theirDependenceDao.getHierarchicalDependencies(aDependenceId);
    }
    
    @Override
    public List<DepencenceDto> getMyUEGsByDependenceRelated(Long aDependenceId)
    {
        return theirDependenceDao.getDependciesIsExecUnitByDependIdRelated(aDependenceId);
    }
    
    @Override
    public List<DepencenceDto> getMyURByDependenceRelated(Long aDependenceId)
    {
        return theirDependenceDao.getDependciesIsRespUnitByDependIdRelated(aDependenceId);
    }
    
    @Override
    public List<DepencenceDto> getMyUPByDependenceRelated(Long aDependenceId)
    {
        return theirDependenceDao.getDependciesIsBudgetByDependIdRelated(aDependenceId);
    }

    @Override
    public BranchSectorAndDependenceFrammingEntity getMyFrammingByDependenceId(DependenceEntity aDependence) {
        return theirBranchSectorAndDependenceFrammingDao.getFrammingByDependence(aDependence);
    }

    @Override
    public List<DepencenceDto> getMyFrammingSectorByDependenceRelated(Long aDependenceId) {
       return theirDependenceDao.getDependciesIsFrammingSectorByDependIdRelated(aDependenceId);
    }
    
    
}
