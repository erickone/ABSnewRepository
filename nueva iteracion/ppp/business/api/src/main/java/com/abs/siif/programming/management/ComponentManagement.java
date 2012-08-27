/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.DeliveryEntity;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface ComponentManagement
{
    List<ComponentEntity> getComponentByDelivery(DeliveryEntity aDeliveryEntity);
}