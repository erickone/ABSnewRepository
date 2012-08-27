/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.UnitMeasureGoalDao;
import com.abs.siif.programming.entities.UnitMeasureGoalEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar Implementa los metodos de negocio para
 * las unidades de medida Preficha->Meta
 */
@Service("unitMeasureGoalManagement")
public class UnitMeasureGoalManagementImpl implements UnitMeasureGoalManagement {

    @Resource(name = "unitMeasureGoalDao")
    private UnitMeasureGoalDao itsUnitMeasureGoalDao;

    @Override
    public Collection<UnitMeasureGoalEntity> getUnitMeasureGoals() {
        return itsUnitMeasureGoalDao.getUnitMeasureGoals();
    }
}
