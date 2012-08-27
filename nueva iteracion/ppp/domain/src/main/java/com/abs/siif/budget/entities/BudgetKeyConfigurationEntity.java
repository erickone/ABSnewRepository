package com.abs.siif.budget.entities;

import com.abs.siif.budget.data.QueryType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Table(name = "siifpppcvepptalcfg")
@Entity
public class BudgetKeyConfigurationEntity implements
        Serializable, Comparable<BudgetKeyConfigurationEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdCveCfg")
    private Long budgetKeyConfigurationId;
    @Column(name = "Posiciones")
    private short budgetKeyConfigurationPositions;
    @Column(name = "SqlValida", length = 2000)
    private String budgetKeyConfigurationSQLQuery;
    @Column(name = "IdNivelCreacion")
    private short budgetKeyConfigurationHierarchy;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TipoSQL")
    private QueryType budgetKeyConfigurationQueryType;
    @OneToOne
    @JoinColumn(name = "idelemento", nullable = true)
    private BudgetKeyItemEntity budgetKeyItem;
    @OneToMany(mappedBy = "BudgetKeyConfiguration", fetch = FetchType.LAZY)
    private List<BudgetDetailsKeyEntity> budgetDetailKeys;
    
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="Iddefcvepresupuestal")
    private BudgetKeyDefinitionEntity budgetKeyDefinition;

    public BudgetKeyDefinitionEntity getBudgetKeyDefinition() {
        return budgetKeyDefinition;
    }

    public void setBudgetKeyDefinition(BudgetKeyDefinitionEntity budgetKeyDefinition) {
        this.budgetKeyDefinition = budgetKeyDefinition;
    }
    
    

    public List<BudgetDetailsKeyEntity> getBudgetDetailKeys() {
        return budgetDetailKeys;
    }

    public BudgetKeyItemEntity getBudgetKeyItem() {
        return budgetKeyItem;
    }

    public void setBudgetKeyItem(BudgetKeyItemEntity budgetKeyItem) {
        this.budgetKeyItem = budgetKeyItem;
    }

    public QueryType getBudgetKeyConfigurationQueryType() {
        return budgetKeyConfigurationQueryType;
    }

    public void setBudgetKeyConfigurationQueryType(QueryType budgetKeyConfigurationQueryType) {
        this.budgetKeyConfigurationQueryType = budgetKeyConfigurationQueryType;
    }

    public short getBudgetKeyConfigurationHierarchy() {
        return budgetKeyConfigurationHierarchy;
    }

    public void setBudgetKeyConfigurationHierarchy(short budgetKeyConfigurationHierarchy) {
        this.budgetKeyConfigurationHierarchy = budgetKeyConfigurationHierarchy;
    }

    public Long getBudgetKeyConfigurationId() {
        return budgetKeyConfigurationId;
    }

    public void setBudgetKeyConfigurationId(Long budgetKeyConfigurationId) {
        this.budgetKeyConfigurationId = budgetKeyConfigurationId;
    }

    public short getBudgetKeyConfigurationPositions() {
        return budgetKeyConfigurationPositions;
    }

    public void setBudgetKeyConfigurationPositions(short budgetKeyConfigurationPositions) {
        this.budgetKeyConfigurationPositions = budgetKeyConfigurationPositions;
    }

    public String getBudgetKeyConfigurationSQLQuery() {
        return budgetKeyConfigurationSQLQuery != null ? budgetKeyConfigurationSQLQuery.trim()
                : budgetKeyConfigurationSQLQuery;
    }

    public void setBudgetKeyConfigurationSQLQuery(String budgetKeyConfigurationSQLQuery) {
        this.budgetKeyConfigurationSQLQuery = budgetKeyConfigurationSQLQuery;
    }

    @Override
    public int compareTo(BudgetKeyConfigurationEntity anOtherEntity) {
        return this.budgetKeyConfigurationId.compareTo(
                anOtherEntity.budgetKeyConfigurationId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BudgetKeyConfigurationEntity) {
            return this.budgetKeyConfigurationId.equals(
                    ((BudgetKeyConfigurationEntity) obj).budgetKeyConfigurationId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.budgetKeyConfigurationId != null
                ? this.budgetKeyConfigurationId.hashCode() : 0);
        return hash;
    }
}
