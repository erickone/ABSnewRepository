/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  EncuadreController
 *  Purpose:   Controlar la Relación de Programas con Unidad Ejecutora del 
 *             Gasto (UEG)
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.BudgetKeyEntity;
import com.abs.siif.budget.entities.DestinationEntity;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.dto.ObjectExpenseDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dto.ApplicationBudgetDto;
import com.abs.siif.programming.dto.InitBudgetKeyPreFileDto;
import com.abs.siif.programming.entities.InputEntity;
import com.abs.siif.programming.entities.InvPreFileEntity;
import java.math.BigDecimal;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abs71
 */
@Repository("financialStructureDao")
public class FinancialStructureDaoImpl extends SIIFBaseDaoImpl<ObjectExpenseEntity, Long> implements FinancialStructureDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    /**
     * Get list of ObjectExpenseEntity where objectExpenseSpecificPar = true
     *
     * @return List of ObjectExpenseEntity
     */
    @Transactional(readOnly = true)
    @Override
    public List<ObjectExpenseEntity> getAllObjectExpenses() {
        String myQueryHQL;
        List find = null;

        myQueryHQL = "select exp from ObjectExpenseEntity exp"
                + " where exp.objectExpenseLevelEnt.objectExpenseSpecificPar = 't'";
        find = this.find(myQueryHQL);

        return find;
    }

    /**
     * Get list of DestinationEntity
     *
     * @return List of DestinationEntity
     */
    @Transactional(readOnly = true)
    @Override
    public List<DestinationEntity> getAllDestinationByObjExp(Long id) {
        String myQueryHQL;
        List find = null;
        try {
            myQueryHQL = "select des from DestinationEntity des"
                    + " join fetch des.objectExpenseDestinitaion ex"
                    + " where ex.ObjectExpenseId = " + id + "";
            find = this.find(myQueryHQL);
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    /**
     * Get list of InputEntity
     *
     * @param id of PreFile
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<InputEntity> getAllInputByPreFile(Long id) {
        String myQueryHQL;
        List find = null;
        try {
            myQueryHQL = "select ie from InputEntity ie"
                    + " where ie.inputInvPreFile.invPreFileId = " + id;
            find = this.find(myQueryHQL);
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    /**
     * Extrea el monto asignado
     *
     * @param destinationEntity
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public BigDecimal getAmountAssigned(DestinationEntity destinationEntity) {
        String myQueryHQL;
        BigDecimal find = new BigDecimal(0);
        try {
            myQueryHQL = "select nvl(apppre.estatal, 0) asigned from siifppppreficha pref "
                    + "inner join siifpppdetcvepreficha detpre "
                    + "on pref.idpreficha = detpre.idpreficha  "
                    + "inner join siifpppcvepresupuestal cveptal "
                    + "on detpre.idcvepresupuestal = cveptal.idcvepresupuestal "
                    + "inner join siifpppaportacionprefich apppre "
                    + "on apppre.idpreficha = pref.idpreficha "
                    + "inner join siifpppObjGastoDestino objGD  "
                    + "on objGD.idobjetogasto = cveptal.idobjetogasto "
                    + "where apppre.tipo='ASIGNACION' and objGD.iddestino = :idDestino and objGD.idobjetogasto=:idObjetoGasto "
                    + "group by pref.idpreficha,apppre.estatal";

            SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(myQueryHQL);
            mySQLQuery.setLong("idDestino", destinationEntity.getDestinationId());
            mySQLQuery.setLong("idObjetoGasto", destinationEntity.getObjectExpenseDestinitaion().get(0).getObjectExpenseId());

            mySQLQuery.setResultTransformer(Transformers.aliasToBean(ApplicationBudgetDto.class));

            List<ApplicationBudgetDto> list = mySQLQuery.list();
            if (list != null && !list.isEmpty()) {
                for (ApplicationBudgetDto dto : list) {
                    find = find.add(dto.getAsigned());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    /**
     * Extre la sumatoria de Techo asignado por fuente de financiamiento
     *
     * @param budgetKeyEntity
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public BigDecimal getSumaryTechoCveFuente(BudgetKeyEntity budgetKeyEntity) {
        String myQueryHQL;
        BigDecimal find = new BigDecimal(0);
        try {
//            myQueryHQL = "select nvl(SUM(apppre.estatal), 0) asigned from siifppppreficha pre "
//                    + "inner join siifpppdetcvepreficha dcvepreon on pre.idpreficha = dcvepreon.idpreficha "
//                    + "inner join siifpppcvepresupuestal cvepre on cvepre.idcvepresupuestal = dcvepreon.idcvepresupuestal "
//                    + "inner join siifpppaportacionprefich apppre on apppre.idpreficha = pre.idpreficha "
//                    + "inner join siifpppconftechopptal conftpot on conftpot.iddefcvepresupuestal = cvepre.iddefcvepresupuestal "
//                    + "inner join siifppptechopptal techo on techo.idconftechopptal = conftpot.idconftechopptal "
//                    + "inner join siifpppfuefinanciamiento fin on fin.idfuentefinanciamiento = cvepre.idfuentefinanciamiento "
//                    + "where cvepre.clave like '%'||:cvepresupuestal||'%' and  fin.idfuentefinanciamiento = :idFuenteFin";
            myQueryHQL = "select NVL(SUM(cvepre.montooriginal), 0) asigned from siifpppdetcvepreficha dcvepreon "
                    + "inner join siifpppcvepresupuestal cvepre "
                    + "on cvepre.idcvepresupuestal = dcvepreon.idcvepresupuestal "
                    + "inner join siifpppfuefinanciamiento fin "
                    + "on fin.idfuentefinanciamiento = cvepre.idfuentefinanciamiento "
                    + "where fin.idfuentefinanciamiento = :idFuenteFin";

            SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(myQueryHQL);
//            mySQLQuery.setString("cvepresupuestal", budgetKeyEntity.getBudgetKeyBProgramaticKey());
            mySQLQuery.setLong("idFuenteFin", budgetKeyEntity.getFinancingSourceBudgetKey().getFinancingSourceId());

            mySQLQuery.setResultTransformer(Transformers.aliasToBean(ApplicationBudgetDto.class));

            List<ApplicationBudgetDto> list = mySQLQuery.list();
            if (list != null && !list.isEmpty()) {
                find = list.get(0).getAsigned();
            }
        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;

    }

    /**
     * Extrae las lcaves presupuestales ya configuradas para la preficha
     *
     * @param invPreFileEntity
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<InitBudgetKeyPreFileDto> getInitBudgetKeyPreFile(InvPreFileEntity invPreFileEntity) {
        String myQueryHQL;
        List<InitBudgetKeyPreFileDto> list = new ArrayList<InitBudgetKeyPreFileDto>();
        try {
            myQueryHQL = "select cve.clave clavePresupuestal, fte.clave fuenteFinanciamiento, (select nvl(SUM(apppre.estatal), 0) asigned "
                    + " from siifppppreficha pre "
                    + "inner join siifpppdetcvepreficha dcvepreon on pre.idpreficha = dcvepreon.idpreficha "
                    + "inner join siifpppcvepresupuestal cvepre on cvepre.idcvepresupuestal = dcvepreon.idcvepresupuestal "
                    + "inner join siifpppaportacionprefich apppre on apppre.idpreficha = pre.idpreficha "
                    + "inner join siifpppconftechopptal conftpot on conftpot.iddefcvepresupuestal = cvepre.iddefcvepresupuestal  "
                    + "inner join siifppptechopptal techo on techo.idconftechopptal = conftpot.idconftechopptal "
                    + "inner join siifpppfuefinanciamiento fin on fin.idfuentefinanciamiento = cvepre.idfuentefinanciamiento "
                    + "where cvepre.clave like cve.clave and  fin.idfuentefinanciamiento = fte.idfuentefinanciamiento) techoCve, "
                    + "nvl(cve.montooriginal, 0) asignadoEstatal, cve.idobjetogasto objetoid, cve.idcvepresupuestal cveid, "
                    + "nvl(cve.montoadicional, 0) asignadoadicional "
                    + "from siifpppdetcvepreficha detcve "
                    + "inner join siifppppreficha pre on detcve.idpreficha = pre.idpreficha "
                    + "inner join siifpppcvepresupuestal cve on cve.idcvepresupuestal = detcve.idcvepresupuestal "
                    + "inner join siifpppfuefinanciamiento fte on fte.idfuentefinanciamiento = cve.idfuentefinanciamiento "
                    + "where pre.idpreficha = :idPreFicha";

            SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(myQueryHQL);
            mySQLQuery.setLong("idPreFicha", invPreFileEntity.getInvPreFileId());

            mySQLQuery.setResultTransformer(Transformers.aliasToBean(InitBudgetKeyPreFileDto.class));

            list = mySQLQuery.list();

        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Extrae las claves presupouestales adicionales configuradas para la
     * preficha
     *
     * @param invPreFileEntity
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<InitBudgetKeyPreFileDto> getInitBudgetKeyAdditional(InvPreFileEntity invPreFileEntity) {
        String myQueryHQL;

        List<InitBudgetKeyPreFileDto> list = new ArrayList<InitBudgetKeyPreFileDto>();
        try {
            myQueryHQL = "select siifpppcvepptaladicional.clave clavepresupuestal, "
                    + "siifpppcvepptaladicional.monto asignadoadicional "
                    + "from siifpppcvepptaladicional where idpreficha = :idPreFicha ";

            SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(myQueryHQL);
            mySQLQuery.setLong("idPreFicha", invPreFileEntity.getInvPreFileId());

            mySQLQuery.setResultTransformer(Transformers.aliasToBean(InitBudgetKeyPreFileDto.class));

            list = mySQLQuery.list();

        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Transactional(readOnly = false)
    @Override
    public void deletebudgetKey(BudgetKeyEntity budgetKeyEntity) {
        String myQueryString1 = "DELETE FROM siifpppcvemensualpptal "
                + "WHERE idcvepresupuestal = :cvepptal ; "
                + "DELETE FROM siifpppdetcvepreficha "
                + "WHERE idcvepresupuestal = :cvepptal ; "
                + "DELETE FROM siifpppcvepptaldesglose "
                + "WHERE idcvepresupuestal = :cvepptal ; "
                + "DELETE FROM siifpppdetallecvepptal "
                + "WHERE idcvepresupuestal = :cvepptal ; "
                + "DELETE FROM siifpppcvepresupuestal "
                + " WHERE idcvepresupuestal = :cvepptal ;";

        SQLQuery mySQLQuery1 = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString1);

        mySQLQuery1.setLong("cvepptal", budgetKeyEntity.getBudgetKeyId());

        mySQLQuery1.executeUpdate();
    }

    @Transactional(readOnly = false)
    @Override
    public void updatebudgetKeyMonthly(BudgetKeyEntity budgetKeyEntity) {
        String myQueryString1 = "UPDATE siifpppcvemensualpptal SET montooriginal=0 "
                + "WHERE idcvepresupuestal = :cvepptal";
        SQLQuery mySQLQuery1 = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString1);

        mySQLQuery1.setLong("cvepptal", budgetKeyEntity.getBudgetKeyId());

        mySQLQuery1.executeUpdate();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ObjectExpenseEntity> getBudgetingFramming(DependenceEntity father, Long idInvPrefile) {
        String myQueryHQL;

        List<ObjectExpenseEntity> listReturn = new ArrayList<ObjectExpenseEntity>();
        List<ObjectExpenseDto> list = new ArrayList<ObjectExpenseDto>();
        try {
//            myQueryHQL = "select obj.clave, obj.idobjetogasto idDependency, obj.descripcion"
//                    + " from siifpppEncDependenciaObjDestino encdep"
//                    + " inner join siifabsdependencia dep on dep.iddependencia = encdep.iddependencia"
//                    + " inner join siifpppobjetogasto obj on obj.idobjetogasto = encdep.idobjetogasto"
//                    + " inner join siifpppencaccionobjetogasto acc on acc.idobjetogasto = obj.idobjetogasto"
//                    + " inner join siifpppaccionmetabenef accmentb on acc.idaccionmetabenef = accmentb.idaccionmetabenef"
//                    + " inner join siifpppconceptoobra conobra on conobra.idconceptoobra = accmentb.idconceptoobra"
//                    + " inner join  siifpppconceptogral congral on congral.idconceptogral = conobra.idconceptogral"
//                    + " inner join siifpppmetabeneficiario mb on mb.idacciongb = accmentb.idaccionmetabenef"
//                    + " where obj.inversionpublica= 't' and dep.iddependencia = :idUEG"
//                    + " and mb.idpreficha = :idInvPrefile"
//                    + " group by obj.idobjetogasto, obj.descripcion, obj.clave";
            myQueryHQL = "select obj.clave, obj.idobjetogasto idDependency, obj.descripcion"
                    + " from siifpppEncDependenciaObjDestino encdep"
                    + " inner join siifpppobjetogasto obj on obj.idobjetogasto = encdep.idobjetogasto"
                    // ESTE INNER SI IRA YA QUE SE TENGA CLARO LOS DATOS DE ACCIONOBJGASTO
//                    + " inner join siifpppencaccionobjetogasto acc on acc.idobjetogasto = obj.idobjetogasto"
//		    + " inner join siifpppaccionmetabenef accmentb on acc.idaccionmetabenef = accmentb.idaccionmetabenef"
//                    + " inner join siifpppmetabeneficiario mb on mb.idacciongb = accmentb.idaccionmetabenef   " 
                    + " where encdep.inversion= 't' and encdep.iddependencia = :idUEG"
                    + " group by obj.idobjetogasto, obj.descripcion, obj.clave";
//                    + " and mb.idpreficha = :idInvPrefile";

            SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(myQueryHQL);
            mySQLQuery.setLong("idUEG", father.getDependenceId());
//            mySQLQuery.setLong("idInvPrefile", idInvPrefile);            
            
            mySQLQuery.setResultTransformer(Transformers.aliasToBean(ObjectExpenseDto.class));
            
            list = mySQLQuery.list();
            if(list!=null && !list.isEmpty()){
                ObjectExpenseEntity ent;
                for(ObjectExpenseDto dto: list){
                    ent = new ObjectExpenseEntity();
                    ent.setObjectExpenseKey(dto.getClave());
                    ent.setObjectExpenseDescription(dto.getDescripcion());
                    ent.setObjectExpenseId(Long.parseLong(String.valueOf(dto.getIddependency())));
                    listReturn.add(ent);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReturn;
    } 
}
