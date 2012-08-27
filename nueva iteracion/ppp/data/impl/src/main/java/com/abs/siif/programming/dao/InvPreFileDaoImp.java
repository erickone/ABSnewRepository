/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dto.InvPreFileDto;
import com.abs.siif.programming.entities.*;
import com.abs.siif.support.UtilGenerateFolio;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Israel Ruiz
 */
@Repository("invPreFileDao")
public class InvPreFileDaoImp extends SIIFBaseDaoImpl<InvPreFileEntity, Long> implements InvPreFileDao {

    @Autowired
    private SessionFactory theirSessionFactory;
    @Resource(name = "DependenceDao")
    DependenceDao dependenceDao;
    @Resource(name = "draftProjectDaoImpl")
    DraftProjectDao draftProjectDao;
    @Resource(name = "promoterDao")
    PromoterDao promoterDao;
    @Resource(name = "componentImpl")
    ComponentDao componentDao;
    @Resource(name = "federalUrRegulatoryDao")
    FederalUrRegulatoryDao federalUrRegulatoryDao;

    @Transactional(readOnly = true)
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    /**
     * Verifica si existe una Preficha con la misma prioirdad
     *
     * @param invPreFileEntity
     * @param prefix
     * @return true si existe una prioridad igual
     */
    @Transactional(readOnly = true)
    @Override
    public boolean findPriorityEqual(InvPreFileEntity invPreFileEntity,
            String prefix) {
        boolean result = false;
        List<InvPreFileEntity> entities =
                this.find("SELECT s "
                + " FROM InvPreFileEntity s WHERE "
                + " s.priority = '" + prefix + "_" + invPreFileEntity.getPriority() + "'");

        if (!entities.isEmpty()) {
            result = true;
        }
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public String getNexNumberInvPreFile(InvPreFileEntity invPreFileEntity) {

        Criteria criteria = getTheirSessionFactory().getCurrentSession().
                createCriteria(InvPreFileEntity.class);

        /**
         * Crea el criterio para encontrar el folio Maximo donde la UR sea la
         * misma que trae la entidad
         */
        String folio;
        criteria.setProjection(Projections.max("folio")).add(
                Restrictions.eq("invPreFileUExecuting.dependenceId",
                invPreFileEntity.getInvPreFileUExecuting().getDependenceId()));
        try {
            String lastFolio = (String) criteria.uniqueResult();
            if (lastFolio.trim().length() == 0) {
                lastFolio = "0";
            }
            folio = UtilGenerateFolio.generateNextFolio(lastFolio, "nnnnn");

        } catch (Exception err) {
            //Logear el mensaje para que en caso necesario revisar el error
            logWarningLevel(err.getMessage());
            folio = UtilGenerateFolio.generateNextFolio("0", "nnnnn");
        }
        return folio.toString();

    }

    @Transactional(readOnly = true)
    @Override
    public InvPreFileEntity saveInvPreFile(InvPreFileEntity invPreFileEntity) {
        DependenceEntity hepDep = invPreFileEntity.getInvPreFileUExecuting();
        hepDep = dependenceDao.findById(hepDep.getDependenceId(), true);
        invPreFileEntity.setInvPreFileUExecuting(hepDep);

        DraftProjectEntity hepDraft = invPreFileEntity.getInvPreFileDraftProject();
        hepDraft = draftProjectDao.findById(hepDraft.getDraftProjectId(), true);
        invPreFileEntity.setInvPreFileDraftProject(hepDraft);

        PromoterEntity prom = invPreFileEntity.getPromoter();
        prom = promoterDao.findById(prom.getIdPromoter(), true);
        invPreFileEntity.setPromoter(prom);

        FederalURRegulatoryEntity hepURN = invPreFileEntity.getInvPreFileURRegulatory();
        hepURN = federalUrRegulatoryDao.findById(hepURN.getFederalUrRegulatoryId(), true);
        invPreFileEntity.setInvPreFileURRegulatory(hepURN);

        ComponentEntity hepCom = invPreFileEntity.getInvPreFileComponent();
        hepCom = componentDao.findById(hepCom.getComponentId(), true);
        invPreFileEntity.setInvPreFileComponent(hepCom);
        //invPreFileEntity.setUnitExecSpending(null);

        String query = "INSERT INTO "
                + "InvPreFileEntity VALUES ("
                + invPreFileEntity + ")";

        getTheirSessionFactory().getCurrentSession().
                saveOrUpdate(invPreFileEntity);
        getTheirSessionFactory().getCurrentSession().flush();
        return invPreFileEntity;
    }

    @Transactional(readOnly = true)
    public Collection<InvPreFileEntity> getInvPreFileWithFilter() {
        String myHqlQuery = "select DISTINCT IPE from InvPreFileEntity IPE"
                + " left join fetch IPE.invPreFileUExecuting UEG"
                + " left join fetch IPE.invPreFileURRegulatory UR"
                + " left join fetch IPE.invPreFileDraftProject DP"
                + " left join fetch IPE.invPreFileComponent Comp"
                + " left join fetch IPE.physicalAndFinancialProgramInvPreFile FisFin"
                + " left join fetch IPE.inputPhysicalAndFinInvPreFile FisFinInput"
                + " left join fetch IPE.inputEntity IE"
                + " order by IPE.invPreFileId";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myHqlQuery);
        return myQuery.list();
    }

