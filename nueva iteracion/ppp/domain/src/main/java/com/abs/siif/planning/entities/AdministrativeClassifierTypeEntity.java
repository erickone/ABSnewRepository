/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AdministrativeClassifierTypeEntity
 *  Purpose:  [Mapea el tipo de clasificador administrativo]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name = "siifabstipoclasifdep")
public class AdministrativeClassifierTypeEntity  
implements Comparable<AdministrativeClassifierTypeEntity>, Serializable {
  
     
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idtipoclasifdep")
    private Long adminClassTypeId; 
   
    @Column(name="Descripcion",length=100,nullable=false)
    private String adminClassTypeDescription;
    
    @OneToMany(mappedBy = "administrativeClassifierType")
    private Set<DependenceEntity> dependencies;
    
    public Set<DependenceEntity> getDependencies() {
        return dependencies;
    }
 
    public String getAdminClassTypeDescription() {
        return adminClassTypeDescription;
    }

    public void setAdminClassTypeDescription(String adminClassTypeDescription) {
        this.adminClassTypeDescription = adminClassTypeDescription;
    }

    public Long getAdminClassTypeId() {
        return adminClassTypeId;
    }

    public void setAdminClassTypeId(Long adminClassTypeId) {
        this.adminClassTypeId = adminClassTypeId;
    }

   

    @Override
    public boolean equals(Object obj) {
         boolean result = false;
        if (obj instanceof AdministrativeClassifierTypeEntity 
                && this.adminClassTypeId != null 
                && ((AdministrativeClassifierTypeEntity) obj).adminClassTypeId != null) {
            result = this.adminClassTypeId.equals(
                    ((AdministrativeClassifierTypeEntity) obj).adminClassTypeId);
        }

        return result;
    }

    @Override
    public int hashCode() {
         int hash = 7;
        hash = 37 * hash + (this.adminClassTypeId != null ? 
                this.adminClassTypeId.hashCode() : 0);
        hash = 37 * hash + (this.adminClassTypeDescription!= null ? 
                this.adminClassTypeDescription.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(AdministrativeClassifierTypeEntity obj) {
        int result = -1;
        if (this.getAdminClassTypeId() != null && obj.getAdminClassTypeId() != null) {
            result = this.getAdminClassTypeId().compareTo(obj.getAdminClassTypeId());
        }
        return result;
    }
    
    
}
