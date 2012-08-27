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
@Table(name = "siifabsramosector")
public class BranchAndSectorFrammingEntity 
implements Comparable<BranchAndSectorFrammingEntity>, Serializable
{
    /**
     * Identificador
     */
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idramosector")
    private Long branchAndSectorFrammingId;

    /**
     * Liga con la entidad Ramo
     */
    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "idramo", nullable = false)
    private BranchEntity branchAndSectorFrammingBranch;
  
    /**
     * Liga entidad Sector
     */
    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "idsector", nullable = false)
    private SectorEntity branchAndSectorFrammingSector;

    public BranchEntity getBranchAndSectorFrammingBranch() {
        return branchAndSectorFrammingBranch;
    }

    public void setBranchAndSectorFrammingBranch(BranchEntity branchAndSectorFrammingBranch) {
        this.branchAndSectorFrammingBranch = branchAndSectorFrammingBranch;
    }

    public Long getBranchAndSectorFrammingId() {
        return branchAndSectorFrammingId;
    }

    public void setBranchAndSectorFrammingId(Long branchAndSectorFrammingId) {
        this.branchAndSectorFrammingId = branchAndSectorFrammingId;
    }

    public SectorEntity getBranchAndSectorFrammingSector() {
        return branchAndSectorFrammingSector;
    }

    public void setBranchAndSectorFrammingSector(SectorEntity branchAndSectorFrammingSector) {
        this.branchAndSectorFrammingSector = branchAndSectorFrammingSector;
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
         boolean result = false;
        if (obj instanceof BranchAndSectorFrammingEntity 
                && this.branchAndSectorFrammingId != null 
                && ((BranchAndSectorFrammingEntity) obj).branchAndSectorFrammingId != null) {
            result = this.branchAndSectorFrammingId.equals(((BranchAndSectorFrammingEntity) obj).branchAndSectorFrammingId);
        }

        return result;
    }

    @Override
    public int hashCode() {
         int hash = 7;
        hash = 37 * hash + (this.branchAndSectorFrammingId != null ? 
                this.branchAndSectorFrammingId.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(BranchAndSectorFrammingEntity obj) {
        int result = -1;
        if (this.getBranchAndSectorFrammingId() != null && obj.getBranchAndSectorFrammingId() != null) {
            result = this.getBranchAndSectorFrammingId().compareTo(obj.getBranchAndSectorFrammingId());
        }
        return result;
    }
    
}
