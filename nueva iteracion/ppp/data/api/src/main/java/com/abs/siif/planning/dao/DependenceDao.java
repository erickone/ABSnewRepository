package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Erick Leija
 */
public interface DependenceDao extends SIIFBaseDao<DependenceEntity, Long> {

    List<DependenceEntity> getViewAllDependences();

    void Save(DependenceEntity aDependence);

    void Delete(DependenceEntity aDependence);

    DependenceEntity getDependenceById(Long aDependenceId);

    void save(List<DependenceEntity> aDependences);

    void Delete(List<DependenceEntity> aDependences);

    Collection<DependenceEntity> getViewHasBudgetDependencies();

    List<DependenceEntity> getHierarchicalDependencies(List<Long> anIdentities);

    List<DependenceEntity> getHierarchicalDependencies(Long anIdentity);

    List<DependenceEntity> getHierarchicalDependenciesByEntities(List<DependenceEntity> anIdentity);

    /**
     * Obtiene las dependencias hijas de la dependencia que se indica
     *
     * @param urEx dependencia padre
     * @return Lista de dependencias hijas
     */
    List<DependenceEntity> getChilds(DependenceEntity urEx);

    /**
     *
     * Obtiene todas las dependencias que su nivel sea de tipo UR
     *
     * @return
     */
    public List<DependenceEntity> getViewDependenciesTypeUR();

    /**
     * Obtiene las dependencias que pueden ser programable tienen su bandera
     * HasProgramming = true
     */
    public List<DependenceEntity> getViewDepHasProgramming();

    /**
     * Obtiene las dependencias que tengan tienen Plan Istitucional
     *
     * @return
     */
    public List<DependenceEntity> getViewDepHasInstitutionalPlan();

    /**
     * Obtiene las dependencias que estan marcada como ejecutoras
     *
     * @return
     */
    public List<DependenceEntity> getViewDepIsExecutionUnit();

    /**
     * Obtiene las dependencias que esten marcadas como Branch
     *
     * @return
     */
    public List<DependenceEntity> getViewDepIsBranch();

    /**
     * Obtien las dependencias que estan marcada como Sectores
     *
     * @return
     */
    public List<DependenceEntity> getViewDepIsSector();

    /**
     * Obtiene las dependencias que estan marcadas como objeto para encuadre
     *
     * @return
     */
    public List<DependenceEntity> getViewDepHasObjFrame();

    /**
     * Obtiene todas las dependencias que son de tipo presupuestal
     *
     * @return
     */
    public List<DependenceEntity> getViewDepIsBudgetUnit();

    /**
     * Obtien las dependencias que sean tipo presupuestal en base a una id de
     * Dependecia indicada
     *
     * @param idDependency
     * @return
     */
    public List<DepencenceDto> getDependciesIsBudgetByDependIdRelated(
            Long idDependency);

    /**
     * Obtiene las dependencias que estan marcadas como unidades responsables
     * dado un id de depndecia
     *
     * @param idDependency
     * @return
     */
    public List<DepencenceDto> getDependciesIsRespUnitByDependIdRelated(
            Long idDependency);

    /**
     * Obtiene todas las Unidades ejecutoras que estan relacionada a Id de una
     * dependenciacia indicada
     *
     * @param idDependency
     * @return
     */
    public List<DepencenceDto> getDependciesIsExecUnitByDependIdRelated(
            Long idDependency);

    public List<DepencenceDto> getDependciesIsFrammingSectorByDependIdRelated(
            Long idDependency);
    
    public List<DepencenceDto> getDependciesHasInstPlanByDependIdRelated(
            Long idDependency);

    public List<DepencenceDto> getDependciesIsBranchByDependIdRelated(
            Long idDependency);

    public List<DepencenceDto> getDependciesIsSectorByDependIdRelated(
            Long idDependency);

    public List<DepencenceDto> getDependciesHasBudgetByDependIdRelated(
            Long idDependency);

    public List<DepencenceDto> getDependciesHasProgrammingByDependIdRelated(
            Long idDependency);

    public List<DepencenceDto> getDependciesHasObjectiveFramingByDependIdRelated(
            Long idDependency);

    public Collection<DependenceEntity> getDependencesByUr();

    public Collection<DependenceEntity> getDependencesByUrFilter(String filter);

    public Long GetUEGbyUR(Long urId);

    /*
     * Obtiene todas las dependencias que son creadas primer nivel, carga las
     * dependencias que no tienen padre
     */
    public Collection<DependenceEntity> getViewDepWithoutFather();

    /*
     * Obtiene las dependencia donde aDependencyId, es su padre
     */
    public Collection<DependenceEntity> getViewDepByFather(DependenceEntity 
            aDependencyId);

    public DependenceEntity getDependencyByEdit(DependenceEntity anEntity);
    
}
