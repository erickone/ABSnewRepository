/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.SefinValidationEntity;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface SefinValidationDao
{
 List<Long> saveSefinValidation(List<SefinValidationEntity> aSefinValidationEntity);
 List<SefinValidationEntity> getSefinValidationByComponent(ComponentEntity aComponentEntity);
}