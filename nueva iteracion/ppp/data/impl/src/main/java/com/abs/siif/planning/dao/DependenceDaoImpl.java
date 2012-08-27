package com.abs.siif.planning.dao;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.support.AliasEntityEnum;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
@Repository("DependenceDao")
public class DependenceDaoImpl extends SIIFBaseDaoImpl<DependenceEntity,
        Long> implements
        DependenceDao, DependenceDaoFilters {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Cacheable("depedendenciesAll")
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewAllDependences() {

        String myQueryHQL = "select distinct "
                + AliasEntityEnum.DEPENDENCY.name() + " from DependenceEntity "
                + AliasEntityEnum.DEPENDENCY.name()
                + " left join fetch "
                + AliasEntityEnum.DEPENDENCY.name() + ".father"
                + " left join fetch "
                + AliasEntityEnum.DEPENDENCY.name() + ".dependenceChilds"
                + " left join fetch "
                + AliasEntityEnum.DEPENDENCY.name() + ".institutionalPlanChilds"
                + " order by "
                + AliasEntityEnum.DEPENDENCY.name() + ".dependenceId asc ";

        return super.find(myQueryHQL);

    }

    @Transactional(readOnly = false)
    @Override
    public void Save(DependenceEntity aDependence) {
        theirSessionFactory.getCurrentSession().saveOrUpdate(aDependence);
    }

    @Transactional(readOnly = false)
    @Override
    public void Delete(DependenceEntity aDependence) {
        theirSessionFactory.getCurrentSession().delete(aDependence);
    }

    @Transactional(readOnly = true)
    @Override
    public DependenceEntity getDependenceById(Long aDependenceId) {

        String myQueryHQL = "select distinct dep from DependenceEntity dep left join fetch dep.father"
                + " left join fetch dep.institutionalPlanChilds"
                + " left join fetch dep.dependenceChilds"
                + " left join fetch dep.dependenceLevel so"
                + " left join fetch so.dependenceLevelChilds"
                + " left join fetch dep.father "
                + " left join fetch dep.responsible "
                + " where dep.dependenceId = :dependenceId"
                + " order by dep.dependenceKey asc ";

        Query mySQLQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryHQL);

        mySQLQuery.setLong("dependenceId", aDependenceId);

        return (DependenceEntity) mySQLQuery.list().get(0);
    }

    @Transactional(readOnly = false)
    @Override
    public void save(List<DependenceEntity> aDependences) {
        for (DependenceEntity myDependenceEntity : aDependences) {
            theirSessionFactory.getCurrentSession().save(myDependenceEntity);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void Delete(List<DependenceEntity> aDependences) {
        for (DependenceEntity myDependenceEntity : aDependences) {
            theirSessionFactory.getCurrentSession().delete(myDependenceEntity);
        }
    }

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getHierarchicalDependencies(List<Long> anIdentities) {
        String myTableName = getMessage("abs.siif.entities.dependenceentity.tablename");
        String myPrimaryKey = getMessage("abs.siif.entities.dependenceentity.primaryKey");
        String myPivotColumn = getMessage("abs.siif.entities.dependenceentity.pivotcolumn");
        String myFields = getMessage("abs.siif.entities.dependenceentity.fields");
        StringBuilder myStringBuffer = new StringBuilder();
        String myPrefixContitional = "";
        for (Long myIdentity : anIdentities) {

            if (myStringBuffer.length() > 0) {
                myStringBuffer.append("or");
            }
            myStringBuffer.append("(");
            myStringBuffer.append(myPrefixContitional);
            myStringBuffer.append(myPrimaryKey);
            myStringBuffer.append("=");
            myStringBuffer.append(myIdentity);
            myStringBuffer.append(")");
        }
        return super.getHierarchicalStructures(myStringBuffer.toString(),
                myFields, myTableName, myPrimaryKey, myPivotColumn);

    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getHierarchicalDependencies(Long anIdentity) {
        List<Long> myIdentities = new ArrayList<Long>();
        myIdentities.add(anIdentity);
        return getHierarchicalDependencies(myIdentities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getHierarchicalDependenciesByEntities(List<DependenceEntity> anEntities) {
        List<Long> myIdentities = new ArrayList<Long>();
        for (DependenceEntity myEntity : anEntities) {
            myIdentities.add(myEntity.getDependenceId());
        }
        return getHierarchicalDependencies(myIdentities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getChilds(DependenceEntity father) {
        return this.findByCriteria(
                Restrictions.eq("father.dependenceId",
                father.getDependenceId()));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DependenceEntity> getViewHasBudgetDependencies() {
        return getViewHasBudgetFilter(null);
    }

    @Cacheable("dependenciesWithLevelIsUEG")
    @Transactional(readOnly = true)
    @Override
    public Collection<DependenceEntity> getViewHasBudgetFilter(String filter) {
        String level = "level.dependencyLevelHasBudget='t'";
        return findLevel(level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDependenciesTypeUR() {
        return getViewDepTypeURFilter(null);
    }

    @Cacheable("dependenciesWithLevelIsUR")
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepTypeURFilter(String filter) {
        String level = "level.dependencyLevelIsResponsibleUnit='t'";
        return findLevel(level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepHasProgramming() {
        return getViewDepHasProgrammingFilter(null);
    }

    @Cacheable("getViewDepIsProgrammingFilter")
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepHasProgrammingFilter(String filter) {
        String level = "level.dependencyLevelHasProgramming='t'";
        return findLevel(level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepIsExecutionUnit() {
        return getViewDepIsExecutionUnitFilter(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepIsExecutionUnitFilter(String filter) {
        String level = "level.dependencyLevelIsExecutionUnit='t'";
        return findLevel(level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepHasInstitutionalPlan() {
        return getViewDepHasInstitutionalPlanFilter(null);
    }

    //@Cacheable("getViewDepIsProgrammingFilter")
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepHasInstitutionalPlanFilter(String filter) {
        String level = "level.dependencyLevelHasInstitutionalPlan='t'";
        return findLevel(level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepIsBranch() {
        return getViewDepIsBranchFilter(null);
    }

    //@Cacheable("getViewDepIsProgrammingFilter")
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepIsBranchFilter(String filter) {
        String level = "level.dependencyLevelIsBranch='t'";
        return findLevel(level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepIsSector() {
        return getViewDepIsSectorFilter(null);
    }

    //@Cacheable("getViewDepIsProgrammingFilter")
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepIsSectorFilter(String filter) {
        String level = "level.dependencyLevelIsSector='t'";
        return findLevel(level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepHasObjFrame() {
        return getViewDepHasObjFrameFilter(null);
    }

    //@Cacheable("getViewDepIsProgrammingFilter")
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepHasObjFrameFilter(String filter) {
        String level = "level.dependencyLevelHasObjectiveFraming='t'";
        return findLevel(level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepIsBudgetUnit() {
        return getViewDepIsBudgetUnitFilter(null);
    }

    //@Cacheable("getViewDepIsProgrammingFilter")
    @Transactional(readOnly = true)
    @Override
    public List<DependenceEntity> getViewDepIsBudgetUnitFilter(String filter) {
        String level = "level.dependencyLevelIsBudgetUnit = 't'";
        return findLevel(level, filter);
    }

    /**
     * Obtiene todas las dependencias presupuestales Estan mapeadas atraves del
     * campo unipresupuestal
     *
     * @param idDependency
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsBudgetByDependIdRelated(
            Long idDependency){
       return  getDependciesIsBudgetByDependIdRelatedFilter(
               idDependency, null); 

    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsBudgetByDependIdRelatedFilter(
            Long idDependency, String filter){
        String level = " nivel.unipresupuestal = 't'";
        return getDependenciesByIdRelated(idDependency, level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsRespUnitByDependIdRelated(
            Long idDependency){
        
       return  getDependciesIsRespUnitByDependIdRelatedFilter(
               idDependency,  null); 

    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsRespUnitByDependIdRelatedFilter(
            Long idDependency, String filter){
        String level = " nivel.uniresponsable = 't'";
        return getDependenciesByIdRelated(idDependency, level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsExecUnitByDependIdRelated(
            Long idDependency){
        
       return  getDependciesIsExecUnitByDependIdRelatedFilter(
               idDependency,  null); 

    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsExecUnitByDependIdRelatedFilter(
            Long idDependency, String filter){
        String level = " nivel.uniejecutora = 't'";
        return getDependenciesByIdRelated(idDependency, level, filter);
    }

     @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsFrammingSectorByDependIdRelated(
            Long idDependency){
        
       return  getDependciesIsExecUnitByDependIdRelatedFilter(
               idDependency,  null); 

    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsFrammingSectorByDependIdRelatedFilter(
            Long idDependency, String filter){
        String level = " nivel.sector = 't'";
        return getDependenciesByIdRelated(idDependency, level, filter);
    }
    
    
    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesHasInstPlanByDependIdRelated(
            Long idDependency){
       return  getDependciesHasInstPlanByDependIdRelatedFilter(
               idDependency,  null); 
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesHasInstPlanByDependIdRelatedFilter(
            Long idDependency,  String filter){
        String level = " nivel.planinstitucional = 't'";
        return getDependenciesByIdRelated(idDependency, level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsBranchByDependIdRelated(
            Long idDependency){
       return  getDependciesIsBranchByDependIdRelatedFilter(
               idDependency,  null); 
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsBranchByDependIdRelatedFilter(
            Long idDependency,  String filter){
        String level = " nivel.ramo = 't'";
        return getDependenciesByIdRelated(idDependency, level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsSectorByDependIdRelated(
            Long idDependency){
       return  getDependciesIsSectorByDependIdRelatedFilter(
               idDependency, null); 
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesIsSectorByDependIdRelatedFilter(
            Long idDependency,  String filter){
        String level = " nivel.sector = 't'";
        return getDependenciesByIdRelated(idDependency, level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesHasBudgetByDependIdRelated(
            Long idDependency){
       return  getDependciesHasBudgetByDependIdRelatedFilter(
               idDependency,  null); 
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesHasBudgetByDependIdRelatedFilter(
            Long idDependency,  String filter){
        String level = " nivel.presupuesta = 't'";
        return getDependenciesByIdRelated(idDependency, level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesHasProgrammingByDependIdRelated(
            Long idDependency){
       return  getDependciesHasProgrammingByDependIdRelatedFilter(
               idDependency,  null); 
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesHasProgrammingByDependIdRelatedFilter(
            Long idDependency,  String filter){
        String level = " nivel.programacion = 't'";
        return getDependenciesByIdRelated(idDependency, level, filter);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesHasObjectiveFramingByDependIdRelated(
            Long idDependency){
       return  getDependciesHasObjectiveFramingByDependIdRelatedFilter(
               idDependency,  null); 
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepencenceDto> getDependciesHasObjectiveFramingByDependIdRelatedFilter(
            Long idDependency, String filter){
        String level = " nivel.encuadreplanest = 't'";
        return getDependenciesByIdRelated(idDependency, level, filter);
    }

    @Transactional(readOnly = true)
    private List<DependenceEntity> findLevel(String level, String filter) {
        String myQueryString = "select  "
                + AliasEntityEnum.DEPENDENCY.name()
                + " from DependenceEntity "
                + AliasEntityEnum.DEPENDENCY.name()
                + " left join fetch "
                + AliasEntityEnum.DEPENDENCY.name() + ".dependenceLevel level"
                + " where (" + level + ") ";
        if (filter != null) {
            myQueryString += " and " + filter;
        }

        myQueryString += " order by "
                + AliasEntityEnum.DEPENDENCY.name() + ".dependenceKey";

        return super.find(myQueryString);
    }

    @Transactional(readOnly = true)
    private List<DepencenceDto> getDependenciesByIdRelated(
        Long idDependency, String level, String filter) {
       
        String query = this.getMessage("siif.dao.nativequeries.SQLDependency");
        query += level;

        if (filter != null) {
            query += filter;
        }
         
        SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(query);
         
         mySQLQuery.setLong("denpendeceId", idDependency);
         mySQLQuery.setLong("denpendeceId1", idDependency);
         

        
        return getListFromSQLquery(mySQLQuery , DepencenceDto.class);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Collection<DependenceEntity> getDependencesByUr() {
        return getDependencesByUrFilter(null);
    }

    @Cacheable("dependenciesWithLevelIsUEG")
    @Transactional(readOnly = true)
    @Override
    public Collection<DependenceEntity> getDependencesByUrFilter(String filter) {
        String level = "level.dependencyLevelIsResponsibleUnit='t'";
        return findLevel(level, filter);
    }
    
    @Transactional(readOnly = true)
    @Override
    public Long GetUEGbyUR(Long urId) {
        String query="Select dpE.father.dependenceId from DependenceEntity dpE" +
                " where dpE.dependenceId = " + urId +"";
       List<Long> find = find(query);
       return find.get(0);
    }
    
     @Transactional(readOnly = true)
    @Override
    public Collection<DependenceEntity> getViewDepWithoutFather() {

         String myAnnio =SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString();
        String myQueryString = "select " + AliasEntityEnum.DEPENDENCY.name()
                + " from DependenceEntity " + AliasEntityEnum.DEPENDENCY.name()
                + " inner join " +AliasEntityEnum.DEPENDENCY.name() 
                + ".dependenceLevel n"
                + " where ((" + AliasEntityEnum.DEPENDENCY.name()
                + ".budgetKeyDefinitionDependency.presupuestalKeyDefinitionId="
                + "n.budgetKeyDefinitionLevels.presupuestalKeyDefinitionId)and"
                + "(" + AliasEntityEnum.DEPENDENCY.name()
                + ".budgetKeyDefinitionDependency.presupuestalKeyDefinitionYear="
                + myAnnio + ")and("+ AliasEntityEnum.DEPENDENCY.name()
                + ".father.dependenceId is null))"
                + "order by " + AliasEntityEnum.DEPENDENCY.name()
                + ".dependenceKey asc";


        return super.find(myQueryString);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DependenceEntity> getViewDepByFather(DependenceEntity 
            anEntity) {
        String myAnnio =SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString();
        String myQueryString = "select " + AliasEntityEnum.DEPENDENCY.name()
                + " from DependenceEntity " + AliasEntityEnum.DEPENDENCY.name()
                + " inner join " +AliasEntityEnum.DEPENDENCY.name() 
                + ".dependenceLevel n"
                + " where ((" + AliasEntityEnum.DEPENDENCY.name()
                + ".budgetKeyDefinitionDependency.presupuestalKeyDefinitionId="
                + "n.budgetKeyDefinitionLevels.presupuestalKeyDefinitionId)and"
                + "(" + AliasEntityEnum.DEPENDENCY.name()
                + ".budgetKeyDefinitionDependency.presupuestalKeyDefinitionYear="
                + myAnnio + ")and(" + AliasEntityEnum.DEPENDENCY.name()
                + ".father.dependenceId='%s'))"
                + "order by " + AliasEntityEnum.DEPENDENCY.name()
                + ".dependenceKey asc";
        

        myQueryString = String.format(myQueryString, anEntity.getDependenceId());
        return super.find(myQueryString);
    }


    @Transactional(readOnly=true)
    @Override
    public DependenceEntity getDependencyByEdit(DependenceEntity anEntity) {
      String myQueryString="select dep from DependenceEntity dep"
              + " left   join fetch dep.dependenceLevel lev"
              + " left   join fetch lev.colectiveType colType"
              + " left   join fetch dep.colective col"
              + " left   join fetch col.statusColective stacol"
              + " left   join fetch dep.employees employee"
              + " left   join fetch dep.administrativeClassifierType admintype" 
              + " where (dep.dependenceId='%s')";
      DependenceEntity myDependency=null;
      myQueryString=String.format(myQueryString, anEntity.getDependenceId());
      
      List myList=super.find(myQueryString);
      
      if (myList.size()>0)
          myDependency=(DependenceEntity) myList.get(0);
      
      return myDependency;
    }
    
    
 
}
