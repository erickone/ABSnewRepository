/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AdministrativeClassifierStatusEntity
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name = "siifabsestcolectiva")
public class StatusColectiveEntity  implements  Serializable,
        Comparable<StatusColectiveEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idestatuscolectiva")
    private Long statusColectiveId;
    @Column(name = "Descripcion", length = 100, nullable = false)
    private String statusColectiveDescription;
    @OneToMany(mappedBy = "statusColective", fetch = FetchType.LAZY)
    private List<ColectiveEntity> colectives;

    public List<ColectiveEntity> getColectives() {
        return colectives;
    }

    public void setColectives(List<ColectiveEntity> colectives) {
        this.colectives = colectives;
    }

    public String getStatusColectiveDescription() {
        return statusColectiveDescription;
    }

    public void setStatusColectiveDescription(String statusColectiveDescription) {
        this.statusColectiveDescription = statusColectiveDescription;
    }

    public Long getStatusColectiveId() {
        return statusColectiveId;
    }

    public void setStatusColectiveId(Long statusColectiveId) {
        this.statusColectiveId = statusColectiveId;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof StatusColectiveEntity
                && this.statusColectiveId != null
                && ((StatusColectiveEntity) obj).statusColectiveId != null) {
            result = this.statusColectiveId.equals(
                    ((StatusColectiveEntity) obj).statusColectiveId);
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.statusColectiveId != null
                ? this.statusColectiveId.hashCode() : 0);
        hash = 37 * hash + (this.statusColectiveDescription != null
                ? this.statusColectiveDescription.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(StatusColectiveEntity anOtherObject) {
          int result = -1;
        if (this.getStatusColectiveId() != null && anOtherObject.getStatusColectiveId() != null) {
            result = this.getStatusColectiveId().compareTo(anOtherObject.getStatusColectiveId());
        }
        return result;
    }
}
