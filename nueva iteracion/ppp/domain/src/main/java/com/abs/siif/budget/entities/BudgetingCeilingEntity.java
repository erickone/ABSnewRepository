package com.abs.siif.budget.entities;

import com.abs.siif.planning.entities.DependenceEntity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author Erick Leija Entidad del techo presupuestal mapeada a la dependencia y
 * al Objeto Gasto
 */
@Entity
@Table(name="SIIFPPPTechoPptal")
public class BudgetingCeilingEntity
        implements Comparable<BudgetingCeilingEntity>, Serializable
{
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdTechoPptal")
    private Long budgetCeilingId;
    //Aqui se realiza la Relación con la Dependencia
    @ManyToOne (fetch= FetchType.LAZY)
    @JoinColumn(name = "IdDependencia", nullable = false)
    private DependenceEntity dependence;

    @Column(name = "Consecutivo")
    private long budgetCeilingConsecutive;
    //Aqui se realiza la relacion con el Objeto del Gasto

    @OneToOne(fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "IdObjetoGasto", nullable = true)
    private ObjectExpenseEntity objectExpense;



    @Column(name = "Monto")
    private long budgetCeilingAmount;



    public long getBudgetCeilingAmount()
    {
        return budgetCeilingAmount;
    }

    public void setBudgetCeilingAmount(long anAmount) {
        this.budgetCeilingAmount = anAmount;
    }

    public long getBudgetCeilingConsecutive() {
        return budgetCeilingConsecutive;
    }

    public void setBudgetCeilingConsecutive(long aConsecutive) {
        this.budgetCeilingConsecutive = aConsecutive;
    }

    public DependenceEntity getDependence() {
        return dependence;
    }

    public void setDependence(DependenceEntity dependenceId) {
        this.dependence = dependenceId;
    }



    public Long getBudgetCeilingId()
    {
        return budgetCeilingId;
    }

    public void setBudgetCeilingId(Long aBudgetCeilingId) {
        this.budgetCeilingId = aBudgetCeilingId;
    }

    public ObjectExpenseEntity getObjectExpense() {
        return objectExpense;
    }

    public void setObjectExpense(ObjectExpenseEntity objectExpense) {
        this.objectExpense = objectExpense;
    }

   @Override
    public int compareTo(BudgetingCeilingEntity anOtherEntity) {
        return this.budgetCeilingId.compareTo(
                anOtherEntity.budgetCeilingId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BudgetingCeilingEntity) {
            return this.budgetCeilingId.equals(
                    ((BudgetingCeilingEntity) obj).budgetCeilingId);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.budgetCeilingId != null
                ? this.budgetCeilingId.hashCode() : 0);
        return hash;
    }

}
