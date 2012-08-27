/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierDaoImpl
 *  Purpose:  This class has all the queries related to Functional Classifer
 *              Catalog.
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
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.entities.FunctionalClassifierEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FENIX-02
 */
@Repository("functionalClassifierDao")
public class FunctionalClassifierDaoImpl extends SIIFBaseDaoImpl<FunctionalClassifierEntity, Long>
            implements FunctionalClassifierDao{
    
    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<FunctionalClassifierEntity> getAllFuncClassifiers()
    {
        Query myQuery = null;
        String myQueryHQL = "select distinct func from FunctionalClassifierEntity func"
                + " left join fetch func.functionalClassifierFather"
                + " left join fetch func.functionalClassifierChilds"
                + " left join fetch func.functionalLevelClassifier"
                + " order by func.functionalClassifierKey  asc ";
        myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        return myQuery.list();
    }

    @Transactional(readOnly = false)
    @Override
    public Long Save(FunctionalClassifierEntity anFCData, SaveType aSaveType)
    {
        if (aSaveType == SaveType.UPDATE) {
            theirSessionFactory.getCurrentSession().merge(anFCData);
        } else {
            theirSessionFactory.getCurrentSession().save(anFCData);
        }

        return anFCData.getFunctionalClassifierId();
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteFunctionalClassifier(FunctionalClassifierEntity aFuncClassifier)
    {
        theirSessionFactory.getCurrentSession().delete(aFuncClassifier);
    }

    @Transactional(readOnly = true)
    @Override
    public FunctionalClassifierEntity getFuncClassifierByIdWithObjs(Long anIdentity)
    {
        Query myQuery = null;
        String myQueryHQL = "select distinct func from FunctionalClassifierEntity func"
                + " left join fetch func.funtionalClassifierObjectives"
                + " where func.functionalClassifierId = :idEntity";
        myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL).
                setLong("idEntity", anIdentity);

        return (FunctionalClassifierEntity) myQuery.list().get(0);
    }

    /**
     * Este metodo regresa el numero consecutivo siguiente para un clasificador
     * nuevo.
     * En caso que no haya un IdFC significa que se esta dando de alta un 
     * clasificador de primer nivel.
     * @param idPadre
     * @return 
     */
    @Override
    @Transactional(readOnly = true)
    public Long getNextFuncClassifierKey(String IdFC) {
        Query myQuery;
        if (IdFC != null){
            String myQueryHQL = "select COUNT(FC.functionalClassifierId) from FunctionalClassifierEntity FC "
                    + "where FC.functionalClassifierFather = :idPadre";

            myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL).
                setString("idPadre", IdFC);
        }
        else{
            String myQueryHQL = "select COUNT(FC.functionalClassifierId) from FunctionalClassifierEntity FC "
                    + "where FC.functionalLevelClassifier = :idNivel";

            myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL).
                setString("idNivel", "1");
            
        }
        
        Long myCounter;
        List myList = myQuery.list();
        myCounter = (Long) myList.get(0);
        return myCounter;
    }

}
