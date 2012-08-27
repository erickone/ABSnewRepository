/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.security.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.security.entities.UserEntity;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Miguel Baizabal Aguirre
 */
@Repository("userDao")
public class UserDaoImpl extends SIIFBaseDaoImpl<UserEntity, Long>
        implements UserDao {

    @Autowired
    private SessionFactory theirSessionFactory;

    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<UserEntity> getAllUser() {
        return super.getAllAndOrderByColumn("userDescription");
    }

    @Transactional(readOnly = false)
    @Override
    public void saveAll(Collection<UserEntity> anEntities) {
        super.saveAll(anEntities);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAll(Collection<UserEntity> anEntities) {
        super.deleteAll(anEntities);
    }

    @Transactional(readOnly = true)
    @Override
    public UserEntity getUserById(Long anIdentity) {
        return findById(anIdentity, Boolean.TRUE);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> verifyData(String aUserName) {
        String myQueryString = "FROM UserEntity as user"
                + " WHERE user.userName = :user ";

        Query myQuery = getTheirSessionFactory().
                getCurrentSession().createQuery(myQueryString);

        myQuery.setString("user", aUserName.trim());
   
        return myQuery.list();
    }

    @Transactional(readOnly = true)
    @Override
    public DepencenceDto getDependenceByUserId(Long Id) {
        String myQueryString = "select dep.clave, dep.descripcion as nameDepend,"
                + " dep.iddependencia as idDependency"
                + " from informix.siifabsdependencia as dep"
                + " inner join siifabsempleado as emp on dep.iddependencia=emp.iddependencia" 
                + " inner join siifabscolectiva as col on emp.idcolectiva=col.idcolectiva" 
                + " inner join siifabsusuario as usu on col.idcolectiva=usu.idcolectiva" 
                + " where usu.idusuario=:Id"; 
        
        SQLQuery myQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myQueryString);
        myQuery.setLong("Id", Id);

        List myDtoList = getListFromSQLquery(myQuery , DepencenceDto.class);
        return (DepencenceDto)myDtoList.get(0);
    }
    
}
