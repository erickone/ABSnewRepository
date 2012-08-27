/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.budget.comparators.ObjectExpenseKeyComparator;
import com.abs.siif.budget.dao.*;
import com.abs.siif.budget.dto.BudgetDispURDto;
import com.abs.siif.budget.dto.BudgetKeyBreakDownDto;
import com.abs.siif.budget.dto.BudgetingSummaryDto;
import com.abs.siif.budget.dto.DestinationDto;
import com.abs.siif.budget.entities.*;
import com.abs.siif.budget.management.BudgetDetailsKeyManagement;
import com.abs.siif.budget.management.BudgetKeyAdditionalManagement;
import com.abs.siif.budget.management.CeilingBudgetManagement;
import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.ProgrammingEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Erick Leija
 */
@Service("budgetingManagement")
public class BudgetingManagementImpl implements BudgetingManagement, Serializable {

    @Resource(name = "destinationDao")
    DestinationDao theirDestinationDao;
    @Resource(name = "financingSourceDao")
    FinancingSourceDao theirFinancingSourceDao;
    @Resource(name = "budgetKeyDao")
    BudgetKeyDao theirBudgetKeyDao;
    @Resource(name = "objectExpenseDao")
    ObjectExpenseDao theirObjectExpenseDao;
    @Resource(name = "budgetKeyAndDraftProjectFramingDao")
    BudgetKeyAndDraftProjectFramingDao theirBudgetKeyAndDraftProjectFramingDao;
    @Resource(name = "budgetKeyAdditionalManagement")
    BudgetKeyAdditionalManagement theirBudgetKeyAdditionalManagement;
    @Resource(name = "destinyObjectExpenseRUBUDao")
    DestinyObjectExpenseRUBUDao theirDestinyObjectExpenseRUBUDao;
    @Resource(name = "DependenceDao")
    DependenceDao theirDependenceDao;
    @Resource(name = "budgetDetailsKeyManagement")
    private BudgetDetailsKeyManagement itsbudgetDetailsKeyManagement;
    @Resource(name = "ceillingBudgetManagement")
    CeilingBudgetManagement ceillingBudgetManagement;
    @Resource(name = "budgetingManagement")
    private transient BudgetingManagement theirBudgetingManagement;
    @Resource(name = "draftProjectManagement")
    private transient DraftProjectManagement theirDraftProjectManagement;

    @Override
    public Collection<FinancingSourceEntity> getFinancingSourceByObjectExpense(Long anObjectExpenseId) {
        return theirFinancingSourceDao.getFinancingSourceByObjectExpense(anObjectExpenseId);
    }

    @Override
    public Collection<DestinationEntity> getDestinyByObjectExpense(Long anObjectExpenseId) {
        return theirDestinationDao.getDestinyByObjectExpense(anObjectExpenseId);
    }

    @Override
    public FinancingSourceEntity getFinancingSourceById(FinancingSourceEntity aFinancingSource) {
        return theirFinancingSourceDao.findById(aFinancingSource.getFinancingSourceId(), true);
    }

