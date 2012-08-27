/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.dto.ValidateDeleteCeilingBudgetDto;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.budget.entities.CeillingBudgetEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author absvalenzuela
 */
@Repository("importCeilingBudgetDao")
public class ImportCeilingBudgetDaoImpl extends SIIFBaseDaoImpl<CeillingBudgetEntity, Long>
        implements ImportCeilingBudgetDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean saveCeillingBudgetItem(ValidateDeleteCeilingBudgetDto deleteCeiling, List<CeillingBudgetEntity> dataFile) {
        boolean saveWithoutError = false;
        List findElement = null;
        try {
            
            if (deleteCeiling.isDeleteCeilingBudget()) {
                String myQueryHQL = "delete CeillingBudgetEntity cbe "
                        + " where cbe.ceilingConfigId.ceilingConfigId = :aTechoId ";
                
                Query mySQLQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
                
                 mySQLQuery.setLong("aTechoId", dataFile.get(0).getCeilingConfigId().getCeilingConfigId());
                
                mySQLQuery.executeUpdate();
            }
            
            for (CeillingBudgetEntity ceilingBudget : dataFile) {

                String myQueryHQL = "select cbe from CeillingBudgetEntity cbe"
                        + " where cbe.claveCarga = :aClave ";

                Query mySQLQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);

                mySQLQuery.setString("aClave", ceilingBudget.getClaveCarga());

                findElement = mySQLQuery.list();

                if (findElement.size() > 0) {
                     CeillingBudgetEntity object = (CeillingBudgetEntity) findElement.get(0);
                     ceilingBudget.setTechoPresupuestalId(object.getTechoPresupuestalId()); 

                    if (!deleteCeiling.isDeleteCeilingBudget()) {

                        long lMonto = ceilingBudget.getMonto();

                        ceilingBudget.setMonto(lMonto);
                    }
                    super.merge(ceilingBudget);

                } else {
                    super.save(ceilingBudget);
                }
            }
            saveWithoutError = true;
        } catch (Exception ex) {
            Logger.getLogger(ImportCeilingBudgetDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saveWithoutError;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean saveCeillingBudgetItem(CeillingBudgetEntity dataEntity) {
        boolean saveWithoutError = false;
        try {
            super.save(dataEntity);
            saveWithoutError = true;
        } catch (Exception ex) {
            Logger.getLogger(ImportCeilingBudgetDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return saveWithoutError;
    }

    /**
     * Este metodo regresa el Techo de una relacion UR-Gasto-Destino
     * osea el Techo de un objeto de gasto en especifico
     */
    @Transactional(readOnly = true)
    @Override
    public Long getSummatoryTotalOfCeilingsByChargeKey(int myYear,
            String chargeKey, String finSource) {
        String myQueryString = "select sum(CBE.monto) from CeillingBudgetEntity CBE"
                + " where CBE.ceilingConfigId.ceilingConfigBudgetKey.presupuestalKeyDefinitionYear = :year"
                + " and CBE.claveCarga = :key"
                + " and CBE.esFuenteFinanciamiento = :fuente";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setString("key", chargeKey);
        mySQLQuery.setString("fuente", finSource);
        mySQLQuery.setInteger("year", myYear);

        return (Long) mySQLQuery.uniqueResult();
    }
    
    /**
     * Este metodo Regresa la Suma de los techos de los objetos de gasto que estan
     * marcados como No basicos
     */
    @Transactional(readOnly = true)
    @Override
    public Long getSummatoryTotalOfCeilingsNotBasicObject(int myYear,  String chargeKey) {
        
        String clavUR = null;
        if(chargeKey != null && chargeKey.length()>3){
            clavUR = chargeKey.substring(0,4);  // Toma la clave de 4 digitos que corresponde a la UR 
        }
        
        String myQueryString = "select sum(c.monto)"
                + " from siifpppencdependenciaobjdestino a"
                + " join siifpppobjetogasto b on a.idobjetogasto = b.idobjetogasto and a.basica = 'f' and a.inversion='f'"
                + " join siifppptechopptal c on b.clave = substr(c.clavecarga, 6, 4)"
                + " join siifabsdependencia d on a.iddependencia = d.iddependencia and d.claveinterna = substr(c.clavecarga, 1, 4)"
                + " where d.claveinterna = :UR";

                

        SQLQuery mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);
       
        mySQLQuery.setString("UR", clavUR);
        
         BigDecimal bf =BigDecimal.ZERO;
                
        if (mySQLQuery.uniqueResult()!=null) {
            bf=(BigDecimal)mySQLQuery.uniqueResult();
        }
        
        return bf.longValue();
        
    }    
    
     /**
     * Este metodo Regresa la Suma de los techos de los objetos de gasto que estan
     * marcados como Objetos de Inversión
     */
    @Transactional(readOnly = true)
    @Override
    public Long getSummatoryTotalOfCeilingsInvestObjects(int myYear,  String chargeKey) 
    {
        
        String clavUR = null;
        if(chargeKey != null && chargeKey.length()>3){
            clavUR = chargeKey.substring(0,4);  // Toma la clave de 4 digitos que corresponde a la UR 
        }
        
        String myQueryString = "select sum(c.monto)"
                + " from siifpppencdependenciaobjdestino a"
                + " join siifpppobjetogasto b on a.idobjetogasto = b.idobjetogasto and a.inversion='t'"
                + " join siifppptechopptal c on b.clave = substr(c.clavecarga, 6, 4)"
                + " join siifabsdependencia d on a.iddependencia = d.iddependencia and d.claveinterna = substr(c.clavecarga, 1, 4)"
                + " where d.claveinterna = :UR  And a.clavedestino = substr(c.clavecarga, 11, 2)";


        SQLQuery mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);
       
        mySQLQuery.setString("UR", clavUR);
        
         BigDecimal bf = BigDecimal.ZERO;
                
        if (mySQLQuery.uniqueResult()!=null) {
            bf=(BigDecimal)mySQLQuery.uniqueResult();
        }
        
        return bf.longValue();
        
    }   
    
    
}
