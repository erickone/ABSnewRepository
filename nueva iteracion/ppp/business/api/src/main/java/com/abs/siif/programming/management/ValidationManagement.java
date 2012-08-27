/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

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
public interface ValidationManagement {

    Collection<ValidationEntity> getValidationByComponentId(ComponentEntity aComponentEntity);

    Collection<ComponentEntity> getComponentsByDraftProjectId(Long aDraftProjectId);

    DeliveryEntity getDeliveryByDraftProject(DraftProjectEntity aDraftProjectId);

    Collection<ComponentEntity> getComponentsByDelivery(DeliveryEntity aDelivery);

    List<Long> saveSeplanValidation(List<ValidationEntity> aValidationEntityList);
    
    public void exitValidationSEPLAN(Long aDraftProjectId);
}