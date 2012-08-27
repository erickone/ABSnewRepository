/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.DraftProjectTypeEntity;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Miguel Baizabal / Juan Antonio Zavala Aguilar Define los metodos de
 * acceso a datos para los tipos de anteproyecto
 */
public interface DraftProjectTypeDao extends SIIFBaseDao<DraftProjectTypeEntity, Long> {

    Collection<DraftProjectTypeEntity> getAllDraftProjectTypes();

    void saveAll(Collection<DraftProjectTypeEntity> myEntities);

    void deleteAll(Collection<DraftProjectTypeEntity> anEntities);
}
