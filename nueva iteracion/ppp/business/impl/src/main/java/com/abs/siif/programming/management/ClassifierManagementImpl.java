/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.dao.ClassifierDao;
import com.abs.siif.programming.entities.ClassifierEntity;
import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author FENIX-02
 */
@Service("classifierManagement")
public class ClassifierManagementImpl implements ClassifierManagement, Serializable
{
    @Resource(name="classifierDaoImpl")
    private ClassifierDao itsClassifierDao;

    @Override
    public ClassifierEntity saveClassifier(ClassifierEntity aClassifierEntity)
    {
        ClassifierEntity savedClassifierEntity;
        if (aClassifierEntity.getClassifierId() != null)
        {
            savedClassifierEntity = itsClassifierDao.merge(aClassifierEntity);
        }
        else{
            savedClassifierEntity = itsClassifierDao.save(aClassifierEntity);
        }
        return savedClassifierEntity;
    }

    @Override
    public ClassifierEntity getClassifierByDraftProyectId(Long aDraftProyectId)
    {
        ClassifierEntity savedClassifierEntity;
        savedClassifierEntity = itsClassifierDao.getClassifierByDraftProyectId(aDraftProyectId);
        return savedClassifierEntity;
    }
    
}
