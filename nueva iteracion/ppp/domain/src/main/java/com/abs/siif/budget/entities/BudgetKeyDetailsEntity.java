package com.abs.siif.budget.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Luis Agustin Carreón
 */
@Entity
@Table(name = "SIIFPPPDetalleCvePptal")
public class BudgetKeyDetailsEntity implements
        Serializable, Comparable<BudgetKeyDetailsEntity> {
    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdDetalleCvePptal")
    private Long budgetKeyDetailsId;

    // Identificador de la clave presupuestal
    @ManyToOne
    @JoinColumn(name="IdCvePresupuestal", nullable=false)
    private BudgetKeyEntity budgetKeyId;

    // TODO : Llave Foránea a la entidad de Configuración de Clave Presupuestal
    /*/ FK to budgetKeyConfigurationEntity
    @ManyToOne
    @JoinColumn(name="IdDefCvePresupuestal", nullable=true)
    */
    //private int budgetKeyConfigId;

    @Column(name = "IdReal", nullable=false)
    private int budgetKeyDetailsRealId;

    public Long getBudgetKeyDetailsId() {
        return budgetKeyDetailsId;
    }

    public void setBudgetKeyDetailsId(Long budgetKeyDetailsId) {
        this.budgetKeyDetailsId = budgetKeyDetailsId;
    }

    public int getBudgetKeyDetailsRealId() {
        return budgetKeyDetailsRealId;
    }

    public void setBudgetKeyDetailsRealId(int budgetKeyDetailsRealId) {
        this.budgetKeyDetailsRealId = budgetKeyDetailsRealId;
    }

    public BudgetKeyEntity getBudgetKeyId() {
        return budgetKeyId;
    }

    public void setBudgetKeyId(BudgetKeyEntity budgetKeyId) {
        this.budgetKeyId = budgetKeyId;
    }

    @Override
    public int compareTo(BudgetKeyDetailsEntity anOtherEntity) {
        return this.budgetKeyDetailsId.compareTo(
                anOtherEntity.budgetKeyDetailsId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BudgetKeyDetailsEntity) {
            return this.budgetKeyDetailsId.equals(
                    ((BudgetKeyDetailsEntity) obj).budgetKeyDetailsId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.budgetKeyDetailsId != null
                ? this.budgetKeyDetailsId.hashCode() : 0);
        return hash;
    }

}
