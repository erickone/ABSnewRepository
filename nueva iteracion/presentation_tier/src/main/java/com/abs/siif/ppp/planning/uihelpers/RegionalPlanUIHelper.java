/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalPlanEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class RegionalPlanUIHelper {

    public static RegionalPlanDataModel populateData(Collection<RegionalPlanEntity> anRegionaPlans) {

        List<RegionalPlanEntity> myRegions = new ArrayList<RegionalPlanEntity>(anRegionaPlans);
        RegionalPlanDataModel myDataModel = new RegionalPlanDataModel(myRegions);

        return myDataModel;
    }

    public static RegionalPlanEntity mapperRegionalEntity(
            Long aRegionPlanId,
            String anOjectiveDescription,
            Long aRegionalClassifierId) {


        RegionalPlanEntity myRegionalPlan = new RegionalPlanEntity();
        RegionalClassifierEntity myRegionalClassifier = new RegionalClassifierEntity();
        myRegionalPlan.setRegionalPlanId(aRegionPlanId);
        myRegionalClassifier.setRegionalClassifierId(aRegionalClassifierId);
        myRegionalPlan.setRegionalPlanObjective(anOjectiveDescription);
        myRegionalPlan.setRegionalClassifierPlan(myRegionalClassifier);

        return myRegionalPlan;
    }

    public static int getIndexEditItem(List<RegionalPlanEntity> anEntities, RegionalPlanEntity anEntity) {
        return anEntities.indexOf(anEntity);
    }

    public static List<RegionalPlanEntity> mapperIdentities(RegionalPlanEntity[] anRegionalPlans) {
        List<RegionalPlanEntity> myIdentities = new ArrayList<RegionalPlanEntity>();
        myIdentities.addAll(Arrays.asList(anRegionalPlans));

        return myIdentities;
    }
}