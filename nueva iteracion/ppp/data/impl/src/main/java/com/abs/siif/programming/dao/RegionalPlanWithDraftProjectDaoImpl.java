/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  RegionalPlanWithDraftProjectDaoImpl
 *  Purpose:  [ Esta es la implementación de los planes regionales seleccionados
 *  en la pantalla de vinculación al PED]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.RegionalPlansOfPEDEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
@Repository("regionalPlanWithDraftProjectDao")
public class RegionalPlanWithDraftProjectDaoImpl
        extends SIIFBaseDaoImpl<RegionalPlansOfPEDEntity, Long>
        implements RegionalPlanWithDraftProjectDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = false)
    @Override
    public Long saveRegionalPlanAndDraftProject(RegionalPlansOfPEDEntity anEntity) {
        return super.save(anEntity).getPEDregionalPlanId();
    }

    @Transactional(readOnly = false)
    @Override
    public Long persistsaveRegionalPlanAndDraftProject(RegionalPlansOfPEDEntity anEntity) {
        return super.merge(anEntity).getPEDregionalPlanId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<RegionalPlansOfPEDEntity> getRegionalPlanByDraftProjectId(Long aDraftProjectId) {

        String myQueryString = "select distinct PED from RegionalPlansOfPEDEntity PED"
                + " left join fetch PED.regionalPlansOfPED"
                + " where PED.PEDdraftProjectId = " + aDraftProjectId;

        return super.find(myQueryString);
    }

    @Transactional(readOnly = false)
    @Override
    public String deleteAllRegionalPlansByDraftProjectIDAndRegionClassifierId(Long aDraftProjectId, Long aRegionalClassifier) {
        String myQueryString = "delete RegionalPlansOfPEDEntity PED"
                + " where (PED.PEDdraftProjectId = :aDraftProjectId)"
                + " and (PED.regionalPlansOfPED = :aRegionalClassifier)";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);
        myQuery.setLong("aDraftProjectId", aDraftProjectId);
        myQuery.setLong("aRegionalClassifier", aRegionalClassifier);
        myQuery.executeUpdate();


        return "Success";
    }
}
