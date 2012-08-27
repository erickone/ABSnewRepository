/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.SizingDao;
import com.abs.siif.programming.entities.SizingEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Erick Leija
 */
@Service("sizingManagement")
public class SizingManagementImpl  implements SizingManagement, Serializable
{
    @Resource(name="sizingDao")
    SizingDao theirSizingDao;

    @Override
    public int getTheTotalAmounts(Long aPreFileId)
    {
        return theirSizingDao.getTheTotalAmounts(aPreFileId);
    }

    @Override
    public Collection<SizingEntity> getListOfSizingByPreFileId(Long aPrefileId)
    {
       return theirSizingDao.getListOfSizingByPreFileId(aPrefileId);
    }

    @Override
    public Long saveSizing(SizingEntity aSizingEntity)
    {
        return theirSizingDao.saveSizing(aSizingEntity);
    }

    @Override
    public String deleteSizing(Long aSizingEntityId)
    {
        return theirSizingDao.deleteSizing(aSizingEntityId);
    }

    @Override
    public int getTheTotalAditionals(Long aPreFileId) {
        return theirSizingDao.getTheTotalAditionals(aPreFileId);
    }
}
