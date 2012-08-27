package com.abs.siif.planning.management;

import com.abs.siif.planning.dao.ObjectiveLevelDao;
import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import com.abs.siif.planning.exception.ObjectiveLevelBusinessException;
import com.abs.siif.planning.validator.ObjectiveLevelBusinessValidator;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Service("objectiveLevelManagement")
public class ObjectiveLevelManagementImpl implements ObjectiveLevelManagement,Serializable {

    @Resource(name = "objectiveLevelBusinessValidator")
    private transient ObjectiveLevelBusinessValidator theirObjectiveLevelBusinessValidator;
    @Resource(name = "objectiveLevelDao")
    private transient ObjectiveLevelDao theirObjectiveLevelDao;
    
    private ObjectiveLevelManagementImpl() {
    }

    ObjectiveLevelManagementImpl(ObjectiveLevelBusinessValidator anObjectiveLevelBusinessValidator, ObjectiveLevelDao anObjectiveLevelDao) {
        this.theirObjectiveLevelBusinessValidator = anObjectiveLevelBusinessValidator;
        this.theirObjectiveLevelDao = anObjectiveLevelDao;
    }

    @Override
    public List<ObjectiveLevelEntity> getAllObjectiveLevels() {
        return theirObjectiveLevelDao.getAllObjectiveLevels();
    }

    @Override
    public Long saveOrUpdate(ObjectiveLevelEntity anObjectiveLevel) {
        if (!theirObjectiveLevelBusinessValidator.validateObjectiveLevelInSequence(anObjectiveLevel)) {
            throw new ObjectiveLevelBusinessException("ppp.planning.notSecuency");
        }

        if (theirObjectiveLevelBusinessValidator.existObjectiveLevelWithRelationshipUEG(anObjectiveLevel)) {
            throw new ObjectiveLevelBusinessException("ppp.planning.uniqueUEG");
        }
        Long myKey = theirObjectiveLevelDao.saveOrUpdate(anObjectiveLevel);

        return myKey;
    }

    @Override
    public void delete(List<ObjectiveLevelEntity> anObjectiveLevels) {
        if (!theirObjectiveLevelBusinessValidator.canDeleteObjectiveLevels(anObjectiveLevels)) {
            throw new ObjectiveLevelBusinessException("ppp.planning.notSecuencyDelete");
        }

        theirObjectiveLevelDao.delete(anObjectiveLevels);

    }

    @Override
    public ObjectiveLevelEntity getObjectiveLevelByLevel(short aLevel) {
        return theirObjectiveLevelDao.getObjectiveLevelByLevel(aLevel);
    }
    }
