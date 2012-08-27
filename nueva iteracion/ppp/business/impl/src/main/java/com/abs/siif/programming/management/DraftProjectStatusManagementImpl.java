/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.DraftProjectStatusDao;
import com.abs.siif.programming.dto.DraftProjectStatusSearchDto;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("draftProjectStatusManagement")
public class DraftProjectStatusManagementImpl implements DraftProjectStatusManagement, Serializable {

    @Resource(name = "draftProjectStatusDaoImpl")
    private DraftProjectStatusDao theirDraftProjectStatusDao;

    @Override
    public Collection<DraftProjectStatusEntity> getDraftProjectStatus() {
        return theirDraftProjectStatusDao.getAllDraftProjectStatus();
    }

    @Override
    public DraftProjectStatusEntity getStatusByConsecutive(int aConsecutive) {
        return theirDraftProjectStatusDao.getStatusByConsecutive(aConsecutive);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DraftProjectStatusEntity> getStatus(
            DraftProjectStatusSearchDto aDraftProjectStatusSearchDto
            ) {
       return theirDraftProjectStatusDao.getPossibleStatus(aDraftProjectStatusSearchDto);
    }

    @Transactional(readOnly = true)
    @Override
    public DraftProjectStatusEntity getIniStatus() {
       return theirDraftProjectStatusDao.getInitialStatus();
    }

    @Transactional(readOnly = true)
    @Override
    public DraftProjectStatusEntity getEndStatus() {
        return theirDraftProjectStatusDao.getFinalStatus();
    }
}
