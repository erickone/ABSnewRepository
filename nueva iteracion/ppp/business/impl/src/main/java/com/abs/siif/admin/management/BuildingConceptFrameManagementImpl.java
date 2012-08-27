/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.admin.management;

import com.abs.siif.admin.dao.BuildingConceptFrameDao;
import com.abs.siif.admin.dto.BuildingConceptFrameDto;
import com.abs.siif.admin.entities.BuildingConceptFrameEntity;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.budget.dao.ObjectExpenseDao;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.programming.dao.ActionGBDao;
import com.abs.siif.programming.dao.BuildingConceptDao;
import com.abs.siif.programming.dao.GeneralConceptDao;
import com.abs.siif.programming.entities.ActionGBEntity;
import com.abs.siif.programming.entities.BuildingConceptEntity;
import com.abs.siif.programming.entities.GeneralConceptEntity;
import java.util.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author cesar.toledano
 */
@Service("buildingConceptFrameManagement")
public class BuildingConceptFrameManagementImpl implements BuildingConceptFrameManagement{
    
    private enum TYPE { GENERAL, BUILDING, ACTION, OBJECT };
    
    @Resource(name = "generalConceptDao")
    private GeneralConceptDao generalConceptDao;
    
    @Resource(name = "buildingConceptDao")
    private BuildingConceptDao buildingConceptDao;
    
    @Resource(name = "actionGBDao")
    private ActionGBDao actionGBDao;
    
    @Resource(name = "objectExpenseDao")
    private ObjectExpenseDao objectExpenseDao;
    
    @Resource(name = "buildingConceptFrameDao")
    private BuildingConceptFrameDao buildingConceptFrameDao;
    
    @Override
    public List<BuildingConceptFrameDto> getGeneralConcepts() {
        List<BuildingConceptFrameDto> generalConceptsDto = new ArrayList<BuildingConceptFrameDto>();
        Collection<GeneralConceptEntity> entities = generalConceptDao.getGeneralConcepts();
        
        for(GeneralConceptEntity entity : entities){
            BuildingConceptFrameDto dto = new BuildingConceptFrameDto();
            dto.setDescription(entity.getGeneralConceptDescription());
            dto.setId(entity.getGeneralConceptId());
            dto.setKey(entity.getGeneralConceptKey());
            generalConceptsDto.add(dto);
        }
        return generalConceptsDto;
    }

    @Override
    public List<BuildingConceptFrameDto> getBuildingConceptsUsed(Long id) {
        Set<BuildingConceptFrameDto> results = new HashSet<BuildingConceptFrameDto>();
        Collection<BuildingConceptFrameEntity> entities = buildingConceptFrameDao.getBuildingsByGeneral(id);
        
        for(BuildingConceptFrameEntity entity : entities){
            BuildingConceptFrameDto dto = new BuildingConceptFrameDto();
            dto.setDescription(entity.getBuildingConceptEntity().getBuildingConceptDescription());
            dto.setId(entity.getBuildingConceptId());
            dto.setKey(entity.getBuildingConceptEntity().getBuildingConceptKey());
            results.add(dto);
        }        
        return new ArrayList<BuildingConceptFrameDto>(results);
    }

    @Override
    public List<BuildingConceptFrameDto> getBuildingConceptsNotUsed(List<BuildingConceptFrameDto> buildingConceptsUsed) {
        Set<BuildingConceptFrameDto> results = new HashSet<BuildingConceptFrameDto>();
        int i = 0;
        Long[] ids = new Long[buildingConceptsUsed.size()];
        for(BuildingConceptFrameDto dto : buildingConceptsUsed){
            ids[i] = dto.getId();
            i++;
        }
                
        List<BuildingConceptEntity> entities = buildingConceptDao.getBuildingConceptsNotWithIds(ids);
        for(BuildingConceptEntity entity : entities){
            BuildingConceptFrameDto dto = new BuildingConceptFrameDto();
            dto.setDescription(entity.getBuildingConceptDescription());
            dto.setId(entity.getBuildingConceptId());
            dto.setKey(entity.getBuildingConceptKey());
            results.add(dto);
                    
        }
        return new ArrayList<BuildingConceptFrameDto>(results);
    }

