/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.base.management.ManagementBase;
import com.abs.siif.programming.dao.*;
import com.abs.siif.programming.entities.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Israel Ruiz
 */
@Service("deliveryManagement")
public class DeliveryManagementImpl extends ManagementBase implements DeliveryManagement {

    @Resource(name = "deliveryDaoImpl")
    private DeliveryDao deliveryDao;
    @Resource(name = "componentImpl")
    ComponentDao componentDao;
    @Resource(name = "activityDao")
    ActivityDao activityDao;
    @Resource(name = "unitMeasureDaoImpl")
    UnitMeasureDao unitMeasureDao;
    @Resource(name = "mensualComponentImpl")
    MensualComponentDao mensualComponentDao;
    @Resource(name = "mensualActivityDao")
    MensualActivityDao mensualActivityDao;

    @Override
    public List<DeliveryEntity> getDeliveries(DraftProjectEntity draftProj) {
        List<DeliveryEntity> refDeliveries = deliveryDao.getDeliveriesByDraftProject(draftProj);
        for (DeliveryEntity delivery : refDeliveries) {
            delivery.setComponents(
                    new HashSet<ComponentEntity>(getComponents(delivery)));
        }
        return refDeliveries;

    }

    @Transactional(readOnly = true)
    @Override
    public List<ComponentEntity> getComponents(DeliveryEntity delivery) {
        List<ComponentEntity> compList =
                componentDao.getComponentByDelivery(delivery);
        List<MensualComponentEntity> month;
        for (ComponentEntity comp : compList) {
            comp.setActivities(
                    new HashSet<ActivityEntity>(getActivities(comp)));
            month = mensualComponentDao.getMensualComponentByComponent(comp);
            comp.setMensualcomponents(new HashSet<MensualComponentEntity>(month));
            comp.setDelivery(delivery);
        }

        return compList;

    }

    @Transactional(readOnly = true)
    @Override
    public List<ActivityEntity> getActivities(ComponentEntity component) {
        List<ActivityEntity> activities = activityDao.getActivityByComponent(
                component);
        List<MensualActivityEntity> month;

        for (ActivityEntity activity : activities) {
            month = mensualActivityDao.getMensualActivityByActivity(activity);
            activity.setMensualactivities(
                    new HashSet<MensualActivityEntity>(month));
        }
        return activities;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UnitMeasureEntity> getUnitMeasureCatalog() {
        List<UnitMeasureEntity> result =
                new ArrayList<UnitMeasureEntity>(
                unitMeasureDao.getAllUnitMeasure());
        return result;
    }

    @Transactional(readOnly = false)
    @Override
    public DeliveryEntity deleteComponent(ComponentEntity component) {
        Long deliveryId = component.getDelivery().getDeliveryId();
        component = componentDao.findById(component.getComponentId(), true);
        componentDao.delete(component);

        DeliveryEntity delivery = deliveryDao.getDeliveryById(deliveryId);
        delivery.setComponents(
                new HashSet<ComponentEntity>(getComponents(delivery)));

        return delivery;
    }

    @Transactional(readOnly = false)
    @Override
    public DeliveryEntity saveComponent(ComponentEntity component) {
        ComponentEntity componentSave;
        Set<MensualComponentEntity> menComps = component.getMensualcomponents();

        Long deliveryId = component.getDelivery().getDeliveryId();
        DeliveryEntity delivery = deliveryDao.getDeliveryById(deliveryId);
        component.setDelivery(delivery);
        component.setMensualcomponents(null);
        if (component.getComponentId() != null) {
            componentSave = componentDao.merge(component);

        } else {
            componentSave = componentDao.persist(component);
        }

        MensualComponentEntity attach = null;

        for (MensualComponentEntity month : menComps) {
            month.setComponent(componentSave);
            if (month.getMensualComponentId() == null) {
                mensualComponentDao.save(month);
            } else if (month.getMensualComponentId() != null) {
                attach = mensualComponentDao.findById(month.getMensualComponentId(), true);
                attach.setProgrammedGoal(month.getProgrammedGoal());
                mensualComponentDao.merge(attach);
            }
        }


        delivery.setComponents(
                new HashSet<ComponentEntity>(getComponents(delivery)));

        return delivery;
    }

    @Transactional(readOnly = false)
    @Override
    public List<DeliveryEntity> addPropositToDelivery(DeliveryEntity delivery) {
        DeliveryEntity deliveryResult;

        if (delivery.getDeliveryId() == null) {
            deliveryResult = deliveryDao.persist(delivery);
        } else {
            deliveryResult = deliveryDao.merge(delivery);
        }

        return getDeliveries(deliveryResult.getDraftProject());
    }

    @Transactional(readOnly = false)
    @Override
    public ComponentEntity addActivityToComp(ActivityEntity activity) {

        ComponentEntity compResult;
        ActivityEntity activityResult;

        Set<MensualActivityEntity> menAct = activity.getMensualactivities();
        activity.setMensualactivities(null);

        compResult = componentDao.getComponentById(
                activity.getComponent().getComponentId());

        compResult.setActivities(
                new HashSet<ActivityEntity>(getActivities(compResult)));
        // Se obtiene su prog Mesual del componente
        activity.setComponent(compResult);


        if (compResult.getActivities() == null
                || compResult.getActivities().isEmpty()) {
            compResult.setActivities(new HashSet<ActivityEntity>());
            compResult.getActivities().add(activity);
        }


        if (activity.getActivityId() == null) {
            activityResult = activityDao.persist(activity);
        } else {
            activityResult = activityDao.merge(activity);
        }

        for (MensualActivityEntity month : menAct) {
            month.setActivity(activityResult);
            if (month.getMensualActivityId() == null) {
                mensualActivityDao.persist(month);
            } else {
                mensualActivityDao.merge(month);
            }
        }

        compResult = componentDao.getComponentById(
                activityResult.getComponent().getComponentId());
        // Se obtiene su prog Mesual del componente
        List<MensualComponentEntity> month =
                mensualComponentDao.getMensualComponentByComponent(compResult);
        compResult.setMensualcomponents(new HashSet<MensualComponentEntity>(month));
        // Se obtine la lista actualizada de Activeidades
        compResult.setActivities(
                new HashSet<ActivityEntity>(getActivities(compResult)));


        return compResult;
    }

    @Transactional(readOnly = false)
    @Override
    public ComponentEntity deleteActivity(ActivityEntity activity) {

        Long compId = activity.getComponent().getComponentId();
        
        ComponentEntity com = componentDao.getComponentById(compId);
        List<MensualComponentEntity> month = mensualComponentDao.getMensualComponentByComponent(com);
        com.setMensualcomponents(new HashSet<MensualComponentEntity>(month));
        
        Set<ActivityEntity> activities = new HashSet<ActivityEntity>(getActivities(com));

        activities.remove(activity);
        ActivityEntity toDelete = activityDao.findById(activity.getActivityId(), true);

        activityDao.delete(toDelete);

        com.setActivities(activities);
        return com;
    }
}
