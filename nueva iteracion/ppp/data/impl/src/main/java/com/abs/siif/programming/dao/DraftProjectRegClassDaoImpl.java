/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.DraftProjRegionalClassifierEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Israel Ruiz
 */
@Repository("draftProjectRegClassDao")
public class DraftProjectRegClassDaoImpl extends SIIFBaseDaoImpl<DraftProjRegionalClassifierEntity, Long> implements
        DraftProjectRegClassDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DraftProjRegionalClassifierEntity> findUBicationsDraftProject(
            DraftProjectEntity draftProjEntity) {

        String hqlUpdate = "from DraftProjRegionalClassifierEntity "
                + "projectEntity "
                + " where  projectEntity."
                + "draftProjRegClassifDraftProject.draftProjectId ="
                + draftProjEntity.getDraftProjectId();
        return this.find(hqlUpdate);

    }
    @Transactional(readOnly = false)
    @Override
    public void deleteUbicationDraftProj(Long draftProjectId) {
        String hqlUpdate = "delete DraftProjRegionalClassifierEntity"
                + " entity where entity."
                + "draftProjRegClassifDraftProject.draftProjectId = "
                + ":idDraftProject";

        getTheirSessionFactory().getCurrentSession().
                createQuery(hqlUpdate).
                setLong("idDraftProject", draftProjectId).executeUpdate();

    }
}
