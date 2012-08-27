/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.DeliveryEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.ValidationEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jacob.flores
 */
@Repository("validationDao")
public class ValidationDaoImpl extends SIIFBaseDaoImpl<ValidationEntity, Long> implements ValidationDao {

    @Autowired
    private SessionFactory theirSessionFactory;
    @Resource(name = "deliveryDaoImpl")
    DeliveryDao itsDeliveryDao;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<ValidationEntity> getValidationByComponentId(ComponentEntity aComponentEntity) {
        String myQueryString = "select val from ValidationEntity val"
                + " left outer join val.Component comp"
                + " where (comp.componentId= "+ aComponentEntity.getComponentId() +")";
        //myQueryString = String.format(myQueryString, aComponentEntity.getComponentId());

        return super.find(myQueryString);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<ComponentEntity> getComponentsByDraftProjectId(Long aDraftProjectId) {
        String myQueryString = "select comp from ComponentEntity comp"
                + " left outer join comp.delivery del"
                + " left outer join del.draftProject dp"
                + " where (dp.draftProjectId= " + aDraftProjectId + ")";
        //myQueryString = String.format(myQueryString, aDraftProjectId);


        return super.find(myQueryString);
    }

    @Override
    public DeliveryEntity getDeliveryByProjectId(DraftProjectEntity aDraftProjectEntity) {
        List<DeliveryEntity> myDeliveryList = (List<DeliveryEntity>) itsDeliveryDao.getDeliveriesByDraftProject(aDraftProjectEntity);
        DeliveryEntity myDeliveryEntity = new DeliveryEntity();

        if (myDeliveryList.size() > 0) {
            myDeliveryEntity = myDeliveryList.get(0);
        }
        return myDeliveryEntity;
    }

    @Transactional(readOnly = false)
    @Override
    public List<Long> saveValidationEntities(List<ValidationEntity> aValidationEntity) {
        List find = null;
        try {
            for (ValidationEntity validation : aValidationEntity) {
                if (validation.getValidationId() != null) {
                    super.merge(validation);
                } else {
                    super.save(validation);
                }
            }
            find = getValidationByComponentId(aValidationEntity.iterator().next().getComponent().getComponentId());
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    @Transactional(readOnly = false)
    private List<Long> getValidationByComponentId(Long aComponentId) {
        String myQueryHQL;
        List find = null;
        try {
            myQueryHQL = "select ValidationId from ValidationEntity validation"
                    + " where validation.Component.componentId= " + aComponentId;

            find = this.find(myQueryHQL);

        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    @Transactional(readOnly = true)
    @Override
    public ValidationEntity getValidationSEPLAN(Long aDraftProjectId) {
        String myQueryString = "select val from ValidationEntity val"
                + " left outer join val.Component comp"
                + " left outer join comp.delivery del"
                + " left outer join del.draftProject proy"
                + " where (proy.draftProjectId= "+ aDraftProjectId +")";
        //myQueryString = String.format(myQueryString, aDraftProjectId);


        ValidationEntity myValidation = null;

        List myResults = super.find(myQueryString);

        if (myResults.size() > 0) {
            myValidation = (ValidationEntity) myResults.get(0);
        }

        return myValidation;
    }
}
