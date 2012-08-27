package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Israel Ruiz
 */
@Entity
@Table(name = "siifpppLinInversion")
public class InvestmentLineEntity implements
        Comparable<InvestmentLineEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdLinInversion")
    private Long investLineId;
    @Column(name = "Clave", length = 20, nullable = false)
    private String investLineKey;
    @Column(name = "Descripcion", length = 150, nullable = false)
    private String investLineDescription;
    @OneToMany(mappedBy = "investmentLineBenefAndGoals")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<BenefAndGoalsEntity> investmentLineBenefAndGoals;

    public String getInvestLineDescription() {
        return investLineDescription != null ? investLineDescription.trim() : investLineDescription;
    }

    public void setInvestLineDescription(String investLineDescription) {
        this.investLineDescription = investLineDescription;
    }

    public Long getInvestLineId() {
        return investLineId;
    }

    public void setInvestLineId(Long investLineId) {
        this.investLineId = investLineId;
    }

    public String getInvestLineKey() {
        return investLineKey != null ? investLineKey.trim() : investLineKey;
    }

    public void setInvestLineKey(String investLineKey) {
        this.investLineKey = investLineKey;
    }

    @Override
    public int compareTo(InvestmentLineEntity anOtherEntity) {
        int result = -1;
        if (this.investLineId != null 
                && anOtherEntity.investLineId != null) {
            result = this.investLineId.compareTo(
                anOtherEntity.investLineId);
        }
        return result;
    }
    
    @Override 
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof InvestmentLineEntity 
                 && this.investLineId != null
                 && ((InvestmentLineEntity) obj).investLineId != null) {
            result = this.investLineId.equals(
                    ((InvestmentLineEntity) obj).investLineId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + (this.investLineId != null 
                ? this.investLineId.hashCode() : 0);
        return hash;
    }
}
