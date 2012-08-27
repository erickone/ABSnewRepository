/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  FinancialAdvancedProgramBudgetKeyDaoImpl
 *  Purpose:  [ short Description  ]
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
import com.abs.siif.budget.entities.MensualBudgetKeyEntity;
import com.abs.siif.programming.dto.FinancialAdvProgDto;
import com.abs.siif.programming.dto.FinancialAdvancedBudgetKeyDto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ABS15
 */
@Repository("financialAdvancedProgramBudgetKeyDao")
public class FinancialAdvancedProgramBudgetKeyDaoImpl extends SIIFBaseDaoImpl<MensualBudgetKeyEntity, Long>
                implements FinancialAdvancedProgramBudgetKeyDao { 
    
    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void saveFinancialAdvancedProgram(MensualBudgetKeyEntity mensualBudgetKeyEntity){
        super.save(mensualBudgetKeyEntity);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void mergeFinancialAdvancedProgram(MensualBudgetKeyEntity mensualBudgetKeyEntity){
        
        super.merge(mensualBudgetKeyEntity);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<MensualBudgetKeyEntity> getMensualAdvancedByBudgetKeyId(Long budgetKeyId){
        List<MensualBudgetKeyEntity> mensualBudgetKeyEntitys = new ArrayList<MensualBudgetKeyEntity>();
         String myQueryString = "select mensualBudgetKey from MensualBudgetKeyEntity as mensualBudgetKey"  +            
                                " where mensualBudgetKey.budgetKeyMensualBudgetKey  = :budgetKeyId "; 
        
        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("budgetKeyId",budgetKeyId);
        
        mensualBudgetKeyEntitys = (List<MensualBudgetKeyEntity>)mySQLQuery.list();
        return mensualBudgetKeyEntitys;
    }
}
