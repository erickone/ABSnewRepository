/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.MensualBudgetKeyEntity;
import com.abs.siif.programming.dto.FinancialAdvancedBudgetKeyDto;
import com.abs.siif.programming.entities.BenefAndGoalsEntity;
import com.abs.siif.programming.entities.InputEntity;
import com.abs.siif.programming.entities.PhysicalAndFinancialProgramEntity;
import com.abs.siif.support.InputTypeEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jacob.flores
 */
@Repository("physicalAndFinancialProgramDao")
public class PhysicalAndFinancialProgramDaoImpl extends SIIFBaseDaoImpl<PhysicalAndFinancialProgramEntity, Long>
        implements PhysicalAndFinancialProgramDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public PhysicalAndFinancialProgramEntity getPhysicalAndFinancialProgramByPreFileId(Long aPreFileId) {
        String myQueryString = "select p from PhysicalAndFinancialProgramEntity p"
                + " left outer join p.physicalAndFinancialProgramInvPreFile inv"
                + " where (inv.invPreFileId=" + aPreFileId + ")";
        //myQueryString = String.format(myQueryString, aPreFileId);
        List myResults = super.find(myQueryString);
        PhysicalAndFinancialProgramEntity myEntity = new PhysicalAndFinancialProgramEntity();
        if (myResults.size() > 0) {
            myEntity = (PhysicalAndFinancialProgramEntity) myResults.get(0);
        }

        return myEntity;

    }
    
    @Transactional(readOnly = true)
    @Override
    public List<InputEntity> getAllInputInitialAsig(Long id) {
        String myQueryHQL;
        List find = null;
        try{
            myQueryHQL="select ie from InputEntity ie"
                    + " where ie.inputType like '"+InputTypeEnum.ASIGNACION.name()
                    + "' and ie.inputInvPreFile.invPreFileId = " + id;
            find = this.find(myQueryHQL);                  
        }
        catch(Exception ex){
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return find;
    }
    
    @Transactional(readOnly = true)
    @Override
    public BenefAndGoalsEntity getBenefAndGoalsByInvPreFile(Long invPreFileId) {
        BenefAndGoalsEntity benefAndGoalsEntity = null;
        String myQueryString = "select b from BenefAndGoalsEntity b"
                + " left join fetch b.invPreFile inv"
                + " where (inv.invPreFileId=" + invPreFileId + ")";
        //myQueryString = String.format(myQueryString, invPreFileId);

        List myResults = super.find(myQueryString);
        if(myResults != null && myResults.size() > 0){
            benefAndGoalsEntity = (BenefAndGoalsEntity) myResults.get(0);
        }
        return benefAndGoalsEntity;      
    }
    
}
