/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.dto.DraftProjectStatusSearchDto;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala
 */
@Repository("draftProjectStatusDaoImpl")
public class DraftProjectStatusDaoImpl extends SIIFBaseDaoImpl<DraftProjectStatusEntity, Long>
        implements DraftProjectStatusDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Cacheable("projectStatusAll")
    @Transactional(readOnly = true)
    @Override
    public Collection<DraftProjectStatusEntity> getAllDraftProjectStatus() {
        return super.findAll();
    }

    @CacheEvict(value = "projectStatusAll", allEntries = true)
    @Transactional(readOnly = false)
    @Override
    public void saveAll(Collection<DraftProjectStatusEntity> myEntities) {
        super.saveAll(myEntities);
    }

    @CacheEvict(value = "projectStatusAll", allEntries = true)
    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<DraftProjectStatusEntity> myEntities) {
        super.deleteAll(myEntities);
    }

    @Transactional(readOnly = false)
    @Override
    public DraftProjectStatusEntity getStatusByConsecutive(int aConsecutiveOfStatus) {
        String myQuery;
        DraftProjectStatusEntity myEntity = null;
        try {
            myQuery = "select dstatus from DraftProjectStatusEntity  dstatus where "
                    + " dstatus.draftProjectStatusConsecutiveStatus = " + aConsecutiveOfStatus;
            myEntity = (DraftProjectStatusEntity) super.find(myQuery).get(0);
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myEntity;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DraftProjectStatusEntity> getPossibleStatus
            (DraftProjectStatusSearchDto aDraftProjectStatusSearchDto) {
        

        String myQueryString = "select status from DraftProjectStatusEntity status"
                + " join status.draftProjectStatusChangeEnd dest"
                + " join dest.draftProjectStatusChangeProfile prof"
                + " where ("
                + "dest.beginDraftProjectStatus.draftProjectStatusId=%s)"
                + "and (prof.profileId=%s)"
                + " Order by dest.draftProjectStatusChangeOmission Desc";

        myQueryString = String.format(myQueryString,
                aDraftProjectStatusSearchDto.getDraftProject().getDraftProjectStatus().getDraftProjectStatusId().toString(),
                aDraftProjectStatusSearchDto.getProfile().getProfileId().toString());

        return super.find(myQueryString);
    }

    @Transactional(readOnly = true)
    @Override
    public DraftProjectStatusEntity getInitialStatus() {
        String myQueryString="select status from DraftProjectStatusEntity status"
                + " join status.draftProjectStatusConfigEnt conf"
                + " where conf.draftProjectStatusConfigIsInitial='t'";
        
       
        DraftProjectStatusEntity myResult = null;
        List myResults=super.find(myQueryString);
        
        if (myResults.size()>0)
            myResult=(DraftProjectStatusEntity) myResults.get(0);
        
        return myResult;
    }

    @Transactional(readOnly = true)
    @Override
    public DraftProjectStatusEntity getFinalStatus() {
         String myQueryString="select status from DraftProjectStatusEntity status"
                + " join status.draftProjectStatusConfigEnt conf"
                + " where conf.draftProjectStatusConfigIsFinal='t'";
        
        DraftProjectStatusEntity myResult=null;
        List myResults=super.find(myQueryString);
        
        if (myResults.size()>0)
            myResult=(DraftProjectStatusEntity) myResults.get(0);
        
        return myResult;
    }
}
