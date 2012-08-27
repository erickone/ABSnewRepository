/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.dto.DestinationDto;
import com.abs.siif.budget.entities.DestinationEntity;
import java.util.Collection;
import org.hibernate.SQLQuery;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
@Repository("destinationDao")
public class DestinationDaoImpl
extends SIIFBaseDaoImpl<DestinationEntity, Long> 
implements DestinationDao
{
     @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DestinationEntity> getDestinyByObjectExpense(Long anObjectExpenseId)
    {
       String myQueryString = "SELECT b.iddestino,b.descripcion,b.clave FROM siifpppobjgastodestino"
                + " as a inner join siifpppdestino as b on a.iddestino=b.iddestino"
                + " where a.idobjetogasto = :objectExpenseId";

        SQLQuery mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);

        mySQLQuery.setLong("objectExpenseId", anObjectExpenseId);

        return mySQLQuery.list();
    }
    
    @Transactional(readOnly = true)
    @Override
    public DestinationEntity getDestinyById(Long aDestinationId)
    {
       String myQueryString = "SELECT DISTINCT DESTINY FROM DestinationEntity DESTINY"
                + " where DESTINY.destinationId= :destinyId";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("destinyId", aDestinationId);

        return (DestinationEntity) mySQLQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public DestinationEntity getDestinationByKey(String aKey)
    {
       String myQueryString = "SELECT DISTINCT DESTINY FROM DestinationEntity DESTINY"
                + " where DESTINY.destinationKey = :key";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setString("key", aKey);

        return (DestinationEntity) mySQLQuery.uniqueResult();
    }
    
    @Transactional(readOnly = true)
    @Override
    public DestinationDto getEncDepObjGasDest(Long anObjectExpenseId, Long aDependenceId, String aDestinyKey)
    {
        
        DestinationDto myResult = new DestinationDto(aDependenceId, aDestinyKey, aDestinyKey);
       String myQueryString = "Select idencdependenciaobjdestino as destinationid, "
               + "descdestino as destinationdesc, "
               + "clavedestino as destinationkey "
               + "from  siifpppencdependenciaobjdestino "
               + "where idobjetogasto = :objectExpenseId "
               + "and clavedestino = :destinyKey "
               + "and iddependencia = :dependenceId";

        SQLQuery mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);

        mySQLQuery.setLong("dependenceId", aDependenceId);
        mySQLQuery.setLong("objectExpenseId", anObjectExpenseId);
        mySQLQuery.setString("destinyKey", aDestinyKey);
        
         //mySQLQuery.setResultTransformer(Transformers.aliasToBean(EncDepObjGasDestDto.class));
            Object[] result = (Object[]) mySQLQuery.uniqueResult();
            
        if(result.length > 0)
        {
            myResult.setDestinationKey(result[2].toString());
            myResult.setDestinationDesc(result[1].toString());
        }
        return myResult;
    }
    
}