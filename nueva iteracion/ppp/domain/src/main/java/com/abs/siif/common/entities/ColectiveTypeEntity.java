/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ColectiveTypeEntity
 *  Purpose:  [Mapea los tipos de colectiva ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.entities;

import com.abs.siif.planning.entities.DependenceLevelEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name = "siifabstipocolectiva")
public class ColectiveTypeEntity implements
        Comparable<ColectiveTypeEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idtipocolectiva")
    private Long colectiveTypeId;
    @Column(name = "clave", nullable = false, length = 20)
    private String colectiveTypeKey;
    @Column(name = "tipo", nullable = false, length = 10)
    private String colectiveType;
    @Column(name = "descripcion", nullable = false, length = 100)
    private String colectiveTypeDescription;
    @OneToMany(mappedBy = "colectiveType")
    private Set<ColectiveEntity> colectives;
    @OneToMany(mappedBy = "colectiveType",fetch= FetchType.LAZY)
    private List<DependenceLevelEntity> dependenceLevels;

    public List<DependenceLevelEntity> getDependenceLevels() {
        return dependenceLevels;
    }
    
    

    public Set<ColectiveEntity> getColectives() {
        return colectives;
    }

    public String getColectiveType() {
        return colectiveType;
    }

    public void setColectiveType(String colectiveType) {
        this.colectiveType = colectiveType;
    }

    public Long getColectiveTypeId() {
        return colectiveTypeId;
    }

    public void setColectiveTypeId(Long colectiveTypeId) {
        this.colectiveTypeId = colectiveTypeId;
    }

    public String getColectiveTypeDescription() {
        return colectiveTypeDescription;
    }

    public void setColectiveTypeDescription(String colectiveTypeDescription) {
        this.colectiveTypeDescription = colectiveTypeDescription;
    }

    public String getColectiveTypeKey() {
        return colectiveTypeKey;
    }

    public void setColectiveTypeKey(String colectiveTypeKey) {
        this.colectiveTypeKey = colectiveTypeKey;
    }

    @Override
    public int compareTo(ColectiveTypeEntity obj) {
        int result = -1;
        if (this.getColectiveTypeId() != null && obj.getColectiveTypeId() != null) {
            result = this.getColectiveTypeId().compareTo(obj.getColectiveTypeId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof ColectiveTypeEntity
                && this.colectiveTypeId != null
                && ((ColectiveTypeEntity) obj).colectiveTypeId != null) {
            result = this.colectiveTypeId.equals(
                    ((ColectiveTypeEntity) obj).colectiveTypeId);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.colectiveTypeId != null
                ? this.colectiveTypeId.hashCode() : 0);
        hash = 37 * hash + (this.colectiveTypeKey != null
                ? this.colectiveTypeKey.hashCode() : 0);
        return hash;
    }
}
