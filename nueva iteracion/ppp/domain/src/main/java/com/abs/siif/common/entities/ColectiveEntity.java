/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ColectiveEntity
 *  Purpose:  [Mapea las colectivas a utilizar dentro del SIIF]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.entities;

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.RegionalClassifierEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name = "siifabscolectiva")
public class ColectiveEntity implements
        Comparable<ColectiveEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idcolectiva")
    private Long colectiveId;
    @Column(name = "clave", length = 50, nullable = false)
    private String colectiveKey;
    @Column(name = "nombre", length = 100, nullable = false)
    private String colectiveName;
    @Column(name = "apematerno", length = 100)
    private String colectiveMiddleName;
    @Column(name = "apepaterno", length = 100)
    private String colectiveLastName;
    @Column(name = "fechaalta", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date colectiveRegisterDate;
    @Column(name = "fechaultmodif")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date colectiveLastModifiedDate;
    @Column(name = "direccion", length = 100)
    private String colectiveAddress;
    @Column(name = "telefono", length = 20)
    private String colectivePhone;
    @Column(name = "email", length = 50)
    private String colectiveEmail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idclasifregional", nullable = true)
    private RegionalClassifierEntity regionalClassifier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtipocolectiva")
    private ColectiveTypeEntity colectiveType;
    @ManyToOne
    @JoinColumn(name = "idestcolectiva")
    private StatusColectiveEntity statusColective;
    @OneToOne(mappedBy = "colective", fetch = FetchType.LAZY)
    private DependenceEntity dependencyColective;
    @OneToMany(mappedBy = "colectiveEmployee", fetch = FetchType.LAZY)
    private List<EmployeeEntity> employees;

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public DependenceEntity getDependencyColective() {
        return dependencyColective;
    }

    public void setDependencyColective(DependenceEntity dependencyColective) {
        this.dependencyColective = dependencyColective;
    }

    public StatusColectiveEntity getStatusColective() {
        return statusColective;
    }

    public void setStatusColective(StatusColectiveEntity statusColective) {
        this.statusColective = statusColective;
    }

    public ColectiveTypeEntity getColectiveType() {
        return colectiveType;
    }

    public void setColectiveType(ColectiveTypeEntity colectiveType) {
        this.colectiveType = colectiveType;
    }

    public String getColectiveMiddleName() {
        return colectiveMiddleName;
    }

    public void setColectiveMiddleName(String colectiveMiddleName) {
        this.colectiveMiddleName = colectiveMiddleName;
    }

    public RegionalClassifierEntity getRegionalClassifier() {
        return regionalClassifier;
    }

    public void setRegionalClassifier(RegionalClassifierEntity regionalClassifier) {
        this.regionalClassifier = regionalClassifier;
    }

    public String getColectiveAddress() {
        return colectiveAddress;
    }

    public void setColectiveAddress(String colectiveAddress) {
        this.colectiveAddress = colectiveAddress;
    }

    public String getColectiveEmail() {
        return colectiveEmail;
    }

    public void setColectiveEmail(String colectiveEmail) {
        this.colectiveEmail = colectiveEmail;
    }

    public Date getColectiveLastModifiedDate() {
        return colectiveLastModifiedDate;
    }

    public void setColectiveLastModifiedDate(Date colectiveLastModifiedDate) {
        this.colectiveLastModifiedDate = colectiveLastModifiedDate;
    }

    public String getColectiveLastName() {
        return colectiveLastName;
    }

    public void setColectiveLastName(String colectiveLastName) {
        this.colectiveLastName = colectiveLastName;
    }

    public String getColectiveMaidenName() {
        return colectiveMiddleName;
    }

    public void setColectiveMaidenName(String colectiveMaidenName) {
        this.colectiveMiddleName = colectiveMaidenName;
    }

    public String getColectiveName() {
        return colectiveName;
    }

    public void setColectiveName(String colectiveName) {
        this.colectiveName = colectiveName;
    }

    public String getColectivePhone() {
        return colectivePhone;
    }

    public void setColectivePhone(String colectivePhone) {
        this.colectivePhone = colectivePhone;
    }

    public Date getColectiveRegisterDate() {
        return colectiveRegisterDate;
    }

    public void setColectiveRegisterDate(Date colectiveRegisterDate) {
        this.colectiveRegisterDate = colectiveRegisterDate;
    }

    public Long getColectiveId() {
        return colectiveId;
    }

    public void setColectiveId(Long colectiveId) {
        this.colectiveId = colectiveId;
    }

    public String getColectiveKey() {
        return colectiveKey;
    }

    public void setColectiveKey(String colectiveKey) {
        this.colectiveKey = colectiveKey;
    }

    @Override
    public int compareTo(ColectiveEntity obj) {
        int result = -1;
        if (this.getColectiveId() != null && obj.getColectiveId() != null) {
            result = this.getColectiveId().compareTo(obj.getColectiveId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof StatusColectiveEntity
                && this.colectiveId != null
                && ((ColectiveEntity) obj).colectiveId != null) {
            result = this.colectiveId.equals(
                    ((ColectiveEntity) obj).colectiveId);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.colectiveId != null
                ? this.colectiveId.hashCode() : 0);
        hash = 37 * hash + (this.colectiveKey != null
                ? this.colectiveKey.hashCode() : 0);
        return hash;
    }

    public String getCompositeName() {
        String myName = this.colectiveName;
        String myMiddleName = this.colectiveMiddleName;
        String myLastName = this.colectiveLastName;

        String myCompositeName = new StringBuffer().append(myName).append(" ").append(myMiddleName).append(" ").append(myLastName).toString();

        return myCompositeName;
    }
}
