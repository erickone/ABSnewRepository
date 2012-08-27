package com.abs.siif.budget.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import org.hibernate.annotations.GenericGenerator;
/**
 * @author Miguel Baizabal Aguirre
 *
 */
@Entity
@Table(name = "siifpppCveMensualPptal")
public class MensualBudgetKeyEntity
        implements Serializable, Comparable<MensualBudgetKeyEntity>{

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdCveMensualPptal")
    private Long mensualBudgetKeyId;

    // Identificador de la Clave Presupuestal
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
   // @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,})
    @JoinColumn(name="IdCvePresupuestal", nullable=false)
    private BudgetKeyEntity budgetKeyMensualBudgetKey;

    // Identificador del Componente
    @Column(name = "Mes", length = 10)
    private String month;

    // Monto Original de la Clave Presupuestal
    @Column(name = "MontoOriginal")
    private Double originalAmount;

    public BudgetKeyEntity getBudgetKeyMensualBudgetKey() {
        return budgetKeyMensualBudgetKey;
    }

    public void setBudgetKeyMensualBudgetKey(BudgetKeyEntity budgetKeyMensualBudgetKey) {
        this.budgetKeyMensualBudgetKey = budgetKeyMensualBudgetKey;
    }

    public Long getMensualBudgetKeyId() {
        return mensualBudgetKeyId;
    }

    public void setMensualBudgetKeyId(Long mensualBudgetKeyId) {
        this.mensualBudgetKeyId = mensualBudgetKeyId;
    }

    public String getMonth() {
        return month != null ? month.trim() : month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    @Override
    public int compareTo(MensualBudgetKeyEntity anOtherEntity) {
        return this.mensualBudgetKeyId.compareTo(
                anOtherEntity.mensualBudgetKeyId);
    }

    @Override
    public boolean equals(Object obj) {
       boolean result = false;
        if (obj instanceof MensualBudgetKeyEntity && this.getMensualBudgetKeyId() != null
                && ((MensualBudgetKeyEntity) obj).getMensualBudgetKeyId() != null) {
            result = this.getMensualBudgetKeyId().equals(
                    ((MensualBudgetKeyEntity) obj).getMensualBudgetKeyId());
        }

        return result;    
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 89 * hash + (this.mensualBudgetKeyId != null
                ? this.mensualBudgetKeyId.hashCode() : 0);
        return hash;
    }
}
