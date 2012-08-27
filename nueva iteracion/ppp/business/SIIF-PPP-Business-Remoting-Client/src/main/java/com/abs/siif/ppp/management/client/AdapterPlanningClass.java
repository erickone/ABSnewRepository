/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AdapterPlanningClass
 *  Purpose:  [ short Description  ]
 *
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.management.client;

import com.abs.siif.AdapterBaseSiif;
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.planning.data.SaveType;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.*;
import com.abs.siif.planning.management.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component("planningadapterClass")
public class AdapterPlanningClass extends AdapterBaseSiif implements
        AdministrativeClassifierTypeManagement,
        DependenceLevelManagement,
        DependenceManagement,
        DependencyLevelManagement,
        DependencyManagement,
        FunctionalClassifierLevelManagement,
        FunctionalClassifierManagement,
        InstitutionalPlanManagement,
        ObjectiveLevelManagement,
        ObjectiveManagement,
        RegClassifManagement,
        RegionalClassifierLevelManagement,
        RegionalPlanManagement
{

    @Override
    public Collection<AdministrativeClassifierTypeEntity> getAdministrativeClassifierTypes(){
        beanName = "administrativeClassifierTypeManagement";
        return null;
    }

    @Override
    public Map<String, List<?>> getAllDependenceLevelsAndSupportListByYear(
            int aYear){
        beanName = "dependenceLevelManagement";
        return null;
    }

    @Override
    public Long saveDependenceLevel(DependenceLevelEntity aDependenceEntity){
        beanName = "dependenceLevelManagement";
        return null;
    }

    @Override
    public void deleteDependenceLevel(DependenceLevelEntity aDependenceEntity){
        beanName = "dependenceLevelManagement";
    }

    @Override
    public List<DependenceEntity> getViewDepIsBudgetUnit(){
        beanName = "dependenceManagement";
        return null;
    }

    @Override
    public List<DepencenceDto> getDependciesIsRespUnitByDependIdRelated(
            Long idDependency){
        beanName = "dependenceManagement";
        return null;
    }

    @Override
    public List<DepencenceDto> getDependciesIsExecUnitByDependIdRelated(
            Long idDependency){
        beanName = "dependenceManagement";
        return null;
    }

    @Override
    public List<DependenceEntity> getViewDependenciesTypeUR(){
        beanName = "dependenceManagement";
        return null;
    }

    @Override
    public List<DependenceEntity> getViewDepIsExecutionUnit(){
        beanName = "dependenceManagement";
        return null;
    }

    @Override
    public DependenceEntity getDependenceById(Long aDependenceId){
        beanName = "dependenceManagement";
        return null;
    }

    @Override
    public DependenceLevelEntity getDependencyLevelByLevel(DependenceLevelEntity
            aDependenceLevelEntity){
        beanName = "dependencyLevelManagement";
        return null;
    }

    @Override
    public int getMaxLevel(BudgetKeyDefinitionEntity aBudgetKeyDefinition){
        beanName = "dependencyLevelManagement";
        return 0;
    }

    @Override
    public DependenceLevelEntity getDependenceLevelWithColective(
            DependenceLevelEntity
            aDependencyLevel){
        beanName = "dependencyLevelManagement";
        return null;
    }

    @Override
    public DependenceLevelEntity getDependenceLevelByLevelByAnnio(short aLevel){
        beanName = "dependencyLevelManagement";
        return null;
    }

    @Override
    public Map<String,List<?>> getSupportList(){
        beanName = "dependencyManagement";
        return null;
    }

    @Override
    public DependenceEntity getDependecyById(DependenceEntity anEntity){
        beanName = "dependencyManagement";
        return null;
    }

    @Override
    public DependenceEntity persistDependency(DependenceEntity anEntity){
        beanName = "dependencyManagement";
        return null;
    }

    @Override
    public void deleteDependency(DependenceEntity anEntity){
        beanName = "dependencyManagement";
    }

    @Override
    public FunctionalLevelClassifier getFuncClassifierLevelByLevel(int aFCLevel){
        beanName = "functionalClassifierLevelManagement";
        return null;
    }

    @Override
    public List<FunctionalLevelClassifier> getAllFunClassifierLevels(){
        beanName = "functionalClassifierLevelManagement";
        return null;
    }

    @Override
    public Long saveOrUpdate(
            FunctionalLevelClassifier anEntity, SaveType aSaveType){
        beanName = "functionalClassifierLevelManagement";
        return null;
    }

    @Override
    public void deleteFunctionalCassifier(
            List<FunctionalLevelClassifier> anEntityList){
        beanName = "functionalClassifierLevelManagement";
    }

    @Override
    public List<FunctionalClassifierEntity> getAllFuncClassifiers(){
        beanName = "functionalClassifierManagement";
        return null;
    }

    @Override
    public FunctionalClassifierEntity getFunctionalClassifierById(
            Long aFuncClassifierId){
        beanName = "functionalClassifierManagement";
        return null;
    }

    @Override
    public Long Save(FunctionalClassifierEntity anFCData,SaveType aSaveType){
        beanName = "functionalClassifierManagement";
        return null;
    }

    @Override
    public void deleteFunctionalClassifier(Long aFuncClassifierId){
        beanName = "functionalClassifierManagement";
    }

    @Override
    public FunctionalClassifierEntity getFuncClassifierByIdWithObjs(
            Long anIdentity){
        beanName = "functionalClassifierManagement";
        return null;
    }

    @Override
    public Long getNextFuncClassifierKey(String IdFC){
        beanName = "functionalClassifierManagement";
        return null;
    }

    @Override
    public Collection<FunctionalClassifierEntity> getMyFunctionalClassifierAncestry(
            Long aObjectiveId){
        beanName = "functionalClassifierManagement";
        return null;
    }

    @Override
    public List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceId(
            Long aDependenceId) {
        beanName = "institutionalPlanManagement";
        return null;
    }

    @Override
    public InstitutionalPlanEntity getInstitutionalPlanById(
            Long anInstitutionalPlanId){
        beanName = "institutionalPlanManagement";
        return null;
    }

    @Override
    public void deleteInstitutionalPlan(Long anIdInstitutionalPlan){
        beanName = "institutionalPlanManagement";
    }

    @Override
    public Long updateInstitutionalPlan(
            InstitutionalPlanEntity anInstitutionalPlanEntity){
        beanName = "institutionalPlanManagement";
        return null;
    }

    @Override
    public Long addInstitutionalPlan(
            InstitutionalPlanEntity anInstitutionalPlanEntity){
        beanName = "institutionalPlanManagement";
        return null;
    }

    @Override
    public void saveObjectives(List<InstitutionalPlanObjectiveEntity> list){
        beanName = "institutionalPlanManagement";
    }

    @Override
    public List<InstitutionalPlanObjectiveEntity>searchInstPlanObjectives(
            InstitutionalPlanEntity input){
        beanName = "institutionalPlanManagement";
        return null;
    }

    @Override
    public void deleteObjectives(List<InstitutionalPlanObjectiveEntity> list){
        beanName = "institutionalPlanManagement";
    }

    @Override
    public List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceChildId(
            Long aDependenceId){
        beanName = "institutionalPlanManagement";
        return null;
    }

    @Override
    public List<ObjectiveLevelEntity> getAllObjectiveLevels(){
        beanName = "objectiveLevelManagement";
        return null;
    }

    @Override
    public Long saveOrUpdate(ObjectiveLevelEntity anObjectiveLevel){
        beanName = "objectiveLevelManagement";
        return null;
    }

    @Override
    public void delete(List<ObjectiveLevelEntity> anObjectiveLevels){
        beanName = "objectiveLevelManagement";
    }

    @Override
    public ObjectiveLevelEntity getObjectiveLevelByLevel(short aLevel){
        beanName = "objectiveLevelManagement";
        return null;
    }

    @Override
    public List<ObjectiveEntity> GetAllObjectives(){
        beanName = "objectiveManagement";
        return null;
    }

    @Override
    public ObjectiveEntity getObjectiveById(Long anIdObjective){
        beanName = "objectiveManagement";
        return null;
    }

    @Override
    public ObjectiveEntity getObjectiveByIdentity(Long anIdObjective){
        beanName = "objectiveManagement";
        return null;
    }

    @Override
    public void deleteObjective(Long anIdObjective){
        beanName = "objectiveManagement";
    }

    @Override
    public void updateObjective(
            Long anObjectiveId, String anObjectiveName, String anObjectiveDefinition, Short anObjectivePriority,
            Long anObjectiveLevelId, Long aFatherId){
        beanName = "objectiveManagement";
    }

    @Override
    public ObjectiveEntity getObjectiveEagerByIdentity(Long anObjectiveId){
        beanName = "objectiveManagement";
        return null;
    }

    @Override
    public ObjectiveEntity getObjectiveSpecificObjAndIndicatorByIdentity(
            Long anObjectiveId){
        beanName = "objectiveManagement";
        return null;
    }

    @Override
    public Long Save(ObjectiveEntity anObjectiveData,SaveType aSaveType){
        beanName = "objectiveManagement";
        return null;
    }

    @Override
    public List<DependenceEntity> getChildsRelatedObjList(Long anObjectiveId){
        beanName = "objectiveManagement";
        return null;
    }

    @Override
    public Set<FunctionalClassifierEntity>getFunctionalClassifiersByObjectiveId(
            Long anObjectiveId){
        beanName = "objectiveManagement";
        return null;
    }

    @Override
    public List<ObjectiveEntity> getStateAlignmet(ObjectiveEntity subProgram){
        beanName = "objectiveManagement";
        return null;
    }

    @Override
    public List<RegionalClassifierEntity> getRegionsByFatherId(
            RegionalClassifierEntity aRegionFather){
        beanName = "regClassifManagement";
        return null;
    }

    @Override
    public RegionalClassifierEntity getRegionalClassifierById(
            RegionalClassifierEntity aRegionalClassifierId){
        beanName = "regClassifManagement";
        return null;
    }

    @Override
    public Collection<RegionalClassifierEntity> getAllRegionalClassifier(){
        beanName = "regClassifManagement";
        return null;
    }

    @Override
    public Collection<RegionalClassifierEntity> getRegionalClassifierWithoutFather(){
        beanName = "regClassifManagement";
        return null;
    }

    @Override
    public Long saveOrUpdateRegion(
            RegionalClassifierEntity aRegionalClassifierEntity){
        beanName = "regClassifManagement";
        return null;
    }

    @Override
    public void deleteRegionalClassifier(
            RegionalClassifierEntity aRegionalClassifierEntity){
        beanName = "regClassifManagement";
    }

    @Override
    public RegionalLevelClassifierEntity getRegionalClassifierLevelByLevel(
            int aFCLevel){
        beanName = "regionalClassifierLevelManagement";
        return null;
    }

    @Override
    public List<RegionalLevelClassifierEntity> getAllRegionalClassifierLevels(){
        beanName = "regionalClassifierLevelManagement";
        return null;
    }

    @Override
    public Long saveOrUpdate(
            RegionalLevelClassifierEntity anEntity, SaveType aSaveType){
        beanName = "regionalClassifierLevelManagement";
        return null;
    }

    @Override
    public void deleteRegionalClassifier(
            List<RegionalLevelClassifierEntity> anEntityList){
        beanName = "regionalClassifierLevelManagement";
    }

    @Override
    public RegionalLevelClassifierEntity getRegionalLevelClassifierById
            (RegionalLevelClassifierEntity aRegionalLevelClassifierEntity){
        beanName = "regionalClassifierLevelManagement";
        return null;
    }

    @Override
    public Collection<RegionalClassifierEntity> getAllRegionalClassifierRP(){
        beanName = "regionalPlanManagement";
        return null;
    }

    @Override
    public Collection<RegionalPlanEntity> getRegionalPlanByRegionalClassifier(
            Long aRegionalClassifierId){
        beanName = "regionalPlanManagement";
        return null;
    }

    @Override
    public Long persistEntity(RegionalPlanEntity aRegionaPlan){
        beanName = "regionalPlanManagement";
        return null;
    }

    @Override
    public void deleteRegionPlans(List<RegionalPlanEntity> aRegionalPlans){
        beanName = "regionalPlanManagement";
    }
}