    @Override
    public void generateGeneralConceptBuildingRelation(Long id, Long[] ids) {        
        buildingConceptFrameDao.saveAll(convertIdsToEntities(id, null, null, ids, TYPE.GENERAL));
    }
        
    @Override
    public boolean deleteGeneralConceptBuildingRelation(Long id, Long[] ids) {
        Long zeroLong = new Long(0);
        Collection<BuildingConceptFrameEntity> entities = buildingConceptFrameDao.getBuildingsByGeneral(id);
        for(Long buildingId : ids){
            for(BuildingConceptFrameEntity entity : entities){
                if(buildingId.equals(entity.getBuildingConceptId())){                    
                    if(!entity.getActionId().equals(zeroLong)){
                        return false;
                    }else{
                        break;
                    }
                }
            }
        }

        buildingConceptFrameDao.deleteAll(convertIdsToEntities(id, null, null, ids, TYPE.GENERAL));
        return true;
    }        

    @Override
    public List<BuildingConceptFrameDto> getActionsUsed(Long general, Long building) {
        Set<BuildingConceptFrameDto> results = new HashSet<BuildingConceptFrameDto>();
        Collection<BuildingConceptFrameEntity> entities = buildingConceptFrameDao.
                getActionsByGeneralAndBuilding(general, building);
        
        for(BuildingConceptFrameEntity entity : entities){
            BuildingConceptFrameDto dto = new BuildingConceptFrameDto();
            dto.setDescription(entity.getActionGBEntity().getActionGBDescription());
            dto.setId(entity.getActionId());
            dto.setKey(entity.getActionGBEntity().getActionGBKey());
            results.add(dto);
        }        
        return new ArrayList<BuildingConceptFrameDto>(results);
    }

    @Override
    public List<BuildingConceptFrameDto> getActionsNotUsed(List<BuildingConceptFrameDto> actionsUsed) {
        Set<BuildingConceptFrameDto> results = new HashSet<BuildingConceptFrameDto>();
        int i = 0;
        Long[] ids = new Long[actionsUsed.size()];
        for(BuildingConceptFrameDto dto : actionsUsed){
            ids[i] = dto.getId();
            i++;
        }
                
        List<ActionGBEntity> entities = actionGBDao.getActionsNotWithIds(ids);
        for(ActionGBEntity entity : entities){
            BuildingConceptFrameDto dto = new BuildingConceptFrameDto();
            dto.setDescription(entity.getActionGBDescription());
            dto.setId(entity.getActionGBId());
            dto.setKey(entity.getActionGBKey());
            results.add(dto);
                    
        }
        return new ArrayList<BuildingConceptFrameDto>(results);
    }

    @Override
    public void generateBuildingConceptActionRelation(Long generalId, Long buildingId, Long[] ids) {
        buildingConceptFrameDao.saveAll(convertIdsToEntities(generalId, buildingId, null, ids, TYPE.BUILDING));
    }

    @Override
    public boolean deleteBuildingConceptActionRelation(Long generalId, Long buildingId, Long[] ids) {
        Long zeroLong = new Long(0);
        Collection<BuildingConceptFrameEntity> entities = buildingConceptFrameDao.getActionsByGeneralAndBuilding(
                generalId, buildingId);
        
        for(Long actionId : ids){
            for(BuildingConceptFrameEntity entity : entities){
                if(actionId.equals(entity.getActionId())){                    
                    if(!entity.getObjectExpenseId().equals(zeroLong)){
                        return false;
                    }else{
                        break;
                    }
                }
            }
        }

        buildingConceptFrameDao.deleteAll(convertIdsToEntities(generalId, buildingId, null, ids, TYPE.BUILDING));
        return true;
    }