    @Transactional(readOnly = false)
    @Override
    public Long saveBudgetKey(BudgetKeyEntity aBudgetEntity, BudgetDispURDto dto, Boolean ficha) {
        Long urBudget = -1L;

        if (ficha) {
            if (dto != null && !dto.isAdditionalAmount()) {
                urBudget = ceillingBudgetManagement.getBudgetForUR(dto.getDependenceEntity(), dto.getObjectExpenseEntity(), dto.getDesting(), dto.getFinancingSourceEntity());

                if (aBudgetEntity.getOriginalAmount() > urBudget) {
                    throw new BaseBusinessException("error.budgetamout");
                }
            }
        } else {
            //TODO:PRe-ficha
        }
        Long myBudgetId = theirBudgetKeyDao.saveBudgetKey(aBudgetEntity);

        for (BudgetDetailsKeyEntity myDetail : aBudgetEntity.getBudgetDetailKeys()) {
            BudgetKeyEntity myBudget = new BudgetKeyEntity();
            myBudget.setBudgetKeyId(myBudgetId);
            myDetail.setBudgetKey(myBudget);
        }

        itsbudgetDetailsKeyManagement.persistBudgetDetailsKey(aBudgetEntity.getBudgetDetailKeys());
        if (!dto.isAdditionalAmount()) {
            theirBudgetKeyDao.insertInBudgetKeyDetail(aBudgetEntity);
        }

        DraftProjectEntity myDraftProject =
                theirDraftProjectManagement.getDraftProjectByIdWithBudgets(dto.getIdDraftProject());

        BudgetKeyAndDraftProjectFramingEntity myEntityToFraming = new BudgetKeyAndDraftProjectFramingEntity();
        myEntityToFraming.setBudgetKeyIdBudgetKeyAndDraftPro(aBudgetEntity);
        myEntityToFraming.setDraftProjectIdBudgetKeyAndDraftPro(myDraftProject);

        myEntityToFraming.setBudgetKeyAndDraftProId(
                theirBudgetingManagement.saveBudgetAndProjectFraming(myEntityToFraming));

        myDraftProject.getDraftProjectBudgetDetail().add(myEntityToFraming);

        ProgrammingEntity myProgram = myDraftProject.getDraftProjectProgramming();

        theirDraftProjectManagement.saveDraftProject(myDraftProject,
                myProgram.getProgrammingObjective().getObjectiveId());

        return myBudgetId;
    }

