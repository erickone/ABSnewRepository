package com.abs.siif.budget.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
@Entity
@Table(name = "SIIFPPPFueFinanciamiento")
public class FinancingSourceEntity implements
        Comparable<FinancingSourceEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdFuenteFinanciamiento")
    private Long financingSourceId;

    @Column(name = "Clave", length = 20, nullable = false)
    private String financingSourceKey;

    @Column(name = "Descripcion", length = 150, nullable = false)
    private String financingSourceDescription;
    
    @Column(name = "fechavigencia", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }   
    
   
    @OneToMany(mappedBy = "financingSourceBudgetKey")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE}) private
    Set<BudgetKeyEntity> financingSourceBudgetKey;

    // Relacion Many to Many con la Fuente de Financiamiento
    @ManyToMany
    @JoinTable(name = "siifpppObjGastoFueFinan", joinColumns = {
        @JoinColumn(name = "IdFuenteFinanciamiento", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdObjetoGasto",
        nullable = false, updatable = false)})
    private List<ObjectExpenseEntity> objectExpenseFinancingSource;

    public String getFinancingSourceDescription() {
        return financingSourceDescription != null ? financingSourceDescription.trim() :
               financingSourceDescription;
    }

    public void setFinancingSourceDescription(String financingSourceDescription) {
        this.financingSourceDescription = financingSourceDescription;
    }

    public Long getFinancingSourceId() {
        return financingSourceId;
    }

    public void setFinancingSourceId(Long financingSourceId) {
        this.financingSourceId = financingSourceId;
    }

    public String getFinancingSourceKey() {
        return financingSourceKey != null ? financingSourceKey.trim() :
               financingSourceKey;
    }

    public void setFinancingSourceKey(String financingSourceKey) {
        this.financingSourceKey = financingSourceKey;
    }

    @Override
    public int compareTo(FinancingSourceEntity anOtherEntity) {
        return this.financingSourceId.compareTo(
                anOtherEntity.financingSourceId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MensualBudgetKeyEntity) {
            return this.financingSourceId.equals(
                    ((FinancingSourceEntity) obj).financingSourceId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.financingSourceId != null
                ? this.financingSourceId.hashCode() : 0);
        return hash;
    }

}
