/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.ClassifierEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FENIX-02
 */
@Repository("classifierDaoImpl")
public class ClassifierDaoImpl extends SIIFBaseDaoImpl<ClassifierEntity, Long> implements ClassifierDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public ClassifierEntity saveClassifier(ClassifierEntity aClassifierEntity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional(readOnly = true)
    @Override
    public ClassifierEntity getClassifierByDraftProyectId(Long aDraftProyectId) {
        String myQueryString = "from ClassifierEntity as classifierEnt"
                + " left join fetch classifierEnt.finalidad fin "
                + " left join fetch classifierEnt.funcion fun"
                + " left join fetch classifierEnt.subFuncion sub"
                + " where classifierEnt.classifierDraftProject."
                + "draftProjectId = :idDraftProject ";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString).
                setLong("idDraftProject", aDraftProyectId);


        List myList = myQuery.list();
        ClassifierEntity myClassifierEntity = new ClassifierEntity();
        if (myList.size() > 0) {
            myClassifierEntity = (ClassifierEntity) myList.get(0);
        }
        return myClassifierEntity;
    }
}
