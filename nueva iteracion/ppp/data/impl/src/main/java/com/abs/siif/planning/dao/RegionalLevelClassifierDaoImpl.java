package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.entities.RegionalLevelClassifierEntity;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 */

@Repository("regionalLevelClassifierDao")
public class RegionalLevelClassifierDaoImpl extends SIIFBaseDaoImpl<RegionalLevelClassifierEntity, Long>
            implements RegionalLevelClassifierDao{

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<RegionalLevelClassifierEntity> getAllRegionalLevelClassifier() {
        return super.getAllAndOrderByColumn("regionalLevelClassifierLevel");
    }

    @Transactional(readOnly = false)
    @Override
    public void saveAll(Collection<RegionalLevelClassifierEntity> anEntities) {
        super.saveAll(anEntities);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<RegionalLevelClassifierEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    @Transactional(readOnly = true)
    @Override
    public RegionalLevelClassifierEntity getRegionalLevelClassifierById(Long anIdentity) {
        return findById(anIdentity, Boolean.TRUE);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RegionalLevelClassifierEntity> findUbicationScope() {
        return this.findByCriteria(
                Restrictions.eq("regionalLevelClassifierIsScope",true));
    }
    
    @Transactional(readOnly = true)
    @Override
    public RegionalLevelClassifierEntity getRegionalClassifierLevelByLevel(int aFCLevel)
    {
        Criterion myCriterion = Restrictions.eq("regionalLevelClassifierLevel", aFCLevel);

        List<RegionalLevelClassifierEntity> myRegClassifierLevels = super.findByCriteria(myCriterion);

        return (myRegClassifierLevels.size() > 0
                ? myRegClassifierLevels.get(0) : null);
    }

     /**
     *
     * @return Regresa un entero corto que nos indica que nivel podemos dar de
     * alta, en caso de que no haya ningun registro en la base de datos nos
     * devuelve un 0
     */
    @Transactional(readOnly = true)
    @Override
    public int getLastRegionalLevel()
    {
        String myQueryHQL = "select MAX(RCL.regionalLevelClassifierLevel) from RegionalLevelClassifierEntity RCL";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        Integer myLastLevel = 0;
        List myList = myQuery.list();
        if (myList.get(0) != null) {
            myLastLevel = (Integer) myList.get(0);
        }
        return myLastLevel;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isRegClassifKeyValid(String aKey)
    {
        boolean isValid;
        String myQueryHQL = "select COUNT(RCL.regionalLevelClassifierId) from RegionalLevelClassifierEntity RCL "
                + "where RCL.regionalLevelClassifierKey = :aKey";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL).
            setString("aKey", aKey);
        Long myCounter;
        List myList = myQuery.list();
        myCounter = (Long) myList.get(0);
        if(myCounter == 0)
            isValid = true;
        else
            isValid = false;
        
        return isValid;
    }

    /**
     * Este metodo sirve para guardar una entidad Regional Classifier Level
     * @param anEntity
     * @return 
     */
    @Transactional(readOnly = false)
    @Override
    public Long saveOrUpdate(RegionalLevelClassifierEntity anEntity)
    {
        theirSessionFactory.getCurrentSession().saveOrUpdate(anEntity);
        return anEntity.getRegionalLevelClassifierId();
    }

    @Transactional(readOnly = true)
    @Override
    public RegionalLevelClassifierEntity existRegClassifLevelWithCensusActive(RegionalLevelClassifierEntity anRCLevel)
    {
        return (RegionalLevelClassifierEntity) theirSessionFactory.getCurrentSession().createCriteria(RegionalLevelClassifierEntity.class).
                add(Restrictions.eq("regionalLevelClassifierIsCensus", true)).uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public RegionalLevelClassifierEntity existRegClassifLevelWithGenderActive(RegionalLevelClassifierEntity anRCLevel)
    {
        return (RegionalLevelClassifierEntity) theirSessionFactory.getCurrentSession().createCriteria(RegionalLevelClassifierEntity.class).
                add(Restrictions.eq("regionalLevelClassifierIsGender", true)).uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public RegionalLevelClassifierEntity existRegClassifLevelWithStateActive(RegionalLevelClassifierEntity anRCLevel)
    {
        return (RegionalLevelClassifierEntity) theirSessionFactory.getCurrentSession().createCriteria(RegionalLevelClassifierEntity.class).
                add(Restrictions.eq("regionalLevelClassifierIsState", true)).uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public RegionalLevelClassifierEntity existRegClassifLevelWithMunicipalityActive(RegionalLevelClassifierEntity anRCLevel)
    {
        return (RegionalLevelClassifierEntity) theirSessionFactory.getCurrentSession().createCriteria(RegionalLevelClassifierEntity.class).
                add(Restrictions.eq("regionalLevelClassifierIsMunicipality", true)).uniqueResult();
    }

    @Transactional(readOnly = true)
    @Override
    public RegionalLevelClassifierEntity existRegClassifLevelWithPostalCodeActive(RegionalLevelClassifierEntity anRCLevel)
    {
        return (RegionalLevelClassifierEntity) theirSessionFactory.getCurrentSession().createCriteria(RegionalLevelClassifierEntity.class).
                add(Restrictions.eq("regionalLevelClassifierIsPostalCode", true)).uniqueResult();
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(List<RegionalLevelClassifierEntity> anIdentities)
    {
        super.deleteAll(anIdentities);
    }

}
