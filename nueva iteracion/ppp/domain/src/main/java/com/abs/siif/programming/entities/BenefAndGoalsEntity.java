package com.abs.siif.programming.entities;

import com.abs.siif.budget.data.ExecutionPeriodType;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Israel Ruiz
 */
@Entity
@Table(name = "siifpppMetaBeneficiario")
public class BenefAndGoalsEntity  implements Serializable,
        Comparable<BenefAndGoalsEntity> {

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdMetaBeneficiario")
    private Long benefAndGoalId;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "PeriodoEjecucion")
    private ExecutionPeriodType executionPeriod;
    @Column(name = "MetaTotal")
    private double totalGoal;
    @Column(name = "MetaPorAnio")
    private double goalByYear;
    @Column(name = "BenefDirectos", length = 500, nullable = false)
    private double benefDirects;
    @Column(name = "BenefIndirectos", length = 500, nullable = false)
    private double benefIndirects;
   
    // Identificador de la Unidad de Medida (Meta)
    @ManyToOne
     
    @JoinColumn(name = "IdUniMedidaMeta", nullable = false)
    private UnitMeasureGoalEntity unitMeasureGoal;
    // Identificador de la Unidad de Medida (Benef)
    @ManyToOne
    
    @JoinColumn(name = "IdUniMedidaBenef", nullable = false)
    private UnitMeasureBenefAndGoalEntity unitMeasureBenef;
    // Identificador del Grupo Vulnerable
    @ManyToOne
    
    @JoinColumn(name = "IdGpoVulnerable", nullable = false)
    private VulnerableGroupEntity vulnerableGroupBenefAndGoals;
    // Identificador del Concepto General
    @ManyToOne
    
    @JoinColumn(name = "IdConceptoGral", nullable = false)
    private GeneralConceptEntity generalConceptBenefAndGoals;
    // Identificador del Concepto de Obra
    @ManyToOne
    
    @JoinColumn(name = "IdConceptoObra", nullable = false)
    private BuildingConceptEntity buildingConceptBenefAndGoals;
    // Identificador de las acciones
    @ManyToOne
    
    @JoinColumn(name = "IdAccionGB", nullable = false)
    private ActionGBEntity actionGBBenefAndGoals;
    // Identificador de las líneas de inversión
    @ManyToOne
   
    @JoinColumn(name = "IdLinIversion", nullable = false)
    private InvestmentLineEntity investmentLineBenefAndGoals;
    // Identificador de Macro Obra
    @ManyToOne
    
    @JoinColumn(name = "IdMacroObra", nullable = false)
    private MacroBuildEntity macroBuildBenefAndGoals;
    
    
    @OneToOne
    @JoinColumn(name="idpreficha")
   private InvPreFileEntity invPreFile;

    public InvPreFileEntity getInvPreFile() {
        return invPreFile;
    }

    public void setInvPreFile(InvPreFileEntity invPreFile) {
        this.invPreFile = invPreFile;
    }
    
    

    public double getBenefIndirects() {
        return benefIndirects;
    }

    public void setBenefIndirects(double benefIndirects) {
        this.benefIndirects = benefIndirects;
    }
 

    public ActionGBEntity getActionGBBenefAndGoals() {
        return actionGBBenefAndGoals;
    }

    public void setActionGBBenefAndGoals(ActionGBEntity actionGBBenefAndGoals) {
        this.actionGBBenefAndGoals = actionGBBenefAndGoals;
    }

    public ExecutionPeriodType getExecutionPeriod() {
        return executionPeriod;
    }

    public void setExecutionPeriod(ExecutionPeriodType executionPeriod) {
        this.executionPeriod = executionPeriod;
    }

     
    public Long getBenefAndGoalId() {
        return benefAndGoalId;
    }

    public void setBenefAndGoalId(Long benefAndGoalId) {
        this.benefAndGoalId = benefAndGoalId;
    }

    public double getBenefDirects() {
        return benefDirects;
    }

    public void setBenefDirects(double benefDirects) {
        this.benefDirects = benefDirects;
    }

    public BuildingConceptEntity getBuildingConceptBenefAndGoals() {
        return buildingConceptBenefAndGoals;
    }

    public void setBuildingConceptBenefAndGoals(BuildingConceptEntity buildingConceptBenefAndGoals) {
        this.buildingConceptBenefAndGoals = buildingConceptBenefAndGoals;
    }

    public GeneralConceptEntity getGeneralConceptBenefAndGoals() {
        return generalConceptBenefAndGoals;
    }

    public void setGeneralConceptBenefAndGoals(GeneralConceptEntity generalConceptBenefAndGoals) {
        this.generalConceptBenefAndGoals = generalConceptBenefAndGoals;
    }

    public double getGoalByYear() {
        return goalByYear;
    }

    public void setGoalByYear(double goalByYear) {
        this.goalByYear = goalByYear;
    }

    public InvestmentLineEntity getInvestmentLineBenefAndGoals() {
        return investmentLineBenefAndGoals;
    }

    public void setInvestmentLineBenefAndGoals(InvestmentLineEntity investmentLineBenefAndGoals) {
        this.investmentLineBenefAndGoals = investmentLineBenefAndGoals;
    }

    public MacroBuildEntity getMacroBuildBenefAndGoals() {
        return macroBuildBenefAndGoals;
    }

    public void setMacroBuildBenefAndGoals(MacroBuildEntity macroBuildBenefAndGoals) {
        this.macroBuildBenefAndGoals = macroBuildBenefAndGoals;
    }

    

    public double getTotalGoal() {
        return totalGoal;
    }

    public void setTotalGoal(double totalGoal) {
        this.totalGoal = totalGoal;
    }

    public UnitMeasureBenefAndGoalEntity getUnitMeasureBenef() {
        return unitMeasureBenef;
    }

    public void setUnitMeasureBenef(UnitMeasureBenefAndGoalEntity unitMeasureBenef) {
        this.unitMeasureBenef = unitMeasureBenef;
    }

    public UnitMeasureGoalEntity getUnitMeasureGoal() {
        return unitMeasureGoal;
    }

    public void setUnitMeasureGoal(UnitMeasureGoalEntity unitMeasureGoal) {
        this.unitMeasureGoal = unitMeasureGoal;
    }

   

    public VulnerableGroupEntity getVulnerableGroupBenefAndGoals() {
        return vulnerableGroupBenefAndGoals;
    }

    public void setVulnerableGroupBenefAndGoals(VulnerableGroupEntity vulnerableGroupBenefAndGoals) {
        this.vulnerableGroupBenefAndGoals = vulnerableGroupBenefAndGoals;
    }

    @Override
    public int compareTo(BenefAndGoalsEntity anOtherEntity)
    {
        int result = -1;
        if (this.benefAndGoalId != null && anOtherEntity.benefAndGoalId != null) {
        result = this.benefAndGoalId.compareTo(anOtherEntity.benefAndGoalId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj){
         boolean result = false;
         if (obj instanceof BenefAndGoalsEntity && this.benefAndGoalId != null 
                 && ((BenefAndGoalsEntity) obj).benefAndGoalId != null) {
            result = this.benefAndGoalId.equals(
                    ((BenefAndGoalsEntity) obj).benefAndGoalId);
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 23 * hash + (this.benefAndGoalId != null
                ? this.benefAndGoalId.hashCode() : 0);
        return hash;
    }
    
}
