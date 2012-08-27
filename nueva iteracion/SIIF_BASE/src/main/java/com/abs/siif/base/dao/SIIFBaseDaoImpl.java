/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.base.dao;

import com.abs.siif.base.SIIFBase;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * CLase base que contiene los operaciones comunes de la capa de persistencia,
 * el manejo se establece por el uso del Hibernate y Spring JPA. T será la
 * entity que se esta trabajando el ID es la llave que requiere la entidad para
 * operar Las clases hijas deberán de proporcionar ela sesion para porceder con
 * operacionesdel padre
 *
 * @author Israel Ruiz
 */
public abstract class SIIFBaseDaoImpl<T, ID extends Serializable>
        extends SIIFBase implements SIIFBaseDao<T, ID> {

    private Class<T> persistentClass;

    public SIIFBaseDaoImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public abstract SessionFactory getTheirSessionFactory();

    /**
     * Obtiene la clase que esta haciendose persistencia.
     *
     * @return
     */
    @Override
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    /**
     * @return Este metodo regresa una Lista de todos los Niveles que existen en
     * la base de datos
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    /**
     * Obtener la lista oredenada por una columna
     *
     * @param columnOrder
     * @return
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public List<T> getAllAndOrderByColumn(String aColumnOrder) {

        return getTheirSessionFactory().getCurrentSession().
                createCriteria(getPersistentClass()).
                addOrder(Order.asc(aColumnOrder)).list();

    }

    /**
     * Actualizar o salvar la entidad
     *
     * @param entity
     * @return
     */
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    @Override
    public T persist(T anEntity) {
        getTheirSessionFactory().getCurrentSession().saveOrUpdate(anEntity);

        return anEntity;
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    @Override
    public T merge(T anEntity) {
        getTheirSessionFactory().getCurrentSession().merge(anEntity);
        return anEntity;
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    @Override
    public T save(T anEntity) {
        Serializable save = getTheirSessionFactory().getCurrentSession().save(anEntity);

        return anEntity;
    }

    /**
     * Eliminar la entidad
     *
     * @param entity que será eliminada
     */
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void delete(T anEntity) {
        getTheirSessionFactory().getCurrentSession().delete(anEntity);
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void deleteAll(Collection<T> anEntities) {
        for (T t : anEntities) {
            delete(t);
        }
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void saveAll(Collection<T> anEntities) {

        for (T t : anEntities) {
            persist(t);
        }
    }

    /**
     * Encontar la entidad por su llave el lock indica si requieres esperar a
     * que se libere en caso que este bloqueada por otra transacción
     *
     * @param id
     * @param lock
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public T findById(ID anId, boolean aLock) {
        T anEntity;
        if (aLock) {
            anEntity = (T) getTheirSessionFactory().getCurrentSession().
                    load(getPersistentClass(), anId, LockOptions.UPGRADE);
        } else {
            anEntity = (T) getTheirSessionFactory().getCurrentSession().
                    load(getPersistentClass(), anId);
        }

        return anEntity;
    }

    /**
     * Busqueda la lista de la entidad dado una serie de criterios este methodo
     * puede ser utilizada directamente por las clases que heredan y
     * particulizar como lo necesiten
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public List<T> findByCriteria(Criterion... anCriterions) {
        Session sess = getTheirSessionFactory().getCurrentSession();
        Criteria crit = sess.createCriteria(getPersistentClass());
        for (Criterion c : anCriterions) {
            crit.add(c);
        }
        return crit.list();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public List find(String aQueryString) {
        List result;

        // Obtiene los filtros que pueden ser aplicables para el query
        Collection<?> resultFilter = (Collection<?>) SIIFContextBase.getParameterSession(
                SessionKeyEnum.VIEW_USER_CONSTRAINS);

        if (resultFilter != null) {
            result = new ArrayList(resultFilter);
            SIIFContextBase.setParameterSession(
                    SessionKeyEnum.VIEW_USER_CONSTRAINS, null);
        } else {
            Query myQuery = getTheirSessionFactory().getCurrentSession().
                    createQuery(aQueryString);
            result = myQuery.list();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public List getListToNativeQuery(String anQueryString) {
        SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(anQueryString);
        return mySQLQuery.list();
    }

    /**
     * A partir de un del id de un elemento jerarquico obtiene toda la coleccion
     * de sus antecesores (Padre,Abuelo,Biabuelo)
     *
     * @param aConditional Id de elemento a buscar abstraidos en forma de
     * condiciones por ejemplo (idobjetivo='item') ó ((idobjetivo='3') or
     * (idobjetivo='4'))
     * @param aFields indica los campos a mostrar en el query puede realizarse
     * un query por (*)
     * @param aTableName Nombre de la tabla del elemento jerarquico
     * @param aIdentityName Nombre del primary key de la tabla jerarquica
     * @param aFieldFatherName Nombre del campo que es el padre dentro de la
     * estructura jerarquica
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public List getHierarchicalStructures(String aConditional, String aFields, String aTableName,
            String anIdentityName, String aFieldFatherName) {

        String myQueryString = getMessage("siif.dao.nativequeries.SQLHierarchies",
                aFields, aTableName, aConditional, aFieldFatherName, anIdentityName);

        return getListToNativeQuery(myQueryString);
    }

    /**
     * Obtiene una lista a partir de un query nativo y transforma el resultado
     * en una lista de objetos de tipo dados por el parametro "toTransform"
     *
     * @param query
     * @param toTransform
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public List getListFromSQLquery(String query, Class toTransform) {
        List result;
        // Obtiene los filtros que pueden ser aplicables para el query
        Collection<?> resultFilter = (Collection<?>) SIIFContextBase.getParameterSession(
                SessionKeyEnum.VIEW_USER_CONSTRAINS);

        if (resultFilter != null) {
            result = new ArrayList(resultFilter);
            SIIFContextBase.setParameterSession(
                    SessionKeyEnum.VIEW_USER_CONSTRAINS, null);
        } else {

            SQLQuery mySQLQuery = getTheirSessionFactory().getCurrentSession().createSQLQuery(query);
            mySQLQuery.setResultTransformer(Transformers.aliasToBean(toTransform));
            result = mySQLQuery.list();
        }
        return result;
    }

    /**
     * Obtiene una lista a partir de un query nativo y transforma el resultado
     * en una lista de objetos de tipo dados por el parametro "toTransform"
     *
     * @param query
     * @param toTransform
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public List getListFromSQLquery(SQLQuery mySQLQuery, Class toTransform) {
        List result;
        // Obtiene los filtros que pueden ser aplicables para el query
        Collection<?> resultFilter = (Collection<?>) SIIFContextBase.getParameterSession(
                SessionKeyEnum.VIEW_USER_CONSTRAINS);

        if (resultFilter != null) {
            result = new ArrayList(resultFilter);
            SIIFContextBase.setParameterSession(
                    SessionKeyEnum.VIEW_USER_CONSTRAINS, null);
        } else {

            mySQLQuery.setResultTransformer(Transformers.aliasToBean(toTransform));
            result = mySQLQuery.list();
        }
        return result;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    @Override
    public Query createQuery(String myHQL) {
        return getTheirSessionFactory().getCurrentSession().createQuery(myHQL);
    }
}
