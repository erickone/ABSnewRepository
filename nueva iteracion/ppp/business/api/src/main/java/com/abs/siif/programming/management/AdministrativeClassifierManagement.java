/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.BranchSectorAndDependenceFrammingEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface AdministrativeClassifierManagement
{
    Collection<DependenceEntity> getMyAncestryClassiFier(Long aDependenceId);
    
    List<DepencenceDto> getMyUEGsByDependenceRelated(Long aDependenceId);
    
    List<DepencenceDto> getMyURByDependenceRelated(Long aDependenceId);
    
    List<DepencenceDto> getMyUPByDependenceRelated(Long aDependenceId);
    
    List<DepencenceDto> getMyFrammingSectorByDependenceRelated(Long aDependenceId);
    
    
    BranchSectorAndDependenceFrammingEntity getMyFrammingByDependenceId(DependenceEntity aDependence);
}
