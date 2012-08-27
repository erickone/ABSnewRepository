/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.SefinValidationEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jacob.flores
 */

@Repository("sefinValidationDao")
public class SefinValidationDaoImpl extends SIIFBaseDaoImpl<SefinValidationEntity, Long>
        implements SefinValidationDao
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
  public List<Long> saveSefinValidation(List<SefinValidationEntity> aSefinValidationEntity)
  {
    List find = null;
    try
    {
      for (SefinValidationEntity validation : aSefinValidationEntity)
      {
        if (validation.getValidationId() != null)
        {
          super.merge(validation);
        }
        else
        {
          super.save(validation);
        }
      }
      find = getSefinValidationByComponent(aSefinValidationEntity.iterator().next().getComponent());
    }
    catch (Exception ex)
    {
      Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return find;
  }

  @Transactional(readOnly = true)
  @Override
  public List<SefinValidationEntity> getSefinValidationByComponent(ComponentEntity aComponentEntity)
  {
    String myQueryString = "select Validation from SefinValidationEntity Validation"
            + " left outer join Validation.Component Component"
            + " where Component.componentId = " + aComponentEntity.getComponentId();
    return super.find(myQueryString);
  }
}