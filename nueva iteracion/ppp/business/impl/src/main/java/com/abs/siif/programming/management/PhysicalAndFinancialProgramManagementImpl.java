/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.budget.dao.BudgetKeyDao;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import com.abs.siif.budget.entities.MensualBudgetKeyEntity;
import com.abs.siif.programming.dao.*;
import com.abs.siif.programming.dto.PhysicalAndFinancialProgramDto;
import com.abs.siif.programming.entities.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("physicalAndFinancialProgramManagement")
public class PhysicalAndFinancialProgramManagementImpl
        implements PhysicalAndFinancialProgramManagement, Serializable {

    @Resource(name = "physicalAndFinancialProgramDao")
    PhysicalAndFinancialProgramDao pAndFinProg;
    @Resource(name = "physicalProgrammedAdvanceDao")
    PhysicalProgrammedAdvanceDao physicalProgrammedAdvanceDao;
    @Resource(name = "inputPhysicalAndFinDao")
    InputPhysicalAndFinDao inputPhysicalAndFinDao;
    @Resource(name="invPreFileDao")
    InvPreFileDao invPreFileDao;
    @Resource(name="financialAdvancedProgramBudgetKeyDao")
    FinancialAdvancedProgramBudgetKeyDao financialAdvancedProgramBudgetKeyDao;
    @Resource(name="budgetKeyDao")
    BudgetKeyDao budgetKeyDao;
   
    @Autowired
    private SessionFactory theirSessionFactory;
   
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }
    
    @Override
    public PhysicalAndFinancialProgramDto getPhysicalAndFinancialProgrambyPrefileId(Long aPreFileId) {           
        PhysicalAndFinancialProgramDto dto = new PhysicalAndFinancialProgramDto();        
        PhysicalAndFinancialProgramEntity entityHeader = 
             pAndFinProg.getPhysicalAndFinancialProgramByPreFileId(aPreFileId);
        if(entityHeader != null)
            dto.setHeader(entityHeader);
        else
            dto.setHeader(new PhysicalAndFinancialProgramEntity());
        Collection<PhysicalProgrammedAdvanceEntity> myPhysicalProgrammedAdvance =  physicalProgrammedAdvanceDao.getPhysicalProgrammedByInvPreFile(aPreFileId);
        if(myPhysicalProgrammedAdvance != null){
            dto.setAdvProgram((List<PhysicalProgrammedAdvanceEntity>)myPhysicalProgrammedAdvance);
        }
        else
        {
            dto.setAdvProgram(new ArrayList<PhysicalProgrammedAdvanceEntity>());
        }
        dto.setAportations(inputPhysicalAndFinDao.getInputPhysicalFin(aPreFileId));
        return dto;
    }

    @Override
    public String savePhysicalAndFinancialProgram(
            PhysicalAndFinancialProgramDto aPhysicalAndFinancialDto) {
        
        PhysicalAndFinancialProgramEntity entityPFProg = 
                aPhysicalAndFinancialDto.getHeader();
        InvPreFileEntity entityInvPre = 
                entityPFProg.getPhysicalAndFinancialProgramInvPreFile();
        
           entityInvPre=   invPreFileDao.findById(
                   entityInvPre.getInvPreFileId(), true);
           
           entityPFProg.setPhysicalAndFinancialProgramInvPreFile(entityInvPre);
           
           if(entityPFProg.getProgPhysicFinancialId() != null){
               pAndFinProg.merge(entityPFProg);
           }else{
                pAndFinProg.save(entityPFProg);
           }
           
          List<PhysicalProgrammedAdvanceEntity> entityList = 
                  aPhysicalAndFinancialDto.getAdvProgram();
          for(PhysicalProgrammedAdvanceEntity aEntity : entityList){
              aEntity.setPhysicalProgrammedAdvancePreFile(entityInvPre);
              if(aEntity.getPhysicalProgrammedAdvanceId() != null){
                  physicalProgrammedAdvanceDao.merge(aEntity);
              }else{
                  physicalProgrammedAdvanceDao.save(aEntity);
              }
          }
          
          InputPhysicalAndFinEntity inputPF = aPhysicalAndFinancialDto.getAportations();
          inputPF.setInputPhysicalAndFinInvPreFile(entityInvPre);
          if(inputPF.getInputPhysicalAndFinEntityId() != null){
              inputPhysicalAndFinDao.merge(inputPF);
          }else{
              inputPhysicalAndFinDao.save(inputPF);
          }
        
        return "";
    }

    @Override
    public List<InputEntity> getAllInputInitialAsig(Long id) {
          List<InputEntity> inputEntitys = null;
        try{
             inputEntitys = pAndFinProg.getAllInputInitialAsig(id);                   
        }
        catch(Exception ex){
            Logger.getLogger(FinancialStructureManagmentImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inputEntitys;
    }

    @Override
    public BenefAndGoalsEntity getBenefAndGoalsByInvPreFile(Long invPreFileId) {
         BenefAndGoalsEntity benefAndGoals = null;
        try{
             benefAndGoals =pAndFinProg.getBenefAndGoalsByInvPreFile(invPreFileId);                   
        }
        catch(Exception ex){
            Logger.getLogger(FinancialStructureManagmentImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return benefAndGoals;
    }
    
    @Override
    public List<BudgetKeyEntity> getFinancialAdvProg(Long invPreFileId){
        List<BudgetKeyEntity> budgetKeyList = new ArrayList<BudgetKeyEntity>();
        try{
            budgetKeyList = budgetKeyDao.getBudgetKeyByInvPreFileId(invPreFileId);
            for(BudgetKeyEntity budget: budgetKeyList){
                List<MensualBudgetKeyEntity> mensualAdvanced = financialAdvancedProgramBudgetKeyDao.getMensualAdvancedByBudgetKeyId(budget.getBudgetKeyId());
                budget.setMensualBudgetKeyBudgetKey(new HashSet<MensualBudgetKeyEntity>(mensualAdvanced));
            }
        }
        catch(Exception ex){
            Logger.getLogger(FinancialStructureManagmentImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return budgetKeyList;
    }
    
    @Override
    public BudgetKeyEntity getBudgetKeyById(Long budgetKeyId){
        BudgetKeyEntity budgetKey = new BudgetKeyEntity();
        try{
            budgetKey = budgetKeyDao.getBudgetKeyById(budgetKeyId);       
        }catch(Exception ex){
            Logger.getLogger(FinancialStructureManagmentImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return budgetKey;
    }
    
    @Override
    public void saveFinancialAdvancedProg(List<MensualBudgetKeyEntity> mensualBudgetKeyList){        
        try{
            for(MensualBudgetKeyEntity mensualBudgetKeyEntity: mensualBudgetKeyList){
                if(mensualBudgetKeyEntity.getMensualBudgetKeyId() == null || mensualBudgetKeyEntity.getMensualBudgetKeyId() == 0){
                    financialAdvancedProgramBudgetKeyDao.saveFinancialAdvancedProgram(mensualBudgetKeyEntity);  
                }else{
                    financialAdvancedProgramBudgetKeyDao.mergeFinancialAdvancedProgram(mensualBudgetKeyEntity);
                }
            }        
        }
        catch(Exception ex){
            Logger.getLogger(FinancialStructureManagmentImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
