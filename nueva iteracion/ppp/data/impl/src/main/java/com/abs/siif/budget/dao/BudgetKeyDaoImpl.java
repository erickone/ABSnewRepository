/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.budget.dto.BudgetKeyBreakDownDto;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import com.abs.siif.budget.entities.FinancingSourceEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
@Repository("budgetKeyDao")
public class BudgetKeyDaoImpl
        extends SIIFBaseDaoImpl<BudgetKeyEntity, Long>
        implements BudgetKeyDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = false)
    @Override
    public Long saveBudgetKey(BudgetKeyEntity aBudgetEntity) {
        BudgetKeyEntity myBudgetKey = super.save(aBudgetEntity);
        return myBudgetKey.getBudgetKeyId();
    }

    @Transactional(readOnly = false)
    @Override
    public void persistBudgetKey(BudgetKeyEntity aBudgetEntity) {
        super.merge(aBudgetEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<BudgetKeyEntity> getBudgetKeysWithProgrammaticKey(String aProgrammaticKey) {
        String myQueryString = " from BudgetKeyEntity as budget  "
                + "  left join fetch budget.objectExpenseBudgetKey"
                + "  where budget.budgetKeyBProgramaticKey like  :programmaticKey ";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setString("programmaticKey", aProgrammaticKey + "%");

        return mySQLQuery.list();
    }

    @Transactional(readOnly = true)
    @Override
    public Long getBudgetKeysSummaryAmountsByFinancingSouce(FinancingSourceEntity aFinancingSource) {
        String myQueryString = "Select sum(budget.originalAmount) from BudgetKeyEntity as budget  "
                + "  where budget.financingSourceBudgetKey = :finsourceId ";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("finsourceId", aFinancingSource.getFinancingSourceId());

        Double myAmount = (Double) mySQLQuery.uniqueResult();
        if (myAmount == null) {
            myAmount = 0.0;
        }
        Long myLong = myAmount.longValue();
        return myLong;
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteBudgetKey(Long aBudgetKey) {
        String myStrQuery = null;
        BudgetKeyEntity myBudgetKeyEntity = this.getBudgetKeyById(aBudgetKey);
        List<BudgetKeyEntity> myListOfBudgetKeyEntity = this.findByCriteria(
                Restrictions.eq("objectExpenseBudgetKey",
                myBudgetKeyEntity.getObjectExpenseBudgetKey()));
        Collections.sort(myListOfBudgetKeyEntity);
        long myNext = -1;
        Object myMontoDesglose = null;
        long amounttoDelete = myBudgetKeyEntity.getOriginalAmount().longValue();
        boolean update = false;
        for (BudgetKeyEntity myBudgetKeyEntityToIterate : myListOfBudgetKeyEntity) {

            if (myBudgetKeyEntity.getURFromCve().equals(myBudgetKeyEntityToIterate.getURFromCve())
                    && myBudgetKeyEntityToIterate.getBudgetKeyId().longValue() != aBudgetKey.longValue()) {
                myNext = myBudgetKeyEntityToIterate.getBudgetKeyId();

                myStrQuery = "select basico, nobasico from siifpppcvepptaldesglose "
                        + " where idcvepresupuestal = :cvepptal";

                SQLQuery mySQLDesglose = getTheirSessionFactory().
                        getCurrentSession().createSQLQuery(myStrQuery);
                mySQLDesglose.setLong("cvepptal", myNext);
                myMontoDesglose = mySQLDesglose.list();
                Object[] montosObji = null;

                if (myMontoDesglose != null && ((List) myMontoDesglose).size() > 0) {

                    montosObji = (Object[]) ((List) myMontoDesglose).get(0);
                    BigDecimal basico = (BigDecimal) montosObji[0];
                    BigDecimal nobasico = (BigDecimal) montosObji[1];
                    long lbasico = basico.longValue();
                    long lnobasico = nobasico.longValue();

                    if (lnobasico != 0 && lnobasico > amounttoDelete && amounttoDelete > 0) {
                        lbasico += amounttoDelete;
                        lnobasico -= amounttoDelete;
                        amounttoDelete = 0;
                        update = true;
                    }else if(lnobasico != 0 && lnobasico < amounttoDelete && amounttoDelete > 0){
                        lbasico += lnobasico;
                        amounttoDelete -= lnobasico;
                        lnobasico = 0;
                        update = true;
                    }else if(lnobasico != 0 && lnobasico == amounttoDelete && amounttoDelete > 0){
                        lbasico += lnobasico;
                        amounttoDelete = 0;
                        lnobasico = 0;
                        update = true;
                    }
                    if(update){
                        update = false;
                        myStrQuery = "update siifpppcvepptaldesglose set basico =" + lbasico
                                + ", nobasico = " + lnobasico + ""
                                + " where idcvepresupuestal =" + myNext;

                        SQLQuery mySQLQueryDelete = getTheirSessionFactory().
                                getCurrentSession().createSQLQuery(myStrQuery);
                        mySQLQueryDelete.executeUpdate();
                    
                    }
                }
            }
        }

       
        String myQueryString = "DELETE siifpppcvepptaldesglose"
                + " WHERE idcvepresupuestal = :cvepptal";


        SQLQuery mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);

        mySQLQuery.setLong("cvepptal", aBudgetKey);

        mySQLQuery.executeUpdate();
        this.delete(getBudgetKeyById(aBudgetKey));

       
    }

    @Transactional(readOnly = false)
    @Override
    public void updateBudgetKey(BudgetKeyEntity aBudgetKey) {
        if (!aBudgetKey.isIsAdditional()) {
            updateInBudgetKeyDetail(aBudgetKey);
        }
        if(aBudgetKey.getBudgetKeyId() == null){
            this.save(aBudgetKey);
        }else{
            this.merge(aBudgetKey);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public BudgetKeyEntity getBudgetKeyById(Long aBudgetKeyId) {
        String myQueryString = "from BudgetKeyEntity as budget"
                + " left join fetch budget.objectExpenseBudgetKey"
                + " left join fetch budget.mensualBudgetKeyBudgetKey"
                + " left join fetch budget.financingSourceBudgetKey"
                + " where budget.budgetKeyId  = :budgetKeyId ";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("budgetKeyId", aBudgetKeyId);

        return (BudgetKeyEntity) mySQLQuery.uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public BudgetKeyEntity getBudgetKeyByBudgetKey(BudgetKeyEntity BudgetKey) {
        String myQueryString = "from BudgetKeyEntity as budget"
                + " left join fetch budget.mensualBudgetKeyBudgetKey"
                + " where budget.budgetKeyBProgramaticKey  like :budgetKey ";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setString("budgetKey", BudgetKey.getBudgetKeyBProgramaticKey());

        return (BudgetKeyEntity) mySQLQuery.uniqueResult();
    }

    @Transactional(readOnly = false)
    @Override
    public void insertInBudgetKeyDetail(BudgetKeyEntity aBudgetKeyEntity) {
        String myQueryString = "INSERT INTO siifpppcvepptaldesglose (idcvepresupuestal,monto,basico,nobasico)"
                + "VALUES (:cvepptal,:montos,:basico,:nobasico)";


        SQLQuery mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);

        mySQLQuery.setLong("cvepptal", aBudgetKeyEntity.getBudgetKeyId());
        mySQLQuery.setLong("montos", aBudgetKeyEntity.getOriginalAmount().longValue());
        mySQLQuery.setLong("basico", aBudgetKeyEntity.getBudgetKeyBasic());
        mySQLQuery.setLong("nobasico", aBudgetKeyEntity.getBudgetKeyNoBasic());

        if (aBudgetKeyEntity.getOriginalAmount().longValue()
                != (aBudgetKeyEntity.getBudgetKeyBasic() + aBudgetKeyEntity.getBudgetKeyNoBasic())) {
            throw new BaseBusinessException("Error en calculo de presupuestación, intentar de nuevo!");
        }

        mySQLQuery.executeUpdate();
    }

    @Transactional(readOnly = true)
    @Override
    public List<BudgetKeyEntity> getBudgetKeyByInvPreFileId(Long invPreFileId) {
        List<BudgetKeyEntity> budgetKeys = new ArrayList<BudgetKeyEntity>();

        String myQueryString = "select budget from BudgetKeyEntity as budget"
                + " left join fetch budget.invPreFileDetailBudgetKey as preficha"
                + " where preficha.invPreFileDetailPreFile  = :invPreFileId ";


        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        mySQLQuery.setLong("invPreFileId", invPreFileId);

        budgetKeys = (List<BudgetKeyEntity>) mySQLQuery.list();
        return budgetKeys;
    }

    @Transactional(readOnly = false)
    @Override
    public void updateInBudgetKeyDetail(BudgetKeyEntity aBudgetKeyEntity) {
        String myQueryString = "UPDATE siifpppcvepptaldesglose SET monto = :montos, basico = :basicos, nobasico = :nobasicos"
                + " where idcvepresupuestal = :cvepptal";


        SQLQuery mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);

        mySQLQuery.setLong("cvepptal", aBudgetKeyEntity.getBudgetKeyId());
        mySQLQuery.setLong("montos", aBudgetKeyEntity.getOriginalAmount().longValue());
        mySQLQuery.setLong("basicos", aBudgetKeyEntity.getBudgetKeyBasic());
        mySQLQuery.setLong("nobasicos", aBudgetKeyEntity.getBudgetKeyNoBasic());

        if (aBudgetKeyEntity.getOriginalAmount().longValue()
                != (aBudgetKeyEntity.getBudgetKeyBasic() + aBudgetKeyEntity.getBudgetKeyNoBasic())) {
            throw new BaseBusinessException("Error en calculo de presupuestación, intentar de nuevo!");
        }

        mySQLQuery.executeUpdate();
    }

    @Transactional(readOnly = false)
    @Override
    public BudgetKeyBreakDownDto getBudgetDesgloce(Long lCvePptalId) {
        String myQueryString = "SELECT idcvepptaldesglose, idcvepresupuestal, monto, basico, nobasico"
                + " from siifpppcvepptaldesglose where idcvepresupuestal = :cvepptal";

        BudgetKeyBreakDownDto myEntity = null;
        SQLQuery mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);

        mySQLQuery.setLong("cvepptal", lCvePptalId);

        List oblst = getListFromSQLquery(mySQLQuery, BudgetKeyBreakDownDto.class);

        if (oblst.size() > 0) {
            myEntity = (BudgetKeyBreakDownDto) oblst.get(0);
        }
        return myEntity;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BudgetKeyBreakDownDto> getBudgetKeyWithBreakDown(String aKey, String other) {
        String key = aKey.concat("%").concat(other).concat("%");
        String myQueryString = "SELECT siifpppcvepresupuestal.idcvepresupuestal, clave, "
                + "idcvepptaldesglose, monto, basico, nobasico "
                + "from siifpppcvepresupuestal inner join siifpppcvepptaldesglose  "
                + "on siifpppcvepresupuestal.idcvepresupuestal = siifpppcvepptaldesglose.idcvepresupuestal "
                + "where clave like :key";

        SQLQuery mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);

        mySQLQuery.setString("key", key);

        List oblst = getListFromSQLquery(mySQLQuery, BudgetKeyBreakDownDto.class);

        return oblst;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean updateBudgetKeyWithBreakDown(List<BudgetKeyBreakDownDto> aList) {
        boolean isOK = false;
        String myQueryString = "UPDATE siifpppcvepptaldesglose SET"
                + " monto = :montos, basico = :basicos, nobasico = :nobasicos"
                + " where idcvepptaldesglose = :iddesgloce";


        String myQueryStringTwo = "UPDATE siifpppcvepresupuestal SET"
                + " montooriginal = :montoorig"
                + " where idcvepresupuestal = :idpptal";
        try {
            for (BudgetKeyBreakDownDto myDto : aList) {

                SQLQuery mySQLQuery = getTheirSessionFactory().
                        getCurrentSession().createSQLQuery(myQueryString);

                mySQLQuery.setLong("iddesgloce", myDto.getIdcvepptaldesglose());
                mySQLQuery.setLong("montos", myDto.getMonto().longValue());
                mySQLQuery.setLong("basicos", myDto.getBasico().longValue());
                mySQLQuery.setLong("nobasicos", myDto.getNobasico().longValue());

                if (myDto.getMonto().longValue()
                        != (myDto.getBasico().longValue() + myDto.getNobasico().longValue())) {
                    throw new BaseBusinessException("Error en calculo de presupuestación, intentar de nuevo!");
                }


                mySQLQuery.executeUpdate();

                mySQLQuery = getTheirSessionFactory().
                        getCurrentSession().createSQLQuery(myQueryStringTwo);
                mySQLQuery.setLong("idpptal", myDto.getIdcvepresupuestal());
                mySQLQuery.setBigDecimal("montoorig", myDto.getMonto());
                mySQLQuery.executeUpdate();
            }
            isOK = true;
        } catch (Exception ex) {
            Logger.getLogger(ImportCeilingBudgetDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isOK;
    }
}
