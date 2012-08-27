/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.programming.entities.SizingEntity;
import java.util.Collection;

/**
 *
 * @author Erick Leija
 */
public interface SizingDao
{
    int getTheTotalAmounts(Long aPreFileId);
    int getTheTotalAditionals(Long aPreFileId);
    Collection<SizingEntity> getListOfSizingByPreFileId(Long aPrefileId);
    Long saveSizing(SizingEntity aSizingEntity);
    String deleteSizing(Long aSizingEntityId);
}
