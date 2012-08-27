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
@Table(name = "siifabssector")
public class SectorEntity 
implements Comparable<SectorEntity>, Serializable
{
       
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idsector")
    private Long sectorId;

    @Column(name="clave",length=50,nullable=false)
    private String sectorKey;
  
    @Column(name="descripcion",length=255,nullable=false)
    private String sectorDescription;

    public String getSectorDescription() {
        return sectorDescription;
    }

    public void setSectorDescription(String sectorDescription) {
        this.sectorDescription = sectorDescription;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorKey() {
        return sectorKey;
    }

    public void setSectorKey(String sectorKey) {
        this.sectorKey = sectorKey;
    }
    
    
    
   @Override
    public boolean equals(Object obj) {
         boolean result = false;
        if (obj instanceof SectorEntity 
                && this.sectorId != null 
                && ((SectorEntity) obj).sectorId != null) {
            result = this.sectorId.equals(
                    ((SectorEntity) obj).sectorId);
        }

        return result;
    }

    @Override
    public int hashCode() {
         int hash = 7;
        hash = 37 * hash + (this.sectorId != null ? 
                this.sectorId.hashCode() : 0);
        hash = 37 * hash + (this.sectorKey!= null ? 
                this.sectorKey.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(SectorEntity obj) {
        int result = -1;
        if (this.getSectorId() != null && obj.getSectorId() != null) {
            result = this.getSectorId().compareTo(obj.getSectorId());
        }
        return result;
    }
    
}
