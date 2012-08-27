package com.abs.siif.planning.management;

import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import java.util.List;

/**
 * Sirve para le interconexion entre la GUI y la Data Tier verifica reglas de
 * negocio a travez de Validadores de negocios
 *
 * @author Juan Antonio Zavala Aguilar
 *
 */
public interface ObjectiveLevelManagement {

    List<ObjectiveLevelEntity> getAllObjectiveLevels();

    Long saveOrUpdate(ObjectiveLevelEntity anObjectiveLevel);

    void delete(List<ObjectiveLevelEntity> anObjectiveLevels);

    ObjectiveLevelEntity getObjectiveLevelByLevel(short aLevel);
}
