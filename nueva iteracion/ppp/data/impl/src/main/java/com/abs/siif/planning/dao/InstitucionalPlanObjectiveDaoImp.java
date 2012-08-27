/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  InstitucionalPlanObjectiveDaoImp
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.entities.InstitutionalPlanEntity;
import com.abs.siif.planning.entities.InstitutionalPlanObjectiveEntity;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Francisco Luna
 */
@Repository("institucionalPlanObjectiveDao")
public class InstitucionalPlanObjectiveDaoImp extends SIIFBaseDaoImpl<InstitutionalPlanObjectiveEntity, Long>
            implements InstitucionalPlanObjectiveDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = false)
    @Override
    public void saveInstitucionalObjective(List<InstitutionalPlanObjectiveEntity> listToSave) {
        for(InstitutionalPlanObjectiveEntity current : listToSave){
           try{ 
                if(current.getInstitutionalPlanObjectiveId() != null){
                    merge(current);
                }else{
                    save(current);
                }
            }catch(Exception e){
                String a = e.toString();
           }
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<InstitutionalPlanObjectiveEntity> searchObjectives(InstitutionalPlanEntity input) {
        String myQueryString = "from InstitutionalPlanObjectiveEntity as insObj"
                + " where insObj.institutionalPlanEntity.institutionalPlanId = :institutionalPlanId";    
        
        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("institutionalPlanId", input.getInstitutionalPlanId());

        return mySQLQuery.list();
        
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteInstitucionalObjective(List<InstitutionalPlanObjectiveEntity> listToSave) {
        deleteAll(listToSave);
    }
    
}
