/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.budget.dao.BudgetingCeilingDao;
import com.abs.siif.budget.dao.ObjectExpenseDao;
import com.abs.siif.budget.dto.BudgetKeyAdditionalDto;
import com.abs.siif.budget.dto.BudgetSummaryDto;
import com.abs.siif.budget.dto.BudgetingDependenceTotalDto;
import com.abs.siif.budget.dto.BudgetingSummaryDto;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.budget.management.BudgetKeyAdditionalManagement;
import com.abs.siif.budget.management.BudgetKeyConfigurationManagement;
import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dao.DeliveryDao;
import com.abs.siif.programming.dao.DraftProjectDao;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.DeliveryEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author Erick Leija
 */
@Service("budgetingSummaryManagement")
public class BudgetingSummaryManagementImpl implements BudgetingSummaryManagement, Serializable
{
    @Resource(name = "budgetingCeilingDao")
    BudgetingCeilingDao theirbudgetingCeilingDao;
    @Resource(name = "draftProjectDaoImpl")
    DraftProjectDao theirDraftProjectDao;
    @Resource(name="deliveryDaoImpl")
    DeliveryDao theirDeliveryDao;
    @Resource(name="DependenceDao")
    DependenceDao theirDependenceDao; 
    @Resource(name="objectExpenseDao")
    ObjectExpenseDao theirobjectExpenseDao;
    @Resource(name="budgetKeyAdditionalManagement")
    BudgetKeyAdditionalManagement theirBudgetKeyAdditionalManagement;
    @Resource(name="budgetKeyConfigurationManagement")
    BudgetKeyConfigurationManagement theirbudgetKeyConfigurationManagement;
    
    public NumberFormat numberValidator = NumberFormat.getInstance(Locale.US);
    
    @Override
    public List<ComponentEntity> getCompByDraftProId(Long DraftProjectId)
    {
        List<ComponentEntity> myComponents = null;
        try
        {
            DraftProjectEntity myDraftProject = theirDraftProjectDao.getDraftProjectById(DraftProjectId);
            List<DeliveryEntity> deliveries = theirDeliveryDao.getDeliveriesByDraftProject(myDraftProject);
            if (deliveries != null && deliveries.size() > 0)
            {
                myComponents = new ArrayList<ComponentEntity>(deliveries.get(0).getComponents());
            }
        } catch (RuntimeException ex)
        {
            throw new BaseBusinessException("Error: " + ex);
        }

        return myComponents;
    }
    
    @Override
    public DependenceEntity getDependenceById(Long aDependenceId)
    {
        return theirDependenceDao.getDependenceById(aDependenceId);
    }

    @Override
    public List<ObjectExpenseEntity> getObjectExpenseRoots()
    {
       return theirbudgetingCeilingDao.getObjectExpenseRoots();
      
    }

    @Override
    public BudgetSummaryDto getAllAmountsOfChapters(String aProgrammaticKey, String aditionalFlag)
    {
        return theirobjectExpenseDao.getAllAmountsOfChapters(aProgrammaticKey,aditionalFlag);
    }
    

    @Override
    public Collection<ObjectExpenseEntity> getTheObjectExpenseHeirachy(Long myObjectExpenseId)
    {
        return theirobjectExpenseDao.getLastLevelObjects(myObjectExpenseId);
    }

    @Override
    public Long getTheLevelofTheSpecificParId()
    {
       return theirbudgetingCeilingDao.getLevelOfSpecificParId();
    }

    @Override
    public Long getAvailableAdditional(BudgetKeyAdditionalDto anAdditionalDto)
    {
       return (long) theirBudgetKeyAdditionalManagement.getSumByBudgetKey(anAdditionalDto);
       
    }
    
    @Override
    public List<DepencenceDto> getTheRelatedUEGs(DependenceEntity aDependence)
    {
         List<DepencenceDto> myUEGentity = new ArrayList<DepencenceDto>();
         List<DepencenceDto> myUREntity =
                theirDependenceDao.getDependciesIsRespUnitByDependIdRelated(aDependence.getDependenceId());
         if (myUREntity.size() > 0)
         {
             myUEGentity =
                      theirDependenceDao.getDependciesIsExecUnitByDependIdRelated(myUREntity.get(0).getIdDependency());
         }
        return myUEGentity;
    }

    @Override
    public List<BudgetingDependenceTotalDto> getMyDependenceSummaryDTO(List<DepencenceDto> myListOfDependences, Long myDrafProjectId)
    {
        List<BudgetingDependenceTotalDto> myListToReturn = new ArrayList<BudgetingDependenceTotalDto>();
        for (DepencenceDto myDependencetoIterate : myListOfDependences)
        {
            Map<String, Long> myMap = new HashMap<String, Long>();
            myMap.put("iddependencia", myDependencetoIterate.getIdDependency());
            myMap.put("idproyecto", myDrafProjectId);
            String itsProgrammaticKey = theirbudgetKeyConfigurationManagement.getBudgetKey(myMap);
            BudgetSummaryDto mySummary = getAllAmountsOfChapters(itsProgrammaticKey, "f"); 
            BudgetingDependenceTotalDto MyDtoToLoad =new 
                    BudgetingDependenceTotalDto(myDependencetoIterate.getIdDependency(), 
                    myDependencetoIterate.getClave(), 
                    myDependencetoIterate.getNameDepend(), 
                    mySummary.getOriginalAmount().longValue(), 
                    mySummary.getAdditionalAmount().longValue(),mySummary.getSum().longValue());
            myListToReturn.add(MyDtoToLoad);
            
        }
        return myListToReturn;
    }

    @Override
    public List<BudgetingSummaryDto> getTheMegaSummaryByUR(List<DepencenceDto> myListOfDependences, Long DrafProjectId, 
            List<ObjectExpenseEntity> myObjectExpenseRoots)
    {
         Map<String, Long> myMap = new HashMap<String, Long>();
        if (myListOfDependences.size()<1)
        {
           return null;
        }
        List<BudgetingSummaryDto> myrealList = new ArrayList<BudgetingSummaryDto>(); 
            for (ObjectExpenseEntity myObjectEntity : myObjectExpenseRoots)
            {
                
                Long myAditionalTotal = 0L;
                Long myInitialTotal = 0L;
                Long mySumTotal = 0L;
                for (DepencenceDto myDependencetoIterate : myListOfDependences)
                {
                    myMap.put("iddependencia", myDependencetoIterate.getIdDependency());
                    myMap.put("idproyecto", DrafProjectId);
                    String itsProgrammaticKey = theirbudgetKeyConfigurationManagement.getBudgetKey(myMap);
                    
                    String myKeyToFind = itsProgrammaticKey
                            + String.valueOf(myObjectEntity.getObjectExpenseKey() / 1000);
                    BudgetSummaryDto mySummary = getAllAmountsOfChapters(myKeyToFind, "f");

                    myAditionalTotal += mySummary.getAdditionalAmount().longValue();
                    myInitialTotal += mySummary.getOriginalAmount().longValue();
                    Long mySum = mySummary.getOriginalAmount().longValue()
                            + mySummary.getAdditionalAmount().longValue();
                    mySumTotal += mySum;
                }
                BudgetingSummaryDto myDTO = new BudgetingSummaryDto(myObjectEntity.
                        getObjectExpenseId(), myObjectEntity.getObjectExpenseDescription(),
                        myObjectEntity.getObjectExpenseKey(),
                        numberValidator.format(myInitialTotal),
                        numberValidator.format(myAditionalTotal),
                        numberValidator.format(mySumTotal));
                myrealList.add(myDTO);
            }
            
        
        
       return myrealList;
    }
}
