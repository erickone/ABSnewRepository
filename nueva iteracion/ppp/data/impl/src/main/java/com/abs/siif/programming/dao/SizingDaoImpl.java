/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.SizingEntity;
import java.util.Collection;
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
@Repository("sizingDao")
public class SizingDaoImpl
        extends SIIFBaseDaoImpl<SizingEntity, Long>
        implements SizingDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public int getTheTotalAmounts(Long aPreFileId) {

        /*String myQueryString = "SELECT "
                + "(inputFederal + inputMunicipality + inputParticular + inputSpecie + inputState) as sum"
                + " FROM InputEntity as IE where IE.inputInvPreFile = :aPreFileId  and tipo='ASIGNACION'";*/
        String myQueryString = "SELECT "
                + "(inputState) as sum"
                + " FROM InputEntity as IE where IE.inputInvPreFile = :aPreFileId  and tipo='ASIGNACION'";
        int myInt = 0;

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString).
                setLong("aPreFileId", aPreFileId);

        List resultLst = myQuery.list();

        if (resultLst.size() > 0) {
            double myDob = Double.parseDouble(String.valueOf(resultLst.get(0)));
            myInt = (int) myDob;
        }
        return myInt;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<SizingEntity> getListOfSizingByPreFileId(Long aPrefileId) {
        String myQueryString = "select iddimensionamiento, adicional,"
                + " cantidad, consecutivo, descripcion, inicial, porcentaje,"
                + " suma, idpreficha from siifpppdimensionamiento "
                + " where idpreficha = :aPrefileId order by iddimensionamiento";

        Query myQuery = theirSessionFactory.getCurrentSession().createSQLQuery(myQueryString);
        myQuery.setLong("aPrefileId", aPrefileId);
        return myQuery.list();
    }

    @Transactional(readOnly = false)
    @Override
    public Long saveSizing(SizingEntity aSizingEntity) {
        SizingEntity mySizing;
        if (aSizingEntity.getSizingId() != null) {
            mySizing = this.merge(aSizingEntity);
        } else {
            mySizing = this.save(aSizingEntity);
        }
        return mySizing.getSizingId();
    }

    @Transactional(readOnly = false)
    @Override
    public String deleteSizing(Long aSizingEntityId) {

        SizingEntity mySizing = new SizingEntity();
        mySizing.setSizingId(aSizingEntityId);
        this.delete(mySizing);
        return "Success";
    }

    @Transactional(readOnly = true)
    @Override
    public int getTheTotalAditionals(Long aPreFileId) {


        /*String myQueryString = "SELECT "
                + "(inputFederal + inputMunicipality + inputParticular + inputSpecie + inputState) as sum"
                + " FROM InputEntity as IE where IE.inputInvPreFile = :aPreFileId  and tipo='ADICIONAL'";*/
        String myQueryString = "SELECT "
                + "(inputState) as sum"
                + " FROM InputEntity as IE where IE.inputInvPreFile = :aPreFileId  and tipo='ADICIONAL'";
        int myInt = 0;
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);
        myQuery.setLong("aPreFileId", aPreFileId);
        List resultLst = myQuery.list();
        if (resultLst.size() > 0) {
            double myDob = Double.parseDouble(String.valueOf(resultLst.get(0)));
            myInt = (int) myDob;
        }
        return myInt;
    }

}
