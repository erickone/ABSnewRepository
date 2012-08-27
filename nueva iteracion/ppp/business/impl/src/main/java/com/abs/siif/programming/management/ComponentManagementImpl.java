/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.ComponentDao;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.DeliveryEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("componentManagement")
public class ComponentManagementImpl implements ComponentManagement, Serializable
{

    @Resource(name = "componentImpl")
    ComponentDao itsComponentDao;

    @Override
    public List<ComponentEntity> getComponentByDelivery(DeliveryEntity aDeliveryEntity)
    {
        return this.itsComponentDao.getComponentByDelivery(aDeliveryEntity);
    }
}
