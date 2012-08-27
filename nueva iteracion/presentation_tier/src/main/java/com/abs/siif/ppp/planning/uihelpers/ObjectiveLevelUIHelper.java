/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.planning.uihelpers;

import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import com.abs.siif.ppp.planning.exception.ObjectiveLevelControllerException;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.utility.SaveType;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan Antonio Zavala Aguilar / Erick Leija Cardenas
 */
@Component("objectiveLevelComponent")
public class ObjectiveLevelUIHelper extends SIIFControllerBase{

    
    public static ObjectiveLevelEntity mapperObjectiveLevelEntity(Long anIdentity,
            Date aYear, String aLevel, String aLevelKey, String aDescription,
            boolean aHasIndicator, boolean aHasEUG, SaveType aSaveType)  {
        
        validateObjectiveLevelData(aLevel, aLevelKey);
        
        
        ObjectiveLevelEntity myEntity = new ObjectiveLevelEntity();

        if (aSaveType == SaveType.Update) {
            myEntity.setObjectiveLevelId(anIdentity);
        }

        myEntity.setObjectiveLevel(Short.valueOf(aLevel));
        myEntity.setObjectiveLevelDescription(aDescription.trim());
        myEntity.setObjectiveLevelHasIndicator(aHasIndicator);
        myEntity.setObjectiveLevelShowBudgetKey(aHasEUG);
        myEntity.setObjectiveLevelKey(aLevelKey.trim());

        return myEntity;
    }

    private static void validateObjectiveLevelData(String aLevel, String aLevelkey)   {
        //TODO: queda pendiente verificar el lugar en donde se atrapa la exepcion.
        
        if (aLevel.trim().toString().equals("")) { 
            throw new ObjectiveLevelControllerException("ppp.planning.excpLevelEmpty");
        }        
        if (aLevelkey.trim().toString().equals("")) {
            throw new ObjectiveLevelControllerException("ppp.planning.excpKeyEmpty");
        }
    }

    public static int getIndexEditItem(List<ObjectiveLevelEntity> anEntities, ObjectiveLevelEntity anEntity) {
        return Collections.binarySearch(anEntities, anEntity);
    }

    public static ObjectiveLevelDataModel populateObjectiveLevels(List<ObjectiveLevelEntity> anObjectiveLevels) {
        ObjectiveLevelDataModel myDataModel = new ObjectiveLevelDataModel(anObjectiveLevels);

        return myDataModel;
    }

    public static List<ObjectiveLevelEntity> mapperIdentities(ObjectiveLevelEntity[] itsSelectedObjectives) {
        List<ObjectiveLevelEntity> myIdentities = new ArrayList<ObjectiveLevelEntity>();
        myIdentities.addAll(Arrays.asList(itsSelectedObjectives));

        return myIdentities;
    }
}
