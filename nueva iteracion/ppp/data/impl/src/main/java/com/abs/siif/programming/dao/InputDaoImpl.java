/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.InputEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abs71
 */
@Repository("inputDao")
public class InputDaoImpl extends SIIFBaseDaoImpl<InputEntity, Long>
        implements InputDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    /**
    * Save a InputEntitys
    * @param inputEntitys coleccion de InputEntity to save
    */
    @Transactional(readOnly = false)
    @Override
    public List<Long> saveInputEntitys(Collection<InputEntity> inputEntitys) {
        
        List find = null;
        try{          
            for(InputEntity ie:inputEntitys){
                if(ie.getInputId()!=null && ie.getInputId() != null)
                    super.merge(ie);
                else
                    super.save(ie);
            }
//            super.saveAll(inputEntitys);
             
            find = getInputEntitysByPreFile(inputEntitys.iterator().next().getInputInvPreFile().getInvPreFileId());
         }
        catch(Exception ex){
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return find;
        
    }
    
    @Transactional(readOnly = false)
    @Override
    public List<Long> getInputEntitysByPreFile(Long id){
         String myQueryHQL;
        List find = null;
        try{           
             myQueryHQL="select inputId from InputEntity ie"
                    + " where ie.inputInvPreFile.invPreFileId = " + id +"";
            find = this.find(myQueryHQL);
         
         }
        catch(Exception ex){
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return find;
    }
    
}
