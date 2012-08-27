/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Erick Leija
 */
@Entity
@Table(name = "siifabsramo")
public class BranchEntity
implements Comparable<BranchEntity>, Serializable
{
         
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idramo")
    private Long branchId;

    @Column(name="clave",length=50,nullable=false)
    private String branchKey;
  
    @Column(name="descripcion",length=255,nullable=false)
    private String branchDescription;

    public String getBranchDescription() {
        return branchDescription;
    }

    public void setBranchDescription(String branchDescription) {
        this.branchDescription = branchDescription;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchKey() {
        return branchKey;
    }

    public void setBranchKey(String branchKey) {
        this.branchKey = branchKey;
    }
    
    
     @Override
    public boolean equals(Object obj) {
         boolean result = false;
        if (obj instanceof BranchEntity 
                && this.branchId != null 
                && ((BranchEntity) obj).branchId != null) {
            result = this.branchId.equals(((BranchEntity) obj).branchId);
        }

        return result;
    }

    @Override
    public int hashCode() {
         int hash = 7;
        hash = 37 * hash + (this.branchId != null ? 
                this.branchId.hashCode() : 0);
        hash = 37 * hash + (this.branchKey!= null ? 
                this.branchKey.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(BranchEntity obj) {
        int result = -1;
        if (this.getBranchId() != null && obj.getBranchId() != null) {
            result = this.getBranchId().compareTo(obj.branchId);
        }
        return result;
    }
}
