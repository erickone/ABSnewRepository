package com.abs.siif.budget.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar Define cada uno de los elementos los
 * cuales deben ser empleados para la generación de la clave presupuestal
 */
@Table(name = "siifpppelemento")
@Entity
public class BudgetKeyItemEntity implements
        Serializable, Comparable<BudgetKeyItemEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idelemento")
    private Long budgetKeyId;

    @Column(name = "elemento", length = 50)
    private String budgetKeyName;

    @Column(name = "descripcion", length = 150)
    private String budgetKeyDescription;

    @Column(name = "tabla", length = 20)
    private String budgetKeyEntity;

    @Column(name = "entidadbase", length = 50)
    private String budgetKeyItemBaseEntity;

    @Column(name = "atributonivel", length = 100)
    private String budgetKeyItemLevelAttribute;

    @OneToOne(mappedBy = "budgetKeyItem")
    private BudgetKeyConfigurationEntity budgetKeyConfigurationEntity;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "siifpppdetconftechopptal", joinColumns = {
        @JoinColumn(name = "idelemento", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "idconftechopptal", nullable = false, updatable = false)})
    private List<CeilingConfigurationEntity> ceilingConfigurations = new ArrayList<CeilingConfigurationEntity>();

    public String getBudgetKeyItemBaseEntity() {
        return budgetKeyItemBaseEntity != null ? budgetKeyItemBaseEntity.trim()
                : budgetKeyItemBaseEntity;
    }

    public void setBudgetKeyItemBaseEntity(String budgetKeyItemBaseEntity) {
        this.budgetKeyItemBaseEntity = budgetKeyItemBaseEntity;
    }

    public String getBudgetKeyItemLevelAttribute() {
        return budgetKeyItemLevelAttribute != null ? budgetKeyItemLevelAttribute.trim()
                : budgetKeyItemLevelAttribute;
    }

    public void setBudgetKeyItemLevelAttribute(String budgetKeyItemLevelAttribute) {
        this.budgetKeyItemLevelAttribute = budgetKeyItemLevelAttribute;
    }

    public BudgetKeyConfigurationEntity getBudgetKeyConfigurationEntity() {
        return budgetKeyConfigurationEntity;
    }

    public void setBudgetKeyConfigurationEntity(BudgetKeyConfigurationEntity budgetKeyConfigurationEntity) {
        this.budgetKeyConfigurationEntity = budgetKeyConfigurationEntity;
    }

    public String getBudgetKeyDescription() {
        return budgetKeyDescription != null ? budgetKeyDescription.trim()
                : budgetKeyDescription;
    }

    public void setBudgetKeyDescription(String budgetKeyDescription) {
        this.budgetKeyDescription = budgetKeyDescription;
    }

    public String getBudgetKeyEntity() {
        return budgetKeyEntity != null ? budgetKeyEntity.trim()
                : budgetKeyEntity;
    }

    public void setBudgetKeyEntity(String budgetKeyEntity) {
        this.budgetKeyEntity = budgetKeyEntity;
    }

    public Long getBudgetKeyId() {
        return budgetKeyId;
    }

    public void setBudgetKeyId(Long budgetKeyId) {
        this.budgetKeyId = budgetKeyId;
    }

    public String getBudgetKeyName() {
        return budgetKeyName != null ? budgetKeyName.trim()
                : budgetKeyName;
    }

    public void setBudgetKeyName(String budgetKeyName) {
        this.budgetKeyName = budgetKeyName;
    }

    public List<CeilingConfigurationEntity> getCeilingConfigurations() {
        return ceilingConfigurations;
    }

    public void setCeilingConfigurations(List<CeilingConfigurationEntity> ceilingConfigurations) {
        this.ceilingConfigurations = ceilingConfigurations;
    }

    @Override
    public int compareTo(BudgetKeyItemEntity anOtherEntity) {
        return this.budgetKeyId.compareTo(
                anOtherEntity.budgetKeyId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BudgetKeyItemEntity) {
            return this.budgetKeyId.equals(
                    ((BudgetKeyItemEntity) obj).budgetKeyId);
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

    @Override
    public String toString() {
        return this.budgetKeyId.toString();
    }
}