    @Transactional(readOnly = false)
    @Override
    public Long saveBudgetAndProjectFraming(BudgetKeyAndDraftProjectFramingEntity aBudgetEntity) {
        return theirBudgetKeyAndDraftProjectFramingDao.saveBudgetKeyAndDraftProjectFraming(aBudgetEntity);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteABudgetAndProjectFraming(BudgetKeyAndDraftProjectFramingEntity aBudgerFramingEntity) {
        theirBudgetKeyAndDraftProjectFramingDao.deleteBudgetKeyAndDraftProjectFraming(aBudgerFramingEntity);

    }

    @Transactional(readOnly = false)
    @Override
    public BudgetKeyAndDraftProjectFramingEntity getABudgetAndProjectFramingByIds(Long aProjectId, Long aBudgetKeyId) {
        return theirBudgetKeyAndDraftProjectFramingDao.getBudgetKeyAndDraftProjectFramingByProjectIDAndBudgetId(aProjectId, aBudgetKeyId);
    }

    @Override
    public Long getCeilingAvailableByDependenceId(Long aDependenceId) {
        return theirBudgetKeyAndDraftProjectFramingDao.getCeilingAvailableByDependenceId(aDependenceId);
    }

    @Override
    public ObjectExpenseEntity getObjectExpenseById(Long anObjectExpenseId) {
        return theirObjectExpenseDao.findById(anObjectExpenseId, true);
    }

    @Transactional(readOnly = false)
    @Override
    public void persistBudgetKey(BudgetKeyEntity aBudgetEntity) {
        theirBudgetKeyDao.persistBudgetKey(aBudgetEntity);
        theirBudgetKeyDao.updateInBudgetKeyDetail(aBudgetEntity);
    }

    @Override
    public Collection<BudgetKeyEntity> getTheBudgetingKeysWithProgramaticKey(String aProgrammaticKey) {
        return theirBudgetKeyDao.getBudgetKeysWithProgrammaticKey(aProgrammaticKey);
    }

    @Override
    public void deleteBudgetKey(Long aBudgetKeyEntity) {
        BudgetKeyEntity myEntity = new BudgetKeyEntity();
        myEntity.setBudgetKeyId(aBudgetKeyEntity);
        itsbudgetDetailsKeyManagement.deleteBudgetDetailsKey(myEntity);
        theirBudgetKeyDao.deleteBudgetKey(aBudgetKeyEntity);
    }

    @Override
    public BudgetKeyEntity getBudgetEntityById(Long aBudgetEntiityId) {

        return theirBudgetKeyDao.getBudgetKeyById(aBudgetEntiityId);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateABudgetKeyEntity(BudgetKeyEntity myBudgetKey, BudgetDispURDto dto) {

        BudgetKeyEntity tempEnmtity =
                theirBudgetKeyDao.getBudgetKeyById(myBudgetKey.getBudgetKeyId());
        //Verifica si el escenario es colocar el monto original
        if (tempEnmtity.getOriginalAmount() == 0
                && myBudgetKey.getOriginalAmount() != 0) {

            long urBudget = ceillingBudgetManagement.getBudgetForUR(dto.getDependenceEntity(), dto.getObjectExpenseEntity(), dto.getDesting(), dto.getFinancingSourceEntity());

            if (myBudgetKey.getOriginalAmount() > urBudget) {
                throw new BaseBusinessException("error.budgetamout");
            }

            theirBudgetKeyDao.insertInBudgetKeyDetail(myBudgetKey);

        }

        theirBudgetKeyDao.updateBudgetKey(myBudgetKey);

    }

    @Override
    public DestinationEntity getDestinationByKey(String aKey) {
        return theirDestinationDao.getDestinationByKey(aKey);
    }

    @Override
    public List<ObjectExpenseEntity> getTheBudgetingFramming(DependenceEntity aDependence, BudgetingSummaryDto aBudgetingDto, boolean investFlag) {
        List<DepencenceDto> myUREntity =
                theirDependenceDao.getDependciesIsRespUnitByDependIdRelated(aDependence.getDependenceId());
        if (myUREntity == null) {
            return null;
        } else {
            if (myUREntity.size() > 0) {
                Set<ObjectExpenseEntity> myObjectExpense = new HashSet<ObjectExpenseEntity>();

                for (DestinyObjectExpenseRUBUEntity myFramming :
                        theirDestinyObjectExpenseRUBUDao.getFrammingByRUkey(myUREntity.get(myUREntity.size() - 1), aBudgetingDto, investFlag)) {
                    myObjectExpense.add(myFramming.getDestinyObjectEpenseRUBUObject());
                }
                List<ObjectExpenseEntity> myOrdererList = new ArrayList<ObjectExpenseEntity>(myObjectExpense);
                Collections.sort(myOrdererList, ObjectExpenseKeyComparator.getInstance());
                return myOrdererList;
            } else {
                return null;
            }
        }
    }

    @Override
    public DepencenceDto getDependenciesRespUnitBytDependIdRelated(Long dependenceId) {
        DepencenceDto dependenceUr = null;
        List<DepencenceDto> myUREntity =
                theirDependenceDao.getDependciesIsRespUnitByDependIdRelated(dependenceId);
        if (myUREntity != null) {
            if (myUREntity.size() > 0) {
                dependenceUr = myUREntity.get(0);
            }
        }
        return dependenceUr;
    }

    @Override
    public List<DestinyObjectExpenseRUBUEntity> getTheBudgetingDestinationByObject(DependenceEntity aDependence, ObjectExpenseEntity aBudgetingDto) {
        List<DepencenceDto> myUREntity =
                theirDependenceDao.getDependciesIsRespUnitByDependIdRelated(aDependence.getDependenceId());
        List<ObjectExpenseEntity> myObject =
                theirObjectExpenseDao.getObjectExpenseBySpecificParByObjectIdRelated(aBudgetingDto.getObjectExpenseId());
        if (myUREntity == null) {
            return null;
        } else {
            if (myUREntity.size() > 0 && myObject.size() > 0) {

                List<DestinyObjectExpenseRUBUEntity> myOrdererList = new ArrayList<DestinyObjectExpenseRUBUEntity>(theirDestinyObjectExpenseRUBUDao.getDestinationByObject(myUREntity.get(myUREntity.size() - 1), myObject.get(myObject.size() - 1)));
                return myOrdererList;
            } else {
                return null;
            }
        }

    }

    @Override
    public List<DestinyObjectExpenseRUBUEntity> getTheBudgetingDestinationByObjectInv(DependenceEntity aDependence, ObjectExpenseEntity aBudgetingDto) {
        List<DepencenceDto> myUREntity =
                theirDependenceDao.getDependciesIsRespUnitByDependIdRelated(aDependence.getDependenceId());
        List<ObjectExpenseEntity> myObject =
                theirObjectExpenseDao.getObjectExpenseBySpecificParByObjectIdRelated(aBudgetingDto.getObjectExpenseId());
        if (myUREntity == null) {
            return null;
        } else {
            if (myUREntity.size() > 0 && myObject.size() > 0) {

                List<DestinyObjectExpenseRUBUEntity> myOrdererList = new ArrayList<DestinyObjectExpenseRUBUEntity>(theirDestinyObjectExpenseRUBUDao.getDestinationByObjectInv(myUREntity.get(myUREntity.size() - 1), myObject.get(myObject.size() - 1)));
                return myOrdererList;
            } else {
                return null;
            }
        }

    }

    @Override
    public BudgetKeyBreakDownDto getBudgetDesgloce(Long lCvePptalId) {
        return theirBudgetKeyDao.getBudgetDesgloce(lCvePptalId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean modifyCeilingBudget(BudgetDispURDto dto, BudgetKeyBreakDownDto objectDto) {
        boolean updateCeilingBudget = false;
        boolean hasUpdate = true;


        Long montoDisponibleTechoUr = ceillingBudgetManagement.getBudgetForUR(dto.getDependenceEntity(), dto.getObjectExpenseEntity(), dto.getDesting(), dto.getFinancingSourceEntity());
        Long montoTecho = dto.getCeilingBudgetObjectExpense();
        Long MontoTechoUsed = dto.getCeilingBudgetObjectExpenseUsed();
        Long different = 0L;
        Long differentMontoTecho = 0L;

        List<BudgetKeyBreakDownDto> lstObjectToModify = theirBudgetKeyDao.getBudgetKeyWithBreakDown(dto.getItsUrDependence().getClave(),
                String.valueOf(dto.getObjectExpenseEntity().getObjectExpenseKey()));
        
        Long montoAsigBasico = 0L;
        Long montoNoAsigBasico = 0L;
        Long montoAsignadoTotal = 0L;
        
        for (BudgetKeyBreakDownDto elementDto : lstObjectToModify) {
            montoAsigBasico += elementDto.getBasico().longValue();
            montoNoAsigBasico+= elementDto.getNobasico().longValue();
            montoAsignadoTotal+=elementDto.getMonto().longValue();  
        }
        boolean basic = montoAsigBasico.intValue() >0 ? true: false;
        Long montoDispoBasico = montoTecho;
        if(montoAsigBasico.longValue() > 0){
            montoDispoBasico = montoTecho.longValue() - montoAsigBasico.longValue();
        }else{
            montoDispoBasico = 0L;
        }
        
        for (BudgetKeyBreakDownDto elementDto : lstObjectToModify) {

            if (elementDto.getIdcvepptaldesglose() == objectDto.getIdcvepptaldesglose()) {
                different = objectDto.getMonto().longValue() - elementDto.getMonto().longValue();

                if (different == 0) {
                    hasUpdate = false;
                    updateCeilingBudget = true;
                    break;
                } else {
                        // EL monto es menor (Se dismunuye y se libera el techo), 
                        Long nobasica = elementDto.getNobasico().longValue();
                        Long basica = elementDto.getBasico().longValue();
                        Long amountChanged = elementDto.getMonto().longValue() - objectDto.getMonto().longValue();
                        elementDto.setMonto(BigDecimal.valueOf(objectDto.getMonto().longValue()));
                        Long dipTecho = montoTecho - MontoTechoUsed;
                        if(montoDispoBasico.longValue()>0 ){
                           dipTecho = montoDispoBasico;
                        }
                        
                        //Cuando la el monto se esta reducciendo
                        if (different < 0 && basica > 0) {
                            if (nobasica == 0) {
                                elementDto.setBasico(BigDecimal.valueOf(elementDto.getBasico().longValue() + (different)));
                                if (basica > elementDto.getBasico().longValue()) {
                                    differentMontoTecho = basica - elementDto.getBasico().longValue();
                                }
                            } else {
                                if (nobasica > Math.abs(different)) {
                                    elementDto.setNobasico(BigDecimal.valueOf(elementDto.getNobasico().longValue() + different));
                                } else if (nobasica < Math.abs(different)) {
                                    elementDto.setNobasico(BigDecimal.ZERO);
                                    if(basic){
                                        elementDto.setBasico(BigDecimal.valueOf(elementDto.getBasico().longValue() - (Math.abs(different) - nobasica)));
                                    }
                                   differentMontoTecho = Math.abs(different) - nobasica;
                                }
                            }

                        }
                        if (different < 0 && basica == 0) {
                            elementDto.setNobasico(BigDecimal.valueOf(nobasica + different));

                        } else { //Cuando la el monto se esta aumentando
                            // Verifica techo disponible, si este disponible es menor 
                            if (different > 0 && nobasica > 0) {
                                elementDto.setNobasico(BigDecimal.valueOf(nobasica + different));
                            } else if (different > 0 && nobasica == 0 && dipTecho <= different) {
                                elementDto.setBasico(BigDecimal.valueOf(elementDto.getBasico().longValue() + (dipTecho)));
                                elementDto.setNobasico(BigDecimal.valueOf(objectDto.getMonto().longValue() - elementDto.getBasico().longValue()));

                            } else if (different > 0 && nobasica == 0 && dipTecho > different){
                                 elementDto.setBasico(BigDecimal.valueOf(elementDto.getBasico().longValue() + different));
                                elementDto.setNobasico(BigDecimal.ZERO);
                            }
                        }


                        break;
                    }
            }
        }


        if (differentMontoTecho > 0) {
            for (BudgetKeyBreakDownDto elementDto : lstObjectToModify) {
                if (elementDto.getNobasico().longValue() > 0) {
                    different = elementDto.getNobasico().longValue() - differentMontoTecho;

                    if (different == 0) {
                        elementDto.setNobasico(BigDecimal.ZERO);
                        elementDto.setBasico(BigDecimal.valueOf(elementDto.getBasico().longValue() + different));
                        break;
                    } else {

                        if (different > 0) {
                            elementDto.setNobasico(BigDecimal.valueOf(different));
                            elementDto.setBasico(BigDecimal.valueOf(elementDto.getBasico().longValue() + differentMontoTecho));
                            differentMontoTecho = 0L;
                            break;
                        } else {
                            // Se divide el presupuesto en 1 o mas row
                            different = differentMontoTecho - elementDto.getNobasico().longValue();
                            elementDto.setBasico(BigDecimal.valueOf(elementDto.getBasico().longValue() + differentMontoTecho - different));
                            differentMontoTecho = differentMontoTecho - elementDto.getNobasico().longValue();
                            elementDto.setNobasico(BigDecimal.ZERO);

                        }
                    }

                }
            }
        }
        theirBudgetKeyDao.updateBudgetKey(dto.getMyBudget());
        if (hasUpdate) {
            updateCeilingBudget = this.theirBudgetKeyDao.updateBudgetKeyWithBreakDown(lstObjectToModify);
        }

        return updateCeilingBudget;
    }

    @Override
    public DestinationDto getEncDepObjGasDest(Long anObjectExpenseId, Long aDependenceId, String aDestinyKey) {
        List<DepencenceDto> lstDto =
                this.theirDependenceDao.getDependciesIsRespUnitByDependIdRelated(aDependenceId);

        if (lstDto != null && lstDto.size() > 0) {
            Long myDependenceFatherId = lstDto.get(0).getIdDependency();
            return this.theirDestinationDao.getEncDepObjGasDest(anObjectExpenseId, myDependenceFatherId, aDestinyKey);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBudgetProcess(BudgetDispURDto dto) {
        if (dto.getMyEntityToFraming() != null) {
            deleteABudgetAndProjectFraming(dto.getMyEntityToFraming());
        }
        if (dto.getMyBudget() == null) {
            throw new BaseBusinessException("No hay presupuesto a eliminar!!");
        } else {
            deleteBudgetKey(dto.getMyBudget().getBudgetKeyId());
        }

    }
}
