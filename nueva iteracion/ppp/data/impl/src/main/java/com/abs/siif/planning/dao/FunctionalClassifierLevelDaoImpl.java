/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FunctionalClassifierLevelDaoImpl
 *  Purpose:  This file contains the implementation of queries related to 
 *            functional classifier level.
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
import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FENIX-02
 */
@Repository("FunctionalClassifierLevelDao")
public class FunctionalClassifierLevelDaoImpl extends SIIFBaseDaoImpl<FunctionalLevelClassifier, Long>
implements FunctionalClassifierLevelDao{
            
    @Autowired
    private SessionFactory theirSessionFactory;
    
    @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }

    /**
     *
     * @param aFCLevel Es un entero corto que sirve para realizar la busqueda en
     * la base de datos
     * @return Regresa la entidad que tiene el nivel que le fue proporcionado
     */
    @Transactional(readOnly = true)
    @Override
    public FunctionalLevelClassifier getFuncClassifierLevelByLevel(int aFCLevel)
    {
        Criterion myCriterion = Restrictions.eq("functionalLevelClassifier", aFCLevel);

        List<FunctionalLevelClassifier> myFuncClassifierLevels = super.findByCriteria(myCriterion);

        return (myFuncClassifierLevels.size() > 0
                ? myFuncClassifierLevels.get(0) : null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FunctionalLevelClassifier> getAllFunClassifierLevels()
    {
        List<FunctionalLevelClassifier> myTmp = super.getAllAndOrderByColumn("functionalLevelClassifier");
        return myTmp;
    }

    /**
     *
     * @param objectiveLevel
     * @return Este metodo sirve para guardar una entidad Functional Classifier 
     * Level en la base de datos
     *
     */
    @Transactional(readOnly = false)
    @Override
    public Long saveOrUpdate(FunctionalLevelClassifier anEntity)
    {
        theirSessionFactory.getCurrentSession().saveOrUpdate(anEntity);
        return anEntity.getFunctionalLevelClassifierId();
    }

    /**
     *
     * @return Regresa un entero corto que nos indica que nivel podemos dar de
     * alta, en caso de que no haya ningun registro en la base de datos nos
     * devuelve un 0
     */
    @Transactional(readOnly = true)
    @Override
    public int getLastObjectiveLevel()
    {
        String myQueryHQL = "select MAX(FCL.functionalLevelClassifier) from FunctionalLevelClassifier FCL";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        Integer myLastLevel = 0;
        List myList = myQuery.list();
        if (myList.get(0) != null) {
            myLastLevel = (Integer) myList.get(0);
        }
        return myLastLevel;
    }

    /**
     *
     * @return Regresa la entidad de la base de datos que tiene activado el
     * Encuadre UEG
     */
    @Transactional(readOnly = true)
    @Override
    public FunctionalLevelClassifier getFuncClassifLevelRelationshipPlanEst()
    {
        return (FunctionalLevelClassifier) theirSessionFactory.getCurrentSession().createCriteria(FunctionalLevelClassifier.class).
                add(Restrictions.eq("functionalLevelClassifierIsEncPlaneacion", true)).uniqueResult();
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(List<FunctionalLevelClassifier> anIdentities)
    {
        super.deleteAll(anIdentities);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean isFuncClassifKeyValid(String aKey)
    {
        boolean isValid;
        String myQueryHQL = "select COUNT(FCL.functionalLevelClassifierId) from FunctionalLevelClassifier FCL "
                + "where FCL.functionalLevelClassifierKey = :aKey";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL).
            setString("aKey", aKey);
        Long myCounter;
        List myList = myQuery.list();
        myCounter = (Long) myList.get(0);
        if(myCounter == 0)
            isValid = true;
        else
            isValid = false;
        
        return isValid;
    }

}
