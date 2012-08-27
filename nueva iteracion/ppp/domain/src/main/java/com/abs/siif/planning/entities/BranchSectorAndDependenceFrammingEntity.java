/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 */

@Entity
@Table(name = "siifabsramosectordep")
public class BranchSectorAndDependenceFrammingEntity 
implements Serializable, Comparable<BranchSectorAndDependenceFrammingEntity>
{
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idramosectordep")
    private Long branchSectorAndDependenceFrammingId;

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "idramosector", nullable = false)
    private BranchAndSectorFrammingEntity branchSectorAndDependenceFrammingBranchSectorId;
  
    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "IdDependencia", nullable = false)
    private DependenceEntity branchSectorAndDependenceDependence;

    public DependenceEntity getBranchSectorAndDependenceDependence() {
        return branchSectorAndDependenceDependence;
    }

    public void setBranchSectorAndDependenceDependence(DependenceEntity branchSectorAndDependenceDependence) {
        this.branchSectorAndDependenceDependence = branchSectorAndDependenceDependence;
    }

    public BranchAndSectorFrammingEntity getBranchSectorAndDependenceFrammingBranchSectorId() {
        return branchSectorAndDependenceFrammingBranchSectorId;
    }

    public void setBranchSectorAndDependenceFrammingBranchSectorId(BranchAndSectorFrammingEntity branchSectorAndDependenceFrammingBranchSectorId) {
        this.branchSectorAndDependenceFrammingBranchSectorId = branchSectorAndDependenceFrammingBranchSectorId;
    }

    public Long getBranchSectorAndDependenceFrammingId() {
        return branchSectorAndDependenceFrammingId;
    }

    public void setBranchSectorAndDependenceFrammingId(Long branchSectorAndDependenceFrammingId) {
        this.branchSectorAndDependenceFrammingId = branchSectorAndDependenceFrammingId;
    }
    
      
    @Override
    public boolean equals(Object obj) {
         boolean result = false;
        if (obj instanceof BranchSectorAndDependenceFrammingEntity 
                && this.branchSectorAndDependenceFrammingId != null 
                && ((BranchSectorAndDependenceFrammingEntity) obj).branchSectorAndDependenceFrammingId != null) {
            result = this.branchSectorAndDependenceFrammingId.equals
                    (((BranchSectorAndDependenceFrammingEntity) obj).branchSectorAndDependenceFrammingId);
        }

        return result;
    }

    @Override
    public int hashCode() {
         int hash = 7;
        hash = 37 * hash + (this.branchSectorAndDependenceFrammingId != null ? 
                this.branchSectorAndDependenceFrammingId.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(BranchSectorAndDependenceFrammingEntity obj) {
        int result = -1;
        if (this.getBranchSectorAndDependenceFrammingId() != null && 
                obj.getBranchSectorAndDependenceFrammingId() != null) 
        {
            result = this.getBranchSectorAndDependenceFrammingId().
                    compareTo(obj.getBranchSectorAndDependenceFrammingId());
        }
        return result;
    }
    
}
