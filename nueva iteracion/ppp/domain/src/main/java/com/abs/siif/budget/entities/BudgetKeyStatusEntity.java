package com.abs.siif.budget.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
@Entity
@Table(name = "siifpppcveestado")
public class BudgetKeyStatusEntity implements
        Serializable, Comparable<BudgetKeyStatusEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdCveEstado")
    private Long budgetKeyStatusEntityId;

    @Column(name="Clave", length= 20)
    private String budgetKeyStatusEntityKey;

    @Column(name="Descripcion", length= 150)
    private String budgetKeyStatusEntityDescription;

    @Column(name="Estatus", length= 1)
    private String budgetKeyStatusEntityType;

    @OneToMany(mappedBy = "budgetKeyStatusBudgetKey")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE}) private
    Set<BudgetKeyEntity> budgetKeyStatusBudgetKey;

    public String getBudgetKeyStatusEntityDescription() {
        return budgetKeyStatusEntityDescription != null ? budgetKeyStatusEntityDescription.trim() :
               budgetKeyStatusEntityDescription;
    }

    public void setBudgetKeyStatusEntityDescription(String budgetKeyStatusEntityDescription) {
        this.budgetKeyStatusEntityDescription = budgetKeyStatusEntityDescription;
    }

    public Long getBudgetKeyStatusEntityId() {
        return budgetKeyStatusEntityId;
    }

    public void setBudgetKeyStatusEntityId(Long budgetKeyStatusEntityId) {
        this.budgetKeyStatusEntityId = budgetKeyStatusEntityId;
    }

    public String getBudgetKeyStatusEntityKey() {
        return budgetKeyStatusEntityKey != null ? budgetKeyStatusEntityKey.trim() :
               budgetKeyStatusEntityKey;
    }

    public void setBudgetKeyStatusEntityKey(String budgetKeyStatusEntityKey) {
        this.budgetKeyStatusEntityKey = budgetKeyStatusEntityKey;
    }

    public String getBudgetKeyStatusEntityType() {
        return budgetKeyStatusEntityType != null ? budgetKeyStatusEntityType.trim() :
               budgetKeyStatusEntityType;
    }

    public void setBudgetKeyStatusEntityType(String budgetKeyStatusEntityType) {
        this.budgetKeyStatusEntityType = budgetKeyStatusEntityType;
    }

    @Override
    public int compareTo(BudgetKeyStatusEntity anOtherEntity) {
        return this.budgetKeyStatusEntityId.compareTo(
                anOtherEntity.budgetKeyStatusEntityId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BudgetKeyStatusEntity) {
            return this.budgetKeyStatusEntityId.equals(
                    ((BudgetKeyStatusEntity) obj).budgetKeyStatusEntityId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.budgetKeyStatusEntityId != null
                ? this.budgetKeyStatusEntityId.hashCode() : 0);
        return hash;
    }

}