    @Transactional(readOnly = true)
    @Override
    public InvPreFileEntity getInvPreFileEntityById(Long anInvPreFileEntity) {
        String myHqlQuery = "select DISTINCT IPE from InvPreFileEntity IPE"
                + " left join fetch IPE.invPreFileUExecuting URE"
                + " left join fetch IPE.invPreFileURRegulatory UR"
                + " left join fetch IPE.invPreFileDraftProject DP"
                + " left join fetch IPE.invPreFileComponent Comp"
                + " left join fetch IPE.promoter Prom"
                + " left join fetch IPE.physicalAndFinancialProgramInvPreFile FisFin"
                + " left join fetch IPE.inputPhysicalAndFinInvPreFile FisFinInput"
                + " left join fetch IPE.inputEntity IE"
                + " order by IPE.invPreFileId";
        Filter myInvPrefileIdFilter = theirSessionFactory.getCurrentSession().enableFilter("InvPreFileIdFilter");
        myInvPrefileIdFilter.setParameter("invPreFileIdParam", anInvPreFileEntity);
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myHqlQuery);
        InvPreFileEntity myIPF = (InvPreFileEntity) myQuery.list().get(0);
        myIPF.setUnitExecSpending(getUEGByInvPreFileId(anInvPreFileEntity));
        return myIPF;
    }

    @Transactional(readOnly = true)
    private List<DependenceEntity> getUEGByInvPreFileId(Long anId) {
        String myHqlQuery = "select DISTINCT dep from DependenceEntity dep"
                + " left join fetch dep.invPreFileDependence inv"
                + " where inv.invPreFileId = " + anId + "";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myHqlQuery);
        return myQuery.list();
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteInvPreFile(InvPreFileEntity invPreFile) {
        deleteInvPreFileRelated(invPreFile);
        theirSessionFactory.getCurrentSession().delete(invPreFile);
    }

    /**
     * Este metodo se creo debido a que una vez que la preficha tiene
     * informacion relacionada a ella hacia las distintas tablas abajo
     * mencionadas, se necesita borrar primero dicha informacion antes de borrar
     * la preficha en si, debido a constraint violations. Ojo el orden tambien
     * importa.
     *
     * @param invPreFile
     */
    @Transactional(readOnly = false)
    private void deleteInvPreFileRelated(InvPreFileEntity invPreFile) {
        //siifpppprogfisfinanciero
        String myQueryHQL = "delete from PhysicalAndFinancialProgramEntity "
                + "where physicalAndFinancialProgramInvPreFile.invPreFileId = :invPreFileId";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("invPreFileId", invPreFile.getInvPreFileId());
        myQuery.executeUpdate();

        //siifpppMetaBeneficiario
        myQueryHQL = "delete from BenefAndGoalsEntity "
                + "where invPreFile.invPreFileId = :invPreFileId";
        myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("invPreFileId", invPreFile.getInvPreFileId());
        myQuery.executeUpdate();

        //siifpppdimensionamiento
        myQueryHQL = "delete from SizingEntity "
                + "where sizingInvPreFile.invPreFileId = :invPreFileId";
        myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("invPreFileId", invPreFile.getInvPreFileId());
        myQuery.executeUpdate();

        //siifpppavancefisprog
        myQueryHQL = "delete from PhysicalProgrammedAdvanceEntity "
                + "where physicalProgrammedAdvancePreFile.invPreFileId = :invPreFileId";
        myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("invPreFileId", invPreFile.getInvPreFileId());
        myQuery.executeUpdate();

        //siifpppaportacionprefich
        myQueryHQL = "delete from InputEntity "
                + "where inputInvPreFile.invPreFileId = :invPreFileId";
        myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("invPreFileId", invPreFile.getInvPreFileId());
        myQuery.executeUpdate();

        //siifpppaportacfisfinan
        myQueryHQL = "delete from InputPhysicalAndFinEntity "
                + "where inputPhysicalAndFinInvPreFile.invPreFileId = :invPreFileId";
        myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("invPreFileId", invPreFile.getInvPreFileId());
        myQuery.executeUpdate();

        //SIIFPPPReqPreinversion
        myQueryHQL = "delete from PreInvRequestEntity "
                + "where preInvRequestPreFile.invPreFileId = :invPreFileId";
        myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("invPreFileId", invPreFile.getInvPreFileId());
        myQuery.executeUpdate();

        //siifpppprefichaclasifreg
        myQueryHQL = "delete from InvPreFileRegionalClassifierEntity "
                + "where invPreFileEntity.invPreFileId = :invPreFileId";
        myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("invPreFileId", invPreFile.getInvPreFileId());
        myQuery.executeUpdate();
    }

    @Transactional(readOnly = true)
    @Override
    public List<InvPreFileDto> getFilteredInvPrefileDTO(InvPreFileEntity InvPreFileEntityWithParams) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTime(InvPreFileEntityWithParams.getInvPreFileDraftProject().getDraftProjectStartDate());
        String myYear = Integer.toString(myCalendar.get(Calendar.YEAR)) + "-31-12T00:00:00.000+01:00";
        Date myDateQ = InvPreFileEntityWithParams.getInvPreFileDraftProject().getDraftProjectStartDate();
        Date myEndDate = InvPreFileEntityWithParams.getInvPreFileDraftProject().getDraftProjectEndDate();
        //TODO: Esto se implementó temporalmente mientras se soluciona las consultas de "sub-entitites"
        List<DependenceEntity> myDepList = InvPreFileEntityWithParams.getUnitExecSpending();
        DependenceEntity myFatherDep = InvPreFileEntityWithParams.getInvPreFileUExecuting().getFather();

        String LineUEG;
        String LineUP;
        /**
         * Esta condición permite buscar por UEG
         */
        if (myDepList.get(0).getDependenceId() > 0) {
            LineUEG = " and IPED.iddependencia = :uesDependenceId";

        } else {
            LineUEG = "";
        }
        /**
         * Esta condición permite buscar por la UP (que es el padre de la UR)
         */
        if (myFatherDep != null && myFatherDep.getDependenceId() != Long.valueOf(0)) {
            LineUP = "and IPE.idurejecutora in "
                    + "(select iddependencia from siifabsdependencia where idpadre = :fatherId)";
        } else {
            LineUP = "";
        }


        String myHqlQuery = "Select  to_char(IPE.idpreficha) as invprefileid,"
                + " IPE.folio as invprefilenumber,"
                + " IPE.nombre as invprefilename, "
                + " IPE.prioridad as invprefilepriority, "
                + " to_char(NVL((select sum(federal + municipal + particular + especie + estatal)"
                + " from siifpppaportacionprefich "
                + " where idpreficha = ipe.idpreficha and tipo = 'ASIGNACION'), '0.00')) as invprefileinitialasignation, "
                + " to_char(NVL((select sum(federal + municipal + particular + especie + estatal) "
                + " from siifpppaportacionprefich "
                + " where idpreficha = ipe.idpreficha and tipo = 'ADICIONAL'), '0.00')) as invprefileaditionalasignation, "
                + " IPE.folio as invprefiletotal, "//--Total
                + " ANP.clave as invprefiledraftprojectnumber,"
                + " ANP.nombrecorto as invprefiledraftprojectshortname, "
                + " DEP.clave as invprefileuegnumber, "
                + " DEP.descripcion as invprefileuegdescription"
                + " from siifppppreficha IPE, siifabsdependencia DEP, siifpppurnormativafed NOR, "
                + " siifpppanteproyecto ANP, siifpppcomponente COM, siifpppprefichadep IPED "
                + " where IPE.idurnormativafed = NOR.idurnormativafed "
                + " and IPE.idanteproyecto = ANP.idanteproyecto and IPE.idcomponente = COM.idcomponente "
                + " and ANP.iddependencia = DEP.iddependencia "
                + " and ANP.fechainicio >= :date1 and ANP.fechafin <= :endDate "
                + " and IPE.idpreficha = IPED.idpreficha and IPED.iddependencia = DEP.iddependencia "
                + " and ANP.activo = 't' "
                + LineUEG
                + LineUP;

        /**
         * Esta condición permite buscar por la UR
         */
        if (!(InvPreFileEntityWithParams.getInvPreFileUExecuting().getDependenceId() == Long.valueOf(0))) {
            myHqlQuery += " and IPE.idurejecutora = :ur ";
        }

        /**
         * Esta condición permite buscar por el Número de Preficha
         */
        if (!(InvPreFileEntityWithParams.getFolio().equals(""))) {
            myHqlQuery += " and IPE.folio like :folio ";
        }

        /**
         * Esta condición permite buscar por el Nombre de la Preficha
         */
        if (!(InvPreFileEntityWithParams.getName().equals(""))) {
            myHqlQuery += " and IPE.nombre like :nombre ";
        }

        /**
         * Esta condición permite buscar por la Descripción de la Preficha
         */
        if (!(InvPreFileEntityWithParams.getDescription().equals(""))) {
            myHqlQuery += " and IPE.descripcion like :descripcion ";
        }

        myHqlQuery = myHqlQuery + " order by IPE.idpreficha;";

        SQLQuery myQuery = theirSessionFactory.getCurrentSession().createSQLQuery(myHqlQuery);

        /**
         * Esta condición permite buscar por la UR
         */
        if (!(InvPreFileEntityWithParams.getInvPreFileUExecuting().getDependenceId() == Long.valueOf(0))) {
            myQuery.setLong("ur", InvPreFileEntityWithParams.getInvPreFileUExecuting().getDependenceId());
        }        
        
        /**
         * Esta condición permite buscar por la UP (que es el padre de la UR)
         */
        if (myFatherDep != null && myFatherDep.getDependenceId() != Long.valueOf(0)) {
            myQuery.setLong("fatherId", myFatherDep.getDependenceId());
        }

        /**
         * Esta condición permite buscar por UEG
         */
        if (myDepList.get(0).getDependenceId() > 0) {
            myQuery.setLong("uesDependenceId", myDepList.get(0).getDependenceId());
        }

        /**
         * Esta condición permite buscar por el Número de Preficha
         */
        if (!(InvPreFileEntityWithParams.getFolio().equals(""))) {
            String folio = "";
            folio = folio.concat("%").concat(InvPreFileEntityWithParams.getFolio()).concat("%");
            myQuery.setString("folio", folio);
        }

        /**
         * Esta condición permite buscar por el Nombre de la Preficha
         */
        if (!(InvPreFileEntityWithParams.getName().equals(""))) {
            String nombre = "";
            nombre = nombre.concat("%").concat(InvPreFileEntityWithParams.getName()).concat("%");
            myQuery.setString("nombre", nombre);
        }

        /**
         * Esta condición permite buscar por la Descripción de la Preficha
         */
        if (!(InvPreFileEntityWithParams.getDescription().equals(""))) {
            String descripcion = "";
            descripcion = descripcion.concat("%").concat(InvPreFileEntityWithParams.getDescription()).concat("%");
            myQuery.setString("descripcion", descripcion);
        }

        myQuery.setDate("date1", myDateQ);
        myQuery.setDate("endDate", myEndDate);


        List myList = getListFromSQLquery(myQuery, InvPreFileDto.class);


        return myList;
    }
}
