/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  CeilingConfigurationDaoImpl
 *  Purpose:  Define la implementacion de los metodos para acceso a datos, 
 *            que se relacionan a la tabla de la configuracion 
 *            del techo presupuestal.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */

package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.planning.data.SaveType;
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
@Repository("ceilingConfigurationDao")
public class CeilingConfigurationDaoImpl extends SIIFBaseDaoImpl<CeilingConfigurationEntity, Long> 
implements CeilingConfigurationDao{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    /**
     * Trae de vuelta todas las claves de techos presupuestales.
     * @return 
     */
    @Transactional(readOnly = true)
    @Override
    public List<CeilingConfigurationEntity> getAllCeilingConfigurations()
    {
        //return super.getAllAndOrderByColumn("ceilingConfigId");
        
        String myQueryHQL = "select distinct CCE from CeilingConfigurationEntity CCE"
                + " left join fetch CCE.budgetKeyItems"
                + " where CCE.ceilingConfigActive = 't'";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryHQL);


        return mySQLQuery.list();
    }

    @Transactional(readOnly = false)
    @Override
    public Long saveCeilingConfigWithBudgetKeyItems(CeilingConfigurationEntity aCeilingConfigurationEntity, SaveType aSaveType)
    {
        if (aSaveType == SaveType.UPDATE) {
            
            theirSessionFactory.getCurrentSession().merge(aCeilingConfigurationEntity);
        } else {
            theirSessionFactory.getCurrentSession().save(aCeilingConfigurationEntity);
        }

        return aCeilingConfigurationEntity.getCeilingConfigId();
    }
    
    @Transactional(readOnly = false)
    @Override
    public boolean isCeilingConfigKeyValid(String aKey)
    {
        boolean isValid;
        String myQueryHQL = "select COUNT(CCE.ceilingConfigId) from CeilingConfigurationEntity CCE "
                + "where CCE.ceilingConfigName = :aKey";

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

    @Transactional(readOnly = false)
    @Override
    public void deleteCeiling(CeilingConfigurationEntity aCeilingConfig)
    {
        //super.delete(aCeilingConfig);
        String myHqlQuery = "update CeilingConfigurationEntity "
                + "set ceilingConfigActive = 'f', "
                + "ceilingConfigName = :nombreCorto "
                + "where ceilingConfigId = :idEntity";

        String newName = aCeilingConfig.getCeilingConfigName().concat("_inactive");

        try {
            int update = theirSessionFactory.getCurrentSession().createQuery(myHqlQuery).
                    setString("nombreCorto", newName).
                    setLong("idEntity", aCeilingConfig.getCeilingConfigId()).
                    executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CeilingConfigurationEntity getCeilingConfigurationByYear(int aBudgetingYear)
    {
        String myQueryString = "select distinct CCE from CeilingConfigurationEntity CCE"
                + " left join fetch CCE.budgetKeyItems"
                + " left join fetch CCE.ceilingConfigBudgetKey def"
                + " where ((def.presupuestalKeyDefinitionYear = :budgetYear)and (CCE.ceilingConfigActive='t'))";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("budgetYear", aBudgetingYear);

        return (CeilingConfigurationEntity) mySQLQuery.uniqueResult();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<CeilingConfigurationEntity> getAllCeilingConfigurationByYear(int aBudgetingYear)
    {
        String myQueryString = "select distinct CCE from CeilingConfigurationEntity CCE"
                + " left join fetch CCE.budgetKeyItems"
                + " left join fetch CCE.ceilingConfigBudgetKey def"
                + " where ((def.presupuestalKeyDefinitionYear = :budgetYear)and (CCE.ceilingConfigActive='t'))";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("budgetYear", aBudgetingYear);

        return mySQLQuery.list();
    }
    
}
