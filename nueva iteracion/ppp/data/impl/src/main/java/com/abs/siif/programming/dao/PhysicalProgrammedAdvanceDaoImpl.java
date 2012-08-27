/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.PhysicalProgrammedAdvanceEntity;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abs70
 */
@Repository("physicalProgrammedAdvanceDao")
public class PhysicalProgrammedAdvanceDaoImpl
        extends SIIFBaseDaoImpl<PhysicalProgrammedAdvanceEntity, Long>
        implements PhysicalProgrammedAdvanceDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }

    @Transactional(readOnly=true)
    @Override
    public Collection<PhysicalProgrammedAdvanceEntity> getPhysicalProgrammedByInvPreFile(Long anIdentity) {
       String myQueryString="select p from PhysicalProgrammedAdvanceEntity p"
               + " left  outer join  p.physicalProgrammedAdvancePreFile inv"
               + " where (inv.invPreFileId="+ anIdentity +") order by p.physicalProgrammedAdvanceMonth";
       
       //myQueryString=String.format(myQueryString, anIdentity);
       return super.find(myQueryString);
    }

   
}
