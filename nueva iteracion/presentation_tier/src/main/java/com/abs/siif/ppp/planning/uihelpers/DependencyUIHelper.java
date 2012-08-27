/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DependencyUIHelper
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.common.dto.TreeNodeDto;
import com.abs.siif.common.entities.ColectiveEntity;
import com.abs.siif.common.entities.ColectiveTypeEntity;
import com.abs.siif.common.entities.EmployeeEntity;
import com.abs.siif.common.entities.StatusColectiveEntity;
import com.abs.siif.planning.entities.AdministrativeClassifierTypeEntity;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import com.abs.siif.ppp.planning.controller.AdministrativeClassifierController;
import com.abs.siif.utility.SaveType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class DependencyUIHelper {

    public static List<String> convertOptionDataInSelectedOptions(DependenceEntity aDependency) {
        List<String> myOptions = new ArrayList<String>();
        if (aDependency.isDependencyIsExecute()) {
            myOptions.add("1");
        }
        if (aDependency.isDependencyIsNormative()) {
            myOptions.add("2");
        }

        return myOptions;
    }

    public static DependenceEntity MapperAtributtesInEntity(AdministrativeClassifierController aThis) {
        DependenceEntity myDependency = new DependenceEntity();
        ColectiveEntity myColective = new ColectiveEntity();
        DependenceLevelEntity myLevelEntity = new DependenceLevelEntity();
        TreeNodeDto mySelectedNode = (TreeNodeDto) aThis.getTheirSelectedNode().getData();
        DependenceEntity myFather = null;
        short myLevel = (short) (mySelectedNode.getItsLevel());
        java.util.Date myCurrentDate = new Date();
        Long myDependencyLevelId = -1L;

        if (aThis.getTheirSaveType() == SaveType.Save) {
            myDependencyLevelId = aThis.getItsDependenceNextLevel();
            //Cuando se agrega un nuevo elemento, se incrementa el nivel 
            myLevel = (short) (myLevel + 1);
            if (!mySelectedNode.isItsIsRoot()) {
                myFather = new DependenceEntity();
                myFather.setDependenceId(mySelectedNode.getItsIdentity());
            }
            myColective.setColectiveRegisterDate(myCurrentDate);

        } else {
            myColective.setColectiveRegisterDate(aThis.getTheirRegisterDate());
            myDependencyLevelId = mySelectedNode.getItsLevelId();
            myDependency.setDependenceId(myDependencyLevelId);
            if (mySelectedNode.getItsParent() > -1L) {
                myFather = new DependenceEntity();
                myFather.setDependenceId(
                        mySelectedNode.getItsParent());
            }
        }

        myColective.setColectiveLastModifiedDate(myCurrentDate);
        myDependency.setFather(myFather);


        myLevelEntity.setDependenceLevelId(myDependencyLevelId);
        myLevelEntity.setDependenceLevel(myLevel);



        if (aThis.getItsCurrentColectiveId() != -1L) {
            myColective.setColectiveId(aThis.getItsCurrentColectiveId());
        }

        if (aThis.getItsCurrentDependencyId() != -1L) {
            myDependency.setDependenceId(aThis.getItsCurrentDependencyId());
        }

        ColectiveTypeEntity myColectiveType = new ColectiveTypeEntity();
        myColectiveType.setColectiveTypeId(aThis.getItsColectiveTypeId());

        if (!aThis.getTheirSelectedStatusColective().equals("")) {
            StatusColectiveEntity myStatusColective = new StatusColectiveEntity();
            myStatusColective.setStatusColectiveId(
                    Long.valueOf(
                    aThis.getTheirSelectedStatusColective()));
            myColective.setStatusColective(myStatusColective);
        }


        myColective.setColectiveType(myColectiveType);

        if (!aThis.getTheirSelectedResponsible().equals("")) {
            EmployeeEntity myResponsible = new EmployeeEntity();
            myResponsible.setEmployeeId(Long.valueOf(aThis.getTheirSelectedResponsible()));
            myDependency.setResponsible(myResponsible);
        }

        if (!aThis.getTheirSelectedAdmClassType().equals("")) {
            AdministrativeClassifierTypeEntity myClassifier = new AdministrativeClassifierTypeEntity();
            myClassifier.setAdminClassTypeId(
                    Long.valueOf(aThis.getTheirSelectedAdmClassType()));
            myDependency.setAdministrativeClassifierType(myClassifier);
        }

        myDependency.setDependencyIsExecute(
                aThis.getTheirSelectedOptions().contains("1"));

        myDependency.setDependencyIsNormative(
                aThis.getTheirSelectedOptions().contains("2"));

        myColective.setColectiveKey(aThis.getTheirColectiveKey());
        myColective.setColectiveName(aThis.getTheirColectiveShortName());
        myDependency.setDependenceKey(aThis.getTheirColectiveKey());
        myDependency.setDependenceDescription(aThis.getTheirColectiveName());
        myDependency.setDependencyDescLong(aThis.getTheirDescLong());
        myDependency.setDependencyShortName(aThis.getTheirColectiveShortName());
        myDependency.setDependencyFundamental(aThis.getTheirColectiveFundament());
        myDependency.setDependenceLevel(myLevelEntity);
        myDependency.setColective(myColective);

        String hiddenKey=myDependency.getDependenceKey().replace(" ", "");
        myDependency.setDependenceHidden(hiddenKey);
        
        return myDependency;
    }
}
