/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  EmployeeEntity
 *  Purpose:  [ Mapea la entidad de dominio del empleado]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.entities;

import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name = "siifabsempleado")
public class EmployeeEntity implements
        Comparable<EmployeeEntity>,
        Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idempleado")
    private Long employeeId;
    @Column(name = "clave", length = 100, nullable = false)
    private String employeeKey;
    @ManyToOne
    @JoinColumn(name = "idcolectiva", nullable = false)
    private ColectiveEntity colectiveEmployee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddependencia", nullable = false)
    private DependenceEntity dependencyEmployee;
    @OneToMany(mappedBy = "responsible")
    private Set<DependenceEntity> dependenciesResponsible;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddefcvepresupuestal", nullable = false)
    private BudgetKeyDefinitionEntity budgetKeyDefinitionEntityEmployee;

    public Set<DependenceEntity> getDependenciesResponsible() {
        return dependenciesResponsible;
    }

    public void setDependenciesResponsible(Set<DependenceEntity> dependenciesResponsible) {
        this.dependenciesResponsible = dependenciesResponsible;
    }

    public BudgetKeyDefinitionEntity getBudgetKeyDefinitionEntityEmployee() {
        return budgetKeyDefinitionEntityEmployee;
    }

    public void setBudgetKeyDefinitionEntityEmployee(BudgetKeyDefinitionEntity budgetKeyDefinitionEntityEmployee) {
        this.budgetKeyDefinitionEntityEmployee = budgetKeyDefinitionEntityEmployee;
    }

    public ColectiveEntity getColectiveEmployee() {
        return colectiveEmployee;
    }

    public void setColectiveEmployee(ColectiveEntity colectiveEmployee) {
        this.colectiveEmployee = colectiveEmployee;
    }

    public DependenceEntity getDependencyEmployee() {
        return dependencyEmployee;
    }

    public void setDependencyEmployee(DependenceEntity dependencyEmployee) {
        this.dependencyEmployee = dependencyEmployee;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeKey() {
        return employeeKey;
    }

    public void setEmployeeKey(String employeeKey) {
        this.employeeKey = employeeKey;
    }

    @Override
    public int compareTo(EmployeeEntity obj) {
        int result = -1;
        if (this.getEmployeeId() != null && obj.getEmployeeId() != null) {
            result = this.getEmployeeId().compareTo(obj.getEmployeeId());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof EmployeeEntity
                && this.employeeId != null
                && ((EmployeeEntity) obj).employeeId != null) {
            result = this.employeeId.equals(
                    ((EmployeeEntity) obj).employeeId);
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.employeeId != null
                ? this.employeeId.hashCode() : 0);
        hash = 37 * hash + (this.employeeKey != null
                ? this.employeeKey.hashCode() : 0);
        return hash;
    }

    public String compositeName() {
        return this.colectiveEmployee.getCompositeName();
    }
}
