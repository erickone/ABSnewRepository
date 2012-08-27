/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.dto.DraftProjectDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dto.DraftProjectSearchByDto;
import com.abs.siif.programming.dto.DraftProjectSearchDto;
import com.abs.siif.programming.entities.*;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar Implementa los metodos de acceso al
 * anteproyecto a travez de spring
 */
@Repository("draftProjectDaoImpl")
public class DraftProjectDaoImpl extends SIIFBaseDaoImpl<DraftProjectEntity, Long>
        implements DraftProjectDao {

    @Autowired
    private SessionFactory theirSessionFactory;
    @Resource(name = "invPreFileDao")
    InvPreFileDao invPreFileDao;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DraftProjectEntity> getDraftProjects() {
        return super.getAllAndOrderByColumn("draftProjectKey");
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<DraftProjectEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    @Transactional(readOnly = true)
    @Override
    public DraftProjectEntity getDraftProjectById(Long anIdentity) {

        String myQueryHQL = " from DraftProjectEntity draft "
                + " left join fetch draft.draftProjectVulnerableGroup"
                + " where draft.draftProjectId = :idEntity";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL).
                setLong("idEntity", anIdentity);

        return (DraftProjectEntity) myQuery.list().get(0);

    }

    @Transactional(readOnly = true)
    @Override
    public long getTotalProjects(Long anDependenceId) {
        String myQueryString = "select max(Drafts.draftProjectKey) as max"
                + " from DraftProjectEntity Drafts"
                + " left join Drafts.draftProjectDependency dep"
                + " where dep.father = :dependenceId";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString).
                setLong("dependenceId", anDependenceId);

        long mySize = 0;
        List myList = myQuery.list();

        if (myList != null && myList.get(0) != null) {
            mySize = Long.valueOf(myList.get(0).toString());
        } else {
            mySize = Long.valueOf(0);
        }


        return mySize + 1;
    }

    @Transactional(readOnly = true)
    public Collection<DraftProjectEntity> getDraftProjectsWithFilter() {
        return theirSessionFactory.getCurrentSession().createCriteria(DraftProjectEntity.class).list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<DraftProjectSearchByDto> getFilteredDraftProjectDTO(DraftProjectEntity anEntity) {

        Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTime(anEntity.getDraftProjectStartDate());
        String myYear = Integer.toString(myCalendar.get(Calendar.YEAR));


        String myQueryHQL = "select to_char(ANP.idanteproyecto) as idanteproyecto, "
                + " ANP.idprogramacion as idprogramacion, "
                + " ANP.iddependencia as iddependencia, "
                + " to_char(OBJ.idobjetivo) as idobjetivo, "
                + " ANP.clave as clave, ANP.nombrecorto as nombrecorto, "
                + " ANP.nombre as nombre, "
                + " (select OBJP.clave || ' ' || OBJP.nombre "
                + " from siifpppobjetivo OBJP "
                + " where OBJP.idobjetivo = OBJ.idpadre) nombreobjetivopadre, "
                + " (OBJ.clave || ' ' || OBJ.nombre) as nombreobjetivo, "
                + " (select DEPP.clave || ' ' || DEPP.descripcion "
                + " from siifabsdependencia DEPP "
                + " where DEPP.iddependencia = DEP.idpadre) as descripciondependenciapadre, "
                + " (DEP.clave || ' ' || DEP.descripcion) as descripciondependencia, "
                + " EST.descripcion as estatus "
                + " from siifpppanteproyecto ANP, siifabsdependencia DEP, "
                + " siifpppprogramacion PRO, siifpppobjetivo OBJ, "
                + " siifpppestatusanteproy EST "
                + " where ANP.idprogramacion = PRO.idprogramacion "
                + " and ANP.iddependencia = DEP.iddependencia "
                + " and PRO.idobjetivo = OBJ.idobjetivo "
                + " and ANP.idestatusanteproyecto = EST.idestatusanteproyecto "
                + " and ANP.Activo = 't' "
                + " and year(ANP.fechainicio) = :year ";
                //+ " and DEP.iddependencia in (select iddependencia from siifabsdependencia where idpadre = :ur) ";


        /**
         * Esta condición permite buscar por la UR
         */
        if (anEntity.getDraftProjectDependency().getFather().getDependenceId() > 0) {
            myQueryHQL += " and DEP.iddependencia in (select iddependencia from siifabsdependencia where idpadre = :ur) ";
        }

        /**
         * Esta condición permite buscar por el Número de Ficha
         */
        if (!(anEntity.getDraftProjectKey().equals(""))) {
            myQueryHQL += " and ANP.clave like :clave ";
        }

        /**
         * Esta condición permite buscar por el Nombre Corto de la Ficha
         */
        if (!(anEntity.getDraftProjectShortName().equals(""))) {
            myQueryHQL += " and ANP.nombrecorto like :nombrecorto ";
        }

        myQueryHQL += "order by ANP.clave;";

        SQLQuery myQuery = theirSessionFactory.getCurrentSession().createSQLQuery(myQueryHQL);
        
        myQuery.setString("year", myYear);
        
        /**
         * Esta condición permite buscar por la UR
         */
        if (anEntity.getDraftProjectDependency().getFather().getDependenceId() > 0) {
            myQuery.setLong("ur", anEntity.getDraftProjectDependency().getFather().getDependenceId());
        }
        
        /**
         * Esta condición permite buscar por el Número de Ficha
         */
        if (!(anEntity.getDraftProjectKey().equals(""))) {
            String clave = "";
            clave = clave.concat("%").concat(anEntity.getDraftProjectKey()).concat("%");
            myQuery.setString("clave", clave);
        }

        /**
         * Esta condición permite buscar por el Nombre Corto de la Ficha
         */
        if (!(anEntity.getDraftProjectShortName().equals(""))) {
            String nombreCorto = "";
            nombreCorto = nombreCorto.concat("%").concat(anEntity.getDraftProjectShortName()).concat("%");
            myQuery.setString("nombrecorto", nombreCorto);
        }
        
        
        List myList = getListFromSQLquery(myQuery, DraftProjectSearchByDto.class);

        return myList;
    }

    @Transactional(readOnly = true)
    @Override
    public DraftProjectEntity getDraftProjectEager(Long aDraftProjectId) {
        String myQueryHQL = "select distinct Drafts from DraftProjectEntity Drafts"
                + " left join fetch Drafts.draftProjectProblem"
                + " left join fetch Drafts.draftProjectSpecificObjective"
                + " where Drafts.draftProjectId = :draftId";

        DraftProjectEntity myDraft = null;
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("draftId", aDraftProjectId);
        List lstResult = myQuery.list();
        if (lstResult != null && lstResult.size() > 0) {
            myDraft = (DraftProjectEntity) lstResult.get(0);
        }
        return myDraft;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DraftProjectEntity> getDraftProjectsInWorkFlow(DraftProjectSearchDto aDraftProjectSearchDto) {
        String myQueryString = "select proj from DraftProjectEntity proj"
                + "   join fetch proj.draftProjectDependency dep"
                + "   join fetch proj.draftProjectProgramming baseprog"
                + "   join fetch baseprog.programmingObjective subprogram"
                + "   join fetch subprogram.objectiveFather program"
                + " where ((dep.dependenceId=:dendenceId)) ";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);
        myQuery.setLong("dendenceId", aDraftProjectSearchDto.getDependencyId());
        return myQuery.list();
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteDraftProject(DraftProjectEntity anEntity) {
        boolean deleted = false;
        String myHqlQuery = "update DraftProjectEntity "
                + "set isDraftProjectActive = 'f', "
                + "draftProjectShortName = :nombreCorto, "
                + "draftProjectKey = :clave "
                + "where draftProjectId = :idEntity";

        String myIPFHqlQuery = "update InvPreFileEntity "
                + "set folio = '000' "
                + "where invPreFileDraftProject = :idEntity";

        String newShortName = anEntity.getDraftProjectShortName().concat("_inactive");
        String newInvalidKey = "000";

        try {
            int update = theirSessionFactory.getCurrentSession().createQuery(myHqlQuery).
                    setString("nombreCorto", newShortName).
                    setString("clave", newInvalidKey).
                    setLong("idEntity", anEntity.getDraftProjectId()).
                    executeUpdate();


            int updateIPF = theirSessionFactory.getCurrentSession().createQuery(myIPFHqlQuery).
                    setLong("idEntity", anEntity.getDraftProjectId()).
                    executeUpdate();
            deleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DraftProjectEntity> getDraftProjectsByFilter(DraftProjectSearchDto aDraftProjectSearchDto) {

        String myQueryString = getMessage("siif.dao.nativequeries.SQLDraftProjectByFilters");

        SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(myQueryString);
        mySQLQuery.setLong("idDependencia", aDraftProjectSearchDto.getDependencyId());
        mySQLQuery.setString("idAnteProyecto", aDraftProjectSearchDto.getIdAnteProyecto());
        mySQLQuery.setString("idPreFicha", aDraftProjectSearchDto.getIdPreFicha());
        mySQLQuery.setString("nombreCorto", aDraftProjectSearchDto.getNombreCorto());
        mySQLQuery.setLong("status", aDraftProjectSearchDto.getStatus());
        mySQLQuery.setDate("fechaInicio", aDraftProjectSearchDto.getFechaInicio());
        mySQLQuery.setDate("fechaFin", aDraftProjectSearchDto.getFechaFin());

        mySQLQuery.setResultTransformer(Transformers.aliasToBean(DraftProjectDto.class));

        Collection<DraftProjectDto> list = mySQLQuery.list();

        Collection<DraftProjectEntity> listRet = new ArrayList<DraftProjectEntity>();
        for (DraftProjectDto elem : list) {
            DraftProjectEntity draftProject = getDraftProjectById(Long.parseLong(String.valueOf(elem.getIdanteproyecto())));
            listRet.add(draftProject);
        }
        return listRet;
    }

    @Transactional(readOnly = true)
    @Override
    public DraftProjectEntity getFilteredDraftProjectDirectSearch(DraftProjectEntity aDraftProjectEntity, List<DependenceEntity> aDependenceEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional(readOnly = true)
    @Override
    public DraftProjectEntity getFilteredDraftProjectByDependenceUEG(DraftProjectEntity aDraftProjectEntity, DependenceEntity aDependenceEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional(readOnly = true)
    @Override
    public DraftProjectEntity getDraftProjectByIdWithBudgets(Long anIdentity) {
        String myQueryHQL = "select distinct Drafts from DraftProjectEntity Drafts"
                + " left join fetch Drafts.draftProjectBudgetDetail"
                + " left join fetch Drafts.draftProjectProgramming baseprog"
                + " where Drafts.draftProjectId = :draftId";

        DraftProjectEntity myDraft = null;
        Query myQuery =
                theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);

        myQuery.setLong("draftId", anIdentity);

        List lstResult = myQuery.list();
        if (lstResult != null && lstResult.size() > 0) {
            myDraft = (DraftProjectEntity) lstResult.get(0);
        }
        return myDraft;
    }

    /**
     * Este metodo lo que se ha gastado de la canasta de las partidas no basicas
     */
    @Transactional(readOnly = true)
    @Override
    public Long getAvailableCeilingByUEGIds(String identitiesToserach) {


        String myQueryHQL = "Select sum(f.nobasico)  from (Select b.* from"
                + " (Select * from siifpppanteproyecto where iddependencia in (" + identitiesToserach + "))"
                + " as a left join siifpppdetcveanteproy as b on a.idanteproyecto=b.idanteproyecto)"
                + " as c left join siifpppcvepresupuestal as d on c.idcvepresupuestal = d.idcvepresupuestal"
                + " inner join siifpppcvepptaldesglose as f on f.idcvepresupuestal = d.idcvepresupuestal";

        SQLQuery myQuery =
                theirSessionFactory.getCurrentSession().createSQLQuery(myQueryHQL);



        BigDecimal myAmount = (BigDecimal) myQuery.uniqueResult();
        Long myLong;
        if (myAmount == null) {
            myLong = (long) (0);
        } else {
            myLong = myAmount.longValue();
        }

        return myLong;
    }

    /**
     * Este Metodo regresa lo que se ha presupuestado de un objeto de gasto en
     * especifico(por medio del encuadre UR-Objeto de Gasto-Destino)
     */
    @Transactional(readOnly = true)
    @Override
    public Long getAvailableCeilingBasics(String identitiesToserach, Long obj, String Dest) {


        String myQueryHQL = "Select sum(f.monto)  from (Select b.* from"
                + " (Select * from siifpppanteproyecto where iddependencia in (" + identitiesToserach + "))"
                + " as a left join siifpppdetcveanteproy as b on a.idanteproyecto=b.idanteproyecto)"
                + " as c left join siifpppcvepresupuestal as d on c.idcvepresupuestal = d.idcvepresupuestal"
                + " inner join siifpppcvepptaldesglose as f on f.idcvepresupuestal = d.idcvepresupuestal"
                + " where ((d.idobjetogasto = :obj) and (d.clave like :clave))";

        SQLQuery myQuery =
                theirSessionFactory.getCurrentSession().createSQLQuery(myQueryHQL);

        myQuery.setLong("obj", obj);
        myQuery.setString("clave", "%" + Dest);


        BigDecimal myAmount = (BigDecimal) myQuery.uniqueResult();
        Long myLong;
        if (myAmount == null) {
            myLong = (long) (0);
        } else {
            myLong = myAmount.longValue();
        }

        return myLong;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getBudgetByUEG(DraftProjectEntity aDraftProject) {
        Long myResult = new Long(0);
        String myStrQuery = "Select sum(d.montooriginal)  "
                + " from (Select b.* from"
                + " (Select * from siifpppanteproyecto where idanteproyecto = :IdANT)"
                + " as a left join siifpppdetcveanteproy as b on a.idanteproyecto=b.idanteproyecto)"
                + " as c left join siifpppcvepresupuestal as d on c.idcvepresupuestal = d.idcvepresupuestal";

        SQLQuery myQuery = theirSessionFactory.getCurrentSession().createSQLQuery(myStrQuery);
        myQuery.setLong("IdANT", aDraftProject.getDraftProjectId());

        BigDecimal myQueryResult = (BigDecimal) myQuery.uniqueResult();
        if (myQueryResult == null) {
            myResult = (long) (0);
        } else {
            myResult = myQueryResult.longValue();
        }

        return myResult;
    }

    /**
     * Este metodo devuelve el monto gastado en prefichas de inversión
     */
    @Transactional(readOnly = true)
    @Override
    public Long getInvPreFileAvailableCeilingByUEGIds(String identitiesToserach) {

        String myQueryHQL = "Select nvl(sum(mon.montooriginal),0) from (select DISTINCT cvePres.clave, cvePres.idfuentefinanciamiento, cvePres.montooriginal from (Select * from "
                + "(Select * from siifpppanteproyecto where iddependencia in (" + identitiesToserach + "))"
                + " as ant inner join siifppppreficha as preFi on ant.idanteproyecto = preFi.idanteproyecto)"
                + " as a inner join siifpppdetcvepreficha as detPreFi on detPreFi.idpreficha = a.idpreficha"
                + " inner join siifpppcvepresupuestal as cvePres on cvePres.idcvepresupuestal = detPreFi.idcvepresupuestal"
                + " inner join siifpppobjetogasto as ObjGas on ObjGas.idobjetogasto = cvePres.idobjetogasto"
                //                + " inner join siifpppencaccionobjetogasto as accObjGas on accObjGas.idobjetogasto = ObjGas.idobjetogasto"
                // Despues cambiar amarre por encuadre accion objeto de gasto
                + " inner join siifpppencdependenciaobjdestino as depObjDest on ObjGas.idobjetogasto = ObjGas.idobjetogasto"
                + " where depObjDest.inversion = 't') as mon";

        SQLQuery myQuery =
                theirSessionFactory.getCurrentSession().createSQLQuery(myQueryHQL);


        BigDecimal myAmount = (BigDecimal) myQuery.uniqueResult();

        long retVal = myAmount.longValue();

        return retVal;
    }
}