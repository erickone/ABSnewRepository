/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.BenefAndGoalsDao;
import com.abs.siif.programming.entities.BenefAndGoalsEntity;
import com.abs.siif.programming.entities.UnitMeasureBenefAndGoalEntity;
import com.abs.siif.programming.entities.VulnerableGroupEntity;
import com.abs.siif.programming.entities.GeneralConceptEntity;
import com.abs.siif.programming.entities.InvestmentLineEntity;
import com.abs.siif.programming.entities.MacroBuildEntity;
import com.abs.siif.programming.entities.UnitMeasureGoalEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar Implementa los metodos de negocio para
 * las metas beneficios
 *
 */
@Service("benefAndGoalsManagement")
public class BenefAndGoalsManagementImpl implements BenefAndGoalsManagement {

    @Resource(name = "unitMeasureGoalManagement")
    private UnitMeasureGoalManagement itsUnitMeasureGoalManagement;
    @Resource(name = "unitMeasureBenefAndGoalManagement")
    private UnitMeasureBenefAndGoalManagement itsUnitMeasureBenefAndGoalManagement;
    @Resource(name = "vulnerableGroupManagement")
    private VulnerableGroupManagement itsVulnerableGroupManagement;
    @Resource(name = "generalConceptManagement")
    private GeneralConceptManagement itsGeneralConceptManagement;
    @Resource(name = "investmentLineManagement")
    private InvestmentLineManagement itsInvestmentLineManagement;
    @Resource(name = "macroBuildManagement")
    private MacroBuildManagement itsMacroBuildManagement;
    @Resource(name = "benefAndGoalDao")
    private BenefAndGoalsDao itsBenefAndGoalsDao;

    @Override
    public Map<String, List<?>> getSupportBenGoalList() {
        Map<String, List<?>> mySupportList = new HashMap<String, List<?>>();
        Collection<UnitMeasureGoalEntity> myUnitGoals = itsUnitMeasureGoalManagement.getUnitMeasureGoals();
        Collection<UnitMeasureBenefAndGoalEntity> myUnitBenefAndGoals = itsUnitMeasureBenefAndGoalManagement.getUnitMeasureBenefAndGoals();
        Collection<VulnerableGroupEntity> myVulnerableGroups = itsVulnerableGroupManagement.getVulnerableGroups();
        Collection<GeneralConceptEntity> myGeneralConcepts = itsGeneralConceptManagement.getGeneralConcepts();
        Collection<InvestmentLineEntity> myLines = itsInvestmentLineManagement.getInvestmentLines();
        Collection<MacroBuildEntity> myMacros = itsMacroBuildManagement.getMacroBuilds();
        InvestmentLineEntity myDefaultLine = new InvestmentLineEntity();

        myLines.remove(itsInvestmentLineManagement.getDefaultInvestmentLine());
        myMacros.remove(itsMacroBuildManagement.getDefaultMacroBuild());
                
        mySupportList.put("Goals", new ArrayList<UnitMeasureGoalEntity>(myUnitGoals));
        mySupportList.put("BenefAndGoals", new ArrayList<UnitMeasureBenefAndGoalEntity>(myUnitBenefAndGoals));
        mySupportList.put("VulnerableGroups", new ArrayList<VulnerableGroupEntity>(myVulnerableGroups));
        mySupportList.put("GeneralConcepts", new ArrayList<GeneralConceptEntity>(myGeneralConcepts));
        mySupportList.put("Lines", new ArrayList<InvestmentLineEntity>(myLines));
        mySupportList.put("Macros", new ArrayList<MacroBuildEntity>(myMacros));

        return mySupportList;
    }

    @Override
    public Long persist(BenefAndGoalsEntity myBenefAndGoal) {
        Long myIdentity;

        if (myBenefAndGoal.getInvestmentLineBenefAndGoals().getInvestLineId() == -1) {
            myBenefAndGoal.setInvestmentLineBenefAndGoals(itsInvestmentLineManagement.getDefaultInvestmentLine());
        }
        if (myBenefAndGoal.getMacroBuildBenefAndGoals().getMacroBuildId() == -1) {
            myBenefAndGoal.setMacroBuildBenefAndGoals(itsMacroBuildManagement.getDefaultMacroBuild());
        }

        if (myBenefAndGoal.getBenefAndGoalId() == null) {
            myIdentity = itsBenefAndGoalsDao.save(myBenefAndGoal).getBenefAndGoalId();
        } else {
            myIdentity = itsBenefAndGoalsDao.persist(myBenefAndGoal).getBenefAndGoalId();
        }

        return myIdentity;
    }

    @Override
    public BenefAndGoalsEntity getBenefGoalsByInvPreFile(Long invPreFileId) {
        return itsBenefAndGoalsDao.getBenefAndGoalsByInvPreFile(invPreFileId);
    }
}
