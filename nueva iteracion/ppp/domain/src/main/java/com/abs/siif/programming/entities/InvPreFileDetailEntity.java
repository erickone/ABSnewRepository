package com.abs.siif.programming.entities;

import com.abs.siif.budget.entities.BudgetKeyEntity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal
 */

@Entity
@Table(name = "siifpppdetcvepreficha")
public class InvPreFileDetailEntity
    implements Comparable<InvPreFileDetailEntity>, Serializable{

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdDetCvePreFicha")
    private Long invPreFileDetailId;

    // Identificador de la PreFicha
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdPreFicha", nullable = false)
    private InvPreFileEntity invPreFileDetailPreFile;

    // Identificador de la Clave Presupuestal
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdCvePresupuestal", nullable = false)
    private BudgetKeyEntity invPreFileDetailBudgetKey;

    // Monto de la Clave Presupuestal asignado a la PreFicha
    @Column(name = "MontoAsignado")
    private double assignedAmount;

    public double getAssignedAmount() {
        return assignedAmount;
    }

    public void setAssignedAmount(double assignedAmount) {
        this.assignedAmount = assignedAmount;
    }

    public BudgetKeyEntity getInvPreFileDetailBudgetKey() {
        return invPreFileDetailBudgetKey;
    }

    public void setInvPreFileDetailBudgetKey(BudgetKeyEntity invPreFileDetailBudgetKey) {
        this.invPreFileDetailBudgetKey = invPreFileDetailBudgetKey;
    }

    public Long getInvPreFileDetailId() {
        return invPreFileDetailId;
    }

    public void setInvPreFileDetailId(Long invPreFileDetailId) {
        this.invPreFileDetailId = invPreFileDetailId;
    }

    public InvPreFileEntity getInvPreFileDetailPreFile() {
        return invPreFileDetailPreFile;
    }

    public void setInvPreFileDetailPreFile(InvPreFileEntity invPreFileDetailPreFile) {
        this.invPreFileDetailPreFile = invPreFileDetailPreFile;
    }

    @Override
    public int compareTo(InvPreFileDetailEntity anOtherEntity) {
        int result = -1;
        if (this.invPreFileDetailId != null 
                && anOtherEntity.invPreFileDetailId != null) {
            result = this.invPreFileDetailId.compareTo(
                anOtherEntity.invPreFileDetailId);
        }
        return result;
    }
    
    @Override 
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof InvPreFileDetailEntity 
                 && this.invPreFileDetailId != null
                 && ((InvPreFileDetailEntity) obj).invPreFileDetailId != null) {
            result = this.invPreFileDetailId.equals(
                    ((InvPreFileDetailEntity) obj).invPreFileDetailId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + (this.invPreFileDetailId != null 
                ? this.invPreFileDetailId.hashCode() : 0);
        return hash;
    }

}
