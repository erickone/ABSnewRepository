/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.InputPhysicalAndFinEntity;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abs70
 */
@Repository("inputPhysicalAndFinDao")
public class InputPhysicalAndFinDaoImpl
        extends SIIFBaseDaoImpl<InputPhysicalAndFinEntity, Long>
        implements InputPhysicalAndFinDao {

    @Autowired
    private SessionFactory itsSessionFactory;

    @Transactional(readOnly=true)
    @Override
    public InputPhysicalAndFinEntity getInputPhysicalFin(Long idInpreFIle) {
     String myQueryString = "select p from InputPhysicalAndFinEntity p"
                + " left outer join p.inputPhysicalAndFinInvPreFile inv"
                + " where (inv.invPreFileId='%s')";
        myQueryString = String.format(myQueryString, idInpreFIle.toString());
        List myResults = super.find(myQueryString);
        InputPhysicalAndFinEntity myEntity = new InputPhysicalAndFinEntity();
        if (myResults.size() > 0) {
            myEntity = (InputPhysicalAndFinEntity) myResults.get(0);
        }

        return myEntity;
    }

    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }
}
