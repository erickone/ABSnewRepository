/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.DeliveryEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.ValidationEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface ValidationDao {

    Collection<ValidationEntity> getValidationByComponentId(ComponentEntity aComponentEntity);

    Collection<ComponentEntity> getComponentsByDraftProjectId(Long aDraftProjectId);

    List<Long> saveValidationEntities(List<ValidationEntity> aValidationEntity);

    DeliveryEntity getDeliveryByProjectId(DraftProjectEntity aDraftProjectEntity);

    ValidationEntity getValidationSEPLAN(Long aDraftProject);
}

