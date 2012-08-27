/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.UnitMeasureBenefAndGoalDao;
import com.abs.siif.programming.entities.UnitMeasureBenefAndGoalEntity;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar Implementa los metodos de negocio para
 * las unidades de medida Preficha->Meta beneficiarios
 */
@Service("unitMeasureBenefAndGoalManagement")
public class UnitMeasureBenefAndGoalManagementImpl implements UnitMeasureBenefAndGoalManagement {

    @Resource(name = "unitMeasureBenefAndGoalDao")
    private UnitMeasureBenefAndGoalDao itsUnitMeasureBenefAndGoalDao;

    @Override
    public Collection<UnitMeasureBenefAndGoalEntity> getUnitMeasureBenefAndGoals() {
        return itsUnitMeasureBenefAndGoalDao.getUnitMeasureBenefAndGoals();
    }
}
