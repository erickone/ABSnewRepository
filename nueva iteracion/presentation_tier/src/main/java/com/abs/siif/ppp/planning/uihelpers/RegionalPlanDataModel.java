/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.planning.entities.RegionalPlanEntity;
import java.util.Collections;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class RegionalPlanDataModel extends ListDataModel<RegionalPlanEntity>
        implements SelectableDataModel<RegionalPlanEntity> {

    public RegionalPlanDataModel() {
    }

    public RegionalPlanDataModel(List<RegionalPlanEntity> aDataModel) {
        super(aDataModel);
    }

    @Override
    public Object getRowKey(RegionalPlanEntity anEntity) {
        return anEntity.getRegionalPlanId();
    }

    @Override
    public RegionalPlanEntity getRowData(String arowKey) {
        List<RegionalPlanEntity> myRegions = (List<RegionalPlanEntity>) getWrappedData();
        
        for(RegionalPlanEntity myRegion : myRegions) {  
            if(myRegion.getRegionalPlanId().equals(Long.parseLong(arowKey)))  
                return myRegion;  
        }  
        return null;
       
    }
}
