/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.InputEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author abs71
 */
public interface InputDao extends SIIFBaseDao<InputEntity, Long> {
   
    /**
    * Save a InputEntitys
    * @param inputEntitys coleccion de InputEntity to save
    */
   List<Long> saveInputEntitys(Collection<InputEntity> inputEntitys);
   
   List<Long> getInputEntitysByPreFile(Long id);
}
