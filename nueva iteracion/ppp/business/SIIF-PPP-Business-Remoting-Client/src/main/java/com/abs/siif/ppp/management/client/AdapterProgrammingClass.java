/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AdapterProgrammingClass
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
import com.abs.siif.budget.dto.*;
import com.abs.siif.budget.entities.*;
import com.abs.siif.common.entities.DocumentTypeEntity;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.*;
import com.abs.siif.programming.dto.*;
import com.abs.siif.programming.entities.*;
import com.abs.siif.programming.management.*;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.stereotype.Component;

@Component("programmingadapterClass")
public class AdapterProgrammingClass extends AdapterBaseSiif implements
        ActionGBManagement,
        AdministrativeClassifierManagement,
        BenefAndGoalsManagement,
        BudgetingManagement,
        BudgetingSummaryManagement,
        BuildingConceptManagement,
        ClassifierManagement,
        ComponentManagement,
        DeliveryManagement,
        DependenceProgrammingManagement,
        DepToObjLinkManagement,
        DirectCaptureDraftProject,
        DraftProjectBinnacleManagement,
        DraftProjectManagement,
        DraftProjectSearchManagement,
        DraftProjectStateManagement,
        DraftProjectStatusManagement,
        DraftProjectTypeManagement,
        FinancialStructureManagment,
        GeneralConceptManagement,
        InstPlanProgManagement,
        InvestmentLineManagement,
        InvPreFileManagement,
        LogicFrameManagement,
        MacroBuildManagement,
        ObjectiveInstitutionalPlanFramingManagement,
        ObjectiveProgrammingManagement,
        ObservationManagement,
        PedVinculationManagement,
        PhysicalAndFinancialProgramManagement,
        PreInvRequestManagement,
        ProgrammingManagement,
        PromoterManagement,
        RequestUploadFilesManagement,
        SefinValidationManagement,
        SignaturesManagement,
        SignaturesTypeManagement,
        SizingManagement,
        UbicationManagement,
        UnitMeasureBenefAndGoalManagement,
        UnitMeasureGoalManagement,
        ValidationManagement,
        VulnerableGroupManagement,
        WorkFlowDraftProjectManagement
{

    @Override
    public Collection<ActionGBEntity> getActionGBs(){
        beanName = "actionGBManagement";
        return null;
    }

    @Override
    public Collection<ActionGBEntity> getActionsByBuildingConcept(
            Long anIdentity){
        beanName = "actionGBManagement";
        return null;
    }

    @Override
    public Collection<DependenceEntity> getMyAncestryClassiFier(
            Long aDependenceId){
        beanName = "administrativeClassifierManagement";
        return null;
    }

    @Override
    public List<DepencenceDto> getMyUEGsByDependenceRelated(Long aDependenceId){
        beanName = "administrativeClassifierManagement";
        return null;
    }

    @Override
    public List<DepencenceDto> getMyURByDependenceRelated(Long aDependenceId){
        beanName = "administrativeClassifierManagement";
        return null;
    }

    @Override
    public List<DepencenceDto> getMyUPByDependenceRelated(Long aDependenceId){
        beanName = "administrativeClassifierManagement";
        return null;
    }

    @Override
    public List<DepencenceDto> getMyFrammingSectorByDependenceRelated(
            Long aDependenceId){
        beanName = "administrativeClassifierManagement";
        return null;
    }

    @Override
    public BranchSectorAndDependenceFrammingEntity getMyFrammingByDependenceId(
            DependenceEntity aDependence){
        beanName = "administrativeClassifierManagement";
        return null;
    }

    @Override
    public Map<String, List<?>> getSupportBenGoalList(){
        beanName = "benefAndGoalsManagement";
        return null;
    }

    @Override
    public Long persist(BenefAndGoalsEntity myBenefAndGoal){
        beanName = "benefAndGoalsManagement";
        return null;
    }

    @Override
    public BenefAndGoalsEntity getBenefGoalsByInvPreFile(Long invPreFileId){
        beanName = "benefAndGoalsManagement";
        return null;
    }

    @Override
    public List<DestinyObjectExpenseRUBUEntity> getTheBudgetingDestinationByObject(
            DependenceEntity aDependence, ObjectExpenseEntity aBudgetingDto){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public List<ObjectExpenseEntity> getTheBudgetingFramming(
            DependenceEntity aDependence, BudgetingSummaryDto aBudgetingDto, boolean investFlag){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public FinancingSourceEntity getFinancingSourceById(
            FinancingSourceEntity aFinancingSource){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public Collection<FinancingSourceEntity> getFinancingSourceByObjectExpense(
            Long anObjectExpenseId){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public Collection<DestinationEntity> getDestinyByObjectExpense(
            Long anObjectExpenseId){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public Long saveBudgetKey(
            BudgetKeyEntity aBudgetEntity, BudgetDispURDto dto, Boolean ficha){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public Long saveBudgetAndProjectFraming(
            BudgetKeyAndDraftProjectFramingEntity aBudgetEntity){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public void deleteABudgetAndProjectFraming(
            BudgetKeyAndDraftProjectFramingEntity aBudgerFramingEntity){
        beanName = "budgetingManagement";
    }

    @Override
    public BudgetKeyAndDraftProjectFramingEntity getABudgetAndProjectFramingByIds(
            Long aProjectId, Long aBudgetKeyId){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public Long getCeilingAvailableByDependenceId(Long aDependenceId){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public void persistBudgetKey(BudgetKeyEntity aBudgetEntity){
        beanName = "budgetingManagement";
    }

    @Override
    public ObjectExpenseEntity getObjectExpenseById(Long anObjectExpenseId){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public Collection<BudgetKeyEntity> getTheBudgetingKeysWithProgramaticKey(
            String aProgranmaticKey){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public void deleteBudgetProcess(BudgetDispURDto dto){
        beanName = "budgetingManagement";
    }

    @Override
    public void deleteBudgetKey(Long aBudgetKeyEntity){
        beanName = "budgetingManagement";
    }

    @Override
    public BudgetKeyEntity getBudgetEntityById(Long aBudgetEntiityId){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public void updateABudgetKeyEntity(
            BudgetKeyEntity myBudgetKey, BudgetDispURDto dto){
        beanName = "budgetingManagement";
    }

    @Override
    public DestinationEntity getDestinationByKey(String aKey){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public BudgetKeyBreakDownDto getBudgetDesgloce(Long lCvePptalId){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public boolean modifyCeilingBudget(
            BudgetDispURDto dto, BudgetKeyBreakDownDto objectDto){
        beanName = "budgetingManagement";
        return false;
    }

    @Override
    public DepencenceDto getDependenciesRespUnitBytDependIdRelated(
            Long dependenceId){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public List<DestinyObjectExpenseRUBUEntity> getTheBudgetingDestinationByObjectInv(
            DependenceEntity aDependence, ObjectExpenseEntity aBudgetingDto){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public DestinationDto getEncDepObjGasDest(
            Long anObjectExpenseId, Long aDependenceId, String aDestinyKey){
        beanName = "budgetingManagement";
        return null;
    }

    @Override
    public List<DepencenceDto> getTheRelatedUEGs(DependenceEntity aDependence){
        beanName = "budgetingSummaryManagement";
        return null;
    }

    @Override
    public List<ComponentEntity> getCompByDraftProId(Long DraftProjectId){
        beanName = "budgetingSummaryManagement";
        return null;
    }

    @Override
    public DependenceEntity getDependenceById(Long aDependenceId){
        beanName = "budgetingSummaryManagement";
        return null;
    }

    @Override
    public List<ObjectExpenseEntity> getObjectExpenseRoots(){
        beanName = "budgetingSummaryManagement";
        return null;
    }

    @Override
    public BudgetSummaryDto getAllAmountsOfChapters(
            String aProgrammaticKey,String aditionalFlag){
        beanName = "budgetingSummaryManagement";
        return null;
    }

    @Override
    public Collection<ObjectExpenseEntity> getTheObjectExpenseHeirachy(
            Long myObjectExpenseId){
        beanName = "budgetingSummaryManagement";
        return null;
    }

    @Override
    public Long getTheLevelofTheSpecificParId(){
        beanName = "budgetingSummaryManagement";
        return null;
    }

    @Override
    public Long getAvailableAdditional(BudgetKeyAdditionalDto anAdditionalDto){
        beanName = "budgetingSummaryManagement";
        return null;
    }

    @Override
    public List<BudgetingDependenceTotalDto> getMyDependenceSummaryDTO
            (List<DepencenceDto> myListOfDependences,Long DrafProjectId){
        beanName = "budgetingSummaryManagement";
        return null;
    }

    @Override
    public List<BudgetingSummaryDto> getTheMegaSummaryByUR
            (List<DepencenceDto> myListOfDependences,Long DrafProjectId,
            List<ObjectExpenseEntity> myObjectExpenseRoots){
        beanName = "budgetingSummaryManagement";
        return null;
    }

    @Override
    public Collection<BuildingConceptEntity> getBuildingConceptsByConceptGeneral(
            Long anIdentity){
        beanName = "buildingConceptManagement";
        return null;
    }

    @Override
    public ClassifierEntity saveClassifier(ClassifierEntity
            aClassifierEntity){
        beanName = "classifierManagement";
        return null;
    }

    @Override
    public ClassifierEntity getClassifierByDraftProyectId(Long aDraftProyectId){
        beanName = "classifierManagement";
        return null;
    }

    @Override
    public List<ComponentEntity> getComponentByDelivery(
            DeliveryEntity aDeliveryEntity){
        beanName = "componentManagement";
        return null;
    }

    @Override
    public List<DeliveryEntity> getDeliveries(DraftProjectEntity draftProj){
        beanName = "deliveryManagement";
        return null;
    }

    @Override
    public List<ComponentEntity> getComponents(DeliveryEntity delivery){
        beanName = "deliveryManagement";
        return null;
    }

    @Override
    public List<ActivityEntity> getActivities(ComponentEntity component){
        beanName = "deliveryManagement";
        return null;
    }

    @Override
    public List<UnitMeasureEntity> getUnitMeasureCatalog(){
        beanName = "deliveryManagement";
        return null;
    }

    @Override
    public DeliveryEntity deleteComponent(ComponentEntity component){
        beanName = "deliveryManagement";
        return null;
    }

    @Override
    public DeliveryEntity saveComponent(ComponentEntity component){
        beanName = "deliveryManagement";
        return null;
    }

    @Override
    public List<DeliveryEntity> addPropositToDelivery(DeliveryEntity delivery){
        beanName = "deliveryManagement";
        return null;
    }

    @Override
    public ComponentEntity addActivityToComp(ActivityEntity  activity){
        beanName = "deliveryManagement";
        return null;
    }

    @Override
    public ComponentEntity deleteActivity(ActivityEntity acticity){
        beanName = "deliveryManagement";
        return null;
    }

    @Override
    public List<DependenceEntity> getDependencesByUEG(){
        beanName = "dependenceProgrammingManagement";
        return null;
    }

    @Override
    public List<DependenceEntity> getDependencesHasProgramming(){
        beanName = "dependenceProgrammingManagement";
        return null;
    }

    @Override
    public Collection<DependenceEntity> getViewDepWithoutFather(){
        beanName = "dependenceProgrammingManagement";
        return null;
    }

    @Override
    public Collection<DependenceEntity> getViewDepByFather(DependenceEntity
            aDependencyId){
        beanName = "dependenceProgrammingManagement";
        return null;
    }

    @Override
    public boolean saveGeneralDataInvPreFile(DependenceEntity aDependenceEntity){
        beanName = "depToObjLinkManagement";
        return false;
    }

    @Override
    public List<DepencenceDto> getFathersList(){
        beanName = "depToObjLinkManagement";
        return null;
    }

    @Override
    public List<DependenceEntity> getChildsList(Long idFather){
        beanName = "depToObjLinkManagement";
        return null;
    }

    @Override
    public List<DependenceEntity> getChildsRelatedObj(
            Long idDepFather, Long idObjective){
        beanName = "depToObjLinkManagement";
        return null;
    }

    @Override
    public boolean saveObjectiveWithDependencies(
            ObjectiveEntity anObjectiveEntity, Long idpadre){
        beanName = "depToObjLinkManagement";
        return false;
    }

    @Override
    public ClasificationDTO getCatalogs(Long resposableUnit){
        beanName = "directCaptureDraftProject";
        return null;
    }

    @Override
    public List<DependenceEntity> getDraftProjectDependencesByUEG(){
        beanName = "directCaptureDraftProject";
        return null;
    }

    @Override
    public Map<String, List<?>> getDraftProjectSupportLists(){
        beanName = "directCaptureDraftProject";
        return null;
    }

    @Override
    public Collection<FunctionalClassifierEntity> getMyAncestry(
            Long aObjectiveId){
        beanName = "directCaptureDraftProject";
        return null;
    }

    @Override
    public Long GetUEGbyUR(DependenceEntity resposableUnit){
        beanName = "directCaptureDraftProject";
        return null;
    }

    @Override
    public Collection<DraftProjectBinnacleEntity> getDraftProjectBinnaclebyDraftProjectId(
            Long aDraftProjectID){
        beanName = "draftProjectBinnacleManagement";
        return null;
    }

    @Override
    public void saveDraftProjectBinnacle(
            DraftProjectBinnacleEntity aDraftProjectBinnacleEntity){
        beanName = "draftProjectBinnacleManagement";
    }

    @Override
    public Date getDateOfLastStatus(Long aDraftPtojectId, Long StatusId){
        beanName = "draftProjectBinnacleManagement";
        return null;
    }

    @Override
    public void updateDraftProjectStatus(
            DraftProjectBinnacleEntity aDraftBinnacle){
        beanName = "draftProjectBinnacleManagement";
    }

    @Override
    public Map<String, List<?>> getSupportLists(){
        beanName = "draftProjectManagement";
        return null;
    }

    @Override
    public DraftProjectEntity getDraftProjectById(Long anIdentity){
        beanName = "draftProjectManagement";
        return null;
    }

    @Override
    public DraftProjectEntity getDraftProjectByIdWithBudgets(Long anIdentity){
        beanName = "draftProjectManagement";
        return null;
    }

    @Override
    public DraftProjectEntity getDraftProjectByIdEager(Long anIdentity){
        beanName = "draftProjectManagement";
        return null;
    }

    @Override
    public DraftProjectEntity saveDraftProject(
            DraftProjectEntity aDraftProject, Long anObjectiveId){
        beanName = "draftProjectManagement";
        return null;
    }

    @Override
    public DraftProjectStatusEntity getStatusEntityByConsecutive(
            int aConsecutive){
        beanName = "draftProjectManagement";
        return null;
    }

    @Override
    public String getTotalProjects(Long anDependenceId){
        beanName = "draftProjectManagement";
        return null;
    }

    @Override
    public int getDraftProjectInDeterminateStatus(
            DraftProjectEntity aDraftProjectEntity){
        beanName = "draftProjectManagement";
        return 0;
    }

    @Override
    public Collection<DraftProjectEntity> getDraftProInWorkFlow(
            DraftProjectSearchDto
            aDraftProjectSearchDto){
        beanName = "draftProjectManagement";
        return null;
    }

    @Override
    public boolean deleteDraftProject(Long aDraftProjectID){
        beanName = "draftProjectManagement";
        return false;
    }

    @Override
    public List<DraftProjectEntity> getFilteredDraftProject(
            DraftProjectEntity anEntity ){
        beanName = "draftProjectSearchManagement";
        return null;
    }

    @Override
    public List<DraftProjectEntity> getFilteredDraftProjectDirectSearch(
            DraftProjectEntity aDraftProjectEntity, List<DependenceEntity> aDependenceEntity){
        beanName = "draftProjectSearchManagement";
        return null;
    }

    @Override
    public Collection<DraftProjectStateEntity> getDraftProjectState(){
        beanName = "draftProjectStateManagement";
        return null;
    }

    @Override
    public Collection<DraftProjectStatusEntity> getDraftProjectStatus(){
        beanName = "draftProjectStatusManagement";
        return null;
    }

    @Override
    public DraftProjectStatusEntity getStatusByConsecutive(int aConsecutive){
        beanName = "draftProjectStatusManagement";
        return null;
    }

    @Override
    public Collection<DraftProjectStatusEntity> getStatus(
            DraftProjectStatusSearchDto
            aDraftProjectStatusSearchDto){
        beanName = "draftProjectStatusManagement";
        return null;
    }

    @Override
    public DraftProjectStatusEntity getIniStatus(){
        beanName = "draftProjectStatusManagement";
        return null;
    }

    @Override
    public DraftProjectStatusEntity getEndStatus(){
        beanName = "draftProjectStatusManagement";
        return null;
    }

    @Override
    public Collection<DraftProjectTypeEntity> getDraftProjectTypes(){
        beanName = "draftProjectTypeManagement";
        return null;
    }

    @Override
    public List<ObjectExpenseEntity> getAllObjectExpenses(){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public List<DestinationEntity> getAllDestinationByObjExp( Long id){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public List<InputEntity> getAllInputByPreFile(Long id){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public List<Long>  saveInputEntitys(Collection<InputEntity> inputEntitys){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public CeillingBudgetEntity getTechoByCvePresupuestal(
            String cvePresupuestal){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public BigDecimal getAmountAssigned(DestinationEntity destinationEntity){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public BudgetKeyEntity getBudgetKeyByBudgetKey(
            BudgetKeyEntity budgetKeyEntity){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public List<FinancingSourceEntity> getAllFinancingSource(){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public BigDecimal getSumaryTechoCveFuente(BudgetKeyEntity budgetKeyEntity){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public void SaveBudgetKey(BudgetKeyEntity budgetKeyEntity){
        beanName = "financialStructureManagment";
    }

    @Override
    public void SaveInvPreFileDetailDao(
            InvPreFileDetailEntity invPreFileDetailEntity){
        beanName = "financialStructureManagment";
    }

    @Override
    public void SaveBudgetKeyAdditional(
            BudgetKeyAdditionalEntity budgetKeyAdditionalEntity){
        beanName = "financialStructureManagment";
    }

    @Override
    public List<InitBudgetKeyPreFileDto> getInitBudgetKeyPreFile(
            InvPreFileEntity invPreFileEntity){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public List<InitBudgetKeyPreFileDto> getInitBudgetKeyAdditional(
            InvPreFileEntity invPreFileEntity){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public void deletebudgetKey(BudgetKeyEntity budgetKeyEntity){
        beanName = "financialStructureManagment";
    }

    @Override
    public void updatebudgetKeyMonthly(BudgetKeyEntity budgetKeyEntity){
        beanName = "financialStructureManagment";
    }

    @Override
    public List<ObjectExpenseEntity> getBudgetingFramming(
            DependenceEntity father, Long idInvPrefile){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public List<DepencenceDto> getDependciesIsRespUnitByDependIdRelated(
            Long idDependency){
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public Long getSummatoryTotalOfCeilingsInvestObjects(
            int myYear,  String chargeKey) {
        beanName = "financialStructureManagment";
        return null;
    }

    @Override
    public Collection<GeneralConceptEntity> getGeneralConcepts(){
        beanName = "generalConceptManagement";
        return null;
    }

    @Override
    public List<InstitutionalPlanEntity> getInstitutionalPlanByDependenceId(
            Long aDependenceID){
        beanName = "instPlanProgManagement";
        return null;
    }

    @Override
    public Collection<InvestmentLineEntity> getInvestmentLines(){
        beanName = "investmentLineManagement";
        return null;
    }

    @Override
    public InvestmentLineEntity getDefaultInvestmentLine(){
        beanName = "investmentLineManagement";
        return null;
    }

    @Override
    public DependenciesInvPreFileDto getDepenInvPreFile(
            DraftProjectEntity  draftProject){
        beanName = "invPreFileManagement";
        return null;
    }

    @Override
    public InvPreFileEntity saveGeneralDataInvPreFile(InvPreFileEntity
            invPreFileEntity){
        beanName = "invPreFileManagement";
        return null;
    }

    @Override
    public void deleteInvPreFile(InvPreFileEntity invPreFileEntity){
        beanName = "invPreFileManagement";
    }

    @Override
    public DependenciesInvPreFileDto getInvPreFileEdit(
            InvPreFileEntity invPreFileEntity){
        beanName = "invPreFileManagement";
        return null;
    }

    @Override
    public List<InvPreFileDto> getFilteredInvPrefileDTO(
            InvPreFileEntity InvPreFileEntityWithParams){
        beanName = "invPreFileManagement";
        return null;
    }

    @Override
    public InvPreFileEntity getInvPreFileById(Long anInvPrefileId){
        beanName = "invPreFileManagement";
        return null;
    }

    @Override
    public void savePriority(InvPreFileEntity invPreFileEntity){
        beanName = "invPreFileManagement";
    }

    @Override
    public FederalURRegulatoryEntity getDefaultFederalURN(){
        beanName = "invPreFileManagement";
        return null;
    }

    @Override
    public Collection<DocumentTypeEntity> getAllDocumentTypes(){
        beanName = "logicFrameManagement";
        return null;
    }

    @Override
    public Collection<LogicFrameEntity> getLogicFrameByDraftProjectID(
            Long aDraftProjectId){
        beanName = "logicFrameManagement";
        return null;
    }

    @Override
    public Long saveLogicFrameFileData(LogicFrameEntity aLogicFrameEntity){
        beanName = "logicFrameManagement";
        return null;
    }

    @Override
    public void deleteLogicFrameFileData(LogicFrameEntity aLogicFrameId){
        beanName = "logicFrameManagement";
    }

    @Override
    public Long updateLogicFrameFileData(LogicFrameEntity aLogicFrameEntity){
        beanName = "logicFrameManagement";
        return null;
    }

    @Override
    public Collection<MacroBuildEntity> getMacroBuilds(){
        beanName = "macroBuildManagement";
        return null;
    }

    @Override
    public MacroBuildEntity getDefaultMacroBuild(){
        beanName = "macroBuildManagement";
        return null;
    }

    @Override
    public List<ObjectiveEntity> getAllObjByDependId(Long aDependenceId){
        beanName = "objectiveInstitutionalPlanFramingManagement";
        return null;
    }

    @Override
    public List<ObjectiveEntity> getAllObjectivesByDependenceId(
            Long aDependenceId){
        beanName = "objectiveProgrammingManagement";
        return null;
    }

    @Override
    public List<ObservationDto> getObservationsByInvPreFileId(Long Id){
        beanName = "observationManagement";
        return null;
    }

    @Override
    public DepencenceDto getDependenceByUserId(Long Id){
        beanName = "observationManagement";
        return null;
    }

    @Override
    public ObservationEntity saveObservation(ObservationEntity myEntity){
        beanName = "observationManagement";
        return null;
    }

    @Override
    public void deleteObservation(ObservationEntity myEntity){
        beanName = "observationManagement";
    }

    @Override
    public PEDVinculationEntity getPEDVinculationById(Long anDraftProjectId){
        beanName = "pedVinculationManagement";
        return null;
    }

    @Override
    public Long savePEDVinculation(PEDVinculationEntity aPEDVinculation){
        beanName = "pedVinculationManagement";
        return null;
    }

    @Override
    public Long saveRegionalPlanAndDraftProject(
            RegionalPlansOfPEDEntity anEntity){
        beanName = "pedVinculationManagement";
        return null;
    }

    @Override
    public List<RegionalPlansOfPEDEntity> getRegionalPlanByDraftProjectId(
            Long aDraftProjectId){
        beanName = "pedVinculationManagement";
        return null;
    }

    @Override
    public String deleteAllRegionalPlansByDraftProjectIDAndRegionClassifierId(
            Long aDraftProjectId,Long aRegionalClassifier){
        beanName = "pedVinculationManagement";
        return null;
    }

    @Override
    public PhysicalAndFinancialProgramDto getPhysicalAndFinancialProgrambyPrefileId(
            Long aPreFileId){
        beanName = "physicalAndFinancialProgramManagement";
        return null;
    }

    @Override
    public String savePhysicalAndFinancialProgram(
            PhysicalAndFinancialProgramDto aPhysicalAndFinancialDto){
        beanName = "physicalAndFinancialProgramManagement";
        return null;
    }

    @Override
    public List<InputEntity> getAllInputInitialAsig(Long id){
        beanName = "physicalAndFinancialProgramManagement";
        return null;
    }

    @Override
    public BenefAndGoalsEntity getBenefAndGoalsByInvPreFile(Long invPreFileId){
        beanName = "physicalAndFinancialProgramManagement";
        return null;
    }

    @Override
    public void saveFinancialAdvancedProg(
            List<MensualBudgetKeyEntity> mensualBudgetKeyList){
        beanName = "physicalAndFinancialProgramManagement";
    }

    @Override
    public List<BudgetKeyEntity> getFinancialAdvProg(Long invPreFileId){
        beanName = "physicalAndFinancialProgramManagement";
        return null;
    }

    @Override
    public BudgetKeyEntity getBudgetKeyById(Long budgetKeyId){
        beanName = "physicalAndFinancialProgramManagement";
        return null;
    }

    @Override
    public Collection<FederalDependenceEntity> getListOfFederalDependences(){
        beanName = "preInvRequestManagement";
        return null;
    }

    @Override
    public Collection<PreInvRequestEntity> getPreInvRequestByPreFileId(
            Long aPreFileId){
        beanName = "preInvRequestManagement";
        return null;
    }

    @Override
    public InvPreFileEntity getInvPreFileById(long invPreFileId){
        beanName = "preInvRequestManagement";
        return null;
    }

    @Override
    public Long savePreInvRequest(PreInvRequestEntity aPreInvRequestEntity){
        beanName = "preInvRequestManagement";
        return null;
    }

    @Override
    public boolean deleteUploadedFile(
            RequestUploadFilesEntity anEntity, Long idReq){
        beanName = "preInvRequestManagement";
        return false;
    }

    @Override
    public Collection<ProgrammingEntity> getProgrammingByObjectiveId(
            Long anIdentity){
        beanName = "programmingManagement";
        return null;
    }

    @Override
    public Collection<ProgrammingEntity> getProgramminByDependenceAndObjective(
            Long aDependencyId,Long aObjectiveId){
        beanName = "programmingManagement";
        return null;
    }

    @Override
    public Long savePromoter(PromoterEntity anEntity){
        beanName = "promoterManagement";
        return null;
    }

    @Override
    public List<RequestUploadFilesEntity> getUploadedFilesByReqId(Long id){
        beanName = "requestUploadFilesManagement";
        return null;
    }

    @Override
    public RequestUploadFilesEntity saveUploadedFile(
            RequestUploadFilesEntity anEntity){
        beanName = "requestUploadFilesManagement";
        return null;
    }

    @Override
    public RequestUploadFilesEntity getUploadedFileByPath(String aPath){
        beanName = "requestUploadFilesManagement";
        return null;
    }

    @Override
    public List<Long> saveSefinValidation(
            List<SefinValidationEntity> aSefinValidationEntity){
        beanName = "sefinValidationManagement";
        return null;
    }

    @Override
    public List<SefinValidationEntity> getSefinValidationByComponent(
            ComponentEntity aComponentEntity){
        beanName = "sefinValidationManagement";
        return null;
    }

    @Override
    public List<SignaturesEntity> getSignaturesByDraftProjectId(
            DraftProjectEntity aDraftPtojectId){
        beanName = "signaturesManagement";
        return null;
    }

    @Override
    public List<String> saveOrUpdateSignatures(
            List<SignaturesEntity> aSignaturesEntityList){
        beanName = "signaturesManagement";
        return null;
    }

    @Override
    public String getSignantByDependenceId(DependenceEntity aDependenceEntity){
        beanName = "signaturesManagement";
        return null;
    }

    @Override
    public SignatureTypeEntity getSignaturesTypeByType(String aType){
        beanName = "signaturesTypeManagement";
        return null;
    }

    @Override
    public int getTheTotalAmounts(Long aPreFileId){
        beanName = "sizingManagement";
        return 0;
    }

    @Override
    public int getTheTotalAditionals(Long aPreFileId){
        beanName = "sizingManagement";
        return 0;
    }

    @Override
    public Collection<SizingEntity> getListOfSizingByPreFileId(Long aPrefileId){
        beanName = "sizingManagement";
        return null;
    }

    @Override
    public Long saveSizing(SizingEntity aSizingEntity){
        beanName = "sizingManagement";
        return null;
    }

    @Override
    public String deleteSizing(Long aSizingEntityId){
        beanName = "sizingManagement";
        return null;
    }

    @Override
    public List<RegionalLevelClassifierEntity> getUbicationScope(){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public List<RegionalClassifierEntity> getUbicationsByScope(
            RegionalLevelClassifierEntity regionLevelentity){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public RegionalClassifierEntity getRegionalClassiById(
            RegionalClassifierEntity entityToFind){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public List<RegionalClassifierDTO> findUbication(
            RegionalClassifierEntity ubication){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public Map<Long, List<RegionalClassifierDTO>> saveInvPreFileReg(
            List<String> ubicationSelected, InvPreFileEntity invPreFile){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public List<Long> getUbicationsSelected(
            InvPreFileEntity invPreFile){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public List<RegionalClassifierDTO> findUbication(
            RegionalClassifierEntity ubication,
            InvPreFileEntity invPreFile){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public List<Long> getDraftProjectUbications(
            DraftProjectEntity draftProEntity){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public Map<Long, List<RegionalClassifierDTO>> saveDraftProjectUbication(
            List<String> itsSelectedUbications, Long currentId){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public List<RegionalClassifierDTO> findDraftProjectUbication(
            RegionalClassifierEntity ubication,
            DraftProjectEntity entity){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public Long findFather(Long idRegion){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public String findFatherDesc(Long idRegion){
        beanName = "ubicationManagement";
        return null;
    }

    @Override
    public Collection<UnitMeasureBenefAndGoalEntity> getUnitMeasureBenefAndGoals(){
        beanName = "unitMeasureBenefAndGoalManagement";
        return null;
    }

    @Override
    public Collection<UnitMeasureGoalEntity> getUnitMeasureGoals(){
        beanName = "unitMeasureGoalManagement";
        return null;
    }

    @Override
    public Collection<ValidationEntity> getValidationByComponentId(
            ComponentEntity aComponentEntity){
        beanName = "validationManagement";
        return null;
    }

    @Override
    public Collection<ComponentEntity> getComponentsByDraftProjectId(
            Long aDraftProjectId){
        beanName = "validationManagement";
        return null;
    }

    @Override
    public DeliveryEntity getDeliveryByDraftProject(
            DraftProjectEntity aDraftProjectId){
        beanName = "validationManagement";
        return null;
    }

    @Override
    public Collection<ComponentEntity> getComponentsByDelivery(
            DeliveryEntity aDelivery){
        beanName = "validationManagement";
        return null;
    }

    @Override
    public List<Long> saveSeplanValidation(
            List<ValidationEntity> aValidationEntityList){
        beanName = "validationManagement";
        return null;
    }

    @Override
    public void exitValidationSEPLAN(Long aDraftProjectId){
        beanName = "validationManagement";
    }

    @Override
    public Collection<VulnerableGroupEntity> getVulnerableGroups(){
        beanName = "vulnerableGroupManagement";
        return null;
    }

    @Override
    public void executeChangeStatus(DraftProjectEntity aDraftProject,
            DraftProjectStatusEntity aNextStatus){
        beanName = "workFlowDraftProjectManagement";
    }

    @Override
    public void executeChangeStatus(
            Collection<DraftProjectEntity> aDraftProjects,
            DraftProjectStatusEntity aNextStatus){
        beanName = "workFlowDraftProjectManagement";
    }

    @Override
    public HashMap<String, List<?>> getSupportList(){
        beanName = "workFlowDraftProjectManagement";
        return null;
    }

    @Override
    public Collection<DraftProjectStatusEntity> getPossibleStatus(
            DraftProjectStatusSearchDto
            aDraftProjectStatusSearchDto){
        beanName = "workFlowDraftProjectManagement";
        return null;
    }

    @Override
    public DraftProjectStatusEntity getInitialStatus( ){
        beanName = "workFlowDraftProjectManagement";
        return null;
    }

    @Override
    public DraftProjectStatusEntity getFinalStatus(){
        beanName = "workFlowDraftProjectManagement";
        return null;
    }

    @Override
    public Collection<DraftProjectEntity> getDraftProjectsInWorkFlow(
            DraftProjectSearchDto
            aDraftProjectSearchDto){
        beanName = "workFlowDraftProjectManagement";
        return null;
    }

    @Override
    public Collection<DraftProjectBinnacleEntity> getBinnaclebyDraftProjectId(
            Long aDraftProjectID){
        beanName = "workFlowDraftProjectManagement";
        return null;
    }

    @Override
    public Collection<DraftProjectEntity> getDraftProjectsByFilter(
            DraftProjectSearchDto aDraftProjectSearchDto){
        beanName = "workFlowDraftProjectManagement";
        return null;
    }
}
