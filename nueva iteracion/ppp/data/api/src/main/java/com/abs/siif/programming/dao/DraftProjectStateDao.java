/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.DraftProjectStateEntity;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Juan Antonio Zavala Aguilar Define los metodos de acceso a datos al
 * estado del proyecto
 */
public interface DraftProjectStateDao extends SIIFBaseDao<DraftProjectStateEntity, Long> {

    Collection<DraftProjectStateEntity> getAllDraftProjectState();

    void saveAll(Collection<DraftProjectStateEntity> anEntities);

    void deleteAll(Collection<DraftProjectStateEntity> anEntities);

}