    @Override
    public List<BuildingConceptFrameDto> getObjectExpensesUsed(Long general, Long building, Long action) {
        Set<BuildingConceptFrameDto> results = new HashSet<BuildingConceptFrameDto>();
        Collection<BuildingConceptFrameEntity> entities = buildingConceptFrameDao.
                getObjectExpensesByGeneralBuildingAndAction(general, building, action);
        
        for(BuildingConceptFrameEntity entity : entities){
            BuildingConceptFrameDto dto = new BuildingConceptFrameDto();
            dto.setDescription(entity.getObjectExpenseEntity().getObjectExpenseDescription());
            dto.setId(entity.getObjectExpenseId());
            dto.setKey(entity.getObjectExpenseEntity().getObjectExpenseKey() + "");
            results.add(dto);
        }        
        return new ArrayList<BuildingConceptFrameDto>(results);
    }

    @Override
    public List<BuildingConceptFrameDto> getObjectExpensesNotUsed(List<BuildingConceptFrameDto> expensesUsed) {
        Set<BuildingConceptFrameDto> results = new HashSet<BuildingConceptFrameDto>();
        int i = 0;
        Long[] ids = new Long[expensesUsed.size()];
        for(BuildingConceptFrameDto dto : expensesUsed){
            ids[i] = dto.getId();
            i++;
        }
                
        List<ObjectExpenseEntity> entities = objectExpenseDao.getObjectExpensesNotWithIds(ids);
        for(ObjectExpenseEntity entity : entities){
            boolean add = true;
            //@TODO filter by object expense public investment
            //Business rule: 
            //only "active" "public investment" "specific departure" "actual year" object expense can be added
            if(!entity.isObjectExpensePublicInvestment()){
                //add = false;
            }   
            if(!entity.getobjectExpenseLevelEnt().isObjectExpenseSpecificPar()){
                add = false;
            }
            
//            String year = (String) SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR);                        
//            if(entity.getCvePresupuestalEntity().getYear().equals(year)){
//                add = false;
//            }
            
            if(add){
                BuildingConceptFrameDto dto = new BuildingConceptFrameDto();
                dto.setDescription(entity.getObjectExpenseDescription());
                dto.setId(entity.getObjectExpenseId());
                dto.setKey(entity.getObjectExpenseKey() + "");
                results.add(dto); 
            }                                                               
        }                
        return new ArrayList<BuildingConceptFrameDto>(results);
    }

    @Override
    public void generateActionObjectExpenseRelation(Long general, Long building, Long action, Long[] ids) {
        buildingConceptFrameDao.saveAll(convertIdsToEntities(general, building, action, ids, TYPE.ACTION));
    }

    @Override
    public boolean deleteActionObjectExpenseRelation(Long general, Long building, Long action, Long[] ids) {
        buildingConceptFrameDao.deleteAll(convertIdsToEntities(general, building, action, ids, TYPE.ACTION));
        return true;
    }
    
    private List<BuildingConceptFrameEntity> convertIdsToEntities(
            Long general, Long building, Long action, Long[] ids, Enum type){
        
        List<BuildingConceptFrameEntity> entities = new ArrayList<BuildingConceptFrameEntity>();
        if(type.equals(TYPE.GENERAL)){
            for(Long buildingId : ids){
                BuildingConceptFrameEntity entity = new BuildingConceptFrameEntity();                        
                entity.setGeneralConceptId(general);
                entity.setBuildingConceptId(buildingId);
                entity.setActionId(new Long(0));
                entity.setObjectExpenseId(new Long(0));
                entities.add(entity);
            
            }
            
        }else if(type.equals(TYPE.BUILDING)){
            for(Long actionId : ids){
                BuildingConceptFrameEntity entity = new BuildingConceptFrameEntity();                        
                entity.setGeneralConceptId(general);
                entity.setBuildingConceptId(building);
                entity.setActionId(actionId);
                entity.setObjectExpenseId(new Long(0));
                entities.add(entity);            
            }
            
        }else if(type.equals(TYPE.ACTION)){
            for(Long objectExpenseId : ids){
                BuildingConceptFrameEntity entity = new BuildingConceptFrameEntity();                        
                entity.setGeneralConceptId(general);
                entity.setBuildingConceptId(building);
                entity.setActionId(action);
                entity.setObjectExpenseId(objectExpenseId);
                entities.add(entity);            
            }
            
        }
        return entities;
    }

}
