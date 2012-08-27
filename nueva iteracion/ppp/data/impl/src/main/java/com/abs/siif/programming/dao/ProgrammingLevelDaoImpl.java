/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.ProgrammingLevelEntity;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
@Repository("programminglevelDaoImpl")
public class ProgrammingLevelDaoImpl extends SIIFBaseDaoImpl<ProgrammingLevelEntity, Long>
        implements ProgrammingLevelDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }
   
    @Transactional(readOnly = true)
    @Override
    public ProgrammingLevelEntity getProgrammingLevelPreFichaForId() {
        String myQueryString = "from ProgrammingLevelEntity as progLevel"
                + " where progLevel.programmingLevelIsDraftProject = " + 
                    "'t'" + "";
        List foundedList = this.find(myQueryString);
        ProgrammingLevelEntity retObj = (ProgrammingLevelEntity)foundedList.get(0);
        
        return retObj;
    }
    
}
