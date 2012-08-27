/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dto.DraftProjectSearchByDto;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface DraftProjectSearchManagement
{
  List<DraftProjectSearchByDto> getFilteredDraftProjectDTO(DraftProjectEntity anEntity);
  
}
