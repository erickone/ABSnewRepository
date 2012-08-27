/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  FinancialAdvancedProgramBudgetKeyDao
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

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.budget.entities.MensualBudgetKeyEntity;
import com.abs.siif.programming.dto.FinancialAdvProgDto;
import com.abs.siif.programming.dto.FinancialAdvancedBudgetKeyDto;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author ABS15
 */
public interface FinancialAdvancedProgramBudgetKeyDao extends SIIFBaseDao<MensualBudgetKeyEntity, Long>{
    
    /**
     * 
     * @return 
     */
    SessionFactory getTheirSessionFactory();    
    
    /**
     * 
     * @param mensualBudgetKeyEntity 
     */
    void saveFinancialAdvancedProgram(MensualBudgetKeyEntity mensualBudgetKeyEntity);
    
     /**
     * 
     * @param mensualBudgetKeyEntity 
     */
    void mergeFinancialAdvancedProgram(MensualBudgetKeyEntity mensualBudgetKeyEntity);
    
    /**
     * 
     * @param budgetKeyId
     * @return 
     */
    List<MensualBudgetKeyEntity> getMensualAdvancedByBudgetKeyId(Long budgetKeyId);
}
