package com.abs.siif.budget.entities;

import com.abs.siif.programming.entities.InvPreFileDetailEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Miguel Baizabal Aguirre
 *
 */
@Entity
@Table(name = "siifpppCvePresupuestal")
public class BudgetKeyEntity
        implements Serializable, Comparable<BudgetKeyEntity> {

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdCvePresupuestal")
    private Long budgetKeyId;
    // Identificador de la Definición de la Clave Presupuestal
    @ManyToOne (fetch= FetchType.LAZY)
    @JoinColumn(name = "IdDefCvePresupuestal", nullable = true)
    private BudgetKeyDefinitionEntity budgetKeyDefinitionBudgetKey;
    // Identificador del Objeto del Gasto
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdObjetoGasto", nullable = false)
    private ObjectExpenseEntity objectExpenseBudgetKey;
    // Identificador del Estado de la Clave Presupuestal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCveEstado", nullable = true)
    private BudgetKeyStatusEntity budgetKeyStatusBudgetKey;
    // Identificador de la Fuente de Financiamiento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdFuenteFinanciamiento", nullable = true)
    private FinancingSourceEntity financingSourceBudgetKey;
    // Monto Original de la Clave Presupuestal
    @Column(name = "MontoOriginal")
    private Double originalAmount = 0.0;
    // Relación con el Mensualizado de la Clave Presupuestal
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budgetKeyMensualBudgetKey", fetch = FetchType.LAZY)
    //@Cascade({org.hibernate.annotations.CsacadeType.SAVE_UPDATE})
    private Set<MensualBudgetKeyEntity> mensualBudgetKeyBudgetKey;
    // Detalle de Claves Presupuestales de PreFichas
    @OneToMany(mappedBy = "invPreFileDetailBudgetKey", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<InvPreFileDetailEntity> invPreFileDetailBudgetKey;
    //Campo donde se guarda la Clave Presupuestal
    @Column(name = "Clave", length = 100)
    private String budgetKeyBProgramaticKey;
    
   
    
    @Column(name = "montoadicional")
    private Double additionalAmount;
    
    @Column(name="justificacionpptoadic")
     private String additionalJustification;

    public Double getAdditionalAmount() {
        return additionalAmount;
    }

    public void setAdditionalAmount(Double additionalAmount) {
        this.additionalAmount = additionalAmount;
    }

    public String getAdditionalJustification() {
        return additionalJustification;
    }

    public void setAdditionalJustification(String additionalJustification) {
        this.additionalJustification = additionalJustification;
    }
    
    
     
    
    @OneToMany(mappedBy = "budgetKey", fetch = FetchType.LAZY) 
    private List<BudgetDetailsKeyEntity> budgetDetailKeys;

    private transient Long budgetKeyBasic;
    
    private transient boolean isAdditional;

    public boolean isIsAdditional() {
        return isAdditional;
    }

    public void setIsAdditional(boolean isAdditional) {
        this.isAdditional = isAdditional;
    }
    
    
    
    private transient Long budgetKeyNoBasic;
    public List<BudgetDetailsKeyEntity> getBudgetDetailKeys() {
        return budgetDetailKeys;
    }

    public void setBudgetDetailKeys(List<BudgetDetailsKeyEntity> budgetDetailKeys) {
        this.budgetDetailKeys = budgetDetailKeys;
    }
   

    /**
     * @return the budgetKeyId
     */
    public Long getBudgetKeyId() {
        return budgetKeyId;
    }

    /**
     * @param budgetKeyId the budgetKeyId to set
     */
    public void setBudgetKeyId(Long budgetKeyId) {
        this.budgetKeyId = budgetKeyId;
    }

    public Long getBudgetKeyBasic()
    {
        return budgetKeyBasic;
    }

    public void setBudgetKeyBasic(Long budgetKeyBasic)
    {
        this.budgetKeyBasic = budgetKeyBasic;
    }

    public Long getBudgetKeyNoBasic()
    {
        return budgetKeyNoBasic;
    }

    public void setBudgetKeyNoBasic(Long budgetKeyNoBasic)
    {
        this.budgetKeyNoBasic = budgetKeyNoBasic;
    }

    
    /**
     * @return the budgetKeyDefinitionBudgetKey
     */
    public BudgetKeyDefinitionEntity getBudgetKeyDefinitionBudgetKey() {
        return budgetKeyDefinitionBudgetKey;
    }

    /**
     * @param budgetKeyDefinitionBudgetKey the budgetKeyDefinitionBudgetKey to
     * set
     */
    public void setBudgetKeyDefinitionBudgetKey(BudgetKeyDefinitionEntity budgetKeyDefinitionBudgetKey) {
        this.budgetKeyDefinitionBudgetKey = budgetKeyDefinitionBudgetKey;
    }

    /**
     * @return the objectExpenseBudgetKey
     */
    public ObjectExpenseEntity getObjectExpenseBudgetKey() {
        return objectExpenseBudgetKey;
    }

    /**
     * @param objectExpenseBudgetKey the objectExpenseBudgetKey to set
     */
    public void setObjectExpenseBudgetKey(ObjectExpenseEntity objectExpenseBudgetKey) {
        this.objectExpenseBudgetKey = objectExpenseBudgetKey;
    }

    /**
     * @return the budgetKeyStatusBudgetKey
     */
    public BudgetKeyStatusEntity getBudgetKeyStatusBudgetKey() {
        return budgetKeyStatusBudgetKey;
    }

    /**
     * @param budgetKeyStatusBudgetKey the budgetKeyStatusBudgetKey to set
     */
    public void setBudgetKeyStatusBudgetKey(BudgetKeyStatusEntity budgetKeyStatusBudgetKey) {
        this.budgetKeyStatusBudgetKey = budgetKeyStatusBudgetKey;
    }

    /**
     * @return the financingSourceBudgetKey
     */
    public FinancingSourceEntity getFinancingSourceBudgetKey() {
        return financingSourceBudgetKey;
    }

    /**
     * @param financingSourceBudgetKey the financingSourceBudgetKey to set
     */
    public void setFinancingSourceBudgetKey(FinancingSourceEntity financingSourceBudgetKey) {
        this.financingSourceBudgetKey = financingSourceBudgetKey;
    }

    /**
     * @return the originalAmount
     */
    public Double getOriginalAmount() {
        return originalAmount;
    }

    /**
     * @param originalAmount the originalAmount to set
     */
    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Set<InvPreFileDetailEntity> getInvPreFileDetailBudgetKey() {
        return invPreFileDetailBudgetKey;
    }

    public void setInvPreFileDetailBudgetKey(Set<InvPreFileDetailEntity> invPreFileDetailBudgetKey) {
        this.invPreFileDetailBudgetKey = invPreFileDetailBudgetKey;
    }

    public Set<MensualBudgetKeyEntity> getMensualBudgetKeyBudgetKey() {
        return mensualBudgetKeyBudgetKey;
    }

    public void setMensualBudgetKeyBudgetKey(Set<MensualBudgetKeyEntity> mensualBudgetKeyBudgetKey) {
        this.mensualBudgetKeyBudgetKey = mensualBudgetKeyBudgetKey;
    }

    public String getBudgetKeyBProgramaticKey() {
        return budgetKeyBProgramaticKey != null ? budgetKeyBProgramaticKey.trim()
                : budgetKeyBProgramaticKey;
    }

    public void setBudgetKeyBProgramaticKey(String budgetKeyBProgramaticKey) {
        this.budgetKeyBProgramaticKey = budgetKeyBProgramaticKey;
    }

    @Override
    public int compareTo(BudgetKeyEntity anOtherEntity) {
        return this.budgetKeyId.compareTo(
                anOtherEntity.budgetKeyId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BudgetKeyEntity) {
            return this.budgetKeyId.equals(
                    ((BudgetKeyEntity) obj).budgetKeyId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.budgetKeyId != null
                ? this.budgetKeyId.hashCode() : 0);
        return hash;
    }
    
    public String getURFromCve(){
        String returnStr=null;
        if(budgetKeyBProgramaticKey != null){
            returnStr = budgetKeyBProgramaticKey.substring(0,5);
        }
        return returnStr;
    }
}
