/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.programming.dao.ComponentDao;
import com.abs.siif.programming.dao.ValidationDao;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.DeliveryEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.ValidationEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("validationManagement")
public class ValidationManagementImpl implements ValidationManagement, Serializable {

    @Resource(name = "validationDao")
    ValidationDao itsValidationDao;
    @Resource(name = "componentImpl")
    ComponentDao itsComponentDao;

    @Override
    public Collection<ValidationEntity> getValidationByComponentId(ComponentEntity aComponentEntity) {
        return itsValidationDao.getValidationByComponentId(aComponentEntity);
    }

    @Override
    public Collection<ComponentEntity> getComponentsByDraftProjectId(Long aDraftProjectId) {
        return itsValidationDao.getComponentsByDraftProjectId(aDraftProjectId);
    }

    @Override
    public DeliveryEntity getDeliveryByDraftProject(DraftProjectEntity aDraftProjectId) {
        return itsValidationDao.getDeliveryByProjectId(aDraftProjectId);
    }

    @Override
    public Collection<ComponentEntity> getComponentsByDelivery(DeliveryEntity aDelivery) {
        return itsComponentDao.getComponentByDelivery(aDelivery);
    }

    @Override
    public List<Long> saveSeplanValidation(List<ValidationEntity> aValidationEntityList) {
        return itsValidationDao.saveValidationEntities(aValidationEntityList);
    }

    @Override
    public void exitValidationSEPLAN(Long aDraftProjectId) {
        ValidationEntity myValidationEntity = itsValidationDao.getValidationSEPLAN(aDraftProjectId);

        if (myValidationEntity == null) {
            throw new BaseBusinessException("No existe validación SEPLAN para este proyecto");
        }


    }
}