/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.security.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.security.entities.UserEntity;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Miguel Baizabal Aguirre
 * Declaración de métodos para el Usuario
 */
public interface UserDao extends SIIFBaseDao<UserEntity, Long> {

    Collection<UserEntity> getAllUser();

    @Override
    void saveAll(Collection<UserEntity> anEntities);

    @Override
    void deleteAll(Collection<UserEntity> anEntities);

    UserEntity getUserById(Long anIdentity);

    List<UserEntity> verifyData(String aUserName);
    
    /**
     * Este metodo regresa la Dependencia a la que esta relacionado el usuario
     * que acceso al SIIF.
     * @param Id
     * @return 
     */
    public DepencenceDto getDependenceByUserId(Long Id);
    
}
