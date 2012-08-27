/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ViewEntity
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.security.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Miguel Baizabal Aguirre
 *
 * Esta entidad que define los atributos de relación entre una vista, un módulo
 * y usuario
 *
 */
@Entity
@Table(name = "siifsegusumodvista")
public class ViewUserModuleEntity implements
        Serializable, Comparable<ViewUserModuleEntity> {

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdUsuModVista", nullable = false)
    private Long viewUserModuleId;
    // Identificador de la Vista
    
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "IdVista", nullable = false)
    private ViewEntity viewUserModuleView;
    // Identificador del Usuario
    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false)
    private UserEntity viewUserModuleUser;

//    // Identificador del Módulo
//    @Column(name = "IdModulo", nullable=false)
//    private int viewUserModuleModule;
//    public int getViewUserModuleModule() {
//        return viewUserModuleModule;
//    }
//
//    public void setViewUserModuleModule(int viewUserModuleModule) {
//        this.viewUserModuleModule = viewUserModuleModule;
//    }

    public Long getViewUserModuleId() {
        return viewUserModuleId;
    }

    public void setViewUserModuleId(Long viewUserModuleId) {
        this.viewUserModuleId = viewUserModuleId;
    }

    public UserEntity getViewUserModuleUser() {
        return viewUserModuleUser;
    }

    public void setViewUserModuleUser(UserEntity viewUserModuleUser) {
        this.viewUserModuleUser = viewUserModuleUser;
    }

    public ViewEntity getViewUserModuleView() {
        return viewUserModuleView;
    }

    public void setViewUserModuleView(ViewEntity viewUserModuleView) {
        this.viewUserModuleView = viewUserModuleView;
    }

    @Override
    public int compareTo(ViewUserModuleEntity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
