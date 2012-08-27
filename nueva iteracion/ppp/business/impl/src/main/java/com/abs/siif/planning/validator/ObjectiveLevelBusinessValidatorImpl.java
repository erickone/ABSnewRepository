package com.abs.siif.planning.validator;

import com.abs.siif.planning.dao.ObjectiveLevelDao;
import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import com.abs.siif.planning.utility.ObjectiveLevelComparatorDesc;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Component("objectiveLevelBusinessValidator")
public class ObjectiveLevelBusinessValidatorImpl implements ObjectiveLevelBusinessValidator {

    @Resource(name = "objectiveLevelDao")
    private ObjectiveLevelDao theirObjectiveLevelDao;

    private ObjectiveLevelBusinessValidatorImpl() {
    }

    ObjectiveLevelBusinessValidatorImpl(ObjectiveLevelDao anObjectiveLevelDao) {
        this.theirObjectiveLevelDao = anObjectiveLevelDao;
    }

    @Override
    public boolean validateObjectiveLevelInSequence(ObjectiveLevelEntity anObjectiveLevel) {
        boolean myInSequence = Boolean.TRUE;
        if (anObjectiveLevel.getObjectiveLevelId() == null) {
            short myLastLevel = theirObjectiveLevelDao.getLastObjectiveLevel();
            myLastLevel = (short) (myLastLevel + 1);
            myInSequence = (myLastLevel == anObjectiveLevel.getObjectiveLevel());
        }
        return myInSequence;
    }

    @Override
    public boolean existObjectiveLevelWithRelationshipUEG(ObjectiveLevelEntity anObjectiveLevel) {
        boolean myExistObjectiveLevel = Boolean.FALSE;
        ObjectiveLevelEntity myObjectiveLevel = null;

        if (anObjectiveLevel.getObjectiveLevelShowBudgetKey()) {
            myObjectiveLevel = theirObjectiveLevelDao.getObjectiveLevelRelationshipUEG();
            myExistObjectiveLevel = ((myObjectiveLevel != null)
                    && (!myObjectiveLevel.getObjectiveLevelId().equals(anObjectiveLevel.getObjectiveLevelId())));
        }

        return myExistObjectiveLevel;
    }

    @Override
    public boolean canDeleteObjectiveLevels(List<ObjectiveLevelEntity> anObjectiveLevels) {
        boolean myCanDelete = Boolean.TRUE;
        int myIndex = anObjectiveLevels.size() - 1;
        int myBeforeLevel = -1;

        if (myIndex > 0) {
            Collections.sort(anObjectiveLevels, new ObjectiveLevelComparatorDesc());
            myBeforeLevel = anObjectiveLevels.get(myIndex).getObjectiveLevel();
        } else {
            myBeforeLevel = theirObjectiveLevelDao.getLastObjectiveLevel();
        }

        while ((myCanDelete) && (myIndex >= 0)) {
            myCanDelete = (myBeforeLevel == anObjectiveLevels.get(myIndex).getObjectiveLevel());
            myBeforeLevel--;
            myIndex--;
        }

        return myCanDelete;
    }
}