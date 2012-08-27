/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.management;

import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import java.util.List;

/**
 * Se impelementa clase management para dependence
 *
 * @author jacob.flores
 */
public interface DependenceManagement
{

  public List<DependenceEntity> getViewDepIsBudgetUnit();

  public List<DepencenceDto> getDependciesIsRespUnitByDependIdRelated(
          Long idDependency);

  public List<DepencenceDto> getDependciesIsExecUnitByDependIdRelated(
          Long idDependency);
  
  public List<DependenceEntity> getViewDependenciesTypeUR();
  public List<DependenceEntity> getViewDepIsExecutionUnit();
  DependenceEntity getDependenceById(Long aDependenceId);
}