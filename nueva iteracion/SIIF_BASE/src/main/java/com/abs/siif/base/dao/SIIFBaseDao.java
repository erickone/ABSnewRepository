/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Israel Ruiz
 */
public interface SIIFBaseDao<T, ID extends Serializable> {

    /**
     * Eliminar la entidad
     *
     * @param entity que será eliminada
     */
    @Transactional(readOnly = false)
    void delete(T anEntity);

    /**
     *
     * @param anEntities Coleccion de entidades a eliminar
     */
    @Transactional(readOnly = false)
    void deleteAll(Collection<T> anEntities);

    /**
     *
     * @param anEntities Coleccion de entidades a persistir
     */
    @Transactional(readOnly = false)
    void saveAll(Collection<T> anEntities);

    /**
     * @return Este metodo regresa una Lista de todos los Niveles que existen en
     * la base de datos
     */
    @Transactional(readOnly = true)
    List find(String aQueryString);

    @Transactional(readOnly = true)
    List<T> findByCriteria(Criterion... anCriterions);

    @Transactional(readOnly = true)
    @SuppressWarnings(value = "unchecked")
    List<T> findAll();

    /**
     * Encontar la entidad por su llave el lock indica si requieres esperar a
     * que se libere en caso que este bloqueada por otra transacción
     *
     * @param id
     * @param lock
     * @return
     */
    @SuppressWarnings(value = "unchecked")
    T findById(ID anId, boolean aLock);

    /**
     * Obtener la lista oredenada por una columna
     *
     * @param columnOrder
     * @return
     */
    @Transactional(readOnly = true)
    List<T> getAllAndOrderByColumn(String aColumnOrder);

    /**
     * Obtiene la clase que esta haciendose persistencia.
     *
     * @return
     */
    Class<T> getPersistentClass();

    /**
     * Persistir entidad (Actualizar o salvar la entidad)
     *
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    T persist(T anEntity);

    /**
     * Persistir entidad (Actualizar o salvar la entidad)
     *
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    T save(T anEntity);

    /**
     * @return Este metodo regresa una Lista a partir de query's nativos que
     * tienen que ser creados atravez de SQLQuery
     */
    @Transactional(readOnly = true)
    List getListToNativeQuery(String aQueryString);

    /**
     *
     * @return
     */
    @Transactional(readOnly = true)
    List getHierarchicalStructures(String anIdentity, String aFields, String aTableName,
            String aIdentityName, String aFieldFatherName);

    @Transactional(readOnly = false)
    public T merge(T anEntity);

    @Transactional(readOnly = true)
    List getListFromSQLquery(String query, Class toTransform);

    @Transactional(readOnly = true)
    List getListFromSQLquery(SQLQuery mySQLQuery, Class toTransform);

    @Transactional(readOnly = true)
    Query createQuery(String myHQL);
}
