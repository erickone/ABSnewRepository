/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.programming.entities.InputPhysicalAndFinEntity;
import java.io.Serializable;

/**
 *
 * @author Israel Ruiz
 */
public interface InputPhysicalAndFinDao extends 
         SIIFBaseDao<InputPhysicalAndFinEntity, Long>  {
    /**
     * 
     * @param idInpreFIle
     * @return 
     */
    public InputPhysicalAndFinEntity getInputPhysicalFin(Long idInpreFIle);
   }
