/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.MensualComponentEntity;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
@Repository("mensualComponentImpl")
public class MensualComponentDaoImpl extends SIIFBaseDaoImpl<MensualComponentEntity, Long>
        implements MensualComponentDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<MensualComponentEntity> getMensualComponents() {
        return super.findAll();
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<MensualComponentEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    // MBA 20120514 : Se agrega código para que se elimine por Identificador del Componente
    @Transactional(readOnly = false)
    @Override
    public int deleteByComponent(ComponentEntity anEntity) {

        String myQueryHQL = "delete from MensualComponentEntity "
                + "where component.componentId = "
                + " :componentId";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        myQuery.setLong("componentId", anEntity.getComponentId());
        return myQuery.executeUpdate();
    }

    @Transactional(readOnly = true)
    @Override
    public MensualComponentEntity getMensualComponentById(Long anIdentity) {
        return findById(anIdentity, Boolean.TRUE);
    }

    // MBA 20120514 : Se agrega código para que se busque por Identificador del Componente
    @Transactional(readOnly = false)
    @Override
    public List<MensualComponentEntity> getMensualComponentByComponent(
            ComponentEntity anEntity) {

        return this.findByCriteria(
                Restrictions.eq("component.componentId",
                anEntity.getComponentId()));

    }

    // MBA 20120514 : Se agrega código para que se busque por Identificador del Componente y el Mes
    @Transactional(readOnly = true)
    @Override
    public List<MensualComponentEntity> getMensualComponentByComponentAndMonth(
            ComponentEntity anEntity, String aMonth) {

        return this.findByCriteria(
                Restrictions.eq("component.componentId",
                anEntity.getComponentId()),
                Restrictions.eq("conceptProg",
                aMonth));
    }
}
