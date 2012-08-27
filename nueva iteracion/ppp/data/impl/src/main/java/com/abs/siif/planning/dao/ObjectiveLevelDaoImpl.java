package com.abs.siif.planning.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("objectiveLevelDao")
public class ObjectiveLevelDaoImpl extends SIIFBaseDaoImpl<ObjectiveLevelEntity, Long> 
        implements ObjectiveLevelDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    /**
     * @return Este metodo regresa una Lista de todos los Niveles que existen en
     * la base de datos
     */
    @Transactional(readOnly = true)
    @Override
    public List<ObjectiveLevelEntity> getAllObjectiveLevels() {
        return super.getAllAndOrderByColumn("objectiveLevel");
    }

    /**
     *
     * @param objectiveLevel
     * @return Este metodo sirve para guardar una entidad Objetive Level en la
     * base de datos
     * @throws ObjectiveLevelDaoException Envia una excepcion en caso de
     * encontrar un error en el manejo de la base de datos, como que se desee
     * guardar un registro, con una clave de nivel que ya exista, o que se desee
     * repetir la clave que ya se habia definido
     *
     */
    @Transactional(readOnly = false)
    @Override
    public Long saveOrUpdate(ObjectiveLevelEntity anObjectiveLevel) {
        //return super.persist(objectiveLevel).getObjectiveLevelId();
        theirSessionFactory.getCurrentSession().saveOrUpdate(anObjectiveLevel);
        return anObjectiveLevel.getObjectiveLevelId();
    }

    /**
     * @param objectiveLevelId Este metodo recibe un String para realizar la
     * busqueda en la base de datos.
     * @return Este metodo regresa la entidad en la base de datos que tiene el
     * Id que le enviamos
     */
    @Transactional(readOnly = true)
    @Override
    public ObjectiveLevelEntity getObjetiveLevelByIdentity(Long anObjectiveLevelId) {
        return super.findById(anObjectiveLevelId, true);

    }

    /**
     *
     * @param aLevel Es un entero corto que sirve para realizar la busqueda en
     * la base de datos
     * @return Regresa la entidad que tiene el nivel que le fue proporcionado
     */
    @Transactional(readOnly = true)
    @Override
    public ObjectiveLevelEntity getObjectiveLevelByLevel(short aLevel) {
        Criterion myCriterion = Restrictions.eq("objectiveLevel", aLevel);

        List<ObjectiveLevelEntity> myObjectiveLevels = super.findByCriteria(myCriterion);

        return (myObjectiveLevels.size() > 0
                ? myObjectiveLevels.get(0) : null);
    }

    /**
     *
     * @return Regresa un entero corto que nos indica que nivel podemos dar de
     * alta, en caso de que no haya ningun registro en la base de datos nos
     * devuelve un 0
     */
    @Transactional(readOnly = true)
    @Override
    public short getLastObjectiveLevel() {

        String myQueryHQL = "select MAX(OL.objectiveLevel) from ObjectiveLevelEntity OL";

        Query myQuery = theirSessionFactory.getCurrentSession().createQuery(myQueryHQL);
        Short myLastLevel = 0;
        List myList = myQuery.list();
        if (myList.get(0) != null) {
            myLastLevel = (Short) myList.get(0);
        }
        return myLastLevel;
    }

    /**
     *
     * @return Regresa la entidad de la base de datos que tiene activado el
     * Encuadre UEG
     */
    @Transactional(readOnly = true)
    @Override
    public ObjectiveLevelEntity getObjectiveLevelRelationshipUEG() {
        return (ObjectiveLevelEntity) theirSessionFactory.getCurrentSession().createCriteria(ObjectiveLevelEntity.class).
                add(Restrictions.eq("objectiveLevelShowBudgetKey", true)).uniqueResult();
    }

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(List<ObjectiveLevelEntity> anIdentities) {
        super.deleteAll(anIdentities);
    }
}