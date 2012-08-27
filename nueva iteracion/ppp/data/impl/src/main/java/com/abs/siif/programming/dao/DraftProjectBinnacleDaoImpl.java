/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Repository("draftProjectBinnacleDaoImpl")
public class DraftProjectBinnacleDaoImpl extends SIIFBaseDaoImpl<DraftProjectBinnacleEntity, Long>
        implements DraftProjectBinnacleDao
{
  @Resource(name="draftProjectDaoImpl")
  private DraftProjectDao itsDraftProjectDao;
  
    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = false)
    @Override
    public void saveAll(Collection<DraftProjectBinnacleEntity> anEntities) {
        super.saveAll(anEntities);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<DraftProjectBinnacleEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    @Transactional(readOnly = true)
    @Override
    public DraftProjectBinnacleEntity getDraftProjectBinnacleById(Long anIdentity) {
        return findById(anIdentity, Boolean.TRUE);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<DraftProjectBinnacleEntity> getDraftProjectBinnacleByDraftProjectId(Long anIdentity) {
        String myQueryHQL = "select distinct bit from DraftProjectBinnacleEntity bit "
                + " left join fetch bit.draftProject dp"
                + " left join fetch bit.userBinnacle usr"
                + " left join fetch bit.draftProjectStatusBinnacle dps"
                + " where dp.draftProjectId = :anIdentity "
                + " order by  bit.draftProjectBinnacleDate desc ";
        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("anIdentity", anIdentity);
        return myQuery.list();
    }

  @Transactional(readOnly = true)
  @Override
  public Date getDateOfLastStatus(Long aDraftProjectId, Long aStatusId) 
  {
    String myQueryHQL = "Select draftProjectBinnacleDate from DraftProjectBinnacleEntity Bin"
            + " where Bin.draftProject.draftProjectId = :aDraftProjectId and "
            + " Bin.draftProjectStatusBinnacle.draftProjectStatusId =:aStatusId";
    Query myQuery =theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
    myQuery.setLong("aDraftProjectId", aStatusId);
    myQuery.setLong("aStatusId", aStatusId);
    return (Date) myQuery.list().get(0);
  }

  @Override
  public void Save(DraftProjectBinnacleEntity aDraftProjectBinnacleEntity) 
  {
    super.save(aDraftProjectBinnacleEntity);
  }

  @Transactional(readOnly = true)
  private DraftProjectEntity findDraftProjectById(Long aDraftProjectId)
  {
         String myQueryHQL;
        DraftProjectEntity find = null;
        try
        {
         find = itsDraftProjectDao.findById(aDraftProjectId, true);
         }
        catch(Exception ex)
        {
            Logger.getLogger(DraftProjectBinnacleDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return find;
  }
  

  @Override
  public void updateDraftProjectStatus(DraftProjectBinnacleEntity aDraftBinnacle)
  {
    DraftProjectEntity myDraftProject = findDraftProjectById(aDraftBinnacle.getDraftProject().getDraftProjectId());
    myDraftProject.setDraftProjectStatus(aDraftBinnacle.getDraftProjectStatus());
    this.itsDraftProjectDao.merge(myDraftProject);
    
  }
    @Transactional(readOnly = false)
  @Override
  public int getDraftProjectInDeterminateStatus(DraftProjectEntity aDraftProjectEntity)
  {
    String myQuery;
    int myResult = 0;
 try
        {
          myQuery = "select dpb  from DraftProjectBinnacleEntity  dpb where " 
                  +" dpb.draftProject.draftProjectId = " + aDraftProjectEntity.getDraftProjectId().toString() 
                  + " and dpb.draftProjectStatusBinnacle.draftProjectStatusId = " + aDraftProjectEntity.getDraftProjectStatus().getDraftProjectStatusId();
          myResult = super.find(myQuery).size();
        }
        catch(Exception ex)
        {
            Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }    
    return myResult;
  }
}
