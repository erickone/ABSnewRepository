/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.budget.entities.PreInvRequestEntity;
import com.abs.siif.budget.entities.RequestUploadFilesEntity;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jacob.flores
 */
@Repository("PreInvRequestDaoImpl")
public class PreInvRequestDaoImpl extends SIIFBaseDaoImpl<PreInvRequestEntity, Long>
implements PreInvRequestDao
{
    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory()
    {
        return theirSessionFactory;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void savePreInvRequest(PreInvRequestEntity aPreInvRequestEntity)
    {
      super.save(aPreInvRequestEntity);
    }
    
    @Transactional(readOnly = false)
    @Override
    public Collection<PreInvRequestEntity> getPreInvRequestByInvPreFileId(Long aInvPrefileId)
    {
      String myQueryHQL = "select distinct bit from PreInvRequestEntity bit "
                + " left join fetch bit.federalDependenceId FederalDependence"
                + " left join fetch bit.preInvRequestPreFile PreFile"
                + " left join fetch bit.uploadedFiles"
                + " where PreFile.invPreFileId = :aInvPrefileId"
                + " order by bit.preInvRequestId asc ";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("aInvPrefileId", aInvPrefileId);
        return myQuery.list();
    }

    @Transactional(readOnly = false)
    @Override
    public boolean deleteUploadedFile(RequestUploadFilesEntity anEntity, Long idReq)
    {
        boolean isOK = false;
        try{
        String myQueryString = "DELETE FROM siifppparchivoreqpreinv "
                    + "WHERE idarchivo = :idFile "
                    + "and idreqpreinversion = :idReq ";

            SQLQuery myQuery = theirSessionFactory.getCurrentSession().createSQLQuery(myQueryString);
            myQuery.setLong("idFile", anEntity.getRequestUpLoadFileId());
            myQuery.setLong("idReq", idReq);
            myQuery.executeUpdate();
            isOK = true;
        }
        catch(Exception ex){
            Logger.getLogger(PreInvRequestDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isOK;
    }
}
