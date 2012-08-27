package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.entities.RegionalClassifierEntity;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import com.abs.siif.programming.dao.FinancialStructureDaoImpl;
import com.abs.siif.programming.dto.RegionalClassifierDTO;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
@Repository("RegionalClassifierDao")
public class RegionalClassifierDaoImpl extends SIIFBaseDaoImpl<RegionalClassifierEntity, Long>
        implements RegionalClassifierDao, Serializable
{

  @Autowired
  private SessionFactory theirSessionFactory;

  @Override
  public SessionFactory getTheirSessionFactory()
  {
    return theirSessionFactory;
  }

  @Cacheable("regionalClassifierAll")
  @Transactional(readOnly = true)
  @Override
  public Collection<RegionalClassifierEntity> getAllRegionalClassifier()
  {
    Collection<RegionalClassifierEntity> regionalClassifierEntitys = null;
    String myQueryHQL;
//        return super.getAllAndOrderByColumn("regionalClassifierDescription");
    try
    {
      myQueryHQL = "select re from RegionalClassifierEntity re"
              + " where re.regionalLevelClassifier.regionalLevelClassifierIsRegion = 't'"
              + "order by re.regionalClassifierDescription";
      regionalClassifierEntitys = this.find(myQueryHQL);
    }
    catch (Exception ex)
    {
      Logger.getLogger(FinancialStructureDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return regionalClassifierEntitys;
  }

  @CacheEvict(value = "regionalClassifierAll", allEntries = true)
  @Transactional(readOnly = false)
  @Override
  public void saveAll(Collection<RegionalClassifierEntity> anEntities)
  {
    super.saveAll(anEntities);
  }

  @CacheEvict(value = "regionalClassifierAll", allEntries = true)
  @Transactional(readOnly = false)
  @Override
  public void deleteAll(Collection<RegionalClassifierEntity> anEntities)
  {
    super.deleteAll(anEntities);
  }

  @Transactional(readOnly = true)
  @Override
  public RegionalClassifierEntity getRegionalClassifierById(
          Long anIdentity)
  {
    return findById(anIdentity, Boolean.TRUE);
  }

  @Transactional(readOnly = true)
  @Override
  public List<RegionalClassifierEntity> findUbicationByScope(
          RegionalLevelClassifierEntity regionLevelentity)
  {
    return this.findByCriteria(
            Restrictions.eq(
            "regionalLevelClassifier.regionalLevelClassifierId",
            regionLevelentity.getRegionalLevelClassifierId()));
  }

  @Transactional(readOnly = true)
  @Override
  public List<RegionalClassifierDTO> findUbication(
          RegionalClassifierEntity ubication)
  {

    RegionalClassifierEntity father =
            getRegionalClassifierById(ubication.getRegionalClassifierId());

    String query = this.getMessage("siif.dao.nativequeries.SQLUbication",
            new String[]
            {
              ubication.getRegionalClassifierId().toString()
            });

    List childs = this.getListFromSQLquery(query, RegionalClassifierDTO.class);

    return childs;
  }

  @Transactional(readOnly = true)
  @Override
  public Long findFather(RegionalClassifierEntity region)
  {
    Long idRegLevel = 0L;
    String sqlSentence = "SELECT rc.regionalLevelClassifier.regionalLevelClassifierId"
            + " FROM RegionalClassifierEntity rc WHERE"
            + " rc.regionalClassifierId = " + region.getRegionalClassifierId();

    List foundedValues = super.find(sqlSentence);
    if (foundedValues != null && foundedValues.size() > 0)
    {
      idRegLevel = (Long) foundedValues.get(0);
    }
    return idRegLevel;
  }

  @Transactional(readOnly = true)
  @Override
  public String findFatherDesc(RegionalClassifierEntity region)
  {
    String idRegLevel = "";
    String sqlSentence = "SELECT rc.regionalLevelClassifier.regionalLevelClassifierDescription"
            + " FROM RegionalClassifierEntity rc WHERE"
            + " rc.regionalClassifierId = " + region.getRegionalClassifierId();

    List foundedValues = super.find(sqlSentence);
    if (foundedValues != null && foundedValues.size() > 0)
    {
      idRegLevel = (String) foundedValues.get(0);
    }
    return idRegLevel;
  }

  @Transactional(readOnly = true)
  @Override
  public List<RegionalClassifierEntity> getRegionsByFatherId(RegionalClassifierEntity aRegionFather)
  {
    String myQueryString = "select RegClas from RegionalClassifierEntity RegClas"
            + " where RegClas.regionalClassifierFather.regionalClassifierId = :fatherRegionId";

    Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryString);
    myQuery.setLong("fatherRegionId", aRegionFather.getRegionalClassifierId());

    return (List<RegionalClassifierEntity>) myQuery.list();
  }

  @Transactional(readOnly = true)
  @Override
  public Collection<RegionalClassifierEntity> getRegionalClassifierWithoutFather()
  {
    String myQueryString = "select RegClas from RegionalClassifierEntity RegClas "
            + " where RegClas.regionalClassifierFather.regionalClassifierId is null";
    return super.find(myQueryString);
  }

  @Transactional(readOnly = false)
  @Override
  public Long saveOrUpdateRegion(RegionalClassifierEntity aRegionalClassifierEntity)
  {
        Long find = null;
    try
    {
        if (aRegionalClassifierEntity.getRegionalClassifierId() != Long.valueOf(0) 
                && aRegionalClassifierEntity.getRegionalClassifierId() != null)
        {
          super.merge(aRegionalClassifierEntity);
        }
        else
        {
          super.save(aRegionalClassifierEntity);
        }
       RegionalClassifierEntity myRegion = getRegionalClassifierById(aRegionalClassifierEntity.getRegionalClassifierId());
        
        find = myRegion.getRegionalClassifierId();
    }
    catch (Exception ex)
    {
      Logger.getLogger(RegionalClassifierDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return find;
  }

  @Override
  public void deleteRegionalClassifier(RegionalClassifierEntity aRegionalClassifierEntity)
  {
    super.delete(aRegionalClassifierEntity);
  }
}