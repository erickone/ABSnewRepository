/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.*;
import java.util.List;

/**
 * Define las operaciones requeridas por el entregable 
 * que se fine en el anteproyecto   
 * @author Israel Ruiz
 */
public interface DeliveryManagement {
    /**
     * Obtien el entregable que se asocia al anteroyecto
     * Cada entregable tendra su lista de componentes  y cada
     * componente a su vez tendra su lista de Actividades 
     * @param draftProj  anteproyecto que se esta trabajando
     * @return Lista de Entregables que se tienen actualmente
     */
    public List<DeliveryEntity> getDeliveries(DraftProjectEntity draftProj);
    /**
     * Obtener los componentes que pertenecen al entregable
     * @param delivery
     * @return lista de componentes actualizados
     */
    public List<ComponentEntity> getComponents(DeliveryEntity delivery);
    /**
     * Obtener la lista de actividades que se asocian al componente
     * @param component
     * @return 
     */
    public List<ActivityEntity> getActivities(ComponentEntity component);
    /**
     * Obtiene el catalogo de unidades de medidas
     * hacer utilizado para registrar componentes
     * @return 
     */
    public List<UnitMeasureEntity> getUnitMeasureCatalog();
    /**
     * Elimina el componente solicitado del entregable
     * @param component 
     * @return entrega el entregable actualizado
     */
    public DeliveryEntity deleteComponent(ComponentEntity component);
     /**
     * Guarda el Componente indicado
     * @param component 
     * @return entrega el entregable actualizado
     */
    public DeliveryEntity saveComponent(ComponentEntity component);
    /**
     *  Agrega proposito al entregable del anteproyecto
     * @param delivery entregable en el cual lleva la descripción del 
     * proposito
     * @return la Lista actualizada de entregables del anteproyecto
     */
    public List<DeliveryEntity> addPropositToDelivery(DeliveryEntity delivery);
    /**
     * Agrega actividad al componente   
     * @param activity
     * @return El componente actualizado con sus Actividades
     */
    public ComponentEntity addActivityToComp(ActivityEntity  activity);
    /**
     * Elimina la actividad del componente que se asocia
     * @param acticity
     * @return  El componente actualizado
     */
    public ComponentEntity deleteActivity(ActivityEntity acticity);
     
    
}
