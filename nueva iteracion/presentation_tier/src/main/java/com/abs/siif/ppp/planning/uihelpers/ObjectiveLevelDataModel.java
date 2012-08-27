/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class ObjectiveLevelDataModel extends ListDataModel<ObjectiveLevelEntity>
        implements Serializable, SelectableDataModel<ObjectiveLevelEntity> {

    public ObjectiveLevelDataModel() {
    }

    public ObjectiveLevelDataModel(List<ObjectiveLevelEntity> aDataModel) {
        super(aDataModel);
    }

    @Override
    public Object getRowKey(ObjectiveLevelEntity anEntity) {
        return anEntity.getObjectiveLevelId();
    }

    @Override
    public ObjectiveLevelEntity getRowData(String arowKey) {
        List<ObjectiveLevelEntity> myObjectives = (List<ObjectiveLevelEntity>) getWrappedData();
        ObjectiveLevelEntity myEntity = new ObjectiveLevelEntity();
        myEntity.setObjectiveLevelId(Long.parseLong(arowKey));
        int myIndex = myObjectives.indexOf(myEntity);
        
        return myObjectives.get(myIndex);
    }
      
}
