/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ObservationDaoImpl
 *  Purpose:  Data manager for Observations.
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDaoImpl;
import com.abs.siif.programming.dto.ObservationDto;
import com.abs.siif.programming.entities.ObservationEntity;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FENIX-02
 */
@Repository("observationDao")
public class ObservationDaoImpl extends SIIFBaseDaoImpl<ObservationEntity, Long> implements
        ObservationDao{

    @Autowired
    private SessionFactory theirSessionFactory;
    
    @Override
    public SessionFactory getTheirSessionFactory() {
        return theirSessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ObservationDto> getObservationsByInvPreFileId(Long Id) {
        /*String myString = "select distinct obs from ObservationEntity obs"
                + " where obs.observationInvPreFile = :Id";*/
        String myString = "select obs.idprefichaobservacion, obs.idpreficha, "
            + "obs.comentario, obs.idperfil, usu.idusuario, per.descripcion as userdesc, "
            + "usu.usuario, usu.contrasenia, col.nombre, col.apepaterno, "
            + "col.apematerno, col.idcolectiva, emp.idempleado, dep.iddependencia, "
            + "dep.clave, dep.descripcion, obs.fechacomentario "
            + "from informix.siifpppprefichaobserv as obs "
            + "inner join siifabsusuario as usu on obs.idusuario=usu.idusuario "
            + "inner join siifabscolectiva as col on usu.idcolectiva=col.idcolectiva "
            + "inner join siifabsempleado as emp on col.idcolectiva=emp.idcolectiva "
            + "inner join siifabsdependencia as dep on emp.iddependencia=dep.iddependencia "
            + "inner join siifsegusuperfil as usuper on usuper.idusuario=usu.idusuario " 
            + "inner join siifsegperfil as per on per.idperfil=usuper.idperfil "
            + "where obs.idperfil=per.idperfil and obs.idpreficha=:Id";

        SQLQuery myQuery = getTheirSessionFactory().
                getCurrentSession().createSQLQuery(myString);
        myQuery.setLong("Id", Id);

        List myDtoList = getListFromSQLquery(myQuery , ObservationDto.class);
        return myDtoList;
    }
    
}
