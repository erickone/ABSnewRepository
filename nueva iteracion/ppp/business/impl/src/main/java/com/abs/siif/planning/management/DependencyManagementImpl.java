/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependencyManagement
 *  Purpose:  [Implementa las reglas de negocio para el manejo del clasificador
 * de dependencias]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.management;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.budget.entities.BudgetKeyDefinitionEntity;
import com.abs.siif.budget.management.BudgetKeyDefinitionManagement;
import com.abs.siif.common.ColectiveTypeManagement;
import com.abs.siif.common.EmployeeManagement;
import com.abs.siif.common.StatusColectiveManagement;
import com.abs.siif.common.entities.ColectiveTypeEntity;
import com.abs.siif.common.entities.EmployeeEntity;
import com.abs.siif.common.entities.StatusColectiveEntity;
import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.entities.AdministrativeClassifierTypeEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.validator.DependencyValidator;
import java.util.*;
import javax.annotation.Resource;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("dependencyManagement")
public class DependencyManagementImpl implements DependencyManagement {

    /**
     * Retorna las listas de soporte para el manejo del clasificador
     * administrativo,regresa los estatus de colectiva, tipos de colectiva y
     * tipos de clasificacion de dependencia para accesar a las distintas lista
     * siga la siguiente guia
     *
     * @return "StatusColectives" retorna los estatus de la colectiva
     * "AdminClassTypes" retorna los tipos de clasificacion dependencia
     * "ColectiveTypes" retorna los tipos de colectiva.
     */
    @Resource(name = "statusColectiveManagement")
    private StatusColectiveManagement itsStatusColectiveManagement;
    @Resource(name = "colectiveTypeManagement")
    private ColectiveTypeManagement itsColectiveTypeManagement;
    @Resource(name = "administrativeClassifierTypeManagement")
    private AdministrativeClassifierTypeManagement itsAdministrativeClassifierTypeManagement;
    @Resource(name = "DependenceDao")
    private DependenceDao itsDependenceDao;
    @Resource(name = "employeeManagement")
    private EmployeeManagement itsEmployeeManagement;
    @Resource(name = "dependencyValidator")
    private DependencyValidator itsDependencyValidator;
    @Resource(name = "budgetKeyDefinitionManagement")
    private BudgetKeyDefinitionManagement itsBudgetKeyDefinitionManagement;

    @Override
    public Map<String, List<?>> getSupportList() {
        Map mySupportList = new HashMap();


        BudgetKeyDefinitionEntity myDefinition = new BudgetKeyDefinitionEntity();
        myDefinition.setPresupuestalKeyDefinitionYear(2013);
        Collection<EmployeeEntity> myEmployees = itsEmployeeManagement.getEmployeeByDefCve(myDefinition);

        Collection<StatusColectiveEntity> myStatus = itsStatusColectiveManagement.getStatusColectives();
        Collection<ColectiveTypeEntity> myColectiveTypes =
                itsColectiveTypeManagement.getColectiveTypes();

        Collection<AdministrativeClassifierTypeEntity> myAdminClassTypes =
                itsAdministrativeClassifierTypeManagement.getAdministrativeClassifierTypes();
        mySupportList.put("StatusColectives", new ArrayList(myStatus));
        mySupportList.put("AdminClassTypes", new ArrayList(myAdminClassTypes));
        mySupportList.put("ColectiveTypes", new ArrayList(myColectiveTypes));
        mySupportList.put("Responsibles", new ArrayList(myEmployees));
        return mySupportList;

    }

    @Transactional(readOnly = true)
    @Override
    public DependenceEntity getDependecyById(DependenceEntity anEntity) {
        return itsDependenceDao.getDependencyByEdit(anEntity);
    }

    @Override
    public DependenceEntity persistDependency(DependenceEntity anEntity) {
        if (itsDependencyValidator.notEmptyAnyAttributes(anEntity)) {
            throw new BaseBusinessException("ppp.progr.AdminClassifierRequired");
        }

        int myYear = Integer.valueOf(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString());
        BudgetKeyDefinitionEntity myDefinition = itsBudgetKeyDefinitionManagement.getBudgetDefinitionByYear(myYear);

        if (myDefinition == null) {
            throw new BaseBusinessException("ppp.settings.execp");
        }

        anEntity.setBudgetKeyDefinitionDependency(myDefinition);

        if (itsDependencyValidator.notExceedLastLevel(anEntity)) {
            throw new BaseBusinessException("ppp.progr.AdminClassifierNotNewLevel");
        }

        if (anEntity.getDependenceId() != null) {
            anEntity = itsDependenceDao.persist(anEntity);
        } else {
            anEntity = itsDependenceDao.save(anEntity);
        }
        return anEntity;
    }

    @Override
    public void deleteDependency(DependenceEntity anEntity) {
        itsDependenceDao.delete(anEntity);
    }
}
