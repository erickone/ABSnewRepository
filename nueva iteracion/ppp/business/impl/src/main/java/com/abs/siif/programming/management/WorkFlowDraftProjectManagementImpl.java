/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  WorkFlowDraftProjectManagementImpl
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.management;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.planning.dto.DraftProjectDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dao.DraftProjectBinnacleDao;
import com.abs.siif.programming.dao.DraftProjectDao;
import com.abs.siif.programming.dto.DraftProjectSearchDto;
import com.abs.siif.programming.dto.DraftProjectStatusSearchDto;
import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.DraftProjectStatusEntity;
import com.abs.siif.security.entities.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("workFlowDraftProjectManagement")
public class WorkFlowDraftProjectManagementImpl
        implements WorkFlowDraftProjectManagement {
    
    @Resource(name = "draftProjectDaoImpl")
    private DraftProjectDao itsDraftProjectDao;
    @Resource(name = "draftProjectBinnacleDaoImpl")
    private DraftProjectBinnacleDao itsDraftProjectBinnacleDao;
    @Resource(name = "dependenceProgrammingManagement")
    private DependenceProgrammingManagement itsDependenceProgrammingManagement;
    @Resource(name = "draftProjectStatusManagement")
    private DraftProjectStatusManagement itsDraftProjectStatusManagement;
    @Resource(name = "draftProjectManagement")
    private DraftProjectManagement itsDraftProjectManagement;
    @Resource(name = "draftProjectBinnacleManagement")
    private DraftProjectBinnacleManagement itsBinnacleManagement;
    
    @Resource(name="validationManagement")
    private ValidationManagement itsValidationManagement;
    
    @Transactional(readOnly = false)
    @Override
    public void executeChangeStatus(DraftProjectEntity aDraftProject, DraftProjectStatusEntity aNextStatus) {
        if (aDraftProject.getDraftProjectId() == null) {
            throw new RuntimeException("No existe un registro asociado");
        }
        
        Date myCurrentDate = new Date();
        UserEntity myCurrentUser =
                (UserEntity) SIIFContextBase.getParameterSession(SessionKeyEnum.USER);
        //TODO: pendiente de consecutivo y dias reales
//
//        if (aNextStatus.getDraftProjectStatusConfigEnt().
//                isDraftProjectStatusConfigIsCancelled()) {
//            DraftProjectStatusEntity myCancelledStatus = aNextStatus;
//            aDraftProject.setDraftProjectStatus(myCancelledStatus);
//            aNextStatus = getIniStatus();
//        }
        
        if (!(aNextStatus.getDraftProjectStatusConfigEnt() == null) 
                && aNextStatus.getDraftProjectStatusConfigEnt()
                .isDraftProjectStatusConfigIsValidateSEPLAN())
        {
            itsValidationManagement.exitValidationSEPLAN(aDraftProject
                    .getDraftProjectId());
        }
        
        DraftProjectBinnacleEntity myBinnacle = new DraftProjectBinnacleEntity();
        myBinnacle.setDraftProject(aDraftProject);
        myBinnacle.setDraftProjectBinnacleConsecutive(0);
        myBinnacle.setDraftProjectBinnacleDate(myCurrentDate);
        myBinnacle.setDraftProjectBinnacleRealDay(0);
        myBinnacle.setDraftProjectStatus(aNextStatus);
        myBinnacle.setUserBinnacle(myCurrentUser);
        
        aDraftProject.setDraftProjectStatus(aNextStatus);
        
        itsDraftProjectDao.merge(aDraftProject);
        itsDraftProjectBinnacleDao.Save(myBinnacle);
    }
    
    @Override
    public HashMap<String, List<?>> getSupportList() {
        HashMap<String, List<?>> mySupportList = new HashMap<String, List<?>>();
        Collection<DependenceEntity> myDependencies = itsDependenceProgrammingManagement.getDependencesByUEG();
        
        Collection<DraftProjectStatusEntity> myStatus = itsDraftProjectStatusManagement.getDraftProjectStatus();
        
        mySupportList.put("dependencies", new ArrayList<DependenceEntity>(myDependencies));
        mySupportList.put("status", new ArrayList<DraftProjectStatusEntity>(myStatus));
        
        return mySupportList;
    }
    
    @Override
    public Collection<DraftProjectStatusEntity> getPossibleStatus(DraftProjectStatusSearchDto aDraftProjectStatusSearchDto) {
        return itsDraftProjectStatusManagement.getStatus(aDraftProjectStatusSearchDto);
    }
    
    @Override
    public DraftProjectStatusEntity getInitialStatus() {
        return itsDraftProjectStatusManagement.getIniStatus();
    }
    
    @Override
    public DraftProjectStatusEntity getFinalStatus() {
        return itsDraftProjectStatusManagement.getEndStatus();
    }
    
    @Override
    public Collection<DraftProjectEntity> getDraftProjectsInWorkFlow(DraftProjectSearchDto aDraftProjectSearchDto) {
        return itsDraftProjectManagement.getDraftProInWorkFlow(aDraftProjectSearchDto);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void executeChangeStatus(Collection<DraftProjectEntity> aDraftProjects, DraftProjectStatusEntity aNextStatus) {
        for (DraftProjectEntity draftProjectEntity : aDraftProjects) {
            executeChangeStatus(draftProjectEntity, aNextStatus);
        }
    }
    
    @Override
    public Collection<DraftProjectBinnacleEntity> getBinnaclebyDraftProjectId(Long aDraftProjectID) {
        return itsBinnacleManagement.getDraftProjectBinnaclebyDraftProjectId(aDraftProjectID);
    }
    
    @Override
    public Collection<DraftProjectEntity> getDraftProjectsByFilter(DraftProjectSearchDto aDraftProjectSearchDto) {
        return itsDraftProjectDao.getDraftProjectsByFilter(aDraftProjectSearchDto);
    }
}
