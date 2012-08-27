/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.admin.dao;


import com.abs.siif.admin.entities.BuildingConceptFrameEntity;
import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ABS
 */
@Repository("buildingConceptFrameDao")
public class BuildingConceptFrameDaoImpl extends SIIFBaseDaoImpl<BuildingConceptFrameEntity, String> 
implements BuildingConceptFrameDao{
    
    @Autowired
    private SessionFactory itsSessionFactory;
    
    @Override
    public SessionFactory getTheirSessionFactory() {
        return itsSessionFactory;
    }
            
    @Transactional(readOnly = true)
    @Override
    public Collection<BuildingConceptFrameEntity> getBuildingsByGeneral(Long general) {
        String myQueryString = "select entity from BuildingConceptFrameEntity entity"
                    + " left join fetch entity.buildingConceptEntity bc"
                    + " where entity.generalConceptId = :generalConceptId";
        Query myQuery = itsSessionFactory.getCurrentSession().createQuery(myQueryString).
                    setLong("generalConceptId", general);
        
        return myQuery.list();
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<BuildingConceptFrameEntity> getActionsByGeneralAndBuilding(Long general, Long building) {
        String myQueryString = "select entity from BuildingConceptFrameEntity entity"
                + " left join fetch entity.actionGBEntity agb"
                + " where entity.buildingConceptId = :buildingConceptId"
                + " and entity.generalConceptId = :generalConceptId";
        Query myQuery = itsSessionFactory.getCurrentSession().createQuery(myQueryString).
                setLong("buildingConceptId", building).
                setLong("generalConceptId", general);
        
        return myQuery.list();
    }
    
    @Transactional(readOnly = true)
    @Override
    public Collection<BuildingConceptFrameEntity> getObjectExpensesByGeneralBuildingAndAction(
            Long general, Long building, Long action) {

        String myQueryString = "select entity from BuildingConceptFrameEntity entity"
                + " left join fetch entity.objectExpenseEntity oe"
                + " where entity.buildingConceptId = :buildingConceptId"
                + " and entity.generalConceptId = :generalConceptId"
                + " and entity.actionId = :actionId";
        Query myQuery = itsSessionFactory.getCurrentSession().createQuery(myQueryString).
                setLong("buildingConceptId", building).
                setLong("generalConceptId", general).
                setLong("actionId", action);
        
        return myQuery.list();
    }
    
    @Transactional(readOnly = false)
    @Override
    public void saveAll(List<BuildingConceptFrameEntity> entities) {
        String myQueryString = "insert into siifpppconceptogralobraaccion "
                + " (idconceptogral, idconceptoobra, idaccionmetabenef, idobjetogasto)"
                + " values(:idgeneral, :idobra, :idaccion, :idobjetogasto)";
        
        for(BuildingConceptFrameEntity entity : entities){       
            SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(myQueryString);            
            mySQLQuery.setLong("idgeneral", entity.getGeneralConceptId());
            mySQLQuery.setLong("idobra", entity.getBuildingConceptId());
            mySQLQuery.setLong("idaccion", entity.getActionId());
            mySQLQuery.setLong("idobjetogasto", entity.getObjectExpenseId());
            mySQLQuery.executeUpdate();
        }        
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(List<BuildingConceptFrameEntity> entities) {
        super.deleteAll(entities);
    }
}
