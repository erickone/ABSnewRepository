package com.abs.siif.planning.utility;

import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import java.util.Comparator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class ObjectiveLevelComparatorDesc implements Comparator {

    @Override
    public int compare(Object anObject1, Object anObject2) {
        ObjectiveLevelEntity myObjectiveLevel1 = (ObjectiveLevelEntity) anObject1;
        ObjectiveLevelEntity myObjectiveLevel2 = (ObjectiveLevelEntity) anObject2;

        return Short.compare(myObjectiveLevel1.getObjectiveLevel(), myObjectiveLevel2.getObjectiveLevel());
    }
}
